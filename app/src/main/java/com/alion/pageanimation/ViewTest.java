package com.alion.pageanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alion.annotation.TestAinit;
import com.alion.annotation.UserFactory;
import com.alion.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ViewTest extends AppCompatActivity implements FoldView.OnBitmapChanged {
    FoldView myView;
    List<Bitmap> mBitmaps;
    ActionBar mActionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview_test);
        mBitmaps = new ArrayList<>();
        myView = (FoldView)findViewById(R.id.testmyview);
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid1));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid2));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid3));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid4));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid5));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid6));
        mBitmaps.add(BitmapFactory.decodeResource(getResources(),R.mipmap.mid7));
        myView.setBitmaps(mBitmaps,this);
        mActionBar = getSupportActionBar();
    }

    @Override
    public void onChanged(int color) {
        mActionBar.setBackgroundDrawable(new ColorDrawable(color));
    }
}
