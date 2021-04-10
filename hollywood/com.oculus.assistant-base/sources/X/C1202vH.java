package X;

import android.os.Bundle;

/* renamed from: X.vH  reason: case insensitive filesystem */
public final class C1202vH implements AbstractC0106Ak, AbstractC0388Vb {
    public static final C0391Ve A01 = new C0391Ve();
    public final String A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        return new C1202vH(bundle.getString("message"));
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }

    public C1202vH() {
        this(null);
    }

    public C1202vH(String str) {
        this.A00 = str;
    }
}
