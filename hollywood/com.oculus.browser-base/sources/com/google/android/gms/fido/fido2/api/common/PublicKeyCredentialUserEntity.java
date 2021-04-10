package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialUserEntity extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3704mJ1();
    public final byte[] F;
    public final String G;
    public final String H;
    public final String I;

    public PublicKeyCredentialUserEntity(byte[] bArr, String str, String str2, String str3) {
        Objects.requireNonNull(bArr, "null reference");
        this.F = bArr;
        Objects.requireNonNull(str, "null reference");
        this.G = str;
        this.H = str2;
        Objects.requireNonNull(str3, "null reference");
        this.I = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublicKeyCredentialUserEntity)) {
            return false;
        }
        PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = (PublicKeyCredentialUserEntity) obj;
        if (!Arrays.equals(this.F, publicKeyCredentialUserEntity.F) || !AbstractC0895Oq0.a(this.G, publicKeyCredentialUserEntity.G) || !AbstractC0895Oq0.a(this.H, publicKeyCredentialUserEntity.H) || !AbstractC0895Oq0.a(this.I, publicKeyCredentialUserEntity.I)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, this.G, this.H, this.I});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.b(parcel, 2, this.F, false);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        AbstractC5758yO0.g(parcel, 5, this.I, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
