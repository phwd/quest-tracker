package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.Xp  reason: case insensitive filesystem */
public final class C0190Xp {
    public final Proxy A00;
    public final ProxySelector A01;
    public final List<C0178Xd> A02;
    public final List<XP> A03;
    public final SocketFactory A04;
    public final HostnameVerifier A05;
    public final SSLSocketFactory A06;
    public final AbstractC0189Xo A07;
    public final C0183Xi A08;
    public final XZ A09;
    public final XT A0A;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0190Xp)) {
            return false;
        }
        C0190Xp xp = (C0190Xp) obj;
        if (!this.A0A.equals(xp.A0A) || !this.A09.equals(xp.A09) || !this.A07.equals(xp.A07) || !this.A03.equals(xp.A03) || !this.A02.equals(xp.A02) || !this.A01.equals(xp.A01) || !XD.A09(this.A00, xp.A00) || !XD.A09(this.A06, xp.A06) || !XD.A09(this.A05, xp.A05) || !XD.A09(this.A08, xp.A08)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int hashCode = (((((((((((527 + this.A0A.hashCode()) * 31) + this.A09.hashCode()) * 31) + this.A07.hashCode()) * 31) + this.A03.hashCode()) * 31) + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31;
        Proxy proxy = this.A00;
        int i4 = 0;
        if (proxy != null) {
            i = proxy.hashCode();
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        SSLSocketFactory sSLSocketFactory = this.A06;
        if (sSLSocketFactory != null) {
            i2 = sSLSocketFactory.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i5 + i2) * 31;
        HostnameVerifier hostnameVerifier = this.A05;
        if (hostnameVerifier != null) {
            i3 = hostnameVerifier.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        C0183Xi xi = this.A08;
        if (xi != null) {
            i4 = xi.hashCode();
        }
        return i7 + i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        XT xt = this.A0A;
        sb.append(xt.A02);
        sb.append(":");
        sb.append(xt.A00);
        Proxy proxy = this.A00;
        if (proxy != null) {
            sb.append(", proxy=");
            sb.append(proxy);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.A01);
        }
        sb.append("}");
        return sb.toString();
    }

    public C0190Xp(String str, int i, XZ xz, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C0183Xi xi, AbstractC0189Xo xo, Proxy proxy, List<XP> list, List<C0178Xd> list2, ProxySelector proxySelector) {
        String str2;
        String str3;
        StringBuilder sb;
        XU xu = new XU();
        if (sSLSocketFactory != null) {
            str2 = "https";
        } else {
            str2 = "http";
        }
        String str4 = "http";
        if (!str2.equalsIgnoreCase(str4)) {
            str4 = "https";
            if (!str2.equalsIgnoreCase(str4)) {
                sb = new StringBuilder("unexpected scheme: ");
                sb.append(str2);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        xu.A05 = str4;
        if (str != null) {
            String A002 = XU.A00(str, 0, str.length());
            if (A002 != null) {
                xu.A04 = A002;
                if (i <= 0 || i > 65535) {
                    sb = new StringBuilder("unexpected port: ");
                    sb.append(i);
                } else {
                    xu.A00 = i;
                    this.A0A = xu.A03();
                    if (xz != null) {
                        this.A09 = xz;
                        if (socketFactory != null) {
                            this.A04 = socketFactory;
                            if (xo != null) {
                                this.A07 = xo;
                                if (list != null) {
                                    this.A03 = Collections.unmodifiableList(new ArrayList(list));
                                    if (list2 != null) {
                                        this.A02 = Collections.unmodifiableList(new ArrayList(list2));
                                        if (proxySelector != null) {
                                            this.A01 = proxySelector;
                                            this.A00 = proxy;
                                            this.A06 = sSLSocketFactory;
                                            this.A05 = hostnameVerifier;
                                            this.A08 = xi;
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
                                str3 = "proxyAuthenticator == null";
                            }
                        } else {
                            str3 = "socketFactory == null";
                        }
                    } else {
                        str3 = "dns == null";
                    }
                }
            } else {
                sb = new StringBuilder("unexpected host: ");
                sb.append(str);
            }
            throw new IllegalArgumentException(sb.toString());
        }
        str3 = "host == null";
        throw new NullPointerException(str3);
    }
}
