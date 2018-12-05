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
import of.account.bq.utils.GifImageView;


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
        MainActivity.enable_jump=false;
        gifImageView.setAlpha(0.55f);
        gifImageView.setGifResource(R.mipmap.fingergif, new GifImageView.OnPlayListener() {
            @Override
            public void onPlayStart() {
            }

            @Override
            public void onPlaying(@FloatRange(from = 0f, to = 1.0f) float percent) {
            }

            @Override
            public void onPlayPause(boolean pauseSuccess) {
            }

            @Override
            public void onPlayRestart() {
            }

            @Override
            public void onPlayEnd() {
            }
        });
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
                MainActivity.enable_jump=true;
                MainActivity.holoreplace = new BuyHolographicsSucceedFragment();
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.mainFragment, MainActivity.holoreplace).commit();
            }
        }, 4500);

    }
}
