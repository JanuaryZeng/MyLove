package cn.edu.nuc.Adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.TimeForm;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.fragment.PencilFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

public class PencilAdapter extends BaseMultiItemQuickAdapter<PencilNote,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    PencilFragment.setRecycer();
                    break;
                case 20:
                    Toast.makeText(mContext, "服务器错误", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    break;
                case 30:
                    Toast.makeText(mContext, "服务器错误", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    NoteFragment.setReyeler();
                    break;
                case 40:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public PencilAdapter(List<PencilNote> data) {
        super(data);
        addItemType(PencilNote.UP, R.layout.pencil_note_up);
        addItemType(PencilNote.Down,R.layout.pencil_note_down);
        addItemType(PencilNote.TYPE,R.layout.pencil_note_type);
        addItemType(PencilNote.Fin,R.layout.pencil_note_fin);
        addItemType(PencilNote.Blank,R.layout.pencil_note_blank);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final PencilNote item) {
        switch(helper.getItemViewType()){
            case PencilNote.UP:

                helper.setImageResource(R.id.imPencilUpIocn,item.getIcon())
                        .setImageResource(R.id.imPencilUpIocn1,item.getIcon())
                        .setText(R.id.tvPencilUpName,item.getName())
                        .setText(R.id.tvPencilUpName1,item.getName())
                        .setText(R.id.tvPencilUpText,item.getText())
                        .setText(R.id.tvPencilUpText1,item.getText())
                        .setText(R.id.tvPencilUpYear,String.valueOf(item.getYear()))
                        .setText(R.id.tvPencilUpYear1,String.valueOf(item.getYear()))
                        .setText(R.id.tvPencilUpMon,String.valueOf(item.getMonth()))
                        .setText(R.id.tvPencilUpMon1,String.valueOf(item.getMonth()))
                        .setText(R.id.tvPencilUpDay,String.valueOf(item.getDay()))
                        .setText(R.id.tvPencilUpDay1,String.valueOf(item.getDay()))
                        .setOnClickListener(R.id.wancheng, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editText = helper.getView(R.id.edPencilUpMoney);
                                String money = editText.getText().toString().trim();
                                if (money == null)
                                    Toast.makeText(mContext, "金额不能为空", Toast.LENGTH_SHORT).show();
                                else {
                                    AsyncHttpClient client = new AsyncHttpClient();
                                    RequestParams params = new RequestParams();
                                    params.put("table", "moneychangetable");
                                    params.put("method", "_POST");
                                    params.put("loverid", IDHelper.getLoverID());
                                    params.put("moneytypeid", item.getText().toString().trim());
                                    params.put("moneydate", TimeForm.getNowTime());
                                    params.put("moneynumber", money);
                                    client.post("http://" + IDHelper.IP + ":8000/android_user/", params, new DjangoListener(handler, 2, 20));

                                    RequestParams params1 = new RequestParams();
                                    params1.put("table", "notetable");
                                    params1.put("method", "_PUT");
                                    params1.put("loverid", IDHelper.getLoverID());
                                    params1.put("noteid", item.getId());
                                    params1.put("moneytypeid", item.getText());
                                    params1.put("notedate", item.getDate());
                                    params1.put("notetext", item.getName());
                                    params1.put("notestatus", PencilNote.Fin);
                                    client.post("http://" + IDHelper.IP + ":8000/android_user/", params1, new DjangoListener(handler, 3, 30));

                                    RequestParams params2 = new RequestParams();
                                    params2.put("table","lovertable");
                                    params2.put("method", "_PUT");
                                    params2.put("loverid", IDHelper.getLoverID());
                                    params2.put("loverdate", IDHelper.date);
                                    params2.put("loverpassword", IDHelper.password);

                                        Log.e("yao3", "1  item.getName() :"+item.getText());
                                        Log.e("yao3", "1  IDHelper.map.get(item.getName()) :"+IDHelper.map.get(item.getText()));
                                    if(IDHelper.map.get(item.getText()) == 1){
                                        IDHelper.moneyin += Float.valueOf(money);
                                    }else if(IDHelper.map.get(item.getText()) == 2){
                                        IDHelper.moneyout += Float.valueOf(money);
                                    }
                                    Log.e("yao3", "2  money :"+money);
                                    Log.e("yao3", "2  IDHelper.monein :"+IDHelper.moneyin);
                                    Log.e("yao3", "2  IDHelper.moneyout :"+IDHelper.moneyout);
                                    params2.put("moneyout", IDHelper.moneyout);
                                    params2.put("moneyin", IDHelper.moneyin);

                                    client.post("http://"+IDHelper.IP+":8000/android_user/", params2, new DjangoListener(handler, 4, 40));
                                }
                            }
                        })
                        .setOnClickListener(R.id.foldingcell, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((FoldingCell) v).toggle(false);
                            }
                        });

                break;
            case PencilNote.Down:
                helper.setImageResource(R.id.imPencilDownIcon,item.getIcon())
                        .setText(R.id.tvPencilDownName,item.getName())
                        .setText(R.id.tvPencilDownText,item.getText())
                        .setOnClickListener(R.id.ivPenNote, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AsyncHttpClient client = new AsyncHttpClient();
                                RequestParams params = new RequestParams();
                                params.put("table","notetable");
                                params.put("method", "_PUT");
                                params.put("loverid", IDHelper.getLoverID());
                                params.put("noteid", item.getId());
                                params.put("moneytypeid", item.getText());
                                params.put("notedate", item.getDate());
                                params.put("notetext", item.getName());
                                params.put("notestatus", PencilNote.Fin);
                                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 3, 30));
                                PencilFragment.setRecycer();
                            }
                        });
                break;
            case PencilNote.TYPE:
                helper.setText(R.id.tvPencilType,item.getType())
                        .setText(R.id.tvPencilTypeNum,String.valueOf(item.getTypeNum()));
                break;
            case PencilNote.Fin:
                helper.setImageResource(R.id.imPencilFinIcon,item.getIcon())
                        .setText(R.id.tvPencilFinName,item.getName())
                        .setText(R.id.tvPencilFinText,item.getText())
                        .setOnClickListener(R.id.ivFinNote, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AsyncHttpClient client = new AsyncHttpClient();
                                RequestParams params = new RequestParams();
                                params.put("table","notetable");
                                params.put("method", "_DELETE");
                                params.put("noteid", item.getId());
                                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));
                                PencilFragment.setRecycer();
                            }
                        });
                break;
            case PencilNote.Blank:
                break;
            default:
                break;
        }
    }


}
