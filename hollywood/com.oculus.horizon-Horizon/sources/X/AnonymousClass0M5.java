package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0M5  reason: invalid class name */
public final class AnonymousClass0M5 extends C07790vM {
    public final Class<?> A00;
    public final Class<?> A01;
    public final Method A02;
    public final Method A03;
    public final Method A04;

    @Override // X.C07790vM
    public final String A02(SSLSocket sSLSocket) {
        try {
            C07810vO r2 = (C07810vO) Proxy.getInvocationHandler(this.A02.invoke(null, sSLSocket));
            boolean z = r2.A01;
            if (!z && r2.A00 == null) {
                C07790vM.A00.A04(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
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

    @Override // X.C07790vM
    public final void A07(SSLSocket sSLSocket) {
        try {
            this.A04.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    public AnonymousClass0M5(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.A03 = method;
        this.A02 = method2;
        this.A04 = method3;
        this.A00 = cls;
        this.A01 = cls2;
    }

    @Override // X.C07790vM
    public final void A08(SSLSocket sSLSocket, String str, List<EnumC08350wP> list) {
        List<String> A002 = C07790vM.A00(list);
        try {
            Object newProxyInstance = Proxy.newProxyInstance(C07790vM.class.getClassLoader(), new Class[]{this.A00, this.A01}, new C07810vO(A002));
            this.A03.invoke(null, sSLSocket, newProxyInstance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }
}
