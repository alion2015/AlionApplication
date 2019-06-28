package com.alion.delaypool;

import android.util.Log;

import static com.alion.delaypool.DelayPool.needPause;

public class DelayRun implements Runnable{
    long delay;
    Runnable torun;
    int index;

    public DelayRun(int index,long delay, Runnable torun) {
        this.delay = delay;
        this.torun = torun;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            while(needPause){
                Log.d("alionlog", "run: needPause");
                Thread.currentThread().sleep(200);
                Log.d("alionlog", "run: needPause");
            }
            Log.d("alionlog", "run: "+index);
            Log.d("alionlog", "run: delayto"+(System.currentTimeMillis()+delay));
            Thread.currentThread().sleep(delay);
            Log.d("alionlog", "run: "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        torun.run();
    }
}
