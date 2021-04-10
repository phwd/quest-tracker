package defpackage;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.zza;

/* renamed from: oE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4031oE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        PointF[] pointFArr = null;
        int i = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                pointFArr = (PointF[]) AbstractC5588xO0.h(parcel, readInt, PointF.CREATOR);
            } else if (i2 != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zza(pointFArr, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }
}
