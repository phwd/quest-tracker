package com.facebook.http.config;

public interface SocketConfig {
    int getConnectTimeoutMillis();

    int getSocketTimeoutMillis();
}
