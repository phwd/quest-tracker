package com.facebook.base.lwperf.perfstats;

import com.facebook.common.procread.ProcReader;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.quicklog.identifiers.PerformanceEventFields;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcVMStats {
    private static final int ALLOCSTALL = 3;
    private static final int ALLOCSTALL_DMA = 5;
    private static final int ALLOCSTALL_DMA_32 = 6;
    private static final int ALLOCSTALL_HIGH = 4;
    private static final int ALLOCSTALL_MOVABLE = 8;
    private static final int ALLOCSTALL_NORMAL = 7;
    private static final int PGMAJFAULT = 2;
    private static final int PGPGIN = 0;
    private static final int PGPGOUT = 1;
    private static final int PGSTEAL_DIRECT_HIGH = 13;
    private static final int PGSTEAL_DIRECT_MOVABLE = 14;
    private static final int PGSTEAL_DIRECT_NORMAL = 12;
    private static final int PGSTEAL_KSWAPD_HIGH = 10;
    private static final int PGSTEAL_KSWAPD_MOVABLE = 11;
    private static final int PGSTEAL_KSWAPD_NORMAL = 9;
    private static final String[] PROC_VMSTAT_LINES = new String[15];
    private static final int STAT_COUNT = 15;
    private static final String VMSTAT_PATH = "/proc/vmstat";

    static {
        String[] strArr = PROC_VMSTAT_LINES;
        strArr[0] = "pgpgin";
        strArr[1] = "pgpgout";
        strArr[2] = "pgmajfault";
        strArr[3] = PerformanceEventFields.ALLOC_STALL;
        strArr[4] = "allocstall_high";
        strArr[5] = "allocstall_dma";
        strArr[6] = "allocstall_dma32";
        strArr[7] = "allocstall_normal";
        strArr[8] = "allocstall_movable";
        strArr[9] = "pgsteal_kswapd_normal";
        strArr[10] = "pgsteal_kswapd_high";
        strArr[11] = "pgsteal_kswapd_movable";
        strArr[12] = "pgsteal_direct_normal";
        strArr[13] = "pgsteal_direct_high";
        strArr[14] = "pgsteal_direct_movable";
    }

    private ProcVMStats() {
    }

    public static final VMStatsValues readFromProc() {
        String[] strArr = PROC_VMSTAT_LINES;
        long[] jArr = new long[strArr.length];
        ProcReader.readProcLines(VMSTAT_PATH, strArr, jArr);
        VMStatsValues vMStatsValues = new VMStatsValues();
        vMStatsValues.mPagesIn = jArr[0];
        vMStatsValues.mPagesOut = jArr[1];
        vMStatsValues.mPagesMajFault = jArr[2];
        vMStatsValues.mAllocStalls += jArr[3];
        vMStatsValues.mAllocStalls += jArr[4];
        vMStatsValues.mAllocStalls += jArr[5];
        vMStatsValues.mAllocStalls += jArr[6];
        vMStatsValues.mAllocStalls += jArr[7];
        vMStatsValues.mAllocStalls += jArr[8];
        vMStatsValues.mPageSteals += jArr[9];
        vMStatsValues.mPageSteals += jArr[10];
        vMStatsValues.mPageSteals += jArr[11];
        vMStatsValues.mPageSteals += jArr[12];
        vMStatsValues.mPageSteals += jArr[13];
        vMStatsValues.mPageSteals += jArr[14];
        return (vMStatsValues.mPagesIn == 0 && vMStatsValues.mPagesOut == 0 && vMStatsValues.mPagesMajFault == 0 && vMStatsValues.mAllocStalls == 0 && vMStatsValues.mPageSteals == 0) ? new VMStatsValues(-1) : vMStatsValues;
    }

    public static final class VMStatsValues {
        public long mAllocStalls;
        public long mPageSteals;
        public long mPagesIn;
        public long mPagesMajFault;
        public long mPagesOut;

        public VMStatsValues() {
        }

        public VMStatsValues(int i) {
            long j = (long) i;
            this.mAllocStalls = j;
            this.mPagesIn = j;
            this.mPagesOut = j;
            this.mPagesMajFault = j;
            this.mPageSteals = j;
        }
    }
}
