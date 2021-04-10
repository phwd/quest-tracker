package X;

import com.squareup.okhttp.internal.framed.FramedConnection;
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

/* renamed from: X.Wj  reason: case insensitive filesystem */
public final class C0158Wj implements Closeable {
    public static final ExecutorService A0I = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), new XE("OkHttp Http2Connection"));
    public long A00 = 0;
    public int A01;
    public int A02;
    public long A03;
    public Wb A04 = new Wb();
    public boolean A05 = false;
    public boolean A06;
    public final Socket A07;
    public final String A08;
    public final Map<Integer, C0156Wh> A09 = new LinkedHashMap();
    public final Set<Integer> A0A = new LinkedHashSet();
    public final ExecutorService A0B;
    public final AbstractC0159Wk A0C;
    public final EA A0D;
    public final C0155Wg A0E;
    public final Wc A0F;
    public final Wb A0G = new Wb();
    public final boolean A0H;

    public final synchronized int A00() {
        int i;
        Wb wb = this.A0G;
        i = Integer.MAX_VALUE;
        if ((wb.A00 & 16) != 0) {
            i = wb.A01[4];
        }
        return i;
    }

    public final synchronized C0156Wh A01(int i) {
        return this.A09.get(Integer.valueOf(i));
    }

    public final synchronized C0156Wh A02(int i) {
        C0156Wh remove;
        remove = this.A09.remove(Integer.valueOf(i));
        notifyAll();
        return remove;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5 = r8.A0E;
        r4 = java.lang.Math.min((int) java.lang.Math.min(r12, r2), r5.A00);
        r0 = (long) r4;
        r8.A03 = r2 - r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(int r9, boolean r10, X.AnonymousClass8k r11, long r12) throws java.io.IOException {
        /*
            r8 = this;
            r1 = 0
            r6 = 0
            int r0 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x000d
            X.Wg r0 = r8.A0E
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
            java.util.Map<java.lang.Integer, X.Wh> r1 = r8.A09     // Catch:{ InterruptedException -> 0x004f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r9)     // Catch:{ InterruptedException -> 0x004f }
            boolean r0 = r1.containsKey(r0)     // Catch:{ InterruptedException -> 0x004f }
            if (r0 == 0) goto L_0x0047
            r8.wait()     // Catch:{ InterruptedException -> 0x004f }
            goto L_0x0012
        L_0x0028:
            long r0 = java.lang.Math.min(r12, r2)     // Catch:{ all -> 0x0055 }
            int r4 = (int) r0     // Catch:{ all -> 0x0055 }
            X.Wg r5 = r8.A0E     // Catch:{ all -> 0x0055 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0158Wj.A04(int, boolean, X.8k, long):void");
    }

    public final void A05(EnumC0166Wr wr, EnumC0166Wr wr2) throws IOException {
        IOException e;
        C0156Wh[] whArr = null;
        try {
            C0155Wg wg = this.A0E;
            synchronized (wg) {
                synchronized (this) {
                    if (!this.A06) {
                        this.A06 = true;
                        int i = this.A01;
                        byte[] bArr = XD.A0E;
                        if (wg.A01) {
                            throw new IOException("closed");
                        } else if (wr.httpCode != -1) {
                            int length = bArr.length;
                            C0155Wg.A00(wg, 0, length + 8, (byte) 7, (byte) 0);
                            Du du = wg.A04;
                            du.A41(i);
                            du.A41(wr.httpCode);
                            if (length > 0) {
                                du.A3v(bArr);
                            }
                            du.flush();
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
            Map<Integer, C0156Wh> map = this.A09;
            if (!map.isEmpty()) {
                whArr = (C0156Wh[]) map.values().toArray(new C0156Wh[map.size()]);
                map.clear();
            }
        }
        if (whArr != null) {
            for (C0156Wh wh : whArr) {
                try {
                    wh.A05(wr2);
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

    public final void A03(int i, EnumC0166Wr wr) {
        A0I.execute(new EL(this, new Object[]{this.A08, Integer.valueOf(i)}, i, wr));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        A05(EnumC0166Wr.NO_ERROR, EnumC0166Wr.CANCEL);
    }

    public C0158Wj(C0160Wl wl) {
        this.A0F = wl.A00;
        boolean z = wl.A01;
        this.A0H = z;
        this.A0C = wl.A04;
        int i = z ? 1 : 2;
        this.A02 = i;
        if (z) {
            this.A02 = i + 2;
        }
        if (z) {
            this.A04.A01(7, FramedConnection.OKHTTP_CLIENT_WINDOW_SIZE);
        }
        this.A08 = wl.A02;
        this.A0B = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new XE(String.format(Locale.US, "OkHttp %s Push Observer", this.A08)));
        Wb wb = this.A0G;
        wb.A01(7, 65535);
        wb.A01(5, Http2.INITIAL_MAX_FRAME_SIZE);
        this.A03 = (long) wb.A00();
        this.A07 = wl.A03;
        Du du = wl.A05;
        boolean z2 = this.A0H;
        this.A0E = new C0155Wg(du, z2);
        this.A0D = new EA(this, new C0157Wi(wl.A06, z2));
    }
}
