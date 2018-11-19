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


import java.util.Random;

import of.account.bq.R;
import of.account.bq.Toast.OnlyOneToast;
import of.account.bq.activity.MainActivity;

public class AssociateFingerSucceedFragment extends Fragment {
    private ImageView imageView;
    private TextView textView;
    private ImageView iv;
    private AnimationDrawable anim;
    private int duration = 0;
    public static String s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.succeed_added_fingerprint, container, false);
        initViews(view);
        addto_DataBase();
        return view;
    }

    private void addto_DataBase() {
        Random random = new Random();
        s = "00" + Integer.toString(random.nextInt(10));
        // MainActivity.dataOperator.add_fingerprint(s);
       // Toast.makeText(getContext(), "succeed added data to Database", Toast.LENGTH_LONG).show();
        OnlyOneToast.makeText(getContext(),"succeed added data to Database");
    }

    private void initViews(View view) {
        imageView = view.findViewById(R.id.add_succeed_icon);
        textView = view.findViewById(R.id.add_succeed);
        iv = (ImageView) view.findViewById(R.id.anim);
        iv.setImageResource(R.drawable.finger_true);
        anim = (AnimationDrawable) iv.getDrawable();
        imageView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        anim.start();
        for (int i = 0; i < anim.getNumberOfFrames(); i++) {
            duration += anim.getDuration(i);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                anim.stop();
                imageView.clearAnimation();
                textView.clearAnimation();
                Animation animation = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                Animation animation1 = (AnimationUtils.loadAnimation(getActivity(), R.anim.translate));
                Animation animation2 = (AnimationUtils.loadAnimation(getActivity(), R.anim.scale_smaller));
                imageView.startAnimation(animation);
                iv.startAnimation(animation2);
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
                        getFragmentManager().popBackStack();
                        MainActivity.fragmentreplace = new StartAssociateFacePromptFragment();
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
        }, duration);


    }

}
