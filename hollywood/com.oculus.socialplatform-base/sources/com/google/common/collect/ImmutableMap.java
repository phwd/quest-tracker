package com.google.common.collect;

import X.AbstractC05160uM;
import X.AbstractC05710wh;
import X.AnonymousClass0th;
import X.AnonymousClass0wE;
import com.google.common.annotations.Beta;
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
    public transient ImmutableSet<Map.Entry<K, V>> A00;
    @LazyInit
    public transient ImmutableCollection<V> A01;
    @LazyInit
    public transient ImmutableSet<K> A02;

    public static class Builder<K, V> {
        public int A00;
        public Object[] A01;

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> A00(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            int size;
            Object[] objArr;
            int length;
            if ((iterable instanceof Collection) && (size = (this.A00 + ((Collection) iterable).size()) << 1) > (length = (objArr = this.A01).length)) {
                this.A01 = Arrays.copyOf(objArr, AbstractC05160uM.A01(length, size));
            }
            for (Map.Entry<? extends K, ? extends V> entry : iterable) {
                put(entry);
            }
            return this;
        }

        public ImmutableMap<K, V> build() {
            return RegularImmutableMap.A00(this.A00, this.A01);
        }

        @CanIgnoreReturnValue
        public Builder<K, V> A01(Map<? extends K, ? extends V> map) {
            A00(map.entrySet());
            return this;
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
                this.A01 = Arrays.copyOf(objArr, AbstractC05160uM.A01(length, i));
            }
            AnonymousClass0th.A01(k, v);
            Object[] objArr2 = this.A01;
            int i2 = this.A00;
            int i3 = i2 << 1;
            objArr2[i3] = k;
            objArr2[i3 + 1] = v;
            this.A00 = i2 + 1;
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

        public final Object A00(Builder<Object, Object> builder) {
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
            return A00(new Builder<>(this.keys.length));
        }

        public SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            ImmutableSet<Map.Entry<K, V>> immutableSet = immutableMap.A00;
            if (immutableSet == null) {
                immutableSet = (ImmutableSet<Map.Entry<K, V>>) immutableMap.A0B();
                immutableMap.A00 = immutableSet;
            }
            AbstractC05710wh<Map.Entry<K, V>> A0I = immutableSet.iterator();
            int i = 0;
            while (A0I.hasNext()) {
                Map.Entry<K, V> next = A0I.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }
    }

    public abstract ImmutableCollection<V> A08();

    public abstract ImmutableSet<K> A0A();

    public abstract ImmutableSet<Map.Entry<K, V>> A0B();

    public abstract boolean A0C();

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    public static <K, V> Builder<K, V> A04() {
        return new Builder<>();
    }

    /* renamed from: A07 */
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.A01;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> A08 = A08();
        this.A01 = A08;
        return A08;
    }

    /* renamed from: A09 */
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.A02;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> A0A = A0A();
        this.A02 = A0A;
        return A0A;
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> A0B = A0B();
        this.A00 = A0B;
        return A0B;
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

    public final int hashCode() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.A00;
        if (immutableSet == null) {
            immutableSet = A0B();
            this.A00 = immutableSet;
        }
        return AnonymousClass0wE.A00(immutableSet);
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

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <K, V> ImmutableMap<K, V> A05(K k, V v) {
        AnonymousClass0th.A01(k, v);
        return RegularImmutableMap.A00(1, new Object[]{k, v});
    }

    public static <K, V> ImmutableMap<K, V> A06(K k, V v, K k2, V v2) {
        AnonymousClass0th.A01(k, v);
        AnonymousClass0th.A01(k2, v2);
        return RegularImmutableMap.A00(2, new Object[]{k, v, k2, v2});
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.A03;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        if (v2 == null) {
            return v;
        }
        return v2;
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int size = size();
        AnonymousClass0th.A00(size, "size");
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
