package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0237Dw0();
    public int F;
    public int G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f9452J;

    public ParcelableVolumeInfo(int i, int i2, int i3, int i4, int i5) {
        this.F = i;
        this.G = i2;
        this.H = i3;
        this.I = i4;
        this.f9452J = i5;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeInt(this.H);
        parcel.writeInt(this.I);
        parcel.writeInt(this.f9452J);
        parcel.writeInt(this.G);
    }

    public ParcelableVolumeInfo(Parcel parcel) {
        this.F = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readInt();
        this.f9452J = parcel.readInt();
        this.G = parcel.readInt();
    }
}
