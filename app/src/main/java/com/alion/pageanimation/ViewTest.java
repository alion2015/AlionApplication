package com.alion.pageanimation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wutong.ContentObserver.R;

import java.util.ArrayList;
import java.util.List;

public class ViewTest extends AppCompatActivity {
    FoldView myView;
    List<Bitmap> mBitmaps;
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
        myView.setBitmaps(mBitmaps);
    }
}
