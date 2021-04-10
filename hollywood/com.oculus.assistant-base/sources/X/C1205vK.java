package X;

import android.os.Bundle;

/* renamed from: X.vK  reason: case insensitive filesystem */
public final class C1205vK implements AbstractC0106Ak, AbstractC0388Vb {
    public final int A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        int i = 0;
        if (bundle != null) {
            i = bundle.getInt("micVolume", 0);
        }
        return new C1205vK(i);
    }

    public C1205vK(int i) {
        this.A00 = i;
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }
}
