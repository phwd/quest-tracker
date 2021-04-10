package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;

/* renamed from: cJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1996cJ1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 3) {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str3 = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredentialRpEntity(str, str2, str3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialRpEntity[i];
    }
}
