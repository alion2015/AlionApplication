package com.alion.provider;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alion.myapplication.R;
import com.alion.recycleview.NormalAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView demo:四大组件
 * Layout Manager(必选)
 * Adapter(必选)
 * Item Decoration(可选，默认为空)
 * Item Animator(可选，默认为DefaultItemAnimator)
 */
public class LauncherPReader extends AppCompatActivity {

    RecyclerView mRecycle;
    List<LauncherData> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecycle);

        mRecycle = (RecyclerView)findViewById(R.id.recycleview);
        initData();
        mRecycle.setLayoutManager(/*new LinearLayoutManager(this)*/new GridLayoutManager(this,3,OrientationHelper.HORIZONTAL,true));
        mRecycle.setAdapter(new NormalAdapter(mData));
        //mRecycle.addItemDecoration(new MDGridRvDividerDecoration(this));

    }

    private void initData() {
        mData = new ArrayList<LauncherData>();
        Uri uri = Uri.parse("content://com.android.launcherWT.settings/favorites");
        Cursor query = getContentResolver().query(uri,new String[]{"title","intent","icon"},null,null,null);
        if (query!=null &&query.getCount()>0) {
            while (query.moveToNext()) {
                String title = query.getString(0);
                String intent = query.getString(1);
                if(null==title || null==intent)continue;
                byte[] data =  query.getBlob(2);
                Bitmap icon = BitmapFactory.decodeByteArray(data, 0, data.length);
                mData.add(new LauncherData(title,intent,icon));
                Log.d("alionlog", "initData: "+title);
            }
        }
    }
}
