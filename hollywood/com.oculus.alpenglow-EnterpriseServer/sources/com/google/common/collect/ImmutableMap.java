package com.google.common.collect;

import X.AnonymousClass0td;
import X.AnonymousClass0u6;
import X.C07340r5;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    public static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    @LazyInit
    public transient ImmutableSet<Map.Entry<K, V>> entrySet;
    @LazyInit
    public transient ImmutableSet<K> keySet;
    @LazyInit
    public transient ImmutableCollection<V> values;

    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] keys;
        public final Object[] values;

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
        /* JADX WARN: Multi-variable type inference failed */
        public Object readResolve() {
            Builder builder = new Builder(this.keys.length);
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

        public SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            ImmutableSet immutableSet = immutableMap.entrySet;
            if (immutableSet == null) {
                immutableSet = immutableMap.createEntrySet();
                immutableMap.entrySet = immutableSet;
            }
            AnonymousClass0u6 it = immutableSet.iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                this.keys[i] = entry.getKey();
                this.values[i] = entry.getValue();
                i++;
            }
        }
    }

    public abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    public abstract ImmutableSet<K> createKeySet();

    public abstract ImmutableCollection<V> createValues();

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.A03;
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsValue(@NullableDecl Object obj) {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection == null) {
            immutableCollection = createValues();
            this.values = immutableCollection;
        }
        return immutableCollection.contains(obj);
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public int hashCode() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet == null) {
            immutableSet = createEntrySet();
            this.entrySet = immutableSet;
        }
        return AnonymousClass0td.A00(immutableSet);
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Set keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> createKeySet = createKeySet();
        this.keySet = createKeySet;
        return createKeySet;
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Collection values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> createValues = createValues();
        this.values = createValues;
        return createValues;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public boolean containsKey(@NullableDecl Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        if (v2 == null) {
            return v;
        }
        return v2;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        int size = size();
        C07340r5.A00(size, LoggerConstants.SIZE_KEY);
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

    public static class Builder<K, V> {
        public Object[] alternatingKeysAndValues;
        public boolean entriesUsed;
        public int size;

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x005e, code lost:
            r9[r1] = r2;
            r7 = r7 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.collect.ImmutableMap<K, V> build() {
            /*
            // Method dump skipped, instructions count: 170
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableMap.Builder.build():com.google.common.collect.ImmutableMap");
        }

        public Builder() {
            this(4);
        }

        public Builder(int i) {
            this.alternatingKeysAndValues = new Object[(i << 1)];
            this.size = 0;
            this.entriesUsed = false;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k, V v) {
            int i = (this.size + 1) << 1;
            Object[] objArr = this.alternatingKeysAndValues;
            int length = objArr.length;
            if (i > length) {
                if (i >= 0) {
                    int i2 = length + (length >> 1) + 1;
                    if (i2 < i) {
                        i2 = Integer.highestOneBit(i - 1) << 1;
                    }
                    if (i2 < 0) {
                        i2 = Integer.MAX_VALUE;
                    }
                    this.alternatingKeysAndValues = Arrays.copyOf(objArr, i2);
                    this.entriesUsed = false;
                } else {
                    throw new AssertionError("cannot store more than MAX_VALUE elements");
                }
            }
            C07340r5.A01(k, v);
            Object[] objArr2 = this.alternatingKeysAndValues;
            int i3 = this.size;
            int i4 = i3 << 1;
            objArr2[i4] = k;
            objArr2[i4 + 1] = v;
            this.size = i3 + 1;
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.ImmutableMap$Builder<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            put(entry.getKey(), entry.getValue());
            return this;
        }
    }
}
