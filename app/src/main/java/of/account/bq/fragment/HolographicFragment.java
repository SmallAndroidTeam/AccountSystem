package of.account.bq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import of.account.bq.R;
import of.account.bq.activity.MainActivity;


public class HolographicFragment extends Fragment implements View.OnClickListener {
    private ImageView holo_left;
    private TextView text_left;
    private ImageView holo_middle;
    private TextView text_middle;
    private ImageView holo_right;
    private TextView text_right;
    private ImageView ring_left;
    private ImageView ring_middle;
    private ImageView ring_right;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holographic, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        holo_left = view.findViewById(R.id.holographic_left);
        holo_middle = view.findViewById(R.id.holographic_middle);
        holo_right = view.findViewById(R.id.holographic_right);
        text_left = view.findViewById(R.id.text1);
        text_middle = view.findViewById(R.id.text2);
        text_right = view.findViewById(R.id.text3);
        ring_left = view.findViewById(R.id.choose1);
        ring_middle = view.findViewById(R.id.choose2);
        ring_right = view.findViewById(R.id.choose3);
        holo_left.setOnClickListener(this);
        holo_middle.setOnClickListener(this);
        holo_right.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.holographic_left:
                ring_left.setVisibility(View.VISIBLE);
                ring_middle.setVisibility(View.INVISIBLE);
                ring_right.setVisibility(View.INVISIBLE);
                break;
            case R.id.holographic_middle:
                ring_left.setVisibility(View.INVISIBLE);
                ring_middle.setVisibility(View.VISIBLE);
                ring_right.setVisibility(View.INVISIBLE);
                break;
            case R.id.holographic_right:
                ring_left.setVisibility(View.INVISIBLE);
                ring_middle.setVisibility(View.INVISIBLE);
                ring_right.setVisibility(View.VISIBLE);
                getFragmentManager()
                        .beginTransaction().hide(MainActivity.holoreplace).commit();
                MainActivity.holoreplace = new WhetherBuyHolographicFragment();
             //   MainActivity.flag_holo=true;
                getFragmentManager()
                        .beginTransaction()
                   //     .hide(MainActivity.holoreplace)
                        .add(R.id.mainFragment, MainActivity.holoreplace).commit();
                break;
            default:
                break;
        }
    }
}
