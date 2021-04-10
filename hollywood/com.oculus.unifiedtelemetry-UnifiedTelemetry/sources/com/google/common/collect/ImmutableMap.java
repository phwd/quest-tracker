package com.google.common.collect;

import X.AnonymousClass6y;
import X.AnonymousClass8f;
import X.AnonymousClass9M;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    @LazyInit
    public transient ImmutableCollection<V> A00;
    @LazyInit
    public transient ImmutableSet<Map.Entry<K, V>> A01;
    @LazyInit
    public transient ImmutableSet<K> A02;

    public static class Builder<K, V> {
        public int A00;
        public Object[] A01;

        public ImmutableMap<K, V> build() {
            return RegularImmutableMap.A00(this.A00, this.A01);
        }

        public Builder() {
            this(4);
        }

        public Builder(int i) {
            this.A01 = new Object[(i << 1)];
            this.A00 = 0;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k, V v) {
            int i = (this.A00 + 1) << 1;
            Object[] objArr = this.A01;
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
                    this.A01 = Arrays.copyOf(objArr, i2);
                } else {
                    throw new AssertionError("cannot store more than MAX_VALUE elements");
                }
            }
            AnonymousClass9M.A01(k, v);
            Object[] objArr2 = this.A01;
            int i3 = this.A00;
            int i4 = i3 << 1;
            objArr2[i4] = k;
            objArr2[i4 + 1] = v;
            this.A00 = i3 + 1;
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
            AnonymousClass8f<Map.Entry<?, ?>> A0D = immutableMap.entrySet().iterator();
            int i = 0;
            while (A0D.hasNext()) {
                Map.Entry<?, ?> next = A0D.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }
    }

    public abstract ImmutableCollection<V> A02();

    public abstract ImmutableSet<Map.Entry<K, V>> A03();

    public abstract ImmutableSet<K> A04();

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    /* renamed from: A01 */
    public final ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.A01;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> A03 = A03();
        this.A01 = A03;
        return A03;
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        ImmutableCollection<V> immutableCollection = this.A00;
        if (immutableCollection == null) {
            immutableCollection = A02();
            this.A00 = immutableCollection;
        }
        return immutableCollection.contains(obj);
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public final Set keySet() {
        ImmutableSet<K> immutableSet = this.A02;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> A04 = A04();
        this.A02 = A04;
        return A04;
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
    public final /* bridge */ /* synthetic */ Collection values() {
        ImmutableCollection<V> immutableCollection = this.A00;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> A022 = A02();
        this.A00 = A022;
        return A022;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public final boolean containsKey(@NullableDecl Object obj) {
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

    public final int hashCode() {
        return AnonymousClass6y.A00(entrySet());
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int size = size();
        AnonymousClass9M.A00(size, "size");
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

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.A03;
    }
}
