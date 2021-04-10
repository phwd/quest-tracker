package android.icu.impl;

import android.icu.util.TimeZone;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class JavaTimeZone extends TimeZone {
    private static final TreeSet<String> AVAILABLESET = new TreeSet<>();
    private static Method mObservesDaylightTime = null;
    private static final long serialVersionUID = 6977448185543929364L;
    private volatile transient boolean isFrozen;
    private transient Calendar javacal;
    private java.util.TimeZone javatz;

    static {
        String[] availableIds;
        for (String str : java.util.TimeZone.getAvailableIDs()) {
            AVAILABLESET.add(str);
        }
        try {
            mObservesDaylightTime = java.util.TimeZone.class.getMethod("observesDaylightTime", null);
        } catch (NoSuchMethodException | SecurityException e) {
        }
    }

    public JavaTimeZone() {
        this(java.util.TimeZone.getDefault(), null);
    }

    public JavaTimeZone(java.util.TimeZone jtz, String id) {
        this.isFrozen = false;
        id = id == null ? jtz.getID() : id;
        this.javatz = jtz;
        setID(id);
        this.javacal = new GregorianCalendar(this.javatz);
    }

    public static JavaTimeZone createTimeZone(String id) {
        java.util.TimeZone jtz = null;
        if (AVAILABLESET.contains(id)) {
            jtz = java.util.TimeZone.getTimeZone(id);
        }
        if (jtz == null) {
            boolean[] isSystemID = new boolean[1];
            String canonicalID = TimeZone.getCanonicalID(id, isSystemID);
            if (isSystemID[0] && AVAILABLESET.contains(canonicalID)) {
                jtz = java.util.TimeZone.getTimeZone(canonicalID);
            }
        }
        if (jtz == null) {
            return null;
        }
        return new JavaTimeZone(jtz, id);
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
        return this.javatz.getOffset(era, year, month, day, dayOfWeek, milliseconds);
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long date, boolean local, int[] offsets) {
        int min;
        int hour;
        synchronized (this.javacal) {
            if (local) {
                int[] fields = new int[6];
                Grego.timeToFields(date, fields);
                int tmp = fields[5];
                int mil = tmp % 1000;
                int tmp2 = tmp / 1000;
                int sec = tmp2 % 60;
                int tmp3 = tmp2 / 60;
                int min2 = tmp3 % 60;
                int hour2 = tmp3 / 60;
                this.javacal.clear();
                this.javacal.set(fields[0], fields[1], fields[2], hour2, min2, sec);
                this.javacal.set(14, mil);
                int doy1 = this.javacal.get(6);
                int hour1 = this.javacal.get(11);
                int min1 = this.javacal.get(12);
                int sec1 = this.javacal.get(13);
                int mil1 = this.javacal.get(14);
                if (fields[4] == doy1) {
                    hour = hour2;
                    if (hour == hour1) {
                        min = min2;
                        if (min == min1 && sec == sec1) {
                            if (mil != mil1) {
                            }
                        }
                    } else {
                        min = min2;
                    }
                } else {
                    hour = hour2;
                    min = min2;
                }
                this.javacal.setTimeInMillis((this.javacal.getTimeInMillis() - ((long) (((((((((((((Math.abs(doy1 - fields[4]) > 1 ? 1 : doy1 - fields[4]) * 24) + hour1) - hour) * 60) + min1) - min) * 60) + sec1) - sec) * 1000) + mil1) - mil))) - 1);
            } else {
                this.javacal.setTimeInMillis(date);
            }
            offsets[0] = this.javacal.get(15);
            offsets[1] = this.javacal.get(16);
        }
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        return this.javatz.getRawOffset();
    }

    @Override // android.icu.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return this.javatz.inDaylightTime(date);
    }

    @Override // android.icu.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        if (!isFrozen()) {
            this.javatz.setRawOffset(offsetMillis);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen JavaTimeZone instance.");
    }

    @Override // android.icu.util.TimeZone
    public boolean useDaylightTime() {
        return this.javatz.useDaylightTime();
    }

    @Override // android.icu.util.TimeZone
    public boolean observesDaylightTime() {
        Method method = mObservesDaylightTime;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(this.javatz, null)).booleanValue();
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }
        return super.observesDaylightTime();
    }

    @Override // android.icu.util.TimeZone
    public int getDSTSavings() {
        return this.javatz.getDSTSavings();
    }

    public java.util.TimeZone unwrap() {
        return this.javatz;
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    @Override // android.icu.util.TimeZone
    public int hashCode() {
        return super.hashCode() + this.javatz.hashCode();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.javacal = new GregorianCalendar(this.javatz);
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
        JavaTimeZone tz = (JavaTimeZone) super.cloneAsThawed();
        tz.javatz = (java.util.TimeZone) this.javatz.clone();
        tz.javacal = new GregorianCalendar(this.javatz);
        tz.isFrozen = false;
        return tz;
    }
}
