package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.util.List;

/* renamed from: X.Kw  reason: case insensitive filesystem */
public final class C0126Kw implements dL {
    public static final List<ci> A04;
    public static final List<ci> A05;
    public static final ci A06 = ci.A04("connection");
    public static final ci A07 = ci.A04("encoding");
    public static final ci A08 = ci.A04("host");
    public static final ci A09 = ci.A04("keep-alive");
    public static final ci A0A = ci.A04("proxy-connection");
    public static final ci A0B = ci.A04("te");
    public static final ci A0C = ci.A04("transfer-encoding");
    public static final ci A0D;
    public C0335d3 A00;
    public final C0350dM A01;
    public final LD A02;
    public final C0337d5 A03;

    static {
        ci A042 = ci.A04("upgrade");
        A0D = A042;
        ci ciVar = A06;
        ci ciVar2 = A08;
        ci ciVar3 = A09;
        ci ciVar4 = A0A;
        ci ciVar5 = A0B;
        ci ciVar6 = A0C;
        ci ciVar7 = A07;
        A04 = dZ.A05(ciVar, ciVar2, ciVar3, ciVar4, ciVar5, ciVar6, ciVar7, A042, C0343dC.A06, C0343dC.A07, C0343dC.A08, C0343dC.A05);
        A05 = dZ.A05(ciVar, ciVar2, ciVar3, ciVar4, ciVar5, ciVar6, ciVar7, A0D);
    }

    @Override // X.dL
    public final AbstractC0313cc A1j(C0362dj djVar, long j) {
        return this.A00.A01();
    }

    @Override // X.dL
    public final void A2D() throws IOException {
        this.A03.A0F.A01();
    }

    @Override // X.dL
    public final AbstractC0358df A47(C0359dg dgVar) throws IOException {
        return new L2(dgVar.A06, new AnonymousClass93(new AnonymousClass9A(this, this.A00.A08)));
    }

    /* JADX INFO: finally extract failed */
    @Override // X.dL
    public final C0360dh A4Z(boolean z) throws IOException {
        List<C0343dC> list;
        C0335d3 d3Var = this.A00;
        synchronized (d3Var) {
            if (d3Var.A06()) {
                AnonymousClass99 r2 = d3Var.A09;
                r2.enter();
                while (d3Var.A02 == null && d3Var.A03 == null) {
                    try {
                        try {
                            d3Var.wait();
                        } catch (InterruptedException unused) {
                            throw new InterruptedIOException();
                        }
                    } catch (Throwable th) {
                        r2.A00();
                        throw th;
                    }
                }
                r2.A00();
                list = d3Var.A02;
                if (list != null) {
                    d3Var.A02 = null;
                } else {
                    throw new C0329cw(d3Var.A03);
                }
            } else {
                throw new IllegalStateException("servers cannot read response headers");
            }
        }
        C0370dt dtVar = new C0370dt();
        int size = list.size();
        C0345dF dFVar = null;
        for (int i = 0; i < size; i++) {
            C0343dC dCVar = list.get(i);
            if (dCVar != null) {
                ci ciVar = dCVar.A01;
                String A0A2 = dCVar.A02.A0A();
                if (ciVar.equals(C0343dC.A04)) {
                    dFVar = C0345dF.A00(AnonymousClass06.A04("HTTP/1.1 ", A0A2));
                } else if (!A05.contains(ciVar)) {
                    AbstractC0355dc.A00.A07(dtVar, ciVar.A0A(), A0A2);
                }
            } else if (dFVar != null && dFVar.A00 == 100) {
                dtVar = new C0370dt();
                dFVar = null;
            }
        }
        if (dFVar != null) {
            C0360dh dhVar = new C0360dh();
            dhVar.A06 = EnumC0364dl.HTTP_2;
            dhVar.A00 = dFVar.A00;
            dhVar.A03 = dFVar.A01;
            dhVar.A05 = new C0369ds(dtVar).A01();
            if (!z || AbstractC0355dc.A00.A00(dhVar) != 100) {
                return dhVar;
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
    @Override // X.dL
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A5w(X.C0362dj r23) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 630
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0126Kw.A5w(X.dj):void");
    }

    @Override // X.dL
    public final void cancel() {
        C0335d3 d3Var = this.A00;
        if (d3Var != null) {
            dD dDVar = dD.CANCEL;
            if (C0335d3.A00(d3Var, dDVar)) {
                d3Var.A07.A03(d3Var.A06, dDVar);
            }
        }
    }

    @Override // X.dL
    public final void finishRequest() throws IOException {
        this.A00.A01().close();
    }

    public C0126Kw(LD ld, C0350dM dMVar, C0337d5 d5Var) {
        this.A02 = ld;
        this.A01 = dMVar;
        this.A03 = d5Var;
    }
}
