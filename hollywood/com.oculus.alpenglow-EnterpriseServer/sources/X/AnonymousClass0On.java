package X;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.0On  reason: invalid class name */
public final class AnonymousClass0On extends C04670hG {
    public final Set<String> A00 = new HashSet();
    public final C04700hJ A01;
    public final C04680hH<Socket> A02;
    public final C04680hH<Socket> A03;
    public final C04680hH<Socket> A04;
    public final C04680hH<Socket> A05;

    @Override // X.C04670hG
    public final void A04(int i, String str, Throwable th) {
        int min;
        int i2 = 5;
        if (i != 5) {
            i2 = 3;
        }
        if (th != null) {
            str = AnonymousClass006.A00(str, '\n', Log.getStackTraceString(th));
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            do {
                min = Math.min(indexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                i3 = min;
            } while (min < indexOf);
            i3 = min + 1;
        }
    }

    @Override // X.C04670hG
    public final void A08(SSLSocket sSLSocket, String str, List<AnonymousClass0kh> list) {
        if (str != null) {
            this.A05.A02(sSLSocket, true);
            this.A04.A02(sSLSocket, str);
        }
        C04680hH<Socket> r5 = this.A03;
        if (!(r5 == null || C04680hH.A00(r5, sSLSocket.getClass()) == null)) {
            AnonymousClass0HR r4 = new AnonymousClass0HR();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AnonymousClass0kh r1 = list.get(i);
                if (r1 != AnonymousClass0kh.HTTP_1_0) {
                    String r12 = r1.toString();
                    r4.A09(r12.length());
                    r4.A0F(r12);
                }
            }
            r5.A01(sSLSocket, r4.A79());
        }
    }

    @Override // X.C04670hG
    public final Object A01(String str) {
        C04700hJ r4 = this.A01;
        Method method = r4.A00;
        if (method != null) {
            try {
                Object invoke = method.invoke(null, new Object[0]);
                r4.A01.invoke(invoke, str);
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // X.C04670hG
    public final String A02(SSLSocket sSLSocket) {
        byte[] bArr;
        C04680hH<Socket> r1 = this.A02;
        if (r1 == null || C04680hH.A00(r1, sSLSocket.getClass()) == null || (bArr = (byte[]) r1.A01(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, C05570jz.A00);
    }

    @Override // X.C04670hG
    public final AbstractC04660hF A03(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new AnonymousClass0Oo(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.A03(x509TrustManager);
        }
    }

    @Override // X.C04670hG
    public final void A05(String str, Object obj) {
        C04700hJ r1 = this.A01;
        if (obj != null) {
            try {
                r1.A02.invoke(obj, new Object[0]);
                return;
            } catch (Exception unused) {
            }
        }
        A04(5, str, null);
    }

    @Override // X.C04670hG
    public final boolean A09(String str) {
        if (this.A00.contains(str)) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.A09(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/0hH<Ljava/net/Socket;>;LX/0hH<Ljava/net/Socket;>;LX/0hH<Ljava/net/Socket;>;LX/0hH<Ljava/net/Socket;>;)V */
    public AnonymousClass0On(C04680hH r8, C04680hH r9, C04680hH r10, C04680hH r11) {
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
        this.A01 = new C04700hJ(method3, method, method2);
        this.A05 = r8;
        this.A04 = r9;
        this.A02 = r10;
        this.A03 = r11;
    }

    @Override // X.C04670hG
    public final void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (C05570jz.A08(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (SecurityException e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }
}
