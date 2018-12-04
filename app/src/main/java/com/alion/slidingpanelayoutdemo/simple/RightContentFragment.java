/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.alion.slidingpanelayoutdemo.simple;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alion.myapplication.R;


public class RightContentFragment extends Fragment {

    private TextView mContentTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rightcontent, container, false);
        mContentTV = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    public void setContent(String content) {
        mContentTV.setText(content);
    }
}
