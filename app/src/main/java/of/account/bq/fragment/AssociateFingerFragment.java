package of.account.bq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import of.account.bq.service.AccountService;
import of.account.bq.R;
import of.account.bq.activity.MainActivity;
import of.account.bq.utils.FingerImageView;


public class AssociateFingerFragment extends Fragment implements AccountService.FINGERPRINT {
    private TextView tv_finger;
    private int process = 0;

    /**
     * 显示提示性动画的ImageView
     */
    private FingerImageView iv_finger;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.association_finger, container, false);
        initView(view);
        iv_finger.startGif();
       // AccountService.setFingerprint(this);
        return view;
    }


    private void initView(View view) {
        iv_finger = view.findViewById(R.id.iv_finger);
        tv_finger = view.findViewById(R.id.tv_finger);
        iv_finger.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        tv_finger.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                iv_finger.clearAnimation();
                tv_finger.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                Animation animation1 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                iv_finger.startAnimation(animation);
                tv_finger.startAnimation(animation1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        iv_finger.setVisibility(View.GONE);
                        tv_finger.setVisibility(View.GONE);
                        getFragmentManager().popBackStack();
                        MainActivity.fragmentreplace = new AssociateFingerSucceedFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .add(R.id.mainFragment,MainActivity.fragmentreplace).commit();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        },3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
   //     AccountService.setFingerprint(null);
    }

    @Override
    public void sendId(int id) {
        Log.i("kfjskd", "sendId: "+id);
//        AccountService.setFingerprint(null);
//        iv_finger.clearAnimation();
//        tv_finger.clearAnimation();
//        Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
//        Animation animation1 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
//        iv_finger.startAnimation(animation);
//        tv_finger.startAnimation(animation1);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                iv_finger.setVisibility(View.GONE);
//                tv_finger.setVisibility(View.GONE);
//                getFragmentManager().popBackStack();
//                MainActivity.fragmentreplace = new AssociateFingerSucceedFragment();
//                getFragmentManager()
//                        .beginTransaction()
//                        .addToBackStack(null)
//                        .add(R.id.mainFragment,MainActivity.fragmentreplace).commit();
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }
}
