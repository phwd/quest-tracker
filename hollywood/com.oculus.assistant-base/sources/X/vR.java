package X;

import android.os.Bundle;

public final class vR implements AbstractC0106Ak, AbstractC0388Vb {
    public static final C0393Vg A01 = new C0393Vg();
    public final String A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        String string = bundle.getString("message");
        bundle.getString("voice");
        return new vR(string);
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }

    public vR() {
        this(null);
    }

    public vR(String str) {
        this.A00 = str;
    }
}
