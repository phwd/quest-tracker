package com.oculus.authapi;

import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

public interface AuthResultCallback<T, E extends AuthError> {
    void A2R(E e);

    void A2b(@Nullable T t);
}
