package X;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.List;

public final class c9 {
    public int A00;
    public C0556bt A01;
    public C1141th A02;
    public AbstractC0571cA A03;
    public boolean A04;
    public boolean A05;
    public final Object A06;
    public final C0523bM A07;
    public final C0533bW A08;
    public final C0569c7 A09;

    public static Socket A00(c9 c9Var, boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            c9Var.A03 = null;
        }
        if (z2) {
            c9Var.A05 = true;
        }
        C1141th thVar = c9Var.A02;
        if (thVar != null) {
            if (z) {
                thVar.A0A = true;
            }
            if (c9Var.A03 == null && (c9Var.A05 || thVar.A0A)) {
                List list = thVar.A0B;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (((Reference) list.get(i)).get() == c9Var) {
                        list.remove(i);
                        if (c9Var.A02.A0B.isEmpty()) {
                            C1141th thVar2 = c9Var.A02;
                            thVar2.A02 = System.nanoTime();
                            C0533bW bWVar = c9Var.A08;
                            if (thVar2.A0A || bWVar.A01 == 0) {
                                bWVar.A04.remove(thVar2);
                                socket = c9Var.A02.A04;
                                c9Var.A02 = null;
                                return socket;
                            }
                            bWVar.notifyAll();
                        }
                        socket = null;
                        c9Var.A02 = null;
                        return socket;
                    }
                }
                throw new IllegalStateException();
            }
        }
        return null;
    }

    public final synchronized C1141th A01() {
        return this.A02;
    }

    public final void A02() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, true, false, false);
        }
        C0561by.A07(A002);
    }

    public final void A03() {
        Socket A002;
        synchronized (this.A08) {
            A002 = A00(this, false, true, false);
        }
        C0561by.A07(A002);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x005e, code lost:
        if (r10.A00 <= 1) goto L_0x0064;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A04(java.io.IOException r11) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.c9.A04(java.io.IOException):void");
    }

    public final void A05(boolean z, AbstractC0571cA cAVar) {
        Socket A002;
        synchronized (this.A08) {
            if (cAVar != null) {
                if (cAVar == this.A03) {
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
            sb.append(cAVar);
            throw new IllegalStateException(sb.toString());
        }
        C0561by.A07(A002);
    }

    public c9(C0533bW bWVar, C0523bM bMVar, Object obj) {
        this.A08 = bWVar;
        this.A07 = bMVar;
        this.A09 = new C0569c7(bMVar, bWVar.A05);
        this.A06 = obj;
    }

    public final String toString() {
        C1141th A012 = A01();
        if (A012 != null) {
            return A012.toString();
        }
        return this.A07.toString();
    }
}
