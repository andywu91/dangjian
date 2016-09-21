
package com.example.andy.dangjian;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andy.dangjian.fragment.PageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FragmentAdapter fragmentAdapter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        context = this;

        setSupportActionBar(toolbar);

        init();

    }

    private void init() {
        initViewPager();
        initTabLayout();
    }

    private void initViewPager() {

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), this);

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
        private int[] imageResId = {R.drawable.login_avatar,
                R.drawable.login_avatar,
                R.drawable.login_avatar, R.drawable.login_avatar, R.drawable.login_avatar};
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
