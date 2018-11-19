package of.account.bq.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import of.account.bq.R;


public class FingerImageView extends ImageView {

    public FingerImageView(Context context) {
        super(context);
    }

    public FingerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FingerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startGif() {
        // 初始化
        setAlpha(0f);
        setScaleX(1f);
        setScaleY(1f);

        // 设置帧动画
        setBackgroundResource(R.drawable.finger_gif);
        // 帧动画开始
        ((AnimationDrawable) getBackground()).start();

        // 开启渐变动画
        ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(200).start();
    }
    public void startGif_step1() {
        // 初始化
        setAlpha(0f);
        setScaleX(1f);
        setScaleY(1f);

        // 设置帧动画
        setBackgroundResource(R.drawable.finger_gif_step1);
        // 帧动画开始
        ((AnimationDrawable) getBackground()).start();

        // 开启渐变动画
        ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(200).start();
    }
    public void startGif_step2() {
        // 初始化
        setAlpha(0f);
        setScaleX(1f);
        setScaleY(1f);

        // 设置帧动画
        setBackgroundResource(R.drawable.finger_gif_step2);
        // 帧动画开始
        ((AnimationDrawable) getBackground()).start();

        // 开启渐变动画
        ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(200).start();
    }

    public void startGif_step3() {
        // 初始化
        setAlpha(0f);
        setScaleX(1f);
        setScaleY(1f);

        // 设置帧动画
        setBackgroundResource(R.drawable.finger_gif_step3);
        // 帧动画开始
        ((AnimationDrawable) getBackground()).start();

        // 开启渐变动画
        ObjectAnimator.ofFloat(this, "alpha", 0, 1).setDuration(200).start();
    }




}
