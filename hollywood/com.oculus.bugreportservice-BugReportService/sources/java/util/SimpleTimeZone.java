package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.CalendarSystem;
import sun.util.calendar.CalendarUtils;
import sun.util.calendar.Gregorian;

public class SimpleTimeZone extends TimeZone {
    private static final Gregorian gcal = CalendarSystem.getGregorianCalendar();
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
    private final byte[] monthLength = staticMonthLength;
    private int rawOffset;
    private int serialVersionOnStream = 2;
    private int startDay;
    private int startDayOfWeek;
    private int startMode;
    private int startMonth;
    private int startTime;
    private int startTimeMode;
    private int startYear;
    private boolean useDaylight = false;

    public SimpleTimeZone(int i, String str) {
        this.rawOffset = i;
        setID(str);
        this.dstSavings = 3600000;
    }

    @Override // java.util.TimeZone
    public int getOffset(long j) {
        return getOffsets(j, null);
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.TimeZone
    public int getOffsets(long j, int[] iArr) {
        int i = this.rawOffset;
        if (this.useDaylight) {
            synchronized (this) {
                if (this.cacheStart == 0 || j < this.cacheStart || j >= this.cacheEnd) {
                    BaseCalendar baseCalendar = j >= -12219292800000L ? gcal : (BaseCalendar) CalendarSystem.forName("julian");
                    BaseCalendar.Date date = (BaseCalendar.Date) baseCalendar.newCalendarDate(TimeZone.NO_TIMEZONE);
                    baseCalendar.getCalendarDate(((long) this.rawOffset) + j, date);
                    int normalizedYear = date.getNormalizedYear();
                    if (normalizedYear >= this.startYear) {
                        date.setTimeOfDay(0, 0, 0, 0);
                        i = getOffset(baseCalendar, date, normalizedYear, j);
                    }
                } else {
                    i += this.dstSavings;
                }
            }
        }
        if (iArr != null) {
            int i2 = this.rawOffset;
            iArr[0] = i2;
            iArr[1] = i - i2;
        }
        return i;
    }

    @Override // java.util.TimeZone
    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i == 1 || i == 0) {
            int i7 = i == 0 ? 1 - i2 : i2;
            if (i7 >= 292278994) {
                i7 = (i7 % 2800) + 2800;
            } else if (i7 <= -292269054) {
                i7 = (int) CalendarUtils.mod((long) i7, 28);
            }
            int i8 = i3 + 1;
            Gregorian gregorian = gcal;
            BaseCalendar.Date date = (BaseCalendar.Date) gregorian.newCalendarDate(TimeZone.NO_TIMEZONE);
            date.setDate(i7, i8, i4);
            long time = gregorian.getTime(date) + ((long) (i6 - this.rawOffset));
            Gregorian gregorian2 = gregorian;
            if (time < -12219292800000L) {
                BaseCalendar baseCalendar = (BaseCalendar) CalendarSystem.forName("julian");
                date = (BaseCalendar.Date) baseCalendar.newCalendarDate(TimeZone.NO_TIMEZONE);
                date.setNormalizedDate(i7, i8, i4);
                time = (baseCalendar.getTime(date) + ((long) i6)) - ((long) this.rawOffset);
                gregorian2 = baseCalendar;
            }
            if (date.getNormalizedYear() != i7 || date.getMonth() != i8 || date.getDayOfMonth() != i4 || i5 < 1 || i5 > 7 || i6 < 0 || i6 >= 86400000) {
                throw new IllegalArgumentException();
            } else if (!this.useDaylight || i2 < this.startYear || i != 1) {
                return this.rawOffset;
            } else {
                return getOffset(gregorian2, date, i7, time);
            }
        } else {
            throw new IllegalArgumentException("Illegal era " + i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        r0 = getStart(r7, r8, r9);
        r2 = getEnd(r7, r8, r9);
        r4 = r6.rawOffset;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        if (r0 > r2) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r10 < r0) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        if (r10 >= r2) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        r4 = r4 + r6.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r6.cacheYear = (long) r9;
        r6.cacheStart = r0;
        r6.cacheEnd = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0050, code lost:
        if (r10 >= r2) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        r0 = getStart(r7, r8, r9 - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005a, code lost:
        if (r10 < r0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005c, code lost:
        r7 = r6.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005e, code lost:
        r4 = r4 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0062, code lost:
        if (r10 < r0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0064, code lost:
        r2 = getEnd(r7, r8, r9 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006c, code lost:
        if (r10 >= r2) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006e, code lost:
        r7 = r6.dstSavings;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0073, code lost:
        if (r0 > r2) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0075, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r6.cacheYear = ((long) r6.startYear) - 1;
        r6.cacheStart = r0;
        r6.cacheEnd = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0082, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0087, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getOffset(sun.util.calendar.BaseCalendar r7, sun.util.calendar.BaseCalendar.Date r8, int r9, long r10) {
        /*
        // Method dump skipped, instructions count: 139
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.SimpleTimeZone.getOffset(sun.util.calendar.BaseCalendar, sun.util.calendar.BaseCalendar$Date, int, long):int");
    }

    private long getStart(BaseCalendar baseCalendar, BaseCalendar.Date date, int i) {
        int i2 = this.startTime;
        if (this.startTimeMode != 2) {
            i2 -= this.rawOffset;
        }
        return getTransition(baseCalendar, date, this.startMode, i, this.startMonth, this.startDay, this.startDayOfWeek, i2);
    }

    private long getEnd(BaseCalendar baseCalendar, BaseCalendar.Date date, int i) {
        int i2 = this.endTime;
        if (this.endTimeMode != 2) {
            i2 -= this.rawOffset;
        }
        if (this.endTimeMode == 0) {
            i2 -= this.dstSavings;
        }
        return getTransition(baseCalendar, date, this.endMode, i, this.endMonth, this.endDay, this.endDayOfWeek, i2);
    }

    private long getTransition(BaseCalendar baseCalendar, BaseCalendar.Date date, int i, int i2, int i3, int i4, int i5, int i6) {
        date.setNormalizedYear(i2);
        date.setMonth(i3 + 1);
        if (i == 1) {
            date.setDayOfMonth(i4);
        } else if (i == 2) {
            date.setDayOfMonth(1);
            if (i4 < 0) {
                date.setDayOfMonth(baseCalendar.getMonthLength(date));
            }
            date = (BaseCalendar.Date) baseCalendar.getNthDayOfWeek(i4, i5, date);
        } else if (i == 3) {
            date.setDayOfMonth(i4);
            date = (BaseCalendar.Date) baseCalendar.getNthDayOfWeek(1, i5, date);
        } else if (i == 4) {
            date.setDayOfMonth(i4);
            date = (BaseCalendar.Date) baseCalendar.getNthDayOfWeek(-1, i5, date);
        }
        return baseCalendar.getTime(date) + ((long) i6);
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.rawOffset;
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
        SimpleTimeZone simpleTimeZone = (SimpleTimeZone) obj;
        return getID().equals(simpleTimeZone.getID()) && hasSameRules(simpleTimeZone);
    }

    public boolean hasSameRules(TimeZone timeZone) {
        boolean z;
        if (this == timeZone) {
            return true;
        }
        if (!(timeZone instanceof SimpleTimeZone)) {
            return false;
        }
        SimpleTimeZone simpleTimeZone = (SimpleTimeZone) timeZone;
        if (this.rawOffset == simpleTimeZone.rawOffset && (z = this.useDaylight) == simpleTimeZone.useDaylight) {
            if (!z) {
                return true;
            }
            if (this.dstSavings == simpleTimeZone.dstSavings && this.startMode == simpleTimeZone.startMode && this.startMonth == simpleTimeZone.startMonth && this.startDay == simpleTimeZone.startDay && this.startDayOfWeek == simpleTimeZone.startDayOfWeek && this.startTime == simpleTimeZone.startTime && this.startTimeMode == simpleTimeZone.startTimeMode && this.endMode == simpleTimeZone.endMode && this.endMonth == simpleTimeZone.endMonth && this.endDay == simpleTimeZone.endDay && this.endDayOfWeek == simpleTimeZone.endDayOfWeek && this.endTime == simpleTimeZone.endTime && this.endTimeMode == simpleTimeZone.endTimeMode && this.startYear == simpleTimeZone.startYear) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return SimpleTimeZone.class.getName() + "[id=" + getID() + ",offset=" + this.rawOffset + ",dstSavings=" + this.dstSavings + ",useDaylight=" + this.useDaylight + ",startYear=" + this.startYear + ",startMode=" + this.startMode + ",startMonth=" + this.startMonth + ",startDay=" + this.startDay + ",startDayOfWeek=" + this.startDayOfWeek + ",startTime=" + this.startTime + ",startTimeMode=" + this.startTimeMode + ",endMode=" + this.endMode + ",endMonth=" + this.endMonth + ",endDay=" + this.endDay + ",endDayOfWeek=" + this.endDayOfWeek + ",endTime=" + this.endTime + ",endTimeMode=" + this.endTimeMode + ']';
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

    private int[] packTimes() {
        return new int[]{this.startTime, this.endTime};
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        packRules();
        packTimes();
        makeRulesCompatible();
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
