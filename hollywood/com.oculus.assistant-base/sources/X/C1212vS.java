package X;

import android.os.Bundle;

/* renamed from: X.vS  reason: case insensitive filesystem */
public final class C1212vS implements AbstractC0106Ak, AbstractC0388Vb {
    public static final C0394Vh A01 = new C0394Vh();
    public final byte[] A00;

    public C1212vS(byte[] bArr) {
        C0514bB.A02(bArr, "data");
        this.A00 = bArr;
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
