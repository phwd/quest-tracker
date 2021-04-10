package X;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* renamed from: X.0wA  reason: invalid class name and case insensitive filesystem */
public final class C08200wA {
    public final InetSocketAddress A00;
    public final Proxy A01;
    public final C08610wt A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C08200wA)) {
            return false;
        }
        C08200wA r4 = (C08200wA) obj;
        if (!this.A02.equals(r4.A02) || !this.A01.equals(r4.A01) || !this.A00.equals(r4.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((((527 + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Route{");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }

    public C08200wA(C08610wt r3, Proxy proxy, InetSocketAddress inetSocketAddress) {
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
