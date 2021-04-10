package com.squareup.okhttp.internal.huc;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class HttpsURLConnectionImpl extends DelegatingHttpsURLConnection {
    public final HttpURLConnectionImpl delegate;

    public long getContentLengthLong() {
        return this.delegate.getContentLengthLong();
    }

    public long getHeaderFieldLong(String str, long j) {
        return this.delegate.getHeaderFieldLong(str, j);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public HostnameVerifier getHostnameVerifier() {
        return this.delegate.client.hostnameVerifier;
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public SSLSocketFactory getSSLSocketFactory() {
        return this.delegate.client.sslSocketFactory;
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public Handshake handshake() {
        HttpURLConnectionImpl httpURLConnectionImpl = this.delegate;
        HttpEngine httpEngine = httpURLConnectionImpl.httpEngine;
        if (httpEngine == null) {
            throw new IllegalStateException("Connection has not yet been established");
        } else if (httpEngine.hasResponse()) {
            return httpEngine.getResponse().handshake;
        } else {
            return httpURLConnectionImpl.handshake;
        }
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        this.delegate.setFixedLengthStreamingMode(j);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.delegate.client.hostnameVerifier = hostnameVerifier;
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate.client.sslSocketFactory = sSLSocketFactory;
    }

    public HttpsURLConnectionImpl(HttpURLConnectionImpl httpURLConnectionImpl) {
        super(httpURLConnectionImpl);
        this.delegate = httpURLConnectionImpl;
    }

    public HttpsURLConnectionImpl(URL url, OkHttpClient okHttpClient) {
        this(new HttpURLConnectionImpl(url, okHttpClient));
    }
}
