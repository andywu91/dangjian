package com.example.andy.dangjian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andy.dangjian.fragment.PageFragment;
import com.example.andy.dangjian.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    HomeActivity.FragmentAdapter fragmentAdapter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        context = this;

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        TextView userPhoneNumberTextView = (TextView) view.findViewById(R.id.user_phone_number);

        Utils utils = Utils.INSTANCE;
        userPhoneNumberTextView.setText(utils.getUserPhoneNumber(this));


        init();

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
        getMenuInflater().inflate(R.menu.setting, menu);
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

        if (id == R.id.logout) {

            Utils utils = Utils.INSTANCE;
            utils.clearUserPhoneNumber(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        initViewPager();
        initTabLayout();
    }

    private void initViewPager() {

        fragmentAdapter = new HomeActivity.FragmentAdapter(getSupportFragmentManager(), this);

        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initTabLayout() {

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(fragmentAdapter.getTabView(i));
        }


        TextView textview = (TextView) tabLayout.getTabAt(0).getCustomView().findViewById(R.id.tab_textview);
        textview.setTextColor(ContextCompat.getColor(context, R.color.tab_select_color));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                TextView textview;

                textview = (TextView) tab.getCustomView().findViewById(R.id.tab_textview);

                textview.setTextColor(ContextCompat.getColor(context, R.color.tab_select_color));


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                TextView textview;

                textview = (TextView) tab.getCustomView().findViewById(R.id.tab_textview);

                textview.setTextColor(ContextCompat.getColor(context, R.color.black));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private class FragmentAdapter extends FragmentPagerAdapter {

        final int PAGE_COUNT = 5;
        private String tabTitles[] = new String[]{"首页", "新闻", "发现", "消息", "个人中心"};
        private int[] imageResId = {R.drawable.new_home_page,
                R.drawable.new_news,
                R.drawable.new_search, R.drawable.new_message, R.drawable.new_personal_center};
        private Context context;

        public FragmentAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //第一次的代码
            //return tabTitles[position];
            //第二次的代码
            /**
             Drawable image = context.getResources().getDrawable(imageResId[position]);
             image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
             SpannableString sb = new SpannableString(" " + tabTitles[position]);
             ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
             sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
             return sb;*/


            return null;
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tab_textview);
            tv.setText(tabTitles[position]);
            ImageView img = (ImageView) view.findViewById(R.id.tab_imageview);
            img.setImageResource(imageResId[position]);

            return view;
        }
    }

}
