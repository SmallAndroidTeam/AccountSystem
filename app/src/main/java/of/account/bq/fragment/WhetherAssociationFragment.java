package of.account.bq.fragment;

import android.content.Intent;
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


public class WhetherAssociationFragment extends Fragment {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView iv;
    private int duration = 0;
    private AnimationDrawable anim_start;
    private boolean clicked = false;
    private Timer back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whether_start_association, container, false);
        initViews(view);
         noResponse();
        return view;
    }
    private void noResponse() {
        back= new Timer();
        back.schedule(new TimerTask() {
            @Override
            public void run() {
                getFragmentManager().popBackStack();
                MainActivity.fragmentreplace = new FingerPrintEnteringFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
            }
        },5000);
    }
    private void initViews(View view) {
        textView1 = view.findViewById(R.id.whether_associate_face);
        textView2 = view.findViewById(R.id.sure_association);
        //   mSquareProgress = (SquareProgress) findViewById(R.id.sp);
        textView3 = view.findViewById(R.id.quxiao);
        iv = (ImageView) view.findViewById(R.id.anim);
        iv.setImageResource(R.drawable.associate_step1);
        anim_start = (AnimationDrawable) iv.getDrawable();
        textView1.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_bigger));
        textView2.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        textView3.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
        textView3.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        anim_start.start();
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.cancel();
                if (!clicked) {
                    Intent intent=new Intent("of.account.bq.send");
                    intent.setPackage("thread.ofilm.com.testtrinity");
                    getActivity().sendBroadcast(intent);
                    iv.setImageResource(R.drawable.associate_step2);
                    anim_start = (AnimationDrawable) iv.getDrawable();
                    anim_start.start();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            anim_start.stop();
//                            Intent intent = new Intent();
//                            intent.setAction("of.account.ba.add");
//                            getActivity().sendBroadcast(intent);
//                        Message msg = MainActivity.handler.obtainMessage();
//                        MainActivity.handler.sendMessage(msg);
                            getFragmentManager().popBackStack();
                            MainActivity.fragmentreplace = new StartAssociateFingerPromptFragment();
                            getFragmentManager()
                                    .beginTransaction()
                                    .addToBackStack(null)
                                    .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
                        }
                    }, 700);
                    clicked = true;
                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.cancel();
                getFragmentManager().popBackStack();
                MainActivity.fragmentreplace = new FingerPrintEnteringFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
            }
        });

    }
}
