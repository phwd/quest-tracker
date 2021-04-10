package java.sql;

import java.util.Date;

public class Time extends Date {
    static final long serialVersionUID = 8397324403548013681L;

    @Deprecated
    public Time(int hour, int minute, int second) {
        super(70, 0, 1, hour, minute, second);
    }

    public Time(long time) {
        super(time);
    }

    @Override // java.util.Date
    public void setTime(long time) {
        super.setTime(time);
    }

    public static Time valueOf(String s) {
        if (s != null) {
            int firstColon = s.indexOf(58);
            int secondColon = s.indexOf(58, firstColon + 1);
            boolean z = true;
            boolean z2 = (firstColon > 0) & (secondColon > 0);
            if (secondColon >= s.length() - 1) {
                z = false;
            }
            if (z && z2) {
                return new Time(Integer.parseInt(s.substring(0, firstColon)), Integer.parseInt(s.substring(firstColon + 1, secondColon)), Integer.parseInt(s.substring(secondColon + 1)));
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    public String toString() {
        String hourString;
        String minuteString;
        String secondString;
        int hour = super.getHours();
        int minute = super.getMinutes();
        int second = super.getSeconds();
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
        return hourString + ":" + minuteString + ":" + secondString;
    }

    @Override // java.util.Date
    @Deprecated
    public int getYear() {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public int getMonth() {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public int getDay() {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public int getDate() {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public void setYear(int i) {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public void setMonth(int i) {
        throw new IllegalArgumentException();
    }

    @Override // java.util.Date
    @Deprecated
    public void setDate(int i) {
        throw new IllegalArgumentException();
    }
}
