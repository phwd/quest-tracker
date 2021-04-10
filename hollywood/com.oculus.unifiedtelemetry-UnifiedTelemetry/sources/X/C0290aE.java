package X;

import androidx.annotation.NonNull;

/* renamed from: X.aE  reason: case insensitive filesystem */
public class C0290aE<T> implements AbstractC00126j<T> {
    public int A00;
    public final Object[] A01;

    @Override // X.AbstractC00126j
    public boolean A4e(@NonNull T t) {
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

    @Override // X.AbstractC00126j
    public T A10() {
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

    public C0290aE(int i) {
        this.A01 = new Object[i];
    }
}
