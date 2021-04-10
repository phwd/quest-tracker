package com.google.common.collect;

import X.Tw;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Map;

public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    public static final ImmutableMap A03 = new RegularImmutableMap(null, new Object[0], 0);
    public static final long serialVersionUID = 0;
    public final transient int A00;
    public final transient int[] A01;
    public final transient Object[] A02;

    public class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;
        public final transient ImmutableMap A03;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.A03.get(key))) {
                return false;
            }
            return true;
        }

        public EntrySet(ImmutableMap immutableMap, Object[] objArr, int i, int i2) {
            this.A03 = immutableMap;
            this.A02 = objArr;
            this.A00 = i;
            this.A01 = i2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        public final int size() {
            return this.A01;
        }
    }

    public final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList A00;
        public final transient ImmutableMap A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (this.A01.get(obj) != null) {
                return true;
            }
            return false;
        }

        public final int size() {
            return this.A01.size();
        }

        public KeySet(ImmutableMap immutableMap, ImmutableList immutableList) {
            this.A01 = immutableMap;
            this.A00 = immutableList;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public final ImmutableList A0D() {
            return this.A00;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }
    }

    public final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0B() {
            return true;
        }

        @Override // java.util.List
        public final Object get(int i) {
            Preconditions.checkElementIndex(i, this.A01);
            return this.A02[(i << 1) + this.A00];
        }

        public KeysOrValuesAsList(Object[] objArr, int i, int i2) {
            this.A02 = objArr;
            this.A00 = i;
            this.A01 = i2;
        }

        public final int size() {
            return this.A01;
        }
    }

    public static Object A00(int[] iArr, Object[] objArr, int i, int i2, Object obj) {
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[i2].equals(obj)) {
                return objArr[i2 ^ 1];
            }
            return null;
        } else if (iArr == null) {
            return null;
        } else {
            int length = iArr.length - 1;
            int A002 = Tw.A00(obj.hashCode());
            while (true) {
                int i3 = A002 & length;
                int i4 = iArr[i3];
                if (i4 == -1) {
                    return null;
                }
                if (objArr[i4].equals(obj)) {
                    return objArr[i4 ^ 1];
                }
                A002 = i3 + 1;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        r8[r1] = r2;
        r6 = r6 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] A01(java.lang.Object[] r10, int r11, int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.A01(java.lang.Object[], int, int, int):int[]");
    }

    public RegularImmutableMap(int[] iArr, Object[] objArr, int i) {
        this.A01 = iArr;
        this.A02 = objArr;
        this.A00 = i;
    }

    public final int size() {
        return this.A00;
    }
}
