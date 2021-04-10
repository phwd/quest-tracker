package X;

import androidx.annotation.NonNull;

/* renamed from: X.0KB  reason: invalid class name */
public class AnonymousClass0KB<T> extends AnonymousClass0sU<T> {
    public final Object A00 = new Object();

    @Override // X.AnonymousClass0sU
    public final T A00() {
        T t;
        synchronized (this.A00) {
            t = (T) super.A00();
        }
        return t;
    }

    @Override // X.AnonymousClass0sU
    public final boolean A01(@NonNull T t) {
        boolean A01;
        synchronized (this.A00) {
            A01 = super.A01(t);
        }
        return A01;
    }

    public AnonymousClass0KB(int i) {
        super(i);
    }
}
