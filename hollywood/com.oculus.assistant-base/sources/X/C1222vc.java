package X;

import android.os.Bundle;

/* renamed from: X.vc  reason: case insensitive filesystem */
public final class C1222vc implements AbstractC0106Ak, AbstractC0388Vb {
    public static final Vn A01 = new Vn();
    public final String A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        return new C1222vc(bundle.getString("appId"));
    }

    public C1222vc(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
