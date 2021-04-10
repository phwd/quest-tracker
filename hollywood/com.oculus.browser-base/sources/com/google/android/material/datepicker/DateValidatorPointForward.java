package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DateValidatorPointForward implements CalendarConstraints.DateValidator {
    public static final Parcelable.Creator CREATOR = new C3855nD();
    public final long F;

    public DateValidatorPointForward(long j) {
        this.F = j;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DateValidatorPointForward)) {
            return false;
        }
        return this.F == ((DateValidatorPointForward) obj).F;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.F)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.F);
    }

    public DateValidatorPointForward(long j, C3855nD nDVar) {
        this.F = j;
    }
}
