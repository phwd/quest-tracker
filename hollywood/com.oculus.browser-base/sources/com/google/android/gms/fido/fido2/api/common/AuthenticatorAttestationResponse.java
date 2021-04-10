package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR = new C4037oG1();
    public final byte[] F;
    public final byte[] G;
    public final byte[] H;

    public AuthenticatorAttestationResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Objects.requireNonNull(bArr, "null reference");
        this.F = bArr;
        Objects.requireNonNull(bArr2, "null reference");
        this.G = bArr2;
        Objects.requireNonNull(bArr3, "null reference");
        this.H = bArr3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorAttestationResponse)) {
            return false;
        }
        AuthenticatorAttestationResponse authenticatorAttestationResponse = (AuthenticatorAttestationResponse) obj;
        if (!Arrays.equals(this.F, authenticatorAttestationResponse.F) || !Arrays.equals(this.G, authenticatorAttestationResponse.G) || !Arrays.equals(this.H, authenticatorAttestationResponse.H)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.F)), Integer.valueOf(Arrays.hashCode(this.G)), Integer.valueOf(Arrays.hashCode(this.H))});
    }

    public String toString() {
        C3173jD1 a2 = AbstractC2149dD1.a(this);
        GE1 ge1 = GE1.f8076a;
        a2.a("keyHandle", ge1.a(this.F));
        a2.a("clientDataJSON", ge1.a(this.G));
        a2.a("attestationObject", ge1.a(this.H));
        return a2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.b(parcel, 2, this.F, false);
        AbstractC5758yO0.b(parcel, 3, this.G, false);
        AbstractC5758yO0.b(parcel, 4, this.H, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
