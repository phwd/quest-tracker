package android.icu.util;

import android.icu.impl.Grego;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class SimpleTimeZone extends BasicTimeZone {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DOM_MODE = 1;
    private static final int DOW_GE_DOM_MODE = 3;
    private static final int DOW_IN_MONTH_MODE = 2;
    private static final int DOW_LE_DOM_MODE = 4;
    public static final int STANDARD_TIME = 1;
    public static final int UTC_TIME = 2;
    public static final int WALL_TIME = 0;
    private static final long serialVersionUID = -7034676239311322769L;
    private static final byte[] staticMonthLength = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int dst = 3600000;
    private transient AnnualTimeZoneRule dstRule;
    private int endDay;
    private int endDayOfWeek;
    private int endMode;
    private int endMonth;
    private int endTime;
    private int endTimeMode;
    private transient TimeZoneTransition firstTransition;
    private transient InitialTimeZoneRule initialRule;
    private volatile transient boolean isFrozen = false;
    private int raw;
    private int startDay;
    private int startDayOfWeek;
    private int startMode;
    private int startMonth;
    private int startTime;
    private int startTimeMode;
    private int startYear;
    private transient AnnualTimeZoneRule stdRule;
    private transient boolean transitionRulesInitialized;
    private boolean useDaylight;
    private STZInfo xinfo = null;

    public SimpleTimeZone(int rawOffset, String ID) {
        super(ID);
        construct(rawOffset, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3600000);
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2) {
        super(ID);
        construct(rawOffset, startMonth2, startDay2, startDayOfWeek2, startTime2, 0, endMonth2, endDay2, endDayOfWeek2, endTime2, 0, 3600000);
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int startTimeMode2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2, int endTimeMode2, int dstSavings) {
        super(ID);
        construct(rawOffset, startMonth2, startDay2, startDayOfWeek2, startTime2, startTimeMode2, endMonth2, endDay2, endDayOfWeek2, endTime2, endTimeMode2, dstSavings);
    }

    public SimpleTimeZone(int rawOffset, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2, int dstSavings) {
        super(ID);
        construct(rawOffset, startMonth2, startDay2, startDayOfWeek2, startTime2, 0, endMonth2, endDay2, endDayOfWeek2, endTime2, 0, dstSavings);
    }

    @Override // android.icu.util.TimeZone
    public void setID(String ID) {
        if (!isFrozen()) {
            super.setID(ID);
            this.transitionRulesInitialized = false;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    @Override // android.icu.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        if (!isFrozen()) {
            this.raw = offsetMillis;
            this.transitionRulesInitialized = false;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        return this.raw;
    }

    public void setStartYear(int year) {
        if (!isFrozen()) {
            getSTZInfo().sy = year;
            this.startYear = year;
            this.transitionRulesInitialized = false;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    public void setStartRule(int month, int dayOfWeekInMonth, int dayOfWeek, int time) {
        if (!isFrozen()) {
            getSTZInfo().setStart(month, dayOfWeekInMonth, dayOfWeek, time, -1, false);
            setStartRule(month, dayOfWeekInMonth, dayOfWeek, time, 0);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    private void setStartRule(int month, int dayOfWeekInMonth, int dayOfWeek, int time, int mode) {
        this.startMonth = month;
        this.startDay = dayOfWeekInMonth;
        this.startDayOfWeek = dayOfWeek;
        this.startTime = time;
        this.startTimeMode = mode;
        decodeStartRule();
        this.transitionRulesInitialized = false;
    }

    public void setStartRule(int month, int dayOfMonth, int time) {
        if (!isFrozen()) {
            getSTZInfo().setStart(month, -1, -1, time, dayOfMonth, false);
            setStartRule(month, dayOfMonth, 0, time, 0);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    public void setStartRule(int month, int dayOfMonth, int dayOfWeek, int time, boolean after) {
        if (!isFrozen()) {
            getSTZInfo().setStart(month, -1, dayOfWeek, time, dayOfMonth, after);
            setStartRule(month, after ? dayOfMonth : -dayOfMonth, -dayOfWeek, time, 0);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    public void setEndRule(int month, int dayOfWeekInMonth, int dayOfWeek, int time) {
        if (!isFrozen()) {
            getSTZInfo().setEnd(month, dayOfWeekInMonth, dayOfWeek, time, -1, false);
            setEndRule(month, dayOfWeekInMonth, dayOfWeek, time, 0);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    public void setEndRule(int month, int dayOfMonth, int time) {
        if (!isFrozen()) {
            getSTZInfo().setEnd(month, -1, -1, time, dayOfMonth, false);
            setEndRule(month, dayOfMonth, 0, time);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    public void setEndRule(int month, int dayOfMonth, int dayOfWeek, int time, boolean after) {
        if (!isFrozen()) {
            getSTZInfo().setEnd(month, -1, dayOfWeek, time, dayOfMonth, after);
            setEndRule(month, dayOfMonth, dayOfWeek, time, 0, after);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
    }

    private void setEndRule(int month, int dayOfMonth, int dayOfWeek, int time, int mode, boolean after) {
        setEndRule(month, after ? dayOfMonth : -dayOfMonth, -dayOfWeek, time, mode);
    }

    private void setEndRule(int month, int dayOfWeekInMonth, int dayOfWeek, int time, int mode) {
        this.endMonth = month;
        this.endDay = dayOfWeekInMonth;
        this.endDayOfWeek = dayOfWeek;
        this.endTime = time;
        this.endTimeMode = mode;
        decodeEndRule();
        this.transitionRulesInitialized = false;
    }

    public void setDSTSavings(int millisSavedDuringDST) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify a frozen SimpleTimeZone instance.");
        } else if (millisSavedDuringDST != 0) {
            this.dst = millisSavedDuringDST;
            this.transitionRulesInitialized = false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // android.icu.util.TimeZone
    public int getDSTSavings() {
        return this.dst;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        STZInfo sTZInfo = this.xinfo;
        if (sTZInfo != null) {
            sTZInfo.applyTo(this);
        }
    }

    public String toString() {
        return "SimpleTimeZone: " + getID();
    }

    private STZInfo getSTZInfo() {
        if (this.xinfo == null) {
            this.xinfo = new STZInfo();
        }
        return this.xinfo;
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
        if (month >= 0 && month <= 11) {
            return getOffset(era, year, month, day, dayOfWeek, millis, Grego.monthLength(year, month));
        }
        throw new IllegalArgumentException();
    }

    @Deprecated
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis, int monthLength) {
        if (month >= 0 && month <= 11) {
            return getOffset(era, year, month, day, dayOfWeek, millis, Grego.monthLength(year, month), Grego.previousMonthLength(year, month));
        }
        throw new IllegalArgumentException();
    }

    private int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis, int monthLength, int prevMonthLength) {
        int i;
        if ((era == 1 || era == 0) && month >= 0 && month <= 11 && day >= 1 && day <= monthLength && dayOfWeek >= 1 && dayOfWeek <= 7 && millis >= 0 && millis < 86400000 && monthLength >= 28 && monthLength <= 31 && prevMonthLength >= 28 && prevMonthLength <= 31) {
            int result = this.raw;
            if (!this.useDaylight || year < this.startYear) {
                return result;
            }
            if (era != 1) {
                return result;
            }
            boolean southern = this.startMonth > this.endMonth;
            int startCompare = compareToRule(month, monthLength, prevMonthLength, day, dayOfWeek, millis, this.startTimeMode == 2 ? -this.raw : 0, this.startMode, this.startMonth, this.startDayOfWeek, this.startDay, this.startTime);
            int endCompare = 0;
            if (southern != (startCompare >= 0)) {
                int i2 = this.endTimeMode;
                if (i2 == 0) {
                    i = this.dst;
                } else {
                    i = i2 == 2 ? -this.raw : 0;
                }
                endCompare = compareToRule(month, monthLength, prevMonthLength, day, dayOfWeek, millis, i, this.endMode, this.endMonth, this.endDayOfWeek, this.endDay, this.endTime);
            }
            if (southern || startCompare < 0 || endCompare >= 0) {
                if (!southern) {
                    return result;
                }
                if (startCompare < 0 && endCompare >= 0) {
                    return result;
                }
            }
            return result + this.dst;
        }
        throw new IllegalArgumentException();
    }

    @Override // android.icu.util.BasicTimeZone
    @Deprecated
    public void getOffsetFromLocal(long date, int nonExistingTimeOpt, int duplicatedTimeOpt, int[] offsets) {
        long date2 = date;
        offsets[0] = getRawOffset();
        int[] fields = new int[6];
        Grego.timeToFields(date2, fields);
        offsets[1] = getOffset(1, fields[0], fields[1], fields[2], fields[3], fields[5]) - offsets[0];
        boolean recalc = false;
        if (offsets[1] > 0) {
            if ((nonExistingTimeOpt & 3) == 1 || !((nonExistingTimeOpt & 3) == 3 || (nonExistingTimeOpt & 12) == 12)) {
                date2 -= (long) getDSTSavings();
                recalc = true;
            }
        } else if ((duplicatedTimeOpt & 3) == 3 || ((duplicatedTimeOpt & 3) != 1 && (duplicatedTimeOpt & 12) == 4)) {
            date2 -= (long) getDSTSavings();
            recalc = true;
        }
        if (recalc) {
            Grego.timeToFields(date2, fields);
            offsets[1] = getOffset(1, fields[0], fields[1], fields[2], fields[3], fields[5]) - offsets[0];
        }
    }

    private int compareToRule(int month, int monthLen, int prevMonthLen, int dayOfMonth, int dayOfWeek, int millis, int millisDelta, int ruleMode, int ruleMonth, int ruleDayOfWeek, int ruleDay, int ruleMillis) {
        int millis2 = millis + millisDelta;
        int month2 = month;
        int dayOfMonth2 = dayOfMonth;
        int dayOfWeek2 = dayOfWeek;
        while (millis2 >= 86400000) {
            millis2 -= Grego.MILLIS_PER_DAY;
            dayOfMonth2++;
            dayOfWeek2 = (dayOfWeek2 % 7) + 1;
            if (dayOfMonth2 > monthLen) {
                dayOfMonth2 = 1;
                month2++;
            }
        }
        while (millis2 < 0) {
            dayOfMonth2--;
            dayOfWeek2 = ((dayOfWeek2 + 5) % 7) + 1;
            if (dayOfMonth2 < 1) {
                dayOfMonth2 = prevMonthLen;
                month2--;
            }
            millis2 += Grego.MILLIS_PER_DAY;
        }
        if (month2 < ruleMonth) {
            return -1;
        }
        if (month2 > ruleMonth) {
            return 1;
        }
        int ruleDayOfMonth = 0;
        int ruleDay2 = ruleDay;
        if (ruleDay2 > monthLen) {
            ruleDay2 = monthLen;
        }
        if (ruleMode == 1) {
            ruleDayOfMonth = ruleDay2;
        } else if (ruleMode != 2) {
            if (ruleMode == 3) {
                ruleDayOfMonth = ruleDay2 + (((((ruleDayOfWeek + 49) - ruleDay2) - dayOfWeek2) + dayOfMonth2) % 7);
            } else if (ruleMode == 4) {
                ruleDayOfMonth = ruleDay2 - (((((49 - ruleDayOfWeek) + ruleDay2) + dayOfWeek2) - dayOfMonth2) % 7);
            }
        } else if (ruleDay2 > 0) {
            ruleDayOfMonth = ((ruleDay2 - 1) * 7) + 1 + (((ruleDayOfWeek + 7) - ((dayOfWeek2 - dayOfMonth2) + 1)) % 7);
        } else {
            ruleDayOfMonth = (((ruleDay2 + 1) * 7) + monthLen) - (((((dayOfWeek2 + monthLen) - dayOfMonth2) + 7) - ruleDayOfWeek) % 7);
        }
        if (dayOfMonth2 < ruleDayOfMonth) {
            return -1;
        }
        if (dayOfMonth2 > ruleDayOfMonth) {
            return 1;
        }
        if (millis2 < ruleMillis) {
            return -1;
        }
        if (millis2 > ruleMillis) {
            return 1;
        }
        return 0;
    }

    @Override // android.icu.util.TimeZone
    public boolean useDaylightTime() {
        return this.useDaylight;
    }

    @Override // android.icu.util.TimeZone
    public boolean observesDaylightTime() {
        return this.useDaylight;
    }

    @Override // android.icu.util.TimeZone
    public boolean inDaylightTime(Date date) {
        GregorianCalendar gc = new GregorianCalendar(this);
        gc.setTime(date);
        return gc.inDaylightTime();
    }

    private void construct(int _raw, int _startMonth, int _startDay, int _startDayOfWeek, int _startTime, int _startTimeMode, int _endMonth, int _endDay, int _endDayOfWeek, int _endTime, int _endTimeMode, int _dst) {
        this.raw = _raw;
        this.startMonth = _startMonth;
        this.startDay = _startDay;
        this.startDayOfWeek = _startDayOfWeek;
        this.startTime = _startTime;
        this.startTimeMode = _startTimeMode;
        this.endMonth = _endMonth;
        this.endDay = _endDay;
        this.endDayOfWeek = _endDayOfWeek;
        this.endTime = _endTime;
        this.endTimeMode = _endTimeMode;
        this.dst = _dst;
        this.startYear = 0;
        this.startMode = 1;
        this.endMode = 1;
        decodeRules();
        if (_dst == 0) {
            throw new IllegalArgumentException();
        }
    }

    private void decodeRules() {
        decodeStartRule();
        decodeEndRule();
    }

    private void decodeStartRule() {
        int i;
        this.useDaylight = (this.startDay == 0 || this.endDay == 0) ? false : true;
        if (this.useDaylight && this.dst == 0) {
            this.dst = Grego.MILLIS_PER_DAY;
        }
        int i2 = this.startDay;
        if (i2 != 0) {
            int i3 = this.startMonth;
            if (i3 < 0 || i3 > 11) {
                throw new IllegalArgumentException();
            }
            int i4 = this.startTime;
            if (i4 < 0 || i4 > 86400000 || (i = this.startTimeMode) < 0 || i > 2) {
                throw new IllegalArgumentException();
            }
            int i5 = this.startDayOfWeek;
            if (i5 == 0) {
                this.startMode = 1;
            } else {
                if (i5 > 0) {
                    this.startMode = 2;
                } else {
                    this.startDayOfWeek = -i5;
                    if (i2 > 0) {
                        this.startMode = 3;
                    } else {
                        this.startDay = -i2;
                        this.startMode = 4;
                    }
                }
                if (this.startDayOfWeek > 7) {
                    throw new IllegalArgumentException();
                }
            }
            if (this.startMode == 2) {
                int i6 = this.startDay;
                if (i6 < -5 || i6 > 5) {
                    throw new IllegalArgumentException();
                }
                return;
            }
            int i7 = this.startDay;
            if (i7 < 1 || i7 > staticMonthLength[this.startMonth]) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void decodeEndRule() {
        int i;
        this.useDaylight = (this.startDay == 0 || this.endDay == 0) ? false : true;
        if (this.useDaylight && this.dst == 0) {
            this.dst = Grego.MILLIS_PER_DAY;
        }
        int i2 = this.endDay;
        if (i2 != 0) {
            int i3 = this.endMonth;
            if (i3 < 0 || i3 > 11) {
                throw new IllegalArgumentException();
            }
            int i4 = this.endTime;
            if (i4 < 0 || i4 > 86400000 || (i = this.endTimeMode) < 0 || i > 2) {
                throw new IllegalArgumentException();
            }
            int i5 = this.endDayOfWeek;
            if (i5 == 0) {
                this.endMode = 1;
            } else {
                if (i5 > 0) {
                    this.endMode = 2;
                } else {
                    this.endDayOfWeek = -i5;
                    if (i2 > 0) {
                        this.endMode = 3;
                    } else {
                        this.endDay = -i2;
                        this.endMode = 4;
                    }
                }
                if (this.endDayOfWeek > 7) {
                    throw new IllegalArgumentException();
                }
            }
            if (this.endMode == 2) {
                int i6 = this.endDay;
                if (i6 < -5 || i6 > 5) {
                    throw new IllegalArgumentException();
                }
                return;
            }
            int i7 = this.endDay;
            if (i7 < 1 || i7 > staticMonthLength[this.endMonth]) {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override // android.icu.util.TimeZone
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SimpleTimeZone that = (SimpleTimeZone) obj;
        if (this.raw == that.raw && this.useDaylight == that.useDaylight && idEquals(getID(), that.getID())) {
            if (!this.useDaylight) {
                return true;
            }
            if (this.dst == that.dst && this.startMode == that.startMode && this.startMonth == that.startMonth && this.startDay == that.startDay && this.startDayOfWeek == that.startDayOfWeek && this.startTime == that.startTime && this.startTimeMode == that.startTimeMode && this.endMode == that.endMode && this.endMonth == that.endMonth && this.endDay == that.endDay && this.endDayOfWeek == that.endDayOfWeek && this.endTime == that.endTime && this.endTimeMode == that.endTimeMode && this.startYear == that.startYear) {
                return true;
            }
        }
        return false;
    }

    private boolean idEquals(String id1, String id2) {
        if (id1 == null && id2 == null) {
            return true;
        }
        if (id1 == null || id2 == null) {
            return false;
        }
        return id1.equals(id2);
    }

    @Override // android.icu.util.TimeZone
    public int hashCode() {
        int hashCode = super.hashCode();
        int i = this.raw;
        boolean z = this.useDaylight;
        int ret = (hashCode + i) ^ ((i >>> 8) + (!z ? 1 : 0));
        if (z) {
            return ret;
        }
        int i2 = this.dst;
        int i3 = this.startMode;
        int i4 = i2 ^ ((i2 >>> 10) + i3);
        int i5 = i3 >>> 11;
        int i6 = this.startMonth;
        int i7 = i4 ^ (i5 + i6);
        int i8 = i6 >>> 12;
        int i9 = this.startDay;
        int i10 = i7 ^ (i8 + i9);
        int i11 = i9 >>> 13;
        int i12 = this.startDayOfWeek;
        int i13 = i10 ^ (i11 + i12);
        int i14 = i12 >>> 14;
        int i15 = this.startTime;
        int i16 = i13 ^ (i14 + i15);
        int i17 = i15 >>> 15;
        int i18 = this.startTimeMode;
        int i19 = i16 ^ (i17 + i18);
        int i20 = i18 >>> 16;
        int i21 = this.endMode;
        int i22 = i19 ^ (i20 + i21);
        int i23 = i21 >>> 17;
        int i24 = this.endMonth;
        int i25 = i22 ^ (i23 + i24);
        int i26 = i24 >>> 18;
        int i27 = this.endDay;
        int i28 = i25 ^ (i26 + i27);
        int i29 = i27 >>> 19;
        int i30 = this.endDayOfWeek;
        int i31 = i28 ^ (i29 + i30);
        int i32 = i30 >>> 20;
        int i33 = this.endTime;
        int i34 = i31 ^ (i32 + i33);
        int i35 = i33 >>> 21;
        int i36 = this.endTimeMode;
        int i37 = i34 ^ (i35 + i36);
        int i38 = i36 >>> 22;
        int i39 = this.startYear;
        return ret + ((i37 ^ (i38 + i39)) ^ (i39 >>> 23));
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    @Override // android.icu.util.TimeZone
    public boolean hasSameRules(TimeZone othr) {
        boolean z;
        if (this == othr) {
            return true;
        }
        if (!(othr instanceof SimpleTimeZone)) {
            return false;
        }
        SimpleTimeZone other = (SimpleTimeZone) othr;
        if (other != null && this.raw == other.raw && (z = this.useDaylight) == other.useDaylight) {
            if (!z) {
                return true;
            }
            if (this.dst == other.dst && this.startMode == other.startMode && this.startMonth == other.startMonth && this.startDay == other.startDay && this.startDayOfWeek == other.startDayOfWeek && this.startTime == other.startTime && this.startTimeMode == other.startTimeMode && this.endMode == other.endMode && this.endMonth == other.endMonth && this.endDay == other.endDay && this.endDayOfWeek == other.endDayOfWeek && this.endTime == other.endTime && this.endTimeMode == other.endTimeMode && this.startYear == other.startYear) {
                return true;
            }
        }
        return false;
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getNextTransition(long base, boolean inclusive) {
        if (!this.useDaylight) {
            return null;
        }
        initTransitionRules();
        long firstTransitionTime = this.firstTransition.getTime();
        if (base < firstTransitionTime || (inclusive && base == firstTransitionTime)) {
            return this.firstTransition;
        }
        Date stdDate = this.stdRule.getNextStart(base, this.dstRule.getRawOffset(), this.dstRule.getDSTSavings(), inclusive);
        Date dstDate = this.dstRule.getNextStart(base, this.stdRule.getRawOffset(), this.stdRule.getDSTSavings(), inclusive);
        if (stdDate != null && (dstDate == null || stdDate.before(dstDate))) {
            return new TimeZoneTransition(stdDate.getTime(), this.dstRule, this.stdRule);
        }
        if (dstDate == null || (stdDate != null && !dstDate.before(stdDate))) {
            return null;
        }
        return new TimeZoneTransition(dstDate.getTime(), this.stdRule, this.dstRule);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getPreviousTransition(long base, boolean inclusive) {
        if (!this.useDaylight) {
            return null;
        }
        initTransitionRules();
        long firstTransitionTime = this.firstTransition.getTime();
        if (base < firstTransitionTime || (!inclusive && base == firstTransitionTime)) {
            return null;
        }
        Date stdDate = this.stdRule.getPreviousStart(base, this.dstRule.getRawOffset(), this.dstRule.getDSTSavings(), inclusive);
        Date dstDate = this.dstRule.getPreviousStart(base, this.stdRule.getRawOffset(), this.stdRule.getDSTSavings(), inclusive);
        if (stdDate != null && (dstDate == null || stdDate.after(dstDate))) {
            return new TimeZoneTransition(stdDate.getTime(), this.dstRule, this.stdRule);
        }
        if (dstDate == null || (stdDate != null && !dstDate.after(stdDate))) {
            return null;
        }
        return new TimeZoneTransition(dstDate.getTime(), this.stdRule, this.dstRule);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules() {
        initTransitionRules();
        TimeZoneRule[] rules = new TimeZoneRule[(this.useDaylight ? 3 : 1)];
        rules[0] = this.initialRule;
        if (this.useDaylight) {
            rules[1] = this.stdRule;
            rules[2] = this.dstRule;
        }
        return rules;
    }

    private synchronized void initTransitionRules() {
        int timeRuleType;
        int timeRuleType2;
        if (!this.transitionRulesInitialized) {
            if (this.useDaylight) {
                DateTimeRule dtRule = null;
                if (this.startTimeMode == 1) {
                    timeRuleType = 1;
                } else {
                    timeRuleType = this.startTimeMode == 2 ? 2 : 0;
                }
                int i = this.startMode;
                if (i == 1) {
                    dtRule = new DateTimeRule(this.startMonth, this.startDay, this.startTime, timeRuleType);
                } else if (i == 2) {
                    dtRule = new DateTimeRule(this.startMonth, this.startDay, this.startDayOfWeek, this.startTime, timeRuleType);
                } else if (i == 3) {
                    dtRule = new DateTimeRule(this.startMonth, this.startDay, this.startDayOfWeek, true, this.startTime, timeRuleType);
                } else if (i == 4) {
                    dtRule = new DateTimeRule(this.startMonth, this.startDay, this.startDayOfWeek, false, this.startTime, timeRuleType);
                }
                this.dstRule = new AnnualTimeZoneRule(getID() + "(DST)", getRawOffset(), getDSTSavings(), dtRule, this.startYear, Integer.MAX_VALUE);
                long firstDstStart = this.dstRule.getFirstStart(getRawOffset(), 0).getTime();
                if (this.endTimeMode == 1) {
                    timeRuleType2 = 1;
                } else {
                    timeRuleType2 = this.endTimeMode == 2 ? 2 : 0;
                }
                int i2 = this.endMode;
                if (i2 == 1) {
                    dtRule = new DateTimeRule(this.endMonth, this.endDay, this.endTime, timeRuleType2);
                } else if (i2 == 2) {
                    dtRule = new DateTimeRule(this.endMonth, this.endDay, this.endDayOfWeek, this.endTime, timeRuleType2);
                } else if (i2 == 3) {
                    dtRule = new DateTimeRule(this.endMonth, this.endDay, this.endDayOfWeek, true, this.endTime, timeRuleType2);
                } else if (i2 == 4) {
                    dtRule = new DateTimeRule(this.endMonth, this.endDay, this.endDayOfWeek, false, this.endTime, timeRuleType2);
                }
                this.stdRule = new AnnualTimeZoneRule(getID() + "(STD)", getRawOffset(), 0, dtRule, this.startYear, Integer.MAX_VALUE);
                long firstStdStart = this.stdRule.getFirstStart(getRawOffset(), this.dstRule.getDSTSavings()).getTime();
                if (firstStdStart < firstDstStart) {
                    this.initialRule = new InitialTimeZoneRule(getID() + "(DST)", getRawOffset(), this.dstRule.getDSTSavings());
                    this.firstTransition = new TimeZoneTransition(firstStdStart, this.initialRule, this.stdRule);
                } else {
                    this.initialRule = new InitialTimeZoneRule(getID() + "(STD)", getRawOffset(), 0);
                    this.firstTransition = new TimeZoneTransition(firstDstStart, this.initialRule, this.dstRule);
                }
            } else {
                this.initialRule = new InitialTimeZoneRule(getID(), getRawOffset(), 0);
            }
            this.transitionRulesInitialized = true;
        }
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
        SimpleTimeZone tz = (SimpleTimeZone) super.cloneAsThawed();
        tz.isFrozen = false;
        return tz;
    }
}
