package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0M6  reason: invalid class name */
public final class AnonymousClass0M6 extends C07790vM {
    public final Method A00;
    public final Method A01;

    @Override // X.C07790vM
    public final String A02(SSLSocket sSLSocket) {
        try {
            String str = (String) this.A00.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    public AnonymousClass0M6(Method method, Method method2) {
        this.A01 = method;
        this.A00 = method2;
    }

    @Override // X.C07790vM
    public final void A08(SSLSocket sSLSocket, String str, List<EnumC08350wP> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> A002 = C07790vM.A00(list);
            this.A01.invoke(sSLParameters, A002.toArray(new String[A002.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }
}
