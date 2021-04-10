package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LandmarkParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new TH1();
    public final int F;
    public final float G;
    public final float H;
    public final int I;

    public LandmarkParcel(int i, float f, float f2, int i2) {
        this.F = i;
        this.G = f;
        this.H = f2;
        this.I = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        float f = this.G;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeFloat(f);
        float f2 = this.H;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeFloat(f2);
        int i3 = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.n(parcel, l);
    }
}
