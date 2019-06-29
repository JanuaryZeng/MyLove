package cn.edu.nuc.mylove.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assionhonty.lib.assninegridview.AssNineGridView;
import com.assionhonty.lib.assninegridview.AssNineGridViewAdapter;
import com.assionhonty.lib.assninegridview.AssNineGridViewClickAdapter;
import com.assionhonty.lib.assninegridview.ImageInfo;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nuc.Helper.GifSizeFilter;
import cn.edu.nuc.Helper.Glide4Engine;
import cn.edu.nuc.Helper.UpLoadPhotos;
import cn.edu.nuc.fragment.FriendFragment;
import cn.edu.nuc.mylove.R;

public class AddFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_order_note_text = null;
    private Button btnAddPhoto = null;
    private Button btnPutData = null;
    private Button btnPutDataFanhui = null;


    private AssNineGridView addPhoto = null;

    private List<ImageInfo> imageInfos = null;

    private String[] photos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        edt_order_note_text = findViewById(R.id.edt_order_note_text);
        btnAddPhoto = findViewById(R.id.btnAddPhoto);
        addPhoto = findViewById(R.id.addPhoto);
        btnPutData = findViewById(R.id.btnPutData);
        btnPutDataFanhui = findViewById(R.id.btnPutDataFanhui);

        //设置监听
        btnAddPhoto.setOnClickListener(this);
        btnPutData.setOnClickListener(this);
        btnPutDataFanhui.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAddPhoto:
                Matisse.from(AddFriendActivity.this)
                        .choose(MimeType.ofAll(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(
                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                        .maxSelectable(9)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(
                                getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                        .imageEngine(new Glide4Engine())    // for glide-V4
                        .setOnSelectedListener(new OnSelectedListener() {
                            @Override
                            public void onSelected(
                                    @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("onSelected", "onSelected: pathList=" + pathList);

                            }
                        })
                        .originalEnable(true)
                        .maxOriginalSize(10)
//                                    .autoHideToolbarOnSingleTap(true)
                        .setOnCheckedListener(new OnCheckedListener() {
                            @Override
                            public void onCheck(boolean isChecked) {
                                // DO SOMETHING IMMEDIATELY HERE
                                Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                            }
                        })
                        .forResult(23);
                break;

            case R.id.btnPutData:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String str = edt_order_note_text.getText().toString();
                        if(str==null){}
                        else if(photos.length == 0){}
                        else{
                            UpLoadPhotos.init(photos, str);
                        }
                    }
                }).start();
                break;
            case R.id.btnPutDataFanhui:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 23 && resultCode == RESULT_OK) {
            String str = String.valueOf(Matisse.obtainPathResult(data));
            photos = str.split(", ");
            photos[0] = photos[0].substring(1);
            photos[photos.length-1] = photos[photos.length-1].substring(0,photos[photos.length-1].length()-1);

//            Log.d("Matisse", "-1-----" + str);
//
//            Log.d("Matisse", "0----" + photos[0]);
//            Log.d("Matisse", "1----" + photos[1]);
//            Log.d("Matisse", "-1----" + photos[photos.length-1]);

            imageInfos = getImageInfo(photos);
//            Log.d("Matisse", "123----" + imageInfos.size());

            addPhoto.setAdapter(new AssNineGridViewClickAdapter(AddFriendActivity.this,imageInfos));
        }
    }

    private List<ImageInfo> getImageInfo(String[] sets){
        List<ImageInfo> images = new ArrayList<ImageInfo>();
        for(String url : sets){
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setBigImageUrl(url);
            imageInfo.setThumbnailUrl(url);
            images.add(imageInfo);
        }
        return images;
    }

}
