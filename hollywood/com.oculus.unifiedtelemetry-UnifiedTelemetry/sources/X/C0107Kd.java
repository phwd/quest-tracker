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

/* renamed from: X.Kd  reason: case insensitive filesystem */
public final class C0107Kd extends C0324cr {
    public final Set<String> A00 = new HashSet();
    public final C0327cu A01;
    public final C0325cs<Socket> A02;
    public final C0325cs<Socket> A03;
    public final C0325cs<Socket> A04;
    public final C0325cs<Socket> A05;

    @Override // X.C0324cr
    public final void A04(int i, String str, Throwable th) {
        int min;
        int i2 = 5;
        if (i != 5) {
            i2 = 3;
        }
        if (th != null) {
            str = AnonymousClass06.A00(str, '\n', Log.getStackTraceString(th));
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

    @Override // X.C0324cr
    public final void A08(SSLSocket sSLSocket, String str, List<EnumC0364dl> list) {
        if (str != null) {
            this.A05.A02(sSLSocket, true);
            this.A04.A02(sSLSocket, str);
        }
        C0325cs<Socket> csVar = this.A03;
        if (!(csVar == null || C0325cs.A00(csVar, sSLSocket.getClass()) == null)) {
            AnonymousClass98 r4 = new AnonymousClass98();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                EnumC0364dl dlVar = list.get(i);
                if (dlVar != EnumC0364dl.HTTP_1_0) {
                    String obj = dlVar.toString();
                    r4.A09(obj.length());
                    r4.A0G(obj);
                }
            }
            csVar.A01(sSLSocket, r4.A4S());
        }
    }

    @Override // X.C0324cr
    public final Object A01(String str) {
        C0327cu cuVar = this.A01;
        Method method = cuVar.A00;
        if (method != null) {
            try {
                Object invoke = method.invoke(null, new Object[0]);
                cuVar.A01.invoke(invoke, str);
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // X.C0324cr
    public final String A02(SSLSocket sSLSocket) {
        byte[] bArr;
        C0325cs<Socket> csVar = this.A02;
        if (csVar == null || C0325cs.A00(csVar, sSLSocket.getClass()) == null || (bArr = (byte[]) csVar.A01(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, dZ.A04);
    }

    @Override // X.C0324cr
    public final cq A03(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0108Ke(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.A03(x509TrustManager);
        }
    }

    @Override // X.C0324cr
    public final void A05(String str, Object obj) {
        C0327cu cuVar = this.A01;
        if (obj != null) {
            try {
                cuVar.A02.invoke(obj, new Object[0]);
                return;
            } catch (Exception unused) {
            }
        }
        A04(5, str, null);
    }

    @Override // X.C0324cr
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

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;LX/cs<Ljava/net/Socket;>;LX/cs<Ljava/net/Socket;>;LX/cs<Ljava/net/Socket;>;LX/cs<Ljava/net/Socket;>;)V */
    public C0107Kd(C0325cs csVar, C0325cs csVar2, C0325cs csVar3, C0325cs csVar4) {
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
        this.A01 = new C0327cu(method3, method, method2);
        this.A05 = csVar;
        this.A04 = csVar2;
        this.A02 = csVar3;
        this.A03 = csVar4;
    }

    @Override // X.C0324cr
    public final void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (dZ.A08(e)) {
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
