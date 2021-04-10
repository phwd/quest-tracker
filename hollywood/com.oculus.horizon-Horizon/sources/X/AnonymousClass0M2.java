package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.0M2  reason: invalid class name */
public final class AnonymousClass0M2 extends AbstractC07750vI {
    public final Method A00;
    public final X509TrustManager A01;

    @Override // X.AbstractC07750vI
    public final X509Certificate A00(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.A00.invoke(this.A01, x509Certificate);
            if (trustAnchor != null) {
                return trustAnchor.getTrustedCert();
            }
            return null;
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        } catch (InvocationTargetException unused2) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0M2)) {
            return false;
        }
        AnonymousClass0M2 r4 = (AnonymousClass0M2) obj;
        return this.A01.equals(r4.A01) && this.A00.equals(r4.A00);
    }

    public final int hashCode() {
        return this.A01.hashCode() + (this.A00.hashCode() * 31);
    }

    public AnonymousClass0M2(X509TrustManager x509TrustManager, Method method) {
        this.A00 = method;
        this.A01 = x509TrustManager;
    }
}
