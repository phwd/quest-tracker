package X;

import com.facebook.acra.util.HttpRequestMultipart;
import com.oculus.http.core.interceptor.GzipInterceptor;
import java.io.IOException;
import java.util.List;

public final class L5 implements Cdo {
    public final AbstractC0374dx A00;

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        C0362dj djVar = l3.A01;
        C0363dk dkVar = new C0363dk(djVar);
        AbstractC0361di diVar = djVar.A04;
        if (diVar != null) {
            C0366dn A01 = diVar.A01();
            if (A01 != null) {
                dkVar.A02(HttpRequestMultipart.CONTENT_TYPE, A01.toString());
            }
            long A002 = diVar.A00();
            if (A002 != -1) {
                dkVar.A02("Content-Length", Long.toString(A002));
                dkVar.A03.A01("Transfer-Encoding");
            } else {
                dkVar.A02("Transfer-Encoding", "chunked");
                dkVar.A03.A01("Content-Length");
            }
        }
        C0369ds dsVar = djVar.A02;
        boolean z = false;
        if (dsVar.A00("Host") == null) {
            dkVar.A02("Host", dZ.A04(djVar.A03, false));
        }
        if (dsVar.A00("Connection") == null) {
            dkVar.A02("Connection", "Keep-Alive");
        }
        if (dsVar.A00("Accept-Encoding") == null && dsVar.A00("Range") == null) {
            z = true;
            dkVar.A02("Accept-Encoding", "gzip");
        }
        AbstractC0374dx dxVar = this.A00;
        C0367dp dpVar = djVar.A03;
        List<C0375dy> A3O = dxVar.A3O(dpVar);
        if (!A3O.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = A3O.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append("; ");
                }
                C0375dy dyVar = A3O.get(i);
                sb.append(dyVar.A02);
                sb.append('=');
                sb.append(dyVar.A04);
            }
            dkVar.A02("Cookie", sb.toString());
        }
        if (dsVar.A00("User-Agent") == null) {
            dkVar.A02("User-Agent", "okhttp/3.6.0");
        }
        C0359dg A003 = l3.A00(dkVar.A00());
        C0369ds dsVar2 = A003.A06;
        dI.A02(dxVar, dpVar, dsVar2);
        C0360dh dhVar = new C0360dh(A003);
        dhVar.A07 = djVar;
        if (z && "gzip".equalsIgnoreCase(A003.A00(GzipInterceptor.HEADER_CONTENT_ENCODING)) && dI.A03(A003)) {
            K1 k1 = new K1(A003.A0B.A02());
            C0370dt A012 = dsVar2.A01();
            A012.A01(GzipInterceptor.HEADER_CONTENT_ENCODING);
            A012.A01("Content-Length");
            C0369ds dsVar3 = new C0369ds(A012);
            dhVar.A05 = dsVar3.A01();
            dhVar.A0B = new L2(dsVar3, new AnonymousClass93(k1));
        }
        return dhVar.A00();
    }

    public L5(AbstractC0374dx dxVar) {
        this.A00 = dxVar;
    }
}
