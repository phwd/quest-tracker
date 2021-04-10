package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0Mh  reason: invalid class name and case insensitive filesystem */
public final class C01240Mh implements AbstractC08380wS {
    public final AbstractC08470wd A00;

    public C01240Mh(AbstractC08470wd r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r15) throws IOException {
        C08330wN A8H = r15.A8H();
        C08340wO r2 = new C08340wO(A8H);
        AbstractC08320wM r3 = A8H.A04;
        if (r3 != null) {
            C08370wR A01 = r3.A01();
            if (A01 != null) {
                r2.A02("Content-Type", A01.toString());
            }
            long A002 = r3.A00();
            if (A002 != -1) {
                r2.A02("Content-Length", Long.toString(A002));
                r2.A03.A01("Transfer-Encoding");
            } else {
                r2.A02("Transfer-Encoding", "chunked");
                r2.A03.A01("Content-Length");
            }
        }
        AnonymousClass0wX r32 = A8H.A02;
        boolean z = false;
        if (r32.A00("Host") == null) {
            r2.A02("Host", C08160w5.A04(A8H.A03, false));
        }
        if (r32.A00("Connection") == null) {
            r2.A02("Connection", "Keep-Alive");
        }
        if (r32.A00("Accept-Encoding") == null && r32.A00("Range") == null) {
            z = true;
            r2.A02("Accept-Encoding", "gzip");
        }
        AbstractC08470wd r9 = this.A00;
        C08400wU r4 = A8H.A03;
        List<C08480we> A5E = r9.A5E(r4);
        if (!A5E.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = A5E.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                C08480we r13 = A5E.get(i);
                sb.append(r13.A02);
                sb.append('=');
                sb.append(r13.A04);
            }
            r2.A02("Cookie", sb.toString());
        }
        if (r32.A00("User-Agent") == null) {
            r2.A02("User-Agent", "okhttp/3.6.0");
        }
        C08220wC A7Z = r15.A7Z(r2.A00());
        AnonymousClass0wX r22 = A7Z.A06;
        C08050vo.A02(r9, r4, r22);
        C08230wD r42 = new C08230wD(A7Z);
        r42.A07 = A8H;
        if (z && "gzip".equalsIgnoreCase(A7Z.A00("Content-Encoding")) && C08050vo.A03(A7Z)) {
            AnonymousClass0Lr r33 = new AnonymousClass0Lr(A7Z.A0B.A03());
            C08420wY A012 = r22.A01();
            A012.A01("Content-Encoding");
            A012.A01("Content-Length");
            AnonymousClass0wX r23 = new AnonymousClass0wX(A012);
            r42.A05 = r23.A01();
            r42.A0B = new C01210Me(r23, new C00560Au(r33));
        }
        return r42.A00();
    }
}
