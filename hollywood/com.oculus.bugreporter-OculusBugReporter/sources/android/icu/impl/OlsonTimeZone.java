package android.icu.impl;

import android.icu.util.AnnualTimeZoneRule;
import android.icu.util.BasicTimeZone;
import android.icu.util.DateTimeRule;
import android.icu.util.InitialTimeZoneRule;
import android.icu.util.SimpleTimeZone;
import android.icu.util.TimeArrayTimeZoneRule;
import android.icu.util.TimeZone;
import android.icu.util.TimeZoneRule;
import android.icu.util.TimeZoneTransition;
import android.icu.util.UResourceBundle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.MissingResourceException;

public class OlsonTimeZone extends BasicTimeZone {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = ICUDebug.enabled("olson");
    private static final int MAX_OFFSET_SECONDS = 86400;
    private static final int SECONDS_PER_DAY = 86400;
    private static final String ZONEINFORES = "zoneinfo64";
    private static final int currentSerialVersion = 1;
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
    private volatile transient boolean isFrozen = false;
    private int serialVersionOnStream = 1;
    private int transitionCount;
    private transient boolean transitionRulesInitialized;
    private long[] transitionTimes64;
    private int typeCount;
    private byte[] typeMapData;
    private int[] typeOffsets;

    @Override // android.icu.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
        if (month >= 0 && month <= 11) {
            return getOffset(era, year, month, day, dayOfWeek, milliseconds, Grego.monthLength(year, month));
        }
        throw new IllegalArgumentException("Month is not in the legal range: " + month);
    }

    public int getOffset(int era, int year, int month, int dom, int dow, int millis, int monthLength) {
        int year2;
        if (era == 1 || era == 0) {
            if (month >= 0 && month <= 11 && dom >= 1 && dom <= monthLength && dow >= 1 && dow <= 7 && millis >= 0 && millis < 86400000 && monthLength >= 28 && monthLength <= 31) {
                if (era == 0) {
                    year2 = -year;
                } else {
                    year2 = year;
                }
                SimpleTimeZone simpleTimeZone = this.finalZone;
                if (simpleTimeZone != null && year2 >= this.finalStartYear) {
                    return simpleTimeZone.getOffset(era, year2, month, dom, dow, millis);
                }
                int[] offsets = new int[2];
                getHistoricalOffset((Grego.fieldsToDay(year2, month, dom) * 86400000) + ((long) millis), true, 3, 1, offsets);
                return offsets[0] + offsets[1];
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        int sav;
        DateTimeRule end;
        DateTimeRule start;
        TimeZoneTransition tzt;
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify a frozen OlsonTimeZone instance.");
        } else if (getRawOffset() != offsetMillis) {
            long current = System.currentTimeMillis();
            if (((double) current) < this.finalStartMillis) {
                SimpleTimeZone stz = new SimpleTimeZone(offsetMillis, getID());
                boolean bDst = useDaylightTime();
                if (bDst) {
                    TimeZoneRule[] currentRules = getSimpleTimeZoneRulesNear(current);
                    if (!(currentRules.length == 3 || (tzt = getPreviousTransition(current, false)) == null)) {
                        currentRules = getSimpleTimeZoneRulesNear(tzt.getTime() - 1);
                    }
                    if (currentRules.length != 3 || !(currentRules[1] instanceof AnnualTimeZoneRule) || !(currentRules[2] instanceof AnnualTimeZoneRule)) {
                        stz.setStartRule(0, 1, 0);
                        stz.setEndRule(11, 31, 86399999);
                    } else {
                        AnnualTimeZoneRule r1 = (AnnualTimeZoneRule) currentRules[1];
                        AnnualTimeZoneRule r2 = (AnnualTimeZoneRule) currentRules[2];
                        int offset1 = r1.getRawOffset() + r1.getDSTSavings();
                        int offset2 = r2.getRawOffset() + r2.getDSTSavings();
                        if (offset1 > offset2) {
                            start = r1.getRule();
                            end = r2.getRule();
                            sav = offset1 - offset2;
                        } else {
                            start = r2.getRule();
                            end = r1.getRule();
                            sav = offset2 - offset1;
                        }
                        stz.setStartRule(start.getRuleMonth(), start.getRuleWeekInMonth(), start.getRuleDayOfWeek(), start.getRuleMillisInDay());
                        stz.setEndRule(end.getRuleMonth(), end.getRuleWeekInMonth(), end.getRuleDayOfWeek(), end.getRuleMillisInDay());
                        stz.setDSTSavings(sav);
                    }
                }
                int[] fields = Grego.timeToFields(current, null);
                this.finalStartYear = fields[0];
                this.finalStartMillis = (double) Grego.fieldsToDay(fields[0], 0, 1);
                if (bDst) {
                    stz.setStartYear(this.finalStartYear);
                }
                this.finalZone = stz;
            } else {
                this.finalZone.setRawOffset(offsetMillis);
            }
            this.transitionRulesInitialized = false;
        }
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long date, boolean local, int[] offsets) {
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone == null || ((double) date) < this.finalStartMillis) {
            getHistoricalOffset(date, local, 4, 12, offsets);
        } else {
            simpleTimeZone.getOffset(date, local, offsets);
        }
    }

    @Override // android.icu.util.BasicTimeZone
    public void getOffsetFromLocal(long date, int nonExistingTimeOpt, int duplicatedTimeOpt, int[] offsets) {
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone == null || ((double) date) < this.finalStartMillis) {
            getHistoricalOffset(date, true, nonExistingTimeOpt, duplicatedTimeOpt, offsets);
        } else {
            simpleTimeZone.getOffsetFromLocal(date, nonExistingTimeOpt, duplicatedTimeOpt, offsets);
        }
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        int[] ret = new int[2];
        getOffset(System.currentTimeMillis(), false, ret);
        return ret[0];
    }

    @Override // android.icu.util.TimeZone
    public boolean useDaylightTime() {
        long current = System.currentTimeMillis();
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null && ((double) current) >= this.finalStartMillis) {
            return simpleTimeZone != null && simpleTimeZone.useDaylightTime();
        }
        int[] fields = Grego.timeToFields(current, null);
        long start = Grego.fieldsToDay(fields[0], 0, 1) * 86400;
        long limit = Grego.fieldsToDay(fields[0] + 1, 0, 1) * 86400;
        for (int i = 0; i < this.transitionCount; i++) {
            long[] jArr = this.transitionTimes64;
            if (jArr[i] >= limit) {
                break;
            } else if ((jArr[i] >= start && dstOffsetAt(i) != 0) || (this.transitionTimes64[i] > start && i > 0 && dstOffsetAt(i - 1) != 0)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.icu.util.TimeZone
    public boolean observesDaylightTime() {
        long current = System.currentTimeMillis();
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            if (simpleTimeZone.useDaylightTime()) {
                return true;
            }
            if (((double) current) >= this.finalStartMillis) {
                return false;
            }
        }
        long currentSec = Grego.floorDivide(current, 1000);
        int trsIdx = this.transitionCount - 1;
        if (dstOffsetAt(trsIdx) != 0) {
            return true;
        }
        while (trsIdx >= 0 && this.transitionTimes64[trsIdx] > currentSec) {
            if (dstOffsetAt(trsIdx - 1) != 0) {
                return true;
            }
            trsIdx--;
        }
        return false;
    }

    @Override // android.icu.util.TimeZone
    public int getDSTSavings() {
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            return simpleTimeZone.getDSTSavings();
        }
        return super.getDSTSavings();
    }

    @Override // android.icu.util.TimeZone
    public boolean inDaylightTime(Date date) {
        int[] temp = new int[2];
        getOffset(date.getTime(), false, temp);
        if (temp[1] != 0) {
            return true;
        }
        return false;
    }

    @Override // android.icu.util.TimeZone
    public boolean hasSameRules(TimeZone other) {
        if (this == other) {
            return true;
        }
        if (!super.hasSameRules(other) || !(other instanceof OlsonTimeZone)) {
            return false;
        }
        OlsonTimeZone o = (OlsonTimeZone) other;
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            SimpleTimeZone simpleTimeZone2 = o.finalZone;
            if (simpleTimeZone2 == null || this.finalStartYear != o.finalStartYear || !simpleTimeZone.hasSameRules(simpleTimeZone2)) {
                return false;
            }
        } else if (o.finalZone != null) {
            return false;
        }
        if (this.transitionCount != o.transitionCount || !Arrays.equals(this.transitionTimes64, o.transitionTimes64) || this.typeCount != o.typeCount || !Arrays.equals(this.typeMapData, o.typeMapData) || !Arrays.equals(this.typeOffsets, o.typeOffsets)) {
            return false;
        }
        return true;
    }

    public String getCanonicalID() {
        if (this.canonicalID == null) {
            synchronized (this) {
                if (this.canonicalID == null) {
                    this.canonicalID = getCanonicalID(getID());
                    if (this.canonicalID == null) {
                        this.canonicalID = getID();
                    }
                }
            }
        }
        return this.canonicalID;
    }

    private void constructEmpty() {
        this.transitionCount = 0;
        this.transitionTimes64 = null;
        this.typeMapData = null;
        this.typeCount = 1;
        this.typeOffsets = new int[]{0, 0};
        this.finalZone = null;
        this.finalStartYear = Integer.MAX_VALUE;
        this.finalStartMillis = Double.MAX_VALUE;
        this.transitionRulesInitialized = false;
    }

    public OlsonTimeZone(UResourceBundle top, UResourceBundle res, String id) {
        super(id);
        construct(top, res);
    }

    private void construct(UResourceBundle top, UResourceBundle res) {
        String str;
        String str2;
        SimpleTimeZone simpleTimeZone;
        String str3 = "Invalid Format";
        if (top == null || res == null) {
            throw new IllegalArgumentException();
        }
        if (DEBUG) {
            System.out.println("OlsonTimeZone(" + res.getKey() + ")");
        }
        int[] transPost32 = null;
        int[] trans32 = null;
        int[] transPre32 = null;
        this.transitionCount = 0;
        try {
            transPre32 = res.get("transPre32").getIntVector();
            if (transPre32.length % 2 == 0) {
                this.transitionCount += transPre32.length / 2;
                try {
                    trans32 = res.get("trans").getIntVector();
                    this.transitionCount += trans32.length;
                } catch (MissingResourceException e) {
                }
                try {
                    transPost32 = res.get("transPost32").getIntVector();
                    if (transPost32.length % 2 == 0) {
                        this.transitionCount += transPost32.length / 2;
                        int i = this.transitionCount;
                        if (i > 0) {
                            this.transitionTimes64 = new long[i];
                            int idx = 0;
                            if (transPre32 != null) {
                                int i2 = 0;
                                for (int i3 = 2; i2 < transPre32.length / i3; i3 = 2) {
                                    this.transitionTimes64[idx] = (((long) transPre32[(i2 * 2) + 1]) & 4294967295L) | ((((long) transPre32[i2 * 2]) & 4294967295L) << 32);
                                    i2++;
                                    idx++;
                                    str3 = str3;
                                }
                                str = str3;
                            } else {
                                str = str3;
                            }
                            if (trans32 != null) {
                                int i4 = 0;
                                while (i4 < trans32.length) {
                                    this.transitionTimes64[idx] = (long) trans32[i4];
                                    i4++;
                                    idx++;
                                }
                            }
                            if (transPost32 != null) {
                                int i5 = 0;
                                while (i5 < transPost32.length / 2) {
                                    this.transitionTimes64[idx] = (((long) transPost32[(i5 * 2) + 1]) & 4294967295L) | ((((long) transPost32[i5 * 2]) & 4294967295L) << 32);
                                    i5++;
                                    idx++;
                                    trans32 = trans32;
                                    transPost32 = transPost32;
                                }
                            }
                        } else {
                            str = str3;
                            this.transitionTimes64 = null;
                        }
                        this.typeOffsets = res.get("typeOffsets").getIntVector();
                        int[] iArr = this.typeOffsets;
                        if (iArr.length < 2 || iArr.length > 32766 || iArr.length % 2 != 0) {
                            throw new IllegalArgumentException(str);
                        }
                        this.typeCount = iArr.length / 2;
                        if (this.transitionCount > 0) {
                            this.typeMapData = res.get("typeMap").getBinary(null);
                            byte[] bArr = this.typeMapData;
                            if (bArr == null || bArr.length != this.transitionCount) {
                                throw new IllegalArgumentException(str);
                            }
                            str2 = str;
                            simpleTimeZone = null;
                        } else {
                            str2 = str;
                            simpleTimeZone = null;
                            this.typeMapData = null;
                        }
                        this.finalZone = simpleTimeZone;
                        this.finalStartYear = Integer.MAX_VALUE;
                        this.finalStartMillis = Double.MAX_VALUE;
                        try {
                            String ruleID = res.getString("finalRule");
                            int ruleRaw = res.get("finalRaw").getInt() * 1000;
                            int[] ruleData = loadRule(top, ruleID).getIntVector();
                            if (ruleData == null || ruleData.length != 11) {
                                throw new IllegalArgumentException(str2);
                            }
                            this.finalZone = new SimpleTimeZone(ruleRaw, "", ruleData[0], ruleData[1], ruleData[2], ruleData[3] * 1000, ruleData[4], ruleData[5], ruleData[6], ruleData[7], ruleData[8] * 1000, ruleData[9], ruleData[10] * 1000);
                            this.finalStartYear = res.get("finalYear").getInt();
                            this.finalStartMillis = (double) (Grego.fieldsToDay(this.finalStartYear, 0, 1) * 86400000);
                        } catch (MissingResourceException e2) {
                            if (0 != 0) {
                                throw new IllegalArgumentException(str2);
                            }
                        }
                    } else {
                        throw new IllegalArgumentException(str3);
                    }
                } catch (MissingResourceException e3) {
                }
            } else {
                throw new IllegalArgumentException(str3);
            }
        } catch (MissingResourceException e4) {
        }
    }

    public OlsonTimeZone(String id) {
        super(id);
        UResourceBundle top = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, ZONEINFORES, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        construct(top, ZoneMeta.openOlsonResource(top, id));
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            simpleTimeZone.setID(id);
        }
    }

    @Override // android.icu.util.TimeZone
    public void setID(String id) {
        if (!isFrozen()) {
            if (this.canonicalID == null) {
                this.canonicalID = getCanonicalID(getID());
                if (this.canonicalID == null) {
                    this.canonicalID = getID();
                }
            }
            SimpleTimeZone simpleTimeZone = this.finalZone;
            if (simpleTimeZone != null) {
                simpleTimeZone.setID(id);
            }
            super.setID(id);
            this.transitionRulesInitialized = false;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen OlsonTimeZone instance.");
    }

    private void getHistoricalOffset(long date, boolean local, int NonExistingTimeOpt, int DuplicatedTimeOpt, int[] offsets) {
        boolean z = false;
        boolean z2 = true;
        if (this.transitionCount != 0) {
            long sec = Grego.floorDivide(date, 1000);
            if (local || sec >= this.transitionTimes64[0]) {
                int transIdx = this.transitionCount - 1;
                while (transIdx >= 0) {
                    long transition = this.transitionTimes64[transIdx];
                    if (local && sec >= transition - 86400) {
                        int offsetBefore = zoneOffsetAt(transIdx - 1);
                        boolean dstBefore = dstOffsetAt(transIdx + -1) != 0 ? z2 : z;
                        int offsetAfter = zoneOffsetAt(transIdx);
                        boolean dstAfter = dstOffsetAt(transIdx) != 0 ? z2 : z;
                        boolean dstToStd = (!dstBefore || dstAfter) ? z : z2;
                        boolean stdToDst = (dstBefore || !dstAfter) ? z : z2;
                        transition = offsetAfter - offsetBefore >= 0 ? (((NonExistingTimeOpt & 3) != z2 || !dstToStd) && ((NonExistingTimeOpt & 3) != 3 || !stdToDst)) ? (((NonExistingTimeOpt & 3) != 1 || !stdToDst) && ((NonExistingTimeOpt & 3) != 3 || !dstToStd)) ? (NonExistingTimeOpt & 12) == 12 ? transition + ((long) offsetBefore) : transition + ((long) offsetAfter) : transition + ((long) offsetAfter) : transition + ((long) offsetBefore) : (((DuplicatedTimeOpt & 3) != 1 || !dstToStd) && ((DuplicatedTimeOpt & 3) != 3 || !stdToDst)) ? (((DuplicatedTimeOpt & 3) != 1 || !stdToDst) && ((DuplicatedTimeOpt & 3) != 3 || !dstToStd)) ? (DuplicatedTimeOpt & 12) == 4 ? transition + ((long) offsetBefore) : transition + ((long) offsetAfter) : transition + ((long) offsetBefore) : transition + ((long) offsetAfter);
                    }
                    if (sec >= transition) {
                        break;
                    }
                    transIdx--;
                    z = false;
                    z2 = true;
                }
                offsets[0] = rawOffsetAt(transIdx) * 1000;
                offsets[1] = dstOffsetAt(transIdx) * 1000;
                return;
            }
            offsets[0] = initialRawOffset() * 1000;
            offsets[1] = initialDstOffset() * 1000;
            return;
        }
        offsets[0] = initialRawOffset() * 1000;
        offsets[1] = initialDstOffset() * 1000;
    }

    private int getInt(byte val) {
        return val & 255;
    }

    private int zoneOffsetAt(int transIdx) {
        int typeIdx = transIdx >= 0 ? getInt(this.typeMapData[transIdx]) * 2 : 0;
        int[] iArr = this.typeOffsets;
        return iArr[typeIdx] + iArr[typeIdx + 1];
    }

    private int rawOffsetAt(int transIdx) {
        return this.typeOffsets[transIdx >= 0 ? getInt(this.typeMapData[transIdx]) * 2 : 0];
    }

    private int dstOffsetAt(int transIdx) {
        return this.typeOffsets[(transIdx >= 0 ? getInt(this.typeMapData[transIdx]) * 2 : 0) + 1];
    }

    private int initialRawOffset() {
        return this.typeOffsets[0];
    }

    private int initialDstOffset() {
        return this.typeOffsets[1];
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(super.toString());
        buf.append('[');
        buf.append("transitionCount=" + this.transitionCount);
        buf.append(",typeCount=" + this.typeCount);
        buf.append(",transitionTimes=");
        if (this.transitionTimes64 != null) {
            buf.append('[');
            for (int i = 0; i < this.transitionTimes64.length; i++) {
                if (i > 0) {
                    buf.append(',');
                }
                buf.append(Long.toString(this.transitionTimes64[i]));
            }
            buf.append(']');
        } else {
            buf.append("null");
        }
        buf.append(",typeOffsets=");
        if (this.typeOffsets != null) {
            buf.append('[');
            for (int i2 = 0; i2 < this.typeOffsets.length; i2++) {
                if (i2 > 0) {
                    buf.append(',');
                }
                buf.append(Integer.toString(this.typeOffsets[i2]));
            }
            buf.append(']');
        } else {
            buf.append("null");
        }
        buf.append(",typeMapData=");
        if (this.typeMapData != null) {
            buf.append('[');
            for (int i3 = 0; i3 < this.typeMapData.length; i3++) {
                if (i3 > 0) {
                    buf.append(',');
                }
                buf.append(Byte.toString(this.typeMapData[i3]));
            }
        } else {
            buf.append("null");
        }
        buf.append(",finalStartYear=" + this.finalStartYear);
        buf.append(",finalStartMillis=" + this.finalStartMillis);
        buf.append(",finalZone=" + ((Object) this.finalZone));
        buf.append(']');
        return buf.toString();
    }

    private static UResourceBundle loadRule(UResourceBundle top, String ruleid) {
        return top.get("Rules").get(ruleid);
    }

    @Override // android.icu.util.TimeZone
    public boolean equals(Object obj) {
        SimpleTimeZone simpleTimeZone;
        SimpleTimeZone simpleTimeZone2;
        if (!super.equals(obj)) {
            return false;
        }
        OlsonTimeZone z = (OlsonTimeZone) obj;
        if (!Utility.arrayEquals(this.typeMapData, (Object) z.typeMapData)) {
            if (this.finalStartYear != z.finalStartYear) {
                return false;
            }
            if (!(this.finalZone == null && z.finalZone == null) && ((simpleTimeZone = this.finalZone) == null || (simpleTimeZone2 = z.finalZone) == null || !simpleTimeZone.equals(simpleTimeZone2) || this.transitionCount != z.transitionCount || this.typeCount != z.typeCount || !Utility.arrayEquals((Object) this.transitionTimes64, (Object) z.transitionTimes64) || !Utility.arrayEquals(this.typeOffsets, (Object) z.typeOffsets) || !Utility.arrayEquals(this.typeMapData, (Object) z.typeMapData))) {
                return false;
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
        int ret = (int) (j ^ ((doubleToLongBits + ((long) (simpleTimeZone == null ? 0 : simpleTimeZone.hashCode()))) + ((long) super.hashCode())));
        if (this.transitionTimes64 != null) {
            int i6 = 0;
            while (true) {
                long[] jArr = this.transitionTimes64;
                if (i6 >= jArr.length) {
                    break;
                }
                ret = (int) (((long) ret) + (jArr[i6] ^ (jArr[i6] >>> 8)));
                i6++;
            }
        }
        int i7 = 0;
        while (true) {
            int[] iArr = this.typeOffsets;
            if (i7 >= iArr.length) {
                break;
            }
            ret += (iArr[i7] >>> 8) ^ iArr[i7];
            i7++;
        }
        if (this.typeMapData != null) {
            int i8 = 0;
            while (true) {
                byte[] bArr = this.typeMapData;
                if (i8 >= bArr.length) {
                    break;
                }
                ret += bArr[i8] & 255;
                i8++;
            }
        }
        return ret;
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getNextTransition(long base, boolean inclusive) {
        initTransitionRules();
        if (this.finalZone != null) {
            if (inclusive && base == this.firstFinalTZTransition.getTime()) {
                return this.firstFinalTZTransition;
            }
            if (base >= this.firstFinalTZTransition.getTime()) {
                if (this.finalZone.useDaylightTime()) {
                    return this.finalZoneWithStartYear.getNextTransition(base, inclusive);
                }
                return null;
            }
        }
        if (this.historicRules == null) {
            return null;
        }
        int ttidx = this.transitionCount;
        while (true) {
            ttidx--;
            if (ttidx < this.firstTZTransitionIdx) {
                break;
            }
            long t = this.transitionTimes64[ttidx] * 1000;
            if (base > t || (!inclusive && base == t)) {
                break;
            }
        }
        if (ttidx == this.transitionCount - 1) {
            return this.firstFinalTZTransition;
        }
        if (ttidx < this.firstTZTransitionIdx) {
            return this.firstTZTransition;
        }
        TimeZoneRule to = this.historicRules[getInt(this.typeMapData[ttidx + 1])];
        TimeZoneRule from = this.historicRules[getInt(this.typeMapData[ttidx])];
        long startTime = this.transitionTimes64[ttidx + 1] * 1000;
        if (from.getName().equals(to.getName()) && from.getRawOffset() == to.getRawOffset() && from.getDSTSavings() == to.getDSTSavings()) {
            return getNextTransition(startTime, false);
        }
        return new TimeZoneTransition(startTime, from, to);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getPreviousTransition(long base, boolean inclusive) {
        initTransitionRules();
        if (this.finalZone != null) {
            if (inclusive && base == this.firstFinalTZTransition.getTime()) {
                return this.firstFinalTZTransition;
            }
            if (base > this.firstFinalTZTransition.getTime()) {
                if (this.finalZone.useDaylightTime()) {
                    return this.finalZoneWithStartYear.getPreviousTransition(base, inclusive);
                }
                return this.firstFinalTZTransition;
            }
        }
        if (this.historicRules == null) {
            return null;
        }
        int ttidx = this.transitionCount;
        while (true) {
            ttidx--;
            if (ttidx < this.firstTZTransitionIdx) {
                break;
            }
            long t = this.transitionTimes64[ttidx] * 1000;
            if (base > t || (inclusive && base == t)) {
                break;
            }
        }
        int i = this.firstTZTransitionIdx;
        if (ttidx < i) {
            return null;
        }
        if (ttidx == i) {
            return this.firstTZTransition;
        }
        TimeZoneRule to = this.historicRules[getInt(this.typeMapData[ttidx])];
        TimeZoneRule from = this.historicRules[getInt(this.typeMapData[ttidx - 1])];
        long startTime = this.transitionTimes64[ttidx] * 1000;
        if (from.getName().equals(to.getName()) && from.getRawOffset() == to.getRawOffset() && from.getDSTSavings() == to.getDSTSavings()) {
            return getPreviousTransition(startTime, false);
        }
        return new TimeZoneTransition(startTime, from, to);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules() {
        initTransitionRules();
        int size = 1;
        if (this.historicRules != null) {
            int i = 0;
            while (true) {
                TimeArrayTimeZoneRule[] timeArrayTimeZoneRuleArr = this.historicRules;
                if (i >= timeArrayTimeZoneRuleArr.length) {
                    break;
                }
                if (timeArrayTimeZoneRuleArr[i] != null) {
                    size++;
                }
                i++;
            }
        }
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            if (simpleTimeZone.useDaylightTime()) {
                size += 2;
            } else {
                size++;
            }
        }
        TimeZoneRule[] rules = new TimeZoneRule[size];
        int idx = 0 + 1;
        rules[0] = this.initialRule;
        if (this.historicRules != null) {
            int i2 = 0;
            while (true) {
                TimeArrayTimeZoneRule[] timeArrayTimeZoneRuleArr2 = this.historicRules;
                if (i2 >= timeArrayTimeZoneRuleArr2.length) {
                    break;
                }
                if (timeArrayTimeZoneRuleArr2[i2] != null) {
                    rules[idx] = timeArrayTimeZoneRuleArr2[i2];
                    idx++;
                }
                i2++;
            }
        }
        SimpleTimeZone simpleTimeZone2 = this.finalZone;
        if (simpleTimeZone2 != null) {
            if (simpleTimeZone2.useDaylightTime()) {
                TimeZoneRule[] stzr = this.finalZoneWithStartYear.getTimeZoneRules();
                int idx2 = idx + 1;
                rules[idx] = stzr[1];
                int i3 = idx2 + 1;
                rules[idx2] = stzr[2];
            } else {
                int i4 = idx + 1;
                rules[idx] = new TimeArrayTimeZoneRule(getID() + "(STD)", this.finalZone.getRawOffset(), 0, new long[]{(long) this.finalStartMillis}, 2);
            }
        }
        return rules;
    }

    private synchronized void initTransitionRules() {
        TimeZoneRule firstFinalRule;
        String dstName;
        if (!this.transitionRulesInitialized) {
            this.initialRule = null;
            this.firstTZTransition = null;
            this.firstFinalTZTransition = null;
            this.historicRules = null;
            this.firstTZTransitionIdx = 0;
            this.finalZoneWithStartYear = null;
            String stdName = getID() + "(STD)";
            String dstName2 = getID() + "(DST)";
            int raw = initialRawOffset() * 1000;
            int dst = initialDstOffset() * 1000;
            this.initialRule = new InitialTimeZoneRule(dst == 0 ? stdName : dstName2, raw, dst);
            if (this.transitionCount > 0) {
                int transitionIdx = 0;
                while (transitionIdx < this.transitionCount && getInt(this.typeMapData[transitionIdx]) == 0) {
                    this.firstTZTransitionIdx++;
                    transitionIdx++;
                }
                if (transitionIdx != this.transitionCount) {
                    long[] times = new long[this.transitionCount];
                    int typeIdx = 0;
                    while (true) {
                        long j = 1000;
                        if (typeIdx >= this.typeCount) {
                            break;
                        }
                        int nTimes = 0;
                        int transitionIdx2 = this.firstTZTransitionIdx;
                        while (transitionIdx2 < this.transitionCount) {
                            if (typeIdx == getInt(this.typeMapData[transitionIdx2])) {
                                long tt = this.transitionTimes64[transitionIdx2] * j;
                                dstName = dstName2;
                                if (((double) tt) < this.finalStartMillis) {
                                    times[nTimes] = tt;
                                    nTimes++;
                                }
                            } else {
                                dstName = dstName2;
                            }
                            transitionIdx2++;
                            dstName2 = dstName;
                            j = 1000;
                        }
                        if (nTimes > 0) {
                            long[] startTimes = new long[nTimes];
                            System.arraycopy((Object) times, 0, (Object) startTimes, 0, nTimes);
                            int raw2 = this.typeOffsets[typeIdx * 2] * 1000;
                            int dst2 = this.typeOffsets[(typeIdx * 2) + 1] * 1000;
                            if (this.historicRules == null) {
                                this.historicRules = new TimeArrayTimeZoneRule[this.typeCount];
                            }
                            this.historicRules[typeIdx] = new TimeArrayTimeZoneRule(dst2 == 0 ? stdName : dstName2, raw2, dst2, startTimes, 2);
                        }
                        typeIdx++;
                        dstName2 = dstName2;
                    }
                    this.firstTZTransition = new TimeZoneTransition(this.transitionTimes64[this.firstTZTransitionIdx] * 1000, this.initialRule, this.historicRules[getInt(this.typeMapData[this.firstTZTransitionIdx])]);
                }
            }
            if (this.finalZone != null) {
                long startTime = (long) this.finalStartMillis;
                if (this.finalZone.useDaylightTime()) {
                    this.finalZoneWithStartYear = (SimpleTimeZone) this.finalZone.clone();
                    this.finalZoneWithStartYear.setStartYear(this.finalStartYear);
                    TimeZoneTransition tzt = this.finalZoneWithStartYear.getNextTransition(startTime, false);
                    firstFinalRule = tzt.getTo();
                    startTime = tzt.getTime();
                } else {
                    this.finalZoneWithStartYear = this.finalZone;
                    firstFinalRule = new TimeArrayTimeZoneRule(this.finalZone.getID(), this.finalZone.getRawOffset(), 0, new long[]{startTime}, 2);
                }
                TimeZoneRule prevRule = null;
                if (this.transitionCount > 0) {
                    prevRule = this.historicRules[getInt(this.typeMapData[this.transitionCount - 1])];
                }
                if (prevRule == null) {
                    prevRule = this.initialRule;
                }
                this.firstFinalTZTransition = new TimeZoneTransition(startTime, prevRule, firstFinalRule);
            }
            this.transitionRulesInitialized = true;
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (this.serialVersionOnStream < 1) {
            boolean initialized = false;
            String tzid = getID();
            if (tzid != null) {
                try {
                    UResourceBundle top = UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, ZONEINFORES, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
                    construct(top, ZoneMeta.openOlsonResource(top, tzid));
                    if (this.finalZone != null) {
                        this.finalZone.setID(tzid);
                    }
                    initialized = true;
                } catch (Exception e) {
                }
            }
            if (!initialized) {
                constructEmpty();
            }
        }
        this.transitionRulesInitialized = false;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone
    public boolean isFrozen() {
        return this.isFrozen;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone freeze() {
        this.isFrozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone cloneAsThawed() {
        OlsonTimeZone tz = (OlsonTimeZone) super.cloneAsThawed();
        SimpleTimeZone simpleTimeZone = this.finalZone;
        if (simpleTimeZone != null) {
            simpleTimeZone.setID(getID());
            tz.finalZone = (SimpleTimeZone) this.finalZone.clone();
        }
        tz.isFrozen = false;
        return tz;
    }
}
