package cn.edu.nuc.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.Adapter.FirstTimeAdapter;
import cn.edu.nuc.Adapter.FriendAdapter;
import cn.edu.nuc.DataBase.FirstTimeNote;
import cn.edu.nuc.DataBase.FriendNote;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class FriendFragment extends BaseFragment {

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        InitView(view);

        return view;
    }

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

        List<FriendNote> friendNotes = new ArrayList<FriendNote>();

        List<String> photos = new ArrayList<String>();
        List<String> photos1 = new ArrayList<String>();
        List<String> photos2 = new ArrayList<String>();
        List<String> photos3 = new ArrayList<String>();
        List<String> photos4 = new ArrayList<String>();



        photos.add(IMGURL1);
        photos1.add(IMGURL1);
        photos2.add(IMGURL1);
        photos3.add(IMGURL1);
        photos4.add(IMGURL1);



        friendNotes.add(new FriendNote(BitmapFactory.decodeResource(this.getResources(), R.mipmap.find),
                "Jan","落霞与孤鹜齐飞，秋水共长天一色","30min",photos ));
        photos1.add(IMGURL2);
        photos2.add(IMGURL2);
        photos3.add(IMGURL2);
        photos4.add(IMGURL2);


        friendNotes.add(new FriendNote(BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1),
                "Yao","天青色等烟雨而我在等你","35min",photos1 ));
        photos2.add(IMGURL3);
        photos2.add(IMGURL4);
        photos3.add(IMGURL3);
        photos3.add(IMGURL4);
        photos4.add(IMGURL3);
        photos4.add(IMGURL4);


        friendNotes.add(new FriendNote(BitmapFactory.decodeResource(this.getResources(), R.mipmap.find),
                "Jan","落霞与孤鹜齐飞","1h25min",photos2 ));
        photos3.add(IMGURL6);
        photos3.add(IMGURL7);
        photos3.add(IMGURL8);
        photos3.add(IMGURL9);
        photos3.add(IMGURL10);
        photos4.add(IMGURL6);
        photos4.add(IMGURL7);
        photos4.add(IMGURL8);
        photos4.add(IMGURL9);
        photos4.add(IMGURL10);
        friendNotes.add(new FriendNote(BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1),
                "Yao","秋水共长天一色","12h25min",photos3 ));
        photos4.add(IMGURL15);
        friendNotes.add(new FriendNote(BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1),
                "Yao","天青","3天前",photos4 ));

        RecyclerView mRv = view.findViewById(R.id.friendRecycler);
        mRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRv.setAdapter(new FriendAdapter(friendNotes));
        return view;
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
