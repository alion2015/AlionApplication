/*
 * Copyright (C) 2016 MarkZhai (http://zhaiyifan.cn).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alion.blockcanary.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.alion.blockcanary.BlockCanaryInternals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Information to trace a block.
 */
public class BlockInfo {

    private static final String TAG = "BlockInfo";

    public static final SimpleDateFormat TIME_FORMATTER =
            new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);

    public static final String SEPARATOR = "\r\n";
    public static final String KV = " = ";

    public static final String NEW_INSTANCE_METHOD = "newInstance: ";

    public static final String KEY_CPU_BUSY = "cpu-busy";
    public static final String KEY_CPU_RATE = "cpu-rate";
    public static final String KEY_TIME_COST = "time";
    public static final String KEY_STACK = "stack";
    public static final String KEY_PROCESS = "process";
    public static final String KEY_TOTAL_MEMORY = "totalMemory";
    public static final String KEY_FREE_MEMORY = "freeMemory";
    public static final String KEY_FRAME_RATE = "frameRate";

    /**
     * The International Mobile Equipment Identity or IMEI /aɪˈmiː/ is a number,
     * usually unique, to identify 3GPP and iDEN mobile phones
     */

    // Per Block Info fields
    public String processName;
    public String freeMemory;
    public String totalMemory;
    public long timeCost;

    public boolean cpuBusy;
    public String cpuRateInfo;
    public String mFrameRate;
    public ArrayList<String> threadStackEntries = new ArrayList<>();

    private StringBuilder basicSb = new StringBuilder();
    private StringBuilder cpuSb = new StringBuilder();
    private StringBuilder timeSb = new StringBuilder();
    private StringBuilder stackSb = new StringBuilder();

    public BlockInfo() {
    }

    public static BlockInfo newInstance() {
        BlockInfo blockInfo = new BlockInfo();
        blockInfo.processName = ProcessUtils.myProcessName();
        blockInfo.freeMemory = String.valueOf(PerformanceUtils.getFreeMemory());
        blockInfo.totalMemory = String.valueOf(PerformanceUtils.getTotalMemory());

        return blockInfo;
    }

    public BlockInfo setCpuBusyFlag(boolean busy) {
        cpuBusy = busy;
        return this;
    }

    public BlockInfo setRecentCpuRate(String info) {
        cpuRateInfo = info;
        return this;
    }
    public BlockInfo setFrameRate(String info) {
        mFrameRate = info;
        return this;
    }

    public BlockInfo setThreadStackEntries(ArrayList<String> threadStackEntries) {
        this.threadStackEntries = threadStackEntries;
        return this;
    }

    public BlockInfo setMainThreadTimeCost(long realTimeStart, long realTimeEnd, long threadTimeStart, long threadTimeEnd) {
        timeCost = realTimeEnd - realTimeStart;
        return this;
    }

    public BlockInfo flushString() {
        String separator = SEPARATOR;
        basicSb.append(KEY_PROCESS).append(KV).append(processName).append(separator);
        basicSb.append(KEY_FRAME_RATE).append(KV).append(mFrameRate).append(separator);
        basicSb.append(KEY_FREE_MEMORY).append(KV).append(freeMemory).append(separator);
        basicSb.append(KEY_TOTAL_MEMORY).append(KV).append(totalMemory).append(separator);

        timeSb.append(KEY_TIME_COST).append(KV).append(timeCost).append(separator);

        cpuSb.append(KEY_CPU_BUSY).append(KV).append(cpuBusy).append(separator);
        cpuSb.append(KEY_CPU_RATE).append(KV).append(cpuRateInfo).append(separator);

        if (threadStackEntries != null && !threadStackEntries.isEmpty()) {
            StringBuilder temp = new StringBuilder();
            for (String s : threadStackEntries) {
                temp.append(s);
                temp.append(separator);
            }
            stackSb.append(KEY_STACK).append(KV).append(temp.toString()).append(separator);
        }
        return this;
    }
    public String toString() {
        return String.valueOf(basicSb) + timeSb + cpuSb + stackSb;
    }
}
