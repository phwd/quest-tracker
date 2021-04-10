package X;

import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;

/* renamed from: X.0PK  reason: invalid class name */
public final class AnonymousClass0PK implements AbstractC05270iw {
    public int A00 = 0;
    public final AnonymousClass0Oe A01;
    public final AnonymousClass0Qs A02;
    public final C05360jA A03;
    public final AnonymousClass0Od A04;

    public static final void A00(AnonymousClass0OW r2) {
        C04540gz r1 = r2.A00;
        C04540gz r0 = C04540gz.NONE;
        if (r0 != null) {
            r2.A00 = r0;
            r1.clearDeadline();
            r1.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final void A01(C06020lp r8, String str) throws IOException {
        int i = this.A00;
        if (i == 0) {
            AnonymousClass0Oe r6 = this.A01;
            r6.A9F(str);
            r6.A9F("\r\n");
            String[] strArr = r8.A00;
            int length = strArr.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 << 1;
                r6.A9F(strArr[i3]);
                r6.A9F(": ");
                r6.A9F(strArr[i3 + 1]);
                r6.A9F("\r\n");
            }
            r6.A9F("\r\n");
            this.A00 = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC05270iw
    public final AnonymousClass0h1 A21(C05700ke r4, long j) {
        int i;
        if ("chunked".equalsIgnoreCase(r4.A02.A00("Transfer-Encoding"))) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new AnonymousClass0PT(this);
            }
        } else if (j != -1) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new AnonymousClass0PN(this, j);
            }
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
    }

    @Override // X.AbstractC05270iw
    public final void A2q() throws IOException {
        this.A01.flush();
    }

    @Override // X.AbstractC05270iw
    public final C05680ka A7G(boolean z) throws IOException {
        int i = this.A00;
        if (i == 1 || i == 3) {
            try {
                AnonymousClass0Od r5 = this.A04;
                AnonymousClass0i1 A002 = AnonymousClass0i1.A00(r5.A7I());
                C05680ka r4 = new C05680ka();
                r4.A06 = A002.A02;
                int i2 = A002.A00;
                r4.A00 = i2;
                r4.A03 = A002.A01;
                C06030lq r2 = new C06030lq();
                while (true) {
                    String A7I = r5.A7I();
                    if (A7I.length() == 0) {
                        break;
                    }
                    AbstractC05620k9.A00.A06(r2, A7I);
                }
                r4.A05 = new C06020lp(r2).A01();
                if (z && i2 == 100) {
                    return null;
                }
                this.A00 = 4;
                return r4;
            } catch (EOFException e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.A03);
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A01("state: ", i));
        }
    }

    @Override // X.AbstractC05270iw
    public final void A9A(C05700ke r6) throws IOException {
        Proxy.Type type = this.A03.A01().A0D.A01.type();
        StringBuilder sb = new StringBuilder();
        sb.append(r6.A01);
        sb.append(' ');
        C05890la r2 = r6.A03;
        if (r2.A03.equals("https") || type != Proxy.Type.HTTP) {
            sb.append(AnonymousClass0i2.A00(r2));
        } else {
            sb.append(r2);
        }
        sb.append(" HTTP/1.1");
        A01(r6.A02, sb.toString());
    }

    @Override // X.AbstractC05270iw
    public final void cancel() {
        C01990Pw A012 = this.A03.A01();
        if (A012 != null) {
            C05570jz.A07(A012.A03);
        }
    }

    @Override // X.AbstractC05270iw
    public final void finishRequest() throws IOException {
        this.A01.flush();
    }

    public AnonymousClass0PK(AnonymousClass0Qs r2, C05360jA r3, AnonymousClass0Od r4, AnonymousClass0Oe r5) {
        this.A02 = r2;
        this.A03 = r3;
        this.A04 = r4;
        this.A01 = r5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        if (r3 != -1) goto L_0x0008;
     */
    @Override // X.AbstractC05270iw
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC05650kC A6k(X.C05660kD r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PK.A6k(X.0kD):X.0kC");
    }
}
