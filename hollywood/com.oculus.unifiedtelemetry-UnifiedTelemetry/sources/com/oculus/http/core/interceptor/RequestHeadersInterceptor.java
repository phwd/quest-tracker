package com.oculus.http.core.interceptor;

import X.C0359dg;
import X.C0363dk;
import X.Cdo;
import X.L3;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RequestHeadersInterceptor implements Cdo {
    public final Map<String, String> requestHeaders;

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        C0363dk dkVar = new C0363dk(l3.A01);
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            dkVar.A02(entry.getKey(), entry.getValue());
        }
        return l3.A00(dkVar.A00());
    }

    public RequestHeadersInterceptor(Map<String, String> map) {
        this.requestHeaders = map;
    }
}
