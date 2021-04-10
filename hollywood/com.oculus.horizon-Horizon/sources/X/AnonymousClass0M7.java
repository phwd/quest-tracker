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

/* renamed from: X.0M7  reason: invalid class name */
public final class AnonymousClass0M7 extends C07790vM {
    public final Set<String> A00 = new HashSet();
    public final C07830vQ A01;
    public final C07800vN<Socket> A02;
    public final C07800vN<Socket> A03;
    public final C07800vN<Socket> A04;
    public final C07800vN<Socket> A05;

    @Override // X.C07790vM
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

    @Override // X.C07790vM
    public final void A08(SSLSocket sSLSocket, String str, List<EnumC08350wP> list) {
        if (str != null) {
            this.A05.A02(sSLSocket, true);
            this.A04.A02(sSLSocket, str);
        }
        C07800vN<Socket> r6 = this.A03;
        if (!(r6 == null || C07800vN.A00(r6, sSLSocket.getClass()) == null)) {
            Object[] objArr = new Object[1];
            AnonymousClass0B3 r4 = new AnonymousClass0B3();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                EnumC08350wP r1 = list.get(i);
                if (r1 != EnumC08350wP.HTTP_1_0) {
                    String obj = r1.toString();
                    r4.A09(obj.length());
                    r4.A0F(obj);
                }
            }
            objArr[0] = r4.A7r();
            r6.A01(sSLSocket, objArr);
        }
    }

    @Override // X.C07790vM
    public final Object A01(String str) {
        C07830vQ r5 = this.A01;
        Method method = r5.A00;
        if (method != null) {
            try {
                Object invoke = method.invoke(null, new Object[0]);
                r5.A01.invoke(invoke, str);
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // X.C07790vM
    public final String A02(SSLSocket sSLSocket) {
        byte[] bArr;
        C07800vN<Socket> r1 = this.A02;
        if (r1 == null || C07800vN.A00(r1, sSLSocket.getClass()) == null || (bArr = (byte[]) r1.A01(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, C08160w5.A04);
    }

    @Override // X.C07790vM
    public final AbstractC07780vL A03(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new AnonymousClass0M8(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.A03(x509TrustManager);
        }
    }

    @Override // X.C07790vM
    public final void A05(String str, Object obj) {
        C07830vQ r1 = this.A01;
        if (obj != null) {
            try {
                r1.A02.invoke(obj, new Object[0]);
                return;
            } catch (Exception unused) {
            }
        }
        A04(5, str, null);
    }

    @Override // X.C07790vM
    public final boolean A09(String str) {
        if (this.A00.contains(str)) {
            return true;
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(invoke, str)).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.A09(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/0vN<Ljava/net/Socket;>;LX/0vN<Ljava/net/Socket;>;LX/0vN<Ljava/net/Socket;>;LX/0vN<Ljava/net/Socket;>;)V */
    public AnonymousClass0M7(C07800vN r8, C07800vN r9, C07800vN r10, C07800vN r11) {
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
        this.A01 = new C07830vQ(method3, method, method2);
        this.A05 = r8;
        this.A04 = r9;
        this.A02 = r10;
        this.A03 = r11;
    }

    @Override // X.C07790vM
    public final void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (C08160w5.A08(e)) {
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
