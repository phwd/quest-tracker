package com.squareup.okhttp.internal.huc;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.OkHttpClient;
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

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void addRequestProperty(String str, String str2) {
        super.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void connect() throws IOException {
        super.connect();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void disconnect() {
        super.disconnect();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getAllowUserInteraction() {
        return super.getAllowUserInteraction();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getCipherSuite() {
        return super.getCipherSuite();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getConnectTimeout() {
        return super.getConnectTimeout();
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Object getContent() throws IOException {
        return super.getContent();
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Object getContent(Class[] clsArr) throws IOException {
        return super.getContent(clsArr);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getContentEncoding() {
        return super.getContentEncoding();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getContentLength() {
        return super.getContentLength();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getContentType() {
        return super.getContentType();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getDate() {
        return super.getDate();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDefaultUseCaches() {
        return super.getDefaultUseCaches();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDoInput() {
        return super.getDoInput();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getDoOutput() {
        return super.getDoOutput();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ InputStream getErrorStream() {
        return super.getErrorStream();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getExpiration() {
        return super.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderField(int i) {
        return super.getHeaderField(i);
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderField(String str) {
        return super.getHeaderField(str);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getHeaderFieldDate(String str, long j) {
        return super.getHeaderFieldDate(str, j);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getHeaderFieldInt(String str, int i) {
        return super.getHeaderFieldInt(str, i);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getHeaderFieldKey(int i) {
        return super.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Map getHeaderFields() {
        return super.getHeaderFields();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getIfModifiedSince() {
        return super.getIfModifiedSince();
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ InputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getInstanceFollowRedirects() {
        return super.getInstanceFollowRedirects();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ long getLastModified() {
        return super.getLastModified();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Certificate[] getLocalCertificates() {
        return super.getLocalCertificates();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Principal getLocalPrincipal() {
        return super.getLocalPrincipal();
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ OutputStream getOutputStream() throws IOException {
        return super.getOutputStream();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return super.getPeerPrincipal();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Permission getPermission() throws IOException {
        return super.getPermission();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getReadTimeout() {
        return super.getReadTimeout();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getRequestMethod() {
        return super.getRequestMethod();
    }

    @Override // java.net.URLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Map getRequestProperties() {
        return super.getRequestProperties();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getRequestProperty(String str) {
        return super.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ int getResponseCode() throws IOException {
        return super.getResponseCode();
    }

    @Override // java.net.HttpURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String getResponseMessage() throws IOException {
        return super.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return super.getServerCertificates();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ URL getURL() {
        return super.getURL();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean getUseCaches() {
        return super.getUseCaches();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setAllowUserInteraction(boolean z) {
        super.setAllowUserInteraction(z);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setChunkedStreamingMode(int i) {
        super.setChunkedStreamingMode(i);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setConnectTimeout(int i) {
        super.setConnectTimeout(i);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDefaultUseCaches(boolean z) {
        super.setDefaultUseCaches(z);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDoInput(boolean z) {
        super.setDoInput(z);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setDoOutput(boolean z) {
        super.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setFixedLengthStreamingMode(int i) {
        super.setFixedLengthStreamingMode(i);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setInstanceFollowRedirects(boolean z) {
        super.setInstanceFollowRedirects(z);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setReadTimeout(int i) {
        super.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection, com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setRequestMethod(String str) throws ProtocolException {
        super.setRequestMethod(str);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setRequestProperty(String str, String str2) {
        super.setRequestProperty(str, str2);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ void setUseCaches(boolean z) {
        super.setUseCaches(z);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public /* bridge */ /* synthetic */ boolean usingProxy() {
        return super.usingProxy();
    }

    public HttpsURLConnectionImpl(URL url, OkHttpClient okHttpClient) {
        this(new HttpURLConnectionImpl(url, okHttpClient));
    }

    public HttpsURLConnectionImpl(HttpURLConnectionImpl httpURLConnectionImpl) {
        super(httpURLConnectionImpl);
        this.delegate = httpURLConnectionImpl;
    }

    /* access modifiers changed from: protected */
    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public Handshake handshake() {
        if (this.delegate.httpEngine != null) {
            return this.delegate.httpEngine.hasResponse() ? this.delegate.httpEngine.getResponse().handshake() : this.delegate.handshake;
        }
        throw new IllegalStateException("Connection has not yet been established");
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.delegate.client.setHostnameVerifier(hostnameVerifier);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public HostnameVerifier getHostnameVerifier() {
        return this.delegate.client.getHostnameVerifier();
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate.client.setSslSocketFactory(sSLSocketFactory);
    }

    @Override // com.squareup.okhttp.internal.huc.DelegatingHttpsURLConnection
    public SSLSocketFactory getSSLSocketFactory() {
        return this.delegate.client.getSslSocketFactory();
    }

    public long getContentLengthLong() {
        return this.delegate.getContentLengthLong();
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        this.delegate.setFixedLengthStreamingMode(j);
    }

    public long getHeaderFieldLong(String str, long j) {
        return this.delegate.getHeaderFieldLong(str, j);
    }
}
