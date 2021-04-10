package com.google.common.collect;

import java.io.Serializable;
import java.util.Map;

public abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {

    public class EntrySetSerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final ImmutableMap map;

        public Object readResolve() {
            return this.map.entrySet();
        }

        public EntrySetSerializedForm(ImmutableMap immutableMap) {
            this.map = immutableMap;
        }
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return ImmutableSortedMap.this.hashCode();
    }

    public final int size() {
        return ImmutableSortedMap.this.size();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new EntrySetSerializedForm(ImmutableSortedMap.this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = ImmutableSortedMap.this.get(entry.getKey());
        if (obj2 == null || !obj2.equals(entry.getValue())) {
            return false;
        }
        return true;
    }
}
