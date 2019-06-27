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

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Toast.makeText(UpdateMoneyActivity.this, "修改成功", Toast.LENGTH_LONG).show();//信息框
                    NoteFragment noteFragment = new NoteFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("moneychangeid", String.valueOf(moneychangeid));
                    bundle.putString("moneydate",moneydate);
                    bundle.putString("moneynumber", String.valueOf(etUpdateMon.getText()));
                    bundle.putString("moneytypeid",spUpdateMon.getSelectedItem().toString());
                    noteFragment.setArguments(bundle);

                    finish();
                    break;
                case 20:
                    Toast.makeText(UpdateMoneyActivity.this, "服务器错误", Toast.LENGTH_LONG).show();//信息框
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
                params.put("loverid","jan");
                params.put("moneychangeid",moneychangeid);
                params.put("moneydate",moneydate);
                params.put("moneynumber",etUpdateMon.getText());
                params.put("moneytypeid",spUpdateMon.getSelectedItem().toString());

                Log.e("789654", String.valueOf(moneychangeid));
                Log.e("789654",moneydate);
                Log.e("789654", String.valueOf(etUpdateMon.getText()));
                Log.e("789654",spUpdateMon.getSelectedItem().toString());

                client.post("http://"+IDHelper.IP+":8000/android_user/", params, new DjangoListener(this.handler, 2, 20));
                break;
            case R.id.btndateMon2:
                finish();
                break;
            default:
                break;
        }
    }
}
