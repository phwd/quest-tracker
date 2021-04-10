package com.google.common.collect;

import X.QN;
import X.Qc;
import X.Rt;
import X.TW;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.RegularImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
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

        public static void A00(Builder builder, int i) {
            int i2 = i << 1;
            Object[] objArr = builder.A01;
            int length = objArr.length;
            if (i2 <= length) {
                return;
            }
            if (i2 >= 0) {
                int i3 = length + (length >> 1) + 1;
                if (i3 < i2) {
                    i3 = Integer.highestOneBit(i2 - 1) << 1;
                }
                if (i3 < 0) {
                    i3 = Integer.MAX_VALUE;
                }
                builder.A01 = Arrays.copyOf(objArr, i3);
                return;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
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
            A00(this, this.A00 + 1);
            QN.A01(k, v);
            Object[] objArr = this.A01;
            int i = this.A00;
            int i2 = i << 1;
            objArr[i2] = k;
            objArr[i2 + 1] = v;
            this.A00 = i + 1;
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
            RegularImmutableMap.EntrySet entrySet = immutableMap.A01;
            if (entrySet == null) {
                RegularImmutableMap regularImmutableMap = (RegularImmutableMap) immutableMap;
                entrySet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.A02, regularImmutableMap.A00);
                immutableMap.A01 = entrySet;
            }
            TW<Map.Entry<K, V>> A0H = entrySet.iterator();
            int i = 0;
            while (A0H.hasNext()) {
                Map.Entry<K, V> next = A0H.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }
    }

    @Override // java.util.Map
    public final V get(@NullableDecl Object obj) {
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        int[] iArr = regularImmutableMap.A01;
        Object[] objArr = regularImmutableMap.A02;
        int i = regularImmutableMap.A00;
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
            int A002 = Qc.A00(obj.hashCode());
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

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = this.A00;
        if (keysOrValuesAsList == null) {
            RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
            keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 1, regularImmutableMap.A00);
            this.A00 = keysOrValuesAsList;
        }
        return keysOrValuesAsList.contains(obj);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.A01;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.EntrySet entrySet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.A02, regularImmutableMap.A00);
        this.A01 = entrySet;
        return entrySet;
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
        RegularImmutableMap.EntrySet entrySet = this.A01;
        if (entrySet == null) {
            RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
            entrySet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.A02, regularImmutableMap.A00);
            this.A01 = entrySet;
        }
        return Rt.A00(entrySet);
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        ImmutableSet<K> immutableSet = this.A02;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeySet keySet = new RegularImmutableMap.KeySet(regularImmutableMap, new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 0, regularImmutableMap.A00));
        this.A02 = keySet;
        return keySet;
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
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 1, regularImmutableMap.A00);
        this.A00 = keysOrValuesAsList;
        return keysOrValuesAsList;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
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

    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        int size = size();
        QN.A00(size, "size");
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
