package com.alion.recycleview;

import android.annotation.TargetApi;
import android.database.ContentObserver;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import com.alion.myapplication.R;

import java.util.ArrayList;
import java.util.List;

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

        mRecycle = findViewById(R.id.recycleview);
        initData();
        mRecycle.setLayoutManager(new GridLayoutManager(this,9,OrientationHelper.HORIZONTAL,true));
        mRecycle.setAdapter(new NormalAdapter(mData));
        mRecycle.addItemDecoration(new MDGridRvDividerDecoration(this));

    }

    private void initData() {
        mData = new ArrayList<String>();
        for(int i=100;i<200;i++){
            mData.add("第"+i+"章");
        }
    }
}
