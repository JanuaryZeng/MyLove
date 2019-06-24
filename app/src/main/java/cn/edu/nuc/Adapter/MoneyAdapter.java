package cn.edu.nuc.Adapter;

import android.graphics.BitmapFactory;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.edu.nuc.DataBase.MoneyNote;
import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.mylove.R;

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
    protected void convert(BaseViewHolder helper, MoneyNote item) {
        switch(helper.getItemViewType()){
            case MoneyNote.LEFT:
                helper.setBackgroundRes(R.id.imMoneyLeft, item.getIcon())
                        .setText(R.id.tvMoneyLeftTime,item.getTime())
                        .setText(R.id.tvMoneyLeft,item.getMoney())
                        .setText(R.id.tvMoneyLeftText,item.getText());
                break;
            case MoneyNote.RIGHT:
                helper.setBackgroundRes(R.id.imMoneyRight,item.getIcon())
                        .setText(R.id.tvMoneyRightTime,item.getTime())
                        .setText(R.id.tvMoneyRight,item.getMoney())
                        .setText(R.id.tvMoneyRightText,item.getText());
                break;
            default:
                break;
        }
    }
}
