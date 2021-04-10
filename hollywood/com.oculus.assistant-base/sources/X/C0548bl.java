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

/* renamed from: X.bl  reason: case insensitive filesystem */
public final class C0548bl implements Cloneable {
    public static final List A0M = C0561by.A05(C0535bY.A06, C0535bY.A05, C0535bY.A04);
    public static final List A0N = C0561by.A05(EnumC0549bm.HTTP_2, EnumC0549bm.HTTP_1_1);
    public final AbstractC0596cf A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final ProxySelector A04;
    public final List A05;
    public final List A06;
    public final List A07;
    public final List A08;
    public final SocketFactory A09;
    public final HostnameVerifier A0A;
    public final SSLSocketFactory A0B;
    public final AbstractC0524bN A0C;
    public final AbstractC0524bN A0D;
    public final C0530bT A0E;
    public final C0533bW A0F;
    public final AbstractC0537ba A0G;
    public final C0538bb A0H;
    public final AbstractC0539bc A0I;
    public final boolean A0J;
    public final boolean A0K;
    public final boolean A0L;

    static {
        AbstractC0558bv.A00 = new C1159tz();
    }

    public C0548bl() {
        this(new C0547bk());
    }

    public C0548bl(C0547bk bkVar) {
        boolean z;
        AbstractC0596cf A012;
        this.A0H = bkVar.A09;
        this.A08 = bkVar.A02;
        this.A05 = bkVar.A01;
        this.A06 = Collections.unmodifiableList(new ArrayList(bkVar.A0G));
        this.A07 = Collections.unmodifiableList(new ArrayList(bkVar.A0F));
        this.A04 = bkVar.A00;
        this.A0G = bkVar.A08;
        this.A09 = bkVar.A03;
        Iterator it = this.A05.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                C0535bY bYVar = (C0535bY) it.next();
                if (z || bYVar.A01) {
                    z = true;
                }
            }
        }
        if (!z) {
            A012 = null;
            this.A0B = null;
        } else {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length == 1) {
                    TrustManager trustManager = trustManagers[0];
                    if (trustManager instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManager;
                        try {
                            SSLContext instance2 = SSLContext.getInstance("TLS");
                            instance2.init(null, new TrustManager[]{x509TrustManager}, null);
                            this.A0B = instance2.getSocketFactory();
                            A012 = C0595ce.A01.A01(x509TrustManager);
                        } catch (GeneralSecurityException unused) {
                            throw new AssertionError();
                        }
                    }
                }
                throw new IllegalStateException(AnonymousClass08.A04("Unexpected default trust managers:", Arrays.toString(trustManagers)));
            } catch (GeneralSecurityException unused2) {
                throw new AssertionError();
            }
        }
        this.A00 = A012;
        this.A0A = bkVar.A04;
        C0530bT bTVar = bkVar.A0E;
        this.A0E = !C0561by.A09(bTVar.A01, A012) ? new C0530bT(bTVar.A00, A012) : bTVar;
        this.A0D = bkVar.A06;
        this.A0C = bkVar.A05;
        this.A0F = bkVar.A07;
        this.A0I = bkVar.A0A;
        this.A0K = true;
        this.A0J = true;
        this.A0L = true;
        this.A01 = bkVar.A0B;
        this.A02 = bkVar.A0C;
        this.A03 = bkVar.A0D;
    }
}
