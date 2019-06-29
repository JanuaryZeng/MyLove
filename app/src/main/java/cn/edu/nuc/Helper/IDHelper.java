package cn.edu.nuc.Helper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class IDHelper  {

    public static String loverID = null;
    public static String IP = "10.0.116.45";
    public static float moneyin = (float) 0.0;
    public static float moneyout = (float) 0.0;
    public static String date = null;
    public static String password = null;
    public static int dir = 0;

    public static String gender = "man";
    public static String born = null;

    public static Map<String,String> userIcon = new HashMap<String,String>();
    public static Map<String,String> userName = new HashMap<String,String>();


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
                    case 3:
                        List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
                        for(Map<String, String> littlemap : maps){

                            String usergender = littlemap.get("usergender");
                            String username = littlemap.get("username");
                            String usericon = littlemap.get("usericon");
                            String userborn = littlemap.get("userborn");
//                            Log.e("jianming","************---"+gender);
//                            Log.e("jianming","************---"+usergender);
//                            Log.e("jianming","************---"+username);
//                            Log.e("jianming","************---"+usericon);
//                            Log.e("jianming","************---"+userborn);

                            if(usergender.equals("man")){
                                userIcon.put("man",usericon);
                                userName.put("man",username);
                                if(gender.equals("man")){
                                    born = userborn;
                                }
                            }else if(usergender.equals("woman")){
                                userIcon.put("woman",usericon);
                                userName.put("woman",username);
                                if(gender.equals("woman")){
                                    born = userborn;
                                }
                            }
//                            Log.e("jianming",myName);
//                            Log.e("jianming",born);
//                            Log.e("jianming", String.valueOf(userIcon));
//                            Log.e("jianjian","------22222222--********"+gender);
//                            Log.e("jianjian","-----222222---********"+userIcon);
                        }
                        break;
                    case 30:
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

        RequestParams params2 = new RequestParams();
        params2.put("table","usertable");
        params2.put("method", "_GET");
        params2.put("loverid", loverID);
        client.post("http://"+IDHelper.IP+":8000/android_user/", params2, new DjangoListener(handler, 3, 30));

    }

    public static String getMyIcon(){
//        Log.e("jianjian","--------********"+gender);
//        Log.e("jianjian","--------********"+userIcon);
        return userIcon.get(gender);
    }
    public static String getTaIcon(){
        if(gender.equals("man"))
            return userIcon.get("woman");
         else if(gender.equals("woman"))
             return userIcon.get("man");
         return "错误";
    }

    public static String getMyName(){
        return userName.get(gender);
    }

    public static String getTaName(){
        if(gender.equals("man"))
            return userName.get("woman");
        else if(gender.equals("woman"))
            return userName.get("man");
        return "错误";
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

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        IDHelper.IP = IP;
    }

    public static float getMoneyin() {
        return moneyin;
    }

    public static void setMoneyin(float moneyin) {
        IDHelper.moneyin = moneyin;
    }

    public static float getMoneyout() {
        return moneyout;
    }

    public static void setMoneyout(float moneyout) {
        IDHelper.moneyout = moneyout;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        IDHelper.date = date;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        IDHelper.password = password;
    }

    public static int getDir() {
        return dir;
    }

    public static void setDir(int dir) {
        IDHelper.dir = dir;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        IDHelper.gender = gender;
    }

    public static Map<String, Integer> getMap() {
        return map;
    }

    public static void setMap(Map<String, Integer> map) {
        IDHelper.map = map;
    }


    public static String getBorn() {
        return born;
    }

    public static void setBorn(String born) {
        IDHelper.born = born;
    }

    public static Map<String, String> getUserIcon() {
        return userIcon;
    }

    public static void setUserIcon(Map<String, String> userIcon) {
        IDHelper.userIcon = userIcon;
    }

    public static Map<String, String> getUserName() {
        return userName;
    }

    public static void setUserName(Map<String, String> userName) {
        IDHelper.userName = userName;
    }
}
