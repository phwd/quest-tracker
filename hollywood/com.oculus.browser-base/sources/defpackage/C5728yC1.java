package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension;

/* renamed from: yC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5728yC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        boolean z = false;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                z = AbstractC5588xO0.k(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new UserVerificationMethodExtension(z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new UserVerificationMethodExtension[i];
    }
}
