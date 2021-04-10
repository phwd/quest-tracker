package android.icu.impl;

import android.icu.util.TimeZone;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;

public class JavaTimeZone extends TimeZone {
    private static final TreeSet AVAILABLESET = new TreeSet();
    private static Method mObservesDaylightTime = null;
    private static final long serialVersionUID = 6977448185543929364L;
    private volatile transient boolean isFrozen;
    private transient Calendar javacal;
    private java.util.TimeZone javatz;

    static {
        String[] availableIDs;
        for (String str : java.util.TimeZone.getAvailableIDs()) {
            AVAILABLESET.add(str);
        }
        try {
            mObservesDaylightTime = java.util.TimeZone.class.getMethod("observesDaylightTime", null);
        } catch (NoSuchMethodException | SecurityException unused) {
        }
    }

    public JavaTimeZone() {
        this(java.util.TimeZone.getDefault(), null);
    }

    public JavaTimeZone(java.util.TimeZone timeZone, String str) {
        this.isFrozen = false;
        str = str == null ? timeZone.getID() : str;
        this.javatz = timeZone;
        setID(str);
        this.javacal = new GregorianCalendar(this.javatz);
    }

    public static JavaTimeZone createTimeZone(String str) {
        java.util.TimeZone timeZone = AVAILABLESET.contains(str) ? java.util.TimeZone.getTimeZone(str) : null;
        if (timeZone == null) {
            boolean[] zArr = new boolean[1];
            String canonicalID = TimeZone.getCanonicalID(str, zArr);
            if (zArr[0] && AVAILABLESET.contains(canonicalID)) {
                timeZone = java.util.TimeZone.getTimeZone(canonicalID);
            }
        }
        if (timeZone == null) {
            return null;
        }
        return new JavaTimeZone(timeZone, str);
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.javatz.getOffset(i, i2, i3, i4, i5, i6);
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long j, boolean z, int[] iArr) {
        synchronized (this.javacal) {
            if (z) {
                int[] iArr2 = new int[6];
                Grego.timeToFields(j, iArr2);
                int i = iArr2[5];
                int i2 = i % 1000;
                int i3 = i / 1000;
                int i4 = i3 % 60;
                int i5 = i3 / 60;
                int i6 = i5 % 60;
                int i7 = i5 / 60;
                this.javacal.clear();
                this.javacal.set(iArr2[0], iArr2[1], iArr2[2], i7, i6, i4);
                this.javacal.set(14, i2);
                int i8 = this.javacal.get(6);
                int i9 = this.javacal.get(11);
                int i10 = this.javacal.get(12);
                int i11 = this.javacal.get(13);
                int i12 = this.javacal.get(14);
                if (!(iArr2[4] == i8 && i7 == i9 && i6 == i10 && i4 == i11 && i2 == i12)) {
                    this.javacal.setTimeInMillis((this.javacal.getTimeInMillis() - ((long) (((((((((((((Math.abs(i8 - iArr2[4]) > 1 ? 1 : i8 - iArr2[4]) * 24) + i9) - i7) * 60) + i10) - i6) * 60) + i11) - i4) * 1000) + i12) - i2))) - 1);
                }
            } else {
                this.javacal.setTimeInMillis(j);
            }
            iArr[0] = this.javacal.get(15);
            iArr[1] = this.javacal.get(16);
        }
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        return this.javatz.getRawOffset();
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
        JavaTimeZone javaTimeZone = (JavaTimeZone) super.cloneAsThawed();
        javaTimeZone.javatz = (java.util.TimeZone) this.javatz.clone();
        javaTimeZone.javacal = new GregorianCalendar(this.javatz);
        javaTimeZone.isFrozen = false;
        return javaTimeZone;
    }
}
