package com.alion.dagger2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

public class ClassB extends Activity {
    @Inject
    ClassA a;
    @Inject
    ClassC c;
    @Inject
    ClassD d;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerBComponent.builder().moduleC(new ModuleC()).build().inject(this);
        super.onCreate(savedInstanceState);
    }
}
