package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension;

/* renamed from: kI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3359kI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                str = AbstractC5588xO0.e(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new FidoAppIdExtension(str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new FidoAppIdExtension[i];
    }
}
