package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension;
import com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension;
import com.google.android.gms.fido.fido2.api.common.zzm;

/* renamed from: AF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        FidoAppIdExtension fidoAppIdExtension = null;
        zzm zzm = null;
        UserVerificationMethodExtension userVerificationMethodExtension = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                fidoAppIdExtension = (FidoAppIdExtension) AbstractC5588xO0.d(parcel, readInt, FidoAppIdExtension.CREATOR);
            } else if (i == 3) {
                zzm = (zzm) AbstractC5588xO0.d(parcel, readInt, zzm.CREATOR);
            } else if (i != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                userVerificationMethodExtension = (UserVerificationMethodExtension) AbstractC5588xO0.d(parcel, readInt, UserVerificationMethodExtension.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AuthenticationExtensions(fidoAppIdExtension, zzm, userVerificationMethodExtension);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticationExtensions[i];
    }
}
