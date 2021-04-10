package X;

import com.oculus.config.updater.ConfigUpdaterDumperPlugin;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.0vM  reason: invalid class name and case insensitive filesystem */
public class C07790vM {
    public static final C07790vM A00;
    public static final Logger A01 = Logger.getLogger(AnonymousClass0N1.class.getName());

    public String A02(SSLSocket sSLSocket) {
        return null;
    }

    public void A04(int i, String str, Throwable th) {
        Level level;
        if (i == 5) {
            level = Level.WARNING;
        } else {
            level = Level.INFO;
        }
        A01.log(level, str, th);
    }

    public void A07(SSLSocket sSLSocket) {
    }

    public void A08(SSLSocket sSLSocket, String str, List<EnumC08350wP> list) {
    }

    public boolean A09(String str) {
        return true;
    }

    static {
        C07790vM r6;
        C07800vN r3;
        C07800vN r0;
        try {
            Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    r6 = new AnonymousClass0M6(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
                } catch (NoSuchMethodException unused3) {
                    try {
                        Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        Class<?> cls2 = Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$Provider"));
                        r6 = new AnonymousClass0M5(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod(ConfigUpdaterDumperPlugin.COMMAND_REMOVE, SSLSocket.class), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        r6 = new C07790vM();
                    }
                }
            }
        }
        C07800vN r5 = new C07800vN(null, "setUseSessionTickets", Boolean.TYPE);
        C07800vN r4 = new C07800vN(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            r3 = new C07800vN(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            try {
                r0 = new C07800vN(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused5) {
            }
        } catch (ClassNotFoundException unused6) {
            r3 = null;
            r0 = null;
            r6 = new AnonymousClass0M7(r5, r4, r3, r0);
            A00 = r6;
        }
        r6 = new AnonymousClass0M7(r5, r4, r3, r0);
        A00 = r6;
    }

    public Object A01(String str) {
        if (A01.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void A05(String str, Object obj) {
        if (obj == null) {
            str = AnonymousClass006.A05(str, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        A04(5, str, (Throwable) obj);
    }

    public static List<String> A00(List<EnumC08350wP> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            EnumC08350wP r1 = list.get(i);
            if (r1 != EnumC08350wP.HTTP_1_0) {
                arrayList.add(r1.toString());
            }
        }
        return arrayList;
    }

    public AbstractC07780vL A03(X509TrustManager x509TrustManager) {
        AbstractC07750vI r1;
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            r1 = new AnonymousClass0M2(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            r1 = new AnonymousClass0M1(x509TrustManager.getAcceptedIssuers());
        }
        return new AnonymousClass0M4(r1);
    }

    public void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }
}
