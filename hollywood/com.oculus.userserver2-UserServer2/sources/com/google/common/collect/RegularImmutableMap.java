package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.oculus.common.build.BuildConfig;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    public static final ImmutableMap<Object, Object> A03 = new RegularImmutableMap(null, new Object[0], 0);
    public static final long serialVersionUID = 0;
    public final transient int A00;
    public final transient int[] A01;
    @VisibleForTesting
    public final transient Object[] A02;

    public static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient int A00;
        public final transient Object[] A01;
        public final transient ImmutableMap<K, V> A02;

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

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        public final int size() {
            return this.A00;
        }
    }

    public static final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList<K> A00;
        public final transient ImmutableMap<K, ?> A01;

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

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public final ImmutableList<K> A0G() {
            return this.A00;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }
    }

    public static final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient int A00;
        public final transient int A01;
        public final transient Object[] A02;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0E() {
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

    public RegularImmutableMap(int[] iArr, Object[] objArr, int i) {
        this.A01 = iArr;
        this.A02 = objArr;
        this.A00 = i;
    }

    public final int size() {
        return this.A00;
    }
}
