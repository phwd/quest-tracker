package com.google.common.collect;

import X.AnonymousClass0Mv;
import com.google.common.base.Function;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

public class Lists$TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final List<F> fromList;
    public final Function<? super F, ? extends T> function;

    public final void clear() {
        this.fromList.clear();
    }

    @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
    public final ListIterator<T> listIterator(int i) {
        return new AnonymousClass0Mv(this, this.fromList.listIterator(i));
    }

    public final int size() {
        return this.fromList.size();
    }

    public Lists$TransformingSequentialList(List<F> list, Function<? super F, ? extends T> function2) {
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
}
