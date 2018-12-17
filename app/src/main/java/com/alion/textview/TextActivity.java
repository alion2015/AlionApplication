package com.alion.textview;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.alion.myapplication.R;

/**
 * ContentObserver 可以用来监听任何数据的变化
 */
public class TextActivity extends AppCompatActivity {

    private String TAG = "TextActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texttest);
        final View oval = this.findViewById(R.id.oval);
        oval.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
               /* Animator animator =
                        ViewAnimationUtils.createCircularReveal(
                                oval,
                                oval.getWidth() / 2,
                                oval.getHeight() / 2,
                                0,
                                oval.getWidth()/2);
                animator.setInterpolator(
                        new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();*/
                Animator animator =
                        ViewAnimationUtils.createCircularReveal(
                                oval,
                                0,
                                oval.getHeight(),
                                0,
                                (float) Math.hypot(oval.getWidth(),
                                        oval.getHeight()));
                animator.setInterpolator(
                        new AccelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }
}
