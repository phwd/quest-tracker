package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;

/* renamed from: X.0e3  reason: invalid class name */
public abstract class AnonymousClass0e3<E> extends AbstractC06730pi<E> {
    public int A00 = 0;
    public boolean A01;
    public Object[] A02 = new Object[4];

    private void A00(int i) {
        Object[] objArr;
        Object[] objArr2 = this.A02;
        int length = objArr2.length;
        if (length < i) {
            objArr = Arrays.copyOf(objArr2, AbstractC06730pi.A01(length, i));
        } else if (this.A01) {
            objArr = (Object[]) objArr2.clone();
        } else {
            return;
        }
        this.A02 = objArr;
        this.A01 = false;
    }

    @Override // X.AbstractC06730pi
    @CanIgnoreReturnValue
    public AbstractC06730pi<E> A02(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            A00(this.A00 + collection.size());
            if (collection instanceof ImmutableCollection) {
                this.A00 = ((ImmutableCollection) collection).A0I(this.A02, this.A00);
                return this;
            }
        }
        super.A02(iterable);
        return this;
    }

    @Override // X.AbstractC06730pi
    @CanIgnoreReturnValue
    public AnonymousClass0e3<E> add(E e) {
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

    @Override // X.AbstractC06730pi
    @CanIgnoreReturnValue
    public AbstractC06730pi<E> add(E... eArr) {
        int length = eArr.length;
        for (int i = 0; i < length; i++) {
            if (eArr[i] == null) {
                throw new NullPointerException(AnonymousClass006.A01("at index ", i));
            }
        }
        A00(this.A00 + length);
        System.arraycopy(eArr, 0, this.A02, this.A00, length);
        this.A00 += length;
        return this;
    }
}
