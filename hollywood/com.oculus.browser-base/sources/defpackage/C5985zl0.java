package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.Month;

/* renamed from: zl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5985zl0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return Month.c(parcel.readInt(), parcel.readInt());
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new Month[i];
    }
}
