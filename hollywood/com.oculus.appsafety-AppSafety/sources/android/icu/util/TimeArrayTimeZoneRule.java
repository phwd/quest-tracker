package android.icu.util;

import java.util.Arrays;
import java.util.Date;

public class TimeArrayTimeZoneRule extends TimeZoneRule {
    private static final long serialVersionUID = -1117109130077415245L;
    private final long[] startTimes;
    private final int timeType;

    public TimeArrayTimeZoneRule(String name, int rawOffset, int dstSavings, long[] startTimes2, int timeType2) {
        super(name, rawOffset, dstSavings);
        if (startTimes2 == null || startTimes2.length == 0) {
            throw new IllegalArgumentException("No start times are specified.");
        }
        this.startTimes = (long[]) startTimes2.clone();
        Arrays.sort(this.startTimes);
        this.timeType = timeType2;
    }

    public long[] getStartTimes() {
        return (long[]) this.startTimes.clone();
    }

    public int getTimeType() {
        return this.timeType;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFirstStart(int prevRawOffset, int prevDSTSavings) {
        return new Date(getUTC(this.startTimes[0], prevRawOffset, prevDSTSavings));
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFinalStart(int prevRawOffset, int prevDSTSavings) {
        long[] jArr = this.startTimes;
        return new Date(getUTC(jArr[jArr.length - 1], prevRawOffset, prevDSTSavings));
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getNextStart(long base, int prevOffset, int prevDSTSavings, boolean inclusive) {
        int i = this.startTimes.length - 1;
        while (i >= 0) {
            long time = getUTC(this.startTimes[i], prevOffset, prevDSTSavings);
            if (time < base || (!inclusive && time == base)) {
                break;
            }
            i--;
        }
        long[] jArr = this.startTimes;
        if (i == jArr.length - 1) {
            return null;
        }
        return new Date(getUTC(jArr[i + 1], prevOffset, prevDSTSavings));
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getPreviousStart(long base, int prevOffset, int prevDSTSavings, boolean inclusive) {
        for (int i = this.startTimes.length - 1; i >= 0; i--) {
            long time = getUTC(this.startTimes[i], prevOffset, prevDSTSavings);
            if (time < base || (inclusive && time == base)) {
                return new Date(time);
            }
        }
        return null;
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isEquivalentTo(TimeZoneRule other) {
        if ((other instanceof TimeArrayTimeZoneRule) && this.timeType == ((TimeArrayTimeZoneRule) other).timeType && Arrays.equals(this.startTimes, ((TimeArrayTimeZoneRule) other).startTimes)) {
            return super.isEquivalentTo(other);
        }
        return false;
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isTransitionRule() {
        return true;
    }

    private long getUTC(long time, int raw, int dst) {
        if (this.timeType != 2) {
            time -= (long) raw;
        }
        if (this.timeType == 0) {
            return time - ((long) dst);
        }
        return time;
    }

    @Override // android.icu.util.TimeZoneRule
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(super.toString());
        buf.append(", timeType=");
        buf.append(this.timeType);
        buf.append(", startTimes=[");
        for (int i = 0; i < this.startTimes.length; i++) {
            if (i != 0) {
                buf.append(", ");
            }
            buf.append(Long.toString(this.startTimes[i]));
        }
        buf.append("]");
        return buf.toString();
    }
}
