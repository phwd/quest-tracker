package com.google.common.util.concurrent;

public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(V v);
}
