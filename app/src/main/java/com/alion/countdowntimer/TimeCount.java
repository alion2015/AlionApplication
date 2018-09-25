package com.alion.countdowntimer;

import android.os.CountDownTimer;

/**
 * Created by wutong on 2018/9/25.
 */

// timer Util
    /* 定义一个倒计时的内部类 */
public class TimeCount extends CountDownTimer {
    public interface TimerCallback {
        public void onFinish();
        public void onTick(long millisUntilFinished);
    }
    TimerCallback mTimerCallback;
    public TimeCount(long millisInFuture, long countDownInterval,TimerCallback timerCallback) {
        super(millisInFuture, countDownInterval);// 总时长,刷新回调间隔
        mTimerCallback = timerCallback;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        mTimerCallback.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {// 刷新--剩余时间
        mTimerCallback.onTick(millisUntilFinished);
    }
}
