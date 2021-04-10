package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialParameters extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new KI1();
    public final PublicKeyCredentialType F;
    public final COSEAlgorithmIdentifier G;

    public PublicKeyCredentialParameters(String str, int i) {
        Objects.requireNonNull(str, "null reference");
        try {
            this.F = PublicKeyCredentialType.b(str);
            Objects.requireNonNull(Integer.valueOf(i), "null reference");
            try {
                this.G = COSEAlgorithmIdentifier.b(i);
            } catch (C1431Xk e) {
                throw new IllegalArgumentException(e);
            }
        } catch (C5405wI0 e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublicKeyCredentialParameters)) {
            return false;
        }
        PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) obj;
        if (!this.F.equals(publicKeyCredentialParameters.F) || !this.G.equals(publicKeyCredentialParameters.G)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        Objects.requireNonNull(this.F);
        AbstractC5758yO0.g(parcel, 2, "public-key", false);
        AbstractC5758yO0.e(parcel, 3, Integer.valueOf(this.G.F.a()), false);
        AbstractC5758yO0.n(parcel, l);
    }
}
