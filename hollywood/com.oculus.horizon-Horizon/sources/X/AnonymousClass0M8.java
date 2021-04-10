package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* renamed from: X.0M8  reason: invalid class name */
public final class AnonymousClass0M8 extends AbstractC07780vL {
    public final Object A00;
    public final Method A01;

    public final int hashCode() {
        return 0;
    }

    public AnonymousClass0M8(Object obj, Method method) {
        this.A00 = obj;
        this.A01 = method;
    }

    @Override // X.AbstractC07780vL
    public final List<Certificate> A00(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
        try {
            Object[] array = list.toArray(new X509Certificate[list.size()]);
            return (List) this.A01.invoke(this.A00, array, "RSA", str);
        } catch (InvocationTargetException e) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
            sSLPeerUnverifiedException.initCause(e);
            throw sSLPeerUnverifiedException;
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    public final boolean equals(Object obj) {
        return obj instanceof AnonymousClass0M8;
    }
}
