package com.oculus.auth.components;

import android.annotation.SuppressLint;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public final class GenericError extends MarshallableError {
    public GenericError(String str) {
        super(-7, null, str);
    }

    public GenericError(Throwable th) {
        super(-7, th);
    }
}
