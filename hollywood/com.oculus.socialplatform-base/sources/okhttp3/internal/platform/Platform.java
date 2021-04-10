package okhttp3.internal.platform;

import X.AnonymousClass006;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;

public class Platform {
    public static final int INFO = 4;
    public static final Platform PLATFORM = findPlatform();
    public static final int WARN = 5;
    public static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public boolean isCleartextTrafficPermitted(String str) {
        return true;
    }

    public void log(int i, String str, Throwable th) {
        Level level;
        if (i == 5) {
            level = Level.WARNING;
        } else {
            level = Level.INFO;
        }
        logger.log(level, str, th);
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        try {
            Object readFieldOrNull = readFieldOrNull(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (readFieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                String obj = protocol.toString();
                buffer.writeByte(obj.length());
                buffer.writeUtf8(obj);
            }
        }
        return buffer.readByteArray();
    }

    public Object getStackTraceForCloseable(String str) {
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void logCloseableLeak(String str, Object obj) {
        if (obj == null) {
            str = AnonymousClass006.A07(str, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        log(5, str, (Throwable) obj);
    }

    public static List<String> alpnProtocolNames(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public static Platform findPlatform() {
        Platform buildIfSupported = AndroidPlatform.buildIfSupported();
        if (buildIfSupported != null) {
            return buildIfSupported;
        }
        Jdk9Platform buildIfSupported2 = Jdk9Platform.buildIfSupported();
        if (buildIfSupported2 != null) {
            return buildIfSupported2;
        }
        Platform buildIfSupported3 = JdkWithJettyBootPlatform.buildIfSupported();
        if (buildIfSupported3 == null) {
            return new Platform();
        }
        return buildIfSupported3;
    }

    public static Platform get() {
        return PLATFORM;
    }

    public static <T> T readFieldOrNull(Object obj, Class<T> cls, String str) {
        Object readFieldOrNull;
        Class<?> cls2 = obj.getClass();
        while (true) {
            if (cls2 != Object.class) {
                try {
                    Field declaredField = cls2.getDeclaredField(str);
                    declaredField.setAccessible(true);
                    Object obj2 = declaredField.get(obj);
                    if (obj2 != null && cls.isInstance(obj2)) {
                        return cls.cast(obj2);
                    }
                } catch (NoSuchFieldException unused) {
                    cls2 = cls2.getSuperclass();
                } catch (IllegalAccessException unused2) {
                    throw new AssertionError();
                }
            } else if (!str.equals("delegate") && (readFieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) != null) {
                return (T) readFieldOrNull(readFieldOrNull, cls, str);
            }
        }
        return null;
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(TrustRootIndex.get(x509TrustManager));
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }
}
