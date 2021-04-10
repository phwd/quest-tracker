package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;

/* renamed from: mJ1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3704mJ1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        byte[] bArr = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = AbstractC5588xO0.b(parcel, readInt);
            } else if (i == 3) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 4) {
                str2 = AbstractC5588xO0.e(parcel, readInt);
            } else if (i != 5) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str3 = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredentialUserEntity(bArr, str, str2, str3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialUserEntity[i];
    }
}
