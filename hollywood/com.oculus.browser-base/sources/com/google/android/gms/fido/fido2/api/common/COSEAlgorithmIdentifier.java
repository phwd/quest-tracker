package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class COSEAlgorithmIdentifier implements Parcelable {
    public static final Parcelable.Creator CREATOR = new BH1();
    public AbstractC2632g4 F;

    public COSEAlgorithmIdentifier(AbstractC2632g4 g4Var) {
        this.F = g4Var;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: QJ[] */
    /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: QJ */
    /* JADX WARN: Multi-variable type inference failed */
    public static COSEAlgorithmIdentifier b(int i) {
        GJ0 gj0;
        if (i == -262) {
            gj0 = GJ0.RS1;
        } else {
            GJ0[] values = GJ0.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    GJ0 gj02 = values[i2];
                    if (gj02.O == i) {
                        gj0 = gj02;
                        break;
                    }
                    i2++;
                } else {
                    QJ[] values2 = QJ.values();
                    for (QJ qj : values2) {
                        if (qj.L == i) {
                            gj0 = qj;
                        }
                    }
                    throw new C1431Xk(i);
                }
            }
        }
        return new COSEAlgorithmIdentifier(gj0);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof COSEAlgorithmIdentifier) && this.F.a() == ((COSEAlgorithmIdentifier) obj).F.a()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.F});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F.a());
    }
}
