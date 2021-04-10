package com.oculus.http.core.interceptor;

import X.AbstractC0358df;
import X.AnonymousClass93;
import X.C0359dg;
import X.C0360dh;
import X.C0366dn;
import X.Cdo;
import X.K1;
import X.L3;
import X.L9;
import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;

public class GzipInterceptor implements Cdo {
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        C0366dn dnVar;
        C0359dg A00 = l3.A00(l3.A01);
        AbstractC0358df dfVar = A00.A0B;
        if (dfVar == null || !"gzip".equalsIgnoreCase(A00.A00(HEADER_CONTENT_ENCODING))) {
            return A00;
        }
        K1 k1 = new K1(dfVar.A02());
        String A002 = A00.A00(HttpRequestMultipart.CONTENT_TYPE);
        if (A002 != null) {
            dnVar = C0366dn.A00(A002);
        } else {
            dnVar = null;
        }
        L9 l9 = new L9(dnVar, -1, new AnonymousClass93(k1));
        C0360dh dhVar = new C0360dh(A00);
        dhVar.A0B = l9;
        dhVar.A05.A01(HEADER_CONTENT_ENCODING);
        return dhVar.A00();
    }
}
