package com.alion.blockcanary;

import android.view.Choreographer;

import com.alion.blockcanary.internal.LruLinkedList;

import java.util.concurrent.TimeUnit;

/**
 * Created by alion on 2018/11/28.
 */
class FrameCallbackImpl implements Choreographer.FrameCallback {

    private LruLinkedList mData;
    private boolean running;
    private long lastFrameTimeNano;
    private long maxValue;

    FrameCallbackImpl(LruLinkedList data) {
        mData = data;   //
        running = true;
        lastFrameTimeNano = 0;
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (!running || mData == null) {
            destroy();
            return;
        }
        final long frameTime = TimeUnit.NANOSECONDS.toMillis(frameTimeNanos - lastFrameTimeNano);
        if (lastFrameTimeNano != 0 && frameTime >= 16) {
            mData.add(frameTime);
            maxValue = frameTime > maxValue ? frameTime : maxValue;
        }
        lastFrameTimeNano = frameTimeNanos;

        Choreographer.getInstance().postFrameCallback(this);
    }



    void destroy() {
        if (mData != null)
            mData.clear();
        mData = null;
        Choreographer.getInstance().removeFrameCallback(this);
    }
}
