package com.squareup.okhttp;

import java.net.InetSocketAddress;
import java.net.Proxy;

public final class Route {
    public final Address address;
    public final InetSocketAddress inetSocketAddress;
    public final Proxy proxy;

    public boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }
        Route route = (Route) obj;
        if (!this.address.equals(route.address) || !this.proxy.equals(route.proxy) || !this.inetSocketAddress.equals(route.inetSocketAddress)) {
            return false;
        }
        return true;
    }

    public Address getAddress() {
        return this.address;
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    public InetSocketAddress getSocketAddress() {
        return this.inetSocketAddress;
    }

    public int hashCode() {
        return ((((527 + this.address.hashCode()) * 31) + this.proxy.hashCode()) * 31) + this.inetSocketAddress.hashCode();
    }

    public boolean requiresTunnel() {
        if (this.address.sslSocketFactory == null || this.proxy.type() != Proxy.Type.HTTP) {
            return false;
        }
        return true;
    }

    public Route(Address address2, Proxy proxy2, InetSocketAddress inetSocketAddress2) {
        String str;
        if (address2 == null) {
            str = "address == null";
        } else if (proxy2 == null) {
            str = "proxy == null";
        } else if (inetSocketAddress2 != null) {
            this.address = address2;
            this.proxy = proxy2;
            this.inetSocketAddress = inetSocketAddress2;
            return;
        } else {
            str = "inetSocketAddress == null";
        }
        throw new NullPointerException(str);
    }
}
