package com.oculus.http.core.interceptor;

import X.C0359dg;
import X.Cdo;
import X.L3;
import java.io.IOException;

public class NpeExceptionHandlerInterceptor implements Cdo {
    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        try {
            return l3.A00(l3.A01);
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        }
    }
}
