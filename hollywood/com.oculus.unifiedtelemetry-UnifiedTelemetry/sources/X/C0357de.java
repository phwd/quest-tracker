package X;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* renamed from: X.de  reason: case insensitive filesystem */
public final class C0357de {
    public final InetSocketAddress A00;
    public final Proxy A01;
    public final eB A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0357de)) {
            return false;
        }
        C0357de deVar = (C0357de) obj;
        if (!this.A02.equals(deVar.A02) || !this.A01.equals(deVar.A01) || !this.A00.equals(deVar.A00)) {
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

    public C0357de(eB eBVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        String str;
        if (eBVar == null) {
            str = "address == null";
        } else if (proxy == null) {
            str = "proxy == null";
        } else if (inetSocketAddress != null) {
            this.A02 = eBVar;
            this.A01 = proxy;
            this.A00 = inetSocketAddress;
            return;
        } else {
            str = "inetSocketAddress == null";
        }
        throw new NullPointerException(str);
    }
}
