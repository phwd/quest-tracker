package X;

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

/* renamed from: X.0Qs  reason: invalid class name */
public interface AnonymousClass0Qs extends Cloneable {
    public static final List<AnonymousClass0m5> A0M = C05570jz.A05(AnonymousClass0m5.A06, AnonymousClass0m5.A05, AnonymousClass0m5.A04);
    public static final List<AnonymousClass0kh> A0N = C05570jz.A05(AnonymousClass0kh.HTTP_2, AnonymousClass0kh.HTTP_1_1);
    public final AbstractC04660hF A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final ProxySelector A04;
    public final List<AnonymousClass0m5> A05;
    public final List<AbstractC05850lW> A06;
    public final List<AbstractC05850lW> A07;
    public final List<AnonymousClass0kh> A08;
    public final SocketFactory A09;
    public final HostnameVerifier A0A;
    public final SSLSocketFactory A0B;
    public final AnonymousClass0nt A0C;
    public final AnonymousClass0nt A0D;
    public final C06330ma A0E;
    public final AnonymousClass0m7 A0F;
    public final AbstractC06150m3 A0G;
    public final C06110ly A0H;
    public final AbstractC06050ls A0I;
    public final boolean A0J;
    public final boolean A0K;
    public final boolean A0L;

    final default AnonymousClass0Qd A00(C05700ke r3) {
        return new AnonymousClass0Qd(this, r3, false);
    }

    static {
        AbstractC05620k9.A00 = new AnonymousClass0R5();
    }

    default AnonymousClass0Qs() {
        this(new C05810lS());
    }

    default AnonymousClass0Qs(C05810lS r5) {
        boolean z;
        AbstractC04660hF r2;
        this.A0H = r5.A08;
        this.A08 = r5.A01;
        this.A05 = r5.A0D;
        this.A06 = Collections.unmodifiableList(new ArrayList(r5.A0H));
        this.A07 = Collections.unmodifiableList(new ArrayList(r5.A0I));
        this.A04 = r5.A00;
        this.A0G = r5.A07;
        this.A09 = r5.A02;
        Iterator<AnonymousClass0m5> it = this.A05.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                AnonymousClass0m5 next = it.next();
                if (z || next.A01) {
                    z = true;
                }
            }
        }
        SSLSocketFactory sSLSocketFactory = r5.A0E;
        if (sSLSocketFactory != null || !z) {
            this.A0B = sSLSocketFactory;
            r2 = r5.A0G;
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
                        this.A0B = instance2.getSocketFactory();
                        r2 = C04670hG.A00.A03(x509TrustManager);
                    }
                }
                throw new IllegalStateException(AnonymousClass006.A05("Unexpected default trust managers:", Arrays.toString(trustManagers)));
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        this.A00 = r2;
        this.A0A = r5.A03;
        C06330ma r1 = r5.A0F;
        this.A0E = !C05570jz.A09(r1.A01, r2) ? new C06330ma(r1.A00, r2) : r1;
        this.A0D = r5.A05;
        this.A0C = r5.A04;
        this.A0F = r5.A06;
        this.A0I = r5.A09;
        this.A0K = true;
        this.A0J = true;
        this.A0L = true;
        this.A01 = r5.A0A;
        this.A02 = r5.A0B;
        this.A03 = r5.A0C;
    }
}
