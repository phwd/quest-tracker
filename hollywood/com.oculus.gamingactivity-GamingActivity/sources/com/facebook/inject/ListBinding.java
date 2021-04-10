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

    public ListBinding<T> add(Class<? extends T> other) {
        return add(Key.get((Class) other));
    }

    public ListBinding<T> add(Class<? extends T> other, Class<? extends Annotation> annotation) {
        return add(Key.get((Class) other, annotation));
    }

    public ListBinding<T> add(Key<? extends T> key) {
        this.mValues.add(key);
        return this;
    }

    public List<Key<? extends T>> getValues() {
        return this.mValues;
    }
}
