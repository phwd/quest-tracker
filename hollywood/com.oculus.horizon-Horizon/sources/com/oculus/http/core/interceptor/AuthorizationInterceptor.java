package com.oculus.http.core.interceptor;

import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.C08220wC;
import X.C08330wN;
import X.C08340wO;
import java.io.IOException;
import javax.annotation.Nullable;

public abstract class AuthorizationInterceptor implements AbstractC08380wS {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;

    @Nullable
    public abstract C08340wO A03(C08330wN v) throws IOException;

    public abstract void A04(C08220wC v);

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r4) throws IOException {
        C08340wO A03;
        C08330wN A8H = r4.A8H();
        if (A8H.A02.A00("Authorization") == null && (A03 = A03(A8H)) != null) {
            A8H = A03.A00();
        }
        C08220wC A7Z = r4.A7Z(A8H);
        if (A7Z.A01 == 401) {
            A04(A7Z);
        }
        return A7Z;
    }
}
