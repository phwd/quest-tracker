package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.List;

public final class EM implements AbstractC0174Wz {
    public static final List<WM> A04;
    public static final List<WM> A05;
    public static final WM A06 = WM.A04("connection");
    public static final WM A07 = WM.A04("encoding");
    public static final WM A08 = WM.A04("host");
    public static final WM A09 = WM.A04("keep-alive");
    public static final WM A0A = WM.A04("proxy-connection");
    public static final WM A0B = WM.A04("te");
    public static final WM A0C = WM.A04("transfer-encoding");
    public static final WM A0D;
    public C0156Wh A00;
    public final X0 A01;
    public final AbstractC0054Ej A02;
    public final C0158Wj A03;

    static {
        WM A042 = WM.A04("upgrade");
        A0D = A042;
        WM wm = A06;
        WM wm2 = A08;
        WM wm3 = A09;
        WM wm4 = A0A;
        WM wm5 = A0B;
        WM wm6 = A0C;
        WM wm7 = A07;
        A04 = XD.A05(wm, wm2, wm3, wm4, wm5, wm6, wm7, A042, C0165Wq.A06, C0165Wq.A07, C0165Wq.A08, C0165Wq.A05);
        A05 = XD.A05(wm, wm2, wm3, wm4, wm5, wm6, wm7, A0D);
    }

    @Override // X.AbstractC0174Wz
    public final WG A1H(XN xn, long j) {
        return this.A00.A01();
    }

    @Override // X.AbstractC0174Wz
    public final void A1Z() throws IOException {
        this.A03.A0E.A01();
    }

    @Override // X.AbstractC0174Wz
    public final XJ A2k(XK xk) throws IOException {
        return new ET(xk.A06, new C00148h(new C00158m(this, this.A00.A08)));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC0174Wz
    public final XL A38(boolean z) throws IOException {
        List<C0165Wq> list;
        C0156Wh wh = this.A00;
        synchronized (wh) {
            if (wh.A06()) {
                AnonymousClass8l r2 = wh.A09;
                r2.enter();
                while (wh.A02 == null && wh.A03 == null) {
                    try {
                        try {
                            wh.wait();
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    } catch (Throwable th) {
                        r2.A00();
                        throw th;
                    }
                }
                r2.A00();
                list = wh.A02;
                if (list != null) {
                    wh.A02 = null;
                } else {
                    throw new Wa(wh.A03);
                }
            } else {
                throw new IllegalStateException("servers cannot read response headers");
            }
        }
        XX xx = new XX();
        int size = list.size();
        C0168Wt wt = null;
        for (int i = 0; i < size; i++) {
            C0165Wq wq = list.get(i);
            if (wq != null) {
                WM wm = wq.A01;
                String A0A2 = wq.A02.A0A();
                if (wm.equals(C0165Wq.A04)) {
                    wt = C0168Wt.A00(AnonymousClass06.A03("HTTP/1.1 ", A0A2));
                } else if (!A05.contains(wm)) {
                    xx.A02(wm.A0A(), A0A2);
                }
            } else if (wt != null && wt.A00 == 100) {
                xx = new XX();
                wt = null;
            }
        }
        if (wt != null) {
            XL xl = new XL();
            xl.A06 = XP.HTTP_2;
            xl.A00 = wt.A00;
            xl.A03 = wt.A01;
            xl.A05 = new XW(xx).A01();
            if (!z || xl.A00 != 100) {
                return xl;
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
    @Override // X.AbstractC0174Wz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A42(X.XN r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: X.EM.A42(X.XN):void");
    }

    @Override // X.AbstractC0174Wz
    public final void finishRequest() throws IOException {
        this.A00.A01().close();
    }

    public EM(AbstractC0054Ej ej, X0 x0, C0158Wj wj) {
        this.A02 = ej;
        this.A01 = x0;
        this.A03 = wj;
    }
}
