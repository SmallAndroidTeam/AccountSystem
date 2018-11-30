package of.account.bq.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import of.account.bq.R;
import of.account.bq.database.DataOperator;
import of.account.bq.fragment.FingerPrintEnteringFragment;
import of.account.bq.fragment.HolographicFragment;
import of.account.bq.fragment.SeatFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView holographic;
    private TextView fingerprint;
    private TextView seat;
    private TextView returnTv;

    private RelativeLayout holographicRelativeLayout;
    private RelativeLayout fingerprintRelativeLayout;
    private RelativeLayout seatRelativeLayout;
    private RelativeLayout returnRelativeLayout;

    public Fragment fingerPrint;
    public static Fragment fragmentreplace;
    public static Fragment holoreplace;
    private Fragment seatFragment;
    public static Fragment holographicFragment;
    private ImageView bgmenu;
    public static DataOperator dataOperator;
    public static boolean flag = false;
    public static boolean flag_holo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
        initShowFragment();
        dataOperator = new DataOperator(MainActivity.this);
    }

    private void initShowFragment() {
        fingerprint.callOnClick();
    }


    private void initEvents() {
        holographic.setOnClickListener(this);
        fingerprint.setOnClickListener(this);
        seat.setOnClickListener(this);
        returnTv.setOnClickListener(this);

    }

    private void initView() {
        holographic = this.findViewById(R.id.tv_holographic);
        fingerprint = this.findViewById(R.id.tv_fingerprint);
        seat = this.findViewById(R.id.tv_seat);
        returnTv = this.findViewById(R.id.tv_return);
        fingerprintRelativeLayout = this.findViewById(R.id.rl_fingerprint);
        seatRelativeLayout = this.findViewById(R.id.rl_seat);
        holographicRelativeLayout = this.findViewById(R.id.rl_holographic);
        returnRelativeLayout = this.findViewById(R.id.rl_return);
        bgmenu = this.findViewById(R.id.bgmenu);
    }

    //设置view透明度变化
    private void setTextViewAlphaChange(View view) {
        Animation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(500);
        view.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        //设置图片动画
        Fade fade = new Fade();
        fade.setDuration(500);
        TransitionSet transitionSet = new TransitionSet().addTransition(fade);
        TransitionManager.beginDelayedTransition((ViewGroup) bgmenu.getParent(), transitionSet);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        initTextViewColor();
        switch (v.getId()) {
            case R.id.tv_fingerprint:
                setTextViewAlphaChange(fingerprint);
                fingerprint.setTextColor(getResources().getColor(R.color.textSelect));
                fingerprintRelativeLayout.setVisibility(View.VISIBLE);
                holographicRelativeLayout.setVisibility(View.INVISIBLE);
                seatRelativeLayout.setVisibility(View.INVISIBLE);
                returnRelativeLayout.setVisibility(View.INVISIBLE);
                if (fingerPrint == null) {
                    fingerPrint = new FingerPrintEnteringFragment();
                    fragmentTransaction
                            .addToBackStack(null)
                            .add(R.id.mainFragment, fingerPrint);
                } else {
                    if (flag) {
                        fragmentTransaction.show(fragmentreplace);
                    } else {
                        fragmentTransaction.show(fingerPrint);
                    }
                }
                break;

            case R.id.tv_seat:
                setTextViewAlphaChange(seat);
                seat.setTextColor(getResources().getColor(R.color.textSelect));
                holographicRelativeLayout.setVisibility(View.INVISIBLE);
                fingerprintRelativeLayout.setVisibility(View.INVISIBLE);
                seatRelativeLayout.setVisibility(View.VISIBLE);
                returnRelativeLayout.setVisibility(View.INVISIBLE);

                if (seatFragment == null) {
                    seatFragment = new SeatFragment();
                    fragmentTransaction.add(R.id.mainFragment, seatFragment);
                } else {

                    fragmentTransaction.show(seatFragment);
                }
                break;
            case R.id.tv_holographic:
                setTextViewAlphaChange(holographic);
                holographic.setTextColor(getResources().getColor(R.color.textSelect));
                holographicRelativeLayout.setVisibility(View.VISIBLE);
                fingerprintRelativeLayout.setVisibility(View.INVISIBLE);
                seatRelativeLayout.setVisibility(View.INVISIBLE);
                returnRelativeLayout.setVisibility(View.INVISIBLE);
                if (holoreplace == null) {
                    holoreplace = new HolographicFragment();
                    fragmentTransaction
                            .add(R.id.mainFragment, holoreplace);
                } else {
                   // if (flag_holo) {
                        fragmentTransaction.show(holoreplace);
//                    } else {
//                        fragmentTransaction.show(holographicFragment);
//                    }

                }

                break;
            case R.id.tv_return:
                setTextViewAlphaChange(returnTv);
                returnTv.setTextColor(getResources().getColor(R.color.textSelect));
                holographicRelativeLayout.setVisibility(View.INVISIBLE);
                fingerprintRelativeLayout.setVisibility(View.INVISIBLE);
                seatRelativeLayout.setVisibility(View.INVISIBLE);
                returnRelativeLayout.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
        fragmentTransaction.commit();
    }

    //隐藏所有的fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fingerPrint != null) {
            fragmentTransaction.hide(fingerPrint);
        }
        if (seatFragment != null) {
            fragmentTransaction.hide(seatFragment);
        }
        if (holographicFragment != null) {
            fragmentTransaction.hide(holographicFragment);
        }
        if (fragmentreplace != null) {
            fragmentTransaction.hide(fragmentreplace);
        }
        if (holoreplace != null) {
            fragmentTransaction.hide(holoreplace);
        }
    }

    private void initTextViewColor() {
        holographic.setTextColor(getResources().getColor(R.color.textNoSelect));
        fingerprint.setTextColor(getResources().getColor(R.color.textNoSelect));
        seat.setTextColor(getResources().getColor(R.color.textNoSelect));
        returnTv.setTextColor(getResources().getColor(R.color.textNoSelect));
    }

//    @SuppressLint("HandlerLeak")
//    public static Handler handler = new Handler() {
//
//        //在主线程中处理从子线程发送过来的消息
//        @Override
//        public void handleMessage(Message msg) {
//            Log.i("s", "ssssssssssssssssss ");
//        }
//    };
//   public static SpannableString getHighLightKeyWord(int color,String text,int size){
//        SpannableString s=new SpannableString(text);
//        s.setSpan(color,0,size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return s;
//   }
}
