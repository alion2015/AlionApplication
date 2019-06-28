package com.alion.delaypool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayPool {
    private static ExecutorService pool = null;

    public static boolean needPause = false;
    /*初始化线程池*/
    public static void init() {
        if (pool == null) {
            pool = Executors.newSingleThreadExecutor();
        }
    }

    /*提交任务执行*/
    public static void execute(Runnable r) {
        init();
        pool.execute(r);
    }

    /* 关闭线程池*/
    public static void unInit() {
        if (pool == null || pool.isShutdown()) return;
        pool.shutdownNow();
        pool = null;
    }
    /* 暂停/恢复线程池*/
    public static void toPause(boolean toPause) {
        needPause = toPause;
    }
}
