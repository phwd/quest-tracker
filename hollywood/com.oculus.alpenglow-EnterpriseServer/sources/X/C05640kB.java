package X;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* renamed from: X.0kB  reason: invalid class name and case insensitive filesystem */
public final class C05640kB {
    public final InetSocketAddress A00;
    public final Proxy A01;
    public final C06800nu A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C05640kB)) {
            return false;
        }
        C05640kB r4 = (C05640kB) obj;
        if (!this.A02.equals(r4.A02) || !this.A01.equals(r4.A01) || !this.A00.equals(r4.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((((527 + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode();
    }

    public final String toString() {
        return "Route{" + this.A00 + "}";
    }

    public C05640kB(C06800nu r3, Proxy proxy, InetSocketAddress inetSocketAddress) {
        String str;
        if (r3 == null) {
            str = "address == null";
        } else if (proxy == null) {
            str = "proxy == null";
        } else if (inetSocketAddress != null) {
            this.A02 = r3;
            this.A01 = proxy;
            this.A00 = inetSocketAddress;
            return;
        } else {
            str = "inetSocketAddress == null";
        }
        throw new NullPointerException(str);
    }
}
