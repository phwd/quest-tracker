package X;

import android.os.Bundle;

/* renamed from: X.ve  reason: case insensitive filesystem */
public final class C1224ve implements AbstractC0106Ak, AbstractC0388Vb {
    public static final C0395Vp A02 = new C0395Vp();
    public final String A00;
    public final String A01;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        return new C1224ve(bundle.getString("appId"), bundle.getString("actionId"));
    }

    public C1224ve(String str, String str2) {
        this.A01 = str;
        this.A00 = str2;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
