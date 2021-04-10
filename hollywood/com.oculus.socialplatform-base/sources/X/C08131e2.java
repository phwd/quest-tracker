package X;

import androidx.annotation.NonNull;

/* renamed from: X.1e2  reason: invalid class name and case insensitive filesystem */
public final class C08131e2<T> implements AnonymousClass06o<T> {
    public final AnonymousClass06o<T> A00;
    public final AbstractC08091dx<T> A01;
    public final AbstractC08141e4<T> A02;

    @Override // X.AnonymousClass06o
    public final T A19() {
        T A19 = this.A00.A19();
        if (A19 == null) {
            A19 = this.A01.A2N();
        }
        if (A19 instanceof AbstractC08161e6) {
            ((C08331eP) A19.A5G()).A00 = false;
        }
        return A19;
    }

    @Override // X.AnonymousClass06o
    public final boolean A8z(@NonNull T t) {
        if (t instanceof AbstractC08161e6) {
            ((C08331eP) t.A5G()).A00 = true;
        }
        this.A02.A9M(t);
        return this.A00.A8z(t);
    }

    public C08131e2(@NonNull AnonymousClass06o<T> r1, @NonNull AbstractC08091dx<T> r2, @NonNull AbstractC08141e4<T> r3) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = r3;
    }
}
