package X;

import com.google.android.gms.common.Feature;
import java.util.Arrays;

/* renamed from: X.Qr  reason: case insensitive filesystem */
public final class C0318Qr {
    public final Feature A00;
    public final C0315Qm A01;

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof C0318Qr)) {
            C0318Qr qr = (C0318Qr) obj;
            return RY.A00(this.A01, qr.A01) && RY.A00(this.A00, qr.A00);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A01, this.A00});
    }

    public final String toString() {
        RX rx = new RX(this);
        rx.A00("key", this.A01);
        rx.A00("feature", this.A00);
        return rx.toString();
    }

    public C0318Qr(C0315Qm qm, Feature feature) {
        this.A01 = qm;
        this.A00 = feature;
    }
}
