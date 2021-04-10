package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;

/* access modifiers changed from: package-private */
@GwtCompatible
public interface SortedIterable<T> extends Iterable<T> {
    Comparator<? super T> comparator();
}
