package com.google.common.collect;

import java.util.Comparator;

/* access modifiers changed from: package-private */
public interface SortedIterable<T> extends Iterable<T> {
    Comparator<? super T> comparator();
}
