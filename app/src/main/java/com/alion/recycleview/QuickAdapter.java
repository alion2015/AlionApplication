package com.alion.recycleview;


import android.support.annotation.Nullable;

import com.alion.myapplication.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;



public class QuickAdapter extends BaseQuickAdapter<QuickAdapter.BaseViewData, BaseViewHolder> {
    public static class BaseViewData{
            String title;

        public BaseViewData setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public QuickAdapter(int layoutResId, @Nullable List<BaseViewData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder title, BaseViewData item) {
        title.setText(R.id.title,item.title)
                .addOnClickListener(R.id.title)
                .setImageResource(R.id.imageicon,R.drawable.ic_lock_redbag);
    }
}
