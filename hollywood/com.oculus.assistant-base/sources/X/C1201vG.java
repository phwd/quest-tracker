package X;

import android.os.Bundle;

/* renamed from: X.vG  reason: case insensitive filesystem */
public final class C1201vG implements AbstractC0106Ak, AbstractC0388Vb {
    public static final C0389Vc A01 = new C0389Vc();
    public final Integer A00;

    public C1201vG(Integer num) {
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
