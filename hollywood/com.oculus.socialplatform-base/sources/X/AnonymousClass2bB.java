package X;

/* renamed from: X.2bB  reason: invalid class name */
public class AnonymousClass2bB<T> implements AnonymousClass2b9<T> {
    public int A00;
    public final Object[] A01 = new Object[256];

    @Override // X.AnonymousClass2b9
    public final void A90(T[] tArr, int i) {
        int length = tArr.length;
        if (i > length) {
            i = length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            int i3 = this.A00;
            Object[] objArr = this.A01;
            if (i3 < objArr.length) {
                objArr[i3] = t;
                this.A00 = i3 + 1;
            }
        }
    }

    @Override // X.AnonymousClass2b9
    public final T A19() {
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

    @Override // X.AnonymousClass2b9
    public final boolean A8z(T t) {
        int i = this.A00;
        Object[] objArr = this.A01;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.A00 = i + 1;
        return true;
    }
}
