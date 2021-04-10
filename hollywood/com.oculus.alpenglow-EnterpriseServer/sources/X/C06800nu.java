package X;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0nu  reason: invalid class name and case insensitive filesystem */
public final class C06800nu {
    public final ProxySelector A00;
    public final List<AnonymousClass0m5> A01;
    public final List<AnonymousClass0kh> A02;
    public final SocketFactory A03;
    public final HostnameVerifier A04;
    public final SSLSocketFactory A05;
    public final AnonymousClass0nt A06;
    public final C06330ma A07;
    public final AbstractC06050ls A08;
    public final C05890la A09;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C06800nu)) {
            return false;
        }
        C06800nu r4 = (C06800nu) obj;
        if (!this.A09.equals(r4.A09) || !this.A08.equals(r4.A08) || !this.A06.equals(r4.A06) || !this.A02.equals(r4.A02) || !this.A01.equals(r4.A01) || !this.A00.equals(r4.A00) || !C05570jz.A09(this.A05, r4.A05) || !C05570jz.A09(this.A04, r4.A04) || !C05570jz.A09(this.A07, r4.A07)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3 = 0;
        int hashCode = (((((((((((((527 + this.A09.hashCode()) * 31) + this.A08.hashCode()) * 31) + this.A06.hashCode()) * 31) + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + 0) * 31;
        SSLSocketFactory sSLSocketFactory = this.A05;
        if (sSLSocketFactory != null) {
            i = sSLSocketFactory.hashCode();
        } else {
            i = 0;
        }
        int i4 = (hashCode + i) * 31;
        HostnameVerifier hostnameVerifier = this.A04;
        if (hostnameVerifier != null) {
            i2 = hostnameVerifier.hashCode();
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        C06330ma r0 = this.A07;
        if (r0 != null) {
            i3 = r0.hashCode();
        }
        return i5 + i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        C05890la r1 = this.A09;
        sb.append(r1.A02);
        sb.append(":");
        sb.append(r1.A00);
        sb.append(", proxySelector=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;ILX/0ls;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;LX/0ma;LX/0nt;Ljava/net/Proxy;Ljava/util/List<LX/0kh;>;Ljava/util/List<LX/0m5;>;Ljava/net/ProxySelector;)V */
    public C06800nu(String str, int i, AbstractC06050ls r7, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C06330ma r11, AnonymousClass0nt r12, List list, List list2, ProxySelector proxySelector) {
        String str2;
        String str3;
        StringBuilder sb;
        C06000lm r1 = new C06000lm();
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
            String A002 = C06000lm.A00(str, 0, str.length());
            if (A002 != null) {
                r1.A04 = A002;
                if (i <= 0 || i > 65535) {
                    sb = new StringBuilder("unexpected port: ");
                    sb.append(i);
                } else {
                    r1.A00 = i;
                    this.A09 = r1.A03();
                    if (r7 != null) {
                        this.A08 = r7;
                        if (socketFactory != null) {
                            this.A03 = socketFactory;
                            if (r12 != null) {
                                this.A06 = r12;
                                if (list != null) {
                                    this.A02 = Collections.unmodifiableList(new ArrayList(list));
                                    if (list2 != null) {
                                        this.A01 = Collections.unmodifiableList(new ArrayList(list2));
                                        if (proxySelector != null) {
                                            this.A00 = proxySelector;
                                            this.A05 = sSLSocketFactory;
                                            this.A04 = hostnameVerifier;
                                            this.A07 = r11;
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
