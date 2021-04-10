package com.android.okhttp.internal;

import android.net.ssl.SSLSockets;
import com.android.okhttp.Protocol;
import com.android.okhttp.internal.tls.RealTrustRootIndex;
import com.android.okhttp.internal.tls.TrustRootIndex;
import com.android.okhttp.okio.Buffer;
import dalvik.system.SocketTagger;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class Platform {
    private static final OptionalMethod<Socket> GET_ALPN_SELECTED_PROTOCOL = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
    private static final AtomicReference<Platform> INSTANCE_HOLDER = new AtomicReference<>(new Platform());
    private static final OptionalMethod<Socket> SET_ALPN_PROTOCOLS = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
    private static final OptionalMethod<Socket> SET_HOSTNAME = new OptionalMethod<>(null, "setHostname", String.class);
    private static final OptionalMethod<Socket> SET_USE_SESSION_TICKETS = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);

    protected Platform() {
    }

    public static Platform get() {
        return INSTANCE_HOLDER.get();
    }

    public static Platform getAndSetForTest(Platform platform) {
        if (platform != null) {
            return INSTANCE_HOLDER.getAndSet(platform);
        }
        throw new NullPointerException();
    }

    public void logW(String warning) {
        System.logW(warning);
    }

    public void tagSocket(Socket socket) throws SocketException {
        SocketTagger.get().tag(socket);
    }

    public void untagSocket(Socket socket) throws SocketException {
        SocketTagger.get().untag(socket);
    }

    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        SSLParameters sslParams = sslSocket.getSSLParameters();
        if (hostname != null) {
            if (SSLSockets.isSupportedSocket(sslSocket)) {
                SSLSockets.setUseSessionTickets(sslSocket, true);
            } else {
                SET_USE_SESSION_TICKETS.invokeOptionalWithoutCheckedException(sslSocket, true);
            }
            try {
                sslParams.setServerNames(Collections.singletonList(new SNIHostName(hostname)));
            } catch (IllegalArgumentException e) {
            }
            if (!isPlatformSocket(sslSocket)) {
                SET_HOSTNAME.invokeOptionalWithoutCheckedException(sslSocket, hostname);
            }
        }
        sslParams.setApplicationProtocols(getProtocolIds(protocols));
        if (!isPlatformSocket(sslSocket) && SET_ALPN_PROTOCOLS.isSupported(sslSocket)) {
            SET_ALPN_PROTOCOLS.invokeWithoutCheckedException(sslSocket, concatLengthPrefixed(protocols));
        }
        sslSocket.setSSLParameters(sslParams);
    }

    public void afterHandshake(SSLSocket sslSocket) {
    }

    public String getSelectedProtocol(SSLSocket socket) {
        byte[] alpnResult;
        try {
            return socket.getApplicationProtocol();
        } catch (UnsupportedOperationException e) {
            if (GET_ALPN_SELECTED_PROTOCOL.isSupported(socket) && (alpnResult = (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket, new Object[0])) != null) {
                return new String(alpnResult, Util.UTF_8);
            }
            return null;
        }
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    public String getPrefix() {
        return "X-Android";
    }

    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        try {
            return (X509TrustManager) readFieldOrNull(readFieldOrNull(sslSocketFactory, Class.forName("com.android.org.conscrypt.SSLParametersImpl"), "sslParameters"), X509TrustManager.class, "x509TrustManager");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TrustRootIndex trustRootIndex(X509TrustManager trustManager) {
        return new RealTrustRootIndex(trustManager.getAcceptedIssuers());
    }

    private static <T> T readFieldOrNull(Object instance, Class<T> fieldType, String fieldName) {
        Object delegate;
        for (Class<?> c = instance.getClass(); c != Object.class; c = c.getSuperclass()) {
            try {
                Field field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(instance);
                if (value != null) {
                    if (fieldType.isInstance(value)) {
                        return fieldType.cast(value);
                    }
                }
                return null;
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            }
        }
        if (fieldName.equals("delegate") || (delegate = readFieldOrNull(instance, Object.class, "delegate")) == null) {
            return null;
        }
        return (T) readFieldOrNull(delegate, fieldType, fieldName);
    }

    private static boolean isPlatformSocket(SSLSocket socket) {
        return socket.getClass().getName().startsWith("com.android.org.conscrypt");
    }

    private static String[] getProtocolIds(List<Protocol> protocols) {
        String[] result = new String[protocols.size()];
        for (int i = 0; i < protocols.size(); i++) {
            result[i] = protocols.get(i).toString();
        }
        return result;
    }

    static byte[] concatLengthPrefixed(List<Protocol> protocols) {
        Buffer result = new Buffer();
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                result.writeByte(protocol.toString().length());
                result.writeUtf8(protocol.toString());
            }
        }
        return result.readByteArray();
    }
}
