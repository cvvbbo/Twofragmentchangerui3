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

/**
 *
 * 不要得意忘形，写底层代码的才是牛掰的！！！！12.15
 *
 */
public class FourFragment extends BaseFragment implements MainActivity.Backfragment{

    /***
     *
     * 12.15再继续观察生命周期，有一点很奇怪就是，在隐藏之后，有些fragment有时候会走onresume和oncreate方法
     * 这个问题记得以后研究研究！！！！12.15
     *
     *  然后是第二部，怎么按物理的回退键，回退到上一个fragment？12.15
     *
     *  注意回退栈里面是会有为空的情况的！！
     *
     *
     *
     *  关于fragmnet中回退按钮的返回接口监听是要取消的，不然的话接口对象不能得到释放，然后别的类在按返回键就会造成
     *  上一个类的回退监听实例没有被释放，走的是上一个回退监听实例的！！！（自己经过测试发现，然后看到一篇博客启发得到的灵感）
     *
     *  https://www.cnblogs.com/lenkevin/p/7614671.html
     *
     */

    private String localname=null;
    private Button bt;

    public static Myinterface.changerThirdFragment  changerThirdFragment;

    //因为这个调用了别人家的方法，所以在别人家的类里面要初始化一下！！
    public static void getThirdFragment(Myinterface.changerThirdFragment changerThirdFragment){
        FourFragment.changerThirdFragment=changerThirdFragment;
    }


    @Override
    public int getlayout() {
        return R.layout.fragment_four;
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
        ((MainActivity)getActivity()).setBackFragment(this);

        Log.e("--当前的类名是--",getClass().getSimpleName());
        localname=getClass().getSimpleName();

        //这个方法能注释掉是因为thirdFragment方法没有调用Fourfragment里面的方法
       //ThirdFragment.getFourFragment(this);

        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.怎么拿到上一个view的控件？
                //getChildFragmentManager().popBackStack(0);

                Log.e("---",localname);
                //在点击事件里面获取不到getclass.getsimplename,所以从外面获取。
               EventBus.getDefault().post(new clickbean(localname));

                changerThirdFragment.ThirdFragmentCallback();


            }
        });

    }


    @Override
    public void onbackfragment() {
        Log.e("---","--接口"+localname);
        EventBus.getDefault().post(new clickbean(localname));
        changerThirdFragment.ThirdFragmentCallback();

    }
}
