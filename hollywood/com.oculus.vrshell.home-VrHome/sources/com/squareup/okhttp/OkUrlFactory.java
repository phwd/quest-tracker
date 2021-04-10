package com.squareup.okhttp;

import com.facebook.common.build.config.BuildConfig;
import com.squareup.okhttp.internal.huc.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.huc.HttpsURLConnectionImpl;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public final class OkUrlFactory implements Cloneable, URLStreamHandlerFactory {
    private final OkHttpClient client;

    public OkUrlFactory(OkHttpClient client2) {
        this.client = client2;
    }

    public OkHttpClient client() {
        return this.client;
    }

    @Override // java.lang.Object
    public OkUrlFactory clone() {
        return new OkUrlFactory(this.client.clone());
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.getProxy());
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copy = this.client.copyWithDefaults();
        copy.setProxy(proxy);
        if (protocol.equals(BuildConfig.HTTP_SCHEME)) {
            return new HttpURLConnectionImpl(url, copy);
        }
        if (protocol.equals(BuildConfig.HTTPS_SCHEME)) {
            return new HttpsURLConnectionImpl(url, copy);
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

    public URLStreamHandler createURLStreamHandler(final String protocol) {
        if (protocol.equals(BuildConfig.HTTP_SCHEME) || protocol.equals(BuildConfig.HTTPS_SCHEME)) {
            return new URLStreamHandler() {
                /* class com.squareup.okhttp.OkUrlFactory.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // java.net.URLStreamHandler
                public URLConnection openConnection(URL url) {
                    return OkUrlFactory.this.open(url);
                }

                /* access modifiers changed from: protected */
                @Override // java.net.URLStreamHandler
                public URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }

                /* access modifiers changed from: protected */
                public int getDefaultPort() {
                    if (protocol.equals(BuildConfig.HTTP_SCHEME)) {
                        return 80;
                    }
                    if (protocol.equals(BuildConfig.HTTPS_SCHEME)) {
                        return 443;
                    }
                    throw new AssertionError();
                }
            };
        }
        return null;
    }
}
