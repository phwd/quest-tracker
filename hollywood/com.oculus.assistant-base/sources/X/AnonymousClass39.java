package X;

import java.net.ProtocolException;

/* renamed from: X.39  reason: invalid class name */
public final class AnonymousClass39 extends AbstractC1135tb {
    public long A00 = -1;
    public boolean A01 = true;
    public final C0544bh A02;
    public final /* synthetic */ tY A03;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass39(tY tYVar, C0544bh bhVar) {
        super(tYVar);
        this.A03 = tYVar;
        this.A02 = bhVar;
    }

    @Override // X.AbstractC0609cs
    public final long A4c(AnonymousClass33 r11, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass08.A03("byteCount < 0: ", j));
        } else if (super.A00) {
            throw new IllegalStateException("closed");
        } else if (!this.A01) {
            return -1;
        } else {
            long j2 = this.A00;
            if (j2 == 0 || j2 == -1) {
                if (j2 != -1) {
                    this.A03.A04.A4l();
                }
                try {
                    tY tYVar = this.A03;
                    t4 t4Var = tYVar.A04;
                    this.A00 = t4Var.A4g();
                    String trim = t4Var.A4l().trim();
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
                        AbstractC0537ba baVar = tYVar.A02.A0G;
                        C0544bh bhVar = this.A02;
                        C0541be beVar = new C0541be();
                        while (true) {
                            String A4l = t4Var.A4l();
                            if (A4l.length() == 0) {
                                break;
                            }
                            AbstractC0558bv.A00.A00(beVar, A4l);
                        }
                        C0574cD.A02(baVar, bhVar, new C0542bf(beVar));
                        A00(true);
                    }
                    if (!this.A01) {
                        return -1;
                    }
                } catch (NumberFormatException e) {
                    throw new ProtocolException(e.getMessage());
                }
            }
            long A4c = this.A03.A04.A4c(r11, Math.min(j, this.A00));
            if (A4c != -1) {
                this.A00 -= A4c;
                return A4c;
            }
            A00(false);
            throw new ProtocolException("unexpected end of stream");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        if (X.C0561by.A0A(r2, 100, java.util.concurrent.TimeUnit.MILLISECONDS) == false) goto L_0x0012;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0609cs
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void close() {
        /*
            r2 = this;
            boolean r0 = r2.A00
            if (r0 != 0) goto L_0x0019
            boolean r0 = r2.A01
            if (r0 == 0) goto L_0x0016
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            r0 = 100
            boolean r0 = X.C0561by.A0A(r2, r0, r1)     // Catch:{ IOException -> 0x0012 }
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass39.close():void");
    }
}
