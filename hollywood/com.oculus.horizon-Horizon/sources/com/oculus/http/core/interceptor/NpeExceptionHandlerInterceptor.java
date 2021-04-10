package com.oculus.http.core.interceptor;

import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.C08220wC;
import java.io.IOException;

public class NpeExceptionHandlerInterceptor implements AbstractC08380wS {
    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r3) throws IOException {
        try {
            return r3.A7Z(r3.A8H());
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        }
    }
}
