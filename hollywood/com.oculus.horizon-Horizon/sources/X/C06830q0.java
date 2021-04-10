package X;

import javax.annotation.Nullable;

/* renamed from: X.0q0  reason: invalid class name and case insensitive filesystem */
public class C06830q0 implements AnonymousClass0HZ {
    public final AnonymousClass0HZ A00;
    @Nullable
    public final C00980Hw A01;

    @Override // X.AnonymousClass0HZ
    public final void A65() {
        this.A00.A65();
        C00980Hw r0 = this.A01;
        if (r0 != null) {
            r0.A01.stopSelf(r0.A00);
        }
    }

    @Override // X.AnonymousClass0HZ
    public final void A7D(boolean z) {
        this.A00.A7D(z);
    }

    public C06830q0(AnonymousClass0HZ r1, @Nullable C00980Hw r2) {
        this.A00 = r1;
        this.A01 = r2;
    }
}
