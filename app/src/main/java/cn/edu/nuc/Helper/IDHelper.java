package cn.edu.nuc.Helper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;

public class IDHelper  {
    public static String loverID = null;
    public static String IP = "10.0.116.45";
    public static float moneyin = (float) 0.0;
    public static float moneyout = (float) 0.0;
    public static String date = null;
    public static String password = null;
    public static int dir = 0;

    public static Map<String, Integer> map = new HashMap<String,Integer>();

    static{
        map.put("交通",2);
        map.put("人情",2);
        map.put("住房",2);
        map.put("其他收入",1);
        map.put("兼职",1);
        map.put("医疗",2);
        map.put("娱乐",2);
        map.put("工资",1);
        map.put("投资",1);
        map.put("教育",2);
        map.put("旅行",2);
        map.put("红包",1);
        map.put("购物",2);
        map.put("餐饮",2);
    }
    @SuppressLint("HandlerLeak")

    public static void init(){
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 2:
                        Map<String, String> map = JSONTOOL.analyze_once_json(msg.obj.toString());
                        date = map.get("loverdate");
                        password = map.get("loverpassword");
                        moneyin = Float.valueOf(map.get("moneyin"));
                        moneyout = Float.valueOf(map.get("moneyout"));
                        break;
                    case 20:
                        break;
                }
                super.handleMessage(msg);
            }
        };

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.put("table","lovertable");
        params1.put("method", "_Money");
        params1.put("Mate", "_GET");
        params1.put("loverid", loverID);
        client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(handler, 2, 20));

    }

    public static String getID(){
        return loverID;
    }

    public static String getLoverID() {
        return loverID;
    }

    public static void setLoverID(String loverID) {
        IDHelper.loverID = loverID;
    }

}
