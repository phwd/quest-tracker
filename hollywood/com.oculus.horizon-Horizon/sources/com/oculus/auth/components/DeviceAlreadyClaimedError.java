package com.oculus.auth.components;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class DeviceAlreadyClaimedError extends MarshallableError {
    public DeviceAlreadyClaimedError(Throwable th) {
        super(-14);
        initCause(th);
    }
}
