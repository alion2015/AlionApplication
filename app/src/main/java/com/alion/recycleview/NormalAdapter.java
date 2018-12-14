package com.alion.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alion.myapplication.R;

import java.util.List;

// ① 创建Adapter
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{
    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
        }
    }

    private List<String> mDatas;
    public NormalAdapter(List<String> data) {
        this.mDatas = data;
    }

    //③ 在Adapter中实现4个方法

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {                                    //可根据类别决定不同的布局
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycletest, parent, false);    //需要附加到parent上
        return new VH(v);                                                                            //生成ViewHolder
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {                                          //为传入的ViewHolder绑定数据
        holder.title.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {                              //为item本身添加点击事件(itemView是inflate生成的view本身)
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {                                                        //根据position返回不同的type用于生成不同的布局
        return super.getItemViewType(position);
    }
}

