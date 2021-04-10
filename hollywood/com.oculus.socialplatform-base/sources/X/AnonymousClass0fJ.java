package X;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/* renamed from: X.0fJ  reason: invalid class name */
public class AnonymousClass0fJ<K, V> extends ImmutableMap.Builder<K, V> {
    public final Comparator<? super K> A00;
    public transient Object[] A01;
    public transient Object[] A02;

    public AnonymousClass0fJ(Comparator<? super K> comparator) {
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
            return ImmutableSortedMap.A03(this.A00);
        }
        if (i != 1) {
            Object[] copyOf = Arrays.copyOf(this.A01, i);
            Comparator<? super K> comparator = this.A00;
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
            regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.A0B(copyOf), comparator);
            immutableList = ImmutableList.A0B(objArr);
        } else {
            Comparator<? super K> comparator2 = this.A00;
            Object obj = this.A01[0];
            Object obj2 = this.A02[0];
            ImmutableList A06 = ImmutableList.A06(obj);
            if (comparator2 != null) {
                regularImmutableSortedSet = new RegularImmutableSortedSet(A06, comparator2);
                immutableList = ImmutableList.A06(obj2);
            } else {
                throw null;
            }
        }
        return new ImmutableSortedMap(regularImmutableSortedSet, immutableList, null);
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    @Beta
    public final ImmutableMap.Builder A00(Iterable iterable) {
        super.A00(iterable);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder A01(Map map) {
        super.A01(map);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder put(Object obj, Object obj2) {
        int i = super.A00 + 1;
        int length = this.A01.length;
        if (i > length) {
            int A012 = AbstractC05160uM.A01(length, i);
            this.A01 = Arrays.copyOf(this.A01, A012);
            this.A02 = Arrays.copyOf(this.A02, A012);
        }
        AnonymousClass0th.A01(obj, obj2);
        Object[] objArr = this.A01;
        int i2 = super.A00;
        objArr[i2] = obj;
        this.A02[i2] = obj2;
        super.A00 = i2 + 1;
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder put(Map.Entry entry) {
        super.put(entry);
        return this;
    }
}
