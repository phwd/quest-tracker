package com.facebook.quicklog;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.quicklog.utils.IntToObjectMap;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class IntermediatePoints {
    private static final int INITIAL_SIZE = 5;
    private PointData[] mData = new PointData[5];
    private int mHashOfStrings;
    private int mIndex;
    private int[] mLevels = new int[5];
    private String[] mNames = new String[5];
    private IntToObjectMap<?>[] mStopMetadata = new IntToObjectMap[5];
    private long[] mTimestamps = new long[5];

    public interface Visitor {
        void visit(long j, long j2, @EventLevel int i, String str, @Nullable PointData pointData, IntToObjectMap<?> intToObjectMap);
    }

    public void clear() {
        this.mIndex = 0;
        this.mHashOfStrings = 0;
        Arrays.fill(this.mNames, (Object) null);
        Arrays.fill(this.mData, (Object) null);
        Arrays.fill(this.mStopMetadata, (Object) null);
    }

    public int size() {
        return this.mIndex;
    }

    public void add(long j, TimeUnit timeUnit, @EventLevel int i, String str, @Nullable PointData pointData, @Nullable IntToObjectMap<?> intToObjectMap, boolean z) {
        int i2;
        int hashCode = str.hashCode();
        if (z) {
            i2 = this.mIndex;
            this.mIndex = i2 + 1;
        } else {
            int i3 = this.mHashOfStrings;
            if ((hashCode & i3) != hashCode) {
                int i4 = this.mIndex;
                this.mIndex = i4 + 1;
                this.mHashOfStrings = i3 | hashCode;
                i2 = i4;
            } else if (keyIndex(str) < 0) {
                i2 = this.mIndex;
                this.mIndex = i2 + 1;
            } else {
                return;
            }
        }
        if (i2 == this.mTimestamps.length) {
            grow((i2 >> 1) + i2);
        }
        if (pointData != null) {
            pointData.ensureLocked();
        }
        this.mTimestamps[i2] = timeUnit.toNanos(j);
        this.mNames[i2] = str;
        this.mData[i2] = pointData;
        this.mLevels[i2] = i;
        this.mStopMetadata[i2] = intToObjectMap;
    }

    private int keyIndex(String str) {
        int i = this.mIndex;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.mNames[i2].equals(str)) {
                return i2;
            }
        }
        return -1;
    }

    public void acceptForAll(Visitor visitor) {
        for (int i = 0; i < this.mIndex; i++) {
            visitor.visit(TimeUnit.NANOSECONDS.toMillis(this.mTimestamps[i]), this.mTimestamps[i], this.mLevels[i], this.mNames[i], this.mData[i], this.mStopMetadata[i]);
        }
    }

    public int findPointIndex(String str) {
        for (int i = 0; i < this.mIndex; i++) {
            if (str.equals(this.mNames[i])) {
                return i;
            }
        }
        return -1;
    }

    public String[] getAllNames() {
        int i = this.mIndex;
        String[] strArr = new String[i];
        System.arraycopy(this.mNames, 0, strArr, 0, i);
        return strArr;
    }

    @Nullable
    public IntToObjectMap<?> getStopMetadata(int i) {
        return this.mStopMetadata[i];
    }

    public long getTimestamp(int i) {
        return TimeUnit.NANOSECONDS.toMillis(this.mTimestamps[i]);
    }

    public long getTimestampNano(int i) {
        return this.mTimestamps[i];
    }

    @Nullable
    public PointData getPointData(int i) {
        return this.mData[i];
    }

    private void grow(int i) {
        this.mTimestamps = Arrays.copyOf(this.mTimestamps, i);
        this.mNames = (String[]) Arrays.copyOf(this.mNames, i);
        this.mData = (PointData[]) Arrays.copyOf(this.mData, i);
        this.mLevels = Arrays.copyOf(this.mLevels, i);
        this.mStopMetadata = (IntToObjectMap[]) Arrays.copyOf(this.mStopMetadata, i);
    }
}
