package com.android.okhttp.internal.huc;

import com.android.okhttp.Handshake;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* access modifiers changed from: package-private */
public abstract class DelegatingHttpsURLConnection extends HttpsURLConnection {
    private final HttpURLConnection delegate;

    @Override // javax.net.ssl.HttpsURLConnection
    public abstract HostnameVerifier getHostnameVerifier();

    @Override // javax.net.ssl.HttpsURLConnection
    public abstract SSLSocketFactory getSSLSocketFactory();

    /* access modifiers changed from: protected */
    public abstract Handshake handshake();

    @Override // javax.net.ssl.HttpsURLConnection
    public abstract void setHostnameVerifier(HostnameVerifier hostnameVerifier);

    @Override // javax.net.ssl.HttpsURLConnection
    public abstract void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory);

    public DelegatingHttpsURLConnection(HttpURLConnection delegate2) {
        super(delegate2.getURL());
        this.delegate = delegate2;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public String getCipherSuite() {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.cipherSuite();
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getLocalCertificates() {
        Handshake handshake = handshake();
        if (handshake == null) {
            return null;
        }
        List<Certificate> result = handshake.localCertificates();
        if (!result.isEmpty()) {
            return (Certificate[]) result.toArray(new Certificate[result.size()]);
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        Handshake handshake = handshake();
        if (handshake == null) {
            return null;
        }
        List<Certificate> result = handshake.peerCertificates();
        if (!result.isEmpty()) {
            return (Certificate[]) result.toArray(new Certificate[result.size()]);
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.peerPrincipal();
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getLocalPrincipal() {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.localPrincipal();
        }
        return null;
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        this.connected = true;
        this.delegate.connect();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        this.delegate.disconnect();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        return this.delegate.getErrorStream();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.delegate.getRequestMethod();
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        return this.delegate.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return this.delegate.getResponseMessage();
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String method) throws ProtocolException {
        this.delegate.setRequestMethod(method);
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.delegate.usingProxy();
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.delegate.getInstanceFollowRedirects();
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean followRedirects) {
        this.delegate.setInstanceFollowRedirects(followRedirects);
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.delegate.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        return this.delegate.getContent();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] types) throws IOException {
        return this.delegate.getContent(types);
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        return this.delegate.getContentEncoding();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        return this.delegate.getContentLength();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        return this.delegate.getContentType();
    }

    @Override // java.net.URLConnection
    public long getDate() {
        return this.delegate.getDate();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.delegate.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.delegate.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.delegate.getDoOutput();
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        return this.delegate.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int pos) {
        return this.delegate.getHeaderField(pos);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        return this.delegate.getHeaderFields();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.delegate.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String field, String newValue) {
        this.delegate.addRequestProperty(field, newValue);
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String key) {
        return this.delegate.getHeaderField(key);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String field, long defaultValue) {
        return this.delegate.getHeaderFieldDate(field, defaultValue);
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String field, int defaultValue) {
        return this.delegate.getHeaderFieldInt(field, defaultValue);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int position) {
        return this.delegate.getHeaderFieldKey(position);
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.delegate.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        return this.delegate.getInputStream();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        return this.delegate.getLastModified();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.delegate.getOutputStream();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.delegate.getPermission();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String field) {
        return this.delegate.getRequestProperty(field);
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.delegate.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.delegate.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean newValue) {
        this.delegate.setAllowUserInteraction(newValue);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean newValue) {
        this.delegate.setDefaultUseCaches(newValue);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean newValue) {
        this.delegate.setDoInput(newValue);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean newValue) {
        this.delegate.setDoOutput(newValue);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long newValue) {
        this.delegate.setIfModifiedSince(newValue);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String field, String newValue) {
        this.delegate.setRequestProperty(field, newValue);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean newValue) {
        this.delegate.setUseCaches(newValue);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int timeoutMillis) {
        this.delegate.setConnectTimeout(timeoutMillis);
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.delegate.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int timeoutMillis) {
        this.delegate.setReadTimeout(timeoutMillis);
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.delegate.getReadTimeout();
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.delegate.toString();
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int contentLength) {
        this.delegate.setFixedLengthStreamingMode(contentLength);
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int chunkLength) {
        this.delegate.setChunkedStreamingMode(chunkLength);
    }
}
