package X;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.16J  reason: invalid class name */
public final class AnonymousClass16J extends AbstractC099517e {
    public static AnonymousClass16J A04 = new AnonymousClass16J();
    public AnonymousClass10j A00 = new AnonymousClass10j();
    public final Map<AnonymousClass17K, SSLSession> A01 = new AnonymousClass173(this);
    public volatile int A02 = 32;
    public volatile int A03 = 60;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized javax.net.ssl.SSLSession getSession(byte[] r10) {
        /*
        // Method dump skipped, instructions count: 298
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass16J.getSession(byte[]):javax.net.ssl.SSLSession");
    }

    @Override // javax.net.ssl.SSLSessionContext
    public final Enumeration<byte[]> getIds() {
        return new AnonymousClass176(this, Arrays.asList(this.A01.values().toArray(new SSLSession[0])).iterator());
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
            Map<AnonymousClass17K, SSLSession> map = this.A01;
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
