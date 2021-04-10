package X;

import android.os.Bundle;

/* renamed from: X.vU  reason: case insensitive filesystem */
public final class C1214vU implements AbstractC0106Ak, AbstractC0388Vb {
    public static final Vj A01 = new Vj();
    public final Integer A00;

    public C1214vU(Integer num) {
        C0514bB.A02(num, "state");
        this.A00 = num;
    }

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        C0514bB.A02(bundle, "bundle");
        throw new C0466aB("One Way Message");
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
