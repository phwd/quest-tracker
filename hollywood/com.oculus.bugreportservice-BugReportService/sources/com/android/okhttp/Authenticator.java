package com.android.okhttp;

import java.net.Proxy;

public interface Authenticator {
    Request authenticate(Proxy proxy, Response response);

    Request authenticateProxy(Proxy proxy, Response response);
}
