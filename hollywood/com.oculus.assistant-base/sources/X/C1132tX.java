package X;

import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.List;

/* renamed from: X.tX  reason: case insensitive filesystem */
public final class C1132tX implements AbstractC0571cA {
    public static final List A04;
    public static final List A05 = C0561by.A05(A06, A08, A09, A0A, A0B, A0C, A07, A0D);
    public static final C0603cm A06 = C0603cm.A02("connection");
    public static final C0603cm A07 = C0603cm.A02("encoding");
    public static final C0603cm A08 = C0603cm.A02("host");
    public static final C0603cm A09 = C0603cm.A02("keep-alive");
    public static final C0603cm A0A = C0603cm.A02("proxy-connection");
    public static final C0603cm A0B = C0603cm.A02("te");
    public static final C0603cm A0C = C0603cm.A02("transfer-encoding");
    public static final C0603cm A0D;
    public cS A00;
    public final c9 A01;
    public final C0548bl A02;
    public final C0584cQ A03;

    static {
        C0603cm A022 = C0603cm.A02("upgrade");
        A0D = A022;
        A04 = C0561by.A05(A06, A08, A09, A0A, A0B, A0C, A07, A022, C0578cJ.A06, C0578cJ.A07, C0578cJ.A08, C0578cJ.A05);
    }

    @Override // X.AbstractC0571cA
    public final AbstractC0608cr A1Z(C0551bo boVar, long j) {
        return this.A00.A01();
    }

    @Override // X.AbstractC0571cA
    public final void A28() {
        this.A00.A01().close();
    }

    @Override // X.AbstractC0571cA
    public final void A2B() {
        this.A03.A0E.A01();
    }

    @Override // X.AbstractC0571cA
    public final AbstractC0555bs A4T(C0554br brVar) {
        return new C1137td(brVar.A06, new C00222y(new AnonymousClass35(this, this.A00.A08)));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC0571cA
    public final C0553bq A4j(boolean z) {
        List list;
        cS cSVar = this.A00;
        synchronized (cSVar) {
            if (cSVar.A06()) {
                AnonymousClass34 r1 = cSVar.A09;
                r1.A08();
                while (cSVar.A02 == null && cSVar.A03 == null) {
                    try {
                        try {
                            cSVar.wait();
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    } catch (Throwable th) {
                        r1.A0B();
                        throw th;
                    }
                }
                r1.A0B();
                list = cSVar.A02;
                if (list != null) {
                    cSVar.A02 = null;
                } else {
                    throw new C0591cZ(cSVar.A03);
                }
            } else {
                throw new IllegalStateException("servers cannot read response headers");
            }
        }
        C0541be beVar = new C0541be();
        int size = list.size();
        cG cGVar = null;
        for (int i = 0; i < size; i++) {
            C0578cJ cJVar = (C0578cJ) list.get(i);
            if (cJVar != null) {
                C0603cm cmVar = cJVar.A01;
                String A082 = cJVar.A02.A08();
                if (cmVar.equals(C0578cJ.A04)) {
                    cGVar = cG.A00(AnonymousClass08.A04("HTTP/1.1 ", A082));
                } else if (!A05.contains(cmVar)) {
                    beVar.A02(cmVar.A08(), A082);
                }
            } else if (cGVar != null && cGVar.A00 == 100) {
                beVar = new C0541be();
                cGVar = null;
            }
        }
        if (cGVar != null) {
            C0553bq bqVar = new C0553bq();
            bqVar.A06 = EnumC0549bm.HTTP_2;
            bqVar.A00 = cGVar.A00;
            bqVar.A03 = cGVar.A01;
            bqVar.A05 = new C0542bf(beVar).A01();
            if (!z || bqVar.A00 != 100) {
                return bqVar;
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
    @Override // X.AbstractC0571cA
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A5u(X.C0551bo r23) {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1132tX.A5u(X.bo):void");
    }

    @Override // X.AbstractC0571cA
    public final void cancel() {
        cS cSVar = this.A00;
        if (cSVar != null) {
            cI cIVar = cI.CANCEL;
            if (cS.A00(cSVar, cIVar)) {
                cSVar.A07.A03(cSVar.A06, cIVar);
            }
        }
    }

    public C1132tX(C0548bl blVar, c9 c9Var, C0584cQ cQVar) {
        this.A02 = blVar;
        this.A01 = c9Var;
        this.A03 = cQVar;
    }
}
