package cn.edu.nuc.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.nuc.DataBase.FirstTimeNote;
import cn.edu.nuc.mylove.R;

public class FirstTimeAdapter extends RecyclerView.Adapter<FirstTimeAdapter.ViewHolder> {

    private List<FirstTimeNote> firstTimeNotes = null;

    /**
     * 这个方法主要生成为每个Item inflater出一个View，但是该方法返回的是一个ViewHolder。
     * 该方法把View直接封装在ViewHolder中，然后我们面向的是ViewHolder这个实例，
     * 当然这个ViewHolder需要我们自己去编写。
     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //必须这样写
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_time_note, parent, false);

        return new ViewHolder(view);
    }

    /**
     * 这个方法主要用于适配渲染数据到View中。方法提供给你了一viewHolder而不是原来的convertView。
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirstTimeNote firstTimeNote = firstTimeNotes.get(position);
        holder.ivFirstTimeNote.setImageBitmap(firstTimeNote.getBitmap());
        holder.tvFirstTimeNote.setText(firstTimeNote.getDate());
    }



    @Override
    public int getItemCount() {
        return firstTimeNotes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivFirstTimeNote = null;

        public TextView tvFirstTimeNote = null;

        public View firstview = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFirstTimeNote = itemView.findViewById(R.id.ivFirstTimeNote);

            tvFirstTimeNote = itemView.findViewById(R.id.tvFirstTimeNote);

            firstview = itemView;

        }
    }

}
