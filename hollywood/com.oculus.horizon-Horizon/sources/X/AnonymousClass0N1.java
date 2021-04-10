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

/* renamed from: X.0N1  reason: invalid class name */
public interface AnonymousClass0N1 extends Cloneable {
    public static final List<C08490wf> A0O = C08160w5.A05(C08490wf.A06, C08490wf.A05, C08490wf.A04);
    public static final List<EnumC08350wP> A0P = C08160w5.A05(EnumC08350wP.HTTP_2, EnumC08350wP.HTTP_1_1);
    public final int A00;
    public final AbstractC07780vL A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final Proxy A05;
    public final ProxySelector A06;
    public final List<C08490wf> A07;
    public final List<AbstractC08380wS> A08;
    public final List<AbstractC08380wS> A09;
    public final List<EnumC08350wP> A0A;
    public final SocketFactory A0B;
    public final HostnameVerifier A0C;
    public final SSLSocketFactory A0D;
    public final AbstractC08600ws A0E;
    public final AbstractC08600ws A0F;
    public final C08540wk A0G;
    public final C08510wh A0H;
    public final AbstractC08470wd A0I;
    public final C08450wb A0J;
    public final AbstractC08440wa A0K;
    public final boolean A0L;
    public final boolean A0M;
    public final boolean A0N;

    static {
        AbstractC08180w8.A00 = new AnonymousClass0N2();
    }

    final default AnonymousClass0Mz A00(C08330wN r3) {
        return new AnonymousClass0Mz(this, r3, false);
    }

    default AnonymousClass0N1() {
        this(new C08360wQ());
    }

    default AnonymousClass0N1(C08360wQ r5) {
        boolean z;
        AbstractC07780vL r2;
        this.A0J = r5.A09;
        this.A05 = r5.A01;
        this.A0A = r5.A03;
        this.A07 = r5.A0H;
        this.A08 = Collections.unmodifiableList(new ArrayList(r5.A0M));
        this.A09 = Collections.unmodifiableList(new ArrayList(r5.A0N));
        this.A06 = r5.A02;
        this.A0I = r5.A08;
        this.A0B = r5.A04;
        Iterator<C08490wf> it = this.A07.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                C08490wf next = it.next();
                if (z || next.A01) {
                    z = true;
                }
            }
        }
        SSLSocketFactory sSLSocketFactory = r5.A0J;
        if (sSLSocketFactory != null || !z) {
            this.A0D = sSLSocketFactory;
            r2 = r5.A0L;
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
                        r2 = C07790vM.A00.A03(x509TrustManager);
                    }
                }
                throw new IllegalStateException(AnonymousClass006.A05("Unexpected default trust managers:", Arrays.toString(trustManagers)));
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        this.A01 = r2;
        this.A0C = r5.A0I;
        C08540wk r1 = r5.A0K;
        this.A0G = !C08160w5.A09(r1.A01, r2) ? new C08540wk(r1.A00, r2) : r1;
        this.A0F = r5.A06;
        this.A0E = r5.A05;
        this.A0H = r5.A07;
        this.A0K = r5.A0A;
        this.A0M = r5.A0C;
        this.A0L = r5.A0B;
        this.A0N = r5.A0D;
        this.A02 = r5.A0E;
        this.A03 = r5.A0F;
        this.A04 = r5.A0G;
        this.A00 = r5.A00;
    }
}
