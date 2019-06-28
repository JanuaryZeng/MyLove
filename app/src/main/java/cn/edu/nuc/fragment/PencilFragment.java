package cn.edu.nuc.fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.ramotion.foldingcell.FoldingCell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.edu.nuc.Adapter.MoneyAdapter;
import cn.edu.nuc.Adapter.PencilAdapter;
import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.JSONTOOL;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.Helper.TimeForm;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class PencilFragment extends BaseFragment {

    private static  List<PencilNote> list = new ArrayList<PencilNote>();
    private static PencilAdapter pencilAdapter;
    private static LinearLayoutManager layoutManager;
    private static RecyclerView pencilRecycler;
    private static TextView tvPenXiao = null;
    private static TextView tvPenDai = null;
    private static TextView tvPenWan = null;
    private static TextView tvPenTong = null;

    @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 3:
                    list.clear();
                    List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
                    int xiaofei = 0;
                    int daiban = 0;
                    int finish = 0;
                    for(Map<String, String> map : maps){
                        int notestatus = Integer.valueOf(map.get("notestatus"));
                        if(notestatus == 1) {
                            xiaofei++;
                        }else if(notestatus == 2){
                            daiban++;
                        }else if(notestatus == 4){
                            finish++;
                        }
                    }
                    PencilNote pencilNote1 = new PencilNote();
                    pencilNote1.setItemType(PencilNote.TYPE);
                    pencilNote1.setTypeNum(xiaofei);
                    pencilNote1.setType("消费代办");
                    tvPenXiao.setText(String.valueOf(xiaofei));
                    list.add(pencilNote1);

                    for(Map<String, String> map : maps){
                        PencilNote pencilNote = new PencilNote();

                        int notestatus = Integer.valueOf(map.get("notestatus"));
                        if(notestatus == 1) {
                            int noteid = Integer.valueOf(map.get("noteid"));
                            String moneytypeid = map.get("moneytypeid");
                            String notedate = map.get("notedate");
                            String notetext = map.get("notetext");
                            String[] strs = notedate.split("-");
                            int Year = Integer.valueOf(strs[0]);
                            int Month = Integer.valueOf(strs[1]);
                            int Day = Integer.valueOf(strs[2]);
                            int icon = Integer.valueOf(MoneyTypeTable.getAllMonMap().get(moneytypeid))+1;

                            pencilNote.setDay(Day);
                            pencilNote.setMonth(Month);
                            pencilNote.setYear(Year);
                            pencilNote.setId(noteid);
                            pencilNote.setItemType(notestatus);
                            pencilNote.setName(notetext);
                            pencilNote.setText(moneytypeid);
                            pencilNote.setIcon(icon);
                            pencilNote.setDate(notedate);

                            list.add(pencilNote);
                        }
                    }

                    PencilNote pencilNote2 = new PencilNote();
                    pencilNote2.setItemType(PencilNote.TYPE);
                    pencilNote2.setTypeNum(daiban);
                    pencilNote2.setType("待处理");
                    tvPenDai.setText(String.valueOf(daiban));
                    list.add(pencilNote2);

                    for(Map<String, String> map : maps){
                        PencilNote pencilNote = new PencilNote();
                        int notestatus = Integer.valueOf(map.get("notestatus"));
                        if(notestatus == 2) {
                            int noteid = Integer.valueOf(map.get("noteid"));
                            String moneytypeid = map.get("moneytypeid");
                            String notedate = map.get("notedate");
                            String notetext = map.get("notetext");
//                            String[] strs = notedate.split("-");
//                            int Year = Integer.valueOf(strs[0]);
//                            int Month = Integer.valueOf(strs[1]);
//                            int Day = Integer.valueOf(strs[2]);
                            int icon = Integer.valueOf(MoneyTypeTable.getAllNoteMap().get(moneytypeid));

//                            pencilNote.setDay(Day);
//                            pencilNote.setMonth(Month);
//                            pencilNote.setYear(Year);
                            pencilNote.setId(noteid);
                            pencilNote.setItemType(notestatus);
                            pencilNote.setName(notetext);
                            pencilNote.setText(moneytypeid);
                            pencilNote.setIcon(icon);
                            pencilNote.setDate(notedate);

                            list.add(pencilNote);
                        }
                    }
                    PencilNote pencilNote3 = new PencilNote();
                    pencilNote3.setItemType(PencilNote.TYPE);
                    pencilNote3.setTypeNum(finish);
                    pencilNote3.setType("已完成");
                    tvPenDai.setText(String.valueOf(daiban));
                    tvPenWan.setText(String.valueOf(finish));
                    tvPenTong.setText(String.valueOf(daiban+xiaofei+finish));

                    list.add(pencilNote3);

                    for(Map<String, String> map : maps){
                        PencilNote pencilNote = new PencilNote();
                        int notestatus = Integer.valueOf(map.get("notestatus"));
                        if(notestatus == 4) {
                            int noteid = Integer.valueOf(map.get("noteid"));
                            String moneytypeid = map.get("moneytypeid");
                            String notedate = map.get("notedate");
                            String notetext = map.get("notetext");
//                            String[] strs = notedate.split("-");
//                            int Year = Integer.valueOf(strs[0]);
//                            int Month = Integer.valueOf(strs[1]);
//                            int Day = Integer.valueOf(strs[2]);
                            int icon = 0;
                            if(MoneyTypeTable.getAllNoteMap().containsKey(moneytypeid))
                                icon = Integer.valueOf(MoneyTypeTable.getAllNoteMap().get(moneytypeid));
                            else if(MoneyTypeTable.getAllMonMap().containsKey(moneytypeid))
                                icon = Integer.valueOf(MoneyTypeTable.getAllMonMap().get(moneytypeid));
//                            pencilNote.setDay(Day);
//                            pencilNote.setMonth(Month);
//                            pencilNote.setYear(Year);
                            pencilNote.setId(noteid);
                            pencilNote.setItemType(notestatus);
                            pencilNote.setName(notetext);
                            pencilNote.setText(moneytypeid);
                            pencilNote.setIcon(icon);
                            pencilNote.setDate(notedate);

                            list.add(pencilNote);
                        }
                    }
                    PencilNote pencilNote4 = new PencilNote();
                    pencilNote4.setItemType(PencilNote.Blank);
                    list.add(pencilNote4);
                    pencilRecycler.setLayoutManager(layoutManager);

                    pencilAdapter = new PencilAdapter(list);

                    pencilRecycler.setAdapter(pencilAdapter);
                    Log.e("jan", String.valueOf(list.size()));
                    Log.e("jan","正确！！！");
                    break;
                case 30:
                    Log.e("jan","错误！！！");
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pencil, container, false);
        initView(view);
        return view;
    }
//PencilNote(int itemType, Bitmap icon, String name, String text,String type,int typeNum)
//PencilNote(int itemType, Bitmap icon, String name, String text, String money, int year, int month, int day)
    private void initView(View view){

//        List<PencilNote> list = new ArrayList<PencilNote>();
//
//        list.add(new PencilNote(1, R.mipmap.icon_add
//                ,"铁锅门面","明天之前",200,2019,6,13));
//        list.add(new PencilNote(1, R.mipmap.icon_note
//                ,"大腕儿款面","今天",10,2019,6,12));
//        list.add(new PencilNote(3,"吃饭",2));
//        list.add(new PencilNote(2, R.mipmap.alarm_color
//                ,"大腕儿款面","今天"));
//        list.add(new PencilNote(2, R.mipmap.cancel
//                ,"铁锅门面","明天"));
//        list.add(new PencilNote(3,"学习",3));
//        list.add(new PencilNote(2, R.mipmap.icon_add_touch
//                ,"政治","今天"));
//        list.add(new PencilNote(2, R.mipmap.find1
//                ,"英语","明天"));
//        list.add(new PencilNote(2, R.mipmap.find
//                ,"数学","今天"));

        layoutManager = new LinearLayoutManager(getContext());
        pencilRecycler = view.findViewById(R.id.pencilRecyclerView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        tvPenXiao = view.findViewById(R.id.tvPenXiao);
        tvPenDai = view.findViewById(R.id.tvPenDai);
        tvPenWan = view.findViewById(R.id.tvPenWan);
        tvPenTong = view.findViewById(R.id.tvPenTong);
        setRecycer();

    }

    public static void setRecycer(){
        list.clear();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("table","notetable");
        params.put("method", "_GET");
        params.put("loverid", "jan");
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 3, 30));
    }

}
