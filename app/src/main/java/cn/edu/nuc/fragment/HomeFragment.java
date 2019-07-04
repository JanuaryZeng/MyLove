package cn.edu.nuc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.AlarmActivity;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class HomeFragment extends BaseFragment {

    private ImageView ivHomeRightNaoZhong = null;

    private ImageView ivTouXiangLeft = null;
    private ImageView ivTouXiangRight = null;
    private TextView tvHomeDay = null;
    private TextView tvHomeBri = null;

    private View view = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){

        ivTouXiangLeft = view.findViewById(R.id.ivTouXiangLeft);
        ivTouXiangRight = view.findViewById(R.id.ivTouXiangRight);

        ivHomeRightNaoZhong = view.findViewById(R.id.ivHomeRightNaoZhong);
        ivHomeRightNaoZhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AlarmActivity.class));
            }
        });

        //恋爱天数设置
        try {
            tvHomeDay = view.findViewById(R.id.tvHomeDay);
            tvHomeDay.setText("我们在一起"+IDHelper.getLoverDay()+"天");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        super.onResume();        //恋爱天数设置
        //加载头像
        Glide.with(view.getContext()).load(IDHelper.getMyIcon()).into(ivTouXiangLeft);
        Glide.with(view.getContext()).load(IDHelper.getTaIcon()).into(ivTouXiangRight);
        try {
            tvHomeDay = view.findViewById(R.id.tvHomeDay);
            tvHomeDay.setText("我们在一起"+IDHelper.getLoverDay()+"天");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            tvHomeBri = view.findViewById(R.id.tvHomeBri);
            if(IDHelper.getGender().equals("man"))
                tvHomeBri.setText("距离她的生日还有"+IDHelper.getTaBri()+"天");
            else if(IDHelper.getGender().equals("woman"))
                tvHomeBri.setText("距离他的生日还有"+IDHelper.getTaBri()+"天");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
