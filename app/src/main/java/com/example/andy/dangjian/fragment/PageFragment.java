package com.example.andy.dangjian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andy.dangjian.ImproveUserDataActivity;
import com.example.andy.dangjian.LoginActivity;
import com.example.andy.dangjian.R;
import com.example.andy.dangjian.RegisterActivity;

/**
 * Created by Andy on 2016/9/21.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        if(mPage == 5){
            view = inflater.inflate(R.layout.personal_center_fragment,container,false);
        }else if(mPage == 1){
            view = inflater.inflate(R.layout.home_page_fragment,container,false);
        }else {
            view = inflater.inflate(R.layout.fragment_page, container, false);

        }

        return view;
    }
}
