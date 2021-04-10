package X;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;

/* renamed from: X.0fR  reason: invalid class name */
public abstract class AnonymousClass0fR<E> extends AbstractC05160uM<E> {
    public int A00 = 0;
    public boolean A01;
    public Object[] A02 = new Object[4];

    public AnonymousClass0fR() {
        AnonymousClass0th.A00(4, "initialCapacity");
    }

    private void A00(int i) {
        Object[] objArr;
        Object[] objArr2 = this.A02;
        int length = objArr2.length;
        if (length < i) {
            objArr = Arrays.copyOf(objArr2, AbstractC05160uM.A01(length, i));
        } else if (this.A01) {
            objArr = (Object[]) objArr2.clone();
        } else {
            return;
        }
        this.A02 = objArr;
        this.A01 = false;
    }

    @Override // X.AbstractC05160uM
    @CanIgnoreReturnValue
    public AbstractC05160uM<E> A02(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            A00(this.A00 + collection.size());
            if (collection instanceof ImmutableCollection) {
                this.A00 = ((ImmutableCollection) collection).A0G(this.A02, this.A00);
                return this;
            }
        }
        super.A02(iterable);
        return this;
    }

    @Override // X.AbstractC05160uM
    @CanIgnoreReturnValue
    public AnonymousClass0fR<E> add(E e) {
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

    @Override // X.AbstractC05160uM
    @CanIgnoreReturnValue
    public AbstractC05160uM<E> add(E... eArr) {
        int length = eArr.length;
        for (int i = 0; i < length; i++) {
            if (eArr[i] == null) {
                throw new NullPointerException(AnonymousClass006.A03("at index ", i));
            }
        }
        A00(this.A00 + length);
        System.arraycopy(eArr, 0, this.A02, this.A00, length);
        this.A00 += length;
        return this;
    }
}
