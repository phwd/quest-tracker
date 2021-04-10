package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.C1728ParcelImpl;

/* renamed from: Bw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0115Bw0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new C1728ParcelImpl(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new C1728ParcelImpl[i];
    }
}
