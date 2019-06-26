package cn.edu.nuc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.AlarmActivity;

public class HomeFragment extends BaseFragment {

    private ImageView ivHomeRightNaoZhong = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        ivHomeRightNaoZhong = view.findViewById(R.id.ivHomeRightNaoZhong);
        ivHomeRightNaoZhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AlarmActivity.class));
            }
        });
    }
}
