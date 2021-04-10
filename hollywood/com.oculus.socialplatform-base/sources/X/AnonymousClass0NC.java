package X;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.0NC  reason: invalid class name */
public class AnonymousClass0NC<E> extends AnonymousClass0fR<E> {
    @CanIgnoreReturnValue
    public AnonymousClass0NC<E> A03(Iterable<? extends E> iterable) {
        if (iterable != null) {
            super.A02(iterable);
            return this;
        }
        throw null;
    }

    @CanIgnoreReturnValue
    public AnonymousClass0NC<E> A04(E e) {
        if (e != null) {
            super.add((Object) e);
            return this;
        }
        throw null;
    }

    /* renamed from: A06 */
    public ImmutableSet<E> build() {
        int i = this.A00;
        if (i == 0) {
            return RegularImmutableSet.A05;
        }
        if (i == 1) {
            return new SingletonImmutableSet(this.A02[0]);
        }
        ImmutableSet<E> A03 = ImmutableSet.A03(i, this.A02);
        this.A00 = A03.size();
        this.A01 = true;
        return A03;
    }

    @Override // X.AnonymousClass0fR, X.AbstractC05160uM
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ AbstractC05160uM A02(Iterable iterable) {
        A03(iterable);
        return this;
    }

    @CanIgnoreReturnValue
    public AnonymousClass0NC<E> A05(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0fR, X.AbstractC05160uM
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ AbstractC05160uM add(Object[] objArr) {
        A05(objArr);
        return this;
    }
}
