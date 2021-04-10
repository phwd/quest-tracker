package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class KW extends C0324cr {
    public final Class<?> A00;
    public final Class<?> A01;
    public final Method A02;
    public final Method A03;
    public final Method A04;

    @Override // X.C0324cr
    public final String A02(SSLSocket sSLSocket) {
        try {
            C0326ct ctVar = (C0326ct) Proxy.getInvocationHandler(this.A02.invoke(null, sSLSocket));
            boolean z = ctVar.A01;
            if (!z && ctVar.A00 == null) {
                C0324cr.A00.A04(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            } else if (!z) {
                return ctVar.A00;
            } else {
                return null;
            }
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // X.C0324cr
    public final void A07(SSLSocket sSLSocket) {
        try {
            this.A04.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    public KW(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.A03 = method;
        this.A02 = method2;
        this.A04 = method3;
        this.A00 = cls;
        this.A01 = cls2;
    }

    @Override // X.C0324cr
    public final void A08(SSLSocket sSLSocket, String str, List<EnumC0364dl> list) {
        try {
            this.A03.invoke(null, sSLSocket, Proxy.newProxyInstance(C0324cr.class.getClassLoader(), new Class[]{this.A00, this.A01}, new C0326ct(C0324cr.A00(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }
}
