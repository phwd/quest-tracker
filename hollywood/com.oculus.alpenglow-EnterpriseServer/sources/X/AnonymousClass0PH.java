package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.List;

/* renamed from: X.0PH  reason: invalid class name */
public final class AnonymousClass0PH implements AbstractC05270iw {
    public static final List<C04610h7> A04;
    public static final List<C04610h7> A05;
    public static final C04610h7 A06 = C04610h7.A04("connection");
    public static final C04610h7 A07 = C04610h7.A04("encoding");
    public static final C04610h7 A08 = C04610h7.A04("host");
    public static final C04610h7 A09 = C04610h7.A04("keep-alive");
    public static final C04610h7 A0A = C04610h7.A04("proxy-connection");
    public static final C04610h7 A0B = C04610h7.A04("te");
    public static final C04610h7 A0C = C04610h7.A04("transfer-encoding");
    public static final C04610h7 A0D;
    public C04760hS A00;
    public final C05360jA A01;
    public final AnonymousClass0Qs A02;
    public final C04780hU A03;

    static {
        C04610h7 A042 = C04610h7.A04("upgrade");
        A0D = A042;
        C04610h7 r1 = A06;
        C04610h7 r2 = A08;
        C04610h7 r3 = A09;
        C04610h7 r4 = A0A;
        C04610h7 r5 = A0B;
        C04610h7 r6 = A0C;
        C04610h7 r7 = A07;
        A04 = C05570jz.A05(r1, r2, r3, r4, r5, r6, r7, A042, C04870hr.A06, C04870hr.A07, C04870hr.A08, C04870hr.A05);
        A05 = C05570jz.A05(r1, r2, r3, r4, r5, r6, r7, A0D);
    }

    @Override // X.AbstractC05270iw
    public final AnonymousClass0h1 A21(C05700ke r2, long j) {
        return this.A00.A01();
    }

    @Override // X.AbstractC05270iw
    public final void A2q() throws IOException {
        this.A03.A0F.A01();
    }

    @Override // X.AbstractC05270iw
    public final AbstractC05650kC A6k(C05660kD r4) throws IOException {
        return new AnonymousClass0PW(r4.A06, new AnonymousClass0HO(new AnonymousClass0HT(this, this.A00.A08)));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC05270iw
    public final C05680ka A7G(boolean z) throws IOException {
        List<C04870hr> list;
        C04760hS r3 = this.A00;
        synchronized (r3) {
            if (r3.A06()) {
                AnonymousClass0HS r2 = r3.A09;
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
                    throw new C04710hL(r3.A03);
                }
            } else {
                throw new IllegalStateException("servers cannot read response headers");
            }
        }
        C06030lq r32 = new C06030lq();
        int size = list.size();
        AnonymousClass0i1 r4 = null;
        for (int i = 0; i < size; i++) {
            C04870hr r0 = list.get(i);
            if (r0 != null) {
                C04610h7 r8 = r0.A01;
                String A0A2 = r0.A02.A0A();
                if (r8.equals(C04870hr.A04)) {
                    r4 = AnonymousClass0i1.A00(AnonymousClass006.A05("HTTP/1.1 ", A0A2));
                } else if (!A05.contains(r8)) {
                    AbstractC05620k9.A00.A07(r32, r8.A0A(), A0A2);
                }
            } else if (r4 != null && r4.A00 == 100) {
                r32 = new C06030lq();
                r4 = null;
            }
        }
        if (r4 != null) {
            C05680ka r22 = new C05680ka();
            r22.A06 = AnonymousClass0kh.HTTP_2;
            r22.A00 = r4.A00;
            r22.A03 = r4.A01;
            r22.A05 = new C06020lp(r32).A01();
            if (!z || AbstractC05620k9.A00.A00(r22) != 100) {
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
    @Override // X.AbstractC05270iw
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A9A(X.C05700ke r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PH.A9A(X.0ke):void");
    }

    @Override // X.AbstractC05270iw
    public final void cancel() {
        C04760hS r3 = this.A00;
        if (r3 != null) {
            EnumC04880hs r2 = EnumC04880hs.CANCEL;
            if (C04760hS.A00(r3, r2)) {
                r3.A07.A03(r3.A06, r2);
            }
        }
    }

    @Override // X.AbstractC05270iw
    public final void finishRequest() throws IOException {
        this.A00.A01().close();
    }

    public AnonymousClass0PH(AnonymousClass0Qs r1, C05360jA r2, C04780hU r3) {
        this.A02 = r1;
        this.A01 = r2;
        this.A03 = r3;
    }
}
