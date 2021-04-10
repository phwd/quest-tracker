package com.android.okhttp.internal.huc;

import com.android.okhttp.OkHttpClient;
import com.android.okhttp.internal.URLFilter;
import java.io.InputStream;
import java.net.URL;
import java.security.Permission;

public final class HttpsURLConnectionImpl extends DelegatingHttpsURLConnection {
    private final HttpURLConnectionImpl delegate;

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderField(String str) {
        return super.getHeaderField(str);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getHeaderFieldDate(String str, long j) {
        return super.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ InputStream getInputStream() {
        return super.getInputStream();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getLastModified() {
        return super.getLastModified();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Permission getPermission() {
        return super.getPermission();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ URL getURL() {
        return super.getURL();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getUseCaches() {
        return super.getUseCaches();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setUseCaches(boolean z) {
        super.setUseCaches(z);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public HttpsURLConnectionImpl(URL url, OkHttpClient okHttpClient, URLFilter uRLFilter) {
        this(new HttpURLConnectionImpl(url, okHttpClient, uRLFilter));
    }

    public HttpsURLConnectionImpl(HttpURLConnectionImpl httpURLConnectionImpl) {
        super(httpURLConnectionImpl);
        this.delegate = httpURLConnectionImpl;
    }
}
