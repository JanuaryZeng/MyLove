package cn.edu.nuc.application;

import android.app.Application;

import com.assionhonty.lib.assninegridview.AssNineGridView;

import cn.edu.nuc.ImageLoader.GlideImageLoader;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssNineGridView.setImageLoader(new GlideImageLoader());
    }
}
