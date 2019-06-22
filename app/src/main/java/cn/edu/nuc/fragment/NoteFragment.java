package cn.edu.nuc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.Adapter.MoneyAdapter;
import cn.edu.nuc.Adapter.PencilAdapter;
import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.mylove.R;

public class NoteFragment extends BaseFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_note, container, false);
            viewInit(view);
            return view;
        }

        private void viewInit(View view){
            List<MoneyNote> list = new ArrayList<MoneyNote>();
// MoneyNote(int itemType, String money, String time, String text
            list.add(new MoneyNote(2,"+10","2019-06-28","工作 10.0"));
            list.add(new MoneyNote(1,"-10","2019-06-28","餐饮 -10.0"));
            list.add(new MoneyNote(1,"-30","2019-06-28","租车 -30.0"));
            list.add(new MoneyNote(1,"-30","2019-06-28","停车 -30.0"));
            list.add(new MoneyNote(2,"+10","2019-06-28","打工 10.0"));
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            RecyclerView moneyRecycler = view.findViewById(R.id.moneyRecycler);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            moneyRecycler.setLayoutManager(layoutManager);
            MoneyAdapter moneyAdapter = new MoneyAdapter(list);
            moneyRecycler.setAdapter(moneyAdapter);



        }

}
