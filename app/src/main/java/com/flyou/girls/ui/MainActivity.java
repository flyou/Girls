package com.flyou.girls.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.flyou.girls.R;
import com.flyou.girls.ui.mainImageList.widget.fragment.MRXTFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.NewFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.SWMTFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.ShaoNvFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.WALLPAPERFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.WMXZFragment;
import com.flyou.girls.ui.mainImageList.widget.fragment.XinGanFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Fragment mCurrentFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_new);
        changeFragment(new NewFragment());
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        switch (id) {
            case R.id.nav_new:
                changeFragment(new NewFragment());

                break;
            case R.id.nav_xingan:
                changeFragment(new XinGanFragment());
                break;
            case R.id.nav_shaonv:
                changeFragment(new ShaoNvFragment());
                break;
            case R.id.nav_mr:
                changeFragment(new MRXTFragment());
                break;
            case R.id.nav_sw:
                changeFragment(new SWMTFragment());
                break;
            case R.id.nav_xz:
                changeFragment(new WMXZFragment());
                break;
            case R.id.nav_wallpaper:
                changeFragment(new WALLPAPERFragment());
                break;
            case R.id.nav_share:
                new MaterialDialog.Builder(MainActivity.this)
                        .title("关于应用")
                        .icon(getResources().getDrawable(R.drawable.ic_about))
                        .positiveText("确定")
                        .content(R.string.about_app)
                        .show();
                break;
            case R.id.nav_personal:
                new MaterialDialog.Builder(MainActivity.this)
                        .title("关于作者")
                        .icon(getResources().getDrawable(R.drawable.ic_personal))
                        .positiveText("确定")
                        .content(R.string.about_account)
                        .show();

                break;
        }
        mToolbar.setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // change fragment
    private void changeFragment(Fragment fragment) {
        if (mCurrentFragment == null || !fragment.getClass().getName().equals(mCurrentFragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragent_content, fragment).commit();
            mCurrentFragment = fragment;
        }


    }
}
