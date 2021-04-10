package X;

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

/* renamed from: X.cQ  reason: case insensitive filesystem */
public final class C0584cQ implements Closeable {
    public static final ExecutorService A0I = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC0560bx("OkHttp Http2Connection", true));
    public long A00 = 0;
    public int A01;
    public int A02;
    public long A03;
    public C0590cY A04 = new C0590cY();
    public boolean A05 = false;
    public boolean A06;
    public final Socket A07;
    public final String A08;
    public final Map A09 = new LinkedHashMap();
    public final Set A0A = new LinkedHashSet();
    public final ExecutorService A0B;
    public final cP A0C;
    public final C1123tL A0D;
    public final C0586cT A0E;
    public final AbstractC0589cX A0F;
    public final C0590cY A0G = new C0590cY();
    public final boolean A0H;

    public final synchronized int A00() {
        int i;
        C0590cY cYVar = this.A0G;
        i = Integer.MAX_VALUE;
        if ((cYVar.A00 & 16) != 0) {
            i = cYVar.A01[4];
        }
        return i;
    }

    public final synchronized cS A01(int i) {
        return (cS) this.A09.get(Integer.valueOf(i));
    }

    public final synchronized cS A02(int i) {
        cS cSVar;
        cSVar = (cS) this.A09.remove(Integer.valueOf(i));
        notifyAll();
        return cSVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5 = r8.A0E;
        r4 = java.lang.Math.min((int) java.lang.Math.min(r12, r2), r5.A00);
        r0 = (long) r4;
        r8.A03 = r2 - r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(int r9, boolean r10, X.AnonymousClass33 r11, long r12) {
        /*
            r8 = this;
            r1 = 0
            r6 = 0
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            X.cT r0 = r8.A0E
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
            java.util.Map r1 = r8.A09     // Catch:{ InterruptedException -> 0x004f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x004f }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x004f }
            if (r0 == 0) goto L_0x0047
            r8.wait()     // Catch:{ InterruptedException -> 0x004f }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r12, r2)     // Catch:{ all -> 0x0055 }
            int r4 = (int) r0     // Catch:{ all -> 0x0055 }
            X.cT r5 = r8.A0E     // Catch:{ all -> 0x0055 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0584cQ.A04(int, boolean, X.33, long):void");
    }

    public final void A05(cI cIVar, cI cIVar2) {
        IOException e;
        cS[] cSVarArr = null;
        try {
            C0586cT cTVar = this.A0E;
            synchronized (cTVar) {
                synchronized (this) {
                    if (!this.A06) {
                        this.A06 = true;
                        int i = this.A01;
                        byte[] bArr = C0561by.A05;
                        if (cTVar.A01) {
                            throw new IOException("closed");
                        } else if (cIVar.httpCode != -1) {
                            int length = bArr.length;
                            C0586cT.A00(cTVar, 0, length + 8, (byte) 7, (byte) 0);
                            t6 t6Var = cTVar.A04;
                            t6Var.A5r(i);
                            t6Var.A5r(cIVar.httpCode);
                            if (length > 0) {
                                t6Var.A5i(bArr);
                            }
                            t6Var.flush();
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
            Map map = this.A09;
            if (!map.isEmpty()) {
                cSVarArr = (cS[]) map.values().toArray(new cS[map.size()]);
                map.clear();
            }
        }
        if (cSVarArr != null) {
            for (cS cSVar : cSVarArr) {
                try {
                    cSVar.A05(cIVar2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        try {
            this.A0E.close();
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

    public final void A03(int i, cI cIVar) {
        A0I.execute(new C1131tW(this, new Object[]{this.A08, Integer.valueOf(i)}, i, cIVar));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        A05(cI.NO_ERROR, cI.CANCEL);
    }

    public C0584cQ(C0583cO cOVar) {
        this.A0F = cOVar.A00;
        boolean z = cOVar.A01;
        this.A0H = z;
        this.A0C = cOVar.A04;
        int i = z ? 1 : 2;
        this.A02 = i;
        if (z) {
            this.A02 = i + 2;
        }
        if (z) {
            this.A04.A01(7, 16777216);
        }
        this.A08 = cOVar.A02;
        this.A0B = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0560bx(String.format(Locale.US, "OkHttp %s Push Observer", this.A08), true));
        C0590cY cYVar = this.A0G;
        cYVar.A01(7, 65535);
        cYVar.A01(5, 16384);
        this.A03 = (long) cYVar.A00();
        this.A07 = cOVar.A03;
        t6 t6Var = cOVar.A05;
        boolean z2 = this.A0H;
        this.A0E = new C0586cT(t6Var, z2);
        this.A0D = new C1123tL(this, new C0585cR(cOVar.A06, z2));
    }
}
