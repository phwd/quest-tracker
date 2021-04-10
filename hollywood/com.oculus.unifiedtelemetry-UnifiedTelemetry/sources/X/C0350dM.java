package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;

/* renamed from: X.dM  reason: case insensitive filesystem */
public final class C0350dM {
    public int A00;
    public C0357de A01;
    public L6 A02;
    public dL A03;
    public boolean A04;
    public boolean A05;
    public final Object A06;
    public final eB A07;
    public final e1 A08;
    public final C0352dO A09;

    public static Socket A00(C0350dM dMVar, boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            dMVar.A03 = null;
        }
        if (z2) {
            dMVar.A05 = true;
        }
        L6 l6 = dMVar.A02;
        if (l6 != null) {
            if (z) {
                l6.A0A = true;
            }
            if (dMVar.A03 == null && (dMVar.A05 || l6.A0A)) {
                List<Reference<C0350dM>> list = l6.A0B;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (list.get(i).get() == dMVar) {
                        list.remove(i);
                        if (dMVar.A02.A0B.isEmpty()) {
                            L6 l62 = dMVar.A02;
                            l62.A02 = System.nanoTime();
                            if (AbstractC0355dc.A00.A08(dMVar.A08, l62)) {
                                socket = dMVar.A02.A04;
                                dMVar.A02 = null;
                                return socket;
                            }
                        }
                        socket = null;
                        dMVar.A02 = null;
                        return socket;
                    }
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    public final synchronized L6 A01() {
        return this.A02;
    }

    public final void A02() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, true, false, false);
        }
        dZ.A07(A002);
    }

    public final void A03() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, false, true, false);
        }
        dZ.A07(A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005e, code lost:
        if (r10.A00 <= 1) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.io.IOException r11) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0350dM.A04(java.io.IOException):void");
    }

    public final void A05(boolean z, dL dLVar) {
        Socket A002;
        synchronized (this.A08) {
            if (dLVar != null) {
                if (dLVar == this.A03) {
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
            sb.append(dLVar);
            throw new IllegalStateException(sb.toString());
        }
        dZ.A07(A002);
    }

    public C0350dM(e1 e1Var, eB eBVar, Object obj) {
        this.A08 = e1Var;
        this.A07 = eBVar;
        this.A09 = new C0352dO(eBVar, AbstractC0355dc.A00.A03(e1Var));
        this.A06 = obj;
    }

    public final String toString() {
        L6 A012 = A01();
        if (A012 != null) {
            return A012.toString();
        }
        return this.A07.toString();
    }
}
