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

/* renamed from: X.cr  reason: case insensitive filesystem */
public class C0324cr {
    public static final C0324cr A00;
    public static final Logger A01 = Logger.getLogger(LD.class.getName());

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

    public void A08(SSLSocket sSLSocket, String str, List<EnumC0364dl> list) {
    }

    public boolean A09(String str) {
        return true;
    }

    static {
        C0324cr crVar;
        C0325cs csVar;
        C0325cs csVar2;
        try {
            Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    crVar = new KX(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
                } catch (NoSuchMethodException unused3) {
                    try {
                        Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        crVar = new KW(cls.getMethod("put", SSLSocket.class, Class.forName(AnonymousClass06.A04("org.eclipse.jetty.alpn.ALPN", "$Provider"))), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName(AnonymousClass06.A04("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass06.A04("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        crVar = new C0324cr();
                    }
                }
            }
        }
        C0325cs csVar3 = new C0325cs(null, "setUseSessionTickets", Boolean.TYPE);
        C0325cs csVar4 = new C0325cs(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            csVar = new C0325cs(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            try {
                csVar2 = new C0325cs(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused5) {
            }
        } catch (ClassNotFoundException unused6) {
            csVar = null;
            csVar2 = null;
            crVar = new C0107Kd(csVar3, csVar4, csVar, csVar2);
            A00 = crVar;
        }
        crVar = new C0107Kd(csVar3, csVar4, csVar, csVar2);
        A00 = crVar;
    }

    public Object A01(String str) {
        if (A01.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void A05(String str, Object obj) {
        if (obj == null) {
            str = AnonymousClass06.A04(str, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        A04(5, str, (Throwable) obj);
    }

    public static List<String> A00(List<EnumC0364dl> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            EnumC0364dl dlVar = list.get(i);
            if (dlVar != EnumC0364dl.HTTP_1_0) {
                arrayList.add(dlVar.toString());
            }
        }
        return arrayList;
    }

    public cq A03(X509TrustManager x509TrustManager) {
        AbstractC0322cn cnVar;
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            cnVar = new KU(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            cnVar = new KP(x509TrustManager.getAcceptedIssuers());
        }
        return new KV(cnVar);
    }

    public void A06(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }
}
