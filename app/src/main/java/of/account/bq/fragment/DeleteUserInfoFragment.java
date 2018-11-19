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


public class DeleteUserInfoFragment extends Fragment {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView iv;
    private int duration = 0;
    private AnimationDrawable anim_start;
    private boolean clicked = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whether_delete_userinfo, container, false);
        initViews(view);
        return view;
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
                if (!clicked) {
                    iv.setImageResource(R.drawable.associate_step2);
                    anim_start = (AnimationDrawable) iv.getDrawable();
                    anim_start.start();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            anim_start.stop();

//                        Message msg = MainActivity.handler.obtainMessage();
//                        MainActivity.handler.sendMessage(msg);
                            if (MainActivity.dataOperator.CheckIsDataAlreadyInDBorNot(AssociateFingerSucceedFragment.s)) {
                                MainActivity.dataOperator.delete(AssociateFingerSucceedFragment.s);
                            }
                            MainActivity.fragmentreplace = new FingerPrintEnteringFragment();
                            getFragmentManager().popBackStack();
                            getFragmentManager()
                                    .beginTransaction()
                                    .addToBackStack(null)  //将当前fragment加入到返回栈中
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
                getFragmentManager().popBackStack();
                MainActivity.fragmentreplace = new UserInfoFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
            }
        });

    }
}
