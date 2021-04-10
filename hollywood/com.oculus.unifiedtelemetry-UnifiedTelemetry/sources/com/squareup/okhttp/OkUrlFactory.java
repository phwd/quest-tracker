package com.squareup.okhttp;

import X.AnonymousClass06;
import com.squareup.okhttp.internal.huc.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.huc.HttpsURLConnectionImpl;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public final class OkUrlFactory implements Cloneable, URLStreamHandlerFactory {
    public final OkHttpClient client;

    public URLStreamHandler createURLStreamHandler(final String str) {
        if (str.equals("http") || str.equals("https")) {
            return new URLStreamHandler() {
                /* class com.squareup.okhttp.OkUrlFactory.AnonymousClass1 */

                public int getDefaultPort() {
                    String str = str;
                    if (str.equals("http")) {
                        return 80;
                    }
                    if (str.equals("https")) {
                        return 443;
                    }
                    throw new AssertionError();
                }

                @Override // java.net.URLStreamHandler
                public URLConnection openConnection(URL url) {
                    return OkUrlFactory.this.open(url);
                }

                @Override // java.net.URLStreamHandler
                public URLConnection openConnection(URL url, Proxy proxy) {
                    return OkUrlFactory.this.open(url, proxy);
                }
            };
        }
        return null;
    }

    public OkUrlFactory(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    public OkHttpClient client() {
        return this.client;
    }

    @Override // java.lang.Object
    public OkUrlFactory clone() {
        return new OkUrlFactory(new OkHttpClient(this.client));
    }

    public HttpURLConnection open(URL url) {
        return open(url, this.client.proxy);
    }

    public HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copyWithDefaults = this.client.copyWithDefaults();
        copyWithDefaults.proxy = proxy;
        if (protocol.equals("http")) {
            return new HttpURLConnectionImpl(url, copyWithDefaults);
        }
        if (protocol.equals("https")) {
            return new HttpsURLConnectionImpl(url, copyWithDefaults);
        }
        throw new IllegalArgumentException(AnonymousClass06.A04("Unexpected protocol: ", protocol));
    }
}
