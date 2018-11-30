package of.account.bq.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
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

import of.account.bq.R;
import of.account.bq.activity.MainActivity;


public class StartAssociateFingerPromptFragment extends Fragment {
    private ImageView imageView;
    private TextView textView;
    private AnimationDrawable anim;
    private int duration = 0;
    public static String s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fingerprint_start_prompt, container, false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        imageView = view.findViewById(R.id.add_succeed_icon);
        textView = view.findViewById(R.id.add_succeed);

        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                imageView.clearAnimation();
                textView.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                Animation animation1 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                Animation animation2 = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                imageView.startAnimation(animation);

                textView.startAnimation(animation1);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        textView.setVisibility(View.GONE);
//                        imageView.setVisibility(View.GONE);
//                        iv.setVisibility(View.GONE);
                        MainActivity.fragmentreplace = new AssociateFingerFragment();
                        getFragmentManager().popBackStack();
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack(null)
                                .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 3000);


    }

}
