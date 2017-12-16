package com.fragment2;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by xiong on 2017/12/12.
 */

public class FristFragment extends BaseFragment {


    private Button bt;

    @Override
    public int getlayout() {
        return R.layout.fragment_first;
    }

    @Override
    public void initview() {
        bt = (Button) view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("--","--第一个");
            }
        });

    }


}
