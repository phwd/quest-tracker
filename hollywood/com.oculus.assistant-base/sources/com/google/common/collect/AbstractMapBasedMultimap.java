package com.google.common.collect;

import X.AbstractC1156tw;
import X.C0151Dt;
import X.C0358Ti;
import X.C1151tr;
import X.C1152ts;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public abstract class AbstractMapBasedMultimap extends AbstractC1156tw implements Serializable {
    public static final long serialVersionUID = 2447537837011683357L;
    public transient int A00;
    public transient Map A01;

    public final Collection A03() {
        if (!(this instanceof AbstractSetMultimap)) {
            return new ArrayList(((ArrayListMultimap) ((AbstractListMultimap) this)).A00);
        }
        return new CompactHashSet(((HashMultimap) ((AbstractSetMultimap) this)).A00);
    }

    public final Collection A04(Object obj, Collection collection) {
        if (this instanceof AbstractSetMultimap) {
            return new C1152ts(this, obj, (Set) collection);
        }
        if (!(this instanceof AbstractListMultimap)) {
            return new C0358Ti(this, obj, collection, null);
        }
        List list = (List) collection;
        if (list instanceof RandomAccess) {
            return new C0151Dt(this, obj, list, null);
        }
        return new C1151tr(this, obj, list, null);
    }

    public final void A05(Map map) {
        this.A01 = map;
        this.A00 = 0;
        for (Collection collection : map.values()) {
            Preconditions.checkArgument(!collection.isEmpty());
            this.A00 += collection.size();
        }
    }

    @Override // X.UK
    public Collection A2E(Object obj) {
        Collection collection = (Collection) this.A01.get(obj);
        if (collection == null) {
            collection = A03();
        }
        return A04(obj, collection);
    }

    @Override // X.UK
    public Collection A4n(Object obj) {
        Collection collection = (Collection) this.A01.remove(obj);
        if (collection == null) {
            boolean z = this instanceof AbstractSetMultimap;
            if (z) {
                return Collections.emptySet();
            }
            boolean z2 = this instanceof AbstractListMultimap;
            if (z2) {
                return Collections.emptyList();
            }
            Collection A03 = A03();
            if (z) {
                return Collections.unmodifiableSet((Set) A03);
            }
            if (!z2) {
                return Collections.unmodifiableCollection(A03);
            }
            return Collections.unmodifiableList((List) A03);
        }
        Collection A032 = A03();
        A032.addAll(collection);
        this.A00 -= collection.size();
        collection.clear();
        if (this instanceof AbstractSetMultimap) {
            return Collections.unmodifiableSet((Set) A032);
        }
        if (!(this instanceof AbstractListMultimap)) {
            return Collections.unmodifiableCollection(A032);
        }
        return Collections.unmodifiableList((List) A032);
    }

    @Override // X.UK
    public final void clear() {
        for (Collection collection : this.A01.values()) {
            collection.clear();
        }
        this.A01.clear();
        this.A00 = 0;
    }

    @Override // X.UK
    public final boolean containsKey(Object obj) {
        return this.A01.containsKey(obj);
    }

    public AbstractMapBasedMultimap(Map map) {
        Preconditions.checkArgument(map.isEmpty());
        this.A01 = map;
    }

    @Override // X.UK
    public final int size() {
        return this.A00;
    }
}
