package com.example.andy.dangjian;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.dangjian.utils.Utils;

public class ImproveUserDataActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = ImproveUserDataActivity.class.getSimpleName();

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
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_user_info);

        context = this;

        viewpage1 = (TextView) findViewById(R.id.viewpager_1);
        viewpage2 = (TextView) findViewById(R.id.viewpager_2);
        viewpage3 = (TextView) findViewById(R.id.viewpager_3);
        back = (ImageView) findViewById(R.id.back);

        viewpage1.setOnClickListener(this);
        viewpage2.setOnClickListener(this);
        viewpage3.setOnClickListener(this);
        back.setOnClickListener(this);


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
                switch (position) {
                    case 0:
                        viewpage1.setBackgroundResource(R.drawable.oval_bg);
                        viewpage1.setTextColor(ContextCompat.getColor(context, R.color.white));
                        viewpage2.setBackgroundResource(0);
                        viewpage2.setTextColor(ContextCompat.getColor(context, R.color.black));
                        viewpage3.setBackgroundResource(0);
                        viewpage3.setTextColor(ContextCompat.getColor(context, R.color.black));
                        break;
                    case 1:
                        viewpage1.setBackgroundResource(0);
                        viewpage1.setTextColor(ContextCompat.getColor(context, R.color.black));
                        viewpage2.setBackgroundResource(R.drawable.oval_bg);
                        viewpage2.setTextColor(ContextCompat.getColor(context, R.color.white));
                        viewpage3.setBackgroundResource(0);
                        viewpage3.setTextColor(ContextCompat.getColor(context, R.color.black));
                        break;
                    case 2:
                        viewpage1.setBackgroundResource(0);
                        viewpage1.setTextColor(ContextCompat.getColor(context, R.color.black));
                        viewpage2.setBackgroundResource(0);
                        viewpage2.setTextColor(ContextCompat.getColor(context, R.color.black));
                        viewpage3.setBackgroundResource(R.drawable.oval_bg);
                        viewpage3.setTextColor(ContextCompat.getColor(context, R.color.white));
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
        switch (v.getId()) {
            case R.id.viewpager_1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.viewpager_2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.viewpager_3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.back:
                finish();
                break;

        }
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

        private Utils utils;


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

            TextView messageTextView = (TextView) rootView.findViewById(R.id.message_textview);

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 2:
                    textView.setText("身份证信息");
                    imageView.setBackgroundResource(R.drawable.identitiy_card_icon);

                    utils = Utils.INSTANCE;

                    String userPidNumber = utils.getUserPidNumber(getContext());
                    Log.i(TAG, "onCreateView: "+userPidNumber);

                    if (utils.getUserPidNumber(getContext()).equals("")) {
                        messageTextView.setText("需要完成填写个人信息才能进入");
                    } else {
                        messageTextView.setText("身份证照片上传");
                    }

                    break;
                case 1:
                    textView.setText("个人信息");
                    imageView.setBackgroundResource(R.drawable.user_info_icon);
                    messageTextView.setText("个人信息填写");
                    break;
                case 3:
                    textView.setText("学历证明");
                    imageView.setBackgroundResource(R.drawable.user_photo_icon);

                    utils = Utils.INSTANCE;
                    if (utils.getUserPidNumber(getContext()).equals("")) {
                        messageTextView.setText("需要完成填写个人信息才能进入");
                    } else {
                        messageTextView.setText("学历证明照片上传");
                    }
                    break;
                default:
            }

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent;

                    switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                        case 2:

                            utils = Utils.INSTANCE;
                            if (utils.getUserPidNumber(getContext()).equals("")) {
                                Toast.makeText(getContext(), "请先完成提交个人信息", Toast.LENGTH_SHORT).show();
                            } else {

//                                intent = new Intent();
//                                intent.setType("image/*");
//                                intent.setAction(Intent.ACTION_GET_CONTENT);

                                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                getActivity().startActivityForResult(intent, 2);
                            }

                            break;
                        case 1:
                            intent = new Intent(getActivity(), UserInfoImproveActivity.class);
                            getActivity().startActivityForResult(intent, 1);
                            break;
                        case 3:

                            utils = Utils.INSTANCE;
                            if (utils.getUserPidNumber(getContext()).equals("")) {
                                Toast.makeText(getContext(), "请先完成提交个人信息", Toast.LENGTH_SHORT).show();
                            } else {

//                                intent = new Intent();
//                                intent.setType("image/*");
//                                intent.setAction(Intent.ACTION_GET_CONTENT);

                                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                                getActivity().startActivityForResult(intent, 3);
                            }

                            break;
                    }

                }
            });

            return rootView;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Intent intent = new Intent(this, CredentialUploadActivity.class);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            Utils utils = Utils.INSTANCE;

            TextView messageTextView = (TextView) mViewPager.getChildAt(1).findViewById(R.id.message_textview);

            String userPidNumber = utils.getUserPidNumber(this);

            if (userPidNumber.equals("")) {
                messageTextView.setText("需要完成填写个人信息才能进入");
            } else {
                messageTextView.setText("身份证照片上传");
            }

            TextView xlzmMessageTextView = (TextView) mViewPager.getChildAt(2).findViewById(R.id.message_textview);

            if (userPidNumber.equals("")) {
                xlzmMessageTextView.setText("需要完成填写个人信息才能进入");
            } else {
                xlzmMessageTextView.setText("学历证明照片上传");
            }


        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            intent.putExtra("Credential", "身份证信息");
            intent.putExtra("uri", data.getData());
            startActivity(intent);

        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            intent.putExtra("Credential", "学历证明");

            intent.putExtra("uri", data.getData());
            startActivity(intent);
        }


        super.onActivityResult(requestCode, resultCode, data);
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
