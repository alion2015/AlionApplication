package com.alion.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUERYSUCESS = 0;
    static {
        sURIMatcher.addURI("com.alion.provider","querry",QUERYSUCESS);
    }

    private MyOpenHelper myOpenHelper;

    @Override
    public boolean onCreate() {
        myOpenHelper = new MyOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
        int code = sURIMatcher.match(uri);
        if(code==QUERYSUCESS){
            SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
            Cursor query = sqLiteDatabase.query("info",null,null,null,null,null,null);
            getContext().getContentResolver().notifyChange(uri,null);
            return query;
        }
        else {
            throw new IllegalArgumentException("uri 路径不匹配");
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri,  ContentValues values) {
        return null;
    }

    @Override
    public int delete( Uri uri, String selection,String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
