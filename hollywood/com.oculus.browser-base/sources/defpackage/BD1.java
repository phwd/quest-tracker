package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.vision.zzal;

/* renamed from: BD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        while (parcel.dataPosition() < u) {
            AbstractC5588xO0.t(parcel, parcel.readInt());
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzal();
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzal[i];
    }
}
