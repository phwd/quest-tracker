package X;

import java.net.InetSocketAddress;
import java.net.Proxy;

public final class XI {
    public final InetSocketAddress A00;
    public final Proxy A01;
    public final C0190Xp A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof XI)) {
            return false;
        }
        XI xi = (XI) obj;
        if (!this.A02.equals(xi.A02) || !this.A01.equals(xi.A01) || !this.A00.equals(xi.A00)) {
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

    public XI(C0190Xp xp, Proxy proxy, InetSocketAddress inetSocketAddress) {
        String str;
        if (xp == null) {
            str = "address == null";
        } else if (proxy == null) {
            str = "proxy == null";
        } else if (inetSocketAddress != null) {
            this.A02 = xp;
            this.A01 = proxy;
            this.A00 = inetSocketAddress;
            return;
        } else {
            str = "inetSocketAddress == null";
        }
        throw new NullPointerException(str);
    }
}
