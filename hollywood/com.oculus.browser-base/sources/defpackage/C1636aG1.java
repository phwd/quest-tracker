package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;

/* renamed from: aG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1636aG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        byte[] bArr = null;
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        byte[] bArr5 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                bArr = AbstractC5588xO0.b(parcel, readInt);
            } else if (i == 3) {
                bArr2 = AbstractC5588xO0.b(parcel, readInt);
            } else if (i == 4) {
                bArr3 = AbstractC5588xO0.b(parcel, readInt);
            } else if (i == 5) {
                bArr4 = AbstractC5588xO0.b(parcel, readInt);
            } else if (i != 6) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                bArr5 = AbstractC5588xO0.b(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AuthenticatorAssertionResponse(bArr, bArr2, bArr3, bArr4, bArr5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticatorAssertionResponse[i];
    }
}
