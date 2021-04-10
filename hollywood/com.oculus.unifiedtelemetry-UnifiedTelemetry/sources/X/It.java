package X;

import androidx.annotation.NonNull;

public class It<T> extends C0290aE<T> {
    public final Object A00 = new Object();

    @Override // X.C0290aE, X.AbstractC00126j
    public final T A10() {
        T t;
        synchronized (this.A00) {
            t = (T) super.A10();
        }
        return t;
    }

    @Override // X.C0290aE, X.AbstractC00126j
    public final boolean A4e(@NonNull T t) {
        boolean A4e;
        synchronized (this.A00) {
            A4e = super.A4e(t);
        }
        return A4e;
    }

    public It(int i) {
        super(i);
    }
}
