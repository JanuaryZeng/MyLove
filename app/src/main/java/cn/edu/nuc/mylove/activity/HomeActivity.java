package cn.edu.nuc.mylove.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;


import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.utils.NavigationUtil;
import com.next.easynavigation.view.EasyNavigationBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.edu.nuc.Adapter.FirstTimeAdapter;
import cn.edu.nuc.DataBase.FirstTimeNote;
import cn.edu.nuc.Helper.Glide4Engine;
import cn.edu.nuc.Helper.IDHelper;
import cn.edu.nuc.Helper.GifSizeFilter;

import cn.edu.nuc.Helper.JSONTOOL;
import cn.edu.nuc.Helper.MoneyTypeTable;
import cn.edu.nuc.Helper.UpLoadPhotos;
import cn.edu.nuc.fragment.FriendFragment;
import cn.edu.nuc.fragment.HomeFragment;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.fragment.PencilFragment;
import cn.edu.nuc.myListener.DjangoListener;
import cn.edu.nuc.mylove.R;

import static cn.edu.nuc.Helper.IDHelper.date;
import static cn.edu.nuc.Helper.IDHelper.loverID;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量
    private List<Uri> mSelected;

    private EasyNavigationBar navigationBar;
    private String[] tabText = {"首页", "记录", "", "目标", "记账"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.icon_home, R.mipmap.icon_friend, R.mipmap.add_image, R.mipmap.icon_penci, R.mipmap.icon_note};
    //选中时icon
    private int[] selectIcon = {R.mipmap.icon_home_touch, R.mipmap.icon_friend_touch, R.mipmap.add_image, R.mipmap.icon_pencil_touch, R.mipmap.icon_note_touch};

    private List<Fragment> fragments = new ArrayList<>();


    //仿微博图片和文字集合
    private int[] menuIconItems = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};
    private String[] menuTextItems = {"动态", "first", "消费", "记账"};

    private LinearLayout menuLayout;
    private View cancelImageView;
    private Handler mHandler = new Handler();
    private TextView tvUserName = null;
    private ImageView imageView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        SharedPreferences sp = getSharedPreferences("ID", MODE_PRIVATE);
//        if(sp.contains("loverid")){
//            IDHelper.loverID = sp.getString("loverid",null);
//            IDHelper.setGender(sp.getString("gender",null));
//            IDHelper.init();
//            @SuppressLint("HandlerLeak") Handler handler = new Handler() {
//                public void handleMessage(Message msg) {
//                    switch (msg.what) {
//                        case 3:
//                            List<Map<String, String>> maps = JSONTOOL.analyze_some_json(msg.obj.toString());
//                            for(Map<String, String> littlemap : maps){
//
//                                String usergender = littlemap.get("usergender");
//                                String username = littlemap.get("username");
//                                String usericon = littlemap.get("usericon");
//                                String userborn = littlemap.get("userborn");
//
//                                if(usergender.equals("man")){
//                                    IDHelper.userIcon.put("man",usericon);
//                                    if(IDHelper.gender.equals("man")){
//                                        IDHelper.myName = username;
//                                        IDHelper.born = userborn;
//                                    }
//                                }else if(usergender.equals("woman")){
//                                    IDHelper.userIcon.put("woman",usericon);
//                                    if(IDHelper.gender.equals("woman")){
//                                        IDHelper.myName = username;
//                                        IDHelper. born = userborn;
//                                    }
//                                }
//                                tvUserName.setText(IDHelper.myName);
//                                Glide.with(HomeActivity.this).load(IDHelper.getMyIcon()).into((ImageView) findViewById(R.id.imageView));
//                            }
//                            break;
//                        case 30:
//                            break;
//                    }
//                    super.handleMessage(msg);
//                }
//            };
//            AsyncHttpClient client = new AsyncHttpClient();
//            RequestParams params2 = new RequestParams();
//            params2.put("table","usertable");
//            params2.put("method", "_GET");
//            params2.put("loverid", loverID);
//            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//            navigationView.setNavigationItemSelectedListener(this);
//            View headerView = navigationView.getHeaderView(0);
//
//            tvUserName = headerView.findViewById(R.id.tvUserName);
////            Log.e("jianbo", String.valueOf(tvUserName));
//            client.post("http://"+IDHelper.IP+":8000/android_user/", params2, new DjangoListener(handler, 3, 30));
//        }else{
//            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }

        init();

    }

    private void init(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        tvUserName = headerView.findViewById(R.id.tvUserName);
        imageView = headerView.findViewById(R.id.imageView);
        //加载头像
        Log.e("chuli","12346*******--"+IDHelper.getMyIcon());
        Glide.with(HomeActivity.this).load(IDHelper.getMyIcon()).into(imageView);
        //加载网名
        tvUserName.setText(IDHelper.getMyName());


        //加载数据

        MoneyTypeTable.init();

        //显示底部导航栏
        navigationBar = findViewById(R.id.navigationBar);

        fragments.add(new HomeFragment());
        fragments.add(new FriendFragment());
        fragments.add(new PencilFragment());
        fragments.add(new NoteFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .addLayoutRule(EasyNavigationBar.RULE_BOTTOM)
                .addLayoutBottom(100)
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {

                        if (position == 2) {
                            //跳转页面（全民K歌）   或者   弹出菜单（微博）
                            showMunu();
                        }
                        return false;
                    }
                })
                .mode(EasyNavigationBar.MODE_ADD)
                .anim(Anim.ZoomIn)
                .build();


        navigationBar.setAddViewLayout(createWeiboView());

//        //延时线程
//        TimerTask delayedThreadStartTask = new TimerTask() {
//            @Override
//            public void run() {
//
//                //captureCDRProcess();
//                //moved to TimerTask
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                }).start();
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(delayedThreadStartTask,   5000); //1 minute

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

            Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            loverID = null;
            SharedPreferences sp = getSharedPreferences("ID", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("loverid", null);
            editor.putString("password", null);
            editor.commit();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    /**
     * EazyNavigation
     */
    private View createWeiboView() {
        ViewGroup view = (ViewGroup) View.inflate(HomeActivity.this, R.layout.layout_add_view, null);
        menuLayout = view.findViewById(R.id.icon_group);
        cancelImageView = view.findViewById(R.id.cancel_iv);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAnimation();
            }
        });
        for (int i = 0; i < 4; i++) {
            View itemView = View.inflate(HomeActivity.this, R.layout.item_icon, null);
            ImageView menuImage = itemView.findViewById(R.id.menu_icon_iv);
            final TextView menuText = itemView.findViewById(R.id.menu_text_tv);

            menuImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = (String) menuText.getText();
                    switch(str){
                        case "动态":
                            Intent intent2 = new Intent(HomeActivity.this, AddFriendActivity.class);
                            startActivity(intent2);
                            break;
                        case "消费":
                            Intent intent = new Intent(HomeActivity.this,SpendActivity.class);
                            startActivity(intent);
                            break;
                        case "first":
                            break;
                        case "记账":
                            Intent intent1 = new Intent(HomeActivity.this,AddMoneyActivity.class);
                            startActivity(intent1);
                            break;
                    }
                }
            });

            menuImage.setImageResource(menuIconItems[i]);
            menuText.setText(menuTextItems[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            itemView.setLayoutParams(params);
            itemView.setVisibility(View.GONE);
            menuLayout.addView(itemView);
        }
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
//            Log.d("Matisse", "mSelected: " + mSelected);
        }
    }

    //
    private void showMunu() {
        startAnimation();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //＋ 旋转动画
                cancelImageView.animate().rotation(90).setDuration(400);
            }
        });
        //菜单项弹出动画
        for (int i = 0; i < menuLayout.getChildCount(); i++) {
            final View child = menuLayout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(500);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(500);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50 + 100);
        }
    }


    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = NavigationUtil.getScreenWidth(HomeActivity.this) / 2;
                        int y = (int) (NavigationUtil.getScreenHeith(HomeActivity.this) - NavigationUtil.dip2px(HomeActivity.this, 25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                                y, 0, navigationBar.getAddViewLayout().getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                navigationBar.getAddViewLayout().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelImageView.animate().rotation(0).setDuration(400);
            }
        });

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = NavigationUtil.getScreenWidth(this) / 2;
                int y = (NavigationUtil.getScreenHeith(this) - NavigationUtil.dip2px(this, 25));
                Animator animator = ViewAnimationUtils.createCircularReveal(navigationBar.getAddViewLayout(), x,
                        y, navigationBar.getAddViewLayout().getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        navigationBar.getAddViewLayout().setVisibility(View.GONE);
                        //dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }


    public EasyNavigationBar getNavigationBar() {
        return navigationBar;

    }
    /**
     * Eazy的类
     */
    private class KickBackAnimator implements TypeEvaluator<Float> {
        private final float s = 1.70158f;
        float mDuration = 0f;

        public void setDuration(float duration) {
            mDuration = duration;
        }

        public Float evaluate(float fraction, Float startValue, Float endValue) {
            float t = mDuration * fraction;
            float b = startValue.floatValue();
            float c = endValue.floatValue() - startValue.floatValue();
            float d = mDuration;
            float result = calculate(t, b, c, d);
            return result;
        }

        public Float calculate(float t, float b, float c, float d) {
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }
    }
}



