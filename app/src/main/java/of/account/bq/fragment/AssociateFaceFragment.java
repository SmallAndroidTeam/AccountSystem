package of.account.bq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import of.account.bq.R;
import of.account.bq.activity.MainActivity;


public class AssociateFaceFragment extends Fragment {
    private TextView tv_face;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.association_face, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        tv_face = view.findViewById(R.id.tv_face);
        imageView = view.findViewById(R.id.im);
        tv_face.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tv_face.clearAnimation();
                imageView.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                tv_face.startAnimation(animation);
                imageView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tv_face.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
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
        }, 2000);
    }
}
