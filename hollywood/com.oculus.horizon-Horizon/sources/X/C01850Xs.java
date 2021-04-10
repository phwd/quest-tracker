package X;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.0Xs  reason: invalid class name and case insensitive filesystem */
public final class C01850Xs extends AnonymousClass1ux {
    public static C01850Xs A04 = new C01850Xs();
    public C06200mh A00 = new C06200mh();
    public final Map<C01840Xr, SSLSession> A01 = new C01820Xp(this);
    public volatile int A02 = 32;
    public volatile int A03 = 60;

    @Override // X.AnonymousClass1ux
    public final synchronized void A00(AnonymousClass1ur r10) {
        C01840Xr r5 = new C01840Xr(r10.getId());
        Map<C01840Xr, SSLSession> map = this.A01;
        AnonymousClass1ur r3 = (AnonymousClass1ur) map.get(r5);
        if (r3 == null) {
            try {
                r3 = new AnonymousClass1ur(this, r10.getPeerHost(), r10.getPeerPort(), r10.getCipherSuite());
                map.put(r5, r3);
            } catch (AnonymousClass1v5 e) {
                System.out.println(AnonymousClass006.A05("Encountered Exception : ", e.toString()));
            }
        }
        r3.A03().put(r10.A01(), r10.getPeerCertificates());
        Iterator<C10711us> it = r10.A02().iterator();
        while (it.hasNext()) {
            r3.A02().add(it.next());
        }
        C06200mh r1 = this.A00;
        if (r1 != null) {
            r1.A01(r5.A01, new C01810Xo(r3.getPeerHost(), r3.getPeerPort(), r3.getCipherSuite(), r3.A02(), r3.A03()));
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized javax.net.ssl.SSLSession getSession(byte[] r11) {
        /*
        // Method dump skipped, instructions count: 271
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01850Xs.getSession(byte[]):javax.net.ssl.SSLSession");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public final Enumeration<byte[]> getIds() {
        return new C01830Xq(this, Arrays.asList(this.A01.values().toArray(new SSLSession[0])).iterator());
    }

    @Override // javax.net.ssl.SSLSessionContext
    public final void setSessionCacheSize(int i) throws IllegalArgumentException {
        if (i >= 0) {
            this.A02 = i;
            return;
        }
        throw new IllegalArgumentException("Cache size < 0");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public final void setSessionTimeout(int i) throws IllegalArgumentException {
        if (i >= 0) {
            this.A03 = i;
            Map<C01840Xr, SSLSession> map = this.A01;
            synchronized (map) {
                Iterator<SSLSession> it = map.values().iterator();
                while (it.hasNext()) {
                    if (!it.next().isValid()) {
                        it.remove();
                    }
                }
            }
            return;
        }
        throw new IllegalArgumentException("Timeout < 0");
    }

    public final int getSessionCacheSize() {
        return this.A02;
    }

    public final int getSessionTimeout() {
        return this.A03;
    }
}
