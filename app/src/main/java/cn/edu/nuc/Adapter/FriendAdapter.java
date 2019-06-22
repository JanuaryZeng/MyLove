package cn.edu.nuc.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assionhonty.lib.assninegridview.AssNineGridView;
import com.assionhonty.lib.assninegridview.AssNineGridViewAdapter;
import com.assionhonty.lib.assninegridview.AssNineGridViewClickAdapter;
import com.assionhonty.lib.assninegridview.ImageInfo;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.DataBase.FriendNote;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        FriendNote friendNote = friendNotes.get(position);
        List<ImageInfo> imageInfos = getImageInfos(position);
        holder.angv.setAdapter(new AssNineGridViewClickAdapter(holder.itemView.getContext(),imageInfos));
        holder.image2.setImageBitmap(friendNote.getIcon());
        holder.tvFriendName.setText(friendNote.getName());
        holder.tvFriendText.setText(friendNote.getText());
        holder.tvFriendTime.setText(friendNote.getTime());
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



        MyViewHolder(View itemView) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image2);
            tvFriendName = itemView.findViewById(R.id.tvFriendName);
            tvFriendText = itemView.findViewById(R.id.tvFriendText);
            tvFriendTime = itemView.findViewById(R.id.tvFriendTime);
            angv = itemView.findViewById(R.id.angv);
            this.itemView = itemView;
        }
    }

}
