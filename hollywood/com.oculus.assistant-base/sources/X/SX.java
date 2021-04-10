package X;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.RegularImmutableSortedSet;
import java.util.Arrays;
import java.util.Comparator;

public final class SX extends DS {
    public final Comparator A00;

    /* renamed from: A03 */
    public final ImmutableSortedSet build() {
        RegularImmutableSortedSet regularImmutableSortedSet;
        Object[] objArr = this.A02;
        Comparator comparator = this.A00;
        int i = super.A00;
        if (i == 0) {
            regularImmutableSortedSet = ImmutableSortedSet.A01(comparator);
        } else {
            for (int i2 = 0; i2 < i; i2++) {
                if (objArr[i2] == null) {
                    throw new NullPointerException(AnonymousClass08.A00("at index ", i2));
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
            regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.A0A(objArr, i3), comparator);
        }
        super.A00 = regularImmutableSortedSet.size();
        this.A01 = true;
        return regularImmutableSortedSet;
    }

    public SX(Comparator comparator) {
        if (comparator != null) {
            this.A00 = comparator;
            return;
        }
        throw null;
    }

    @Override // X.DS
    public final /* bridge */ /* synthetic */ DS A00(Object obj) {
        super.A00(obj);
        return this;
    }

    @Override // X.DS
    public final /* bridge */ /* synthetic */ DS A01(Object[] objArr) {
        super.A01(objArr);
        return this;
    }

    public final void A04(Object... objArr) {
        super.A01(objArr);
    }

    @Override // X.Tx, X.AbstractC1160uA, X.DS
    public final /* bridge */ /* synthetic */ Tx add(Object[] objArr) {
        super.A01(objArr);
        return this;
    }
}
