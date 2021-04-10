package X;

import com.squareup.okhttp.internal.framed.FramedStream;
import java.io.IOException;

/* renamed from: X.0Ov  reason: invalid class name and case insensitive filesystem */
public final class C01950Ov implements AnonymousClass0h1 {
    public boolean A00;
    public boolean A01;
    public final AnonymousClass0HR A02 = new AnonymousClass0HR();
    public final /* synthetic */ C04760hS A03;

    public C01950Ov(C04760hS r2) {
        this.A03 = r2;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r10 != r9.A00) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A00(boolean r13) throws java.io.IOException {
        /*
            r12 = this;
            X.0hS r5 = r12.A03
            monitor-enter(r5)
            X.0HS r4 = r5.A0A     // Catch:{ all -> 0x005c }
            r4.enter()     // Catch:{ all -> 0x005c }
        L_0x0008:
            long r1 = r5.A01     // Catch:{ all -> 0x0057 }
            r6 = 0
            int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r0 > 0) goto L_0x0026
            boolean r0 = r12.A01     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            boolean r0 = r12.A00     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            X.0hs r0 = r5.A03     // Catch:{ all -> 0x0057 }
            if (r0 != 0) goto L_0x0026
            r5.wait()     // Catch:{ InterruptedException -> 0x0020 }
            goto L_0x0008
        L_0x0020:
            java.io.InterruptedIOException r0 = new java.io.InterruptedIOException
            r0.<init>()
            throw r0
        L_0x0026:
            r4.A00()
            r5.A03()
            long r0 = r5.A01
            X.0HR r9 = r12.A02
            long r2 = r9.A00
            long r10 = java.lang.Math.min(r0, r2)
            long r0 = r0 - r10
            r5.A01 = r0
            monitor-exit(r5)
            r4.enter()
            X.0hU r6 = r5.A07     // Catch:{ all -> 0x0052 }
            int r7 = r5.A06     // Catch:{ all -> 0x0052 }
            if (r13 == 0) goto L_0x004a
            long r0 = r9.A00     // Catch:{ all -> 0x0052 }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r8 = 1
            if (r2 == 0) goto L_0x004b
        L_0x004a:
            r8 = 0
        L_0x004b:
            r6.A04(r7, r8, r9, r10)     // Catch:{ all -> 0x0052 }
            r4.A00()
            return
        L_0x0052:
            r0 = move-exception
            r4.A00()
            throw r0
        L_0x0057:
            r0 = move-exception
            r4.A00()
            throw r0
        L_0x005c:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01950Ov.A00(boolean):void");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
    public final void close() throws IOException {
        boolean z;
        C04760hS r3 = this.A03;
        synchronized (r3) {
            z = this.A00;
        }
        if (!z) {
            if (!r3.A05.A01) {
                AnonymousClass0HR r6 = this.A02;
                if (r6.A00 <= 0) {
                    r3.A07.A04(r3.A06, true, null, 0);
                } else {
                    while (r6.A00 > 0) {
                        A00(true);
                    }
                }
            }
            synchronized (r3) {
                this.A00 = true;
            }
            r3.A07.A0F.A01();
            r3.A02();
        }
    }

    @Override // X.AnonymousClass0h1, java.io.Flushable
    public final void flush() throws IOException {
        C04760hS r5 = this.A03;
        synchronized (r5) {
            r5.A03();
        }
        while (this.A02.A00 > 0) {
            A00(false);
            r5.A07.A0F.A01();
        }
    }

    @Override // X.AnonymousClass0h1
    public final C04540gz timeout() {
        return this.A03.A0A;
    }

    @Override // X.AnonymousClass0h1
    public final void write(AnonymousClass0HR r7, long j) throws IOException {
        AnonymousClass0HR r5 = this.A02;
        r5.write(r7, j);
        while (r5.A00 >= FramedStream.FramedDataSink.EMIT_BUFFER_SIZE) {
            A00(false);
        }
    }
}
