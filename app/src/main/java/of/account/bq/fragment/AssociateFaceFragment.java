package of.account.bq.fragment;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import of.account.bq.R;
import of.account.bq.activity.MainActivity;
import of.account.bq.utils.GifImageView;

public class AssociateFaceFragment extends Fragment {
    private TextView tv_face;
    private GifImageView gifImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.association_face, container, false);

        initViews(view);

        return view;
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    private void initViews(View view) {
        tv_face = view.findViewById(R.id.tv_face);
        gifImageView = view.findViewById(R.id.iv_facegif);
        MainActivity.enable_jump=false;
        gifImageView.setAlpha(0.50f);
        gifImageView.setGifResource(R.drawable.facegif, new GifImageView.OnPlayListener() {
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
        tv_face.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tv_face.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                tv_face.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tv_face.setVisibility(View.GONE);
                        MainActivity.enable_jump=true;
                        getFragmentManager().popBackStack();
                        MainActivity.fragmentreplace = new AssociateFaceSucceedFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)  //将当前fragment加入到返回栈中
                                .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 1500);
    }

}
