package X;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

/* renamed from: X.Uq  reason: case insensitive filesystem */
public abstract class AbstractC0188Uq<E> extends AnonymousClass3J<E> {
    public int A00 = 0;
    public boolean A01;
    public Object[] A02 = new Object[4];

    private void A00(int i) {
        Object[] objArr;
        Object[] objArr2 = this.A02;
        int length = objArr2.length;
        if (length < i) {
            if (i >= 0) {
                int i2 = length + (length >> 1) + 1;
                if (i2 < i) {
                    i2 = Integer.highestOneBit(i - 1) << 1;
                }
                if (i2 < 0) {
                    i2 = Integer.MAX_VALUE;
                }
                objArr = Arrays.copyOf(objArr2, i2);
            } else {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
        } else if (this.A01) {
            objArr = (Object[]) objArr2.clone();
        } else {
            return;
        }
        this.A02 = objArr;
        this.A01 = false;
    }

    @Override // X.AnonymousClass3J
    @CanIgnoreReturnValue
    public AbstractC0188Uq<E> add(E e) {
        if (e != null) {
            A00(this.A00 + 1);
            Object[] objArr = this.A02;
            int i = this.A00;
            this.A00 = i + 1;
            objArr[i] = e;
            return this;
        }
        throw null;
    }

    @Override // X.AnonymousClass3J
    @CanIgnoreReturnValue
    public AnonymousClass3J<E> add(E... eArr) {
        int length = eArr.length;
        for (int i = 0; i < length; i++) {
            if (eArr[i] == null) {
                throw new NullPointerException(AnonymousClass06.A01("at index ", i));
            }
        }
        A00(this.A00 + length);
        System.arraycopy(eArr, 0, this.A02, this.A00, length);
        this.A00 += length;
        return this;
    }
}
