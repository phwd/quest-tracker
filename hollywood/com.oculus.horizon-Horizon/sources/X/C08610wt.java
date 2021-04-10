package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0wt  reason: invalid class name and case insensitive filesystem */
public final class C08610wt {
    public final Proxy A00;
    public final ProxySelector A01;
    public final List<C08490wf> A02;
    public final List<EnumC08350wP> A03;
    public final SocketFactory A04;
    public final HostnameVerifier A05;
    public final SSLSocketFactory A06;
    public final AbstractC08600ws A07;
    public final C08540wk A08;
    public final AbstractC08440wa A09;
    public final C08400wU A0A;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C08610wt)) {
            return false;
        }
        C08610wt r4 = (C08610wt) obj;
        if (!this.A0A.equals(r4.A0A) || !this.A09.equals(r4.A09) || !this.A07.equals(r4.A07) || !this.A03.equals(r4.A03) || !this.A02.equals(r4.A02) || !this.A01.equals(r4.A01) || !C08160w5.A09(this.A00, r4.A00) || !C08160w5.A09(this.A06, r4.A06) || !C08160w5.A09(this.A05, r4.A05) || !C08160w5.A09(this.A08, r4.A08)) {
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
        C08540wk r0 = this.A08;
        if (r0 != null) {
            i4 = r0.hashCode();
        }
        return i7 + i4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        C08400wU r1 = this.A0A;
        sb.append(r1.A02);
        sb.append(":");
        sb.append(r1.A00);
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

    public C08610wt(String str, int i, AbstractC08440wa r7, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C08540wk r11, AbstractC08600ws r12, Proxy proxy, List<EnumC08350wP> list, List<C08490wf> list2, ProxySelector proxySelector) {
        String str2;
        String str3;
        StringBuilder sb;
        C08410wV r1 = new C08410wV();
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
        r1.A05 = str4;
        if (str != null) {
            String A002 = C08410wV.A00(str, 0, str.length());
            if (A002 != null) {
                r1.A04 = A002;
                if (i <= 0 || i > 65535) {
                    sb = new StringBuilder("unexpected port: ");
                    sb.append(i);
                } else {
                    r1.A00 = i;
                    this.A0A = r1.A03();
                    if (r7 != null) {
                        this.A09 = r7;
                        if (socketFactory != null) {
                            this.A04 = socketFactory;
                            if (r12 != null) {
                                this.A07 = r12;
                                if (list != null) {
                                    this.A03 = Collections.unmodifiableList(new ArrayList(list));
                                    if (list2 != null) {
                                        this.A02 = Collections.unmodifiableList(new ArrayList(list2));
                                        if (proxySelector != null) {
                                            this.A01 = proxySelector;
                                            this.A00 = proxy;
                                            this.A06 = sSLSocketFactory;
                                            this.A05 = hostnameVerifier;
                                            this.A08 = r11;
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
