package X;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* renamed from: X.bt  reason: case insensitive filesystem */
public final class C0556bt {
    public final InetSocketAddress A00;
    public final Proxy A01;
    public final C0523bM A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0556bt)) {
            return false;
        }
        C0556bt btVar = (C0556bt) obj;
        if (!this.A02.equals(btVar.A02) || !this.A01.equals(btVar.A01) || !this.A00.equals(btVar.A00)) {
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

    public C0556bt(C0523bM bMVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (bMVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress != null) {
            this.A02 = bMVar;
            this.A01 = proxy;
            this.A00 = inetSocketAddress;
        } else {
            throw new NullPointerException("inetSocketAddress == null");
        }
    }
}
