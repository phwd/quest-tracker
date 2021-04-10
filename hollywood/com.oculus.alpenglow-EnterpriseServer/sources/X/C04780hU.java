package X;

import com.squareup.okhttp.internal.framed.Http2;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.0hU  reason: invalid class name and case insensitive filesystem */
public final class C04780hU implements Closeable {
    public static final ExecutorService A0I = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC05580k0("OkHttp Http2Connection", true));
    public long A00 = 0;
    public int A01;
    public int A02;
    public long A03;
    public AnonymousClass0hM A04 = new AnonymousClass0hM();
    public boolean A05 = false;
    public boolean A06;
    public final Socket A07;
    public final AbstractC04790hV A08;
    public final AbstractC04720hN A09;
    public final String A0A;
    public final Map<Integer, C04760hS> A0B = new LinkedHashMap();
    public final Set<Integer> A0C = new LinkedHashSet();
    public final ExecutorService A0D;
    public final AnonymousClass0Ox A0E;
    public final C04750hR A0F;
    public final AnonymousClass0hM A0G = new AnonymousClass0hM();
    public final boolean A0H;

    public final synchronized int A00() {
        int i;
        AnonymousClass0hM r2 = this.A0G;
        i = Integer.MAX_VALUE;
        if ((r2.A00 & 16) != 0) {
            i = r2.A01[4];
        }
        return i;
    }

    public final synchronized C04760hS A01(int i) {
        return this.A0B.get(Integer.valueOf(i));
    }

    public final synchronized C04760hS A02(int i) {
        C04760hS remove;
        remove = this.A0B.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5 = r8.A0F;
        r4 = java.lang.Math.min((int) java.lang.Math.min(r12, r2), r5.A00);
        r0 = (long) r4;
        r8.A03 = r2 - r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(int r9, boolean r10, X.AnonymousClass0HR r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r1 = 0
            r6 = 0
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            X.0hR r0 = r8.A0F
            r0.A04(r10, r9, r11, r1)
        L_0x000c:
            return
        L_0x000d:
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x000c
            monitor-enter(r8)
        L_0x0012:
            long r2 = r8.A03     // Catch:{ InterruptedException -> 0x004f }
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x0028
            java.util.Map<java.lang.Integer, X.0hS> r1 = r8.A0B     // Catch:{ InterruptedException -> 0x004f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x004f }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x004f }
            if (r0 == 0) goto L_0x0047
            r8.wait()     // Catch:{ InterruptedException -> 0x004f }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r12, r2)     // Catch:{ all -> 0x0055 }
            int r4 = (int) r0     // Catch:{ all -> 0x0055 }
            X.0hR r5 = r8.A0F     // Catch:{ all -> 0x0055 }
            int r0 = r5.A00     // Catch:{ all -> 0x0055 }
            int r4 = java.lang.Math.min(r4, r0)     // Catch:{ all -> 0x0055 }
            long r0 = (long) r4     // Catch:{ all -> 0x0055 }
            long r2 = r2 - r0
            r8.A03 = r2     // Catch:{ all -> 0x0055 }
            monitor-exit(r8)     // Catch:{ all -> 0x0055 }
            long r12 = r12 - r0
            if (r10 == 0) goto L_0x0042
            int r1 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            r0 = 1
            if (r1 == 0) goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            r5.A04(r0, r9, r11, r4)
            goto L_0x000d
        L_0x0047:
            java.lang.String r1 = "stream closed"
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x004f:
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x0055:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04780hU.A04(int, boolean, X.0HR, long):void");
    }

    public final void A05(EnumC04880hs r10, EnumC04880hs r11) throws IOException {
        IOException e;
        C04760hS[] r3 = null;
        try {
            C04750hR r4 = this.A0F;
            synchronized (r4) {
                synchronized (this) {
                    if (!this.A06) {
                        this.A06 = true;
                        int i = this.A01;
                        byte[] bArr = C05570jz.A05;
                        if (r4.A01) {
                            throw new IOException("closed");
                        } else if (r10.httpCode != -1) {
                            int length = bArr.length;
                            C04750hR.A00(r4, 0, length + 8, (byte) 7, (byte) 0);
                            AnonymousClass0Oe r1 = r4.A04;
                            r1.A97(i);
                            r1.A97(r10.httpCode);
                            if (length > 0) {
                                r1.A8w(bArr);
                            }
                            r1.flush();
                        } else {
                            throw new IllegalArgumentException(String.format(Locale.US, "errorCode.httpCode == -1", new Object[0]));
                        }
                    }
                }
            }
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            Map<Integer, C04760hS> map = this.A0B;
            if (!map.isEmpty()) {
                r3 = (C04760hS[]) map.values().toArray(new C04760hS[map.size()]);
                map.clear();
            }
        }
        if (r3 != null) {
            for (C04760hS r0 : r3) {
                try {
                    r0.A05(r11);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        try {
            this.A0F.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.A07.close();
            if (e != null) {
                throw e;
            }
        } catch (IOException e5) {
            throw e5;
        }
    }

    public final void A03(int i, EnumC04880hs r5) {
        A0I.execute(new AnonymousClass0PG(this, new Object[]{this.A0A, Integer.valueOf(i)}, i, r5));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        A05(EnumC04880hs.NO_ERROR, EnumC04880hs.CANCEL);
    }

    public C04780hU(C04800hW r13) {
        this.A09 = r13.A00;
        boolean z = r13.A01;
        this.A0H = z;
        this.A08 = r13.A04;
        int i = z ? 1 : 2;
        this.A02 = i;
        if (z) {
            this.A02 = i + 2;
        }
        if (z) {
            this.A04.A01(7, 16777216);
        }
        this.A0A = r13.A02;
        this.A0D = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC05580k0(String.format(Locale.US, "OkHttp %s Push Observer", this.A0A), true));
        AnonymousClass0hM r2 = this.A0G;
        r2.A01(7, 65535);
        r2.A01(5, Http2.INITIAL_MAX_FRAME_SIZE);
        this.A03 = (long) r2.A00();
        this.A07 = r13.A03;
        AnonymousClass0Oe r1 = r13.A05;
        boolean z2 = this.A0H;
        this.A0F = new C04750hR(r1, z2);
        this.A0E = new AnonymousClass0Ox(this, new C04770hT(r13.A06, z2));
    }
}
