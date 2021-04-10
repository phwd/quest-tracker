package X;

import android.os.Bundle;

/* renamed from: X.vO  reason: case insensitive filesystem */
public final class C1209vO implements AbstractC0106Ak, AbstractC0388Vb {
    public final VV A00;

    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        VV vv;
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle("configuration")) == null) {
            vv = null;
        } else {
            vv = new VV();
            vv.A02 = bundle2.getBoolean("multiphrase");
            vv.A01 = bundle2.getString("scenario", vv.A01);
            vv.A00 = bundle2.getString("input_type", vv.A00);
        }
        return new C1209vO(vv);
    }

    @Override // X.AbstractC0106Ak
    public final Object A2L() {
        return this;
    }

    public C1209vO() {
        this.A00 = null;
    }

    public C1209vO(VV vv) {
        this.A00 = vv;
    }
}
