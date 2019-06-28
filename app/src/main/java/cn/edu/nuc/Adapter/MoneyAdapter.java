package cn.edu.nuc.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.RegisterActivity;
import cn.edu.nuc.mylove.activity.UpdateMoneyActivity;

public class MoneyAdapter extends BaseMultiItemQuickAdapter<MoneyNote,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MoneyAdapter(List<MoneyNote> data) {
        super(data);
        addItemType(MoneyNote.LEFT, R.layout.left_list_item);
        addItemType(MoneyNote.RIGHT,R.layout.right_list_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MoneyNote item) {
        switch(helper.getItemViewType()){
            case MoneyNote.LEFT:
                helper.setImageResource(R.id.imMoneyLeft, item.getIcon())
                        .setText(R.id.tvMoneyLeftTime,item.getTime())
                        .setText(R.id.tvMoneyLeft,item.getMoney())
                        .setText(R.id.tvMoneyLeftText,item.getText())
                        .setOnClickListener(R.id.tvMoneyLeftText, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), UpdateMoneyActivity.class);
                                intent.putExtra("moneytypeid",item.getText());
                                intent.putExtra("moneychangeid",item.getId());
                                intent.putExtra("moneydate",item.getTime());
                                IDHelper.dir = 2;
                                v.getContext().startActivity(intent);
                            }
                        }).setOnClickListener(R.id.rightDel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(v.getContext());
                        normalDialog.setTitle("删除");
                        normalDialog.setMessage("确定删除吗？");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {
                                        @SuppressLint("HandlerLeak") Handler handler = new Handler() {
                                            public void handleMessage(Message msg) {
                                                switch (msg.what) {
                                                    case 2:
                                                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                                        NoteFragment.setReyeler();
                                                        break;
                                                    case 20:
                                                        Toast.makeText(mContext, "服务器请求失败", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case 3:
                                                        NoteFragment.setReyeler();
                                                        dialog.dismiss();
                                                        break;
                                                    case 30:
                                                        Toast.makeText(mContext, "服务器请求失败", Toast.LENGTH_SHORT).show();
                                                        break;
                                                }
                                                super.handleMessage(msg);
                                            }
                                        };
                                        AsyncHttpClient client = new AsyncHttpClient();
                                        RequestParams params = new RequestParams();
                                        params.put("method", "_DELETE");
                                        params.put("table","moneychangetable");
                                        params.put("moneychangeid",item.getId());
                                        params.put("loverid","jan");
                                        client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));
                                        RequestParams params1 = new RequestParams();
                                        params1.put("table","lovertable");
                                        params1.put("method", "_PUT");
                                        params1.put("loverid", "jan");
                                        params1.put("loverdate", IDHelper.date);
                                        params1.put("loverpassword", IDHelper.password);

//                                        Log.e("yao2", "1  IDHelper.monein :"+IDHelper.moneyin);
//                                        Log.e("yao2", "1  IDHelper.moneyout :"+IDHelper.moneyout);
                                        IDHelper.moneyout += Float.valueOf(item.getMoney());
//                                        Log.e("yao2", "2  IDHelper.monein :"+IDHelper.moneyin);
//                                        Log.e("yao2", "2  IDHelper.moneyout :"+IDHelper.moneyout);
                                        params1.put("moneyout", IDHelper.moneyout);
                                        params1.put("moneyin", IDHelper.moneyin);

                                        client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(handler, 3, 30));

                                    }
                                });
                        normalDialog.setNegativeButton("关闭",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        // 显示
                        normalDialog.show();
                    }
                });
                break;
            case MoneyNote.RIGHT:
                helper.setImageResource(R.id.imMoneyRight,item.getIcon())
                        .setText(R.id.tvMoneyRightTime,item.getTime())
                        .setText(R.id.tvMoneyRight,item.getMoney())
                        .setText(R.id.tvMoneyRightText,item.getText())
                        .setOnClickListener(R.id.tvMoneyRightText, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(v.getContext(), UpdateMoneyActivity.class);
                                intent.putExtra("moneytypeid",item.getText());
                                intent.putExtra("moneychangeid",item.getId());
                                intent.putExtra("moneydate",item.getTime());
                                IDHelper.dir = 1;
                                v.getContext().startActivity(intent);
                            }
                        })
                        .setOnClickListener(R.id.leftDel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(v.getContext());
                                normalDialog.setTitle("删除");
                                normalDialog.setMessage("确定删除吗？");
                                normalDialog.setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(final DialogInterface dialog, int which) {
                                                @SuppressLint("HandlerLeak") Handler handler = new Handler() {
                                                    public void handleMessage(Message msg) {
                                                        switch (msg.what) {
                                                            case 2:
                                                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                                                NoteFragment.setReyeler();
                                                                break;
                                                            case 20:
                                                                Toast.makeText(mContext, "服务器请求失败", Toast.LENGTH_SHORT).show();
                                                                break;
                                                            case 3:
                                                                NoteFragment.setReyeler();
                                                                dialog.dismiss();
                                                                break;
                                                            case 30:
                                                                Toast.makeText(mContext, "服务器请求失败", Toast.LENGTH_SHORT).show();
                                                                break;
                                                        }
                                                        super.handleMessage(msg);
                                                    }
                                                };
                                                AsyncHttpClient client = new AsyncHttpClient();
                                                RequestParams params = new RequestParams();
                                                params.put("method", "_DELETE");
                                                params.put("table","moneychangetable");
                                                params.put("moneychangeid",item.getId());
                                                params.put("loverid","jan");
                                                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));
                                                RequestParams params1 = new RequestParams();
                                                params1.put("table","lovertable");
                                                params1.put("method", "_PUT");
                                                params1.put("loverid", "jan");
                                                params1.put("loverdate", IDHelper.date);
                                                params1.put("loverpassword", IDHelper.password);

                                                Log.e("yao2", "1  IDHelper.monein :"+IDHelper.moneyin);
                                                Log.e("yao2", "1  IDHelper.moneyout :"+IDHelper.moneyout);
                                                IDHelper.moneyin -= Float.valueOf(item.getMoney());
                                                Log.e("yao2", "2  IDHelper.monein :"+IDHelper.moneyin);
                                                Log.e("yao2", "2  IDHelper.moneyout :"+IDHelper.moneyout);
                                                params1.put("moneyout", IDHelper.moneyout);
                                                params1.put("moneyin", IDHelper.moneyin);

                                                client.post("http://"+IDHelper.IP+":8000/android_user/", params1, new DjangoListener(handler, 3, 30));
                                            }
                                        });
                                normalDialog.setNegativeButton("关闭",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                // 显示
                                normalDialog.show();
                            }
                        });
                break;
            default:
                break;
        }
    }
}
