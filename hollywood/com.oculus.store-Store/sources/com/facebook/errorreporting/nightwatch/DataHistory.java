package com.facebook.errorreporting.nightwatch;

import com.facebook.infer.annotation.Nullsafe;
import java.io.DataInputStream;
import java.io.IOException;
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

    public DataHistory(String label2) {
        this.label = "night_watch_" + label2;
        this.tiers = new String[5];
        this.times = new String[5];
    }

    public void setValueAtStart(short valueAtStart2) {
        this.valueAtStart = (long) (65535 & valueAtStart2);
    }

    public static void setNightwatcherStartTimeMs(long nightwatcherStartTimeMs2) {
        nightwatcherStartTimeMs = nightwatcherStartTimeMs2;
    }

    public void setValueAtStart(long valueAtStart2) {
        this.valueAtStart = valueAtStart2;
    }

    public void from(DataInputStream reader) throws IOException {
        this.lastTimeMs = Long.reverseBytes(reader.readLong());
        this.count = Long.reverseBytes(reader.readLong());
        for (int i = 0; i < this.tiers.length; i++) {
            this.tiers[i] = readShortAsHistory(reader);
        }
    }

    public void from_005(DataInputStream reader) throws IOException {
        for (int tier = 0; tier < 5; tier++) {
            constructTier(reader, tier);
        }
        long expectedTimeMs = Long.reverseBytes(reader.readLong());
        this.sampleMs = Integer.reverseBytes(reader.readInt());
        if (expectedTimeMs > -1) {
            this.lastTimeMs = expectedTimeMs - ((long) this.sampleMs);
        }
        this.count = (long) Integer.reverseBytes(reader.readInt());
    }

    public void getEntries(Map<String, String> entryMap) {
        if (!(this.valueAtStart == -1 || this.valueAtStart == 65535)) {
            entryMap.put(this.label + "_at_start", String.valueOf(this.valueAtStart));
        }
        if (this.lastTimeMs != -1) {
            entryMap.put(this.label + "_last_time_ms", String.valueOf(this.lastTimeMs));
        }
        if (this.count != -1) {
            entryMap.put(this.label + "_count", String.valueOf(this.count));
        }
        if (this.sampleMs != -1) {
            entryMap.put(this.label + "_sample_ms", String.valueOf(this.sampleMs));
        }
        for (int i = 0; i < this.tiers.length; i++) {
            if (!this.tiers[i].isEmpty()) {
                entryMap.put(this.label + "_tier" + i, this.tiers[i]);
                entryMap.put(this.label + "_time_ms" + i, this.times[i]);
            }
        }
    }

    public static String readShortAsHistory(DataInputStream reader) throws IOException {
        long history = Long.reverseBytes(reader.readLong());
        if (history == -1) {
            return "";
        }
        short[] historyItems = {-1, -1, -1, -1};
        for (int i = historyItems.length - 1; i >= 0; i--) {
            historyItems[i] = (short) ((int) (65535 & history));
            history >>= 16;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i2 = 0; i2 < historyItems.length; i2++) {
            if (i2 > 0) {
                sb.append(",");
            }
            if (historyItems[i2] == -1) {
                sb.append(-1);
            } else {
                sb.append(historyItems[i2] & 65535);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void constructTier(DataInputStream reader, int tier) throws IOException {
        int numEntries = tier < 2 ? 8 : 4;
        long[] data = new long[numEntries];
        long[] timeMs = new long[numEntries];
        StringBuilder sbData = new StringBuilder();
        StringBuilder sbTimes = new StringBuilder();
        for (int i = 0; i < numEntries; i++) {
            data[i] = (long) Integer.reverseBytes(reader.readInt());
            timeMs[i] = ((long) Integer.reverseBytes(reader.readInt())) + nightwatcherStartTimeMs;
        }
        int earliestIndex = 0;
        for (int i2 = 1; i2 < numEntries; i2++) {
            if (timeMs[i2] < timeMs[earliestIndex]) {
                earliestIndex = i2;
            }
        }
        sbData.append("[");
        sbTimes.append("[");
        for (int j = earliestIndex; j < earliestIndex + numEntries; j++) {
            int i3 = j % numEntries;
            if (data[i3] != -1) {
                if (sbData.length() > 1) {
                    sbData.append(",");
                    sbTimes.append(",");
                }
                sbData.append(data[i3]);
                sbTimes.append(timeMs[i3]);
            }
        }
        sbData.append("]");
        sbTimes.append("]");
        if (sbData.length() > 2) {
            this.tiers[tier] = sbData.toString();
            this.times[tier] = sbTimes.toString();
            return;
        }
        this.tiers[tier] = "";
        this.times[tier] = "";
    }
}
