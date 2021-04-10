package X;

import android.util.Log;
import com.facebook.acra.anr.processmonitor.detector.ProcessErrorMonitorANRDetector;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.ce  reason: case insensitive filesystem */
public class C0595ce {
    public static final Logger A00 = Logger.getLogger(C0548bl.class.getName());
    public static final C0595ce A01;

    public boolean A03(String str) {
        return true;
    }

    static {
        C0595ce ceVar;
        C0594cd cdVar;
        C0594cd cdVar2;
        try {
            Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    ceVar = new C1116tE(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
                } catch (NoSuchMethodException unused3) {
                    try {
                        Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        Class<?> cls2 = Class.forName(AnonymousClass08.A04("org.eclipse.jetty.alpn.ALPN", "$Provider"));
                        ceVar = new C1115tD(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName(AnonymousClass08.A04("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass08.A04("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        ceVar = new C0595ce();
                    }
                }
            }
        }
        C0594cd cdVar3 = new C0594cd(null, "setUseSessionTickets", Boolean.TYPE);
        C0594cd cdVar4 = new C0594cd(null, "setHostname", String.class);
        try {
            Class.forName("android.net.Network");
            cdVar = new C0594cd(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            try {
                cdVar2 = new C0594cd(null, "setAlpnProtocols", byte[].class);
            } catch (ClassNotFoundException unused5) {
            }
        } catch (ClassNotFoundException unused6) {
            cdVar = null;
            cdVar2 = null;
            ceVar = new C1117tF(cdVar3, cdVar4, cdVar, cdVar2);
            A01 = ceVar;
        }
        ceVar = new C1117tF(cdVar3, cdVar4, cdVar, cdVar2);
        A01 = ceVar;
    }

    public final void A02(int i, String str, Throwable th) {
        int min;
        Level level;
        if (!(this instanceof C1117tF)) {
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
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('\n');
            sb.append(Log.getStackTraceString(th));
            str = sb.toString();
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            do {
                min = Math.min(indexOf, i3 + ProcessErrorMonitorANRDetector.START_DELAY_MS);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                i3 = min;
            } while (min < indexOf);
            i3 = min + 1;
        }
    }

    public static List A00(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj != EnumC0549bm.HTTP_1_0) {
                arrayList.add(obj.toString());
            }
        }
        return arrayList;
    }

    public AbstractC0596cf A01(X509TrustManager x509TrustManager) {
        AbstractC0599ci ciVar;
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            ciVar = new C1113tB(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            ciVar = new C1112tA(x509TrustManager.getAcceptedIssuers());
        }
        return new C1114tC(ciVar);
    }
}
