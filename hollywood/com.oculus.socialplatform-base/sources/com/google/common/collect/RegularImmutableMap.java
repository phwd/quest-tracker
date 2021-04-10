package com.google.common.collect;

import X.AbstractC05710wh;
import X.AnonymousClass0th;
import X.C05150uI;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.AbstractMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    public static final ImmutableMap<Object, Object> A03 = new RegularImmutableMap(null, new Object[0], 0);
    public static final long serialVersionUID = 0;
    @VisibleForTesting
    public final transient Object[] A00;
    public final transient int A01;
    public final transient int[] A02;

    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;
        public final transient ImmutableMap<K, V> A03;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final ImmutableList<Map.Entry<K, V>> A0J() {
            return new ImmutableList<Map.Entry<K, V>>() {
                /* class com.google.common.collect.RegularImmutableMap.EntrySet.AnonymousClass1 */

                @Override // com.google.common.collect.ImmutableCollection
                public final boolean A0F() {
                    return true;
                }

                @Override // java.util.List
                public final Object get(int i) {
                    Preconditions.checkElementIndex(i, EntrySet.this.A01);
                    EntrySet entrySet = EntrySet.this;
                    Object[] objArr = entrySet.A02;
                    int i2 = i << 1;
                    int i3 = entrySet.A00;
                    return new AbstractMap.SimpleImmutableEntry(objArr[i3 + i2], objArr[i2 + (i3 ^ 1)]);
                }

                public final int size() {
                    return EntrySet.this.A01;
                }
            };
        }

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

        public EntrySet(ImmutableMap<K, V> immutableMap, Object[] objArr, int i, int i2) {
            this.A03 = immutableMap;
            this.A02 = objArr;
            this.A00 = i;
            this.A01 = i2;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int A0G(Object[] objArr, int i) {
            return A0H().A0G(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        /* renamed from: A0I */
        public final AbstractC05710wh<Map.Entry<K, V>> iterator() {
            return A0H().iterator();
        }

        public final int size() {
            return this.A01;
        }
    }

    public static final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList<K> A00;
        public final transient ImmutableMap<K, ?> A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(@NullableDecl Object obj) {
            if (this.A01.get(obj) != null) {
                return true;
            }
            return false;
        }

        public final int size() {
            return this.A01.size();
        }

        public KeySet(ImmutableMap<K, ?> immutableMap, ImmutableList<K> immutableList) {
            this.A01 = immutableMap;
            this.A00 = immutableList;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int A0G(Object[] objArr, int i) {
            return A0H().A0G(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public final ImmutableList<K> A0H() {
            return this.A00;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        /* renamed from: A0I */
        public final AbstractC05710wh<K> iterator() {
            return A0H().iterator();
        }
    }

    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
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

    public static Object A01(@NullableDecl int[] iArr, @NullableDecl Object[] objArr, int i, int i2, @NullableDecl Object obj) {
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
            int A002 = C05150uI.A00(obj.hashCode());
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
    public static int[] A02(java.lang.Object[] r10, int r11, int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.A02(java.lang.Object[], int, int, int):int[]");
    }

    @Override // com.google.common.collect.ImmutableMap
    public final boolean A0C() {
        return false;
    }

    public static <K, V> RegularImmutableMap<K, V> A00(int i, Object[] objArr) {
        if (i == 0) {
            return (RegularImmutableMap) A03;
        }
        if (i == 1) {
            AnonymousClass0th.A01(objArr[0], objArr[1]);
            return new RegularImmutableMap<>(null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i, objArr.length >> 1);
        return new RegularImmutableMap<>(A02(objArr, i, ImmutableSet.A02(i), 0), objArr, i);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection<V> A08() {
        return new KeysOrValuesAsList(this.A00, 1, this.A01);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<K> A0A() {
        return new KeySet(this, new KeysOrValuesAsList(this.A00, 0, this.A01));
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<Map.Entry<K, V>> A0B() {
        return new EntrySet(this, this.A00, 0, this.A01);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        return (V) A01(this.A02, this.A00, this.A01, 0, obj);
    }

    public RegularImmutableMap(int[] iArr, Object[] objArr, int i) {
        this.A02 = iArr;
        this.A00 = objArr;
        this.A01 = i;
    }

    public final int size() {
        return this.A01;
    }
}
