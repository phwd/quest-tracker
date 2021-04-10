package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialRequestOptions extends RequestOptions {
    public static final Parcelable.Creator CREATOR = new QI1();
    public final byte[] F;
    public final Double G;
    public final String H;
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final Integer f9666J;
    public final TokenBinding K;
    public final zzad L;
    public final AuthenticationExtensions M;

    public PublicKeyCredentialRequestOptions(byte[] bArr, Double d, String str, List list, Integer num, TokenBinding tokenBinding, String str2, AuthenticationExtensions authenticationExtensions) {
        Objects.requireNonNull(bArr, "null reference");
        this.F = bArr;
        this.G = d;
        Objects.requireNonNull(str, "null reference");
        this.H = str;
        this.I = list;
        this.f9666J = num;
        this.K = tokenBinding;
        if (str2 != null) {
            try {
                this.L = zzad.b(str2);
            } catch (QC1 e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.L = null;
        }
        this.M = authenticationExtensions;
    }

    public boolean equals(Object obj) {
        List list;
        List list2;
        if (!(obj instanceof PublicKeyCredentialRequestOptions)) {
            return false;
        }
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = (PublicKeyCredentialRequestOptions) obj;
        if (!Arrays.equals(this.F, publicKeyCredentialRequestOptions.F) || !AbstractC0895Oq0.a(this.G, publicKeyCredentialRequestOptions.G) || !AbstractC0895Oq0.a(this.H, publicKeyCredentialRequestOptions.H) || ((((list = this.I) != null || publicKeyCredentialRequestOptions.I != null) && (list == null || (list2 = publicKeyCredentialRequestOptions.I) == null || !list.containsAll(list2) || !publicKeyCredentialRequestOptions.I.containsAll(this.I))) || !AbstractC0895Oq0.a(this.f9666J, publicKeyCredentialRequestOptions.f9666J) || !AbstractC0895Oq0.a(this.K, publicKeyCredentialRequestOptions.K) || !AbstractC0895Oq0.a(this.L, publicKeyCredentialRequestOptions.L) || !AbstractC0895Oq0.a(this.M, publicKeyCredentialRequestOptions.M))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.F)), this.G, this.H, this.I, this.f9666J, this.K, this.L, this.M});
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str;
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.b(parcel, 2, this.F, false);
        AbstractC5758yO0.c(parcel, 3, this.G, false);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.k(parcel, 5, this.I, false);
        AbstractC5758yO0.e(parcel, 6, this.f9666J, false);
        AbstractC5758yO0.f(parcel, 7, this.K, i, false);
        zzad zzad = this.L;
        if (zzad == null) {
            str = null;
        } else {
            str = zzad.f9668J;
        }
        AbstractC5758yO0.g(parcel, 8, str, false);
        AbstractC5758yO0.f(parcel, 9, this.M, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
