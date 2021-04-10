package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;

public final class tY implements AbstractC0571cA {
    public int A00 = 0;
    public final t6 A01;
    public final C0548bl A02;
    public final c9 A03;
    public final t4 A04;

    public final void A00(C0542bf bfVar, String str) {
        int i = this.A00;
        if (i == 0) {
            t6 t6Var = this.A01;
            t6Var.A5z(str);
            t6Var.A5z(HttpRequestMultipart.LINE_FEED);
            String[] strArr = bfVar.A00;
            int length = strArr.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 << 1;
                t6Var.A5z(strArr[i3]);
                t6Var.A5z(": ");
                t6Var.A5z(strArr[i3 + 1]);
                t6Var.A5z(HttpRequestMultipart.LINE_FEED);
            }
            t6Var.A5z(HttpRequestMultipart.LINE_FEED);
            this.A00 = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass08.A00("state: ", i));
    }

    @Override // X.AbstractC0571cA
    public final AbstractC0608cr A1Z(C0551bo boVar, long j) {
        if ("chunked".equalsIgnoreCase(boVar.A02.A00("Transfer-Encoding"))) {
            int i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new C1134ta(this);
            }
            throw new IllegalStateException(AnonymousClass08.A00("state: ", i));
        } else if (j != -1) {
            int i2 = this.A00;
            if (i2 == 1) {
                this.A00 = 2;
                return new C1133tZ(this, j);
            }
            throw new IllegalStateException(AnonymousClass08.A00("state: ", i2));
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
    }

    @Override // X.AbstractC0571cA
    public final void A28() {
        this.A01.flush();
    }

    @Override // X.AbstractC0571cA
    public final void A2B() {
        this.A01.flush();
    }

    @Override // X.AbstractC0571cA
    public final C0553bq A4j(boolean z) {
        int i = this.A00;
        if (i == 1 || i == 3) {
            try {
                t4 t4Var = this.A04;
                cG A002 = cG.A00(t4Var.A4l());
                C0553bq bqVar = new C0553bq();
                bqVar.A06 = A002.A02;
                int i2 = A002.A00;
                bqVar.A00 = i2;
                bqVar.A03 = A002.A01;
                C0541be beVar = new C0541be();
                while (true) {
                    String A4l = t4Var.A4l();
                    if (A4l.length() == 0) {
                        break;
                    }
                    AbstractC0558bv.A00.A00(beVar, A4l);
                }
                bqVar.A05 = new C0542bf(beVar).A01();
                if (z && i2 == 100) {
                    return null;
                }
                this.A00 = 4;
                return bqVar;
            } catch (EOFException e) {
                StringBuilder sb = new StringBuilder("unexpected end of stream on ");
                sb.append(this.A03);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException(AnonymousClass08.A00("state: ", i));
        }
    }

    @Override // X.AbstractC0571cA
    public final void A5u(C0551bo boVar) {
        Proxy.Type type = this.A03.A01().A0D.A01.type();
        StringBuilder sb = new StringBuilder();
        sb.append(boVar.A01);
        sb.append(' ');
        C0544bh bhVar = boVar.A03;
        if (bhVar.A03.equals("https") || type != Proxy.Type.HTTP) {
            sb.append(C0576cF.A00(bhVar));
        } else {
            sb.append(bhVar);
        }
        sb.append(" HTTP/1.1");
        A00(boVar.A02, sb.toString());
    }

    @Override // X.AbstractC0571cA
    public final void cancel() {
        C1141th A012 = this.A03.A01();
        if (A012 != null) {
            C0561by.A07(A012.A03);
        }
    }

    public tY(C0548bl blVar, c9 c9Var, t4 t4Var, t6 t6Var) {
        this.A02 = blVar;
        this.A03 = c9Var;
        this.A04 = t4Var;
        this.A01 = t6Var;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r3 != -1) goto L_0x0008;
     */
    @Override // X.AbstractC0571cA
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC0555bs A4T(X.C0554br r6) {
        /*
        // Method dump skipped, instructions count: 154
        */
        throw new UnsupportedOperationException("Method not decompiled: X.tY.A4T(X.br):X.bs");
    }
}
