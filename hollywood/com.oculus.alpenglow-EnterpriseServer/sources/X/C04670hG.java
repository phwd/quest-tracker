package X;

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

/* renamed from: X.0hG  reason: invalid class name and case insensitive filesystem */
public class C04670hG {
    public static final C04670hG A00;
    public static final Logger A01 = Logger.getLogger(AnonymousClass0Qs.class.getName());

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

    public void A08(SSLSocket sSLSocket, String str, List<AnonymousClass0kh> list) {
    }

    public boolean A09(String str) {
        return true;
    }

    static {
        C04670hG r6;
        C04680hH r3;
        C04680hH r0;
        try {
            Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    r6 = new AnonymousClass0Om(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
                } catch (NoSuchMethodException unused3) {
                    try {
                        Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        r6 = new AnonymousClass0Ol(cls.getMethod("put", SSLSocket.class, Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$Provider"))), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        r6 = new C04670hG();
                    }
                }
            }
        }
        C04680hH r5 = new C04680hH(null, "setUseSessionTickets", Boolean.TYPE);
        C04680hH r4 = new C04680hH(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            r3 = new C04680hH(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            try {
                r0 = new C04680hH(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused5) {
            }
        } catch (ClassNotFoundException unused6) {
            r3 = null;
            r0 = null;
            r6 = new AnonymousClass0On(r5, r4, r3, r0);
            A00 = r6;
        }
        r6 = new AnonymousClass0On(r5, r4, r3, r0);
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

    public static List<String> A00(List<AnonymousClass0kh> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass0kh r1 = list.get(i);
            if (r1 != AnonymousClass0kh.HTTP_1_0) {
                arrayList.add(r1.toString());
            }
        }
        return arrayList;
    }

    public AbstractC04660hF A03(X509TrustManager x509TrustManager) {
        AnonymousClass0hC r1;
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            r1 = new AnonymousClass0Oj(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            r1 = new AnonymousClass0Oi(x509TrustManager.getAcceptedIssuers());
        }
        return new AnonymousClass0Ok(r1);
    }

    public void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }
}
