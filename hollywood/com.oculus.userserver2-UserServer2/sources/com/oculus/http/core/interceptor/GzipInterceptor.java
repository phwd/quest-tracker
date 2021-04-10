package com.oculus.http.core.interceptor;

import X.C00148h;
import X.C0050Ea;
import X.Dk;
import X.EU;
import X.XJ;
import X.XK;
import X.XL;
import X.XR;
import X.XS;
import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;

public class GzipInterceptor implements XS {
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        XR xr;
        XK A00 = eu.A00(eu.A01);
        XJ xj = A00.A0B;
        if (xj == null || !"gzip".equalsIgnoreCase(A00.A00(HEADER_CONTENT_ENCODING))) {
            return A00;
        }
        Dk dk = new Dk(xj.A01());
        String A002 = A00.A00(HttpRequestMultipart.CONTENT_TYPE);
        if (A002 != null) {
            xr = XR.A00(A002);
        } else {
            xr = null;
        }
        C0050Ea ea = new C0050Ea(xr, -1, new C00148h(dk));
        XL xl = new XL(A00);
        xl.A0B = ea;
        xl.A05.A01(HEADER_CONTENT_ENCODING);
        return xl.A00();
    }
}
