package com.oculus.auth.components;

import android.annotation.SuppressLint;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public final class NetworkError extends MarshallableError {
    public NetworkError(Throwable th) {
        super(-4, th);
    }
}
