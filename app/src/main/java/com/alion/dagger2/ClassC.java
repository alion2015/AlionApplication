package com.alion.dagger2;

import android.util.Log;

import javax.inject.Inject;

public class ClassC {
    @Inject
    public ClassC() {
        Log.d("alionlog", "ClassC: ");
    }
}
