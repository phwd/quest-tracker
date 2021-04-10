package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzai;

/* renamed from: oD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4028oD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        while (parcel.dataPosition() < u) {
            AbstractC5588xO0.t(parcel, parcel.readInt());
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzai();
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzai[i];
    }
}
