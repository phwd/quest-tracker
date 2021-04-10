package com.android.okhttp.internal;

import android.net.ssl.SSLSockets;
import com.android.okhttp.Protocol;
import com.android.okhttp.internal.tls.RealTrustRootIndex;
import com.android.okhttp.internal.tls.TrustRootIndex;
import com.android.okhttp.okio.Buffer;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class Platform {
    private static final OptionalMethod GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
    private static final AtomicReference INSTANCE_HOLDER = new AtomicReference(new Platform());
    private static final OptionalMethod SET_ALPN_PROTOCOLS = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
    private static final OptionalMethod SET_HOSTNAME = new OptionalMethod(null, "setHostname", String.class);
    private static final OptionalMethod SET_USE_SESSION_TICKETS = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public String getPrefix() {
        return "X-Android";
    }

    protected Platform() {
    }

    public static Platform get() {
        return (Platform) INSTANCE_HOLDER.get();
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List list) {
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        if (str != null) {
            if (SSLSockets.isSupportedSocket(sSLSocket)) {
                SSLSockets.setUseSessionTickets(sSLSocket, true);
            } else {
                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sSLSocket, true);
            }
            try {
                sSLParameters.setServerNames(Collections.singletonList(new SNIHostName(str)));
            } catch (IllegalArgumentException unused) {
            }
            if (!isPlatformSocket(sSLSocket)) {
                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
        }
        sSLParameters.setApplicationProtocols(getProtocolIds(list));
        if (!isPlatformSocket(sSLSocket) && SET_ALPN_PROTOCOLS.isSupported(sSLSocket)) {
            SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sSLSocket, concatLengthPrefixed(list));
        }
        sSLSocket.setSSLParameters(sSLParameters);
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        byte[] bArr;
        try {
            return sSLSocket.getApplicationProtocol();
        } catch (UnsupportedOperationException unused) {
            if (!GET_ALPN_SELECTED_PROTOCOL.isSupported(sSLSocket) || (bArr = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(sSLSocket, new Object[0])) == null) {
                return null;
            }
            new String(bArr, Util.UTF_8);
            throw null;
        }
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        socket.connect(inetSocketAddress, i);
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        try {
            return (X509TrustManager) readFieldOrNull(readFieldOrNull(sSLSocketFactory, Class.forName("com.android.org.conscrypt.SSLParametersImpl"), "sslParameters"), X509TrustManager.class, "x509TrustManager");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TrustRootIndex trustRootIndex(X509TrustManager x509TrustManager) {
        return new RealTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }

    private static Object readFieldOrNull(Object obj, Class cls, String str) {
        Object readFieldOrNull;
        for (Class cls2 = obj.getClass(); cls2 != Object.class; cls2 = cls2.getSuperclass()) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null) {
                    if (cls.isInstance(obj2)) {
                        cls.cast(obj2);
                        return obj2;
                    }
                }
                return null;
            } catch (NoSuchFieldException unused) {
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            }
        }
        if (str.equals("delegate") || (readFieldOrNull = readFieldOrNull(obj, Object.class, "delegate")) == null) {
            return null;
        }
        return readFieldOrNull(readFieldOrNull, cls, str);
    }

    private static boolean isPlatformSocket(SSLSocket sSLSocket) {
        return sSLSocket.getClass().getName().startsWith("com.android.org.conscrypt");
    }

    private static String[] getProtocolIds(List list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = ((Protocol) list.get(i)).toString();
        }
        return strArr;
    }

    static byte[] concatLengthPrefixed(List list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }
}
