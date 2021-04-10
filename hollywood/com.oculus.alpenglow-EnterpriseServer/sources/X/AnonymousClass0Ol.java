package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0Ol  reason: invalid class name */
public final class AnonymousClass0Ol extends C04670hG {
    public final Class<?> A00;
    public final Class<?> A01;
    public final Method A02;
    public final Method A03;
    public final Method A04;

    @Override // X.C04670hG
    public final String A02(SSLSocket sSLSocket) {
        try {
            C04690hI r2 = (C04690hI) Proxy.getInvocationHandler(this.A02.invoke(null, sSLSocket));
            boolean z = r2.A01;
            if (!z && r2.A00 == null) {
                C04670hG.A00.A04(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            } else if (!z) {
                return r2.A00;
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // X.C04670hG
    public final void A07(SSLSocket sSLSocket) {
        try {
            this.A04.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    public AnonymousClass0Ol(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.A03 = method;
        this.A02 = method2;
        this.A04 = method3;
        this.A00 = cls;
        this.A01 = cls2;
    }

    @Override // X.C04670hG
    public final void A08(SSLSocket sSLSocket, String str, List<AnonymousClass0kh> list) {
        try {
            this.A03.invoke(null, sSLSocket, Proxy.newProxyInstance(C04670hG.class.getClassLoader(), new Class[]{this.A00, this.A01}, new C04690hI(C04670hG.A00(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }
}
