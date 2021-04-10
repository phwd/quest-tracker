package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;

/* renamed from: X.0jA  reason: invalid class name and case insensitive filesystem */
public final class C05360jA {
    public int A00;
    public C05640kB A01;
    public C01990Pw A02;
    public AbstractC05270iw A03;
    public boolean A04;
    public boolean A05;
    public final Object A06;
    public final C06800nu A07;
    public final AnonymousClass0m7 A08;
    public final C05380jD A09;

    public static Socket A00(C05360jA r5, boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            r5.A03 = null;
        }
        if (z2) {
            r5.A05 = true;
        }
        C01990Pw r1 = r5.A02;
        if (r1 != null) {
            if (z) {
                r1.A0A = true;
            }
            if (r5.A03 == null && (r5.A05 || r1.A0A)) {
                List<Reference<C05360jA>> list = r1.A0B;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).get() == r5) {
                        list.remove(i);
                        if (r5.A02.A0B.isEmpty()) {
                            C01990Pw r2 = r5.A02;
                            r2.A02 = System.nanoTime();
                            if (AbstractC05620k9.A00.A08(r5.A08, r2)) {
                                socket = r5.A02.A04;
                                r5.A02 = null;
                                return socket;
                            }
                        }
                        socket = null;
                        r5.A02 = null;
                        return socket;
                    }
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    public final synchronized C01990Pw A01() {
        return this.A02;
    }

    public final void A02() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, true, false, false);
        }
        C05570jz.A07(A002);
    }

    public final void A03() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, false, true, false);
        }
        C05570jz.A07(A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005e, code lost:
        if (r10.A00 <= 1) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.io.IOException r11) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05360jA.A04(java.io.IOException):void");
    }

    public final void A05(boolean z, AbstractC05270iw r6) {
        Socket A002;
        synchronized (this.A08) {
            if (r6 != null) {
                if (r6 == this.A03) {
                    if (!z) {
                        this.A02.A01++;
                    }
                    A002 = A00(this, z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.A03 + " but was " + r6);
        }
        C05570jz.A07(A002);
    }

    public C05360jA(AnonymousClass0m7 r3, C06800nu r4, Object obj) {
        this.A08 = r3;
        this.A07 = r4;
        this.A09 = new C05380jD(r4, AbstractC05620k9.A00.A03(r3));
        this.A06 = obj;
    }

    public final String toString() {
        C01990Pw A012 = A01();
        if (A012 != null) {
            return A012.toString();
        }
        return this.A07.toString();
    }
}
