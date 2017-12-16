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

public class FiveFragment extends BaseFragment implements MainActivity.Backfragment{


    private String localname=null;
    private Button bt;

    public static Myinterface.changerThirdFragment  changerThirdFragment;

    //因为这个调用了别人家的方法，所以在别人家的类里面要初始化一下！！
    public static void getThirdFragment(Myinterface.changerThirdFragment changerThirdFragment){
        FiveFragment.changerThirdFragment=changerThirdFragment;
    }


    @Override
    public int getlayout() {
        return R.layout.fragment_five;
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

        //这个是MainActivity中回退按钮监听事件的接口
        //首先如果这个回退接口不注销，每次走初始化方法的时候，每个注册了mainactivity里面接口的类都会初始化，
        //然后下次点击返回的时候，（自己实现了两种返回，一种是按钮返回，还有一种是点击物理回退键返回）
        //点了按钮返回之后，物理返回的接口也被实例化了，如果不及时注销，那么下次按过按钮返回，在另一类里面想按物理按钮的返回
        //就会造成上一个类的物理按钮的回退监听实例没有被释放，然后导致fragment走的是上一个物理返回监听里面里面的方法
        //如果用onhiddenchanger来注销物理按钮返回的监听，然后还会带一个问题，因为开始初始化的时候fragment是不会判断
        //当前fragment是不是隐藏了，所以是要hide一次之后才走onhiddenchange这个方法，所以初始化的方法里面还是要先初始化一遍
        //物理按钮的返回监听，然后不用担心这个不能被注销，因为只要点击按钮返回或者是物理按钮的返回，这个物理按钮的返回就会被注销掉了
        ((MainActivity)getActivity()).setBackFragment(this);

        //这个方法能注释掉是因为thirdFragment方法没有调用Fourfragment里面的方法
       //ThirdFragment.getFourFragment(this);
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
