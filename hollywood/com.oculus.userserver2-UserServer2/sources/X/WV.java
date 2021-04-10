package X;

import android.util.Log;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

public class WV {
    public static final Logger A00 = Logger.getLogger(AbstractC0054Ej.class.getName());
    public static final WV A01;

    public boolean A03(String str) {
        return true;
    }

    static {
        WV wv;
        WW ww;
        WW ww2;
        try {
            Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    wv = new E2(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
                } catch (NoSuchMethodException unused3) {
                    try {
                        Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        wv = new E1(cls.getMethod("put", SSLSocket.class, Class.forName(AnonymousClass06.A03("org.eclipse.jetty.alpn.ALPN", "$Provider"))), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName(AnonymousClass06.A03("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass06.A03("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        wv = new WV();
                    }
                }
            }
        }
        WW ww3 = new WW(null, "setUseSessionTickets", Boolean.TYPE);
        WW ww4 = new WW(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            ww = new WW(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            try {
                ww2 = new WW(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused5) {
            }
        } catch (ClassNotFoundException unused6) {
            ww = null;
            ww2 = null;
            wv = new E3(ww3, ww4, ww, ww2);
            A01 = wv;
        }
        wv = new E3(ww3, ww4, ww, ww2);
        A01 = wv;
    }

    public final void A02(int i, String str, Throwable th) {
        int min;
        Level level;
        if (!(this instanceof E3)) {
            if (i == 5) {
                level = Level.WARNING;
            } else {
                level = Level.INFO;
            }
            A00.log(level, str, th);
            return;
        }
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

    public static List<String> A00(List<XP> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            XP xp = list.get(i);
            if (xp != XP.HTTP_1_0) {
                arrayList.add(xp.toString());
            }
        }
        return arrayList;
    }

    public WU A01(X509TrustManager x509TrustManager) {
        WR wr;
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            wr = new Dz(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            wr = new Dy(x509TrustManager.getAcceptedIssuers());
        }
        return new E0(wr);
    }
}
