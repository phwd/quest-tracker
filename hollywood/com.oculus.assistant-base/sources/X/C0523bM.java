package X;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.bM  reason: case insensitive filesystem */
public final class C0523bM {
    public final ProxySelector A00;
    public final List A01;
    public final List A02;
    public final SocketFactory A03;
    public final HostnameVerifier A04;
    public final SSLSocketFactory A05;
    public final AbstractC0524bN A06;
    public final C0530bT A07;
    public final AbstractC0539bc A08;
    public final C0544bh A09;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0523bM)) {
            return false;
        }
        C0523bM bMVar = (C0523bM) obj;
        if (!this.A09.equals(bMVar.A09) || !this.A08.equals(bMVar.A08) || !this.A06.equals(bMVar.A06) || !this.A02.equals(bMVar.A02) || !this.A01.equals(bMVar.A01) || !this.A00.equals(bMVar.A00) || !C0561by.A09(this.A05, bMVar.A05) || !C0561by.A09(this.A04, bMVar.A04) || !C0561by.A09(this.A07, bMVar.A07)) {
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
        C0530bT bTVar = this.A07;
        if (bTVar != null) {
            i3 = bTVar.hashCode();
        }
        return i5 + i3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        C0544bh bhVar = this.A09;
        sb.append(bhVar.A02);
        sb.append(":");
        sb.append(bhVar.A00);
        sb.append(", proxySelector=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }

    public C0523bM(String str, int i, AbstractC0539bc bcVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C0530bT bTVar, AbstractC0524bN bNVar, List list, List list2, ProxySelector proxySelector) {
        String str2;
        C0543bg bgVar = new C0543bg();
        if (sSLSocketFactory != null) {
            str2 = "https";
        } else {
            str2 = "http";
        }
        bgVar.A05(str2);
        if (str != null) {
            String A002 = C0543bg.A00(str, 0, str.length());
            if (A002 != null) {
                bgVar.A04 = A002;
                if (i <= 0 || i > 65535) {
                    throw new IllegalArgumentException(AnonymousClass08.A00("unexpected port: ", i));
                }
                bgVar.A00 = i;
                this.A09 = bgVar.A03();
                if (bcVar != null) {
                    this.A08 = bcVar;
                    if (socketFactory != null) {
                        this.A03 = socketFactory;
                        if (bNVar != null) {
                            this.A06 = bNVar;
                            if (list != null) {
                                this.A02 = Collections.unmodifiableList(new ArrayList(list));
                                if (list2 != null) {
                                    this.A01 = Collections.unmodifiableList(new ArrayList(list2));
                                    if (proxySelector != null) {
                                        this.A00 = proxySelector;
                                        this.A05 = sSLSocketFactory;
                                        this.A04 = hostnameVerifier;
                                        this.A07 = bTVar;
                                        return;
                                    }
                                    throw new NullPointerException("proxySelector == null");
                                }
                                throw new NullPointerException("connectionSpecs == null");
                            }
                            throw new NullPointerException("protocols == null");
                        }
                        throw new NullPointerException("proxyAuthenticator == null");
                    }
                    throw new NullPointerException("socketFactory == null");
                }
                throw new NullPointerException("dns == null");
            }
            throw new IllegalArgumentException(AnonymousClass08.A04("unexpected host: ", str));
        }
        throw new NullPointerException("host == null");
    }
}
