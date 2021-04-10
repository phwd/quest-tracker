package com.google.common.base;

public interface Function<F, T> {
    T apply(F f);

    boolean equals(Object obj);
}
