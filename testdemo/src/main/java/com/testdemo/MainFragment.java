package com.testdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiong on 2017/12/14.
 */

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.findViewById(R.id.btn_show_other).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        //用replace的好处是上一个页面不用替换背景也不用隐藏（就是不会发生视图重叠12.14）
                        //而add方法则会产生视图重叠！！（解决视图重叠的办法是设置背景以及把xml文件层的结构给gone掉即可12.14）
                        //下面原来是replace方法，自己换成add方法的 12.14
                        .add(R.id.container, new OtherFragment()).commit();
            }
        });
        return rootView;
    }
}
