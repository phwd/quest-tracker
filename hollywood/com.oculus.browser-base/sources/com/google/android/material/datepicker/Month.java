package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Month implements Comparable, Parcelable {
    public static final Parcelable.Creator CREATOR = new C5985zl0();
    public final Calendar F;
    public final String G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9692J;
    public final int K;
    public final long L;

    public Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar b = AbstractC2255ds1.b(calendar);
        this.F = b;
        this.H = b.get(2);
        this.I = b.get(1);
        this.f9692J = b.getMaximum(7);
        this.K = b.getActualMaximum(5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LLLL, yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(AbstractC2255ds1.c());
        this.G = simpleDateFormat.format(b.getTime());
        this.L = b.getTimeInMillis();
    }

    public static Month A() {
        return new Month(AbstractC2255ds1.d());
    }

    public static Month c(int i, int i2) {
        Calendar e = AbstractC2255ds1.e();
        e.set(1, i);
        e.set(2, i2);
        return new Month(e);
    }

    public static Month h(long j) {
        Calendar e = AbstractC2255ds1.e();
        e.setTimeInMillis(j);
        return new Month(e);
    }

    /* renamed from: b */
    public int compareTo(Month month) {
        return this.F.compareTo(month.F);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.H == month.H && this.I == month.I;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.H), Integer.valueOf(this.I)});
    }

    public int i() {
        int firstDayOfWeek = this.F.get(7) - this.F.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f9692J : firstDayOfWeek;
    }

    public Month t(int i) {
        Calendar b = AbstractC2255ds1.b(this.F);
        b.add(2, i);
        return new Month(b);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.I);
        parcel.writeInt(this.H);
    }

    public int x(Month month) {
        if (this.F instanceof GregorianCalendar) {
            return (month.H - this.H) + ((month.I - this.I) * 12);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }
}
