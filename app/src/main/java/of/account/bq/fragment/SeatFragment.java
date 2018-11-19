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


public class SeatFragment extends Fragment {
    ImageView serat_user;
    TextView textView1;
    TextView textView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat, container, false);
        initView(view);
        return view;
    }


    public void initView(View view) {
        serat_user = view.findViewById(R.id.seat_user);
        textView1 = view.findViewById(R.id.text1);
        textView2 = view.findViewById(R.id.text2);
    }
}
