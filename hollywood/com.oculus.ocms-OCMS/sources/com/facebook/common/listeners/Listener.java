package com.facebook.common.listeners;

public interface Listener<A, K> {
    void call(A a, K k);
}
