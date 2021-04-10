package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Gregorian;

public class SimpleTimeZone extends TimeZone {
    private static final int DOM_MODE = 1;
    private static final int DOW_GE_DOM_MODE = 3;
    private static final int DOW_IN_MONTH_MODE = 2;
    private static final int DOW_LE_DOM_MODE = 4;
    public static final int STANDARD_TIME = 1;
    public static final int UTC_TIME = 2;
    public static final int WALL_TIME = 0;
    static final int currentSerialVersion = 2;
    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();
    private static final int millisPerDay = 86400000;
    private static final int millisPerHour = 3600000;
    static final long serialVersionUID = -403250971215465050L;
    private static final byte[] staticLeapMonthLength = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final byte[] staticMonthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private transient long cacheEnd;
    private transient long cacheStart;
    private transient long cacheYear;
    private int dstSavings;
    private int endDay;
    private int endDayOfWeek;
    private int endMode;
    private int endMonth;
    private int endTime;
    private int endTimeMode;
    private final byte[] monthLength;
    private int rawOffset;
    private int serialVersionOnStream;
    private int startDay;
    private int startDayOfWeek;
    private int startMode;
    private int startMonth;
    private int startTime;
    private int startTimeMode;
    private int startYear;
    private boolean useDaylight;

    public SimpleTimeZone(int rawOffset2, String ID) {
        this.useDaylight = false;
        this.monthLength = staticMonthLength;
        this.serialVersionOnStream = 2;
        this.rawOffset = rawOffset2;
        setID(ID);
        this.dstSavings = 3600000;
    }

    public SimpleTimeZone(int rawOffset2, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2) {
        this(rawOffset2, ID, startMonth2, startDay2, startDayOfWeek2, startTime2, 0, endMonth2, endDay2, endDayOfWeek2, endTime2, 0, 3600000);
    }

    public SimpleTimeZone(int rawOffset2, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2, int dstSavings2) {
        this(rawOffset2, ID, startMonth2, startDay2, startDayOfWeek2, startTime2, 0, endMonth2, endDay2, endDayOfWeek2, endTime2, 0, dstSavings2);
    }

    public SimpleTimeZone(int rawOffset2, String ID, int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, int startTimeMode2, int endMonth2, int endDay2, int endDayOfWeek2, int endTime2, int endTimeMode2, int dstSavings2) {
        this.useDaylight = false;
        this.monthLength = staticMonthLength;
        this.serialVersionOnStream = 2;
        setID(ID);
        this.rawOffset = rawOffset2;
        this.startMonth = startMonth2;
        this.startDay = startDay2;
        this.startDayOfWeek = startDayOfWeek2;
        this.startTime = startTime2;
        this.startTimeMode = startTimeMode2;
        this.endMonth = endMonth2;
        this.endDay = endDay2;
        this.endDayOfWeek = endDayOfWeek2;
        this.endTime = endTime2;
        this.endTimeMode = endTimeMode2;
        this.dstSavings = dstSavings2;
        decodeRules();
        if (dstSavings2 <= 0) {
            throw new IllegalArgumentException("Illegal daylight saving value: " + dstSavings2);
        }
    }

    public void setStartYear(int year) {
        this.startYear = year;
        invalidateCache();
    }

    public void setStartRule(int startMonth2, int startDay2, int startDayOfWeek2, int startTime2) {
        this.startMonth = startMonth2;
        this.startDay = startDay2;
        this.startDayOfWeek = startDayOfWeek2;
        this.startTime = startTime2;
        this.startTimeMode = 0;
        decodeStartRule();
        invalidateCache();
    }

    public void setStartRule(int startMonth2, int startDay2, int startTime2) {
        setStartRule(startMonth2, startDay2, 0, startTime2);
    }

    public void setStartRule(int startMonth2, int startDay2, int startDayOfWeek2, int startTime2, boolean after) {
        if (after) {
            setStartRule(startMonth2, startDay2, -startDayOfWeek2, startTime2);
        } else {
            setStartRule(startMonth2, -startDay2, -startDayOfWeek2, startTime2);
        }
    }

    public void setEndRule(int endMonth2, int endDay2, int endDayOfWeek2, int endTime2) {
        this.endMonth = endMonth2;
        this.endDay = endDay2;
        this.endDayOfWeek = endDayOfWeek2;
        this.endTime = endTime2;
        this.endTimeMode = 0;
        decodeEndRule();
        invalidateCache();
    }

    public void setEndRule(int endMonth2, int endDay2, int endTime2) {
        setEndRule(endMonth2, endDay2, 0, endTime2);
    }

    public void setEndRule(int endMonth2, int endDay2, int endDayOfWeek2, int endTime2, boolean after) {
        if (after) {
            setEndRule(endMonth2, endDay2, -endDayOfWeek2, endTime2);
        } else {
            setEndRule(endMonth2, -endDay2, -endDayOfWeek2, endTime2);
        }
    }

    @Override // java.util.TimeZone
    public int getOffset(long date) {
        return getOffsets(date, null);
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.TimeZone
    public int getOffsets(long date, int[] offsets) {
        int offset = this.rawOffset;
        if (this.useDaylight) {
            synchronized (this) {
                if (this.cacheStart == 0 || date < this.cacheStart || date >= this.cacheEnd) {
                    BaseCalendar cal = date >= -12219292800000L ? gcal : (BaseCalendar) CalendarSystem.forName("julian");
                    BaseCalendar.Date cdate = (BaseCalendar.Date) cal.newCalendarDate(TimeZone.NO_TIMEZONE);
                    cal.getCalendarDate(((long) this.rawOffset) + date, cdate);
                    int year = cdate.getNormalizedYear();
                    if (year >= this.startYear) {
                        cdate.setTimeOfDay(0, 0, 0, 0);
                        offset = getOffset(cal, cdate, year, date);
                    }
                } else {
                    offset += this.dstSavings;
                }
            }
        }
        if (offsets != null) {
            int i = this.rawOffset;
            offsets[0] = i;
            offsets[1] = offset - i;
        }
        return offset;
    }

    @Override // java.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int millis) {
        int y;
        BaseCalendar cal;
        long time;
        BaseCalendar.Date cdate;
        if (era == 1 || era == 0) {
            int y2 = year;
            if (era == 0) {
                y2 = 1 - y2;
            }
            if (y2 >= 292278994) {
                y = (y2 % 2800) + 2800;
            } else if (y2 <= -292269054) {
                y = (int) CalendarUtils.mod((long) y2, 28);
            } else {
                y = y2;
            }
            int m = month + 1;
            BaseCalendar baseCalendar = gcal;
            BaseCalendar.Date cdate2 = (BaseCalendar.Date) baseCalendar.newCalendarDate(TimeZone.NO_TIMEZONE);
            cdate2.setDate(y, m, day);
            long time2 = baseCalendar.getTime(cdate2) + ((long) (millis - this.rawOffset));
            if (time2 < -12219292800000L) {
                BaseCalendar cal2 = (BaseCalendar) CalendarSystem.forName("julian");
                BaseCalendar.Date cdate3 = (BaseCalendar.Date) cal2.newCalendarDate(TimeZone.NO_TIMEZONE);
                cdate3.setNormalizedDate(y, m, day);
                cal = cal2;
                time = (cal2.getTime(cdate3) + ((long) millis)) - ((long) this.rawOffset);
                cdate = cdate3;
            } else {
                cal = baseCalendar;
                cdate = cdate2;
                time = time2;
            }
            if (cdate.getNormalizedYear() != y || cdate.getMonth() != m || cdate.getDayOfMonth() != day || dayOfWeek < 1 || dayOfWeek > 7 || millis < 0 || millis >= 86400000) {
                throw new IllegalArgumentException();
            } else if (!this.useDaylight || year < this.startYear || era != 1) {
                return this.rawOffset;
            } else {
                return getOffset(cal, cdate, y, time);
            }
        } else {
            throw new IllegalArgumentException("Illegal era " + era);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r0 = getStart(r11, r12, r13);
        r2 = getEnd(r11, r12, r13);
        r4 = r10.rawOffset;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        if (r0 > r2) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r14 < r0) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        if (r14 >= r2) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        r5 = r4 + r10.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0043, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0044, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r10.cacheYear = (long) r13;
        r10.cacheStart = r0;
        r10.cacheEnd = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004c, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0053, code lost:
        if (r14 >= r2) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0055, code lost:
        r0 = getStart(r11, r12, r13 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        if (r14 < r0) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005f, code lost:
        r5 = r4 + r10.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0064, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0068, code lost:
        if (r14 < r0) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006a, code lost:
        r2 = getEnd(r11, r12, r13 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0072, code lost:
        if (r14 >= r2) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0074, code lost:
        r5 = r4 + r10.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0079, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007b, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x007e, code lost:
        if (r0 > r2) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0080, code lost:
        monitor-enter(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r10.cacheYear = ((long) r10.startYear) - 1;
        r10.cacheStart = r0;
        r10.cacheEnd = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x008d, code lost:
        monitor-exit(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0092, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getOffset(sun.util.calendar.BaseCalendar r11, sun.util.calendar.BaseCalendar.Date r12, int r13, long r14) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.SimpleTimeZone.getOffset(sun.util.calendar.BaseCalendar, sun.util.calendar.BaseCalendar$Date, int, long):int");
    }

    private long getStart(BaseCalendar cal, BaseCalendar.Date cdate, int year) {
        int time = this.startTime;
        if (this.startTimeMode != 2) {
            time -= this.rawOffset;
        }
        return getTransition(cal, cdate, this.startMode, year, this.startMonth, this.startDay, this.startDayOfWeek, time);
    }

    private long getEnd(BaseCalendar cal, BaseCalendar.Date cdate, int year) {
        int time = this.endTime;
        if (this.endTimeMode != 2) {
            time -= this.rawOffset;
        }
        if (this.endTimeMode == 0) {
            time -= this.dstSavings;
        }
        return getTransition(cal, cdate, this.endMode, year, this.endMonth, this.endDay, this.endDayOfWeek, time);
    }

    private long getTransition(BaseCalendar cal, BaseCalendar.Date cdate, int mode, int year, int month, int dayOfMonth, int dayOfWeek, int timeOfDay) {
        cdate.setNormalizedYear(year);
        cdate.setMonth(month + 1);
        if (mode == 1) {
            cdate.setDayOfMonth(dayOfMonth);
        } else if (mode == 2) {
            cdate.setDayOfMonth(1);
            if (dayOfMonth < 0) {
                cdate.setDayOfMonth(cal.getMonthLength(cdate));
            }
            cdate = (BaseCalendar.Date) cal.getNthDayOfWeek(dayOfMonth, dayOfWeek, cdate);
        } else if (mode == 3) {
            cdate.setDayOfMonth(dayOfMonth);
            cdate = (BaseCalendar.Date) cal.getNthDayOfWeek(1, dayOfWeek, cdate);
        } else if (mode == 4) {
            cdate.setDayOfMonth(dayOfMonth);
            cdate = (BaseCalendar.Date) cal.getNthDayOfWeek(-1, dayOfWeek, cdate);
        }
        return cal.getTime(cdate) + ((long) timeOfDay);
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.rawOffset;
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        this.rawOffset = offsetMillis;
    }

    public void setDSTSavings(int millisSavedDuringDST) {
        if (millisSavedDuringDST > 0) {
            this.dstSavings = millisSavedDuringDST;
            return;
        }
        throw new IllegalArgumentException("Illegal daylight saving value: " + millisSavedDuringDST);
    }

    @Override // java.util.TimeZone
    public int getDSTSavings() {
        if (this.useDaylight) {
            return this.dstSavings;
        }
        return 0;
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return this.useDaylight;
    }

    @Override // java.util.TimeZone
    public boolean observesDaylightTime() {
        return useDaylightTime();
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return getOffset(date.getTime()) != this.rawOffset;
    }

    @Override // java.util.TimeZone
    public Object clone() {
        return super.clone();
    }

    public synchronized int hashCode() {
        return (((((((this.startMonth ^ this.startDay) ^ this.startDayOfWeek) ^ this.startTime) ^ this.endMonth) ^ this.endDay) ^ this.endDayOfWeek) ^ this.endTime) ^ this.rawOffset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimpleTimeZone)) {
            return false;
        }
        SimpleTimeZone that = (SimpleTimeZone) obj;
        if (!getID().equals(that.getID()) || !hasSameRules(that)) {
            return false;
        }
        return true;
    }

    @Override // java.util.TimeZone
    public boolean hasSameRules(TimeZone other) {
        boolean z;
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimpleTimeZone)) {
            return false;
        }
        SimpleTimeZone that = (SimpleTimeZone) other;
        if (this.rawOffset == that.rawOffset && (z = this.useDaylight) == that.useDaylight) {
            if (!z) {
                return true;
            }
            if (this.dstSavings == that.dstSavings && this.startMode == that.startMode && this.startMonth == that.startMonth && this.startDay == that.startDay && this.startDayOfWeek == that.startDayOfWeek && this.startTime == that.startTime && this.startTimeMode == that.startTimeMode && this.endMode == that.endMode && this.endMonth == that.endMonth && this.endDay == that.endDay && this.endDayOfWeek == that.endDayOfWeek && this.endTime == that.endTime && this.endTimeMode == that.endTimeMode && this.startYear == that.startYear) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return getClass().getName() + "[id=" + getID() + ",offset=" + this.rawOffset + ",dstSavings=" + this.dstSavings + ",useDaylight=" + this.useDaylight + ",startYear=" + this.startYear + ",startMode=" + this.startMode + ",startMonth=" + this.startMonth + ",startDay=" + this.startDay + ",startDayOfWeek=" + this.startDayOfWeek + ",startTime=" + this.startTime + ",startTimeMode=" + this.startTimeMode + ",endMode=" + this.endMode + ",endMonth=" + this.endMonth + ",endDay=" + this.endDay + ",endDayOfWeek=" + this.endDayOfWeek + ",endTime=" + this.endTime + ",endTimeMode=" + this.endTimeMode + ']';
    }

    private synchronized void invalidateCache() {
        this.cacheYear = (long) (this.startYear - 1);
        this.cacheEnd = 0;
        this.cacheStart = 0;
    }

    private void decodeRules() {
        decodeStartRule();
        decodeEndRule();
    }

    private void decodeStartRule() {
        this.useDaylight = (this.startDay == 0 || this.endDay == 0) ? false : true;
        int i = this.startDay;
        if (i != 0) {
            int i2 = this.startMonth;
            if (i2 < 0 || i2 > 11) {
                throw new IllegalArgumentException("Illegal start month " + this.startMonth);
            }
            int i3 = this.startTime;
            if (i3 < 0 || i3 > 86400000) {
                throw new IllegalArgumentException("Illegal start time " + this.startTime);
            }
            int i4 = this.startDayOfWeek;
            if (i4 == 0) {
                this.startMode = 1;
            } else {
                if (i4 > 0) {
                    this.startMode = 2;
                } else {
                    this.startDayOfWeek = -i4;
                    if (i > 0) {
                        this.startMode = 3;
                    } else {
                        this.startDay = -i;
                        this.startMode = 4;
                    }
                }
                if (this.startDayOfWeek > 7) {
                    throw new IllegalArgumentException("Illegal start day of week " + this.startDayOfWeek);
                }
            }
            if (this.startMode == 2) {
                int i5 = this.startDay;
                if (i5 < -5 || i5 > 5) {
                    throw new IllegalArgumentException("Illegal start day of week in month " + this.startDay);
                }
                return;
            }
            int i6 = this.startDay;
            if (i6 < 1 || i6 > staticMonthLength[this.startMonth]) {
                throw new IllegalArgumentException("Illegal start day " + this.startDay);
            }
        }
    }

    private void decodeEndRule() {
        this.useDaylight = (this.startDay == 0 || this.endDay == 0) ? false : true;
        int i = this.endDay;
        if (i != 0) {
            int i2 = this.endMonth;
            if (i2 < 0 || i2 > 11) {
                throw new IllegalArgumentException("Illegal end month " + this.endMonth);
            }
            int i3 = this.endTime;
            if (i3 < 0 || i3 > 86400000) {
                throw new IllegalArgumentException("Illegal end time " + this.endTime);
            }
            int i4 = this.endDayOfWeek;
            if (i4 == 0) {
                this.endMode = 1;
            } else {
                if (i4 > 0) {
                    this.endMode = 2;
                } else {
                    this.endDayOfWeek = -i4;
                    if (i > 0) {
                        this.endMode = 3;
                    } else {
                        this.endDay = -i;
                        this.endMode = 4;
                    }
                }
                if (this.endDayOfWeek > 7) {
                    throw new IllegalArgumentException("Illegal end day of week " + this.endDayOfWeek);
                }
            }
            if (this.endMode == 2) {
                int i5 = this.endDay;
                if (i5 < -5 || i5 > 5) {
                    throw new IllegalArgumentException("Illegal end day of week in month " + this.endDay);
                }
                return;
            }
            int i6 = this.endDay;
            if (i6 < 1 || i6 > staticMonthLength[this.endMonth]) {
                throw new IllegalArgumentException("Illegal end day " + this.endDay);
            }
        }
    }

    private void makeRulesCompatible() {
        int i = this.startMode;
        if (i == 1) {
            this.startDay = (this.startDay / 7) + 1;
            this.startDayOfWeek = 1;
        } else if (i == 3) {
            int i2 = this.startDay;
            if (i2 != 1) {
                this.startDay = (i2 / 7) + 1;
            }
        } else if (i == 4) {
            int i3 = this.startDay;
            if (i3 >= 30) {
                this.startDay = -1;
            } else {
                this.startDay = (i3 / 7) + 1;
            }
        }
        int i4 = this.endMode;
        if (i4 == 1) {
            this.endDay = (this.endDay / 7) + 1;
            this.endDayOfWeek = 1;
        } else if (i4 == 3) {
            int i5 = this.endDay;
            if (i5 != 1) {
                this.endDay = (i5 / 7) + 1;
            }
        } else if (i4 == 4) {
            int i6 = this.endDay;
            if (i6 >= 30) {
                this.endDay = -1;
            } else {
                this.endDay = (i6 / 7) + 1;
            }
        }
        if (this.startTimeMode == 2) {
            this.startTime += this.rawOffset;
        }
        while (true) {
            int i7 = this.startTime;
            if (i7 >= 0) {
                break;
            }
            this.startTime = i7 + 86400000;
            this.startDayOfWeek = ((this.startDayOfWeek + 5) % 7) + 1;
        }
        while (true) {
            int i8 = this.startTime;
            if (i8 < 86400000) {
                break;
            }
            this.startTime = i8 - 86400000;
            this.startDayOfWeek = (this.startDayOfWeek % 7) + 1;
        }
        int i9 = this.endTimeMode;
        if (i9 == 1) {
            this.endTime += this.dstSavings;
        } else if (i9 == 2) {
            this.endTime += this.rawOffset + this.dstSavings;
        }
        while (true) {
            int i10 = this.endTime;
            if (i10 >= 0) {
                break;
            }
            this.endTime = i10 + 86400000;
            this.endDayOfWeek = ((this.endDayOfWeek + 5) % 7) + 1;
        }
        while (true) {
            int i11 = this.endTime;
            if (i11 >= 86400000) {
                this.endTime = i11 - 86400000;
                this.endDayOfWeek = (this.endDayOfWeek % 7) + 1;
            } else {
                return;
            }
        }
    }

    private byte[] packRules() {
        return new byte[]{(byte) this.startDay, (byte) this.startDayOfWeek, (byte) this.endDay, (byte) this.endDayOfWeek, (byte) this.startTimeMode, (byte) this.endTimeMode};
    }

    private void unpackRules(byte[] rules) {
        this.startDay = rules[0];
        this.startDayOfWeek = rules[1];
        this.endDay = rules[2];
        this.endDayOfWeek = rules[3];
        if (rules.length >= 6) {
            this.startTimeMode = rules[4];
            this.endTimeMode = rules[5];
        }
    }

    private int[] packTimes() {
        return new int[]{this.startTime, this.endTime};
    }

    private void unpackTimes(int[] times) {
        this.startTime = times[0];
        this.endTime = times[1];
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        byte[] rules = packRules();
        int[] times = packTimes();
        makeRulesCompatible();
        stream.defaultWriteObject();
        stream.writeInt(rules.length);
        stream.write(rules);
        stream.writeObject(times);
        unpackRules(rules);
        unpackTimes(times);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (this.serialVersionOnStream < 1) {
            if (this.startDayOfWeek == 0) {
                this.startDayOfWeek = 1;
            }
            if (this.endDayOfWeek == 0) {
                this.endDayOfWeek = 1;
            }
            this.endMode = 2;
            this.startMode = 2;
            this.dstSavings = 3600000;
        } else {
            byte[] rules = new byte[stream.readInt()];
            stream.readFully(rules);
            unpackRules(rules);
        }
        if (this.serialVersionOnStream >= 2) {
            unpackTimes((int[]) stream.readObject());
        }
        this.serialVersionOnStream = 2;
    }
}
