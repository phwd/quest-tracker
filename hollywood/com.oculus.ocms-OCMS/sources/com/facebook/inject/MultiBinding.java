package com.facebook.inject;

import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Set;

public class MultiBinding<T> {
    private final Key<T> mKey;
    private final ArrayListSet<Key<? extends T>> mValues = new ArrayListSet<>();

    public MultiBinding(Key<T> key) {
        this.mKey = key;
    }

    public Key<T> getKey() {
        return this.mKey;
    }

    public MultiBinding<T> add(Class<? extends T> cls) {
        return add(Key.get((Class) cls));
    }

    public MultiBinding<T> add(Class<? extends T> cls, Class<? extends Annotation> cls2) {
        return add(Key.get((Class) cls, cls2));
    }

    public MultiBinding<T> add(Key<? extends T> key) {
        this.mValues.add(key);
        return this;
    }

    public Set<Key<? extends T>> getValues() {
        return this.mValues;
    }

    /* access modifiers changed from: private */
    public static class ArrayListSet<T> extends ArrayList<T> implements Set<T> {
        private ArrayListSet() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Set, java.util.ArrayList
        public boolean add(T t) {
            return !contains(t) && super.add(t);
        }
    }
}
