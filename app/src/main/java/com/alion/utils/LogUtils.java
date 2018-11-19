package com.alion.utils;

import android.util.Log;

import java.util.Arrays;

public class LogUtils {

    public static void log(String tag, String message, Object... args) {
        String log = message + Arrays.deepToString(args);
        Log.i(tag, log);
    }

    public static void logTrace(String tag, String message) {
        StringBuffer traceLog = new StringBuffer("");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int length = elements.length;
        for (int i = 3; i < length; i++) {
            traceLog.append(elements[i].toString()).append("\n");
        }
        Log.i(tag, message + "\n" + traceLog);
    }
}