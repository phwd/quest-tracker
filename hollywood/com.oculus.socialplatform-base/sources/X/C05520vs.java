package X;

import androidx.annotation.NonNull;

/* renamed from: X.0vs  reason: invalid class name and case insensitive filesystem */
public class C05520vs<T> implements AnonymousClass06o<T> {
    public int A00;
    public final Object[] A01;

    @Override // X.AnonymousClass06o
    public boolean A8z(@NonNull T t) {
        int i = 0;
        while (true) {
            int i2 = this.A00;
            if (i >= i2) {
                Object[] objArr = this.A01;
                if (i2 >= objArr.length) {
                    return false;
                }
                objArr[i2] = t;
                this.A00 = i2 + 1;
                return true;
            } else if (this.A01[i] == t) {
                throw new IllegalStateException("Already in the pool!");
            } else {
                i++;
            }
        }
    }

    @Override // X.AnonymousClass06o
    public T A19() {
        int i = this.A00;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.A01;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.A00 = i2;
        return t;
    }

    public C05520vs(int i) {
        if (i > 0) {
            this.A01 = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }
}
