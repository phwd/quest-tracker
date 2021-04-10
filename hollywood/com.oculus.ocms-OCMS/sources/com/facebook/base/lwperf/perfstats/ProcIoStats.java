package com.facebook.base.lwperf.perfstats;

import android.os.Process;
import com.facebook.common.procread.ProcReader;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcIoStats {
    public static final int DELAYACCT_BLKIO_TICKS = 4;
    public static final int MAJOR_FAULTS_INDEX = 2;
    public static final int MINOR_FAULTS_INDEX = 0;
    private static final int[] PROC_IO_STATS_FORMAT = {32, 32, 32, 32, 32, 32, 32, 32, 32, 8224, 8224, 8224, 8224, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 8224, 32, 32, 32};
    private static final String PROC_STAT_FILE_PATH = "/proc/self/stat";
    private static final String PROC_TASK_PATH = "/proc/self/task/";
    private static final String STATS_FILE_NAME = "/stat";

    private static final String constructThProcStatString() {
        return constructThProcStatString(Process.myTid());
    }

    private static final String constructThProcStatString(int i) {
        return PROC_TASK_PATH + i + STATS_FILE_NAME;
    }

    private static final long[] readFaultsInfoFromProcFile(String str) {
        long[] jArr = {-1, -1, -1, -1, -1};
        ProcReader.readProcFileLongs(str, PROC_IO_STATS_FORMAT, jArr);
        return jArr;
    }

    public static final long[] readIoStatsFromProc() {
        return readFaultsInfoFromProcFile(PROC_STAT_FILE_PATH);
    }

    public static final long readMajorFaultsFromProc() {
        return readIoStatsFromProc()[2];
    }

    public static final long readDelayacctBlkioTicksFromProc() {
        return readIoStatsFromProc()[4];
    }

    public static final long[] readPerfLoggerStatsFromProc() {
        return readFaultsInfoFromProcFile(PROC_STAT_FILE_PATH);
    }

    public static final long[] readThPerfLoggerStatsFromProc() {
        return readFaultsInfoFromProcFile(constructThProcStatString());
    }

    public static final long readThMajorFaultsFromProc() {
        return readThPerfLoggerStatsFromProc()[2];
    }

    public static final long[] readThPerfLoggerStatsFromProc(int i) {
        return readFaultsInfoFromProcFile(constructThProcStatString(i));
    }
}
