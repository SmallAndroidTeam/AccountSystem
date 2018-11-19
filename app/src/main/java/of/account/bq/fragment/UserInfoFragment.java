package of.account.bq.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import of.account.bq.Bean.PersonInfo;
import of.account.bq.R;
import of.account.bq.Toast.OnlyOneToast;
import of.account.bq.activity.MainActivity;


public class UserInfoFragment extends Fragment implements View.OnClickListener {
    private ImageView icon1;
    private ImageView back1;
    private TextView text1;
    private ImageView back2;
    private TextView text2;
    private TextView name;
    private EditText editname;
    private TextView face;
    private TextView correlation;
    private TextView fingerprint;
    private ImageView delete;
    private ImageView back;
    private ImageView relation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info, container, false);
        initView(view);
        initAnims();
        return view;
    }


    private void initView(View view) {
        // 为单个View添加点击事件
        icon1 = view.findViewById(R.id.icon_add1);
        back1 = view.findViewById(R.id.back1);
        text1 = view.findViewById(R.id.text1);
        back2 = view.findViewById(R.id.back2);
        text2 = view.findViewById(R.id.text2);
        name = view.findViewById(R.id.name);
        editname = view.findViewById(R.id.editname);
        face = view.findViewById(R.id.face);
        back = view.findViewById(R.id.back);
        delete = view.findViewById(R.id.delete);
        relation = view.findViewById(R.id.relation);
        correlation = view.findViewById(R.id.correlation);
        fingerprint = view.findViewById(R.id.fingerprint);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
        relation.setOnClickListener(this);
        if (MainActivity.dataOperator.CheckIsDataAlreadyInDBorNot(AssociateFingerSucceedFragment.s)) {
            correlation.setText("状态：已关联");
        }
        text1.setText("指纹" + AssociateFingerSucceedFragment.s);
        text2.setText("人脸ID" + AssociateFingerSucceedFragment.s);
        fingerprint.setText("指纹：" + text1.getText());
        face.setText("人脸：" + text2.getText());
        back2.setImageDrawable(AssociateFaceSucceedFragment.drawable);
        editname.setText(AssociateFaceSucceedFragment.name);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                MainActivity.fragmentreplace = new FingerPrintEnteringFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
                break;
            case R.id.delete:
                getFragmentManager().popBackStack();
                MainActivity.fragmentreplace = new DeleteUserInfoFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .add(R.id.mainFragment, MainActivity.fragmentreplace).commit();
                break;
            case R.id.relation:
                correlation.setText("状态：已关联");
                if (!MainActivity.dataOperator.CheckIsDataAlreadyInDBorNot(AssociateFingerSucceedFragment.s)) {
                    PersonInfo personInfo = new PersonInfo();
                    personInfo.setFace(AssociateFaceSucceedFragment.drawable);
                    personInfo.setFingerId(AssociateFingerSucceedFragment.s);
                    personInfo.setName(editname.getText().toString());
                    MainActivity.dataOperator.add(personInfo);
                    // Toast.makeText(getContext(), "指纹和脸部已经关联", Toast.LENGTH_LONG).show();
                    OnlyOneToast.makeText(getContext(), "指纹和脸部已经关联");
                } else {
                    // Toast.makeText(getContext(), "指纹和脸部已经关联", Toast.LENGTH_LONG).show();
                    OnlyOneToast.makeText(getContext(), "指纹和脸部已经关联");
                }
                break;
            default:
                break;
        }
    }

    private void initAnims() {
        //初始化底部注册、登录的按钮动画
        //以控件自身所在的位置为原点，从下方距离原点200像素的位置移动到原点
        ObjectAnimator tranName = ObjectAnimator.ofFloat(name, "translationY", 500, 0);
        ObjectAnimator tranFingerprint = ObjectAnimator.ofFloat(fingerprint, "translationY", 500, 0);
        ObjectAnimator tranFace = ObjectAnimator.ofFloat(face, "translationY", 500, 0);
        ObjectAnimator tranCorrelation = ObjectAnimator.ofFloat(correlation, "translationY", 500, 0);
        ObjectAnimator traneditname = ObjectAnimator.ofFloat(editname, "translationY", 500, 0);
        ObjectAnimator tranBack1_X = ObjectAnimator.ofFloat(back1, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack1_Y = ObjectAnimator.ofFloat(back1, "scaleY", 0.5f, 1f);
        ObjectAnimator tranBack2_X = ObjectAnimator.ofFloat(back2, "scaleX", 0.5f, 1f);
        ObjectAnimator tranBack2_Y = ObjectAnimator.ofFloat(back2, "scaleY", 0.5f, 1f);
        ObjectAnimator icon1_X = ObjectAnimator.ofFloat(icon1, "scaleX", 0.5f, 1f);
        ObjectAnimator icon1_Y = ObjectAnimator.ofFloat(icon1, "scaleY", 0.5f, 1f);
        ObjectAnimator icon2_X = ObjectAnimator.ofFloat(back, "scaleX", 0.5f, 1f);
        ObjectAnimator icon2_Y = ObjectAnimator.ofFloat(back, "scaleY", 0.5f, 1f);
        ObjectAnimator arrow_X = ObjectAnimator.ofFloat(delete, "scaleX", 0.1f, 1f);
        ObjectAnimator arrow_Y = ObjectAnimator.ofFloat(delete, "scaleY", 0.1f, 1f);

        //将注册、登录的控件alpha属性从0变到1
        ObjectAnimator alphaLoName = ObjectAnimator.ofFloat(name, "alpha", 0, 1);
        ObjectAnimator alphaFingerprint = ObjectAnimator.ofFloat(fingerprint, "alpha", 0, 1);
        ObjectAnimator alphaFace = ObjectAnimator.ofFloat(face, "alpha", 0, 1);
        ObjectAnimator alphaCorrelation = ObjectAnimator.ofFloat(correlation, "alpha", 0, 1);
        ObjectAnimator alphaBack1 = ObjectAnimator.ofFloat(back1, "alpha", 0, 1);
        ObjectAnimator alphaBack2 = ObjectAnimator.ofFloat(back2, "alpha", 0, 1);
        ObjectAnimator alphaRelation = ObjectAnimator.ofFloat(relation, "alpha", 0, 1);
        ObjectAnimator alphaBack = ObjectAnimator.ofFloat(back, "alpha", 0, 1);
        ObjectAnimator alphadelete = ObjectAnimator.ofFloat(delete, "alpha", 0, 1);
        final AnimatorSet bottomAnim = new AnimatorSet();
        bottomAnim.setDuration(1500);
        //同时执行控件平移和alpha渐变动画
        bottomAnim.play(tranName).with(tranFingerprint).with(tranFace).with(tranCorrelation).with(tranBack1_X)
                  .with(traneditname).with(tranBack1_Y).with(tranBack2_X).with(tranBack2_Y).with(icon1_X)
                  .with(icon1_Y).with(icon2_X).with(icon2_Y).with(arrow_X).with(arrow_Y)
                  .with(alphaLoName).with(alphaFingerprint).with(alphaFace)
                  .with(alphaCorrelation).with(alphaBack1).with(alphaBack2).with(alphaRelation)
                  .with(alphaBack).with(alphadelete);
        bottomAnim.start();


    }


}
