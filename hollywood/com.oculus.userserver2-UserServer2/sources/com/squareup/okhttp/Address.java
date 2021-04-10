package com.squareup.okhttp;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.internal.Util;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class Address {
    public final Authenticator authenticator;
    public final CertificatePinner certificatePinner;
    public final List<ConnectionSpec> connectionSpecs;
    public final Dns dns;
    public final HostnameVerifier hostnameVerifier;
    public final List<Protocol> protocols;
    public final Proxy proxy;
    public final ProxySelector proxySelector;
    public final SocketFactory socketFactory;
    public final SSLSocketFactory sslSocketFactory;
    public final HttpUrl url;

    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        if (!this.url.equals(address.url) || !this.dns.equals(address.dns) || !this.authenticator.equals(address.authenticator) || !this.protocols.equals(address.protocols) || !this.connectionSpecs.equals(address.connectionSpecs) || !this.proxySelector.equals(address.proxySelector) || !Util.equal(this.proxy, address.proxy) || !Util.equal(this.sslSocketFactory, address.sslSocketFactory) || !Util.equal(this.hostnameVerifier, address.hostnameVerifier) || !Util.equal(this.certificatePinner, address.certificatePinner)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public String getUriHost() {
        return this.url.host;
    }

    @Deprecated
    public int getUriPort() {
        return this.url.port;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int hashCode = (((((((((((527 + this.url.hashCode()) * 31) + this.dns.hashCode()) * 31) + this.authenticator.hashCode()) * 31) + this.protocols.hashCode()) * 31) + this.connectionSpecs.hashCode()) * 31) + this.proxySelector.hashCode()) * 31;
        Proxy proxy2 = this.proxy;
        int i4 = 0;
        if (proxy2 != null) {
            i = proxy2.hashCode();
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        SSLSocketFactory sSLSocketFactory = this.sslSocketFactory;
        if (sSLSocketFactory != null) {
            i2 = sSLSocketFactory.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        HostnameVerifier hostnameVerifier2 = this.hostnameVerifier;
        if (hostnameVerifier2 != null) {
            i3 = hostnameVerifier2.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        CertificatePinner certificatePinner2 = this.certificatePinner;
        if (certificatePinner2 != null) {
            i4 = certificatePinner2.hashCode();
        }
        return i7 + i4;
    }

    public Authenticator getAuthenticator() {
        return this.authenticator;
    }

    public CertificatePinner getCertificatePinner() {
        return this.certificatePinner;
    }

    public List<ConnectionSpec> getConnectionSpecs() {
        return this.connectionSpecs;
    }

    public Dns getDns() {
        return this.dns;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public List<Protocol> getProtocols() {
        return this.protocols;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public ProxySelector getProxySelector() {
        return this.proxySelector;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public HttpUrl url() {
        return this.url;
    }

    public Address(String str, int i, Dns dns2, SocketFactory socketFactory2, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier2, CertificatePinner certificatePinner2, Authenticator authenticator2, Proxy proxy2, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector2) {
        String str2;
        String str3;
        HttpUrl.Builder builder = new HttpUrl.Builder();
        if (sSLSocketFactory != null) {
            str2 = "https";
        } else {
            str2 = "http";
        }
        builder.scheme(str2);
        builder.host(str);
        builder.port(i);
        this.url = builder.build();
        if (dns2 != null) {
            this.dns = dns2;
            if (socketFactory2 != null) {
                this.socketFactory = socketFactory2;
                if (authenticator2 != null) {
                    this.authenticator = authenticator2;
                    if (list != null) {
                        this.protocols = Util.immutableList(list);
                        if (list2 != null) {
                            this.connectionSpecs = Util.immutableList(list2);
                            if (proxySelector2 != null) {
                                this.proxySelector = proxySelector2;
                                this.proxy = proxy2;
                                this.sslSocketFactory = sSLSocketFactory;
                                this.hostnameVerifier = hostnameVerifier2;
                                this.certificatePinner = certificatePinner2;
                                return;
                            }
                            str3 = "proxySelector == null";
                        } else {
                            str3 = "connectionSpecs == null";
                        }
                    } else {
                        str3 = "protocols == null";
                    }
                } else {
                    str3 = "authenticator == null";
                }
            } else {
                str3 = "socketFactory == null";
            }
        } else {
            str3 = "dns == null";
        }
        throw new IllegalArgumentException(str3);
    }
}
