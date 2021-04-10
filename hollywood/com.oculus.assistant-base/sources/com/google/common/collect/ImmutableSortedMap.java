package com.google.common.collect;

import X.C1165uK;
import X.UR;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;

public final class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    public static final ImmutableSortedMap A03;
    public static final Comparator A04;
    public static final long serialVersionUID = 0;
    public transient ImmutableSortedMap A00;
    public final transient ImmutableList A01;
    public final transient RegularImmutableSortedSet A02;

    public class SerializedForm extends ImmutableMap.SerializedForm {
        public static final long serialVersionUID = 0;
        public final Comparator comparator;

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return A00(new C1165uK(this.comparator));
        }

        public SerializedForm(ImmutableSortedMap immutableSortedMap) {
            super(immutableSortedMap);
            this.comparator = immutableSortedMap.comparator();
        }
    }

    @Override // java.util.NavigableMap
    public final Map.Entry ceilingEntry(Object obj) {
        return tailMap(obj, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry floorEntry(Object obj) {
        return headMap(obj, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry higherEntry(Object obj) {
        return tailMap(obj, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lowerEntry(Object obj) {
        return headMap(obj, false).lastEntry();
    }

    static {
        NaturalOrdering naturalOrdering = NaturalOrdering.A00;
        A04 = naturalOrdering;
        A03 = new ImmutableSortedMap(ImmutableSortedSet.A01(naturalOrdering), ImmutableList.of(), null);
    }

    private ImmutableSortedMap A00(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return A04(comparator());
        }
        return new ImmutableSortedMap(this.A02.A0J(i, i2), this.A01.subList(i, i2), null);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final ImmutableSortedMap headMap(Object obj, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = this.A02;
        if (obj != null) {
            return A00(0, regularImmutableSortedSet.A0H(obj, z));
        }
        throw null;
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final ImmutableSortedMap tailMap(Object obj, boolean z) {
        RegularImmutableSortedSet regularImmutableSortedSet = this.A02;
        if (obj != null) {
            return A00(regularImmutableSortedSet.A0I(obj, z), size());
        }
        throw null;
    }

    /* access modifiers changed from: private */
    /* renamed from: A03 */
    public final ImmutableSortedMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == null) {
            throw null;
        } else if (obj2 != null) {
            boolean z3 = false;
            if (comparator().compare(obj, obj2) <= 0) {
                z3 = true;
            }
            if (z3) {
                return headMap(obj2, z2).tailMap(obj, z);
            }
            throw new IllegalArgumentException(Strings.lenientFormat("expected fromKey <= toKey but %s > %s", obj, obj2));
        } else {
            throw null;
        }
    }

    public static ImmutableSortedMap A04(Comparator comparator) {
        if (NaturalOrdering.A00.equals(comparator)) {
            return A03;
        }
        return new ImmutableSortedMap(ImmutableSortedSet.A01(comparator), ImmutableList.of(), null);
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return this.A02.comparator();
    }

    @Override // java.util.NavigableMap
    public final NavigableSet descendingKeySet() {
        RegularImmutableSortedSet regularImmutableSortedSet = this.A02;
        ImmutableSortedSet immutableSortedSet = ((ImmutableSortedSet) regularImmutableSortedSet).A00;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet A0F = regularImmutableSortedSet.A0F();
        ((ImmutableSortedSet) regularImmutableSortedSet).A00 = A0F;
        A0F.A00 = regularImmutableSortedSet;
        return A0F;
    }

    @Override // java.util.NavigableMap
    public final NavigableMap descendingMap() {
        UR comparatorOrdering;
        ImmutableSortedMap immutableSortedMap = this.A00;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        if (isEmpty()) {
            Comparator comparator = comparator();
            if (comparator instanceof UR) {
                comparatorOrdering = (UR) comparator;
            } else {
                comparatorOrdering = new ComparatorOrdering(comparator);
            }
            return A04(comparatorOrdering.A00());
        }
        RegularImmutableSortedSet regularImmutableSortedSet = this.A02;
        ImmutableSortedSet immutableSortedSet = ((ImmutableSortedSet) regularImmutableSortedSet).A00;
        if (immutableSortedSet == null) {
            immutableSortedSet = regularImmutableSortedSet.A0F();
            ((ImmutableSortedSet) regularImmutableSortedSet).A00 = immutableSortedSet;
            immutableSortedSet.A00 = regularImmutableSortedSet;
        }
        return new ImmutableSortedMap((RegularImmutableSortedSet) immutableSortedSet, this.A01.A0F(), this);
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return this.A02.first();
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return this.A02.last();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.A01.size();
    }

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public ImmutableSortedMap(RegularImmutableSortedSet regularImmutableSortedSet, ImmutableList immutableList, ImmutableSortedMap immutableSortedMap) {
        this.A02 = regularImmutableSortedSet;
        this.A01 = immutableList;
        this.A00 = immutableSortedMap;
    }

    @Override // java.util.NavigableMap
    public final Object ceilingKey(Object obj) {
        Map.Entry ceilingEntry = ceilingEntry(obj);
        if (ceilingEntry == null) {
            return null;
        }
        return ceilingEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().A0D().get(0);
    }

    @Override // java.util.NavigableMap
    public final Object floorKey(Object obj) {
        Map.Entry floorEntry = floorEntry(obj);
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final Object higherKey(Object obj) {
        Map.Entry higherEntry = higherEntry(obj);
        if (higherEntry == null) {
            return null;
        }
        return higherEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Map.Entry) entrySet().A0D().get(size() - 1);
    }

    @Override // java.util.NavigableMap
    public final Object lowerKey(Object obj) {
        Map.Entry lowerEntry = lowerEntry(obj);
        if (lowerEntry == null) {
            return null;
        }
        return lowerEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final NavigableSet navigableKeySet() {
        return this.A02;
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}
