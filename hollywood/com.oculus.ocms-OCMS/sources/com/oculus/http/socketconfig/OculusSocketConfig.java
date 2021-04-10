package com.oculus.http.socketconfig;

import com.facebook.acra.ErrorReporter;
import com.facebook.http.config.SocketConfig;

/* access modifiers changed from: package-private */
public class OculusSocketConfig implements SocketConfig {
    private static final int CONNECT_TIMEOUT_MILLIS = 15000;
    private static final int READ_TIMEOUT_MILLIS = 15000;

    @Override // com.facebook.http.config.SocketConfig
    public int getConnectTimeoutMillis() {
        return ErrorReporter.MAX_ANR_TRACES_TIME_DELTA_MS;
    }

    @Override // com.facebook.http.config.SocketConfig
    public int getSocketTimeoutMillis() {
        return ErrorReporter.MAX_ANR_TRACES_TIME_DELTA_MS;
    }

    OculusSocketConfig() {
    }

    @Override // com.facebook.http.config.SocketConfig
    public int getReadBufferSize() {
        throw new IllegalStateException("Undefined");
    }
}
