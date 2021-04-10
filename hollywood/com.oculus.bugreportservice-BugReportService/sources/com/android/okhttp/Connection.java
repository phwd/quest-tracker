package com.android.okhttp;

public interface Connection {
    Handshake getHandshake();

    Route getRoute();
}
