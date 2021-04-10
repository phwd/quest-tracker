package com.oculus.authapi;

import com.oculus.authapi.AuthError;

public interface AuthResultCallback<T, E extends AuthError> {
    void onError(E e);

    void onResult(T t);
}
