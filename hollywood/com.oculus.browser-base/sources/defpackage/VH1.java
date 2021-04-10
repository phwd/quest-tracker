package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.zzl;

/* renamed from: VH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        long j = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                j = AbstractC5588xO0.r(parcel, readInt);
            } else if (i == 2) {
                bArr = AbstractC5588xO0.b(parcel, readInt);
            } else if (i == 3) {
                bArr2 = AbstractC5588xO0.b(parcel, readInt);
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                bArr3 = AbstractC5588xO0.b(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzl(j, bArr, bArr2, bArr3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzl[i];
    }
}
