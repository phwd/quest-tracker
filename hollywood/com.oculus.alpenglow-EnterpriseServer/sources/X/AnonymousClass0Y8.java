package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.0Y8  reason: invalid class name */
public class AnonymousClass0Y8<E> extends AbstractC07410rk<E> {
    public AnonymousClass0tI<E> A00;
    public boolean A01;

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/0Y8<TE;>; */
    @CanIgnoreReturnValue
    public final void A00(Object obj, int i) {
        int i2;
        if (i != 0) {
            if (this.A01) {
                this.A00 = new AnonymousClass0tI<>(this.A00);
            }
            this.A01 = false;
            if (obj != null) {
                AnonymousClass0tI<E> r2 = this.A00;
                int A05 = r2.A05(obj);
                if (A05 == -1) {
                    i2 = 0;
                } else {
                    i2 = r2.A05[A05];
                }
                r2.A06(obj, i + i2);
                return;
            }
            throw null;
        }
    }

    @Override // X.AbstractC07410rk
    public final /* bridge */ /* synthetic */ ImmutableCollection build() {
        AnonymousClass0tI<E> r1 = this.A00;
        if (r1.A02 == 0) {
            return RegularImmutableMultiset.A03;
        }
        this.A01 = true;
        return new RegularImmutableMultiset(r1);
    }

    public AnonymousClass0Y8() {
        this(4);
    }

    public AnonymousClass0Y8(int i) {
        this.A01 = false;
        this.A00 = new AnonymousClass0tI<>(i);
    }

    @Override // X.AbstractC07410rk
    @CanIgnoreReturnValue
    public final AbstractC07410rk add(Object obj) {
        A00(obj, 1);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC07410rk
    @CanIgnoreReturnValue
    public final AbstractC07410rk add(Object[] objArr) {
        super.add(objArr);
        return this;
    }
}
