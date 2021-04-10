package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.zzl;
import com.google.android.gms.fido.fido2.api.common.zzm;
import java.util.ArrayList;

/* renamed from: dI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2164dI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        ArrayList arrayList = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                arrayList = AbstractC5588xO0.i(parcel, readInt, zzl.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzm(arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
