package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AuthenticatorAssertionResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR = new C1636aG1();
    public final byte[] F;
    public final byte[] G;
    public final byte[] H;
    public final byte[] I;

    /* renamed from: J  reason: collision with root package name */
    public final byte[] f9662J;

    public AuthenticatorAssertionResponse(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        Objects.requireNonNull(bArr, "null reference");
        this.F = bArr;
        Objects.requireNonNull(bArr2, "null reference");
        this.G = bArr2;
        Objects.requireNonNull(bArr3, "null reference");
        this.H = bArr3;
        Objects.requireNonNull(bArr4, "null reference");
        this.I = bArr4;
        this.f9662J = bArr5;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticatorAssertionResponse)) {
            return false;
        }
        AuthenticatorAssertionResponse authenticatorAssertionResponse = (AuthenticatorAssertionResponse) obj;
        if (!Arrays.equals(this.F, authenticatorAssertionResponse.F) || !Arrays.equals(this.G, authenticatorAssertionResponse.G) || !Arrays.equals(this.H, authenticatorAssertionResponse.H) || !Arrays.equals(this.I, authenticatorAssertionResponse.I) || !Arrays.equals(this.f9662J, authenticatorAssertionResponse.f9662J)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.F)), Integer.valueOf(Arrays.hashCode(this.G)), Integer.valueOf(Arrays.hashCode(this.H)), Integer.valueOf(Arrays.hashCode(this.I)), Integer.valueOf(Arrays.hashCode(this.f9662J))});
    }

    public String toString() {
        C3173jD1 a2 = AbstractC2149dD1.a(this);
        GE1 ge1 = GE1.f8076a;
        a2.a("keyHandle", ge1.a(this.F));
        a2.a("clientDataJSON", ge1.a(this.G));
        a2.a("authenticatorData", ge1.a(this.H));
        a2.a("signature", ge1.a(this.I));
        byte[] bArr = this.f9662J;
        if (bArr != null) {
            a2.a("userHandle", ge1.a(bArr));
        }
        return a2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.b(parcel, 2, this.F, false);
        AbstractC5758yO0.b(parcel, 3, this.G, false);
        AbstractC5758yO0.b(parcel, 4, this.H, false);
        AbstractC5758yO0.b(parcel, 5, this.I, false);
        AbstractC5758yO0.b(parcel, 6, this.f9662J, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
