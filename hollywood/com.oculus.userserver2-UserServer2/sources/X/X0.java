package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;

public final class X0 {
    public int A00;
    public XI A01;
    public EX A02;
    public AbstractC0174Wz A03;
    public boolean A04;
    public final Object A05;
    public final C0190Xp A06;
    public final C0180Xf A07;
    public final X2 A08;

    public static Socket A00(X0 x0, boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            x0.A03 = null;
        }
        if (z2) {
            x0.A04 = true;
        }
        EX ex = x0.A02;
        if (ex != null) {
            if (z) {
                ex.A0A = true;
            }
            if (x0.A03 == null && (x0.A04 || ex.A0A)) {
                List<Reference<X0>> list = ex.A0B;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).get() == x0) {
                        list.remove(i);
                        if (x0.A02.A0B.isEmpty()) {
                            EX ex2 = x0.A02;
                            ex2.A02 = System.nanoTime();
                            C0180Xf xf = x0.A07;
                            if (ex2.A0A || xf.A01 == 0) {
                                xf.A04.remove(ex2);
                                socket = x0.A02.A04;
                                x0.A02 = null;
                                return socket;
                            }
                            xf.notifyAll();
                        }
                        socket = null;
                        x0.A02 = null;
                        return socket;
                    }
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    public final synchronized EX A01() {
        return this.A02;
    }

    public final void A02() {
        Socket A002;
        synchronized (this.A07) {
            A002 = A00(this, true, false, false);
        }
        XD.A07(A002);
    }

    public final void A03() {
        Socket A002;
        synchronized (this.A07) {
            A002 = A00(this, false, true, false);
        }
        XD.A07(A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005e, code lost:
        if (r10.A00 <= 1) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.io.IOException r11) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.X0.A04(java.io.IOException):void");
    }

    public final void A05(boolean z, AbstractC0174Wz wz) {
        Socket A002;
        synchronized (this.A07) {
            if (wz != null) {
                if (wz == this.A03) {
                    if (!z) {
                        this.A02.A01++;
                    }
                    A002 = A00(this, z, false, true);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("expected ");
            sb.append(this.A03);
            sb.append(" but was ");
            sb.append(wz);
            throw new IllegalStateException(sb.toString());
        }
        XD.A07(A002);
    }

    public X0(C0180Xf xf, C0190Xp xp, Object obj) {
        this.A07 = xf;
        this.A06 = xp;
        this.A08 = new X2(xp, xf.A05);
        this.A05 = obj;
    }

    public final String toString() {
        EX A012 = A01();
        if (A012 != null) {
            return A012.toString();
        }
        return this.A06.toString();
    }
}
