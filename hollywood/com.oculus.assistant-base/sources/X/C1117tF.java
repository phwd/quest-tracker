package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.tF  reason: case insensitive filesystem */
public final class C1117tF extends C0595ce {
    public final C0592cb A00;
    public final C0594cd A01;
    public final C0594cd A02;
    public final C0594cd A03;
    public final C0594cd A04;
    public final Set A05 = new HashSet();

    @Override // X.C0595ce
    public final AbstractC0596cf A01(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C1118tG(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.A01(x509TrustManager);
        }
    }

    @Override // X.C0595ce
    public final boolean A03(String str) {
        if (this.A05.contains(str)) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(invoke, str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.A03(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    public C1117tF(C0594cd cdVar, C0594cd cdVar2, C0594cd cdVar3, C0594cd cdVar4) {
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
        this.A00 = new C0592cb(method3, method, method2);
        this.A04 = cdVar;
        this.A03 = cdVar2;
        this.A01 = cdVar3;
        this.A02 = cdVar4;
    }
}
