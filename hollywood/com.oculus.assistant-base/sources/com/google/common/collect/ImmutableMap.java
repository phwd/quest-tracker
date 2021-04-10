package com.google.common.collect;

import X.AbstractC0370Ug;
import X.C0361Tm;
import X.Tx;
import X.UX;
import com.google.common.base.Preconditions;
import com.google.common.collect.RegularImmutableMap;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public abstract class ImmutableMap<K, V> implements Serializable, Map<K, V> {
    public transient ImmutableCollection A00;
    public transient ImmutableSet A01;
    public transient ImmutableSet A02;

    public class Builder {
        public int A00;
        public Object[] A01;

        public Builder A00(Iterable iterable) {
            int size;
            Object[] objArr;
            int length;
            if ((iterable instanceof Collection) && (size = (this.A00 + ((Collection) iterable).size()) << 1) > (length = (objArr = this.A01).length)) {
                this.A01 = Arrays.copyOf(objArr, Tx.A00(length, size));
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                put((Map.Entry) it.next());
            }
            return this;
        }

        public ImmutableMap build() {
            int i = this.A00;
            Object[] objArr = this.A01;
            if (i == 0) {
                return RegularImmutableMap.A03;
            }
            if (i == 1) {
                C0361Tm.A01(objArr[0], objArr[1]);
                return new RegularImmutableMap(null, objArr, 1);
            }
            Preconditions.checkPositionIndex(i, objArr.length >> 1);
            return new RegularImmutableMap(RegularImmutableMap.A01(objArr, i, ImmutableSet.A02(i), 0), objArr, i);
        }

        public Builder() {
            this(4);
        }

        public Builder(int i) {
            this.A01 = new Object[(i << 1)];
            this.A00 = 0;
        }

        public Builder put(Object obj, Object obj2) {
            int i = (this.A00 + 1) << 1;
            Object[] objArr = this.A01;
            int length = objArr.length;
            if (i > length) {
                this.A01 = Arrays.copyOf(objArr, Tx.A00(length, i));
            }
            C0361Tm.A01(obj, obj2);
            Object[] objArr2 = this.A01;
            int i2 = this.A00;
            int i3 = i2 << 1;
            objArr2[i3] = obj;
            objArr2[i3 + 1] = obj2;
            this.A00 = i2 + 1;
            return this;
        }

        public Builder put(Map.Entry entry) {
            put(entry.getKey(), entry.getValue());
            return this;
        }
    }

    public class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] keys;
        public final Object[] values;

        public final Object A00(Builder builder) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return builder.build();
                }
                builder.put(objArr[i], this.values[i]);
                i++;
            }
        }

        public Object readResolve() {
            return A00(new Builder(this.keys.length));
        }

        public SerializedForm(ImmutableMap immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            AbstractC0370Ug A0E = immutableMap.entrySet().iterator();
            int i = 0;
            while (A0E.hasNext()) {
                Map.Entry entry = (Map.Entry) A0E.next();
                this.keys[i] = entry.getKey();
                this.values[i] = entry.getValue();
                i++;
            }
        }
    }

    public static ImmutableMap A05(Map map) {
        int i;
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap immutableMap = (ImmutableMap) map;
            if (!immutableMap.A09()) {
                return immutableMap;
            }
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        if (entrySet instanceof Collection) {
            i = entrySet.size();
        } else {
            i = 4;
        }
        Builder builder = new Builder(i);
        builder.A00(entrySet);
        return builder.build();
    }

    public final ImmutableCollection A06() {
        boolean z = this instanceof ImmutableSortedMap;
        if (z) {
            return ((ImmutableSortedMap) this).A01;
        }
        if (this instanceof ImmutableBiMap) {
            return ((RegularImmutableBiMap) ((ImmutableBiMap) this)).A02.A08();
        }
        ImmutableCollection immutableCollection = this.A00;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        if (this instanceof RegularImmutableMap) {
            RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
            RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 1, regularImmutableMap.A00);
            this.A00 = keysOrValuesAsList;
            return keysOrValuesAsList;
        } else if (!z) {
            throw new AssertionError("should never be called");
        } else {
            throw new AssertionError("should never be called");
        }
    }

    /* renamed from: A07 */
    public final ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.A01;
        if (immutableSet == null) {
            if (this instanceof RegularImmutableMap) {
                RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
                immutableSet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.A02, 0, regularImmutableMap.A00);
            } else if (!(this instanceof RegularImmutableBiMap)) {
                ImmutableSortedMap immutableSortedMap = (ImmutableSortedMap) this;
                if (immutableSortedMap.isEmpty()) {
                    immutableSet = RegularImmutableSet.A05;
                } else {
                    immutableSet = new ImmutableMapEntrySet<K, V>() {
                        /* class com.google.common.collect.ImmutableSortedMap.AnonymousClass1EntrySet */

                        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
                        public final /* bridge */ /* synthetic */ Iterator iterator() {
                            return iterator();
                        }
                    };
                }
            } else {
                RegularImmutableBiMap regularImmutableBiMap = (RegularImmutableBiMap) this;
                immutableSet = new RegularImmutableMap.EntrySet(regularImmutableBiMap, regularImmutableBiMap.A03, regularImmutableBiMap.A00, regularImmutableBiMap.A01);
            }
            this.A01 = immutableSet;
        }
        return immutableSet;
    }

    public final ImmutableSet A08() {
        if (this instanceof ImmutableSortedMap) {
            return ((ImmutableSortedMap) this).A02;
        }
        ImmutableSet immutableSet = this.A02;
        if (immutableSet == null) {
            if (this instanceof RegularImmutableMap) {
                RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
                immutableSet = new RegularImmutableMap.KeySet(regularImmutableMap, new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 0, regularImmutableMap.A00));
            } else if (!(this instanceof RegularImmutableBiMap)) {
                throw new AssertionError("should never be called");
            } else {
                RegularImmutableBiMap regularImmutableBiMap = (RegularImmutableBiMap) this;
                immutableSet = new RegularImmutableMap.KeySet(regularImmutableBiMap, new RegularImmutableMap.KeysOrValuesAsList(regularImmutableBiMap.A03, regularImmutableBiMap.A00, regularImmutableBiMap.A01));
            }
            this.A02 = immutableSet;
        }
        return immutableSet;
    }

    public final boolean A09() {
        if ((this instanceof RegularImmutableMap) || (this instanceof RegularImmutableBiMap)) {
            return false;
        }
        ImmutableSortedMap immutableSortedMap = (ImmutableSortedMap) this;
        if (immutableSortedMap.A02.A0B() || immutableSortedMap.A01.A0B()) {
            return true;
        }
        return false;
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x001e  */
    @Override // java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r4 instanceof com.google.common.collect.RegularImmutableMap
            if (r0 != 0) goto L_0x0025
            r2 = r4
            com.google.common.collect.ImmutableSortedMap r2 = (com.google.common.collect.ImmutableSortedMap) r2
            com.google.common.collect.RegularImmutableSortedSet r0 = r2.A02
            if (r5 == 0) goto L_0x0016
            com.google.common.collect.ImmutableList r1 = r0.A00     // Catch:{ ClassCastException -> 0x0018 }
            java.util.Comparator r0 = r0.A01     // Catch:{ ClassCastException -> 0x0018 }
            int r0 = java.util.Collections.binarySearch(r1, r5, r0)     // Catch:{ ClassCastException -> 0x0018 }
            if (r0 < 0) goto L_0x0016
            goto L_0x0019
        L_0x0016:
            r0 = -1
            goto L_0x0019
        L_0x0018:
            r0 = -1
        L_0x0019:
            r1 = -1
            if (r0 != r1) goto L_0x001e
            r0 = 0
            return r0
        L_0x001e:
            com.google.common.collect.ImmutableList r1 = r2.A01
            java.lang.Object r0 = r1.get(r0)
            return r0
        L_0x0025:
            r0 = r4
            com.google.common.collect.RegularImmutableMap r0 = (com.google.common.collect.RegularImmutableMap) r0
            int[] r3 = r0.A01
            java.lang.Object[] r2 = r0.A02
            int r1 = r0.A00
            r0 = 0
            java.lang.Object r0 = com.google.common.collect.RegularImmutableMap.A00(r3, r2, r1, r0, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableMap.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        if (!(this instanceof ImmutableSortedMap)) {
            return A08();
        }
        return ((ImmutableSortedMap) this).A02;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        if (!(this instanceof ImmutableBiMap)) {
            return A06();
        }
        return ((RegularImmutableBiMap) ((ImmutableBiMap) this)).A02.A08();
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static ImmutableMap of() {
        return RegularImmutableMap.A03;
    }

    public final boolean containsKey(Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        return A06().contains(obj);
    }

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 == null) {
            return obj2;
        }
        return obj3;
    }

    public final int hashCode() {
        return UX.A00(entrySet());
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int size = size();
        C0361Tm.A00(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append((Object) entry.getKey());
            sb.append('=');
            sb.append((Object) entry.getValue());
        }
        sb.append('}');
        return sb.toString();
    }
}
