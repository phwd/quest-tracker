package X;

import androidx.annotation.NonNull;

/* renamed from: X.a0  reason: case insensitive filesystem */
public final class C0279a0 implements AR {
    public Zw A00 = null;

    @Override // X.AR
    @NonNull
    public final AP getLifecycle() {
        Zw zw = this.A00;
        if (zw != null) {
            return zw;
        }
        Zw zw2 = new Zw(this);
        this.A00 = zw2;
        return zw2;
    }
}
