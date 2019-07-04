package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class AddMoneyActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText17 = null;

    private Button button13 = null;
    private Button button14 = null;

    private Spinner spAddMon = null;

    private String time = null;

    @SuppressLint("HandlerLeak")

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(AddMoneyActivity.this, "添加成功", Toast.LENGTH_LONG).show();//信息框
                    NoteFragment.setReyeler();
                    break;
                case 20:
                    Toast.makeText(AddMoneyActivity.this, "服务器错误", Toast.LENGTH_LONG).show();//信息框
                    break;
                case 3:
                    NoteFragment.setReyeler();
                    finish();
                    break;
                case 30:
                    Toast.makeText(AddMoneyActivity.this, "服务器错误", Toast.LENGTH_LONG).show();//信息框
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        editText17 = findViewById(R.id.editText17);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button14.setOnClickListener(this);

        spAddMon = findViewById(R.id.spAddMon);
        if(MoneyTypeTable.getIds()!= null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MoneyTypeTable.getIds());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAddMon.setAdapter(adapter);
            button13.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button13:
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                time = TimeForm.getNowTime();
                params.put("method", "_POST");
                params.put("table","moneychangetable");
                params.put("loverid",IDHelper.getLoverID());
                params.put("moneydate", time);
                params.put("moneynumber",editText17.getText());
                params.put("moneytypeid",spAddMon.getSelectedItem().toString());
                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));

                RequestParams params1 = new RequestParams();
                params1.put("table","lovertable");
                params1.put("method", "_PUT");
                params1.put("loverid", IDHelper.getLoverID());
                params1.put("loverdate", IDHelper.date);
                params1.put("loverpassword", IDHelper.password);

                Log.e("yao1", "1  IDHelper.monein :"+IDHelper.moneyin);
                Log.e("yao1", "1  IDHelper.moneyout :"+IDHelper.moneyout);

                if(IDHelper.map.get(spAddMon.getSelectedItem().toString()) == 1){
                    IDHelper.moneyin += Float.valueOf(String.valueOf(editText17.getText()));
                }else if(IDHelper.map.get(spAddMon.getSelectedItem().toString()) == 2){
                    IDHelper.moneyout += Float.valueOf(String.valueOf(editText17.getText()));
                }
                Log.e("yao1", "2  text :"+String.valueOf(editText17.getText()));
                Log.e("yao1", "2  IDHelper.monein :"+IDHelper.moneyin);
                Log.e("yao1", "2  IDHelper.moneyout :"+IDHelper.moneyout);
                params1.put("moneyout", IDHelper.moneyout);
                params1.put("moneyin", IDHelper.moneyin);

                client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(this.handler, 3, 30));
                break;

            case R.id.button14:
                finish();
                break;
            default:
                break;
        }
    }
}
