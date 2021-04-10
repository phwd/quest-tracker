package com.oculus.http.socketconfig;

import X.AbstractC00970Od;

public class OculusSocketConfig implements AbstractC00970Od {
    public static final int CONNECT_TIMEOUT_MILLIS = 15000;
    public static final int READ_TIMEOUT_MILLIS = 15000;

    @Override // X.AbstractC00970Od
    public int getConnectTimeoutMillis() {
        return 15000;
    }

    @Override // X.AbstractC00970Od
    public int getSocketTimeoutMillis() {
        return 15000;
    }

    public int getReadBufferSize() {
        throw new IllegalStateException("Undefined");
    }
}
