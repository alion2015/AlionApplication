package com.alion.provider;

import android.graphics.Bitmap;

public class LauncherData {
    public String title;
    public String intent;
    public Bitmap icon;

    public LauncherData(String title, String intent, Bitmap icon) {
        this.title = title;
        this.intent = intent;
        this.icon = icon;
    }
}
