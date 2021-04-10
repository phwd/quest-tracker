package com.oculus.auth.components;

import android.annotation.SuppressLint;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public final class UnexpectedResponseError extends MarshallableError {
    public UnexpectedResponseError() {
        super(-7);
    }

    public UnexpectedResponseError(String str) {
        super(-7, null, str);
    }
}
