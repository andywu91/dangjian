package com.example.andy.dangjian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andy.dangjian.ExaminationPlaceQueryActivity;
import com.example.andy.dangjian.ExpensePaymentActivity;
import com.example.andy.dangjian.ImproveUserDataActivity;
import com.example.andy.dangjian.LoginActivity;
import com.example.andy.dangjian.R;
import com.example.andy.dangjian.RegisterActivity;
import com.example.andy.dangjian.UserInfoQueryActivity;

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
        if(mPage == 1){
            view = inflater.inflate(R.layout.home_page_fragment,container,false);

            LayoutClickListener layoutClickListener = new LayoutClickListener();

            RelativeLayout registrationOnlineLayout = (RelativeLayout) view.findViewById(R.id.registration_online);
            registrationOnlineLayout.setOnClickListener(layoutClickListener);
            RelativeLayout studentInfoQueryLayout = (RelativeLayout) view.findViewById(R.id.student_info_query);
            studentInfoQueryLayout.setOnClickListener(layoutClickListener);
            RelativeLayout examinationPlaceQueryLayout = (RelativeLayout) view.findViewById(R.id.examination_place_query);
            examinationPlaceQueryLayout.setOnClickListener(layoutClickListener);
            RelativeLayout expensePaymentLayout = (RelativeLayout) view.findViewById(R.id.expense_payment);
            expensePaymentLayout.setOnClickListener(layoutClickListener);


        }else {
            view = inflater.inflate(R.layout.fragment_page, container, false);

        }

        return view;
    }

    private class LayoutClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            Intent intent;

            switch (v.getId()){
                case R.id.registration_online:
                    intent = new Intent(getActivity(), ImproveUserDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.student_info_query:
                    intent = new Intent(getActivity(), UserInfoQueryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.examination_place_query:
                    intent = new Intent(getActivity(), ExaminationPlaceQueryActivity.class);
                    startActivity(intent);
                    break;
                case R.id.expense_payment:
                    intent = new Intent(getActivity(), ExpensePaymentActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
