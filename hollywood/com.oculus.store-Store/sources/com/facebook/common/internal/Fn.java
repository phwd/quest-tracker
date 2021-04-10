package com.facebook.common.internal;

public interface Fn<A, R> {
    R apply(A a);
}
