package com.google.common.collect;

import X.AbstractC0144Di;
import com.google.common.collect.ImmutableSetMultimap;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class ImmutableMultimap extends AbstractC0144Di implements Serializable {
    public static final long serialVersionUID = 0;
    public final transient int A00;
    public final transient ImmutableMap A01;

    public class EntryCollection<K, V> extends ImmutableCollection<Map.Entry<K, V>> {
        public static final long serialVersionUID = 0;
        public final ImmutableMultimap multimap;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.multimap.A1U(entry.getKey(), entry.getValue());
        }

        public final int size() {
            return this.multimap.size();
        }

        public EntryCollection(ImmutableMultimap immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }
    }

    @Override // X.AbstractC1156tw
    public /* bridge */ /* synthetic */ Collection A02() {
        if (!(this instanceof ImmutableSetMultimap)) {
            return (ImmutableCollection) super.A02();
        }
        ImmutableSetMultimap immutableSetMultimap = (ImmutableSetMultimap) this;
        ImmutableSet immutableSet = immutableSetMultimap.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSetMultimap.EntrySet entrySet = new ImmutableSetMultimap.EntrySet(immutableSetMultimap);
        immutableSetMultimap.A00 = entrySet;
        return entrySet;
    }

    @Override // X.UK
    public /* bridge */ /* synthetic */ Collection A2E(Object obj) {
        ImmutableSetMultimap immutableSetMultimap = (ImmutableSetMultimap) this;
        Object obj2 = ((ImmutableMultimap) immutableSetMultimap).A01.get(obj);
        ImmutableSet immutableSet = immutableSetMultimap.A01;
        if (obj2 == null) {
            if (immutableSet != null) {
                obj2 = immutableSet;
            } else {
                throw new NullPointerException("Both parameters are null");
            }
        }
        return (ImmutableCollection) obj2;
    }

    @Override // X.UK
    public /* bridge */ /* synthetic */ Collection A4n(Object obj) {
        if (!(this instanceof ImmutableSetMultimap)) {
            throw new UnsupportedOperationException();
        }
        throw new UnsupportedOperationException();
    }

    @Override // X.UK
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // X.UK
    public final boolean containsKey(Object obj) {
        return this.A01.containsKey(obj);
    }

    public ImmutableMultimap(ImmutableMap immutableMap, int i) {
        this.A01 = immutableMap;
        this.A00 = i;
    }

    @Override // X.UK
    public final int size() {
        return this.A00;
    }
}
