package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5916zI1();
    public final String F;
    public final String G;
    public final byte[] H;
    public final AuthenticatorAttestationResponse I;

    /* renamed from: J  reason: collision with root package name */
    public final AuthenticatorAssertionResponse f9664J;
    public final AuthenticatorErrorResponse K;
    public final AuthenticationExtensionsClientOutputs L;

    public PublicKeyCredential(String str, String str2, byte[] bArr, AuthenticatorAttestationResponse authenticatorAttestationResponse, AuthenticatorAssertionResponse authenticatorAssertionResponse, AuthenticatorErrorResponse authenticatorErrorResponse, AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs) {
        SE0.a((authenticatorAttestationResponse != null && authenticatorAssertionResponse == null && authenticatorErrorResponse == null) || (authenticatorAttestationResponse == null && authenticatorAssertionResponse != null && authenticatorErrorResponse == null) || (authenticatorAttestationResponse == null && authenticatorAssertionResponse == null && authenticatorErrorResponse != null));
        this.F = str;
        this.G = str2;
        this.H = bArr;
        this.I = authenticatorAttestationResponse;
        this.f9664J = authenticatorAssertionResponse;
        this.K = authenticatorErrorResponse;
        this.L = authenticationExtensionsClientOutputs;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublicKeyCredential)) {
            return false;
        }
        PublicKeyCredential publicKeyCredential = (PublicKeyCredential) obj;
        if (!AbstractC0895Oq0.a(this.F, publicKeyCredential.F) || !AbstractC0895Oq0.a(this.G, publicKeyCredential.G) || !Arrays.equals(this.H, publicKeyCredential.H) || !AbstractC0895Oq0.a(this.I, publicKeyCredential.I) || !AbstractC0895Oq0.a(this.f9664J, publicKeyCredential.f9664J) || !AbstractC0895Oq0.a(this.K, publicKeyCredential.K) || !AbstractC0895Oq0.a(this.L, publicKeyCredential.L)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H, this.f9664J, this.I, this.K, this.L});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 1, this.F, false);
        AbstractC5758yO0.g(parcel, 2, this.G, false);
        AbstractC5758yO0.b(parcel, 3, this.H, false);
        AbstractC5758yO0.f(parcel, 4, this.I, i, false);
        AbstractC5758yO0.f(parcel, 5, this.f9664J, i, false);
        AbstractC5758yO0.f(parcel, 6, this.K, i, false);
        AbstractC5758yO0.f(parcel, 7, this.L, i, false);
        AbstractC5758yO0.n(parcel, l);
    }

    public AuthenticatorResponse x() {
        AuthenticatorAttestationResponse authenticatorAttestationResponse = this.I;
        if (authenticatorAttestationResponse != null) {
            return authenticatorAttestationResponse;
        }
        AuthenticatorAssertionResponse authenticatorAssertionResponse = this.f9664J;
        if (authenticatorAssertionResponse != null) {
            return authenticatorAssertionResponse;
        }
        AuthenticatorErrorResponse authenticatorErrorResponse = this.K;
        if (authenticatorErrorResponse != null) {
            return authenticatorErrorResponse;
        }
        throw new IllegalStateException("No response set.");
    }
}
