package com.facebook.acra.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.annotation.Nullable;

public class SSLConnectionProvider implements HttpConnectionProvider {
    @Nullable
    public final Proxy mProxy;
    public final int mSocketTimeout;

    @Override // com.facebook.acra.util.HttpConnectionProvider
    public HttpURLConnection getConnection(URL url) throws IOException {
        URLConnection openConnection;
        Proxy proxy = this.mProxy;
        if (proxy != null) {
            openConnection = url.openConnection(proxy);
        } else {
            openConnection = url.openConnection();
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        initializeConnectionParameters(httpURLConnection);
        return httpURLConnection;
    }

    public HttpURLConnection initializeConnectionParameters(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.mSocketTimeout);
        httpURLConnection.setReadTimeout(this.mSocketTimeout);
        return httpURLConnection;
    }

    public SSLConnectionProvider(int i, @Nullable Proxy proxy) {
        this.mSocketTimeout = i;
        this.mProxy = proxy;
    }
}
