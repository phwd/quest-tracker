package com.facebook.errorreporting.nightwatch;

import com.facebook.flatbuffers.Flattenable;
import com.facebook.infer.annotation.Nullsafe;
import java.nio.ByteBuffer;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DataHistory {
    private static long nightwatcherStartTimeMs;
    private long count = 0;
    private final String label;
    private long lastTimeMs = -1;
    private int sampleMs = -1;
    private String[] tiers;
    private String[] times;
    private long valueAtStart;

    public DataHistory(String str) {
        this.label = "night_watch_" + str;
        this.tiers = new String[5];
        this.times = new String[5];
    }

    public void setValueAtStart(short s) {
        this.valueAtStart = (long) (s & Flattenable.VirtualFlattenableResolver.INVALID_FLATTENABLE_TYPE);
    }

    public static void setNightwatcherStartTimeMs(long j) {
        nightwatcherStartTimeMs = j;
    }

    public void setValueAtStart(long j) {
        this.valueAtStart = j;
    }

    public long getLastTimeMs() {
        return this.lastTimeMs;
    }

    public void from(ByteBuffer byteBuffer) {
        this.lastTimeMs = byteBuffer.getLong();
        this.count = byteBuffer.getLong();
        int i = 0;
        while (true) {
            String[] strArr = this.tiers;
            if (i < strArr.length) {
                strArr[i] = readShortAsHistory(byteBuffer);
                i++;
            } else {
                return;
            }
        }
    }

    public void from_005(ByteBuffer byteBuffer) {
        for (int i = 0; i < 5; i++) {
            constructTier(byteBuffer, i);
        }
        long j = byteBuffer.getLong();
        this.sampleMs = byteBuffer.getInt();
        if (j > -1) {
            this.lastTimeMs = j - ((long) this.sampleMs);
        }
        this.count = (long) byteBuffer.getInt();
    }

    public void fast_update_from(ByteBuffer byteBuffer) {
        constructTier(byteBuffer, 0);
        long j = byteBuffer.getLong();
        this.sampleMs = byteBuffer.getInt();
        if (j > -1) {
            this.lastTimeMs = j - ((long) this.sampleMs);
        }
        this.count = (long) byteBuffer.getInt();
    }

    public void slow_update_from(ByteBuffer byteBuffer) {
        for (int i = 1; i < 5; i++) {
            constructTier(byteBuffer, i);
        }
    }

    public void getEntries(Map<String, String> map) {
        long j = this.valueAtStart;
        if (!(j == -1 || j == 65535)) {
            map.put(this.label + "_at_start", String.valueOf(this.valueAtStart));
        }
        if (this.lastTimeMs != -1) {
            map.put(this.label + "_last_time_ms", String.valueOf(this.lastTimeMs));
        }
        if (this.count != -1) {
            map.put(this.label + "_count", String.valueOf(this.count));
        }
        if (this.sampleMs != -1) {
            map.put(this.label + "_sample_ms", String.valueOf(this.sampleMs));
        }
        int i = 0;
        while (true) {
            String[] strArr = this.tiers;
            if (i < strArr.length) {
                if (!strArr[i].isEmpty()) {
                    map.put(this.label + "_tier" + i, this.tiers[i]);
                    map.put(this.label + "_time_ms" + i, this.times[i]);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static String readShortAsHistory(ByteBuffer byteBuffer) {
        long j = byteBuffer.getLong();
        if (j == -1) {
            return "";
        }
        short[] sArr = {-1, -1, -1, -1};
        for (int length = sArr.length - 1; length >= 0; length--) {
            sArr[length] = (short) ((int) (65535 & j));
            j >>= 16;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < sArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            if (sArr[i] == -1) {
                sb.append(-1);
            } else {
                sb.append(sArr[i] & Flattenable.VirtualFlattenableResolver.INVALID_FLATTENABLE_TYPE);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void constructTier(ByteBuffer byteBuffer, int i) {
        int i2 = i < 2 ? 8 : 4;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i3] = (long) byteBuffer.getInt();
            jArr2[i3] = ((long) byteBuffer.getInt()) + nightwatcherStartTimeMs;
        }
        int i4 = 0;
        for (int i5 = 1; i5 < i2; i5++) {
            if (jArr2[i5] < jArr2[i4]) {
                i4 = i5;
            }
        }
        sb.append("[");
        sb2.append("[");
        for (int i6 = i4; i6 < i4 + i2; i6++) {
            int i7 = i6 % i2;
            if (jArr[i7] != -1) {
                if (sb.length() > 1) {
                    sb.append(",");
                    sb2.append(",");
                }
                sb.append(jArr[i7]);
                sb2.append(jArr2[i7]);
            }
        }
        sb.append("]");
        sb2.append("]");
        if (sb.length() > 2) {
            this.tiers[i] = sb.toString();
            this.times[i] = sb2.toString();
            return;
        }
        this.tiers[i] = "";
        this.times[i] = "";
    }
}
