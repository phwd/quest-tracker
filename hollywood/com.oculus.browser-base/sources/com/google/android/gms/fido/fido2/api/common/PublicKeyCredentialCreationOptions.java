package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialCreationOptions extends RequestOptions {
    public static final Parcelable.Creator CREATOR = new C4726sI1();
    public final PublicKeyCredentialRpEntity F;
    public final PublicKeyCredentialUserEntity G;
    public final byte[] H;
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final Double f9665J;
    public final List K;
    public final AuthenticatorSelectionCriteria L;
    public final Integer M;
    public final TokenBinding N;
    public final AttestationConveyancePreference O;
    public final AuthenticationExtensions P;

    public PublicKeyCredentialCreationOptions(PublicKeyCredentialRpEntity publicKeyCredentialRpEntity, PublicKeyCredentialUserEntity publicKeyCredentialUserEntity, byte[] bArr, List list, Double d, List list2, AuthenticatorSelectionCriteria authenticatorSelectionCriteria, Integer num, TokenBinding tokenBinding, String str, AuthenticationExtensions authenticationExtensions) {
        Objects.requireNonNull(publicKeyCredentialRpEntity, "null reference");
        this.F = publicKeyCredentialRpEntity;
        Objects.requireNonNull(publicKeyCredentialUserEntity, "null reference");
        this.G = publicKeyCredentialUserEntity;
        Objects.requireNonNull(bArr, "null reference");
        this.H = bArr;
        Objects.requireNonNull(list, "null reference");
        this.I = list;
        this.f9665J = d;
        this.K = list2;
        this.L = authenticatorSelectionCriteria;
        this.M = num;
        this.N = tokenBinding;
        if (str != null) {
            try {
                this.O = AttestationConveyancePreference.b(str);
            } catch (C2544fb e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.O = null;
        }
        this.P = authenticationExtensions;
    }

    public boolean equals(Object obj) {
        List list;
        List list2;
        if (!(obj instanceof PublicKeyCredentialCreationOptions)) {
            return false;
        }
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = (PublicKeyCredentialCreationOptions) obj;
        if (!AbstractC0895Oq0.a(this.F, publicKeyCredentialCreationOptions.F) || !AbstractC0895Oq0.a(this.G, publicKeyCredentialCreationOptions.G) || !Arrays.equals(this.H, publicKeyCredentialCreationOptions.H) || !AbstractC0895Oq0.a(this.f9665J, publicKeyCredentialCreationOptions.f9665J) || !this.I.containsAll(publicKeyCredentialCreationOptions.I) || !publicKeyCredentialCreationOptions.I.containsAll(this.I) || ((((list = this.K) != null || publicKeyCredentialCreationOptions.K != null) && (list == null || (list2 = publicKeyCredentialCreationOptions.K) == null || !list.containsAll(list2) || !publicKeyCredentialCreationOptions.K.containsAll(this.K))) || !AbstractC0895Oq0.a(this.L, publicKeyCredentialCreationOptions.L) || !AbstractC0895Oq0.a(this.M, publicKeyCredentialCreationOptions.M) || !AbstractC0895Oq0.a(this.N, publicKeyCredentialCreationOptions.N) || !AbstractC0895Oq0.a(this.O, publicKeyCredentialCreationOptions.O) || !AbstractC0895Oq0.a(this.P, publicKeyCredentialCreationOptions.P))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, Integer.valueOf(Arrays.hashCode(this.H)), this.I, this.f9665J, this.K, this.L, this.M, this.N, this.O, this.P});
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.f(parcel, 2, this.F, i, false);
        AbstractC5758yO0.f(parcel, 3, this.G, i, false);
        AbstractC5758yO0.b(parcel, 4, this.H, false);
        AbstractC5758yO0.k(parcel, 5, this.I, false);
        AbstractC5758yO0.c(parcel, 6, this.f9665J, false);
        AbstractC5758yO0.k(parcel, 7, this.K, false);
        AbstractC5758yO0.f(parcel, 8, this.L, i, false);
        AbstractC5758yO0.e(parcel, 9, this.M, false);
        AbstractC5758yO0.f(parcel, 10, this.N, i, false);
        AttestationConveyancePreference attestationConveyancePreference = this.O;
        if (attestationConveyancePreference == null) {
            str = null;
        } else {
            str = attestationConveyancePreference.f9661J;
        }
        AbstractC5758yO0.g(parcel, 11, str, false);
        AbstractC5758yO0.f(parcel, 12, this.P, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
