package com.facebook.base.lwperf.perfstats;

import com.facebook.common.procread.ProcReader;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DiskIOStats {
    private static final int CANCELLED_WRITE_BYTES_IDX = 6;
    private static final String PROC_SELF_IO_PATH = "/proc/self/io";
    private static final int RCHAR_IDX = 0;
    private static final int READ_BYTES_IDX = 4;
    private static final int SYSCR_IDX = 2;
    private static final int SYSCW_IDX = 3;
    private static final int WCHAR_IDX = 1;
    private static final int WRITE_BYTES_IDX = 5;
    private static final String[] sProcIoFormat = {"rchar:", "wchar:", "syscr:", "syscw:", "read_bytes:", "write_bytes:", "cancelled_write_bytes:"};
    public final long cancelledWriteBytes;
    public final long readBytes;
    public final long readChars;
    public final long readSysCalls;
    public final long writeBytes;
    public final long writeChars;
    public final long writeSysCalls;

    private DiskIOStats(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.readChars = j;
        this.writeChars = j2;
        this.readSysCalls = j3;
        this.writeSysCalls = j4;
        this.readBytes = j5;
        this.writeBytes = j6;
        this.cancelledWriteBytes = j7;
    }

    @Nullable
    public static DiskIOStats getCurrentStats() {
        String[] strArr = sProcIoFormat;
        long[] jArr = new long[strArr.length];
        if (ProcReader.readProcLines(PROC_SELF_IO_PATH, strArr, jArr)) {
            return new DiskIOStats(jArr[0], jArr[1], jArr[2], jArr[3], jArr[4], jArr[5], jArr[6]);
        }
        return null;
    }

    public DiskIOStats getDiskIOStatsDelta(DiskIOStats diskIOStats) {
        return new DiskIOStats(this.readChars - diskIOStats.readChars, this.writeChars - diskIOStats.writeChars, this.readSysCalls - diskIOStats.readSysCalls, this.writeSysCalls - diskIOStats.writeSysCalls, this.readBytes - diskIOStats.readBytes, this.writeBytes - diskIOStats.writeBytes, this.cancelledWriteBytes - diskIOStats.cancelledWriteBytes);
    }
}
