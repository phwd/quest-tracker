package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* renamed from: X.Ke  reason: case insensitive filesystem */
public final class C0108Ke extends cq {
    public final Object A00;
    public final Method A01;

    public final int hashCode() {
        return 0;
    }

    public C0108Ke(Object obj, Method method) {
        this.A00 = obj;
        this.A01 = method;
    }

    @Override // X.cq
    public final List<Certificate> A00(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
        try {
            return (List) this.A01.invoke(this.A00, list.toArray(new X509Certificate[list.size()]), "RSA", str);
        } catch (InvocationTargetException e) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
            sSLPeerUnverifiedException.initCause(e);
            throw sSLPeerUnverifiedException;
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    public final boolean equals(Object obj) {
        return obj instanceof C0108Ke;
    }
}
