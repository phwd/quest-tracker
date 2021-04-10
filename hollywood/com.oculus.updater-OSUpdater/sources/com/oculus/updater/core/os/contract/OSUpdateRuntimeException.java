package com.oculus.updater.core.os.contract;

public class OSUpdateRuntimeException extends RuntimeException {
    public OSUpdateRuntimeException(String str) {
        super(str);
    }

    public OSUpdateRuntimeException(String str, Throwable th) {
        super(str, th);
    }
}
