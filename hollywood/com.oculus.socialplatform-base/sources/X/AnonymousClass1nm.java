package X;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.1nm  reason: invalid class name */
public final class AnonymousClass1nm extends AnonymousClass1oB {
    public static AnonymousClass1nm A04 = new AnonymousClass1nm();
    public AnonymousClass1RM A00 = new AnonymousClass1RM();
    public final Map<C10541nl, SSLSession> A01 = new AnonymousClass1nk(this);
    public volatile int A02 = 32;
    public volatile int A03 = 60;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized javax.net.ssl.SSLSession getSession(byte[] r10) {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1nm.getSession(byte[]):javax.net.ssl.SSLSession");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public final Enumeration<byte[]> getIds() {
        return new AnonymousClass1nq(this, Arrays.asList(this.A01.values().toArray(new SSLSession[0])).iterator());
    }

    public final int getSessionCacheSize() {
        return this.A02;
    }

    public final int getSessionTimeout() {
        return this.A03;
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
            Map<C10541nl, SSLSession> map = this.A01;
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
}
