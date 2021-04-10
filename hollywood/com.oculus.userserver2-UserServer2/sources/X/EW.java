package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.oculus.http.core.interceptor.GzipInterceptor;
import java.io.IOException;
import java.util.List;

public final class EW implements XS {
    public final AbstractC0176Xb A00;

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        long j;
        XR xr;
        XN xn = eu.A01;
        XO xo = new XO(xn);
        XM xm = xn.A04;
        if (xm != null) {
            boolean z = xm instanceof C0051Eb;
            if (!z && (xr = ((K3) xm).A00) != null) {
                xo.A02(HttpRequestMultipart.CONTENT_TYPE, xr.toString());
            }
            if (z) {
                j = (long) ((C0051Eb) xm).A00;
            } else if (!(xm instanceof K3)) {
                j = -1;
            } else {
                j = ((K3) xm).A01.length();
            }
            if (j != -1) {
                xo.A02("Content-Length", Long.toString(j));
                xo.A03.A01("Transfer-Encoding");
            } else {
                xo.A02("Transfer-Encoding", "chunked");
                xo.A03.A01("Content-Length");
            }
        }
        XW xw = xn.A02;
        boolean z2 = false;
        if (xw.A00("Host") == null) {
            xo.A02("Host", XD.A04(xn.A03, false));
        }
        if (xw.A00("Connection") == null) {
            xo.A02("Connection", "Keep-Alive");
        }
        if (xw.A00("Accept-Encoding") == null && xw.A00("Range") == null) {
            z2 = true;
            xo.A02("Accept-Encoding", "gzip");
        }
        AbstractC0176Xb xb = this.A00;
        XT xt = xn.A03;
        List<C0177Xc> A2I = xb.A2I(xt);
        if (!A2I.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = A2I.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                C0177Xc xc = A2I.get(i);
                sb.append(xc.A02);
                sb.append('=');
                sb.append(xc.A04);
            }
            xo.A02("Cookie", sb.toString());
        }
        if (xw.A00(HttpRequestMultipart.USER_AGENT) == null) {
            xo.A02(HttpRequestMultipart.USER_AGENT, "okhttp/3.6.0");
        }
        XK A002 = eu.A00(xo.A00());
        XW xw2 = A002.A06;
        C0171Ww.A02(xb, xt, xw2);
        XL xl = new XL(A002);
        xl.A07 = xn;
        if (z2 && "gzip".equalsIgnoreCase(A002.A00(GzipInterceptor.HEADER_CONTENT_ENCODING)) && C0171Ww.A03(A002)) {
            Dk dk = new Dk(A002.A0B.A01());
            XX A01 = xw2.A01();
            A01.A01(GzipInterceptor.HEADER_CONTENT_ENCODING);
            A01.A01("Content-Length");
            XW xw3 = new XW(A01);
            xl.A05 = xw3.A01();
            xl.A0B = new ET(xw3, new C00148h(dk));
        }
        return xl.A00();
    }

    public EW(AbstractC0176Xb xb) {
        this.A00 = xb;
    }
}
