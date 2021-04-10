package com.oculus.http.core.interceptor;

import X.EU;
import X.XK;
import X.XO;
import X.XS;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RequestHeadersInterceptor implements XS {
    public final Map<String, String> requestHeaders;

    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        XO xo = new XO(eu.A01);
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            xo.A02(entry.getKey(), entry.getValue());
        }
        return eu.A00(xo.A00());
    }

    public RequestHeadersInterceptor(Map<String, String> map) {
        this.requestHeaders = map;
    }
}
