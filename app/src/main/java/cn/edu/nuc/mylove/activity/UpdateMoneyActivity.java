package cn.edu.nuc.mylove.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class UpdateMoneyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUpdateMon = null;
    private Spinner spUpdateMon = null;

    private Button btndateMon1 = null;
    private Button btndateMon2 = null;
    private int moneychangeid = 0;
    private String moneydate = null;

    private float old = (float) 0.0;
    private float newable = (float) 0.0;

    private int oldDir = 0;
    private int newDir = 0;

    float in = (float) 0.0;
    float out = (float)0.0;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    NoteFragment noteFragment = new NoteFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("moneychangeid", String.valueOf(moneychangeid));
                    bundle.putString("moneydate",moneydate);
                    bundle.putString("moneynumber", String.valueOf(etUpdateMon.getText()));
                    bundle.putString("moneytypeid",spUpdateMon.getSelectedItem().toString());
                    noteFragment.setArguments(bundle);
                    break;
                case 20:
                    Toast.makeText(UpdateMoneyActivity.this, "服务器错误", Toast.LENGTH_LONG).show();//信息框
                    break;
                case 3:
                    IDHelper.moneyin = in;
                    IDHelper.moneyout = out;

                    Toast.makeText(UpdateMoneyActivity.this, "更新成功", Toast.LENGTH_LONG).show();//信息框
                    finish();
                    NoteFragment.setReyeler();
                    break;
                case 30:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_money);

        Intent intent = getIntent();
        String moneytypeid = intent.getStringExtra("moneytypeid").toString().trim();
        moneychangeid = intent.getIntExtra("moneychangeid",0);
        moneydate = intent.getStringExtra("moneydate").toString().trim();


        String str[] = moneytypeid.split(" ");

        etUpdateMon = findViewById(R.id.etUpdateMon);
        spUpdateMon = findViewById(R.id.spUpdateMon);
        etUpdateMon.setText(str[1]);
        old = Float.valueOf(str[1]);
        oldDir = IDHelper.dir;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MoneyTypeTable.getIds());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUpdateMon.setSelection(MoneyTypeTable.getId().indexOf(str[0]));
        spUpdateMon.setAdapter(adapter);

        btndateMon1 = findViewById(R.id.btndateMon1);
        btndateMon2 = findViewById(R.id.btndateMon2);

        btndateMon1.setOnClickListener(this);
        btndateMon2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btndateMon1:
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("method", "_PUT");
                params.put("table","moneychangetable");
                params.put("loverid",IDHelper.getLoverID());
                params.put("moneychangeid",moneychangeid);
                params.put("moneydate",moneydate);
                params.put("moneynumber",etUpdateMon.getText());
                params.put("moneytypeid",spUpdateMon.getSelectedItem().toString());
                newable = Float.valueOf(String.valueOf(etUpdateMon.getText()));
                newDir = Integer.valueOf(IDHelper.map.get(spUpdateMon.getSelectedItem().toString()));
                Log.e("789654", String.valueOf(moneychangeid));
                Log.e("789654",moneydate);
                Log.e("789654", String.valueOf(etUpdateMon.getText()));
                Log.e("789654",spUpdateMon.getSelectedItem().toString());
                client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));

                RequestParams params1 = new RequestParams();
                params1.put("table","lovertable");
                params1.put("method", "_PUT");
                params1.put("loverid", IDHelper.getLoverID());
                params1.put("loverdate", IDHelper.date);
                params1.put("loverpassword", IDHelper.password);


                Log.e("yaoyao", "oldDir :"+oldDir);
                Log.e("yaoyao", "newDir :"+newDir);
                Log.e("yaoyao", "IDHelper.monein :"+IDHelper.moneyin);
                Log.e("yaoyao", "IDHelper.moneyout :"+IDHelper.moneyout);
                Log.e("yaoyao", "newable :"+newable);
                Log.e("yaoyao", "old :"+old);
                if(oldDir == 1){
                    if(newDir == 1){
                        in = newable - old + IDHelper.moneyin;
                        out = IDHelper.moneyout;
                    }else if(newDir == 2){
                        in = IDHelper.moneyin - old;
                        out = IDHelper.moneyout + newable;
                    }
                }else if(oldDir == 2){
                    if(newDir == 1){
                        in = IDHelper.moneyin + newable;
                        out = IDHelper.moneyout - old;
                    }else if(newDir == 2){
                        in = IDHelper.moneyin;
                        out = newable - old + IDHelper.moneyout;
                    }
                }
                params1.put("moneyout", out);
                params1.put("moneyin", in);
                Log.e("yaoyao", "out :"+String.valueOf(out));
                Log.e("yaoyao", "in :"+String.valueOf(in));
                Log.e("yaoyao", "date :"+IDHelper.date);
                Log.e("yaoyao", "password :"+IDHelper.password);


                client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(this.handler, 3, 30));
                break;
            case R.id.btndateMon2:
                finish();
                break;
            default:
                break;
        }
    }
}
