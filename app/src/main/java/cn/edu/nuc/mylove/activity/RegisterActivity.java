package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText editText5 = null;
    private EditText editText6 = null;
    private EditText editText7 = null;

    private Button button3 = null;

    @SuppressLint("HandlerLeak") Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();//信息框
                    finish();
                    break;
                case 20:
                    Toast.makeText(RegisterActivity.this, "此用户名已被使用", Toast.LENGTH_LONG).show();//信息框
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button3:
                String str1 = editText6.getText().toString();
                String str2 = editText7.getText().toString();

                if(!str1.equals(str2)){
                    Toast.makeText(this, "两次输入的密码不相同", Toast.LENGTH_SHORT).show();
                    break;
                }

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "_POST");
                params.put("table","lovertable");
                params.put("loverid",editText5.getText());
                params.put("loverpassword",editText6.getText());
                params.put("loverdate","1970-1-1");
                params.put("moneyout","0");
                params.put("moneyin","0");
                client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));

                RequestParams params1 = new RequestParams();
                params1.put("method", "_POST");
                params1.put("table","usertable");
                params1.put("loverid",editText5.getText());
                params1.put("usergender","man");
                params1.put("username","user");
                client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(this.handler, 2, 20));

                RequestParams params2 = new RequestParams();
                params2.put("method", "_POST");
                params2.put("table","usertable");
                params2.put("loverid",editText5.getText());
                params2.put("usergender","woman");
                params2.put("username","user");
                client.post("http://"+IDHelper.IP+":8000/android_user/", params2, new DjangoListener(this.handler, 2, 20));

                break;
        }
    }
}
