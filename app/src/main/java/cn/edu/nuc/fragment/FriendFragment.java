package cn.edu.nuc.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.edu.nuc.Adapter.FirstTimeAdapter;
import cn.edu.nuc.Adapter.FriendAdapter;
import cn.edu.nuc.DataBase.FirstTimeNote;
import cn.edu.nuc.DataBase.FriendNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.JSONTOOL;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.HomeActivity;
import cn.edu.nuc.mylove.activity.LaunchActivity;
import cn.edu.nuc.mylove.activity.LoginActivity;

import static android.view.View.INVISIBLE;

public class FriendFragment extends BaseFragment {

    private static RecyclerView mRv = null;
    private static  LinearLayoutManager linear = null;
    private static FloatingActionButton fabFri = null;


    private static final String IMGURL1 = "/storage/emulated/0/阅图/PictureUnlock_hknew_78340.pictureunlock.jpg";
    private static final String IMGURL2 = "http://img.hb.aicdn.com/98e2007c524387a1d6444f9b80a15cf253d408b2244ed-owRaCM_fw658";
    private static final String IMGURL3 = "http://img.hb.aicdn.com/f69f6ea969f2231be1f9fe6ffd0e73965774a6336986f-mEVEjy_fw658";
    private static final String IMGURL4 = "http://img.hb.aicdn.com/c7f89bec028ecdc8348e80b0911baf10666f932b40396-u7wY7L_fw658";
    private static final String IMGURL5 = "http://img.hb.aicdn.com/d99978ec4bd8ac4013ac1b36b00a8c13098a5827540dd-LkJX6p_fw658";
    private static final String IMGURL6 = "http://img.hb.aicdn.com/e411e58dbd56ad3227724bbbbd7eb07416e4b43a46a41-JGzo9p_fw658";
    private static final String IMGURL7 = "http://img.hb.aicdn.com/75225644fec9d08dd4fdde72def94de0998cb38528a77-dOl6eM_fw658";
    private static final String IMGURL8 = "http://img.hb.aicdn.com/25e4071ba9d56aec8997857d916811e2cb020256504be-897MSv_fw658";
    private static final String IMGURL9 = "http://img.hb.aicdn.com/9b31060eaa4185bbb660af61d8c72206746657782631e-IviKwU_fw658";
    private static final String IMGURL10 = "http://img.hb.aicdn.com/dad95cc911f2e9b9bd614aacab76fc004c9d8d7e205bd-Awr1Pc_fw658";
    private static final String IMGURL11 = "http://img.hb.aicdn.com/9eececc00249510fcae7fa8253d00629969b8b52131e3-F5DykE_fw658";
    private static final String IMGURL12 = "http://img.hb.aicdn.com/79df58c579c840c2fe8f4a10eff2ceb34737c9df3915c-ZuiCYr_fw658";
    private static final String IMGURL13 = "http://img.hb.aicdn.com/e656366714f79488096591ab01f14e7d6d963782292b4-iRFQVL_fw658";
    private static final String IMGURL14 = "http://img.hb.aicdn.com/a7ab84658399a8a9299e25c73c4c32e365d743b4344d7-X6J071_fw658";
    private static final String IMGURL15 = "http://img.hb.aicdn.com/3c5bdda5f73ed9f0dfe7c1f4ade4959ad82721ee11964-lfaGLD_fw658";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        InitView(view);

        return view;
    }

    @SuppressLint("HandlerLeak")
    private static  Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
                    List<FriendNote> friendNotes = new ArrayList<FriendNote>();
                    for(Map<String ,String> map :maps) {
                        String friendid = map.get("friendid");
                        String usergender = map.get("usergender");
                        String frienddate = map.get("frienddate");
                        String friendphotos1 = map.get("friendphotos1");
                        String friendphotos2 = map.get("friendphotos2");
                        String friendphotos3 = map.get("friendphotos3");
                        String friendphotos4 = map.get("friendphotos4");
                        String friendtext = map.get("friendtext");


                        List<String> photos = new ArrayList<String>();

                        String[] str1 = friendphotos1.split("去");
                        String[] str2 = friendphotos2.split("去");
                        String[] str3 = friendphotos3.split("去");
                        String[] str4 = friendphotos4.split("去");
                        for (int i = 0; i < str1.length; i++){
                            if(str1[i].contains("http"))
                                photos.add(str1[i]);
                        }
                        for(int i = 0; i < str2.length; i++){
                            if(str2[i].contains("http"))
                                photos.add(str2[i]);
                        }
                        for(int i = 0; i < str3.length; i++){
                            if(str3[i].contains("http"))
                                photos.add(str3[i]);
                        }
                        for(int i = 0; i < str4.length; i++){
                            if(str4[i].contains("http"))
                                photos.add(str4[i]);
                        }
                        FriendNote friendNote = new FriendNote();
                        friendNote.setId(friendid);
                        friendNote.setGender(usergender);
                        friendNote.setTime(frienddate);
                        friendNote.setText(friendtext);
                        friendNote.setPhotoUrl(photos);
                        if(usergender.equals("man")){
                            friendNote.setIcon(IDHelper.getUserIcon().get("man"));
                            friendNote.setName(IDHelper.getUserName().get("man"));
                        }
                        else if(usergender.equals("woman")){
                            friendNote.setIcon(IDHelper.getUserIcon().get("woman"));
                            friendNote.setName(IDHelper.getUserName().get("woman"));
                        }
                        friendNotes.add(friendNote);
                    }
                    Collections.reverse(friendNotes);
                    mRv.setLayoutManager(linear);
                    mRv.setAdapter(new FriendAdapter(friendNotes));
                    Log.e("xujian","*-*-*-*-*-*-*-/*-/*-/*-/*-/*-/*-/*-///---=="+friendNotes);
                    break;
                case 20:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private View InitView(View view){
        List<FirstTimeNote> firstTimeNotes = new ArrayList<FirstTimeNote>();

        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
        firstTimeNotes.add(new FirstTimeNote("7", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
        firstTimeNotes.add(new FirstTimeNote("2", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
        firstTimeNotes.add(new FirstTimeNote("8", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
        firstTimeNotes.add(new FirstTimeNote("3", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));

        RecyclerView friendRecycler = (RecyclerView) view.findViewById(R.id.firstRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation((LinearLayout.HORIZONTAL));
        friendRecycler.setLayoutManager(linearLayoutManager);
        friendRecycler.setAdapter(new FirstTimeAdapter(firstTimeNotes));


        //*********************************分隔符***********************************************************************

        mRv = view.findViewById(R.id.friendRecycler);
        linear = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        setReyeler();
        //刷新按钮
        fabFri = (FloatingActionButton) view.findViewById(R.id.fabFri);
        fabFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReyeler();
            }
        });

        return view;
    }


    public static void setReyeler(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("method", "_GET");
        params.put("table","friendtable");
        params.put("loverid","jan");
        client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    static class UrlData {

        /**
         * 图片假数据
         */
        private static final String IMGURL1 = "http://img.hb.aicdn.com/05927bdaec8213d858a0c3ec201ea0f405ad40e845d02-qJDlLb_fw658";
        private static final String IMGURL2 = "http://img.hb.aicdn.com/98e2007c524387a1d6444f9b80a15cf253d408b2244ed-owRaCM_fw658";
        private static final String IMGURL3 = "http://img.hb.aicdn.com/f69f6ea969f2231be1f9fe6ffd0e73965774a6336986f-mEVEjy_fw658";
        private static final String IMGURL4 = "http://img.hb.aicdn.com/c7f89bec028ecdc8348e80b0911baf10666f932b40396-u7wY7L_fw658";
        private static final String IMGURL5 = "http://img.hb.aicdn.com/d99978ec4bd8ac4013ac1b36b00a8c13098a5827540dd-LkJX6p_fw658";
        private static final String IMGURL6 = "http://img.hb.aicdn.com/e411e58dbd56ad3227724bbbbd7eb07416e4b43a46a41-JGzo9p_fw658";
        private static final String IMGURL7 = "http://img.hb.aicdn.com/75225644fec9d08dd4fdde72def94de0998cb38528a77-dOl6eM_fw658";
        private static final String IMGURL8 = "http://img.hb.aicdn.com/25e4071ba9d56aec8997857d916811e2cb020256504be-897MSv_fw658";
        private static final String IMGURL9 = "http://img.hb.aicdn.com/9b31060eaa4185bbb660af61d8c72206746657782631e-IviKwU_fw658";
        private static final String IMGURL10 = "http://img.hb.aicdn.com/dad95cc911f2e9b9bd614aacab76fc004c9d8d7e205bd-Awr1Pc_fw658";
        private static final String IMGURL11 = "http://img.hb.aicdn.com/9eececc00249510fcae7fa8253d00629969b8b52131e3-F5DykE_fw658";
        private static final String IMGURL12 = "http://img.hb.aicdn.com/79df58c579c840c2fe8f4a10eff2ceb34737c9df3915c-ZuiCYr_fw658";
        private static final String IMGURL13 = "http://img.hb.aicdn.com/e656366714f79488096591ab01f14e7d6d963782292b4-iRFQVL_fw658";
        private static final String IMGURL14 = "http://img.hb.aicdn.com/a7ab84658399a8a9299e25c73c4c32e365d743b4344d7-X6J071_fw658";
        private static final String IMGURL15 = "http://img.hb.aicdn.com/3c5bdda5f73ed9f0dfe7c1f4ade4959ad82721ee11964-lfaGLD_fw658";

        public static List<String> getImageLists() {
            List<String> photoLists = new ArrayList<>();

            photoLists.add(IMGURL1);
            photoLists.add(IMGURL2);
            photoLists.add(IMGURL3);
            photoLists.add(IMGURL4);
            photoLists.add(IMGURL5);
            photoLists.add(IMGURL6);
            photoLists.add(IMGURL7);
            photoLists.add(IMGURL8);
            photoLists.add(IMGURL9);
            photoLists.add(IMGURL10);
            photoLists.add(IMGURL11);
            photoLists.add(IMGURL12);
            photoLists.add(IMGURL13);
            photoLists.add(IMGURL14);
            photoLists.add(IMGURL15);

            return photoLists;
        }

    }
}