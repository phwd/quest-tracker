package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public class N4<E> extends Qe<E> {
    public RB<E> A00;
    public boolean A01;

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/N4<TE;>; */
    @CanIgnoreReturnValue
    public final void A00(Object obj, int i) {
        int i2;
        if (i != 0) {
            if (this.A01) {
                this.A00 = new RB<>(this.A00);
            }
            this.A01 = false;
            if (obj != null) {
                RB<E> rb = this.A00;
                int A04 = rb.A04(obj);
                if (A04 == -1) {
                    i2 = 0;
                } else {
                    i2 = rb.A05[A04];
                }
                rb.A05(obj, i + i2);
                return;
            }
            throw null;
        }
    }

    @Override // X.Qe
    public final /* bridge */ /* synthetic */ ImmutableCollection build() {
        RB<E> rb = this.A00;
        if (rb.A02 == 0) {
            return RegularImmutableMultiset.A03;
        }
        this.A01 = true;
        return new RegularImmutableMultiset(rb);
    }

    public N4() {
        this(4);
    }

    public N4(int i) {
        this.A01 = false;
        this.A00 = new RB<>(i);
    }

    @Override // X.Qe
    @CanIgnoreReturnValue
    public final Qe add(Object obj) {
        A00(obj, 1);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.Qe
    @CanIgnoreReturnValue
    public final Qe add(Object[] objArr) {
        super.add(objArr);
        return this;
    }
}
