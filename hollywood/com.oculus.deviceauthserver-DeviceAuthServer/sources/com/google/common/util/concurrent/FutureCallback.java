package com.google.common.util.concurrent;

import javax.annotation.Nullable;

public interface FutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(@Nullable V v);
}
