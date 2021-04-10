package X;

import androidx.core.util.Pools;

/* renamed from: X.0MS  reason: invalid class name */
public class AnonymousClass0MS<T> extends Pools.SimplePool<T> {
    public int A00;
    public final Object[] A01;
    public final Object A02 = new Object();

    public final T A00() {
        T t;
        synchronized (this.A02) {
            int i = this.A00;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = this.A01;
                t = (T) objArr[i2];
                objArr[i2] = null;
                this.A00 = i2;
            } else {
                t = null;
            }
        }
        return t;
    }

    public AnonymousClass0MS(int i) {
        this.A01 = new Object[i];
    }
}
