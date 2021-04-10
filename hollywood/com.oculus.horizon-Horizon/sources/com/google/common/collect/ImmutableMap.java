package com.google.common.collect;

import X.AbstractC06730pi;
import X.AbstractC07380s1;
import X.AnonymousClass0p6;
import X.C07190ra;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    @LazyInit
    public transient ImmutableCollection<V> A00;
    @LazyInit
    public transient ImmutableSet<K> A01;
    @LazyInit
    public transient ImmutableSet<Map.Entry<K, V>> A02;

    public static class Builder<K, V> {
        public int A00;
        public Object[] A01;

        /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Iterable<+Ljava/util/Map$Entry<+TK;+TV;>;>;)Lcom/google/common/collect/ImmutableMap$Builder<TK;TV;>; */
        @CanIgnoreReturnValue
        @Beta
        public final void A00(Iterable iterable) {
            int size;
            Object[] objArr;
            int length;
            if ((iterable instanceof Collection) && (size = (this.A00 + ((Collection) iterable).size()) << 1) > (length = (objArr = this.A01).length)) {
                this.A01 = Arrays.copyOf(objArr, AbstractC06730pi.A01(length, size));
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                put((Map.Entry) it.next());
            }
        }

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
                this.A01 = Arrays.copyOf(objArr, AbstractC06730pi.A01(length, i));
            }
            AnonymousClass0p6.A01(k, v);
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
            AbstractC07380s1<Map.Entry<?, ?>> A0K = immutableMap.entrySet().iterator();
            int i = 0;
            while (A0K.hasNext()) {
                Map.Entry<?, ?> next = A0K.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }
    }

    public abstract ImmutableCollection<V> A04();

    public abstract ImmutableSet<Map.Entry<K, V>> A06();

    public abstract ImmutableSet<K> A07();

    public abstract boolean A08();

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    public static <K, V> Builder<K, V> A01() {
        return new Builder<>();
    }

    /* renamed from: A05 */
    public final ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.A02;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> A06 = A06();
        this.A02 = A06;
        return A06;
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        ImmutableCollection<V> immutableCollection = this.A00;
        if (immutableCollection == null) {
            immutableCollection = A04();
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
    public final /* bridge */ /* synthetic */ Set keySet() {
        ImmutableSet<K> immutableSet = this.A01;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> A07 = A07();
        this.A01 = A07;
        return A07;
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
        ImmutableCollection<V> A04 = A04();
        this.A00 = A04;
        return A04;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <K, V> ImmutableMap<K, V> A02(K k, V v) {
        AnonymousClass0p6.A01(k, v);
        return RegularImmutableMap.A00(1, new Object[]{k, v});
    }

    public static <K, V> ImmutableMap<K, V> A03(K k, V v, K k2, V v2) {
        AnonymousClass0p6.A01(k, v);
        AnonymousClass0p6.A01(k2, v2);
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

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        if (v2 == null) {
            return v;
        }
        return v2;
    }

    public final int hashCode() {
        return C07190ra.A00(entrySet());
    }

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int size = size();
        AnonymousClass0p6.A00(size, "size");
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
