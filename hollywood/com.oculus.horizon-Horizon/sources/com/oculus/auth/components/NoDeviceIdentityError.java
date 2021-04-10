package com.oculus.auth.components;

import android.annotation.SuppressLint;
import javax.annotation.concurrent.Immutable;

@SuppressLint({"CheckSerializableClass"})
@Immutable
public final class NoDeviceIdentityError extends MarshallableError {
    public NoDeviceIdentityError(String str) {
        super(-16, null, str);
    }

    public NoDeviceIdentityError(Throwable th) {
        super(-16, th);
    }
}
