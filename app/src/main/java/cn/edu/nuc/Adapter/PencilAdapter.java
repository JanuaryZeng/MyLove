package cn.edu.nuc.Adapter;

import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import cn.edu.nuc.DataBase.PencilNote;
import cn.edu.nuc.mylove.R;

public class PencilAdapter extends BaseMultiItemQuickAdapter<PencilNote,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PencilAdapter(List<PencilNote> data) {
        super(data);
        addItemType(PencilNote.UP, R.layout.pencil_note_up);
        addItemType(PencilNote.Down,R.layout.pencil_note_down);
        addItemType(PencilNote.TYPE,R.layout.pencil_note_type);
    }

    @Override
    protected void convert(BaseViewHolder helper, PencilNote item) {
        switch(helper.getItemViewType()){
            case PencilNote.UP:
                helper.setImageBitmap(R.id.imPencilUpIocn,item.getIcon())
                        .setImageBitmap(R.id.imPencilUpIocn1,item.getIcon())
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
                        .setText(R.id.edPencilUpMoney,String.valueOf(item.getMoney()))
                        .setOnClickListener(R.id.tvPencilDui, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "yes", Toast.LENGTH_SHORT).show();
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
                helper.setImageBitmap(R.id.imPencilDownIcon,item.getIcon())
                        .setText(R.id.tvPencilDownName,item.getName())
                        .setText(R.id.tvPencilDownText,item.getText());
                break;
            case PencilNote.TYPE:
                helper.setText(R.id.tvPencilType,item.getType())
                        .setText(R.id.tvPencilTypeNum,String.valueOf(item.getTypeNum()));
                break;
            default:
                break;
        }
    }


}
