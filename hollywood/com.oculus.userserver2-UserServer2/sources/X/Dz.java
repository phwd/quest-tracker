package X;

import java.lang.reflect.Method;
import javax.net.ssl.X509TrustManager;

public final class Dz extends WR {
    public final Method A00;
    public final X509TrustManager A01;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Dz)) {
            return false;
        }
        Dz dz = (Dz) obj;
        return this.A01.equals(dz.A01) && this.A00.equals(dz.A00);
    }

    public final int hashCode() {
        return this.A01.hashCode() + (this.A00.hashCode() * 31);
    }

    public Dz(X509TrustManager x509TrustManager, Method method) {
        this.A00 = method;
        this.A01 = x509TrustManager;
    }
}
