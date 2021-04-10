package com.facebook.acra.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

public class SSLConnectionProvider implements HttpConnectionProvider {
    private final Proxy mProxy;
    private final int mSocketTimeout;

    public SSLConnectionProvider(int socketTimeout, Proxy proxy) {
        this.mSocketTimeout = socketTimeout;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        return initializeConnectionParameters((HttpURLConnection) (this.mProxy != null ? url.openConnection(this.mProxy) : url.openConnection()));
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection conn) {
        conn.setConnectTimeout(this.mSocketTimeout);
        conn.setReadTimeout(this.mSocketTimeout);
        return conn;
    }
}
