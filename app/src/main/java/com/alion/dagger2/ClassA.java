package com.alion.dagger2;

import android.util.Log;

import javax.inject.Inject;

public class ClassA {
    @Inject
    public ClassA() {
        Log.d("alionlog", "ClassA: ");
    }
}
