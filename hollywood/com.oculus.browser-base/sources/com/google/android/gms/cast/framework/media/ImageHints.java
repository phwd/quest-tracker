package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ImageHints extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3524lG1();
    public final int F;
    public final int G;
    public final int H;

    public ImageHints(int i, int i2, int i3) {
        this.F = i;
        this.G = i2;
        this.H = i3;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        int i3 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i3);
        int i4 = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.n(parcel, l);
    }
}
