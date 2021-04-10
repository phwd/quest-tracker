package X;

import android.os.Bundle;

/* renamed from: X.vN  reason: case insensitive filesystem */
public final class C1208vN implements AbstractC0388Vb {
    @Override // X.AbstractC0388Vb
    public final Object A2C(Bundle bundle) {
        EnumC00528b r1;
        if (bundle != null) {
            r1 = (EnumC00528b) bundle.getSerializable("transcriptionState");
        } else {
            r1 = EnumC00528b.UNKNOWN;
        }
        if (r1 == null) {
            r1 = EnumC00528b.UNKNOWN;
        }
        return new C0809hx(r1);
    }
}
