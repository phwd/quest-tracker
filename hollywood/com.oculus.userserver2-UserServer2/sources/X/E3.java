package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.X509TrustManager;

public final class E3 extends WV {
    public final WY A00;
    public final WW<Socket> A01;
    public final WW<Socket> A02;
    public final WW<Socket> A03;
    public final WW<Socket> A04;
    public final Set<String> A05 = new HashSet();

    @Override // X.WV
    public final WU A01(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new E4(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.A01(x509TrustManager);
        }
    }

    @Override // X.WV
    public final boolean A03(String str) {
        if (this.A05.contains(str)) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.A03(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/WW<Ljava/net/Socket;>;LX/WW<Ljava/net/Socket;>;LX/WW<Ljava/net/Socket;>;LX/WW<Ljava/net/Socket;>;)V */
    public E3(WW ww, WW ww2, WW ww3, WW ww4) {
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls.getMethod("get", new Class[0]);
            method = cls.getMethod("open", String.class);
            method2 = cls.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method2 = method3;
            method = method3;
        }
        this.A00 = new WY(method3, method, method2);
        this.A04 = ww;
        this.A03 = ww2;
        this.A01 = ww3;
        this.A02 = ww4;
    }
}
