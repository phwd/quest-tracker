package com.squareup.okhttp;

import com.facebook.common.build.config.BuildConfig;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.internal.Util;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class Address {
    final Authenticator authenticator;
    final CertificatePinner certificatePinner;
    final List<ConnectionSpec> connectionSpecs;
    final Dns dns;
    final HostnameVerifier hostnameVerifier;
    final List<Protocol> protocols;
    final Proxy proxy;
    final ProxySelector proxySelector;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final HttpUrl url;

    public Address(String uriHost, int uriPort, Dns dns2, SocketFactory socketFactory2, SSLSocketFactory sslSocketFactory2, HostnameVerifier hostnameVerifier2, CertificatePinner certificatePinner2, Authenticator authenticator2, Proxy proxy2, List<Protocol> protocols2, List<ConnectionSpec> connectionSpecs2, ProxySelector proxySelector2) {
        this.url = new HttpUrl.Builder().scheme(sslSocketFactory2 != null ? BuildConfig.HTTPS_SCHEME : BuildConfig.HTTP_SCHEME).host(uriHost).port(uriPort).build();
        if (dns2 == null) {
            throw new IllegalArgumentException("dns == null");
        }
        this.dns = dns2;
        if (socketFactory2 == null) {
            throw new IllegalArgumentException("socketFactory == null");
        }
        this.socketFactory = socketFactory2;
        if (authenticator2 == null) {
            throw new IllegalArgumentException("authenticator == null");
        }
        this.authenticator = authenticator2;
        if (protocols2 == null) {
            throw new IllegalArgumentException("protocols == null");
        }
        this.protocols = Util.immutableList(protocols2);
        if (connectionSpecs2 == null) {
            throw new IllegalArgumentException("connectionSpecs == null");
        }
        this.connectionSpecs = Util.immutableList(connectionSpecs2);
        if (proxySelector2 == null) {
            throw new IllegalArgumentException("proxySelector == null");
        }
        this.proxySelector = proxySelector2;
        this.proxy = proxy2;
        this.sslSocketFactory = sslSocketFactory2;
        this.hostnameVerifier = hostnameVerifier2;
        this.certificatePinner = certificatePinner2;
    }

    public HttpUrl url() {
        return this.url;
    }

    @Deprecated
    public String getUriHost() {
        return this.url.host();
    }

    @Deprecated
    public int getUriPort() {
        return this.url.port();
    }

    public Dns getDns() {
        return this.dns;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public List<Protocol> getProtocols() {
        return this.protocols;
    }

    public List<ConnectionSpec> getConnectionSpecs() {
        return this.connectionSpecs;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public CertificatePinner getCertificatePinner() {
        return this.certificatePinner;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Address)) {
            return false;
        }
        Address that = (Address) other;
        if (!this.url.equals(that.url) || !this.dns.equals(that.dns) || !this.authenticator.equals(that.authenticator) || !this.protocols.equals(that.protocols) || !this.connectionSpecs.equals(that.connectionSpecs) || !this.proxySelector.equals(that.proxySelector) || !Util.equal(this.proxy, that.proxy) || !Util.equal(this.sslSocketFactory, that.sslSocketFactory) || !Util.equal(this.hostnameVerifier, that.hostnameVerifier) || !Util.equal(this.certificatePinner, that.certificatePinner)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int hashCode = (((((((((((this.url.hashCode() + 527) * 31) + this.dns.hashCode()) * 31) + this.authenticator.hashCode()) * 31) + this.protocols.hashCode()) * 31) + this.connectionSpecs.hashCode()) * 31) + this.proxySelector.hashCode()) * 31;
        if (this.proxy != null) {
            i = this.proxy.hashCode();
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.sslSocketFactory != null) {
            i2 = this.sslSocketFactory.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        if (this.hostnameVerifier != null) {
            i3 = this.hostnameVerifier.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.certificatePinner != null) {
            i4 = this.certificatePinner.hashCode();
        }
        return i7 + i4;
    }
}
