package android.icu.util;

import java.util.Arrays;

public class TimeArrayTimeZoneRule extends TimeZoneRule {
    private static final long serialVersionUID = -1117109130077415245L;
    private final long[] startTimes;
    private final int timeType;

    public TimeArrayTimeZoneRule(String str, int i, int i2, long[] jArr, int i3) {
        super(str, i, i2);
        if (jArr == null || jArr.length == 0) {
            throw new IllegalArgumentException("No start times are specified.");
        }
        this.startTimes = (long[]) jArr.clone();
        Arrays.sort(this.startTimes);
        this.timeType = i3;
    }

    @Override // android.icu.util.TimeZoneRule
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", timeType=");
        sb.append(this.timeType);
        sb.append(", startTimes=[");
        for (int i = 0; i < this.startTimes.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(Long.toString(this.startTimes[i]));
        }
        sb.append("]");
        return sb.toString();
    }
}
