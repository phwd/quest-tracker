package com.facebook.acra.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import javax.annotation.Nullable;

public class SSLConnectionProvider implements HttpConnectionProvider {
    @Nullable
    private final Proxy mProxy;
    private final int mSocketTimeout;

    public SSLConnectionProvider(int i, @Nullable Proxy proxy) {
        this.mSocketTimeout = i;
        this.mProxy = proxy;
    }

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        Proxy proxy = this.mProxy;
        return initializeConnectionParameters((HttpURLConnection) (proxy != null ? url.openConnection(proxy) : url.openConnection()));
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.mSocketTimeout);
        httpURLConnection.setReadTimeout(this.mSocketTimeout);
        return httpURLConnection;
    }
}
