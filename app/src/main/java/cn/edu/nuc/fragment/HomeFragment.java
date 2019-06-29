package cn.edu.nuc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.AlarmActivity;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class HomeFragment extends BaseFragment {

    private ImageView ivHomeRightNaoZhong = null;

    private ImageView ivTouXiangLeft = null;
    private ImageView ivTouXiangRight = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        ivTouXiangLeft = view.findViewById(R.id.ivTouXiangLeft);
        ivTouXiangRight = view.findViewById(R.id.ivTouXiangRight);

        //加载头像
        Glide.with(view.getContext()).load(IDHelper.getMyIcon()).into(ivTouXiangLeft);
        Glide.with(view.getContext()).load(IDHelper.getTaIcon()).into(ivTouXiangRight);


        ivHomeRightNaoZhong = view.findViewById(R.id.ivHomeRightNaoZhong);
        ivHomeRightNaoZhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AlarmActivity.class));
            }
        });
    }
}
