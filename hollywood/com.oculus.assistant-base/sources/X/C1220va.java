package X;

import android.os.Bundle;

/* renamed from: X.va  reason: case insensitive filesystem */
public final class C1220va implements AbstractC0106Ak, AbstractC0388Vb {
    public static final Vl A01 = new Vl();
    public final String A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        return new C1220va(bundle.getString("appId"));
    }

    public C1220va(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
