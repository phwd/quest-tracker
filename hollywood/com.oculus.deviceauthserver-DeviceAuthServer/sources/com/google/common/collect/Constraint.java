package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* access modifiers changed from: package-private */
@GwtCompatible
public interface Constraint<E> {
    E checkElement(E e);

    String toString();
}
