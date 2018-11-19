package of.account.bq.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonDataHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERDATA = "create table if not exists person_info(face BLOB,fingerId varchar(30),name varchar(30))";
    public final static String dbName = "PersonInfo";
    private Context mContext;


    public PersonDataHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, dbName, cursorFactory, version);
        mContext = context;
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);

        Log.i("person", "onCreate succeed");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists person_info");

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}