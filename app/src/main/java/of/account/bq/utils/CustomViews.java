package of.account.bq.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CustomViews extends View  {
    private Paint mPaint;
    private Context mContext;
    private int screenWidth;
    private int screenHeight;
    private int radial = 150;
    private Rect rect;
    private int y = 420;
    private int x = 0;
    private float a;
    private float b;
    boolean up = true;
    boolean down = false;

    public CustomViews(Context context) {
        super(context, null);
        init();
    }

    public CustomViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    /**
     * 由于onDraw()方法会不停的绘制 所以需要定义一个初始化画笔方法 避免导致不停创建造成内存消耗过大
     */
    private void init() {
        mPaint = new Paint();        // 设置画笔为抗锯齿
        mPaint.setAntiAlias(true);        // 设置颜色为红色
        mPaint.setColor(Color.parseColor("#3F51B5"));        /**         * 画笔样式分三种：
         1.Paint.Style.STROKE：描边
         2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充		 */
        mPaint.setStyle(Paint.Style.STROKE);
        /**         * 设置描边的粗细，单位：像素px 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素		 */
        mPaint.setStrokeWidth(2);
        /**         * 获取屏幕的宽高		 */
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);        /**         * 绘制圆环drawCircle的前两个参数表示圆心的XY坐标， 这里我们用到了一个工具类获取屏幕尺寸以便将其圆心设置在屏幕中心位置，		 * 第三个参数是圆的半径，第四个参数则为我们的画笔		 * 		 */
        canvas.drawCircle(screenWidth / 2-850, screenHeight / 2, radial, mPaint);
        rect = new Rect(screenWidth / 2 - x-850, y, screenWidth / 2 + x-850, y + 2);
        canvas.drawRect(rect, mPaint);
        if (y >= 430) {
            up = false;
            down = true;
        }
        if (y <= 270) {
            up = true;
            down = false;
        }
        if (up) {
            y = y + 3;
        }
        if (down) {
            y = y - 3;
        }
        if (y <= 350) {
            x = y / 2 - 23;
        }
        if (y >= 350) {
            x = 330 - y / 2;
        }
        invalidate();
    }

}