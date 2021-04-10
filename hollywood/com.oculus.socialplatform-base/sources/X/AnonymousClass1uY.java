package X;

import java.lang.ref.WeakReference;

/* renamed from: X.1uY  reason: invalid class name */
public class AnonymousClass1uY<T> extends WeakReference<AnonymousClass1uW> {
    public T A00;
    public final AnonymousClass1uj<T> A01;
    public final int A02;

    public AnonymousClass1uY(AnonymousClass1uW r2, int i, AnonymousClass1uj<T> r4) {
        super(r2, AnonymousClass1uW.sReferenceQueue);
        this.A02 = i;
        this.A01 = r4;
    }

    public final boolean A00() {
        boolean z;
        T t = this.A00;
        if (t != null) {
            this.A01.A9B(t);
            z = true;
        } else {
            z = false;
        }
        this.A00 = null;
        return z;
    }
}
