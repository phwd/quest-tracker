package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.List;

/* renamed from: X.0MT  reason: invalid class name */
public final class AnonymousClass0MT implements AbstractC08080vr {
    public static final List<C07700vD> A04;
    public static final List<C07700vD> A05 = C08160w5.A05(A06, A08, A09, A0A, A0B, A0C, A07, A0D);
    public static final C07700vD A06 = C07700vD.A04(AnonymousClass1eW.HEADER_CONNECTION);
    public static final C07700vD A07 = C07700vD.A04("encoding");
    public static final C07700vD A08 = C07700vD.A04("host");
    public static final C07700vD A09 = C07700vD.A04("keep-alive");
    public static final C07700vD A0A = C07700vD.A04("proxy-connection");
    public static final C07700vD A0B = C07700vD.A04("te");
    public static final C07700vD A0C = C07700vD.A04("transfer-encoding");
    public static final C07700vD A0D;
    public C07900vZ A00;
    public final C08090vs A01;
    public final AnonymousClass0N1 A02;
    public final C07920vb A03;

    static {
        C07700vD A042 = C07700vD.A04(AnonymousClass1eW.HEADER_UPGRADE);
        A0D = A042;
        A04 = C08160w5.A05(A06, A08, A09, A0A, A0B, A0C, A07, A042, C07990vi.A06, C07990vi.A07, C07990vi.A08, C07990vi.A05);
    }

    @Override // X.AbstractC08080vr
    public final AbstractC07640v7 A26(C08330wN r2, long j) {
        return this.A00.A01();
    }

    @Override // X.AbstractC08080vr
    public final void A2q() throws IOException {
        this.A03.A0F.A01();
    }

    @Override // X.AbstractC08080vr
    public final AbstractC08210wB A7E(C08220wC r4) throws IOException {
        return new C01210Me(r4.A06, new C00560Au(new AnonymousClass0BB(this, this.A00.A08)));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC08080vr
    public final C08230wD A7y(boolean z) throws IOException {
        List<C07990vi> list;
        C07900vZ r3 = this.A00;
        synchronized (r3) {
            if (r3.A06()) {
                AnonymousClass0BA r2 = r3.A09;
                r2.enter();
                while (r3.A02 == null && r3.A03 == null) {
                    try {
                        try {
                            r3.wait();
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    } catch (Throwable th) {
                        r2.A00();
                        throw th;
                    }
                }
                r2.A00();
                list = r3.A02;
                if (list != null) {
                    r3.A02 = null;
                } else {
                    throw new C07840vS(r3.A03);
                }
            } else {
                throw new IllegalStateException("servers cannot read response headers");
            }
        }
        C08420wY r32 = new C08420wY();
        int size = list.size();
        C08020vl r4 = null;
        for (int i = 0; i < size; i++) {
            C07990vi r0 = list.get(i);
            if (r0 != null) {
                C07700vD r8 = r0.A01;
                String A0A2 = r0.A02.A0A();
                if (r8.equals(C07990vi.A04)) {
                    r4 = C08020vl.A00(AnonymousClass006.A05("HTTP/1.1 ", A0A2));
                } else if (!A05.contains(r8)) {
                    AbstractC08180w8.A00.A07(r32, r8.A0A(), A0A2);
                }
            } else if (r4 != null && r4.A00 == 100) {
                r32 = new C08420wY();
                r4 = null;
            }
        }
        if (r4 != null) {
            C08230wD r22 = new C08230wD();
            r22.A06 = EnumC08350wP.HTTP_2;
            r22.A00 = r4.A00;
            r22.A03 = r4.A01;
            r22.A05 = new AnonymousClass0wX(r32).A01();
            if (!z || AbstractC08180w8.A00.A00(r22) != 100) {
                return r22;
            }
            return null;
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b2, code lost:
        if (r4.A01 == 0) goto L_0x00b4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01c4  */
    @Override // X.AbstractC08080vr
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAG(X.C08330wN r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0MT.AAG(X.0wN):void");
    }

    @Override // X.AbstractC08080vr
    public final void cancel() {
        C07900vZ r3 = this.A00;
        if (r3 != null) {
            EnumC08000vj r2 = EnumC08000vj.CANCEL;
            if (C07900vZ.A00(r3, r2)) {
                r3.A07.A03(r3.A06, r2);
            }
        }
    }

    @Override // X.AbstractC08080vr
    public final void finishRequest() throws IOException {
        this.A00.A01().close();
    }

    public AnonymousClass0MT(AnonymousClass0N1 r1, C08090vs r2, C07920vb r3) {
        this.A02 = r1;
        this.A01 = r2;
        this.A03 = r3;
    }
}
