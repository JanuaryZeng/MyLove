package cn.edu.nuc.Helper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.edu.nuc.myListener.DjangoListener;

public class MoneyTypeTable {
    private static Map<String, String> RightMap = new HashMap<String, String>();
    private static Map<String, String> LeftMap = new HashMap<String, String>();
    private static Map<String, String> AllMonMap = new HashMap<String, String>();
    private static Map<String, String> AllNoteMap = new HashMap<String, String>();

    private static List<String> noteId = new ArrayList<String>();
    private static List<String> id = new ArrayList<String>();
    private static String[] ids;
    private static String[] noteIds;



    public static void init(){
        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3:
                        List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
//('moneytypeid', 'moneytypeicon','moneydirction')
                        for(Map<String, String> map : maps){
//                            Log.e("321","方向"+map.get("moneydirction"));
//                            Log.e("321","名称"+map.get("moneytypeid"));
//                            Log.e("321","图像"+map.get("moneytypeicon"));

                            if(map.get("moneydirction").equals("1")){
                                RightMap.put(map.get("moneytypeid"),map.get("moneytypeicon"));
                                AllMonMap.put(map.get("moneytypeid"),map.get("moneytypeicon"));
                                id.add(map.get("moneytypeid"));
                            }
                            else  if(map.get("moneydirction").equals("2") ){
                                LeftMap.put(map.get("moneytypeid"),map.get("moneytypeicon"));
                                AllMonMap.put(map.get("moneytypeid"),map.get("moneytypeicon"));
                                id.add(map.get("moneytypeid"));
                            }
                            else  if(map.get("moneydirction").equals("3") ){
                                AllNoteMap.put(map.get("moneytypeid"),map.get("moneytypeicon"));
                                noteId.add(map.get("moneytypeid"));
                            }
                        }
//                        Log.e("321","Rightsize：  "+RightMap.size());
//                        Log.e("321","Leftsize：  "+RightMap.size());
                        Log.e("321","正确！！！");
                        ids = new String[id.size()];
                        for(int i = 0; i < id.size(); i++){
                            ids[i] = id.get(i);
                        }
                        noteIds = new String[noteId.size()];
                        for(int i = 0; i < noteId.size(); i++){
                            noteIds[i] = noteId.get(i);
                        }
                        break;
                    case 30:
                        Log.e("321","错误！！！");
                        break;
                }
                super.handleMessage(msg);
            }
        };

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("table","moneytypetable");
        params.put("method", "_GET");
        client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 3, 30));
    }

    public static Map<String, String> getRightMap() {
        return RightMap;
    }

    public static Map<String, String> getLeftMap() {
        return LeftMap;
    }
    public static List<String> getId() {
        return id;
    }

    public static String[] getIds() {
        return ids;
    }

    public static Map<String, String> getAllMonMap() {
        return AllMonMap;
    }

    public static Map<String, String> getAllNoteMap() {
        return AllNoteMap;
    }

    public static List<String> getNoteId() {
        return noteId;
    }

    public static String[] getNoteIds() {
        return noteIds;
    }
}
