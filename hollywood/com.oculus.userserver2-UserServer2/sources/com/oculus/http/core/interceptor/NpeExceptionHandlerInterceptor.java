package com.oculus.http.core.interceptor;

import X.EU;
import X.XK;
import X.XS;
import java.io.IOException;

public class NpeExceptionHandlerInterceptor implements XS {
    @Override // X.XS
    public final XK A29(EU eu) throws IOException {
        try {
            return eu.A00(eu.A01);
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        }
    }
}
