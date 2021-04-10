package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;

/* renamed from: X.0MU  reason: invalid class name */
public final class AnonymousClass0MU implements AbstractC08080vr {
    public int A00 = 0;
    public final AnonymousClass0Lx A01;
    public final AnonymousClass0N1 A02;
    public final C08090vs A03;
    public final AnonymousClass0Lw A04;

    public static final void A00(AnonymousClass0Ls r2) {
        C07620v5 r1 = r2.A00;
        C07620v5 r0 = C07620v5.NONE;
        if (r0 != null) {
            r2.A00 = r0;
            r1.clearDeadline();
            r1.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final void A01(AnonymousClass0wX r8, String str) throws IOException {
        int i = this.A00;
        if (i == 0) {
            AnonymousClass0Lx r6 = this.A01;
            r6.AAI(str);
            r6.AAI(HttpRequestMultipart.LINE_FEED);
            String[] strArr = r8.A00;
            int length = strArr.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 << 1;
                r6.AAI(strArr[i3]);
                r6.AAI(": ");
                r6.AAI(strArr[i3 + 1]);
                r6.AAI(HttpRequestMultipart.LINE_FEED);
            }
            r6.AAI(HttpRequestMultipart.LINE_FEED);
            this.A00 = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC08080vr
    public final AbstractC07640v7 A26(C08330wN r4, long j) {
        int i;
        if ("chunked".equalsIgnoreCase(r4.A02.A00("Transfer-Encoding"))) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new AnonymousClass0MW(this);
            }
        } else if (j != -1) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new AnonymousClass0MV(this, j);
            }
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC08080vr
    public final void A2q() throws IOException {
        this.A01.flush();
    }

    @Override // X.AbstractC08080vr
    public final C08230wD A7y(boolean z) throws IOException {
        int i = this.A00;
        if (i == 1 || i == 3) {
            try {
                AnonymousClass0Lw r5 = this.A04;
                C08020vl A002 = C08020vl.A00(r5.A81());
                C08230wD r4 = new C08230wD();
                r4.A06 = A002.A02;
                int i2 = A002.A00;
                r4.A00 = i2;
                r4.A03 = A002.A01;
                C08420wY r2 = new C08420wY();
                while (true) {
                    String A81 = r5.A81();
                    if (A81.length() == 0) {
                        break;
                    }
                    AbstractC08180w8.A00.A06(r2, A81);
                }
                r4.A05 = new AnonymousClass0wX(r2).A01();
                if (z && i2 == 100) {
                    return null;
                }
                this.A00 = 4;
                return r4;
            } catch (EOFException e) {
                StringBuilder sb = new StringBuilder("unexpected end of stream on ");
                sb.append(this.A03);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
        }
    }

    @Override // X.AbstractC08080vr
    public final void AAG(C08330wN r6) throws IOException {
        Proxy.Type type = this.A03.A01().A0D.A01.type();
        StringBuilder sb = new StringBuilder();
        sb.append(r6.A01);
        sb.append(' ');
        C08400wU r2 = r6.A03;
        if (r2.A03.equals("https") || type != Proxy.Type.HTTP) {
            sb.append(C08030vm.A00(r2));
        } else {
            sb.append(r2);
        }
        sb.append(" HTTP/1.1");
        A01(r6.A02, sb.toString());
    }

    @Override // X.AbstractC08080vr
    public final void cancel() {
        C01250Mm A012 = this.A03.A01();
        if (A012 != null) {
            C08160w5.A07(A012.A03);
        }
    }

    @Override // X.AbstractC08080vr
    public final void finishRequest() throws IOException {
        this.A01.flush();
    }

    public AnonymousClass0MU(AnonymousClass0N1 r2, C08090vs r3, AnonymousClass0Lw r4, AnonymousClass0Lx r5) {
        this.A02 = r2;
        this.A03 = r3;
        this.A04 = r4;
        this.A01 = r5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r3 != -1) goto L_0x0008;
     */
    @Override // X.AbstractC08080vr
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC08210wB A7E(X.C08220wC r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0MU.A7E(X.0wC):X.0wB");
    }
}
