package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class eB {
    public final Proxy A00;
    public final ProxySelector A01;
    public final List<C0376dz> A02;
    public final List<EnumC0364dl> A03;
    public final SocketFactory A04;
    public final HostnameVerifier A05;
    public final SSLSocketFactory A06;
    public final eA A07;
    public final e4 A08;
    public final AbstractC0372dv A09;
    public final C0367dp A0A;

    public final boolean equals(Object obj) {
        if (!(obj instanceof eB)) {
            return false;
        }
        eB eBVar = (eB) obj;
        if (!this.A0A.equals(eBVar.A0A) || !this.A09.equals(eBVar.A09) || !this.A07.equals(eBVar.A07) || !this.A03.equals(eBVar.A03) || !this.A02.equals(eBVar.A02) || !this.A01.equals(eBVar.A01) || !dZ.A09(this.A00, eBVar.A00) || !dZ.A09(this.A06, eBVar.A06) || !dZ.A09(this.A05, eBVar.A05) || !dZ.A09(this.A08, eBVar.A08)) {
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
        e4 e4Var = this.A08;
        if (e4Var != null) {
            i4 = e4Var.hashCode();
        }
        return i7 + i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        C0367dp dpVar = this.A0A;
        sb.append(dpVar.A02);
        sb.append(":");
        sb.append(dpVar.A00);
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

    public eB(String str, int i, AbstractC0372dv dvVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, e4 e4Var, eA eAVar, Proxy proxy, List<EnumC0364dl> list, List<C0376dz> list2, ProxySelector proxySelector) {
        String str2;
        String str3;
        StringBuilder sb;
        C0368dq dqVar = new C0368dq();
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
        dqVar.A05 = str4;
        if (str != null) {
            String A002 = C0368dq.A00(str, 0, str.length());
            if (A002 != null) {
                dqVar.A04 = A002;
                if (i <= 0 || i > 65535) {
                    sb = new StringBuilder("unexpected port: ");
                    sb.append(i);
                } else {
                    dqVar.A00 = i;
                    this.A0A = dqVar.A03();
                    if (dvVar != null) {
                        this.A09 = dvVar;
                        if (socketFactory != null) {
                            this.A04 = socketFactory;
                            if (eAVar != null) {
                                this.A07 = eAVar;
                                if (list != null) {
                                    this.A03 = Collections.unmodifiableList(new ArrayList(list));
                                    if (list2 != null) {
                                        this.A02 = Collections.unmodifiableList(new ArrayList(list2));
                                        if (proxySelector != null) {
                                            this.A01 = proxySelector;
                                            this.A00 = proxy;
                                            this.A06 = sSLSocketFactory;
                                            this.A05 = hostnameVerifier;
                                            this.A08 = e4Var;
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
