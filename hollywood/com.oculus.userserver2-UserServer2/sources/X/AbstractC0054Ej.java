package X;

import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.Ej  reason: case insensitive filesystem */
public interface AbstractC0054Ej extends Cloneable {
    public static final List<C0178Xd> A0O = XD.A05(C0178Xd.A06, C0178Xd.A05, C0178Xd.A04);
    public static final List<XP> A0P = XD.A05(XP.HTTP_2, XP.HTTP_1_1);
    public final int A00;
    public final WU A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final Proxy A05;
    public final ProxySelector A06;
    public final List<C0178Xd> A07;
    public final List<XS> A08;
    public final List<XS> A09;
    public final List<XP> A0A;
    public final SocketFactory A0B;
    public final HostnameVerifier A0C;
    public final SSLSocketFactory A0D;
    public final AbstractC0189Xo A0E;
    public final AbstractC0189Xo A0F;
    public final C0183Xi A0G;
    public final C0180Xf A0H;
    public final AbstractC0176Xb A0I;
    public final C0175Xa A0J;
    public final XZ A0K;
    public final boolean A0L;
    public final boolean A0M;
    public final boolean A0N;

    static {
        XG.A00 = new C0055Ek();
    }

    default AbstractC0054Ej() {
        this(new XQ());
    }

    default AbstractC0054Ej(XQ xq) {
        boolean z;
        WU wu;
        this.A0J = xq.A09;
        this.A05 = xq.A01;
        this.A0A = xq.A03;
        this.A07 = xq.A0H;
        this.A08 = Collections.unmodifiableList(new ArrayList(xq.A0M));
        this.A09 = Collections.unmodifiableList(new ArrayList(xq.A0N));
        this.A06 = xq.A02;
        this.A0I = xq.A08;
        this.A0B = xq.A04;
        Iterator<C0178Xd> it = this.A07.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                C0178Xd next = it.next();
                if (z || next.A01) {
                    z = true;
                }
            }
        }
        SSLSocketFactory sSLSocketFactory = xq.A0J;
        if (sSLSocketFactory != null || !z) {
            this.A0D = sSLSocketFactory;
            wu = xq.A0L;
        } else {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length == 1) {
                    TrustManager trustManager = trustManagers[0];
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        SSLContext instance2 = SSLContext.getInstance("TLS");
                        instance2.init(null, new TrustManager[]{x509TrustManager}, null);
                        this.A0D = instance2.getSocketFactory();
                        wu = WV.A01.A01(x509TrustManager);
                    }
                }
                throw new IllegalStateException(AnonymousClass06.A03("Unexpected default trust managers:", Arrays.toString(trustManagers)));
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        this.A01 = wu;
        this.A0C = xq.A0I;
        C0183Xi xi = xq.A0K;
        this.A0G = !XD.A09(xi.A01, wu) ? new C0183Xi(xi.A00, wu) : xi;
        this.A0F = xq.A06;
        this.A0E = xq.A05;
        this.A0H = xq.A07;
        this.A0K = xq.A0A;
        this.A0M = xq.A0C;
        this.A0L = xq.A0B;
        this.A0N = xq.A0D;
        this.A02 = xq.A0E;
        this.A03 = xq.A0F;
        this.A04 = xq.A0G;
        this.A00 = xq.A00;
    }
}
