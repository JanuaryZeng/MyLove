package cn.edu.nuc.Helper;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;


import cn.edu.nuc.fragment.FriendFragment;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;
import cz.msebera.android.httpclient.entity.mime.content.StringBody;
import cz.msebera.android.httpclient.impl.client.CloseableHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

public class UpLoadPhotos   {

    public static void init(String[] path,String cont){
        String serverURL = "http://"+IDHelper.getIP()+":8000/uploading/";
//        String path = "/storage/emulated/0/阅图/PictureUnlock_hknew_78340.pictureunlock.jpg";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/KK.jpeg";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setCharset(Charset.forName(HTTP.UTF_8));
        int len = path.length;

        StringBody length = new StringBody(String.valueOf(len), ContentType.TEXT_PLAIN);
        StringBody loverid = new StringBody(IDHelper.getID(), ContentType.TEXT_PLAIN);
        StringBody usergender = new StringBody(IDHelper.getGender(), ContentType.TEXT_PLAIN);
        StringBody frienddate = new StringBody(TimeForm.getNowTime(), ContentType.TEXT_PLAIN);
        StringBody friendtext = null;
        try {
            friendtext = new StringBody(cont, Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        multipartEntityBuilder.addPart("len",length);
        multipartEntityBuilder.addPart("loverid",loverid);
        multipartEntityBuilder.addPart("usergender",usergender);
        multipartEntityBuilder.addPart("frienddate",frienddate);
        multipartEntityBuilder.addPart("friendtext",friendtext);


        for(int i = 0; i < len; i++){
            FileBody bin = new FileBody(new File(path[i]));
            multipartEntityBuilder.addPart("sourcefiles"+String.valueOf(i), bin);
        }

        HttpPost httppost = new HttpPost(serverURL);
        // 默认的的ContentType是application/octet-stream
// FileBody bin = new FileBody(new File(path), ContentType.create("image/png"));

        // 其他文本类型的参数
        HttpEntity reqEntity = multipartEntityBuilder.build();

        httppost.setEntity(reqEntity);

        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {

                System.out.println("服务器正常返回的数据: " + EntityUtils.toString(resEntity));// httpclient自带的工具类读取返回数据

                System.out.println(resEntity.getContent());

            } else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {

                System.out.println("上传文件发生异常，请检查服务端异常问题");
            }

            EntityUtils.consume(resEntity);
            response.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initIcon(String path){
        String serverURL = "http://"+IDHelper.getIP()+":8000/uploadicon/";
//        String path = "/storage/emulated/0/阅图/PictureUnlock_hknew_78340.pictureunlock.jpg";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/KK.jpeg";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        StringBody userid = new StringBody(IDHelper.getUserId(), ContentType.TEXT_PLAIN);
        StringBody usergender = new StringBody(IDHelper.getGender(), ContentType.TEXT_PLAIN);
        StringBody username = new StringBody(IDHelper.getMyName(), ContentType.TEXT_PLAIN);
        StringBody userborn = new StringBody(IDHelper.getBorn(), ContentType.TEXT_PLAIN);
        StringBody loverid = new StringBody(IDHelper.getID(), ContentType.TEXT_PLAIN);


        multipartEntityBuilder.addPart("userid",userid);
        multipartEntityBuilder.addPart("usergender",usergender);
        multipartEntityBuilder.addPart("username",username);
        multipartEntityBuilder.addPart("userborn",userborn);
        multipartEntityBuilder.addPart("loverid",loverid);

        FileBody bin = new FileBody(new File(path));
        multipartEntityBuilder.addPart("icon", bin);

        HttpPost httppost = new HttpPost(serverURL);
        // 默认的的ContentType是application/octet-stream
// FileBody bin = new FileBody(new File(path), ContentType.create("image/png"));

        // 其他文本类型的参数
        HttpEntity reqEntity = multipartEntityBuilder.build();

        httppost.setEntity(reqEntity);

        try {
            CloseableHttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {

                System.out.println("服务器正常返回的数据: " + EntityUtils.toString(resEntity));// httpclient自带的工具类读取返回数据

                System.out.println(resEntity.getContent());

            } else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {

                System.out.println("上传文件发生异常，请检查服务端异常问题");
            }

            EntityUtils.consume(resEntity);
            response.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
