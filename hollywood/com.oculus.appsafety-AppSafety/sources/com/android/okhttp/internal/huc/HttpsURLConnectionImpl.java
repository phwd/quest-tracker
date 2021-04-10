package com.android.okhttp.internal.huc;

import com.android.okhttp.Handshake;
import com.android.okhttp.OkHttpClient;
import com.android.okhttp.internal.URLFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public final class HttpsURLConnectionImpl extends DelegatingHttpsURLConnection {
    private final HttpURLConnectionImpl delegate;

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void addRequestProperty(String str, String str2) {
        super.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void connect() throws IOException {
        super.connect();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void disconnect() {
        super.disconnect();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getAllowUserInteraction() {
        return super.getAllowUserInteraction();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getCipherSuite() {
        return super.getCipherSuite();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getConnectTimeout() {
        return super.getConnectTimeout();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Object getContent() throws IOException {
        return super.getContent();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Object getContent(Class[] clsArr) throws IOException {
        return super.getContent(clsArr);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getContentEncoding() {
        return super.getContentEncoding();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getContentLength() {
        return super.getContentLength();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getContentType() {
        return super.getContentType();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getDate() {
        return super.getDate();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDefaultUseCaches() {
        return super.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDoInput() {
        return super.getDoInput();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDoOutput() {
        return super.getDoOutput();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ InputStream getErrorStream() {
        return super.getErrorStream();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getExpiration() {
        return super.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderField(int i) {
        return super.getHeaderField(i);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderField(String str) {
        return super.getHeaderField(str);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getHeaderFieldDate(String str, long j) {
        return super.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getHeaderFieldInt(String str, int i) {
        return super.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderFieldKey(int i) {
        return super.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Map getHeaderFields() {
        return super.getHeaderFields();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getIfModifiedSince() {
        return super.getIfModifiedSince();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ InputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getInstanceFollowRedirects() {
        return super.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getLastModified() {
        return super.getLastModified();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Certificate[] getLocalCertificates() {
        return super.getLocalCertificates();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Principal getLocalPrincipal() {
        return super.getLocalPrincipal();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ OutputStream getOutputStream() throws IOException {
        return super.getOutputStream();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return super.getPeerPrincipal();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Permission getPermission() throws IOException {
        return super.getPermission();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getReadTimeout() {
        return super.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getRequestMethod() {
        return super.getRequestMethod();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Map getRequestProperties() {
        return super.getRequestProperties();
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getRequestProperty(String str) {
        return super.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getResponseCode() throws IOException {
        return super.getResponseCode();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getResponseMessage() throws IOException {
        return super.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return super.getServerCertificates();
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
    public /* bridge */ /* synthetic */ void setAllowUserInteraction(boolean z) {
        super.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setChunkedStreamingMode(int i) {
        super.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setConnectTimeout(int i) {
        super.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDefaultUseCaches(boolean z) {
        super.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDoInput(boolean z) {
        super.setDoInput(z);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDoOutput(boolean z) {
        super.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setFixedLengthStreamingMode(int i) {
        super.setFixedLengthStreamingMode(i);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setInstanceFollowRedirects(boolean z) {
        super.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setReadTimeout(int i) {
        super.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setRequestMethod(String str) throws ProtocolException {
        super.setRequestMethod(str);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setRequestProperty(String str, String str2) {
        super.setRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setUseCaches(boolean z) {
        super.setUseCaches(z);
    }

    @Override // java.net.URLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // java.net.HttpURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean usingProxy() {
        return super.usingProxy();
    }

    public HttpsURLConnectionImpl(URL url, OkHttpClient client) {
        this(new HttpURLConnectionImpl(url, client));
    }

    public HttpsURLConnectionImpl(URL url, OkHttpClient client, URLFilter filter) {
        this(new HttpURLConnectionImpl(url, client, filter));
    }

    public HttpsURLConnectionImpl(HttpURLConnectionImpl delegate2) {
        super(delegate2);
        this.delegate = delegate2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public Handshake handshake() {
        if (this.delegate.httpEngine == null) {
            throw new IllegalStateException("Connection has not yet been established");
        } else if (this.delegate.httpEngine.hasResponse()) {
            return this.delegate.httpEngine.getResponse().handshake();
        } else {
            return this.delegate.handshake;
        }
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.delegate.client.setHostnameVerifier(hostnameVerifier);
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public HostnameVerifier getHostnameVerifier() {
        return this.delegate.client.getHostnameVerifier();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        if (sslSocketFactory != null) {
            this.delegate.client.setSslSocketFactory(sslSocketFactory);
            return;
        }
        throw new IllegalArgumentException("sslSocketFactory == null");
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.android.okhttp.internal.huc.DelegatingHttpsURLConnection
    public SSLSocketFactory getSSLSocketFactory() {
        return this.delegate.client.getSslSocketFactory();
    }

    @Override // java.net.URLConnection
    public long getContentLengthLong() {
        return this.delegate.getContentLengthLong();
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long contentLength) {
        this.delegate.setFixedLengthStreamingMode(contentLength);
    }

    @Override // java.net.URLConnection
    public long getHeaderFieldLong(String field, long defaultValue) {
        return this.delegate.getHeaderFieldLong(field, defaultValue);
    }
}
