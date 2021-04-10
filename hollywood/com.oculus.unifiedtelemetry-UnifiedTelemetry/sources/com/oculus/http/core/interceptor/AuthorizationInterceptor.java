package com.oculus.http.core.interceptor;

import X.C0359dg;
import X.C0362dj;
import X.C0363dk;
import X.Cdo;
import X.L3;
import java.io.IOException;
import javax.annotation.Nullable;

public abstract class AuthorizationInterceptor implements Cdo {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;

    @Nullable
    public abstract C0363dk A03(C0362dj djVar) throws IOException;

    public abstract void A04(C0359dg dgVar);

    @Override // X.Cdo
    public final C0359dg A38(L3 l3) throws IOException {
        C0363dk A03;
        C0362dj djVar = l3.A01;
        if (djVar.A02.A00("Authorization") == null && (A03 = A03(djVar)) != null) {
            djVar = A03.A00();
        }
        C0359dg A00 = l3.A00(djVar);
        if (A00.A01 == 401) {
            A04(A00);
        }
        return A00;
    }
}
