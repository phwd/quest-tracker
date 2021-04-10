package X;

import androidx.annotation.NonNull;

/* renamed from: X.0WB  reason: invalid class name */
public class AnonymousClass0WB<T> extends C05520vs<T> {
    public final Object A00 = new Object();

    @Override // X.C05520vs, X.AnonymousClass06o
    public final T A19() {
        T t;
        synchronized (this.A00) {
            t = (T) super.A19();
        }
        return t;
    }

    @Override // X.C05520vs, X.AnonymousClass06o
    public final boolean A8z(@NonNull T t) {
        boolean A8z;
        synchronized (this.A00) {
            A8z = super.A8z(t);
        }
        return A8z;
    }

    public AnonymousClass0WB(int i) {
        super(i);
    }
}
