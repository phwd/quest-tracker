package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import java.util.ArrayList;

/* renamed from: EI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        byte[] bArr = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                str = AbstractC5588xO0.e(parcel, readInt);
            } else if (i == 3) {
                bArr = AbstractC5588xO0.b(parcel, readInt);
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                arrayList = AbstractC5588xO0.i(parcel, readInt, Transport.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredentialDescriptor(str, bArr, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialDescriptor[i];
    }
}
