package com.oculus.http.core.interceptor;

import X.AbstractC08210wB;
import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.AnonymousClass0Lr;
import X.AnonymousClass0Mx;
import X.C00560Au;
import X.C08220wC;
import X.C08230wD;
import X.C08370wR;
import java.io.IOException;

public class GzipInterceptor implements AbstractC08380wS {
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r8) throws IOException {
        C08370wR r4;
        C08220wC A7Z = r8.A7Z(r8.A8H());
        AbstractC08210wB r2 = A7Z.A0B;
        if (r2 == null || !"gzip".equalsIgnoreCase(A7Z.A00("Content-Encoding"))) {
            return A7Z;
        }
        AnonymousClass0Lr r1 = new AnonymousClass0Lr(r2.A03());
        String A00 = A7Z.A00("Content-Type");
        if (A00 != null) {
            r4 = C08370wR.A00(A00);
        } else {
            r4 = null;
        }
        AnonymousClass0Mx r0 = new AnonymousClass0Mx(r4, -1, new C00560Au(r1));
        C08230wD r12 = new C08230wD(A7Z);
        r12.A0B = r0;
        r12.A05.A01("Content-Encoding");
        return r12.A00();
    }
}
