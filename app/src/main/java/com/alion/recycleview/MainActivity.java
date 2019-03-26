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
import android.widget.Toast;

import com.alion.myapplication.R;
import com.chad.library.adapter.base.BaseQuickAdapter;

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
    List<QuickAdapter.BaseViewData> mData;
    QuickAdapter qa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecycle);

        mRecycle = (RecyclerView)findViewById(R.id.recycleview);
        initData();
        mRecycle.setLayoutManager(new GridLayoutManager(this,9,OrientationHelper.HORIZONTAL,true));
        //mRecycle.setAdapter(new NormalAdapter(mData));
        mRecycle.addItemDecoration(new MDGridRvDividerDecoration(this));
        qa = new QuickAdapter(R.layout.recycletest,mData);
        qa.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.title){
                    Toast.makeText(getApplicationContext(),"R.id.title",Toast.LENGTH_SHORT).show();
                }
            }
        });
        qa.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getApplicationContext(),""+position,Toast.LENGTH_SHORT).show();
            }
        });
        mRecycle.setAdapter(qa);
    }

    private void initData() {
        mData = new ArrayList<QuickAdapter.BaseViewData>();
        for(int i=100;i<200;i++){
            mData.add(new QuickAdapter.BaseViewData().setTitle("第"+i+"章"));
        }
    }
}
