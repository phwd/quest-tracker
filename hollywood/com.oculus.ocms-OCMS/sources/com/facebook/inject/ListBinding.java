package com.facebook.inject;

import com.google.inject.Key;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ListBinding<T> {
    private final Key<T> mKey;
    private final ArrayList<Key<? extends T>> mValues = new ArrayList<>();

    public ListBinding(Key<T> key) {
        this.mKey = key;
    }

    public Key<T> getKey() {
        return this.mKey;
    }

    public ListBinding<T> add(Class<? extends T> cls) {
        return add(Key.get((Class) cls));
    }

    public ListBinding<T> add(Class<? extends T> cls, Class<? extends Annotation> cls2) {
        return add(Key.get((Class) cls, cls2));
    }

    public ListBinding<T> add(Key<? extends T> key) {
        this.mValues.add(key);
        return this;
    }

    public List<Key<? extends T>> getValues() {
        return this.mValues;
    }
}
