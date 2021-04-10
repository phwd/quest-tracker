package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0336Fl();
    public final Month F;
    public final Month G;
    public final Month H;
    public final DateValidator I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9691J;
    public final int K;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface DateValidator extends Parcelable {
    }

    public CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator, C0336Fl fl) {
        this.F = month;
        this.G = month2;
        this.H = month3;
        this.I = dateValidator;
        if (month.F.compareTo(month3.F) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (month3.F.compareTo(month2.F) <= 0) {
            this.K = month.x(month2) + 1;
            this.f9691J = (month2.I - month.I) + 1;
        } else {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        return this.F.equals(calendarConstraints.F) && this.G.equals(calendarConstraints.G) && this.H.equals(calendarConstraints.H) && this.I.equals(calendarConstraints.I);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H, this.I});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.F, 0);
        parcel.writeParcelable(this.G, 0);
        parcel.writeParcelable(this.H, 0);
        parcel.writeParcelable(this.I, 0);
    }
}
