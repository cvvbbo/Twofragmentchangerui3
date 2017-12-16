package com.fragment2;

import android.content.ServiceConnection;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fragment2.bean.clickbean;
import com.fragment2.childfragment.FiveFragment;
import com.fragment2.childfragment.FourFragment;
import com.fragment2.childfragment.SevenFragment;
import com.fragment2.childfragment.SixFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by xiong on 2017/12/12.
 */

public class ThirdFragment extends BaseFragment implements Myinterface.changerThirdFragment,View.OnClickListener{

    /**
     *
     * https://stackoverflow.com/questions/13757875/getchildfragmentmanager-on-programmatically-dynamically-added-fragments
     * 再下一个fragment中改变上一个fragment的ui。只能把上一个fragment作为容器，
     *
     *
     */
    private Button bt;
    private LinearLayout ly;
    private FrameLayout fl;
    private  int fragmentnum=-1;

    public static Myinterface.changerFourfragment changerFourfragment;
    //public FragmentManager childFragmentManager;
    private Button bt1;
    private Button bt2;
    private Button bt3;

    ArrayList<BaseFragment> childfragment=new ArrayList<>();
    private FragmentManager childFragmentManager;
    private FragmentTransaction beginTransaction;



    @Override
    public int getlayout() {
        return R.layout.fragment_third;
    }


    @Override
    public void initview() {
        initchildfragment();

        //这里的这个不能注释掉，因为这个的实例是用来调用ThirdFragment的方法的12.14
        //之前fragment和Activity之间交互的例子是强制类型转化12.15
        FourFragment.getThirdFragment(this);
        //第三页的其他按钮都是抄thirdfragment和fourfragment的,因为是复制粘贴的12.14
        FiveFragment.getThirdFragment(this);
        SixFragment.getThirdFragment(this);
        SevenFragment.getThirdFragment(this);


        bt = (Button) view.findViewById(R.id.bt);
        ly = (LinearLayout) view.findViewById(R.id.ly);
        fl = (FrameLayout) view.findViewById(R.id.fl);
        bt1 = (Button) view.findViewById(R.id.bt1);
        bt2 = (Button) view.findViewById(R.id.bt2);
        bt3 = (Button) view.findViewById(R.id.bt3);

        bt.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);



//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transation();
//
//            }
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt:
                switchchildfragment(0);
                break;
            case R.id.bt1:
                switchchildfragment(1);
                break;
            case R.id.bt2:
                switchchildfragment(2);
                break;
            case R.id.bt3:
                switchchildfragment(3);
                break;
        }

    }



    /***
     *
     * 这里用add和show方法但是没有什么意义，因为加了addTobackStack方法之后之后，再弹栈之后（观察生命周期，直接把弹栈的fragment的生命周期销毁了）12.14
     *
     * 如果想点进去再保存上一次退出来的位置，则需要保存状态，这个具体的方法saveinstance里面找找（如果是使用弹栈的方式的话） 12.14
     *
     * ！！！！12.14结尾，既然弹栈是消除fragment，那么就不弹栈了，直接返回的时候隐藏fragment？？？！！！（很精髓！！！）
     *
     * @param j
     */
//    public void switchchildfragment(int j){
//        ly.setVisibility(View.GONE);
//        //这个是把集合放到外面来12.14
//        BaseFragment baseFragment = childfragment.get(j);
//        childFragmentManager = getChildFragmentManager();
//        beginTransaction = childFragmentManager.beginTransaction();
//
//        for (int i=0;i<childfragment.size();i++){
//            if (i==j){
//                if (baseFragment.isAdded()){
//                    beginTransaction.show(baseFragment);
//                }else {
//                    beginTransaction.add(R.id.fl,baseFragment).addToBackStack(null);
//                }
//
//            }else {
//                if (baseFragment.isAdded()){
//                    beginTransaction.hide(baseFragment);
//                }
//            }
//        }
//        beginTransaction.commit();
//
//    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(clickbean clickbean){
        String getfragmentname = clickbean.getGetfragmentname();
        if (getfragmentname.equals("FourFragment")){
            fragmentnum=0;
        }else if (getfragmentname.equals("FiveFragment")){
            fragmentnum=1;

        }else if (getfragmentname.equals("SixFragment")){
            fragmentnum=2;

        }else if (getfragmentname.equals("SevenFragment")){
            fragmentnum=3;

        }


    }

    public void switchchildfragment(int j){
        //ly.setVisibility(View.GONE);
        //这个是把集合放到外面来12.14
        BaseFragment baseFragment = childfragment.get(j);
        childFragmentManager = getChildFragmentManager();
        beginTransaction = childFragmentManager.beginTransaction();

        for (int i=0;i<childfragment.size();i++){
            if (i==j&&fragmentnum==-1) {
                ly.setVisibility(View.GONE);
                if (baseFragment.isAdded()) {
                    beginTransaction.show(baseFragment);
                } else {
                    //fragment在弹栈的时候会走ondestory方法，目前（2017.12.15），从表面上看是不太好保存fragment的状态
                    //弹栈也可以，只是现在还没有研究弹栈之后保存fragment状态的方法12.15
                    //beginTransaction.add(R.id.fl, baseFragment).addToBackStack(null); //12.15
                    beginTransaction.add(R.id.fl, baseFragment);
                }
            }

            //括号里面的的条件首先的一条就是i==j，然后才是判断他按了返回键的判断
             if (i==j&&fragmentnum!=-1){
                    ly.setVisibility(View.VISIBLE);
                     beginTransaction.hide(baseFragment);
                 //重置状态能够再次返回，不然button就点击不了了
                 fragmentnum=-1;

            }

//            }else {
//                if (baseFragment.isAdded()){
//                    beginTransaction.hide(baseFragment);
//                }
//            }
        }
        beginTransaction.commit();

    }



    //子类的show和hide方法没有意义。。（假设在fragment中设置一个标识，当这个标识触发的时候，才会隐藏fragment）
    //先试试能不能对已经提交的事务进行两次操作 12.15
//    public void showandhidefragment(){
//
//
//    }

    public void initchildfragment(){
        childfragment.add(new FourFragment());
        childfragment.add(new FiveFragment());
        childfragment.add(new SixFragment());
        childfragment.add(new SevenFragment());
    }


    @Override
    public void ThirdFragmentCallback() {
        /*
        *
        * 下面这个可以提取一下方法,如果后面fragment多了的话，全是要这样写，有点小麻烦
        *
        * */

        if (fragmentnum==0){
            switchchildfragment(fragmentnum);
        }else if (fragmentnum==1){
            switchchildfragment(fragmentnum);
        }else if (fragmentnum==2){
            switchchildfragment(fragmentnum);
        }else if (fragmentnum==3){
            switchchildfragment(fragmentnum);
        }
    }


}
