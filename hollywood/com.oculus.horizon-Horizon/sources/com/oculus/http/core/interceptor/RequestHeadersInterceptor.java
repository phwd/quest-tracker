package com.oculus.http.core.interceptor;

import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.C08220wC;
import X.C08340wO;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RequestHeadersInterceptor implements AbstractC08380wS {
    public final Map<String, String> requestHeaders;

    public RequestHeadersInterceptor(Map<String, String> map) {
        this.requestHeaders = map;
    }

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r5) throws IOException {
        C08340wO r3 = new C08340wO(r5.A8H());
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            r3.A02(entry.getKey(), entry.getValue());
        }
        return r5.A7Z(r3.A00());
    }
}
