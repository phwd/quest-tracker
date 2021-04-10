package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.0BE  reason: invalid class name */
public class AnonymousClass0BE extends AbstractC01190Mc {
    public long A00 = -1;
    public boolean A01 = true;
    public final C08400wU A02;
    public final /* synthetic */ AnonymousClass0MU A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0BE(AnonymousClass0MU r3, C08400wU r4) {
        super(r3);
        this.A03 = r3;
        this.A02 = r4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (X.C08160w5.A0A(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
     */
    @Override // X.AbstractC07630v6, java.io.Closeable, java.lang.AutoCloseable
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
            boolean r0 = X.C08160w5.A0A(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0BE.close():void");
    }

    @Override // X.AbstractC07630v6
    public final long read(AnonymousClass0B3 r11, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A04("byteCount < 0: ", j));
        } else if (super.A00) {
            throw new IllegalStateException("closed");
        } else if (!this.A01) {
            return -1;
        } else {
            long j2 = this.A00;
            if (j2 == 0 || j2 == -1) {
                if (j2 != -1) {
                    this.A03.A04.A81();
                }
                try {
                    AnonymousClass0MU r3 = this.A03;
                    AnonymousClass0Lw r5 = r3.A04;
                    this.A00 = r5.A7v();
                    String trim = r5.A81().trim();
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
                        AbstractC08470wd r4 = r3.A02.A0I;
                        C08400wU r32 = this.A02;
                        C08420wY r2 = new C08420wY();
                        while (true) {
                            String A81 = r5.A81();
                            if (A81.length() == 0) {
                                break;
                            }
                            AbstractC08180w8.A00.A06(r2, A81);
                        }
                        C08050vo.A02(r4, r32, new AnonymousClass0wX(r2));
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
