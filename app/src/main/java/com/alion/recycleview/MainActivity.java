package com.alion.recycleview;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alion.delaypool.DelayPool;
import com.alion.delaypool.DelayRun;
import com.alion.myapplication.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RecycleView demo:四大组件
 * Layout Manager(必选)
 * Adapter(必选)
 * Item Decoration(可选，默认为空)
 * Item Animator(可选，默认为DefaultItemAnimator)
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycle;
    List<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecycle);

        /*mRecycle = (RecyclerView)findViewById(R.id.recycleview);
        initData();
        mRecycle.setLayoutManager(new GridLayoutManager(this,9,OrientationHelper.HORIZONTAL,true));
        //mRecycle.setAdapter(new NormalAdapter(mData));
        mRecycle.addItemDecoration(new MDGridRvDividerDecoration(this));
        mRecycle.setAdapter(new CommonAdapter<String>(this,R.layout.recycletest,mData) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.title,s)
                        .setOnClickListener(R.id.title, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("alionlog", "onClick: ");
                            }
                        })
                        .setImageResource(R.id.imageicon,R.drawable.ic_lock_redbag);
            }
        });*/
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelayPool.needPause = DelayPool.needPause?false:true;
                //需要等待一个最大随机等待时间再下一步操作
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DelayPool.unInit();
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                activityManager.killBackgroundProcesses("widget.wtcl.demo");
            }
        });


    }

    private void initData() {
        /*mData = new ArrayList<String>();
        for(int i=100;i<200;i++){
            mData.add("第"+i+"章");
        }*/

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("alionlog", "run: runnable");
            }
        };
        Random r = new Random();
        for(int i=0;i<100;i++){
            DelayRun dy = new DelayRun(i,r.nextInt(4000)+1000,runnable);
            DelayPool.execute(dy);
        }
    }
}
