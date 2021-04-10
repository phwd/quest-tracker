package com.facebook.inject;

import com.google.inject.Key;
import java.util.ArrayList;
import java.util.Set;

public class MultiBinding<T> {
    private final ArrayListSet<Key<? extends T>> mValues;

    public Set<Key<? extends T>> getValues() {
        return this.mValues;
    }

    private static class ArrayListSet<T> extends ArrayList<T> implements Set<T> {
        private ArrayListSet() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Set, java.util.ArrayList
        public boolean add(T t) {
            return !contains(t) && super.add(t);
        }
    }
}
