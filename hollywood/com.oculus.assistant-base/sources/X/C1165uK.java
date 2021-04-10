package X;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.RegularImmutableSortedSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/* renamed from: X.uK  reason: case insensitive filesystem */
public final class C1165uK extends ImmutableMap.Builder {
    public final Comparator A00;
    public transient Object[] A01;
    public transient Object[] A02;

    public C1165uK(Comparator comparator) {
        super(4);
        if (comparator != null) {
            this.A00 = comparator;
            this.A01 = new Object[4];
            this.A02 = new Object[4];
            return;
        }
        throw null;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap build() {
        RegularImmutableSortedSet regularImmutableSortedSet;
        ImmutableList immutableList;
        int i = super.A00;
        if (i == 0) {
            return ImmutableSortedMap.A04(this.A00);
        }
        if (i != 1) {
            Object[] copyOf = Arrays.copyOf(this.A01, i);
            Comparator comparator = this.A00;
            Arrays.sort(copyOf, comparator);
            Object[] objArr = new Object[super.A00];
            for (int i2 = 0; i2 < super.A00; i2++) {
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    if (comparator.compare(copyOf[i3], copyOf[i2]) == 0) {
                        StringBuilder sb = new StringBuilder("keys required to be distinct but compared as equal: ");
                        sb.append(copyOf[i3]);
                        sb.append(" and ");
                        sb.append(copyOf[i2]);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                objArr[Arrays.binarySearch(copyOf, this.A01[i2], comparator)] = this.A02[i2];
            }
            regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.A07(copyOf), comparator);
            immutableList = ImmutableList.A07(objArr);
        } else {
            Comparator comparator2 = this.A00;
            Object obj = this.A01[0];
            Object obj2 = this.A02[0];
            ImmutableList A05 = ImmutableList.A05(obj);
            if (comparator2 != null) {
                regularImmutableSortedSet = new RegularImmutableSortedSet(A05, comparator2);
                immutableList = ImmutableList.A05(obj2);
            } else {
                throw null;
            }
        }
        return new ImmutableSortedMap(regularImmutableSortedSet, immutableList, null);
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder A00(Iterable iterable) {
        super.A00(iterable);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder put(Object obj, Object obj2) {
        int i = super.A00 + 1;
        int length = this.A01.length;
        if (i > length) {
            int A002 = Tx.A00(length, i);
            this.A01 = Arrays.copyOf(this.A01, A002);
            this.A02 = Arrays.copyOf(this.A02, A002);
        }
        C0361Tm.A01(obj, obj2);
        Object[] objArr = this.A01;
        int i2 = super.A00;
        objArr[i2] = obj;
        this.A02[i2] = obj2;
        super.A00 = i2 + 1;
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder put(Map.Entry entry) {
        super.put(entry);
        return this;
    }
}
