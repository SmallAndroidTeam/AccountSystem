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


public class BuyHolographicPromptFragment extends Fragment {
    private ImageView imageView;
    private TextView tv_buy;
    private TextView tv_prompt;
    private AnimationDrawable anim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_prompt, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        imageView = view.findViewById(R.id.add_succeed_icon);
        tv_buy = view.findViewById(R.id.tv_buy);
        tv_prompt=view.findViewById(R.id.tv_prompt);
        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        tv_buy.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        tv_prompt.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                imageView.clearAnimation();
                tv_buy.clearAnimation();
                tv_prompt.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                Animation animation1 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                Animation animation2 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                imageView.startAnimation(animation);
                tv_buy.startAnimation(animation1);
                tv_prompt.startAnimation(animation2);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tv_buy.setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                        tv_prompt.setVisibility(View.GONE);
                        getFragmentManager()
                                .beginTransaction()
                                .hide(MainActivity.holoreplace)
                                .commit();
                        MainActivity.holoreplace = new FingerIsAssociatingFragment();
                        getFragmentManager()
                                .beginTransaction()
                                .add(R.id.mainFragment, MainActivity.holoreplace).commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 3000);


    }

}
