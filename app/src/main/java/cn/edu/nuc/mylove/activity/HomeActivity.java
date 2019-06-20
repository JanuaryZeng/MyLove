package cn.edu.nuc.mylove.activity;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import cn.edu.nuc.fragment.FriendFragment;
import cn.edu.nuc.fragment.HomeFragment;
import cn.edu.nuc.fragment.NoteFragment;
import cn.edu.nuc.fragment.PencilFragment;
import cn.edu.nuc.mylove.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private HomeFragment homeFragment = null;
    private FriendFragment friendFragment = null;
    private PencilFragment pencilFragment = null;
    private NoteFragment noteFragment = null;
    private Fragment mCommonFragmentOne;
    private Fragment mCurrent;

    private RelativeLayout home_layout;
    private RelativeLayout friend_layout;
    private RelativeLayout add_layout;
    private RelativeLayout pencil_layout;
    private RelativeLayout note_layout;

    private TextView tvHomeIcon;
    private TextView tvFriendIcon;
    private TextView tvAddIcon;
    private TextView tvPencilIcon;
    private TextView tvNoteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init(){
        myListener listener = new myListener();
        home_layout = findViewById(R.id.home_layout);
        home_layout.setOnClickListener(listener);
        friend_layout = findViewById(R.id.friend_layout);
        friend_layout.setOnClickListener(listener);
        add_layout =  findViewById(R.id.add_layout);
        add_layout.setOnClickListener(listener);
        pencil_layout = findViewById(R.id.pencil_layout);
        pencil_layout.setOnClickListener(listener);
        note_layout = findViewById(R.id.note_layout);
        note_layout.setOnClickListener(listener);

        tvHomeIcon = findViewById(R.id.tvHomeIcon);
        tvHomeIcon.setBackgroundResource(R.drawable.icon_home_touch);
        tvFriendIcon = findViewById(R.id.tvFriendIcon);
        tvAddIcon = findViewById(R.id.tvAddIcon);
        tvPencilIcon = findViewById(R.id.tvPencilIcon);
        tvNoteIcon = findViewById(R.id.tvNoteIcon);

        homeFragment = new HomeFragment();//新建Home的activity
        //添加默认要显示的Fragment
        fm = getSupportFragmentManager();   //获取管理fragment的管理对象
        FragmentTransaction fragmentTransaction = fm.beginTransaction();//开始事务处理
        fragmentTransaction.replace(R.id.content_layout, homeFragment);//等同于先remove在add,切换fragment
        fragmentTransaction.commit();//执行切换的动作
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    class myListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //处理切换Fragment逻辑
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            switch(v.getId()){
                case R.id.home_layout:
                    //设置点击UI会变
                    tvHomeIcon.setBackgroundResource(R.drawable.icon_home_touch);
                    tvFriendIcon.setBackgroundResource(R.drawable.icon_friend);
                    tvAddIcon.setBackgroundResource(R.drawable.icon_add);
                    tvPencilIcon.setBackgroundResource(R.drawable.icon_penci);
                    tvNoteIcon.setBackgroundResource(R.drawable.icon_note);

                    //隐藏其他fragment
                    hideFragment(friendFragment, fragmentTransaction);
                    hideFragment(pencilFragment, fragmentTransaction);
                    hideFragment(noteFragment, fragmentTransaction);
                    //将我们的HomeFragment显示出来,不为空则显示.为空创建显示
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                        fragmentTransaction.add(R.id.content_layout, homeFragment);
                    } else {
                        mCurrent = homeFragment;
                        fragmentTransaction.show(homeFragment);
                    }
                    break;
                case R.id.friend_layout:
                    tvHomeIcon.setBackgroundResource(R.drawable.icon_home);
                    tvFriendIcon.setBackgroundResource(R.drawable.icon_friend_touch);
                    tvAddIcon.setBackgroundResource(R.drawable.icon_add);
                    tvPencilIcon.setBackgroundResource(R.drawable.icon_penci);
                    tvNoteIcon.setBackgroundResource(R.drawable.icon_note);

                    hideFragment(homeFragment, fragmentTransaction);
                    hideFragment(pencilFragment, fragmentTransaction);
                    hideFragment(noteFragment, fragmentTransaction);
                    if (friendFragment == null) {
                        friendFragment = new FriendFragment();
                        fragmentTransaction.add(R.id.content_layout, friendFragment);
                    } else {
                        mCurrent = friendFragment;
                        fragmentTransaction.show(friendFragment);
                    }
                    break;
            }
            fragmentTransaction.commit();
        }
    }

}
