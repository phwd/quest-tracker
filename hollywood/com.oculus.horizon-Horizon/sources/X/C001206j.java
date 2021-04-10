package X;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

/* renamed from: X.06j  reason: invalid class name and case insensitive filesystem */
public final class C001206j<E> extends AnonymousClass0CC<E> {
    public final Comparator<? super E> A00;

    /* renamed from: A07 */
    public final ImmutableSortedSet<E> build() {
        RegularImmutableSortedSet regularImmutableSortedSet;
        Object[] objArr = this.A02;
        Comparator<? super E> comparator = this.A00;
        int i = super.A00;
        if (i == 0) {
            regularImmutableSortedSet = ImmutableSortedSet.A04(comparator);
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                if (objArr[i2] == null) {
                    throw new NullPointerException(AnonymousClass006.A01("at index ", i2));
                }
            }
            Arrays.sort(objArr, 0, i, comparator);
            int i3 = 1;
            for (int i4 = 1; i4 < i; i4++) {
                Object obj = objArr[i4];
                if (comparator.compare(obj, objArr[i3 - 1]) != 0) {
                    objArr[i3] = obj;
                    i3++;
                }
            }
            Arrays.fill(objArr, i3, i, (Object) null);
            if (i3 < (objArr.length >> 1)) {
                objArr = Arrays.copyOf(objArr, i3);
            }
            regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.A0G(objArr, i3), comparator);
        }
        super.A00 = regularImmutableSortedSet.size();
        this.A01 = true;
        return regularImmutableSortedSet;
    }

    public C001206j(Comparator<? super E> comparator) {
        if (comparator != null) {
            this.A00 = comparator;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass0e3, X.AnonymousClass0CC, X.AbstractC06730pi
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AbstractC06730pi A02(Iterable iterable) {
        super.A03(iterable);
        return this;
    }

    @Override // X.AnonymousClass0CC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0CC A03(Iterable iterable) {
        super.A03(iterable);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0CC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0CC A04(Object obj) {
        super.A04(obj);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0CC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0CC A05(Object[] objArr) {
        super.A05(objArr);
        return this;
    }

    /* JADX WARN: Incorrect return type in method signature: ([TE;)LX/06j<TE;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public final void A08(Object... objArr) {
        super.A05(objArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0e3, X.AnonymousClass0CC, X.AbstractC06730pi
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AbstractC06730pi add(Object[] objArr) {
        super.A05(objArr);
        return this;
    }
}
