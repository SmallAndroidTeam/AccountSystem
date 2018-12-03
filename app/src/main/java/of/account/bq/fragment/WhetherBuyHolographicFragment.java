package of.account.bq.fragment;

import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import of.account.bq.R;
import of.account.bq.activity.MainActivity;


public class WhetherBuyHolographicFragment extends Fragment {
    private TextView bg_text;
    private TextView buy;
    private TextView whther_by;
    private TextView sure_by;
    private TextView quxiao;
    private ImageView iv_buy_anim;
    private ImageView iv_quxiao_anim;
    private ImageView bg_left;
    private ImageView holographic_left;
    private ImageView ring_left;
    private int duration = 0;
    private AnimationDrawable anim_start_buy;
    private AnimationDrawable anim_start_quxiao;
    private boolean clicked = false;
    private Timer back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whether_buy_holographic, container, false);
        initViews(view);
        // noResponse();
        return view;
    }
    private void noResponse() {
        back= new Timer();
        back.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.holoreplace = new HolographicFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragment, MainActivity.holoreplace).commit();
            }
        },5000);
    }
    private void initViews(View view) {
        bg_left = view.findViewById(R.id.bg_left);
        holographic_left= view.findViewById(R.id.holographic_left);
        ring_left= view.findViewById(R.id.ring_left);
        bg_text = view.findViewById(R.id.bg_text);
        buy = view.findViewById(R.id.buy);
        sure_by=view.findViewById(R.id.sure_buy);
        quxiao=view.findViewById(R.id.quxiao);
        //   mSquareProgress = (SquareProgress) findViewById(R.id.sp);
        whther_by = view.findViewById(R.id.whether_buy);
        iv_buy_anim = (ImageView) view.findViewById(R.id.anim);
        iv_buy_anim.setImageResource(R.drawable.associate_step1);
        anim_start_buy = (AnimationDrawable) iv_buy_anim.getDrawable();
        iv_quxiao_anim = (ImageView) view.findViewById(R.id.anim2);
        iv_quxiao_anim.setImageResource(R.drawable.cancel_step1);
        anim_start_quxiao = (AnimationDrawable) iv_quxiao_anim.getDrawable();
        holographic_left.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        ring_left.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        buy.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        whther_by.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        //quxiao.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        anim_start_buy.start();
        anim_start_quxiao.start();
        sure_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  back.cancel();
                if (!clicked) {
                    iv_buy_anim.setImageResource(R.drawable.associate_step2);
                    anim_start_buy = (AnimationDrawable) iv_buy_anim.getDrawable();
                    anim_start_buy.start();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            anim_start_buy.stop();

                            getFragmentManager()
                                    .beginTransaction()
                                    .hide(MainActivity.holoreplace).commit();
                            MainActivity.holoreplace = new BuyHolographicPromptFragment();
                            getFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.mainFragment, MainActivity.holoreplace).commit();
                        }
                    }, 700);
                    clicked = true;
                }
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  back.cancel();
                if (!clicked) {
                    iv_quxiao_anim.setImageResource(R.drawable.cancel_step2);
                    anim_start_quxiao = (AnimationDrawable) iv_quxiao_anim.getDrawable();
                    anim_start_quxiao.start();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            anim_start_quxiao.stop();
//                        Message msg = MainActivity.handler.obtainMessage();
//                        MainActivity.handler.sendMessage(msg);
                            getFragmentManager()
                                    .beginTransaction()
                              .hide(MainActivity.holoreplace).commit();
                            MainActivity.holoreplace = new HolographicFragment();
                            getFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.mainFragment, MainActivity.holoreplace).commit();
                        }
                    }, 700);
                    clicked = true;
                }
            }
        });

    }
}
