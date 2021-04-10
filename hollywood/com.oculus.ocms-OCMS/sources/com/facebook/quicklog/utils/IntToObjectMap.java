package com.facebook.quicklog.utils;

import javax.annotation.Nullable;

public interface IntToObjectMap<E> {
    void append(int i, @Nullable E e);

    void clear();

    E get(int i);

    int indexOfKey(int i);

    void put(int i, @Nullable E e);

    void remove(int i);

    void setValueAt(int i, @Nullable E e);

    int size();

    E valueAt(int i);
}
