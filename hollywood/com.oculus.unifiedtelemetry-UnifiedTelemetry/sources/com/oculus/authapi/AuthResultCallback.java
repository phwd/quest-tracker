package com.oculus.authapi;

import com.oculus.authapi.AuthError;
import javax.annotation.Nullable;

public interface AuthResultCallback<T, E extends AuthError> {
    void A3f(E e);

    void A3t(@Nullable T t);
}
