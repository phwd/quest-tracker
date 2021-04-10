package com.google.common.collect;

import X.AbstractC05710wh;
import X.AnonymousClass0fJ;
import X.AnonymousClass0vx;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

@GwtCompatible(emulated = true, serializable = true)
public final class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    public static final ImmutableSortedMap<Comparable, Object> A03;
    public static final Comparator<Comparable> A04;
    public static final long serialVersionUID = 0;
    public transient ImmutableSortedMap<K, V> A00;
    public final transient ImmutableList<V> A01;
    public final transient RegularImmutableSortedSet<K> A02;

    public static class SerializedForm extends ImmutableMap.SerializedForm {
        public static final long serialVersionUID = 0;
        public final Comparator<Object> comparator;

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return A00(new AnonymousClass0fJ(this.comparator));
        }

        public SerializedForm(ImmutableSortedMap<?, ?> immutableSortedMap) {
            super(immutableSortedMap);
            this.comparator = immutableSortedMap.comparator();
        }
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> ceilingEntry(K k) {
        return tailMap(k, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> floorEntry(K k) {
        return headMap(k, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> higherEntry(K k) {
        return tailMap(k, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> lowerEntry(K k) {
        return headMap(k, false).lastEntry();
    }

    static {
        NaturalOrdering naturalOrdering = NaturalOrdering.A00;
        A04 = naturalOrdering;
        A03 = new ImmutableSortedMap<>(ImmutableSortedSet.A01(naturalOrdering), ImmutableList.of(), null);
    }

    private ImmutableSortedMap<K, V> A00(int i, int i2) {
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return A03(comparator());
        }
        return new ImmutableSortedMap<>(this.A02.A0S(i, i2), this.A01.subList(i, i2), null);
    }

    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final ImmutableSortedMap<K, V> headMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.A02;
        if (k != null) {
            return A00(0, regularImmutableSortedSet.A0Q(k, z));
        }
        throw null;
    }

    /* access modifiers changed from: private */
    /* renamed from: A02 */
    public final ImmutableSortedMap<K, V> tailMap(K k, boolean z) {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.A02;
        if (k != null) {
            return A00(regularImmutableSortedSet.A0R(k, z), size());
        }
        throw null;
    }

    public static <K, V> ImmutableSortedMap<K, V> A03(Comparator<? super K> comparator) {
        return NaturalOrdering.A00.equals(comparator) ? (ImmutableSortedMap<K, V>) A03 : new ImmutableSortedMap<>(ImmutableSortedSet.A01(comparator), ImmutableList.of(), null);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection<V> A08() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<K> A0A() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap
    public final boolean A0C() {
        if (this.A02.A0F() || this.A01.A0F()) {
            return true;
        }
        return false;
    }

    @Override // java.util.SortedMap
    public final Comparator<? super K> comparator() {
        return this.A02.comparator();
    }

    @Override // java.util.NavigableMap
    public final NavigableSet descendingKeySet() {
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.A02;
        ImmutableSortedSet<E> immutableSortedSet = ((ImmutableSortedSet) regularImmutableSortedSet).A00;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet immutableSortedSet2 = (ImmutableSortedSet<K>) regularImmutableSortedSet.A0L();
        ((ImmutableSortedSet) regularImmutableSortedSet).A00 = immutableSortedSet2;
        immutableSortedSet2.A00 = regularImmutableSortedSet;
        return immutableSortedSet2;
    }

    @Override // java.util.NavigableMap
    public final NavigableMap descendingMap() {
        AnonymousClass0vx comparatorOrdering;
        ImmutableSortedMap<K, V> immutableSortedMap = this.A00;
        if (immutableSortedMap != null) {
            return immutableSortedMap;
        }
        if (isEmpty()) {
            Comparator<? super K> comparator = comparator();
            if (comparator instanceof AnonymousClass0vx) {
                comparatorOrdering = (AnonymousClass0vx) comparator;
            } else {
                comparatorOrdering = new ComparatorOrdering(comparator);
            }
            return A03(comparatorOrdering.A00());
        }
        RegularImmutableSortedSet<K> regularImmutableSortedSet = this.A02;
        ImmutableSortedSet immutableSortedSet = (ImmutableSortedSet<E>) ((ImmutableSortedSet) regularImmutableSortedSet).A00;
        ImmutableSortedSet immutableSortedSet2 = immutableSortedSet;
        if (immutableSortedSet == null) {
            ImmutableSortedSet immutableSortedSet3 = (ImmutableSortedSet<K>) regularImmutableSortedSet.A0L();
            ((ImmutableSortedSet) regularImmutableSortedSet).A00 = immutableSortedSet3;
            immutableSortedSet3.A00 = regularImmutableSortedSet;
            immutableSortedSet2 = immutableSortedSet3;
        }
        return new ImmutableSortedMap((RegularImmutableSortedSet) immutableSortedSet2, this.A01.A0J(), this);
    }

    @Override // java.util.SortedMap
    public final K firstKey() {
        return this.A02.first();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0015 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0017  */
    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r3) {
        /*
            r2 = this;
            com.google.common.collect.RegularImmutableSortedSet<K> r0 = r2.A02
            if (r3 == 0) goto L_0x000f
            com.google.common.collect.ImmutableList<E> r1 = r0.A00     // Catch:{ ClassCastException -> 0x0011 }
            java.util.Comparator<? super E> r0 = r0.A01     // Catch:{ ClassCastException -> 0x0011 }
            int r0 = java.util.Collections.binarySearch(r1, r3, r0)     // Catch:{ ClassCastException -> 0x0011 }
            if (r0 < 0) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r0 = -1
            goto L_0x0012
        L_0x0011:
            r0 = -1
        L_0x0012:
            r1 = -1
            if (r0 != r1) goto L_0x0017
            r0 = 0
            return r0
        L_0x0017:
            com.google.common.collect.ImmutableList<V> r1 = r2.A01
            java.lang.Object r0 = r1.get(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableSortedMap.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.SortedMap
    public final K lastKey() {
        return this.A02.last();
    }

    @Override // java.util.NavigableMap
    @CanIgnoreReturnValue
    @Deprecated
    public final Map.Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    @CanIgnoreReturnValue
    @Deprecated
    public final Map.Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.A01.size();
    }

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList, ImmutableSortedMap<K, V> immutableSortedMap) {
        this.A02 = regularImmutableSortedSet;
        this.A01 = immutableList;
        this.A00 = immutableSortedMap;
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection<V> A07() {
        return this.A01;
    }

    @Override // com.google.common.collect.ImmutableMap
    public final /* bridge */ /* synthetic */ ImmutableSet A09() {
        return this.A02;
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<Map.Entry<K, V>> A0B() {
        if (isEmpty()) {
            return RegularImmutableSet.A05;
        }
        return new ImmutableMapEntrySet<K, V>() {
            /* class com.google.common.collect.ImmutableSortedMap.AnonymousClass1EntrySet */

            @Override // com.google.common.collect.ImmutableSet
            public final ImmutableList<Map.Entry<K, V>> A0J() {
                return new ImmutableList<Map.Entry<K, V>>() {
                    /* class com.google.common.collect.ImmutableSortedMap.AnonymousClass1EntrySet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0F() {
                        return true;
                    }

                    @Override // java.util.List
                    public final Object get(int i) {
                        return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.A02.A0H().get(i), ImmutableSortedMap.this.A01.get(i));
                    }

                    public final int size() {
                        return ImmutableSortedMap.this.size();
                    }
                };
            }

            @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
            /* renamed from: A0I */
            public final AbstractC05710wh<Map.Entry<K, V>> iterator() {
                return A0H().iterator();
            }

            @Override // com.google.common.collect.ImmutableMapEntrySet
            public final ImmutableMap<K, V> A0L() {
                return ImmutableSortedMap.this;
            }
        };
    }

    @Override // java.util.NavigableMap
    public final K ceilingKey(K k) {
        Map.Entry<K, V> ceilingEntry = ceilingEntry(k);
        if (ceilingEntry == null) {
            return null;
        }
        return ceilingEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        ImmutableSet<Map.Entry<K, V>> immutableSet = super.A00;
        if (immutableSet == null) {
            immutableSet = A0B();
            super.A00 = immutableSet;
        }
        return immutableSet.A0H().get(0);
    }

    @Override // java.util.NavigableMap
    public final K floorKey(K k) {
        Map.Entry<K, V> floorEntry = floorEntry(k);
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final K higherKey(K k) {
        Map.Entry<K, V> higherEntry = higherEntry(k);
        if (higherEntry == null) {
            return null;
        }
        return higherEntry.getKey();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map, java.util.SortedMap
    public final /* bridge */ /* synthetic */ Set keySet() {
        return this.A02;
    }

    @Override // java.util.NavigableMap
    public final Map.Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        ImmutableSet<Map.Entry<K, V>> immutableSet = super.A00;
        if (immutableSet == null) {
            immutableSet = A0B();
            super.A00 = immutableSet;
        }
        return immutableSet.A0H().get(size() - 1);
    }

    @Override // java.util.NavigableMap
    public final K lowerKey(K k) {
        Map.Entry<K, V> lowerEntry = lowerEntry(k);
        if (lowerEntry == null) {
            return null;
        }
        return lowerEntry.getKey();
    }

    @Override // java.util.NavigableMap
    public final NavigableSet navigableKeySet() {
        return this.A02;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public final /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 != 0) {
            boolean z3 = false;
            if (comparator().compare(obj, obj2) <= 0) {
                z3 = true;
            }
            Preconditions.checkArgument(z3, "expected fromKey <= toKey but %s > %s", obj, obj2);
            return headMap(obj2, z2).tailMap(obj, z);
        } else {
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap subMap(Object obj, Object obj2) {
        if (obj == 0) {
            throw null;
        } else if (obj2 != 0) {
            boolean z = false;
            if (comparator().compare(obj, obj2) <= 0) {
                z = true;
            }
            Preconditions.checkArgument(z, "expected fromKey <= toKey but %s > %s", obj, obj2);
            return headMap(obj2, false).tailMap(obj, true);
        } else {
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, java.util.SortedMap
    public final SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}
