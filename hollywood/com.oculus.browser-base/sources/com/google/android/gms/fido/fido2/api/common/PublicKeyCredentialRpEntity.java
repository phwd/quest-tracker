package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialRpEntity extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C1996cJ1();
    public final String F;
    public final String G;
    public final String H;

    public PublicKeyCredentialRpEntity(String str, String str2, String str3) {
        Objects.requireNonNull(str, "null reference");
        this.F = str;
        Objects.requireNonNull(str2, "null reference");
        this.G = str2;
        this.H = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublicKeyCredentialRpEntity)) {
            return false;
        }
        PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = (PublicKeyCredentialRpEntity) obj;
        if (!AbstractC0895Oq0.a(this.F, publicKeyCredentialRpEntity.F) || !AbstractC0895Oq0.a(this.G, publicKeyCredentialRpEntity.G) || !AbstractC0895Oq0.a(this.H, publicKeyCredentialRpEntity.H)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
