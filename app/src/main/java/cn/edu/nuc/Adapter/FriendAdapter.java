package cn.edu.nuc.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assionhonty.lib.assninegridview.AssNineGridView;
import com.assionhonty.lib.assninegridview.AssNineGridViewAdapter;
import com.assionhonty.lib.assninegridview.AssNineGridViewClickAdapter;
import com.assionhonty.lib.assninegridview.ImageInfo;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.DataBase.FriendNote;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.fragment.FriendFragment;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    private List<FriendNote> friendNotes = null;

    public FriendAdapter(List<FriendNote> friendNotes) {
        this.friendNotes = friendNotes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_note, parent, false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final FriendNote friendNote = friendNotes.get(position);
        List<ImageInfo> imageInfos = getImageInfos(position);
        holder.angv.setAdapter(new AssNineGridViewClickAdapter(holder.itemView.getContext(),imageInfos));
        Log.e("adwzx","**********-------------------//////"+friendNote.getIcon());
        Glide.with(holder.image2.getContext()).load(friendNote.getIcon()).into( holder.image2);
        holder.tvFriendName.setText(friendNote.getName());
        holder.tvFriendText.setText(friendNote.getText());
        holder.tvFriendTime.setText(friendNote.getTime());
        holder.rlDeleFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = String.valueOf(friendNotes.get(position).getId());
                final String name = String.valueOf(friendNotes.get(position).getName());
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
                                                FriendFragment.setReyeler();
                                                break;
                                            case 20:
                                                break;
                                        }
                                        super.handleMessage(msg);
                                    }
                                };
                                if(name.equals(IDHelper.getMyName())){
                                AsyncHttpClient client = new AsyncHttpClient();
                                RequestParams params = new RequestParams();
                                params.put("method", "_DELETE");
                                params.put("table","friendtable");
//                                Log.e("xujiandiao", "-----------**********---------"+id);

                                params.put("friendid",id);
                                client.post("http://"+ IDHelper.IP+":8000/android_user/", params, new DjangoListener(handler, 2, 20));
                                }else if(!name.equals(IDHelper.getMyName())){
                                    Toast.makeText(holder.tvFriendName.getContext(), "只能删除自己的记录！！", Toast.LENGTH_SHORT).show();
                                }
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

    }

    @Override
    public int getItemCount() {
        return friendNotes.size();
    }

    private List<ImageInfo> getImageInfos(int position) {
        List<ImageInfo> imageInfos = new ArrayList<>();
        List<String> images = friendNotes.get(position).getPhotoUrl();
        if(images != null){
            for (String url : images) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(url);
                imageInfo.setThumbnailUrl(url);
                imageInfos.add(imageInfo);
            }
        }
        return imageInfos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image2 = null;
        public TextView tvFriendName = null;
        public TextView tvFriendText = null;
        public TextView tvFriendTime = null;
        public AssNineGridView angv = null;
        public View itemView = null;
        public RelativeLayout rlDeleFri = null;



        MyViewHolder(View itemView) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image2);
            tvFriendName = itemView.findViewById(R.id.tvFriendName);
            tvFriendText = itemView.findViewById(R.id.tvFriendText);
            tvFriendTime = itemView.findViewById(R.id.tvFriendTime);
            rlDeleFri = itemView.findViewById(R.id.rlDeleFri);
            angv = itemView.findViewById(R.id.angv);
            this.itemView = itemView;
        }
    }

}


