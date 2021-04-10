package com.google.android.gms.vision.face.internal.client;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4031oE1();
    public final PointF[] F;
    public final int G;

    public zza(PointF[] pointFArr, int i) {
        this.F = pointFArr;
        this.G = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.j(parcel, 2, this.F, i, false);
        int i2 = this.G;
        AbstractC5758yO0.o(parcel, 3, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.n(parcel, l);
    }
}
