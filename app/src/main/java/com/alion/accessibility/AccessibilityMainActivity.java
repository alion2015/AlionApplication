package com.alion.accessibility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alion.myapplication.R;

import static com.alion.accessibility.utils.AccessibilityUtil.jumpToSettingPage;

public class AccessibilityMainActivity extends Activity implements View.OnClickListener {

    private View mOpenSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_main);
        initView();
        AccessibilityOperator.getInstance().init(this);
    }

    private void initView() {
        mOpenSetting = findViewById(R.id.open_accessibility_setting);
        mOpenSetting.setOnClickListener(this);
        findViewById(R.id.accessibility_find_and_click).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.open_accessibility_setting:
                jumpToSettingPage(this);
                break;
            case R.id.accessibility_find_and_click:
                startActivity(new Intent(this, AccessibilityNormalSample.class));
                break;
        }
    }
}
