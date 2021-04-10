package X;

import androidx.annotation.NonNull;

/* renamed from: X.1BN  reason: invalid class name */
public final class AnonymousClass1BN implements AnonymousClass2OR {
    @NonNull
    public final AnonymousClass1Aj A00;

    @Override // X.AnonymousClass2OR
    public final void A6p(int i, int i2, Object obj) {
        this.A00.notifyItemRangeChanged(i, i2, obj);
    }

    @Override // X.AnonymousClass2OR
    public final void A7A(int i, int i2) {
        this.A00.notifyItemRangeInserted(i, i2);
    }

    @Override // X.AnonymousClass2OR
    public final void A7L(int i, int i2) {
        this.A00.notifyItemMoved(i, i2);
    }

    @Override // X.AnonymousClass2OR
    public final void A7k(int i, int i2) {
        this.A00.notifyItemRangeRemoved(i, i2);
    }

    public AnonymousClass1BN(@NonNull AnonymousClass1Aj r1) {
        this.A00 = r1;
    }
}
