package com.fragment2.childfragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fragment2.BaseFragment;
import com.fragment2.MainActivity;
import com.fragment2.Myinterface;
import com.fragment2.R;
import com.fragment2.bean.clickbean;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xiong on 2017/12/12.
 */

public class SixFragment extends BaseFragment implements MainActivity.Backfragment{

    private String localname=null;
    private Button bt;

    public static Myinterface.changerThirdFragment  changerThirdFragment;

    //因为这个调用了别人家的方法，所以在别人家的类里面要初始化一下！！
    public static void getThirdFragment(Myinterface.changerThirdFragment changerThirdFragment){
        SixFragment.changerThirdFragment=changerThirdFragment;
    }


    @Override
    public int getlayout() {
        return R.layout.fragment_six;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            ((MainActivity)getActivity()).setBackFragment(null);
        }else {
            ((MainActivity)getActivity()).setBackFragment(this);
        }
    }


    @Override
    public void initview() {

        //这个方法能注释掉是因为thirdFragment方法没有调用Fourfragment里面的方法
       //ThirdFragment.getFourFragment(this);
        ((MainActivity)getActivity()).setBackFragment(this);

        localname=getClass().getSimpleName();
        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.怎么拿到上一个view的控件？
                Log.e("--","--执行了么");
                //getChildFragmentManager().popBackStack(0);

                EventBus.getDefault().post(new clickbean(localname));
                changerThirdFragment.ThirdFragmentCallback();

            }
        });

    }


    @Override
    public void onbackfragment() {
        EventBus.getDefault().post(new clickbean(localname));
        changerThirdFragment.ThirdFragmentCallback();

    }
}
