package of.account.bq.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import of.account.bq.Bean.PersonInfo;
import of.account.bq.R;
import of.account.bq.Toast.OnlyOneToast;
import of.account.bq.activity.MainActivity;
import of.account.bq.particles.ParticleSmasher;
import of.account.bq.particles.SmashAnimator;
import of.account.bq.utils.LongClickUtils;


public class FingerPrintEnteringFragment extends Fragment {
    private ImageView back1;
    private TextView text1;
    private ImageView back2;
    private TextView text2;
    private ImageView back3;
    private TextView text3;
    private ImageView back4;
    private TextView text4;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout3;
    private ParticleSmasher mSmasher;
    List<PersonInfo> users = new ArrayList<PersonInfo>();
    PersonInfo personInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fingerprint_entering, container, false);
        initViews(view);
        initViewsClick();
        initAnims();
        return view;
    }

    private void initViews(View view) {
        back1 = view.findViewById(R.id.back1);
        text1 = view.findViewById(R.id.text1);
        back2 = view.findViewById(R.id.back2);
        text2 = view.findViewById(R.id.text2);
        back3 = view.findViewById(R.id.back3);
        text3 = view.findViewById(R.id.text3);
        back4 = view.findViewById(R.id.back4);
        text4 = view.findViewById(R.id.text4);
        relativeLayout1 = view.findViewById(R.id.first_user);
        relativeLayout2 = view.findViewById(R.id.second_user);
        relativeLayout3 = view.findViewById(R.id.third_user);
    }

    private void initViewsClick() {

        mSmasher = new ParticleSmasher(getActivity());
        users = MainActivity.dataOperator.queryMany();
        if (users.size() == 0) {
            relativeLayout1.setVisibility(View.INVISIBLE);
            relativeLayout2.setVisibility(View.INVISIBLE);
            relativeLayout3.setVisibility(View.INVISIBLE);
        }
        if (users.size() == 1) {
            personInfo = users.get(0);
            back2.setImageDrawable(personInfo.getFace());
            text2.setText(personInfo.getName());
            relativeLayout2.setVisibility(View.INVISIBLE);
            relativeLayout3.setVisibility(View.INVISIBLE);
        }
        if (users.size() == 2) {
            personInfo = users.get(0);
            back2.setImageDrawable(personInfo.getFace());
            text2.setText(personInfo.getName());
            personInfo = users.get(1);
            back3.setImageDrawable(personInfo.getFace());
            text3.setText(personInfo.getName());
            relativeLayout3.setVisibility(View.INVISIBLE);
        }
        if (users.size() == 3) {
            personInfo = users.get(0);
            back2.setImageDrawable(personInfo.getFace());
            text2.setText(personInfo.getName());
            personInfo = users.get(1);
            back3.setImageDrawable(personInfo.getFace());
            text3.setText(personInfo.getName());
            personInfo = users.get(2);
            back4.setImageDrawable(personInfo.getFace());
            text4.setText(personInfo.getName());
        }

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (users.size() < 3) {
                    text1.setVisibility(View.GONE);
                    back1.setVisibility(View.GONE);
                    mSmasher.with(back1)
                            .setStyle(SmashAnimator.STYLE_FLOAT_LEFT)
                            .setHorizontalMultiple(2)
                            .setVerticalMultiple(2)
                            .setDuration(1500)
                            .addAnimatorListener(new SmashAnimator.OnAnimatorListener() {
                                @Override
                                public void onAnimatorStart() {
                                    super.onAnimatorStart();
                                    // 回调，动画开始
                                }

                                @Override
                                public void onAnimatorEnd() {
                                    super.onAnimatorEnd();
                                    // 回调，动画结束
                                    getFragmentManager().popBackStack();
                                    MainActivity.fragmentreplace = new WhetherAssociationFragment();
                                    MainActivity.flag = true;
                                    getFragmentManager()
                                            .beginTransaction()
                                            .addToBackStack(null)
                                            .add(R.id.mainFragment, MainActivity.fragmentreplace)
                                            .commit();
                                }
                            })
                            .start();
                } else {
                    // Toast.makeText(getContext(), "用户信息录入已满  请先选择删除右侧用户信息", Toast.LENGTH_LONG).show();
                    OnlyOneToast.makeText(getContext(), "用户信息录入已满  请先选择删除右侧用户信息");
                }
            }
        });

        if (users.size() >= 1) {
            back2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text2.setVisibility(View.GONE);
                    back2.setVisibility(View.GONE);
                    mSmasher.with(back2)
                            .setStyle(SmashAnimator.STYLE_FLOAT_TOP)
                            .setHorizontalMultiple(2)
                            .setVerticalMultiple(2)
                            .setDuration(1500)
                            .addAnimatorListener(new SmashAnimator.OnAnimatorListener() {
                                @Override
                                public void onAnimatorStart() {
                                    super.onAnimatorStart();
                                    // 回调，动画开始
                                }

                                @Override
                                public void onAnimatorEnd() {
                                    super.onAnimatorEnd();
                                    // 回调，动画结束
                                    gotoUserInfo(text2.getText().toString());
                                }
                            })
                            .start();
                }
            });
        }
        if (users.size() >= 2) {
            back3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text3.setVisibility(View.GONE);
                    back3.setVisibility(View.GONE);
                    mSmasher.with(back3)
                            .setStyle(SmashAnimator.STYLE_FLOAT_BOTTOM)
                            .setHorizontalMultiple(2)
                            .setVerticalMultiple(2)
                            .setDuration(1500)
                            .addAnimatorListener(new SmashAnimator.OnAnimatorListener() {
                                @Override
                                public void onAnimatorStart() {
                                    super.onAnimatorStart();
                                    // 回调，动画开始
                                }

                                @Override
                                public void onAnimatorEnd() {
                                    super.onAnimatorEnd();
                                    // 回调，动画结束
                                    gotoUserInfo(text3.getText().toString());
                                }
                            })
                            .start();
                }
            });
//            back3.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Drawable drawable = back2.getDrawable();
//                    String temp = text2.getText().toString();
//                    back2.setImageDrawable(back3.getDrawable());
//                    text2.setText(text3.getText());
//                    back3.setImageDrawable(drawable);
//                    text3.setText(temp);
//                    return true;
//                }
//            });
            LongClickUtils.setLongClick(new Handler(), back3, 3000, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Drawable drawable = back2.getDrawable();
                    String temp = text2.getText().toString();
                    back2.setImageDrawable(back3.getDrawable());
                    text2.setText(text3.getText());
                    back3.setImageDrawable(drawable);
                    text3.setText(temp);
                    return true;
                }
            });
        }
        if (users.size() >= 3) {
            back4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text4.setVisibility(View.GONE);
                    back4.setVisibility(View.GONE);
                    mSmasher.with(back4)
                            .setStyle(SmashAnimator.STYLE_FLOAT_RIGHT)
                            .setHorizontalMultiple(2)
                            .setVerticalMultiple(2)
                            .setDuration(1500)
                            .addAnimatorListener(new SmashAnimator.OnAnimatorListener() {
                                @Override
                                public void onAnimatorStart() {
                                    super.onAnimatorStart();
                                    // 回调，动画开始
                                }

                                @Override
                                public void onAnimatorEnd() {
                                    super.onAnimatorEnd();
                                    // 回调，动画结束
                                    gotoUserInfo(text4.getText().toString());
                                }
                            })
                            .start();
                }
            });
//            back4.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Drawable drawable = back2.getDrawable();
//                    String temp = text2.getText().toString();
//                    back2.setImageDrawable(back4.getDrawable());
//                    text2.setText(text4.getText());
//                    back4.setImageDrawable(drawable);
//                    text4.setText(temp);
//                    return true;
//                }
//            });
            LongClickUtils.setLongClick(new Handler(), back4, 3000, new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Drawable drawable = back2.getDrawable();
                    String temp = text2.getText().toString();
                    back2.setImageDrawable(back4.getDrawable());
                    text2.setText(text4.getText());
                    back4.setImageDrawable(drawable);
                    text4.setText(temp);
                    return true;
                }
            });
        }
    }

    private void gotoUserInfo(int i) {
        personInfo = users.get(i);
        AssociateFingerSucceedFragment.s = personInfo.getFingerId();
        AssociateFaceSucceedFragment.drawable = personInfo.getFace();
        AssociateFaceSucceedFragment.name = personInfo.getName();
        MainActivity.fragmentreplace = new UserInfoFragment();
        MainActivity.flag = true;
        getFragmentManager().popBackStack();
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();

    }

    private void gotoUserInfo(String name) {

        personInfo = MainActivity.dataOperator.queryOne(name);
        if (personInfo != null) {
            AssociateFingerSucceedFragment.s = personInfo.getFingerId();
            AssociateFaceSucceedFragment.drawable = personInfo.getFace();
            AssociateFaceSucceedFragment.name = personInfo.getName();
            MainActivity.fragmentreplace = new UserInfoFragment();
            MainActivity.flag = true;
            getFragmentManager().popBackStack();
            getFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
        }
    }

    private void initAnims() {
        //初始化底部注册、登录的按钮动画
        //以控件自身所在的位置为原点，从下方距离原点200像素的位置移动到原点
        ObjectAnimator tranBack1_X = ObjectAnimator.ofFloat(back1, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack1_Y = ObjectAnimator.ofFloat(back1, "scaleY", 0.5f, 1f);
        ObjectAnimator tranBack2_X = ObjectAnimator.ofFloat(back2, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack2_Y = ObjectAnimator.ofFloat(back2, "scaleY", 0.5f, 1f);
        ObjectAnimator tranBack3_X = ObjectAnimator.ofFloat(back3, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack3_Y = ObjectAnimator.ofFloat(back3, "scaleY", 0.5f, 1f);
        ObjectAnimator tranBack4_X = ObjectAnimator.ofFloat(back4, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack4_Y = ObjectAnimator.ofFloat(back4, "scaleY", 0.5f, 1f);

        ObjectAnimator alphaBack1 = ObjectAnimator.ofFloat(back1, "alpha", 0, 1f);
        ObjectAnimator alphaBack2 = ObjectAnimator.ofFloat(back2, "alpha", 0, 1f);
        ObjectAnimator alphaBack3 = ObjectAnimator.ofFloat(back3, "alpha", 0, 1f);
        ObjectAnimator alphaBack4 = ObjectAnimator.ofFloat(back4, "alpha", 0, 1f);
        final AnimatorSet bottomAnim = new AnimatorSet();
        bottomAnim.setDuration(1500);
        //同时执行控件平移和alpha渐变动画
        bottomAnim.play(tranBack1_X).with(tranBack1_Y).with(tranBack2_X).with(tranBack2_Y).with(tranBack3_X)
                .with(tranBack3_Y).with(tranBack4_X).with(tranBack4_Y).with(alphaBack1)
                .with(alphaBack2).with(alphaBack3).with(alphaBack4);
        bottomAnim.start();

    }


}
