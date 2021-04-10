package X;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

/* renamed from: X.0BY  reason: invalid class name */
public final class AnonymousClass0BY<E> extends AnonymousClass0NC<E> {
    public final Comparator<? super E> A00;

    /* renamed from: A07 */
    public final ImmutableSortedSet<E> build() {
        RegularImmutableSortedSet regularImmutableSortedSet;
        Object[] objArr = this.A02;
        Comparator<? super E> comparator = this.A00;
        int i = super.A00;
        if (i == 0) {
            regularImmutableSortedSet = ImmutableSortedSet.A01(comparator);
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                if (objArr[i2] == null) {
                    throw new NullPointerException(AnonymousClass006.A03("at index ", i2));
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
            regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.A0E(objArr, i3), comparator);
        }
        super.A00 = regularImmutableSortedSet.size();
        this.A01 = true;
        return regularImmutableSortedSet;
    }

    public AnonymousClass0BY(Comparator<? super E> comparator) {
        if (comparator != null) {
            this.A00 = comparator;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass0NC, X.AnonymousClass0fR, X.AbstractC05160uM
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AbstractC05160uM A02(Iterable iterable) {
        super.A03(iterable);
        return this;
    }

    @Override // X.AnonymousClass0NC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0NC A03(Iterable iterable) {
        super.A03(iterable);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0NC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0NC A04(Object obj) {
        super.A04(obj);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0NC
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AnonymousClass0NC A05(Object[] objArr) {
        super.A05(objArr);
        return this;
    }

    /* JADX WARN: Incorrect return type in method signature: ([TE;)LX/0BY<TE;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @CanIgnoreReturnValue
    public final void A08(Object... objArr) {
        super.A05(objArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass0NC, X.AnonymousClass0fR, X.AbstractC05160uM
    @CanIgnoreReturnValue
    public final /* bridge */ /* synthetic */ AbstractC05160uM add(Object[] objArr) {
        super.A05(objArr);
        return this;
    }
}
