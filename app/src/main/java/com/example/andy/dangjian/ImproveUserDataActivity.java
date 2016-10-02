package com.example.andy.dangjian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImproveUserDataActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Context context;

    private TextView viewpage1;
    private TextView viewpage2;
    private TextView viewpage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_user_info);

        context = this;

        viewpage1 = (TextView) findViewById(R.id.viewpager_1);
        viewpage2 = (TextView) findViewById(R.id.viewpager_2);
        viewpage3 = (TextView) findViewById(R.id.viewpager_3);

        viewpage1.setOnClickListener(this);
        viewpage2.setOnClickListener(this);
        viewpage3.setOnClickListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        viewpage1.setBackgroundResource(R.drawable.oval_bg);
                        viewpage2.setBackgroundResource(0);
                        viewpage3.setBackgroundResource(0);
                        break;
                    case 1:
                        viewpage1.setBackgroundResource(0);
                        viewpage2.setBackgroundResource(R.drawable.oval_bg);
                        viewpage3.setBackgroundResource(0);
                        break;
                    case 2:
                        viewpage1.setBackgroundResource(0);
                        viewpage2.setBackgroundResource(0);
                        viewpage3.setBackgroundResource(R.drawable.oval_bg);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.viewpager_1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.viewpager_2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.viewpager_3:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_improve_user_info, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_improve_user_info, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.operatino_identity_textview);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.operation_identity_imageview);
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.rounded_layout);

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    textView.setText("身份证信息");
                    imageView.setBackgroundResource(R.drawable.identity_card_info);
                    break;
                case 2:
                    textView.setText("个人信息");
                    imageView.setBackgroundResource(R.drawable.user_info_improve);
                    break;
                case 3:
                    textView.setText("党员证信息");
                    imageView.setBackgroundResource(R.drawable.user_info_improve);
                    break;
                default:
            }

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent;

                    switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                        case 1:
                            intent = new Intent(getActivity(),CredentialUploadActivity.class);
                            intent.putExtra("Credential","身份证信息");
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(getActivity(),UserInfoImproveActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(getActivity(),CredentialUploadActivity.class);
                            intent.putExtra("Credential","党员证信息");
                            startActivity(intent);
                            break;
                    }

                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
