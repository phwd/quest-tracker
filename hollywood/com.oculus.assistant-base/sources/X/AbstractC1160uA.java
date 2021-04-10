package X;

import java.util.Arrays;

/* renamed from: X.uA  reason: case insensitive filesystem */
public abstract class AbstractC1160uA extends Tx {
    public int A00 = 0;
    public boolean A01;
    public Object[] A02 = new Object[4];

    private void A01(int i) {
        Object[] objArr;
        Object[] objArr2 = this.A02;
        int length = objArr2.length;
        if (length < i) {
            objArr = Arrays.copyOf(objArr2, Tx.A00(length, i));
        } else if (this.A01) {
            objArr = (Object[]) objArr2.clone();
        } else {
            return;
        }
        this.A02 = objArr;
        this.A01 = false;
    }

    @Override // X.Tx
    public AbstractC1160uA add(Object obj) {
        if (obj != null) {
            A01(this.A00 + 1);
            Object[] objArr = this.A02;
            int i = this.A00;
            this.A00 = i + 1;
            objArr[i] = obj;
            return this;
        }
        throw null;
    }

    @Override // X.Tx
    public Tx add(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AnonymousClass08.A00("at index ", i));
            }
        }
        A01(this.A00 + length);
        System.arraycopy(objArr, 0, this.A02, this.A00, length);
        this.A00 += length;
        return this;
    }
}
