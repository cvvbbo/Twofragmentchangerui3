package com.testdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiong on 2017/12/14.
 */

public class OtherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_other, container, false);
        rootView.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //从栈中将当前fragment推出
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                //getChildFragmentManager().popBackStack();
            }
        });
        return rootView;
    }
}
