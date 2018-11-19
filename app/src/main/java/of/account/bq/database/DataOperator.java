package of.account.bq.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import of.account.bq.Bean.PersonInfo;


public class DataOperator {
    private PersonDataHelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    private String TAG = "Music";

    public DataOperator(Context context) {
        dbHelper = new PersonDataHelper(context, "PersonInfo", null, 1);
        this.context = context;
        db = dbHelper.getWritableDatabase();
        Log.i(TAG, "");
    }


    public void add(PersonInfo personInfo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Bitmap bitmap = getAppIcon(personInfo.getFace());
        //第二步，声明并创建一个输出字节流对象
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //第三步，调用compress将Bitmap对象压缩为PNG格式，第二个参数为PNG图片质量，第三个参数为接收容器，即输出字节流os
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        //第四步，将输出字节流转换为字节数组，并直接进行存储数据库操作，注意，所对应的列的数据类型应该是BLOB类型
        ContentValues cv = new ContentValues();
        cv.put("face", os.toByteArray());//图片转为二进制
        cv.put("fingerId", personInfo.getFingerId());
        cv.put("name", personInfo.getName());
        db.insert("person_info", null, cv);
        Log.i(TAG, "insert succeed");
    }



    public void delete(String fingerId) {
        db.execSQL("delete from person_info where fingerId=?", new String[]{fingerId});
    }


    public static Bitmap getAppIcon(Drawable drawable) {

        try {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof AdaptiveIconDrawable) {
                Drawable backgroundDr = ((AdaptiveIconDrawable) drawable).getBackground();
                Drawable foregroundDr = ((AdaptiveIconDrawable) drawable).getForeground();

                Drawable[] drr = new Drawable[2];
                drr[0] = backgroundDr;
                drr[1] = foregroundDr;

                LayerDrawable layerDrawable = new LayerDrawable(drr);

                int width = layerDrawable.getIntrinsicWidth();
                int height = layerDrawable.getIntrinsicHeight();

                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);

                layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                layerDrawable.draw(canvas);

                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "Select * from person_info where  fingerId =?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        Log.i(TAG, "add: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }


    // 查询所有的注册信息
    public List<PersonInfo> queryMany() {
        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();
        Cursor c = db.rawQuery("select * from person_info", null);
        while (c.moveToNext()) {
            PersonInfo appInfo = new PersonInfo();
            //第一步，从数据库中读取出相应数据，并保存在字节数组中
            byte[] blob = c.getBlob(c.getColumnIndex("face"));
            //第二步，调用BitmapFactory的解码方法decodeByteArray把字节数组转换为Bitmap对象
            Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
            //第三步，调用BitmapDrawable构造函数生成一个BitmapDrawable对象，该对象继承Drawable对象，所以在需要处直接使用该对象即可
            BitmapDrawable bd = new BitmapDrawable(bmp);
            appInfo.setFace(bd);
            appInfo.setFingerId(c.getString(1));
            appInfo.setName(c.getString(2));
            personInfos.add(appInfo);
        }
        c.close();
        return personInfos;
    }

}