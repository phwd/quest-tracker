package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.0HX  reason: invalid class name */
public class AnonymousClass0HX extends AnonymousClass0PU {
    public long A00 = -1;
    public boolean A01 = true;
    public final C05890la A02;
    public final /* synthetic */ AnonymousClass0PK A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0HX(AnonymousClass0PK r3, C05890la r4) {
        super(r3);
        this.A03 = r3;
        this.A02 = r4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (X.C05570jz.A0A(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04550h0
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
            boolean r0 = X.C05570jz.A0A(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HX.close():void");
    }

    @Override // X.AbstractC04550h0
    public final long read(AnonymousClass0HR r11, long j) throws IOException {
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
                    this.A03.A04.A7I();
                }
                try {
                    AnonymousClass0PK r3 = this.A03;
                    AnonymousClass0Od r5 = r3.A04;
                    this.A00 = r5.A7D();
                    String trim = r5.A7I().trim();
                    long j3 = this.A00;
                    if (j3 < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                        throw new ProtocolException("expected chunk size and optional extensions but was \"" + j3 + trim + "\"");
                    }
                    if (j3 == 0) {
                        this.A01 = false;
                        AbstractC06150m3 r4 = r3.A02.A0G;
                        C05890la r32 = this.A02;
                        C06030lq r2 = new C06030lq();
                        while (true) {
                            String A7I = r5.A7I();
                            if (A7I.length() == 0) {
                                break;
                            }
                            AbstractC05620k9.A00.A06(r2, A7I);
                        }
                        AnonymousClass0iN.A02(r4, r32, new C06020lp(r2));
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
