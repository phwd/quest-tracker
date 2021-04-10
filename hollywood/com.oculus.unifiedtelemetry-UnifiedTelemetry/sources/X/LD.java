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

public interface LD extends Cloneable {
    public static final List<C0376dz> A0O = dZ.A05(C0376dz.A06, C0376dz.A05, C0376dz.A04);
    public static final List<EnumC0364dl> A0P = dZ.A05(EnumC0364dl.HTTP_2, EnumC0364dl.HTTP_1_1);
    public final int A00;
    public final cq A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final Proxy A05;
    public final ProxySelector A06;
    public final List<C0376dz> A07;
    public final List<Cdo> A08;
    public final List<Cdo> A09;
    public final List<EnumC0364dl> A0A;
    public final SocketFactory A0B;
    public final HostnameVerifier A0C;
    public final SSLSocketFactory A0D;
    public final eA A0E;
    public final eA A0F;
    public final e4 A0G;
    public final e1 A0H;
    public final AbstractC0374dx A0I;
    public final C0373dw A0J;
    public final AbstractC0372dv A0K;
    public final boolean A0L;
    public final boolean A0M;
    public final boolean A0N;

    final default LB A00(C0362dj djVar) {
        return new LB(this, djVar, false);
    }

    static {
        AbstractC0355dc.A00 = new LE();
    }

    default LD() {
        this(new C0365dm());
    }

    default LD(C0365dm dmVar) {
        boolean z;
        cq cqVar;
        this.A0J = dmVar.A09;
        this.A05 = dmVar.A01;
        this.A0A = dmVar.A03;
        this.A07 = dmVar.A0H;
        this.A08 = Collections.unmodifiableList(new ArrayList(dmVar.A0M));
        this.A09 = Collections.unmodifiableList(new ArrayList(dmVar.A0N));
        this.A06 = dmVar.A02;
        this.A0I = dmVar.A08;
        this.A0B = dmVar.A04;
        Iterator<C0376dz> it = this.A07.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                C0376dz next = it.next();
                if (z || next.A01) {
                    z = true;
                }
            }
        }
        SSLSocketFactory sSLSocketFactory = dmVar.A0J;
        if (sSLSocketFactory != null || !z) {
            this.A0D = sSLSocketFactory;
            cqVar = dmVar.A0L;
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
                        cqVar = C0324cr.A00.A03(x509TrustManager);
                    }
                }
                throw new IllegalStateException(AnonymousClass06.A04("Unexpected default trust managers:", Arrays.toString(trustManagers)));
            } catch (GeneralSecurityException unused) {
                throw new AssertionError();
            }
        }
        this.A01 = cqVar;
        this.A0C = dmVar.A0I;
        e4 e4Var = dmVar.A0K;
        this.A0G = !dZ.A09(e4Var.A01, cqVar) ? new e4(e4Var.A00, cqVar) : e4Var;
        this.A0F = dmVar.A06;
        this.A0E = dmVar.A05;
        this.A0H = dmVar.A07;
        this.A0K = dmVar.A0A;
        this.A0M = dmVar.A0C;
        this.A0L = dmVar.A0B;
        this.A0N = dmVar.A0D;
        this.A02 = dmVar.A0E;
        this.A03 = dmVar.A0F;
        this.A04 = dmVar.A0G;
        this.A00 = dmVar.A00;
    }
}
