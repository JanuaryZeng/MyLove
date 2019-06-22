package cn.edu.nuc.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.Adapter.PencilAdapter;
import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.mylove.R;

public class PencilFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pencil, container, false);
        initView(view);
        return view;
    }
//PencilNote(int itemType, Bitmap icon, String name, String text,String type,int typeNum)
//PencilNote(int itemType, Bitmap icon, String name, String text, String money, int year, int month, int day)
    private void initView(View view){

        List<PencilNote> list = new ArrayList<PencilNote>();

        list.add(new PencilNote(1,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_add)
                ,"铁锅门面","明天之前",200,2019,6,13));
        list.add(new PencilNote(1,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_note)
                ,"大腕儿款面","今天",10,2019,6,12));
        list.add(new PencilNote(3,"吃饭",2));
        list.add(new PencilNote(2,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.alarm_color)
                ,"大腕儿款面","今天"));
        list.add(new PencilNote(2,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.cancel)
                ,"铁锅门面","明天"));
        list.add(new PencilNote(3,"学习",3));
        list.add(new PencilNote(2,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.icon_add_touch)
                ,"政治","今天"));
        list.add(new PencilNote(2,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)
                ,"英语","明天"));
        list.add(new PencilNote(2,
                BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)
                ,"数学","今天"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView pencilRecycler = view.findViewById(R.id.pencilRecyclerView);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pencilRecycler.setLayoutManager(layoutManager);

        PencilAdapter pencilAdapter = new PencilAdapter(list);

        pencilRecycler.setAdapter(pencilAdapter);

    }

}
