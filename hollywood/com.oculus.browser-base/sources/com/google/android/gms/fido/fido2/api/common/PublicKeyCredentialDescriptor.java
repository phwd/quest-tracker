package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PublicKeyCredentialDescriptor extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new EI1();
    public final PublicKeyCredentialType F;
    public final byte[] G;
    public final List H;

    static {
        KE1 ke1 = AbstractC5060uG1.f11402a;
        KE1 ke12 = AbstractC5060uG1.b;
        int i = AbstractC1630aE1.G;
        int i2 = 2;
        Object[] objArr = {ke1, ke12};
        while (true) {
            if (i2 == 0) {
                C3347kE1 ke13 = C3347kE1.I;
                break;
            }
            if (i2 == 1) {
                new BE1(objArr[0]);
                break;
            }
            int f = AbstractC1630aE1.f(i2);
            Object[] objArr2 = new Object[f];
            int i3 = f - 1;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                Object obj = objArr[i6];
                AbstractC2322eE1.a(obj, i6);
                int hashCode = obj.hashCode();
                int a2 = GD1.a(hashCode);
                while (true) {
                    int i7 = a2 & i3;
                    Object obj2 = objArr2[i7];
                    if (obj2 == null) {
                        objArr[i5] = obj;
                        objArr2[i7] = obj;
                        i4 += hashCode;
                        i5++;
                        break;
                    }
                    if (obj2.equals(obj)) {
                        break;
                    }
                    a2++;
                }
            }
            Arrays.fill(objArr, i5, i2, (Object) null);
            if (i5 == 1) {
                new BE1(objArr[0], i4);
                break;
            } else if (AbstractC1630aE1.f(i5) < f / 2) {
                i2 = i5;
            } else {
                if (i5 < 1) {
                    objArr = Arrays.copyOf(objArr, i5);
                }
                new C3347kE1(objArr, i4, objArr2, i3, i5);
            }
        }
    }

    public PublicKeyCredentialDescriptor(String str, byte[] bArr, List list) {
        Objects.requireNonNull(str, "null reference");
        try {
            this.F = PublicKeyCredentialType.b(str);
            Objects.requireNonNull(bArr, "null reference");
            this.G = bArr;
            this.H = list;
        } catch (C5405wI0 e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object obj) {
        List list;
        if (!(obj instanceof PublicKeyCredentialDescriptor)) {
            return false;
        }
        PublicKeyCredentialDescriptor publicKeyCredentialDescriptor = (PublicKeyCredentialDescriptor) obj;
        if (!this.F.equals(publicKeyCredentialDescriptor.F) || !Arrays.equals(this.G, publicKeyCredentialDescriptor.G)) {
            return false;
        }
        List list2 = this.H;
        if (list2 == null && publicKeyCredentialDescriptor.H == null) {
            return true;
        }
        if (list2 == null || (list = publicKeyCredentialDescriptor.H) == null || !list2.containsAll(list) || !publicKeyCredentialDescriptor.H.containsAll(this.H)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F, Integer.valueOf(Arrays.hashCode(this.G)), this.H});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        Objects.requireNonNull(this.F);
        AbstractC5758yO0.g(parcel, 2, "public-key", false);
        AbstractC5758yO0.b(parcel, 3, this.G, false);
        AbstractC5758yO0.k(parcel, 4, this.H, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
