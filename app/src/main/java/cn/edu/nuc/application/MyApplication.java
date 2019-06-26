package cn.edu.nuc.application;

import android.app.Application;
import android.content.Intent;

import com.assionhonty.lib.assninegridview.AssNineGridView;

import cn.edu.nuc.ImageLoader.GlideImageLoader;
import cn.edu.nuc.mylove.activity.HomeActivity;
import cn.edu.nuc.mylove.activity.LoginActivity;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssNineGridView.setImageLoader(new GlideImageLoader());
    }
}
