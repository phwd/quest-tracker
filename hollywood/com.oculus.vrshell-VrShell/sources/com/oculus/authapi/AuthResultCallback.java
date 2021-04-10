package com.oculus.authapi;

import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

public interface AuthResultCallback<T, E extends AuthError> {
    void onError(E e);

    void onResult(@Nullable T t);
}
