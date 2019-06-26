package cn.edu.nuc.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nuc.Adapter.MoneyAdapter;
import cn.edu.nuc.Adapter.PencilAdapter;
import cn.edu.nuc.DBOpenHelper.DBMoneyNote;
import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.JSONTOOL;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.AddMoneyActivity;

public class NoteFragment extends BaseFragment {
    private  List<MoneyNote> list = new ArrayList<MoneyNote>();
    private MoneyAdapter moneyAdapter;
    private LinearLayoutManager layoutManager;
    RecyclerView moneyRecycler;
    View view = null;

    private ImageView imJiYiBi = null;

    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_note, container, false);
        viewInit(view);
        return view;
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void viewInit(View view){
        Bundle bundle = new Bundle();
        moneyRecycler = view.findViewById(R.id.moneyRecycler);
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
//                延时
                try
                        {
                        Thread.currentThread().sleep(1000);//毫秒
                        }
                        catch(Exception e){}
                switch (msg.what) {
                    case 3:
                        List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
//                        Log.e("123456", msg.obj.toString());
//
//                        Log.e("123456", String.valueOf(maps.size()));
                        for(Map<String, String> map : maps){
                            MoneyNote moneyNote = new MoneyNote();
                            moneyNote.setTime(map.get("moneydate"));
                            String montyid = map.get("moneytypeid");
                            int icon = 0;
                            if(MoneyTypeTable.getLeftMap().containsKey(montyid)){
                                icon = Integer.valueOf(MoneyTypeTable.getLeftMap().get(montyid));
                                moneyNote.setText(montyid+" -"+map.get("moneynumber"));
                                moneyNote.setItemType(1);
                                moneyNote.setIcon(icon);
                            }
                            else if(MoneyTypeTable.getRightMap().containsKey(montyid)){
                                Log.e("123456","---------" +
                                        MoneyTypeTable.getRightMap().get(montyid));
                                icon = Integer.valueOf(MoneyTypeTable.getRightMap().get(montyid));
                                moneyNote.setText(montyid+" +"+map.get("moneynumber"));
                                moneyNote.setItemType(2);
                                moneyNote.setIcon(icon);
                            }else{
                                moneyNote.setItemType(1);
                                moneyNote.setIcon(R.mipmap.icon_friend);
                            }
//                            Log.e("123456",map.get("moneydate"));
//                            Log.e("123456",map.get("moneytypeid"));
//                            Log.e("123456",map.get("moneynumber"));
                            list.add(moneyNote);
                        }
                        Collections.reverse(list);
                        layoutManager = new LinearLayoutManager(getContext());
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        moneyRecycler.setLayoutManager(layoutManager);
                        moneyAdapter = new MoneyAdapter(list);
                        moneyRecycler.setAdapter(moneyAdapter);
                        Log.e("123456","正确！！！");
                        break;
                    case 30:
                        Log.e("123456","错误！！！");
                        break;
                }
                super.handleMessage(msg);
            }
        };
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("table","moneychangetable");
            params.put("method", "_GET");
            params.put("loverid", "jan");
            client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 3, 30));
// MoneyNote(int itemType, String money, String time, String text
//            list.add(new MoneyNote(2, R.mipmap.icon_add_touch,
//                    "+10","2019-06-28","工作 10.0"));
//            list.add(new MoneyNote(1,R.mipmap.icon_add,
//                    "-10","2019-06-28","餐饮 -10.0"));
//            list.add(new MoneyNote(1,R.mipmap.icon_add,
//                    "-30","2019-06-28","租车 -30.0"));
//            list.add(new MoneyNote(1,R.mipmap.icon_add,
//                    "-30","2019-06-28","停车 -30.0"));
//            list.add(new MoneyNote(2,R.mipmap.icon_add_touch,
//                    "+10","2019-06-28","打工 10.0"));

        imJiYiBi = view.findViewById(R.id.imJiYiBi);
        imJiYiBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMoneyActivity.class);
                startActivity(intent);
            }
        });

        }

    @Override
    public void onResume() {
        super.onResume();
    }
}
