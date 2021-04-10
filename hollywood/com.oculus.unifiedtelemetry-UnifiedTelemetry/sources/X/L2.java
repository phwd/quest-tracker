package X;

import com.facebook.acra.util.HttpRequestMultipart;

public final class L2 extends AbstractC0358df {
    public final C0369ds A00;
    public final KC A01;

    @Override // X.AbstractC0358df
    public final long A00() {
        String A002 = this.A00.A00("Content-Length");
        if (A002 == null) {
            return -1;
        }
        try {
            return Long.parseLong(A002);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // X.AbstractC0358df
    public final C0366dn A01() {
        String A002 = this.A00.A00(HttpRequestMultipart.CONTENT_TYPE);
        if (A002 != null) {
            return C0366dn.A00(A002);
        }
        return null;
    }

    public L2(C0369ds dsVar, KC kc) {
        this.A00 = dsVar;
        this.A01 = kc;
    }

    @Override // X.AbstractC0358df
    public final KC A02() {
        return this.A01;
    }
}
