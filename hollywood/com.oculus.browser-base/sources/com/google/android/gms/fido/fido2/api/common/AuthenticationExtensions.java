package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticationExtensions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new AF1();
    public final FidoAppIdExtension F;
    public final zzm G;
    public final UserVerificationMethodExtension H;

    public AuthenticationExtensions(FidoAppIdExtension fidoAppIdExtension, zzm zzm, UserVerificationMethodExtension userVerificationMethodExtension) {
        this.F = fidoAppIdExtension;
        this.H = userVerificationMethodExtension;
        this.G = zzm;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticationExtensions)) {
            return false;
        }
        AuthenticationExtensions authenticationExtensions = (AuthenticationExtensions) obj;
        if (!AbstractC0895Oq0.a(this.F, authenticationExtensions.F) || !AbstractC0895Oq0.a(this.G, authenticationExtensions.G) || !AbstractC0895Oq0.a(this.H, authenticationExtensions.H)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.f(parcel, 4, this.H, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
