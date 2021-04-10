package com.google.common.collect;

import X.AbstractC07380s1;
import X.C06710pf;
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
        public final transient Object[] A01;
        public final transient ImmutableMap<K, V> A02;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0H() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final ImmutableList<Map.Entry<K, V>> A0L() {
            return new ImmutableList<Map.Entry<K, V>>() {
                /* class com.google.common.collect.RegularImmutableMap.EntrySet.AnonymousClass1 */

                @Override // com.google.common.collect.ImmutableCollection
                public final boolean A0H() {
                    return true;
                }

                @Override // java.util.List
                public final Object get(int i) {
                    Preconditions.checkElementIndex(i, EntrySet.this.A00);
                    Object[] objArr = EntrySet.this.A01;
                    int i2 = i << 1;
                    return new AbstractMap.SimpleImmutableEntry(objArr[0 + i2], objArr[i2 + 1]);
                }

                public final int size() {
                    return EntrySet.this.A00;
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
            if (value == null || !value.equals(this.A02.get(key))) {
                return false;
            }
            return true;
        }

        /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;TV;>;[Ljava/lang/Object;II)V */
        public EntrySet(ImmutableMap immutableMap, Object[] objArr, int i) {
            this.A02 = immutableMap;
            this.A01 = objArr;
            this.A00 = i;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int A0I(Object[] objArr, int i) {
            return A0J().A0I(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        /* renamed from: A0K */
        public final AbstractC07380s1<Map.Entry<K, V>> iterator() {
            return A0J().iterator();
        }

        public final int size() {
            return this.A00;
        }
    }

    public static final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList<K> A00;
        public final transient ImmutableMap<K, ?> A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0H() {
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
        public final int A0I(Object[] objArr, int i) {
            return A0J().A0I(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public final ImmutableList<K> A0J() {
            return this.A00;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        /* renamed from: A0K */
        public final AbstractC07380s1<K> iterator() {
            return A0J().iterator();
        }
    }

    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0H() {
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

    @Override // com.google.common.collect.ImmutableMap
    public final boolean A08() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        r9[r1] = r2;
        r6 = r6 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <K, V> com.google.common.collect.RegularImmutableMap<K, V> A00(int r12, java.lang.Object[] r13) {
        /*
        // Method dump skipped, instructions count: 165
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.A00(int, java.lang.Object[]):com.google.common.collect.RegularImmutableMap");
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection<V> A04() {
        return new KeysOrValuesAsList(this.A00, 1, this.A01);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<Map.Entry<K, V>> A06() {
        return new EntrySet(this, this.A00, this.A01);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet<K> A07() {
        return new KeySet(this, new KeysOrValuesAsList(this.A00, 0, this.A01));
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        int[] iArr = this.A02;
        Object[] objArr = this.A00;
        int i = this.A01;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        } else if (iArr == null) {
            return null;
        } else {
            int length = iArr.length - 1;
            int A002 = C06710pf.A00(obj.hashCode());
            while (true) {
                int i2 = A002 & length;
                int i3 = iArr[i2];
                if (i3 == -1) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return (V) objArr[i3 ^ 1];
                }
                A002 = i2 + 1;
            }
        }
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
