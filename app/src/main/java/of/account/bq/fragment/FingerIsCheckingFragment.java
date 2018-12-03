package of.account.bq.fragment;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import of.account.bq.R;
import of.account.bq.activity.MainActivity;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class FingerIsCheckingFragment extends Fragment {
    private GifImageView gifImageView;

    /**
     * 显示提示性动画的ImageView
     */


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finger_is_checking, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        gifImageView = view.findViewById(R.id.iv_fingering);
        gifImageView.setAlpha(0.55f);
//2. 设置给GifImageView控件
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //  gifImageView.setVisibility(View.INVISIBLE);
                getFragmentManager()
                        .beginTransaction()
                        .hide(MainActivity.holoreplace)
                        .commit();
                MainActivity.holoreplace = new BuyHolographicsSucceedFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.mainFragment, MainActivity.holoreplace).commit();
            }
        }, 4500);

    }
}