package com.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     *
     * 本demo的目的是为了测试setUserVisibleHint这个方法。
     *
     *
     */

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.btlayout)
    LinearLayout btlayout;
    ArrayList<BaseFragment> baseFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initfragment();
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    public void switchfragment(int j){
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < baseFragments.size(); i++) {
            Fragment f = baseFragments.get(i);
            if (i == j) {
                if (f.isAdded()) {
                    //已经添加过，直接展示
                    beginTransaction.show(f);
                } else {
                    //并没有添加，则把fragment添加进来
                        beginTransaction.add(R.id.fl, f);
                        //beginTransaction.addToBackStack(null);
                    //下面这个类似于commitAllowingStateLoss()
                        //getSupportFragmentManager().executePendingTransactions();
                }
            } else {
                if (f.isAdded()) {
                    //当遍历的和当前的不相等的时候，把这些全部隐藏起来。
                    beginTransaction.hide(f);
                }
            }
        }
        //最后一定要记得提交
        beginTransaction.commit();
    }

    public void initfragment(){
        baseFragments.add(new FristFragment());
        baseFragments.add(new SecondFragment());
        baseFragments.add(new ThirdFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt1:
                switchfragment(0);
                break;
            case R.id.bt2:
                switchfragment(1);
                break;
            case R.id.bt3:
                switchfragment(2);
                break;
        }
    }



    public interface  Backfragment{
        void onbackfragment();
    }

    Backfragment backfragment;

    public void setBackFragment(Backfragment backfragment){
        this.backfragment=backfragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backfragment!=null){
                Log.e("--返回的接口-",backfragment+"");
                backfragment.onbackfragment();
                //backfragment=null;
                return false;
            }

        }
            return super.onKeyDown(keyCode, event);
    }
}
