package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.9b  reason: invalid class name and case insensitive filesystem */
public class C00289b extends L0 {
    public long A00 = -1;
    public boolean A01 = true;
    public final C0367dp A02;
    public final /* synthetic */ C0127Kx A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C00289b(C0127Kx kx, C0367dp dpVar) {
        super(kx);
        this.A03 = kx;
        this.A02 = dpVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (X.dZ.A0A(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
     */
    @Override // java.io.Closeable, X.AbstractC0312cb, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.A00
            if (r0 != 0) goto L_0x0019
            boolean r0 = r2.A01
            if (r0 == 0) goto L_0x0016
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            r0 = 100
            boolean r0 = X.dZ.A0A(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
            if (r0 != 0) goto L_0x0016
        L_0x0012:
            r0 = 0
            r2.A00(r0)
        L_0x0016:
            r0 = 1
            r2.A00 = r0
        L_0x0019:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C00289b.close():void");
    }

    @Override // X.AbstractC0312cb
    public final long read(AnonymousClass98 r11, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass06.A03("byteCount < 0: ", j));
        } else if (super.A00) {
            throw new IllegalStateException("closed");
        } else if (!this.A01) {
            return -1;
        } else {
            long j2 = this.A00;
            if (j2 == 0 || j2 == -1) {
                if (j2 != -1) {
                    this.A03.A04.A4c();
                }
                try {
                    C0127Kx kx = this.A03;
                    KC kc = kx.A04;
                    this.A00 = kc.A4W();
                    String trim = kc.A4c().trim();
                    long j3 = this.A00;
                    if (j3 < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("expected chunk size and optional extensions but was \"");
                        sb.append(j3);
                        sb.append(trim);
                        sb.append("\"");
                        throw new ProtocolException(sb.toString());
                    }
                    if (j3 == 0) {
                        this.A01 = false;
                        AbstractC0374dx dxVar = kx.A02.A0I;
                        C0367dp dpVar = this.A02;
                        C0370dt dtVar = new C0370dt();
                        while (true) {
                            String A4c = kc.A4c();
                            if (A4c.length() == 0) {
                                break;
                            }
                            AbstractC0355dc.A00.A06(dtVar, A4c);
                        }
                        dI.A02(dxVar, dpVar, new C0369ds(dtVar));
                        A00(true);
                    }
                    if (!this.A01) {
                        return -1;
                    }
                } catch (NumberFormatException e) {
                    throw new ProtocolException(e.getMessage());
                }
            }
            long read = this.A03.A04.read(r11, Math.min(j, this.A00));
            if (read != -1) {
                this.A00 -= read;
                return read;
            }
            A00(false);
            throw new ProtocolException("unexpected end of stream");
        }
    }
}
