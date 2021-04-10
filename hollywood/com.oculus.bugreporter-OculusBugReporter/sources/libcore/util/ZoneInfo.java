package libcore.util;

import android.icu.lang.UCharacter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import libcore.io.BufferIterator;

public final class ZoneInfo extends TimeZone {
    private static final int[] LEAP = {0, 31, 60, 91, 121, 152, 182, 213, 244, UCharacter.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_F_ID, HttpURLConnection.HTTP_USE_PROXY, 335};
    private static final long MILLISECONDS_PER_400_YEARS = 12622780800000L;
    private static final long MILLISECONDS_PER_DAY = 86400000;
    private static final int[] NORMAL = {0, 31, 59, 90, 120, 151, 181, 212, 243, UCharacter.UnicodeBlock.TANGUT_COMPONENTS_ID, HttpURLConnection.HTTP_NOT_MODIFIED, 334};
    private static final long UNIX_OFFSET = 62167219200000L;
    static final long serialVersionUID = -4598738130123921552L;
    private int mDstSavings;
    private final int mEarliestRawOffset;
    private final byte[] mIsDsts;
    private final int[] mOffsets;
    private int mRawOffset;
    private final long[] mTransitions;
    private final byte[] mTypes;
    private final boolean mUseDst;

    public static ZoneInfo readTimeZone(String id, BufferIterator it, long currentTimeMillis) throws IOException {
        int tzh_magic = it.readInt();
        if (tzh_magic == 1415211366) {
            it.skip(28);
            int tzh_timecnt = it.readInt();
            int MAX_TRANSITIONS = Types.JAVA_OBJECT;
            if (tzh_timecnt < 0 || tzh_timecnt > 2000) {
                throw new IOException("Timezone id=" + id + " has an invalid number of transitions=" + tzh_timecnt);
            }
            int tzh_typecnt = it.readInt();
            if (tzh_typecnt < 1) {
                throw new IOException("ZoneInfo requires at least one type to be provided for each timezone but could not find one for '" + id + "'");
            } else if (tzh_typecnt <= 256) {
                it.skip(4);
                int[] transitions32 = new int[tzh_timecnt];
                it.readIntArray(transitions32, 0, transitions32.length);
                long[] transitions64 = new long[tzh_timecnt];
                for (int i = 0; i < tzh_timecnt; i++) {
                    transitions64[i] = (long) transitions32[i];
                    if (i > 0 && transitions64[i] <= transitions64[i - 1]) {
                        throw new IOException(id + " transition at " + i + " is not sorted correctly, is " + transitions64[i] + ", previous is " + transitions64[i - 1]);
                    }
                }
                byte[] type = new byte[tzh_timecnt];
                it.readByteArray(type, 0, type.length);
                for (int i2 = 0; i2 < type.length; i2++) {
                    int typeIndex = type[i2] & 255;
                    if (typeIndex >= tzh_typecnt) {
                        throw new IOException(id + " type at " + i2 + " is not < " + tzh_typecnt + ", is " + typeIndex);
                    }
                }
                int[] gmtOffsets = new int[tzh_typecnt];
                byte[] isDsts = new byte[tzh_typecnt];
                int i3 = 0;
                while (i3 < tzh_typecnt) {
                    gmtOffsets[i3] = it.readInt();
                    byte isDst = it.readByte();
                    if (isDst == 0 || isDst == 1) {
                        isDsts[i3] = isDst;
                        it.skip(1);
                        i3++;
                        MAX_TRANSITIONS = MAX_TRANSITIONS;
                    } else {
                        throw new IOException(id + " dst at " + i3 + " is not 0 or 1, is " + ((int) isDst));
                    }
                }
                return new ZoneInfo(id, transitions64, type, gmtOffsets, isDsts, currentTimeMillis);
            } else {
                throw new IOException("Timezone with id " + id + " has too many types=" + tzh_typecnt);
            }
        } else {
            throw new IOException("Timezone id=" + id + " has an invalid header=" + tzh_magic);
        }
    }

    private ZoneInfo(String name, long[] transitions, byte[] types, int[] gmtOffsets, byte[] isDsts, long currentTimeMillis) {
        if (gmtOffsets.length != 0) {
            this.mTransitions = transitions;
            this.mTypes = types;
            this.mIsDsts = isDsts;
            setID(name);
            int lastStdTransitionIndex = -1;
            int lastDstTransitionIndex = -1;
            int i = this.mTransitions.length - 1;
            while (true) {
                if ((lastStdTransitionIndex == -1 || lastDstTransitionIndex == -1) && i >= 0) {
                    int typeIndex = this.mTypes[i] & 255;
                    if (lastStdTransitionIndex == -1 && this.mIsDsts[typeIndex] == 0) {
                        lastStdTransitionIndex = i;
                    }
                    if (lastDstTransitionIndex == -1 && this.mIsDsts[typeIndex] != 0) {
                        lastDstTransitionIndex = i;
                    }
                    i--;
                }
            }
            if (this.mTransitions.length == 0) {
                this.mRawOffset = gmtOffsets[0];
            } else if (lastStdTransitionIndex != -1) {
                this.mRawOffset = gmtOffsets[this.mTypes[lastStdTransitionIndex] & 255];
            } else {
                throw new IllegalStateException("ZoneInfo requires at least one non-DST transition to be provided for each timezone that has at least one transition but could not find one for '" + name + "'");
            }
            if (lastDstTransitionIndex != -1 && this.mTransitions[lastDstTransitionIndex] < roundUpMillisToSeconds(currentTimeMillis)) {
                lastDstTransitionIndex = -1;
            }
            if (lastDstTransitionIndex == -1) {
                this.mDstSavings = 0;
                this.mUseDst = false;
            } else {
                byte[] bArr = this.mTypes;
                this.mDstSavings = (gmtOffsets[bArr[lastDstTransitionIndex] & 255] - gmtOffsets[bArr[lastStdTransitionIndex] & 255]) * 1000;
                this.mUseDst = true;
            }
            int firstStdTypeIndex = -1;
            int i2 = 0;
            while (true) {
                byte[] bArr2 = this.mIsDsts;
                if (i2 >= bArr2.length) {
                    break;
                } else if (bArr2[i2] == 0) {
                    firstStdTypeIndex = i2;
                    break;
                } else {
                    i2++;
                }
            }
            int earliestRawOffset = firstStdTypeIndex != -1 ? gmtOffsets[firstStdTypeIndex] : this.mRawOffset;
            this.mOffsets = gmtOffsets;
            int i3 = 0;
            while (true) {
                int[] iArr = this.mOffsets;
                if (i3 < iArr.length) {
                    iArr[i3] = iArr[i3] - this.mRawOffset;
                    i3++;
                } else {
                    this.mRawOffset *= 1000;
                    this.mEarliestRawOffset = earliestRawOffset * 1000;
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("ZoneInfo requires at least one offset to be provided for each timezone but could not find one for '" + name + "'");
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (!this.mUseDst && this.mDstSavings != 0) {
            this.mDstSavings = 0;
        }
    }

    @Override // java.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
        int year2 = year % 400;
        long calc = (((long) (year / 400)) * MILLISECONDS_PER_400_YEARS) + (((long) year2) * 31536000000L) + (((long) ((year2 + 3) / 4)) * 86400000);
        if (year2 > 0) {
            calc -= ((long) ((year2 - 1) / 100)) * 86400000;
        }
        return getOffset(((((calc + (((long) (year2 == 0 || (year2 % 4 == 0 && year2 % 100 != 0) ? LEAP : NORMAL)[month]) * 86400000)) + (((long) (day - 1)) * 86400000)) + ((long) millis)) - ((long) this.mRawOffset)) - UNIX_OFFSET);
    }

    public int findTransitionIndex(long seconds) {
        int transition = Arrays.binarySearch(this.mTransitions, seconds);
        if (transition >= 0 || (~transition) - 1 >= 0) {
            return transition;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int findOffsetIndexForTimeInSeconds(long seconds) {
        int transition = findTransitionIndex(seconds);
        if (transition < 0) {
            return -1;
        }
        return this.mTypes[transition] & 255;
    }

    /* access modifiers changed from: package-private */
    public int findOffsetIndexForTimeInMilliseconds(long millis) {
        return findOffsetIndexForTimeInSeconds(roundDownMillisToSeconds(millis));
    }

    static long roundDownMillisToSeconds(long millis) {
        if (millis < 0) {
            return (millis - 999) / 1000;
        }
        return millis / 1000;
    }

    static long roundUpMillisToSeconds(long millis) {
        if (millis > 0) {
            return (999 + millis) / 1000;
        }
        return millis / 1000;
    }

    public int getOffsetsByUtcTime(long utcTimeInMillis, int[] offsets) {
        int totalOffset;
        int type;
        int rawOffset;
        int transitionIndex = findTransitionIndex(roundDownMillisToSeconds(utcTimeInMillis));
        if (transitionIndex == -1) {
            rawOffset = this.mEarliestRawOffset;
            type = 0;
            totalOffset = rawOffset;
        } else {
            int type2 = this.mTypes[transitionIndex] & 255;
            totalOffset = this.mRawOffset + (this.mOffsets[type2] * 1000);
            if (this.mIsDsts[type2] == 0) {
                rawOffset = totalOffset;
                type = 0;
            } else {
                int rawOffset2 = -1;
                while (true) {
                    transitionIndex--;
                    if (transitionIndex < 0) {
                        break;
                    }
                    int type3 = this.mTypes[transitionIndex] & 255;
                    if (this.mIsDsts[type3] == 0) {
                        rawOffset2 = this.mRawOffset + (this.mOffsets[type3] * 1000);
                        break;
                    }
                }
                if (rawOffset2 == -1) {
                    rawOffset = this.mEarliestRawOffset;
                } else {
                    rawOffset = rawOffset2;
                }
                type = totalOffset - rawOffset;
            }
        }
        offsets[0] = rawOffset;
        offsets[1] = type;
        return totalOffset;
    }

    @Override // java.util.TimeZone
    public int getOffset(long when) {
        int offsetIndex = findOffsetIndexForTimeInMilliseconds(when);
        if (offsetIndex == -1) {
            return this.mEarliestRawOffset;
        }
        return this.mRawOffset + (this.mOffsets[offsetIndex] * 1000);
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date time) {
        int offsetIndex = findOffsetIndexForTimeInMilliseconds(time.getTime());
        if (offsetIndex != -1 && this.mIsDsts[offsetIndex] == 1) {
            return true;
        }
        return false;
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.mRawOffset;
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int off) {
        this.mRawOffset = off;
    }

    @Override // java.util.TimeZone
    public int getDSTSavings() {
        return this.mDstSavings;
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return this.mUseDst;
    }

    @Override // java.util.TimeZone
    public boolean hasSameRules(TimeZone timeZone) {
        if (!(timeZone instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo other = (ZoneInfo) timeZone;
        boolean z = this.mUseDst;
        if (z != other.mUseDst) {
            return false;
        }
        if (!z) {
            if (this.mRawOffset == other.mRawOffset) {
                return true;
            }
            return false;
        } else if (this.mRawOffset != other.mRawOffset || !Arrays.equals(this.mOffsets, other.mOffsets) || !Arrays.equals(this.mIsDsts, other.mIsDsts) || !Arrays.equals(this.mTypes, other.mTypes) || !Arrays.equals(this.mTransitions, other.mTransitions)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ZoneInfo)) {
            return false;
        }
        ZoneInfo other = (ZoneInfo) obj;
        if (!getID().equals(other.getID()) || !hasSameRules(other)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((1 * 31) + getID().hashCode()) * 31) + Arrays.hashCode(this.mOffsets)) * 31) + Arrays.hashCode(this.mIsDsts)) * 31) + this.mRawOffset) * 31) + Arrays.hashCode(this.mTransitions)) * 31) + Arrays.hashCode(this.mTypes)) * 31) + (this.mUseDst ? 1231 : 1237);
    }

    public String toString() {
        return getClass().getName() + "[id=\"" + getID() + "\",mRawOffset=" + this.mRawOffset + ",mEarliestRawOffset=" + this.mEarliestRawOffset + ",mUseDst=" + this.mUseDst + ",mDstSavings=" + this.mDstSavings + ",transitions=" + this.mTransitions.length + "]";
    }

    @Override // java.util.TimeZone
    public Object clone() {
        return super.clone();
    }

    public static class WallTime {
        private final GregorianCalendar calendar = new GregorianCalendar(0, 0, 0, 0, 0, 0);
        private int gmtOffsetSeconds;
        private int hour;
        private int isDst;
        private int minute;
        private int month;
        private int monthDay;
        private int second;
        private int weekDay;
        private int year;
        private int yearDay;

        public WallTime() {
            this.calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        public void localtime(int timeSeconds, ZoneInfo zoneInfo) {
            int offsetIndex;
            try {
                int offsetSeconds = zoneInfo.mRawOffset / 1000;
                if (zoneInfo.mTransitions.length == 0) {
                    offsetIndex = 0;
                } else {
                    int offsetIndex2 = zoneInfo.findOffsetIndexForTimeInSeconds((long) timeSeconds);
                    if (offsetIndex2 == -1) {
                        offsetSeconds = zoneInfo.mEarliestRawOffset / 1000;
                        offsetIndex = 0;
                    } else {
                        offsetSeconds += zoneInfo.mOffsets[offsetIndex2];
                        offsetIndex = zoneInfo.mIsDsts[offsetIndex2];
                    }
                }
                this.calendar.setTimeInMillis(((long) ZoneInfo.checked32BitAdd((long) timeSeconds, offsetSeconds)) * 1000);
                copyFieldsFromCalendar();
                this.isDst = offsetIndex;
                this.gmtOffsetSeconds = offsetSeconds;
            } catch (CheckedArithmeticException e) {
            }
        }

        public int mktime(ZoneInfo zoneInfo) {
            int i;
            int i2 = this.isDst;
            if (i2 > 0) {
                this.isDst = 1;
                i = 1;
            } else if (i2 < 0) {
                this.isDst = -1;
                i = -1;
            } else {
                i = 0;
            }
            this.isDst = i;
            copyFieldsToCalendar();
            long longWallTimeSeconds = this.calendar.getTimeInMillis() / 1000;
            if (-2147483648L > longWallTimeSeconds || longWallTimeSeconds > 2147483647L) {
                return -1;
            }
            int wallTimeSeconds = (int) longWallTimeSeconds;
            try {
                int rawOffsetSeconds = zoneInfo.mRawOffset / 1000;
                int rawTimeSeconds = ZoneInfo.checked32BitSubtract((long) wallTimeSeconds, rawOffsetSeconds);
                if (zoneInfo.mTransitions.length != 0) {
                    int initialTransitionIndex = zoneInfo.findTransitionIndex((long) rawTimeSeconds);
                    if (this.isDst < 0) {
                        Integer result = doWallTimeSearch(zoneInfo, initialTransitionIndex, wallTimeSeconds, true);
                        if (result == null) {
                            return -1;
                        }
                        return result.intValue();
                    }
                    Integer result2 = doWallTimeSearch(zoneInfo, initialTransitionIndex, wallTimeSeconds, true);
                    if (result2 == null) {
                        result2 = doWallTimeSearch(zoneInfo, initialTransitionIndex, wallTimeSeconds, false);
                    }
                    if (result2 == null) {
                        result2 = -1;
                    }
                    return result2.intValue();
                } else if (this.isDst > 0) {
                    return -1;
                } else {
                    copyFieldsFromCalendar();
                    this.isDst = 0;
                    this.gmtOffsetSeconds = rawOffsetSeconds;
                    return rawTimeSeconds;
                }
            } catch (CheckedArithmeticException e) {
                return -1;
            }
        }

        private Integer tryOffsetAdjustments(ZoneInfo zoneInfo, int oldWallTimeSeconds, OffsetInterval targetInterval, int transitionIndex, int isDstToFind) throws CheckedArithmeticException {
            int[] offsetsToTry = getOffsetsOfType(zoneInfo, transitionIndex, isDstToFind);
            for (int i : offsetsToTry) {
                int targetIntervalOffsetSeconds = targetInterval.getTotalOffsetSeconds();
                int adjustedWallTimeSeconds = ZoneInfo.checked32BitAdd((long) oldWallTimeSeconds, targetIntervalOffsetSeconds - (i + (zoneInfo.mRawOffset / 1000)));
                if (targetInterval.containsWallTime((long) adjustedWallTimeSeconds)) {
                    int returnValue = ZoneInfo.checked32BitSubtract((long) adjustedWallTimeSeconds, targetIntervalOffsetSeconds);
                    this.calendar.setTimeInMillis(((long) adjustedWallTimeSeconds) * 1000);
                    copyFieldsFromCalendar();
                    this.isDst = targetInterval.getIsDst();
                    this.gmtOffsetSeconds = targetIntervalOffsetSeconds;
                    return Integer.valueOf(returnValue);
                }
            }
            return null;
        }

        private static int[] getOffsetsOfType(ZoneInfo zoneInfo, int startIndex, int isDst2) {
            int[] offsets = new int[(zoneInfo.mOffsets.length + 1)];
            boolean[] seen = new boolean[zoneInfo.mOffsets.length];
            int numFound = 0;
            int delta = 0;
            boolean clampTop = false;
            boolean clampBottom = false;
            while (true) {
                delta *= -1;
                if (delta >= 0) {
                    delta++;
                }
                int transitionIndex = startIndex + delta;
                if (delta < 0 && transitionIndex < -1) {
                    clampBottom = true;
                } else if (delta > 0 && transitionIndex >= zoneInfo.mTypes.length) {
                    clampTop = true;
                } else if (transitionIndex != -1) {
                    int type = zoneInfo.mTypes[transitionIndex] & 255;
                    if (!seen[type]) {
                        if (zoneInfo.mIsDsts[type] == isDst2) {
                            offsets[numFound] = zoneInfo.mOffsets[type];
                            numFound++;
                        }
                        seen[type] = true;
                    }
                } else if (isDst2 == 0) {
                    offsets[numFound] = 0;
                    numFound++;
                }
                if (clampTop && clampBottom) {
                    int[] toReturn = new int[numFound];
                    System.arraycopy((Object) offsets, 0, (Object) toReturn, 0, numFound);
                    return toReturn;
                }
            }
        }

        private Integer doWallTimeSearch(ZoneInfo zoneInfo, int initialTransitionIndex, int wallTimeSeconds, boolean mustMatchDst) throws CheckedArithmeticException {
            int transitionIndexDelta;
            OffsetInterval offsetInterval;
            OffsetInterval offsetInterval2;
            int loop = 0;
            boolean clampTop = false;
            boolean clampBottom = false;
            while (true) {
                int transitionIndexDelta2 = (loop + 1) / 2;
                boolean endSearch = true;
                if (loop % 2 == 1) {
                    transitionIndexDelta = transitionIndexDelta2 * -1;
                } else {
                    transitionIndexDelta = transitionIndexDelta2;
                }
                int loop2 = loop + 1;
                if (transitionIndexDelta <= 0 || !clampTop) {
                    if (transitionIndexDelta >= 0 || !clampBottom) {
                        int currentTransitionIndex = initialTransitionIndex + transitionIndexDelta;
                        offsetInterval = OffsetInterval.create(zoneInfo, currentTransitionIndex);
                        if (offsetInterval == null) {
                            boolean clampTop2 = (transitionIndexDelta > 0) | clampTop;
                            if (transitionIndexDelta >= 0) {
                                endSearch = false;
                            }
                            clampTop = clampTop2;
                            clampBottom |= endSearch;
                        } else {
                            if (mustMatchDst) {
                                if (!offsetInterval.containsWallTime((long) wallTimeSeconds)) {
                                    offsetInterval2 = offsetInterval;
                                } else if (this.isDst == -1 || offsetInterval.getIsDst() == this.isDst) {
                                    int totalOffsetSeconds = offsetInterval.getTotalOffsetSeconds();
                                    int returnValue = ZoneInfo.checked32BitSubtract((long) wallTimeSeconds, totalOffsetSeconds);
                                    copyFieldsFromCalendar();
                                    this.isDst = offsetInterval.getIsDst();
                                    this.gmtOffsetSeconds = totalOffsetSeconds;
                                } else {
                                    offsetInterval2 = offsetInterval;
                                }
                            } else if (this.isDst != offsetInterval.getIsDst()) {
                                offsetInterval2 = offsetInterval;
                                Integer returnValue2 = tryOffsetAdjustments(zoneInfo, wallTimeSeconds, offsetInterval, currentTransitionIndex, this.isDst);
                                if (returnValue2 != null) {
                                    return returnValue2;
                                }
                            } else {
                                offsetInterval2 = offsetInterval;
                            }
                            if (transitionIndexDelta > 0) {
                                if (offsetInterval2.getEndWallTimeSeconds() - ((long) wallTimeSeconds) <= 86400) {
                                    endSearch = false;
                                }
                                if (endSearch) {
                                    clampTop = true;
                                }
                            } else if (transitionIndexDelta < 0) {
                                if (((long) wallTimeSeconds) - offsetInterval2.getStartWallTimeSeconds() < 86400) {
                                    endSearch = false;
                                }
                                if (endSearch) {
                                    clampBottom = true;
                                }
                            }
                        }
                    }
                }
                if (clampTop && clampBottom) {
                    return null;
                }
                loop = loop2;
            }
            int totalOffsetSeconds2 = offsetInterval.getTotalOffsetSeconds();
            int returnValue3 = ZoneInfo.checked32BitSubtract((long) wallTimeSeconds, totalOffsetSeconds2);
            copyFieldsFromCalendar();
            this.isDst = offsetInterval.getIsDst();
            this.gmtOffsetSeconds = totalOffsetSeconds2;
            return Integer.valueOf(returnValue3);
        }

        public void setYear(int year2) {
            this.year = year2;
        }

        public void setMonth(int month2) {
            this.month = month2;
        }

        public void setMonthDay(int monthDay2) {
            this.monthDay = monthDay2;
        }

        public void setHour(int hour2) {
            this.hour = hour2;
        }

        public void setMinute(int minute2) {
            this.minute = minute2;
        }

        public void setSecond(int second2) {
            this.second = second2;
        }

        public void setWeekDay(int weekDay2) {
            this.weekDay = weekDay2;
        }

        public void setYearDay(int yearDay2) {
            this.yearDay = yearDay2;
        }

        public void setIsDst(int isDst2) {
            this.isDst = isDst2;
        }

        public void setGmtOffset(int gmtoff) {
            this.gmtOffsetSeconds = gmtoff;
        }

        public int getYear() {
            return this.year;
        }

        public int getMonth() {
            return this.month;
        }

        public int getMonthDay() {
            return this.monthDay;
        }

        public int getHour() {
            return this.hour;
        }

        public int getMinute() {
            return this.minute;
        }

        public int getSecond() {
            return this.second;
        }

        public int getWeekDay() {
            return this.weekDay;
        }

        public int getYearDay() {
            return this.yearDay;
        }

        public int getGmtOffset() {
            return this.gmtOffsetSeconds;
        }

        public int getIsDst() {
            return this.isDst;
        }

        private void copyFieldsToCalendar() {
            this.calendar.set(1, this.year);
            this.calendar.set(2, this.month);
            this.calendar.set(5, this.monthDay);
            this.calendar.set(11, this.hour);
            this.calendar.set(12, this.minute);
            this.calendar.set(13, this.second);
            this.calendar.set(14, 0);
        }

        private void copyFieldsFromCalendar() {
            this.year = this.calendar.get(1);
            this.month = this.calendar.get(2);
            this.monthDay = this.calendar.get(5);
            this.hour = this.calendar.get(11);
            this.minute = this.calendar.get(12);
            this.second = this.calendar.get(13);
            this.weekDay = this.calendar.get(7) - 1;
            this.yearDay = this.calendar.get(6) - 1;
        }
    }

    /* access modifiers changed from: package-private */
    public static class OffsetInterval {
        private final int endWallTimeSeconds;
        private final int isDst;
        private final int startWallTimeSeconds;
        private final int totalOffsetSeconds;

        public static OffsetInterval create(ZoneInfo timeZone, int transitionIndex) {
            int endWallTimeSeconds2;
            if (transitionIndex < -1 || transitionIndex >= timeZone.mTransitions.length) {
                return null;
            }
            if (transitionIndex == -1) {
                int totalOffsetSeconds2 = timeZone.mEarliestRawOffset / 1000;
                int endWallTimeSeconds3 = ZoneInfo.saturated32BitAdd(timeZone.mTransitions[0], totalOffsetSeconds2);
                if (Integer.MIN_VALUE == endWallTimeSeconds3) {
                    return null;
                }
                return new OffsetInterval(Integer.MIN_VALUE, endWallTimeSeconds3, 0, totalOffsetSeconds2);
            }
            int type = timeZone.mTypes[transitionIndex] & 255;
            int totalOffsetSeconds3 = timeZone.mOffsets[type] + (timeZone.mRawOffset / 1000);
            if (transitionIndex == timeZone.mTransitions.length - 1) {
                endWallTimeSeconds2 = Integer.MAX_VALUE;
            } else {
                endWallTimeSeconds2 = ZoneInfo.saturated32BitAdd(timeZone.mTransitions[transitionIndex + 1], totalOffsetSeconds3);
            }
            byte b = timeZone.mIsDsts[type];
            int startWallTimeSeconds2 = ZoneInfo.saturated32BitAdd(timeZone.mTransitions[transitionIndex], totalOffsetSeconds3);
            if (startWallTimeSeconds2 == endWallTimeSeconds2) {
                return null;
            }
            return new OffsetInterval(startWallTimeSeconds2, endWallTimeSeconds2, b, totalOffsetSeconds3);
        }

        private OffsetInterval(int startWallTimeSeconds2, int endWallTimeSeconds2, int isDst2, int totalOffsetSeconds2) {
            this.startWallTimeSeconds = startWallTimeSeconds2;
            this.endWallTimeSeconds = endWallTimeSeconds2;
            this.isDst = isDst2;
            this.totalOffsetSeconds = totalOffsetSeconds2;
        }

        public boolean containsWallTime(long wallTimeSeconds) {
            return wallTimeSeconds >= ((long) this.startWallTimeSeconds) && wallTimeSeconds < ((long) this.endWallTimeSeconds);
        }

        public int getIsDst() {
            return this.isDst;
        }

        public int getTotalOffsetSeconds() {
            return this.totalOffsetSeconds;
        }

        public long getEndWallTimeSeconds() {
            return (long) this.endWallTimeSeconds;
        }

        public long getStartWallTimeSeconds() {
            return (long) this.startWallTimeSeconds;
        }
    }

    /* access modifiers changed from: private */
    public static class CheckedArithmeticException extends Exception {
        private CheckedArithmeticException() {
        }
    }

    /* access modifiers changed from: private */
    public static int checked32BitAdd(long a, int b) throws CheckedArithmeticException {
        long result = ((long) b) + a;
        if (result == ((long) ((int) result))) {
            return (int) result;
        }
        throw new CheckedArithmeticException();
    }

    /* access modifiers changed from: private */
    public static int checked32BitSubtract(long a, int b) throws CheckedArithmeticException {
        long result = a - ((long) b);
        if (result == ((long) ((int) result))) {
            return (int) result;
        }
        throw new CheckedArithmeticException();
    }

    /* access modifiers changed from: private */
    public static int saturated32BitAdd(long a, int b) {
        long result = ((long) b) + a;
        if (result > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (result < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }
}
