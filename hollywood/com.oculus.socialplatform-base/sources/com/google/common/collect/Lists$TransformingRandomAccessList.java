package com.google.common.collect;

import X.AnonymousClass0Mw;
import com.google.common.base.Function;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class Lists$TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
    public static final long serialVersionUID = 0;
    public final List<F> fromList;
    public final Function<? super F, ? extends T> function;

    public final void clear() {
        this.fromList.clear();
    }

    @Override // java.util.List, java.util.AbstractList
    public final T get(int i) {
        return (T) this.function.apply(this.fromList.get(i));
    }

    public final boolean isEmpty() {
        return this.fromList.isEmpty();
    }

    @Override // java.util.List, java.util.AbstractList
    public final ListIterator<T> listIterator(int i) {
        return new AnonymousClass0Mw(this, this.fromList.listIterator(i));
    }

    @Override // java.util.List, java.util.AbstractList
    public final T remove(int i) {
        return (T) this.function.apply(this.fromList.remove(i));
    }

    public final int size() {
        return this.fromList.size();
    }

    public Lists$TransformingRandomAccessList(List<F> list, Function<? super F, ? extends T> function2) {
        if (list != null) {
            this.fromList = list;
            if (function2 != null) {
                this.function = function2;
                return;
            }
            throw null;
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public final Iterator<T> iterator() {
        return listIterator();
    }
}
