package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static Toast t;

    private EditText et_username = null;
    private EditText et_password = null;

    private Button login_btn = null;
    private TextView AITest = null;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    t = Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG);//信息框
                    t.show();//调用

                    SharedPreferences sp = getSharedPreferences("ID", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("loverid", String.valueOf(et_username.getText()));
                    editor.putString("password", String.valueOf(et_password.getText()));
                    editor.commit();

                    //读取数据
                    //String username = sp.getString(USERNAME, null);
                    //String username = sp.getString(PASSWORD, null);

                    IDHelper.loverID = String.valueOf(et_username.getText());
                    finish();
                    break;
                case 20:
                    t = Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_LONG);//信息框
                    t.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        AITest = findViewById(R.id.AITest);
        AITest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_btn:

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "_GET");
                params.put("table","lovertable");
                params.put("loverid",et_username.getText());
                params.put("loverpassword",et_password.getText());
                client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));

                break;
            case R.id.AITest:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
