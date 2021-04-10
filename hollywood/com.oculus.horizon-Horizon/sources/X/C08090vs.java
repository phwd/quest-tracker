package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;

/* renamed from: X.0vs  reason: invalid class name and case insensitive filesystem */
public final class C08090vs {
    public int A00;
    public C08200wA A01;
    public C01250Mm A02;
    public AbstractC08080vr A03;
    public boolean A04;
    public boolean A05;
    public final Object A06;
    public final C08610wt A07;
    public final C08510wh A08;
    public final C08110vu A09;

    public static Socket A00(C08090vs r5, boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            r5.A03 = null;
        }
        if (z2) {
            r5.A05 = true;
        }
        C01250Mm r1 = r5.A02;
        if (r1 != null) {
            if (z) {
                r1.A0A = true;
            }
            if (r5.A03 == null && (r5.A05 || r1.A0A)) {
                List<Reference<C08090vs>> list = r1.A0B;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).get() == r5) {
                        list.remove(i);
                        if (r5.A02.A0B.isEmpty()) {
                            C01250Mm r2 = r5.A02;
                            r2.A02 = System.nanoTime();
                            if (AbstractC08180w8.A00.A08(r5.A08, r2)) {
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

    public final synchronized C01250Mm A01() {
        return this.A02;
    }

    public final void A02() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, true, false, false);
        }
        C08160w5.A07(A002);
    }

    public final void A03() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, false, true, false);
        }
        C08160w5.A07(A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005e, code lost:
        if (r10.A00 <= 1) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.io.IOException r11) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08090vs.A04(java.io.IOException):void");
    }

    public final void A05(boolean z, AbstractC08080vr r6) {
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
            StringBuilder sb = new StringBuilder();
            sb.append("expected ");
            sb.append(this.A03);
            sb.append(" but was ");
            sb.append(r6);
            throw new IllegalStateException(sb.toString());
        }
        C08160w5.A07(A002);
    }

    public C08090vs(C08510wh r3, C08610wt r4, Object obj) {
        this.A08 = r3;
        this.A07 = r4;
        this.A09 = new C08110vu(r4, AbstractC08180w8.A00.A03(r3));
        this.A06 = obj;
    }

    public final String toString() {
        C01250Mm A012 = A01();
        if (A012 != null) {
            return A012.toString();
        }
        return this.A07.toString();
    }
}
