package com.oculus.horizon.accountlinking.dropbox.interceptor;

import X.AbstractC08320wM;
import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.AnonymousClass0B3;
import X.C08220wC;
import X.C08330wN;
import X.C08340wO;
import X.C08370wR;
import com.facebook.ultralight.Dependencies;
import java.io.IOException;
import java.nio.charset.Charset;

@Dependencies({})
public class DropboxAPIInterceptor implements AbstractC08380wS {
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final Charset UTF8 = Charset.forName("UTF-8");

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r5) throws IOException {
        String str;
        C08330wN A8H = r5.A8H();
        AbstractC08320wM r0 = A8H.A04;
        AnonymousClass0B3 r2 = new AnonymousClass0B3();
        r0.A02(r2);
        Charset charset = UTF8;
        C08370wR A01 = r0.A01();
        if (!(A01 == null || (str = A01.A00) == null)) {
            charset = Charset.forName(str);
        }
        if (r2.A80(charset).equals("\"\"")) {
            C08340wO r22 = new C08340wO(A8H);
            r22.A03.A01("Content-Type");
            A8H = r22.A00();
        }
        return r5.A7Z(A8H);
    }
}
