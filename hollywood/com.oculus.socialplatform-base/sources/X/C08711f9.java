package X;

import androidx.annotation.NonNull;

/* renamed from: X.1f9  reason: invalid class name and case insensitive filesystem */
public final class C08711f9<Z> implements AnonymousClass1fR<Z>, AbstractC08161e6 {
    public static final AnonymousClass06o<C08711f9<?>> A04 = new C08131e2(new AnonymousClass0WB(20), new C08821fa(), AnonymousClass1e3.A00);
    public AnonymousClass1fR<Z> A00;
    public boolean A01;
    public boolean A02;
    public final AbstractC08341eQ A03 = new C08331eP();

    public final synchronized void A00() {
        this.A03.A00();
        if (this.A01) {
            this.A01 = false;
            if (this.A02) {
                A8u();
            }
        } else {
            throw new IllegalStateException("Already unlocked");
        }
    }

    @Override // X.AnonymousClass1fR
    public final synchronized void A8u() {
        this.A03.A00();
        this.A02 = true;
        if (!this.A01) {
            this.A00.A8u();
            this.A00 = null;
            A04.A8z(this);
        }
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Class<Z> A4o() {
        return this.A00.A4o();
    }

    @Override // X.AbstractC08161e6
    @NonNull
    public final AbstractC08341eQ A5G() {
        return this.A03;
    }

    @Override // X.AnonymousClass1fR
    @NonNull
    public final Z get() {
        return this.A00.get();
    }

    @Override // X.AnonymousClass1fR
    public final int getSize() {
        return this.A00.getSize();
    }
}
