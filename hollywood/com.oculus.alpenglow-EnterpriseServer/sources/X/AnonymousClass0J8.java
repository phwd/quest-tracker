package X;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.0J8  reason: invalid class name */
public class AnonymousClass0J8<E> extends AnonymousClass0YB<E> {
    @CanIgnoreReturnValue
    public AnonymousClass0J8<E> A00(E e) {
        if (e != null) {
            super.add((Object) e);
            return this;
        }
        throw null;
    }

    /* renamed from: A02 */
    public ImmutableSet<E> build() {
        int i = this.A00;
        if (i == 0) {
            return RegularImmutableSet.A05;
        }
        if (i == 1) {
            return new SingletonImmutableSet(this.A02[0]);
        }
        ImmutableSet<E> A06 = ImmutableSet.A06(i, this.A02);
        this.A00 = A06.size();
        this.A01 = true;
        return A06;
    }

    @CanIgnoreReturnValue
    public AnonymousClass0J8<E> A01(E... eArr) {
        super.add((Object[]) eArr);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0YB, X.AbstractC07410rk
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ AbstractC07410rk add(Object[] objArr) {
        A01(objArr);
        return this;
    }
}
