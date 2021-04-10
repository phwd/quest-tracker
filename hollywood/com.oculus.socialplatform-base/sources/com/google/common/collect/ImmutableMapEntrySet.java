package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

    @GwtIncompatible
    public static class EntrySetSerializedForm<K, V> implements Serializable {
        public static final long serialVersionUID = 0;
        public final ImmutableMap<K, V> map;

        public Object readResolve() {
            ImmutableMap<K, V> immutableMap = this.map;
            ImmutableSet<Map.Entry<K, V>> immutableSet = immutableMap.A00;
            if (immutableSet != null) {
                return immutableSet;
            }
            ImmutableSet<Map.Entry<K, V>> A0B = immutableMap.A0B();
            immutableMap.A00 = A0B;
            return A0B;
        }

        public EntrySetSerializedForm(ImmutableMap<K, V> immutableMap) {
            this.map = immutableMap;
        }
    }

    public abstract ImmutableMap<K, V> A0L();

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        V v = A0L().get(entry.getKey());
        if (v == null || !v.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A0F() {
        return A0L().A0C();
    }

    @Override // com.google.common.collect.ImmutableSet
    @GwtIncompatible
    public final boolean A0K() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return A0L().hashCode();
    }

    public final int size() {
        return A0L().size();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new EntrySetSerializedForm(A0L());
    }
}
