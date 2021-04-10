package com.oculus.auth.components;

import android.annotation.SuppressLint;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public final class HttpError extends MarshallableError {
    public HttpError(Throwable th) {
        super(-6, th);
    }
}
