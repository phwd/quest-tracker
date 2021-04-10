package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0Pa  reason: invalid class name */
public final class AnonymousClass0Pa implements AbstractC05850lW {
    public final AbstractC06150m3 A00;

    @Override // X.AbstractC05850lW
    public final C05660kD A5K(AnonymousClass0PX r15) throws IOException {
        C05700ke r2 = r15.A01;
        C05710kf r3 = new C05710kf(r2);
        AbstractC05690kc r4 = r2.A04;
        if (r4 != null) {
            C05820lT A01 = r4.A01();
            if (A01 != null) {
                r3.A02("Content-Type", A01.toString());
            }
            long A002 = r4.A00();
            if (A002 != -1) {
                r3.A02("Content-Length", Long.toString(A002));
                r3.A03.A01("Transfer-Encoding");
            } else {
                r3.A02("Transfer-Encoding", "chunked");
                r3.A03.A01("Content-Length");
            }
        }
        C06020lp r5 = r2.A02;
        boolean z = false;
        if (r5.A00("Host") == null) {
            r3.A02("Host", C05570jz.A04(r2.A03, false));
        }
        if (r5.A00("Connection") == null) {
            r3.A02("Connection", "Keep-Alive");
        }
        if (r5.A00("Accept-Encoding") == null && r5.A00("Range") == null) {
            z = true;
            r3.A02("Accept-Encoding", "gzip");
        }
        AbstractC06150m3 r9 = this.A00;
        C05890la r42 = r2.A03;
        List<AnonymousClass0m4> A5f = r9.A5f(r42);
        if (!A5f.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = A5f.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                AnonymousClass0m4 r13 = A5f.get(i);
                sb.append(r13.A02);
                sb.append('=');
                sb.append(r13.A04);
            }
            r3.A02("Cookie", sb.toString());
        }
        if (r5.A00("User-Agent") == null) {
            r3.A02("User-Agent", "okhttp/3.6.0");
        }
        C05660kD A003 = r15.A00(r3.A00());
        C06020lp r52 = A003.A06;
        AnonymousClass0iN.A02(r9, r42, r52);
        C05680ka r43 = new C05680ka(A003);
        r43.A07 = r2;
        if (z) {
            String A004 = r52.A00("Content-Encoding");
            if (A004 == null) {
                A004 = null;
            }
            if ("gzip".equalsIgnoreCase(A004) && AnonymousClass0iN.A03(A003)) {
                AnonymousClass0OV r32 = new AnonymousClass0OV(A003.A0B.A02());
                C06030lq A012 = r52.A01();
                A012.A01("Content-Encoding");
                A012.A01("Content-Length");
                C06020lp r22 = new C06020lp(A012);
                r43.A05 = r22.A01();
                r43.A0B = new AnonymousClass0PW(r22, new AnonymousClass0HO(r32));
            }
        }
        return r43.A00();
    }

    public AnonymousClass0Pa(AbstractC06150m3 r1) {
        this.A00 = r1;
    }
}
