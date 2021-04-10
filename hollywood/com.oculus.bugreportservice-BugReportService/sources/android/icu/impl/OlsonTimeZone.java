package android.icu.impl;

import android.icu.util.BasicTimeZone;
import android.icu.util.InitialTimeZoneRule;
import android.icu.util.SimpleTimeZone;
import android.icu.util.TimeArrayTimeZoneRule;
import android.icu.util.TimeZone;
import android.icu.util.TimeZoneRule;
import android.icu.util.TimeZoneTransition;
import android.icu.util.UResourceBundle;
import java.io.ObjectInputStream;

public class OlsonTimeZone extends BasicTimeZone {
    private static final boolean DEBUG = ICUDebug.enabled("olson");
    static final long serialVersionUID = -6281977362477515376L;
    private volatile String canonicalID = null;
    private double finalStartMillis = Double.MAX_VALUE;
    private int finalStartYear = Integer.MAX_VALUE;
    private SimpleTimeZone finalZone = null;
    private transient SimpleTimeZone finalZoneWithStartYear;
    private transient TimeZoneTransition firstFinalTZTransition;
    private transient TimeZoneTransition firstTZTransition;
    private transient int firstTZTransitionIdx;
    private transient TimeArrayTimeZoneRule[] historicRules;
    private transient InitialTimeZoneRule initialRule;
    private volatile transient boolean isFrozen = DEBUG;
    private int serialVersionOnStream = 1;
    private int transitionCount;
    private transient boolean transitionRulesInitialized;
    private long[] transitionTimes64;
    private int typeCount;
    private byte[] typeMapData;
    private int[] typeOffsets;

    private int getInt(byte b) {
        return b & 255;
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i3 >= 0 && i3 <= 11) {
            return getOffset(i, i2, i3, i4, i5, i6, Grego.monthLength(i2, i3));
        }
        throw new IllegalArgumentException("Month is not in the legal range: " + i3);
    }

    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if ((i == 1 || i == 0) && i3 >= 0 && i3 <= 11 && i4 >= 1 && i4 <= i7 && i5 >= 1 && i5 <= 7 && i6 >= 0 && i6 < 86400000 && i7 >= 28 && i7 <= 31) {
            if (i == 0) {
                i2 = -i2;
            }
            SimpleTimeZone simpleTimeZone = this.finalZone;
            if (simpleTimeZone != null && i2 >= this.finalStartYear) {
                return simpleTimeZone.getOffset(i, i2, i3, i4, i5, i6);
            }
            int[] iArr = new int[2];
            getHistoricalOffset((Grego.fieldsToDay(i2, i3, i4) * 86400000) + ((long) i6), true, 3, 1, iArr);
            return iArr[0] + iArr[1];
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long j, boolean z, int[] iArr) {
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone == null || ((double) j) < this.finalStartMillis) {
            getHistoricalOffset(j, z, 4, 12, iArr);
        } else {
            simpleTimeZone.getOffset(j, z, iArr);
        }
    }

    @Override // android.icu.util.BasicTimeZone
    public void getOffsetFromLocal(long j, int i, int i2, int[] iArr) {
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone == null || ((double) j) < this.finalStartMillis) {
            getHistoricalOffset(j, true, i, i2, iArr);
        } else {
            simpleTimeZone.getOffsetFromLocal(j, i, i2, iArr);
        }
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        int[] iArr = new int[2];
        getOffset(System.currentTimeMillis(), DEBUG, iArr);
        return iArr[0];
    }

    public OlsonTimeZone(UResourceBundle uResourceBundle, UResourceBundle uResourceBundle2, String str) {
        super(str);
        construct(uResourceBundle, uResourceBundle2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v9, resolved type: android.icu.util.SimpleTimeZone */
    /* JADX DEBUG: Multi-variable search result rejected for r4v15, resolved type: android.icu.util.SimpleTimeZone */
    /* JADX DEBUG: Multi-variable search result rejected for r4v21, resolved type: android.icu.util.SimpleTimeZone */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void construct(android.icu.util.UResourceBundle r32, android.icu.util.UResourceBundle r33) {
        /*
        // Method dump skipped, instructions count: 471
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.OlsonTimeZone.construct(android.icu.util.UResourceBundle, android.icu.util.UResourceBundle):void");
    }

    private void getHistoricalOffset(long j, boolean z, int i, int i2, int[] iArr) {
        int i3;
        int i4;
        if (this.transitionCount != 0) {
            long floorDivide = Grego.floorDivide(j, 1000);
            if (z || floorDivide >= this.transitionTimes64[0]) {
                int i5 = this.transitionCount - 1;
                while (i5 >= 0) {
                    long j2 = this.transitionTimes64[i5];
                    if (z && floorDivide >= j2 - 86400) {
                        int i6 = i5 - 1;
                        int zoneOffsetAt = zoneOffsetAt(i6);
                        boolean z2 = dstOffsetAt(i6) != 0;
                        int zoneOffsetAt2 = zoneOffsetAt(i5);
                        boolean z3 = dstOffsetAt(i5) != 0;
                        boolean z4 = z2 && !z3;
                        boolean z5 = !z2 && z3;
                        j2 += (zoneOffsetAt2 - zoneOffsetAt < 0 ? ((i3 = i2 & 3) != 1 || !z4) && ((i3 != 3 || !z5) && ((i3 == 1 && z5) || ((i3 == 3 && z4) || (i2 & 12) == 4))) : ((i4 = i & 3) == 1 && z4) || ((i4 == 3 && z5) || ((i4 != 1 || !z5) && ((i4 != 3 || !z4) && (i & 12) == 12)))) ? (long) zoneOffsetAt : (long) zoneOffsetAt2;
                    }
                    if (floorDivide >= j2) {
                        break;
                    }
                    i5--;
                }
                iArr[0] = rawOffsetAt(i5) * 1000;
                iArr[1] = dstOffsetAt(i5) * 1000;
                return;
            }
            iArr[0] = initialRawOffset() * 1000;
            iArr[1] = initialDstOffset() * 1000;
            return;
        }
        iArr[0] = initialRawOffset() * 1000;
        iArr[1] = initialDstOffset() * 1000;
    }

    private int zoneOffsetAt(int i) {
        int i2 = i >= 0 ? getInt(this.typeMapData[i]) * 2 : 0;
        int[] iArr = this.typeOffsets;
        return iArr[i2] + iArr[i2 + 1];
    }

    private int rawOffsetAt(int i) {
        return this.typeOffsets[i >= 0 ? getInt(this.typeMapData[i]) * 2 : 0];
    }

    private int dstOffsetAt(int i) {
        return this.typeOffsets[(i >= 0 ? getInt(this.typeMapData[i]) * 2 : 0) + 1];
    }

    private int initialRawOffset() {
        return this.typeOffsets[0];
    }

    private int initialDstOffset() {
        return this.typeOffsets[1];
    }

    public String toString() {
        new StringBuilder();
        super.toString();
        throw null;
    }

    private static UResourceBundle loadRule(UResourceBundle uResourceBundle, String str) {
        return uResourceBundle.get("Rules").get(str);
    }

    @Override // android.icu.util.TimeZone
    public boolean equals(Object obj) {
        SimpleTimeZone simpleTimeZone;
        SimpleTimeZone simpleTimeZone2;
        if (!super.equals(obj)) {
            return DEBUG;
        }
        OlsonTimeZone olsonTimeZone = (OlsonTimeZone) obj;
        if (!Utility.arrayEquals(this.typeMapData, (Object) olsonTimeZone.typeMapData)) {
            if (this.finalStartYear != olsonTimeZone.finalStartYear) {
                return DEBUG;
            }
            if (!(this.finalZone == null && olsonTimeZone.finalZone == null) && ((simpleTimeZone = this.finalZone) == null || (simpleTimeZone2 = olsonTimeZone.finalZone) == null || !simpleTimeZone.equals(simpleTimeZone2) || this.transitionCount != olsonTimeZone.transitionCount || this.typeCount != olsonTimeZone.typeCount || !Utility.arrayEquals((Object) this.transitionTimes64, (Object) olsonTimeZone.transitionTimes64) || !Utility.arrayEquals(this.typeOffsets, (Object) olsonTimeZone.typeOffsets) || !Utility.arrayEquals(this.typeMapData, (Object) olsonTimeZone.typeMapData))) {
                return DEBUG;
            }
        }
        return true;
    }

    @Override // android.icu.util.TimeZone
    public int hashCode() {
        int i = this.finalStartYear;
        int i2 = this.transitionCount;
        int i3 = i ^ ((i >>> 4) + i2);
        int i4 = i2 >>> 6;
        int i5 = this.typeCount;
        long j = (long) (i3 ^ (i4 + i5));
        long doubleToLongBits = ((long) (i5 >>> 8)) + Double.doubleToLongBits(this.finalStartMillis);
        SimpleTimeZone simpleTimeZone = this.finalZone;
        int i6 = 0;
        int hashCode = (int) (j ^ ((doubleToLongBits + ((long) (simpleTimeZone == null ? 0 : simpleTimeZone.hashCode()))) + ((long) super.hashCode())));
        if (this.transitionTimes64 != null) {
            int i7 = hashCode;
            int i8 = 0;
            while (true) {
                long[] jArr = this.transitionTimes64;
                if (i8 >= jArr.length) {
                    break;
                }
                i7 = (int) (((long) i7) + ((jArr[i8] >>> 8) ^ jArr[i8]));
                i8++;
            }
            hashCode = i7;
        }
        int i9 = hashCode;
        int i10 = 0;
        while (true) {
            int[] iArr = this.typeOffsets;
            if (i10 >= iArr.length) {
                break;
            }
            i9 += (iArr[i10] >>> 8) ^ iArr[i10];
            i10++;
        }
        if (this.typeMapData != null) {
            while (true) {
                byte[] bArr = this.typeMapData;
                if (i6 >= bArr.length) {
                    break;
                }
                i9 += bArr[i6] & 255;
                i6++;
            }
        }
        return i9;
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getNextTransition(long j, boolean z) {
        int i;
        initTransitionRules();
        if (this.finalZone != null) {
            if (z && j == this.firstFinalTZTransition.getTime()) {
                return this.firstFinalTZTransition;
            }
            if (j >= this.firstFinalTZTransition.getTime()) {
                if (this.finalZone.useDaylightTime()) {
                    return this.finalZoneWithStartYear.getNextTransition(j, z);
                }
                return null;
            }
        }
        if (this.historicRules == null) {
            return null;
        }
        int i2 = this.transitionCount;
        while (true) {
            i2--;
            if (i2 < this.firstTZTransitionIdx || j > this.transitionTimes64[i2] * 1000 || (!z && i == 0)) {
            }
        }
        if (i2 == this.transitionCount - 1) {
            return this.firstFinalTZTransition;
        }
        if (i2 < this.firstTZTransitionIdx) {
            return this.firstTZTransition;
        }
        int i3 = i2 + 1;
        TimeArrayTimeZoneRule timeArrayTimeZoneRule = this.historicRules[getInt(this.typeMapData[i3])];
        TimeArrayTimeZoneRule timeArrayTimeZoneRule2 = this.historicRules[getInt(this.typeMapData[i2])];
        long j2 = this.transitionTimes64[i3] * 1000;
        if (timeArrayTimeZoneRule2.getName().equals(timeArrayTimeZoneRule.getName()) && timeArrayTimeZoneRule2.getRawOffset() == timeArrayTimeZoneRule.getRawOffset() && timeArrayTimeZoneRule2.getDSTSavings() == timeArrayTimeZoneRule.getDSTSavings()) {
            return getNextTransition(j2, DEBUG);
        }
        return new TimeZoneTransition(j2, timeArrayTimeZoneRule2, timeArrayTimeZoneRule);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getPreviousTransition(long j, boolean z) {
        int i;
        initTransitionRules();
        if (this.finalZone != null) {
            if (z && j == this.firstFinalTZTransition.getTime()) {
                return this.firstFinalTZTransition;
            }
            if (j > this.firstFinalTZTransition.getTime()) {
                if (this.finalZone.useDaylightTime()) {
                    return this.finalZoneWithStartYear.getPreviousTransition(j, z);
                }
                return this.firstFinalTZTransition;
            }
        }
        if (this.historicRules == null) {
            return null;
        }
        int i2 = this.transitionCount;
        while (true) {
            i2--;
            if (i2 < this.firstTZTransitionIdx || j > this.transitionTimes64[i2] * 1000 || (z && i == 0)) {
                int i3 = this.firstTZTransitionIdx;
            }
        }
        int i32 = this.firstTZTransitionIdx;
        if (i2 < i32) {
            return null;
        }
        if (i2 == i32) {
            return this.firstTZTransition;
        }
        TimeArrayTimeZoneRule timeArrayTimeZoneRule = this.historicRules[getInt(this.typeMapData[i2])];
        TimeArrayTimeZoneRule timeArrayTimeZoneRule2 = this.historicRules[getInt(this.typeMapData[i2 - 1])];
        long j2 = this.transitionTimes64[i2] * 1000;
        if (timeArrayTimeZoneRule2.getName().equals(timeArrayTimeZoneRule.getName()) && timeArrayTimeZoneRule2.getRawOffset() == timeArrayTimeZoneRule.getRawOffset() && timeArrayTimeZoneRule2.getDSTSavings() == timeArrayTimeZoneRule.getDSTSavings()) {
            return getPreviousTransition(j2, DEBUG);
        }
        return new TimeZoneTransition(j2, timeArrayTimeZoneRule2, timeArrayTimeZoneRule);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules() {
        int i;
        int i2;
        initTransitionRules();
        if (this.historicRules != null) {
            int i3 = 0;
            i = 1;
            while (true) {
                TimeArrayTimeZoneRule[] timeArrayTimeZoneRuleArr = this.historicRules;
                if (i3 >= timeArrayTimeZoneRuleArr.length) {
                    break;
                }
                if (timeArrayTimeZoneRuleArr[i3] != null) {
                    i++;
                }
                i3++;
            }
        } else {
            i = 1;
        }
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            i = simpleTimeZone.useDaylightTime() ? i + 2 : i + 1;
        }
        TimeZoneRule[] timeZoneRuleArr = new TimeZoneRule[i];
        timeZoneRuleArr[0] = this.initialRule;
        if (this.historicRules != null) {
            int i4 = 0;
            i2 = 1;
            while (true) {
                TimeArrayTimeZoneRule[] timeArrayTimeZoneRuleArr2 = this.historicRules;
                if (i4 >= timeArrayTimeZoneRuleArr2.length) {
                    break;
                }
                if (timeArrayTimeZoneRuleArr2[i4] != null) {
                    timeZoneRuleArr[i2] = timeArrayTimeZoneRuleArr2[i4];
                    i2++;
                }
                i4++;
            }
        } else {
            i2 = 1;
        }
        SimpleTimeZone simpleTimeZone2 = this.finalZone;
        if (simpleTimeZone2 != null) {
            if (simpleTimeZone2.useDaylightTime()) {
                TimeZoneRule[] timeZoneRules = this.finalZoneWithStartYear.getTimeZoneRules();
                timeZoneRuleArr[i2] = timeZoneRules[1];
                timeZoneRuleArr[i2 + 1] = timeZoneRules[2];
            } else {
                timeZoneRuleArr[i2] = new TimeArrayTimeZoneRule(getID() + "(STD)", this.finalZone.getRawOffset(), 0, new long[]{(long) this.finalStartMillis}, 2);
            }
        }
        return timeZoneRuleArr;
    }

    private synchronized void initTransitionRules() {
        long j;
        TimeZoneRule timeZoneRule;
        if (!this.transitionRulesInitialized) {
            TimeZoneRule timeZoneRule2 = null;
            this.initialRule = null;
            this.firstTZTransition = null;
            this.firstFinalTZTransition = null;
            this.historicRules = null;
            this.firstTZTransitionIdx = 0;
            this.finalZoneWithStartYear = null;
            String str = getID() + "(STD)";
            String str2 = getID() + "(DST)";
            int initialRawOffset = initialRawOffset() * 1000;
            int initialDstOffset = initialDstOffset() * 1000;
            this.initialRule = new InitialTimeZoneRule(initialDstOffset == 0 ? str : str2, initialRawOffset, initialDstOffset);
            if (this.transitionCount > 0) {
                int i = 0;
                while (i < this.transitionCount && getInt(this.typeMapData[i]) == 0) {
                    this.firstTZTransitionIdx++;
                    i++;
                }
                if (i != this.transitionCount) {
                    long[] jArr = new long[this.transitionCount];
                    int i2 = 0;
                    while (true) {
                        long j2 = 1000;
                        if (i2 >= this.typeCount) {
                            break;
                        }
                        int i3 = this.firstTZTransitionIdx;
                        int i4 = 0;
                        while (i3 < this.transitionCount) {
                            if (i2 == getInt(this.typeMapData[i3])) {
                                long j3 = this.transitionTimes64[i3] * j2;
                                if (((double) j3) < this.finalStartMillis) {
                                    jArr[i4] = j3;
                                    i4++;
                                }
                            }
                            i3++;
                            j2 = 1000;
                        }
                        if (i4 > 0) {
                            long[] jArr2 = new long[i4];
                            System.arraycopy(jArr, 0, jArr2, 0, i4);
                            int i5 = i2 * 2;
                            int i6 = this.typeOffsets[i5] * 1000;
                            int i7 = this.typeOffsets[i5 + 1] * 1000;
                            if (this.historicRules == null) {
                                this.historicRules = new TimeArrayTimeZoneRule[this.typeCount];
                            }
                            this.historicRules[i2] = new TimeArrayTimeZoneRule(i7 == 0 ? str : str2, i6, i7, jArr2, 2);
                        }
                        i2++;
                    }
                    this.firstTZTransition = new TimeZoneTransition(this.transitionTimes64[this.firstTZTransitionIdx] * 1000, this.initialRule, this.historicRules[getInt(this.typeMapData[this.firstTZTransitionIdx])]);
                }
            }
            if (this.finalZone != null) {
                long j4 = (long) this.finalStartMillis;
                if (this.finalZone.useDaylightTime()) {
                    this.finalZoneWithStartYear = (SimpleTimeZone) this.finalZone.clone();
                    this.finalZoneWithStartYear.setStartYear(this.finalStartYear);
                    TimeZoneTransition nextTransition = this.finalZoneWithStartYear.getNextTransition(j4, DEBUG);
                    timeZoneRule = nextTransition.getTo();
                    j = nextTransition.getTime();
                } else {
                    this.finalZoneWithStartYear = this.finalZone;
                    timeZoneRule = new TimeArrayTimeZoneRule(this.finalZone.getID(), this.finalZone.getRawOffset(), 0, new long[]{j4}, 2);
                    j = j4;
                }
                if (this.transitionCount > 0) {
                    timeZoneRule2 = this.historicRules[getInt(this.typeMapData[this.transitionCount - 1])];
                }
                if (timeZoneRule2 == null) {
                    timeZoneRule2 = this.initialRule;
                }
                this.firstFinalTZTransition = new TimeZoneTransition(j, timeZoneRule2, timeZoneRule);
            }
            this.transitionRulesInitialized = true;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    @Override // android.icu.util.TimeZone
    public boolean isFrozen() {
        return this.isFrozen;
    }

    public TimeZone freeze() {
        this.isFrozen = true;
        return this;
    }

    @Override // android.icu.util.TimeZone
    public TimeZone cloneAsThawed() {
        OlsonTimeZone olsonTimeZone = (OlsonTimeZone) super.cloneAsThawed();
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            simpleTimeZone.setID(getID());
            olsonTimeZone.finalZone = (SimpleTimeZone) this.finalZone.clone();
        }
        olsonTimeZone.isFrozen = DEBUG;
        return olsonTimeZone;
    }
}
