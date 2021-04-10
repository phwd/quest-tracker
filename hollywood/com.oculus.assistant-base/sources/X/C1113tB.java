package X;

import java.lang.reflect.Method;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.tB  reason: case insensitive filesystem */
public final class C1113tB extends AbstractC0599ci {
    public final Method A00;
    public final X509TrustManager A01;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1113tB)) {
            return false;
        }
        C1113tB tBVar = (C1113tB) obj;
        return this.A01.equals(tBVar.A01) && this.A00.equals(tBVar.A00);
    }

    public final int hashCode() {
        return this.A01.hashCode() + (this.A00.hashCode() * 31);
    }

    public C1113tB(X509TrustManager x509TrustManager, Method method) {
        this.A00 = method;
        this.A01 = x509TrustManager;
    }
}
