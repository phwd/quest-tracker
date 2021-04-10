package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: androidx.versionedparcelable.ParcelImpl  reason: case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1728ParcelImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0115Bw0();
    public final Ns1 F;

    public C1728ParcelImpl(Ns1 ns1) {
        this.F = ns1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        new Ms1(parcel).o(this.F);
    }

    public C1728ParcelImpl(Parcel parcel) {
        this.F = new Ms1(parcel).k();
    }
}
