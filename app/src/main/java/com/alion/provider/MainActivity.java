package com.alion.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        Cursor query = sqLiteDatabase.query("info",null,null,null,null,null,null);
        if (query!=null &&query.getCount()>0){
            while (query.moveToNext()){
                String name = query.getString(1);
                String age = query.getString(2);
                Log.d("alionlog", "onCreate: name----"+name+"age==="+age);
            }
        }*/

        Uri uri = Uri.parse("content://com.alion.provider/querry");
        Cursor query = getContentResolver().query(uri,new String[]{"name","age"},null,null,null);
        if (query!=null &&query.getCount()>0) {
            while (query.moveToNext()) {
                String name = query.getString(1);
                String age = query.getString(2);
                Log.d("alionlog", "getContentResolver: name----" + name + "age===" + age);
            }
        }
    }
}
