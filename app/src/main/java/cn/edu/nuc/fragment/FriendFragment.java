package cn.edu.nuc.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.Adapter.FirstTimeAdapter;
import cn.edu.nuc.DataBase.FirstTimeNote;
import cn.edu.nuc.mylove.R;
import cn.edu.nuc.mylove.activity.HomeActivity;

public class FriendFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_friend, container, false);

        InitView(view);

        return view;
    }

    private View InitView(View view){
        List<FirstTimeNote> firstTimeNotes = new ArrayList<FirstTimeNote>();

        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));
        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find1)));
        firstTimeNotes.add(new FirstTimeNote("123", BitmapFactory.decodeResource(this.getResources(), R.mipmap.find)));

        RecyclerView friendRecycler = (RecyclerView) view.findViewById(R.id.firstRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation((LinearLayout.HORIZONTAL));
        friendRecycler.setLayoutManager(linearLayoutManager);
        friendRecycler.setAdapter(new FirstTimeAdapter(firstTimeNotes));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
}
