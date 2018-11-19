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


public class HolographicFragment extends Fragment {
    private ImageView holo1;
    private TextView text1;
    private ImageView holo2;
    private TextView text2;
    private ImageView holo3;
    private TextView text3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holographic, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        holo1 = view.findViewById(R.id.quanxi1);
        holo2 = view.findViewById(R.id.quanxi2);
        holo3 = view.findViewById(R.id.quanxi3);
        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
    }

}
