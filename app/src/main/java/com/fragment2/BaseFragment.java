package com.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiong on 2017/12/12.
 */

public abstract class BaseFragment extends Fragment {

    public View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(),getlayout(),null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    public abstract int getlayout();

    public abstract void initview();


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(this.getClass().getSimpleName(),"--onHiddenChanged--"+hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e(this.getClass().getSimpleName(),"--"+isVisibleToUser);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(this.getClass().getSimpleName(),"--onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(this.getClass().getSimpleName(),"--onResume");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getClass().getSimpleName(),"--onCreate");
    }


    //这个方法是先走ondestroy然后再走它
    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(this.getClass().getSimpleName(),"--onDetach");
    }




    @Override
    public void onPause() {
        super.onPause();
        //反射那块挺重要的，有木有什么办法直接拿到反射的类，然后直接调用子类的方法？ 12.12
        Log.e(this.getClass().getSimpleName(),"--onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(this.getClass().getSimpleName(),"--onStop");
    }

    @Override
    public void onDestroy() {
        Log.e(this.getClass().getSimpleName(),"--onDestroy");
        super.onDestroy();
    }





}
