package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.datepicker.DateValidatorPointForward;

/* renamed from: nD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3855nD implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new DateValidatorPointForward(parcel.readLong(), null);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new DateValidatorPointForward[i];
    }
}
