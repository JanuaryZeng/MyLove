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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

//    private ImageView ivFriendIcon = null;


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
//                    Log.e("xujian","*-*-*-*-*-*-*-/*-/*-/*-/*-/*-/*-/*-///---=="+friendNotes);
                    break;
                case 20:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private View InitView(View view){
        //*********************************头像（逝去的功能注释一下以表纪念）***********************************************************************

//        ivFriendIcon = view.findViewById(R.id.ivFriendIcon);
//        Glide.with(view.getContext()).load(IDHelper.getMyIcon()).into(ivFriendIcon);
//        ivFriendIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setReyeler();
//            }
//        });
        //*********************************第一次记录（逝去的功能注释一下以表纪念）***********************************************************************

//        List<FirstTimeNote> firstTimeNotes = new ArrayList<FirstTimeNote>();
//
//        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
//        firstTimeNotes.add(new FirstTimeNote("7", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
//        firstTimeNotes.add(new FirstTimeNote("2", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
//        firstTimeNotes.add(new FirstTimeNote("8", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
//        firstTimeNotes.add(new FirstTimeNote("3", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
//
//        RecyclerView friendRecycler = (RecyclerView) view.findViewById(R.id.firstRecycler);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation((LinearLayout.HORIZONTAL));
//        friendRecycler.setLayoutManager(linearLayoutManager);
//        friendRecycler.setAdapter(new FirstTimeAdapter(firstTimeNotes));


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
//        try
//        {
//            Thread.currentThread().sleep(100);//毫秒
//        }
//        catch(Exception e){}
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("method", "_GET");
        params.put("table","friendtable");
        params.put("loverid",IDHelper.getLoverID());
        client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
}