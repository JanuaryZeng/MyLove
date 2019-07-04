package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.Map;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.JSONTOOL;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.Helper.TimeForm;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

import static cn.edu.nuc.Helper.IDHelper.loverID;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        SharedPreferences sp = getSharedPreferences("ID", MODE_PRIVATE);
        if(sp.contains("loverid")){
            IDHelper.loverID = sp.getString("loverid",null);
            IDHelper.setGender(sp.getString("gender",null));
            IDHelper.setDate(sp.getString("loverdate",null));
            IDHelper.setTaBorn(TimeForm.getNowNoMinTime());
            IDHelper.init();
            MoneyTypeTable.init();
            Integer time = 2000;    //设置等待时间，单位为毫秒
            Handler handler = new Handler();
            //当计时结束时，跳转至主界面
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LaunchActivity.this, HomeActivity.class));
                    LaunchActivity.this.finish();
                }
            }, time);

        }else{
            Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }


    }
}
