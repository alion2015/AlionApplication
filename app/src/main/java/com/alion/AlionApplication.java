package com.alion;

import android.app.Application;

import com.alion.blockcanary.BlockCanary;
import com.alion.blockcanary.BlockCanaryContext;

public class AlionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlockCanary.install(this,new BlockCanaryContext()).start();
    }
}
