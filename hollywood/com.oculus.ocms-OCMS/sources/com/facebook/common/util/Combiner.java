package com.facebook.common.util;

public interface Combiner<T> {
    T combine(T t, T t2);
}
