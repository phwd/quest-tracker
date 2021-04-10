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

/* renamed from: X.d5  reason: case insensitive filesystem */
public final class C0337d5 implements Closeable {
    public static final ExecutorService A0I = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryC0353da("OkHttp Http2Connection", true));
    public long A00 = 0;
    public int A01;
    public int A02;
    public long A03;
    public C0330cx A04 = new C0330cx();
    public boolean A05 = false;
    public boolean A06;
    public final Socket A07;
    public final AbstractC0338d6 A08;
    public final AbstractC0331cy A09;
    public final String A0A;
    public final Map<Integer, C0335d3> A0B = new LinkedHashMap();
    public final Set<Integer> A0C = new LinkedHashSet();
    public final ExecutorService A0D;
    public final C0114Kk A0E;
    public final C0334d2 A0F;
    public final C0330cx A0G = new C0330cx();
    public final boolean A0H;

    public final synchronized int A00() {
        int i;
        C0330cx cxVar = this.A0G;
        i = Integer.MAX_VALUE;
        if ((cxVar.A00 & 16) != 0) {
            i = cxVar.A01[4];
        }
        return i;
    }

    public final synchronized C0335d3 A01(int i) {
        return this.A0B.get(Integer.valueOf(i));
    }

    public final synchronized C0335d3 A02(int i) {
        C0335d3 remove;
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
    public final void A04(int r9, boolean r10, X.AnonymousClass98 r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r1 = 0
            r6 = 0
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            X.d2 r0 = r8.A0F
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
            java.util.Map<java.lang.Integer, X.d3> r1 = r8.A0B     // Catch:{ InterruptedException -> 0x004f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x004f }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x004f }
            if (r0 == 0) goto L_0x0047
            r8.wait()     // Catch:{ InterruptedException -> 0x004f }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r12, r2)     // Catch:{ all -> 0x0055 }
            int r4 = (int) r0     // Catch:{ all -> 0x0055 }
            X.d2 r5 = r8.A0F     // Catch:{ all -> 0x0055 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0337d5.A04(int, boolean, X.98, long):void");
    }

    public final void A05(dD dDVar, dD dDVar2) throws IOException {
        IOException e;
        C0335d3[] d3VarArr = null;
        try {
            C0334d2 d2Var = this.A0F;
            synchronized (d2Var) {
                synchronized (this) {
                    if (!this.A06) {
                        this.A06 = true;
                        int i = this.A01;
                        byte[] bArr = dZ.A0E;
                        if (d2Var.A01) {
                            throw new IOException("closed");
                        } else if (dDVar.httpCode != -1) {
                            int length = bArr.length;
                            C0334d2.A00(d2Var, 0, length + 8, (byte) 7, (byte) 0);
                            KJ kj = d2Var.A04;
                            kj.A5t(i);
                            kj.A5t(dDVar.httpCode);
                            if (length > 0) {
                                kj.A5n(bArr);
                            }
                            kj.flush();
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
            Map<Integer, C0335d3> map = this.A0B;
            if (!map.isEmpty()) {
                d3VarArr = (C0335d3[]) map.values().toArray(new C0335d3[map.size()]);
                map.clear();
            }
        }
        if (d3VarArr != null) {
            for (C0335d3 d3Var : d3VarArr) {
                try {
                    d3Var.A05(dDVar2);
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

    public final void A03(int i, dD dDVar) {
        A0I.execute(new C0125Kv(this, new Object[]{this.A0A, Integer.valueOf(i)}, i, dDVar));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        A05(dD.NO_ERROR, dD.CANCEL);
    }

    public C0337d5(C0339d7 d7Var) {
        this.A09 = d7Var.A00;
        boolean z = d7Var.A01;
        this.A0H = z;
        this.A08 = d7Var.A04;
        int i = z ? 1 : 2;
        this.A02 = i;
        if (z) {
            this.A02 = i + 2;
        }
        if (z) {
            this.A04.A01(7, 16777216);
        }
        this.A0A = d7Var.A02;
        this.A0D = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC0353da(String.format(Locale.US, "OkHttp %s Push Observer", this.A0A), true));
        C0330cx cxVar = this.A0G;
        cxVar.A01(7, 65535);
        cxVar.A01(5, Http2.INITIAL_MAX_FRAME_SIZE);
        this.A03 = (long) cxVar.A00();
        this.A07 = d7Var.A03;
        KJ kj = d7Var.A05;
        boolean z2 = this.A0H;
        this.A0F = new C0334d2(kj, z2);
        this.A0E = new C0114Kk(this, new C0336d4(d7Var.A06, z2));
    }
}
