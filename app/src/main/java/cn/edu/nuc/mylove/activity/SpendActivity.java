package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.Helper.TimeForm;
import cn.edu.nuc.fragment.PencilFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class SpendActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spSpend = null;
    private Spinner spPencil = null;

    private EditText editText21 = null;
    private EditText editText22 = null;

    private Button button21 = null;
    private Button button22 = null;
    private Button button23 = null;

    private String time = null;

    @SuppressLint("HandlerLeak")

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(SpendActivity.this, "添加成功", Toast.LENGTH_LONG).show();//信息框
                    finish();
                    break;
                case 20:
                    Toast.makeText(SpendActivity.this, "服务器错误", Toast.LENGTH_LONG).show();//信息框
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);

        spSpend = findViewById(R.id.spSpend);
        spPencil = findViewById(R.id.spPencil);
        editText21 = findViewById(R.id.editText21);
        editText22 = findViewById(R.id.editText22);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);

        if(MoneyTypeTable.getNoteIds()!= null) {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MoneyTypeTable.getIds());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spSpend.setAdapter(adapter);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MoneyTypeTable.getNoteId());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spPencil.setAdapter(adapter1);

            button21.setOnClickListener(this);
            button22.setOnClickListener(this);
            button23.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button21:
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                time = TimeForm.getNowNoMinTime();
                params.put("method", "_POST");
                params.put("table","notetable");
                params.put("loverid","jan");
                params.put("notedate", time);
                params.put("notetext",editText21.getText());
                params.put("moneytypeid",spSpend.getSelectedItem().toString());
                params.put("notestatus","1");
                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));
                PencilFragment.setRecycer();
                finish();
                break;
            case R.id.button22:
                AsyncHttpClient client1 = new AsyncHttpClient();
                RequestParams params1 = new RequestParams();
                time = TimeForm.getNowNoMinTime();
                params1.put("method", "_POST");
                params1.put("table","notetable");
                params1.put("loverid","jan");
                params1.put("notedate", time);
                params1.put("notetext",editText22.getText());
                params1.put("moneytypeid",spPencil.getSelectedItem().toString());
                params1.put("notestatus","2");
                client1.post("http://"+ IDHelper.IP+":8000/android_user/", params1, new DjangoListener(this.handler, 2, 20));
                PencilFragment.setRecycer();
                finish();
                break;
            case R.id.button23:
                finish();
                break;
        }
    }
}
