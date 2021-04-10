package java.sql;

import android.icu.impl.number.Padder;
import java.util.Date;

public class Timestamp extends Date {
    static final long serialVersionUID = 2745179027874758501L;
    private int nanos;

    @Deprecated
    public Timestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
        super(year, month, date, hour, minute, second);
        if (nano > 999999999 || nano < 0) {
            throw new IllegalArgumentException("nanos > 999999999 or < 0");
        }
        this.nanos = nano;
    }

    public Timestamp(long time) {
        super((time / 1000) * 1000);
        this.nanos = (int) ((time % 1000) * 1000000);
        int i = this.nanos;
        if (i < 0) {
            this.nanos = i + 1000000000;
            super.setTime(((time / 1000) - 1) * 1000);
        }
    }

    @Override // java.util.Date
    public void setTime(long time) {
        super.setTime((time / 1000) * 1000);
        this.nanos = (int) ((time % 1000) * 1000000);
        int i = this.nanos;
        if (i < 0) {
            this.nanos = i + 1000000000;
            super.setTime(((time / 1000) - 1) * 1000);
        }
    }

    @Override // java.util.Date
    public long getTime() {
        return ((long) (this.nanos / 1000000)) + super.getTime();
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.sql.Timestamp valueOf(java.lang.String r38) {
        /*
        // Method dump skipped, instructions count: 477
        */
        throw new UnsupportedOperationException("Method not decompiled: java.sql.Timestamp.valueOf(java.lang.String):java.sql.Timestamp");
    }

    @Override // java.util.Date
    public String toString() {
        String yearString;
        String monthString;
        String dayString;
        String hourString;
        String minuteString;
        String secondString;
        String nanosString;
        int year = super.getYear() + 1900;
        int month = super.getMonth() + 1;
        int day = super.getDate();
        int hour = super.getHours();
        int minute = super.getMinutes();
        int second = super.getSeconds();
        if (year < 1000) {
            String yearString2 = "" + year;
            yearString = "0000".substring(0, 4 - yearString2.length()) + yearString2;
        } else {
            yearString = "" + year;
        }
        if (month < 10) {
            monthString = AndroidHardcodedSystemProperties.JAVA_VERSION + month;
        } else {
            monthString = Integer.toString(month);
        }
        if (day < 10) {
            dayString = AndroidHardcodedSystemProperties.JAVA_VERSION + day;
        } else {
            dayString = Integer.toString(day);
        }
        if (hour < 10) {
            hourString = AndroidHardcodedSystemProperties.JAVA_VERSION + hour;
        } else {
            hourString = Integer.toString(hour);
        }
        if (minute < 10) {
            minuteString = AndroidHardcodedSystemProperties.JAVA_VERSION + minute;
        } else {
            minuteString = Integer.toString(minute);
        }
        if (second < 10) {
            secondString = AndroidHardcodedSystemProperties.JAVA_VERSION + second;
        } else {
            secondString = Integer.toString(second);
        }
        int i = this.nanos;
        if (i == 0) {
            nanosString = AndroidHardcodedSystemProperties.JAVA_VERSION;
        } else {
            String nanosString2 = Integer.toString(i);
            String nanosString3 = "000000000".substring(0, 9 - nanosString2.length()) + nanosString2;
            char[] nanosChar = new char[nanosString3.length()];
            nanosString3.getChars(0, nanosString3.length(), nanosChar, 0);
            int truncIndex = 8;
            while (nanosChar[truncIndex] == '0') {
                truncIndex--;
                nanosString3 = nanosString3;
            }
            nanosString = new String(nanosChar, 0, truncIndex + 1);
        }
        StringBuffer timestampBuf = new StringBuffer(nanosString.length() + 20);
        timestampBuf.append(yearString);
        timestampBuf.append("-");
        timestampBuf.append(monthString);
        timestampBuf.append("-");
        timestampBuf.append(dayString);
        timestampBuf.append(Padder.FALLBACK_PADDING_STRING);
        timestampBuf.append(hourString);
        timestampBuf.append(":");
        timestampBuf.append(minuteString);
        timestampBuf.append(":");
        timestampBuf.append(secondString);
        timestampBuf.append(".");
        timestampBuf.append(nanosString);
        return timestampBuf.toString();
    }

    public int getNanos() {
        return this.nanos;
    }

    public void setNanos(int n) {
        if (n > 999999999 || n < 0) {
            throw new IllegalArgumentException("nanos > 999999999 or < 0");
        }
        this.nanos = n;
    }

    public boolean equals(Timestamp ts) {
        if (!super.equals((Object) ts) || this.nanos != ts.nanos) {
            return false;
        }
        return true;
    }

    @Override // java.util.Date
    public boolean equals(Object ts) {
        if (ts instanceof Timestamp) {
            return equals((Timestamp) ts);
        }
        return false;
    }

    public boolean before(Timestamp ts) {
        return compareTo(ts) < 0;
    }

    public boolean after(Timestamp ts) {
        return compareTo(ts) > 0;
    }

    public int compareTo(Timestamp ts) {
        long thisTime = getTime();
        long anotherTime = ts.getTime();
        int i = thisTime < anotherTime ? -1 : thisTime == anotherTime ? 0 : 1;
        if (i == 0) {
            int i2 = this.nanos;
            int i3 = ts.nanos;
            if (i2 > i3) {
                return 1;
            }
            if (i2 < i3) {
                return -1;
            }
        }
        return i;
    }

    @Override // java.util.Date
    public int compareTo(Date o) {
        if (o instanceof Timestamp) {
            return compareTo((Timestamp) o);
        }
        return compareTo(new Timestamp(o.getTime()));
    }

    @Override // java.util.Date
    public int hashCode() {
        return super.hashCode();
    }
}
