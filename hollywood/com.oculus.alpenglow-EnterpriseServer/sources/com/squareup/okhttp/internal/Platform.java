package com.squareup.okhttp.internal;

import X.AnonymousClass006;
import X.AnonymousClass0HR;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.tls.AndroidTrustRootIndex;
import com.squareup.okhttp.internal.tls.RealTrustRootIndex;
import com.squareup.okhttp.internal.tls.TrustRootIndex;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class Platform {
    public static final Platform PLATFORM = findPlatform();

    public static class Android extends Platform {
        public static final int MAX_LOG_LENGTH = 4000;
        public final OptionalMethod<Socket> getAlpnSelectedProtocol;
        public final OptionalMethod<Socket> setAlpnProtocols;
        public final OptionalMethod<Socket> setHostname;
        public final OptionalMethod<Socket> setUseSessionTickets;
        public final Class<?> sslParametersClass;
        public final Method trafficStatsTagSocket;
        public final Method trafficStatsUntagSocket;

        @Override // com.squareup.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            if (str != null) {
                this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sSLSocket, true);
                this.setHostname.invokeOptionalWithoutCheckedException(sSLSocket, str);
            }
            OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
            if (optionalMethod != null && optionalMethod.isSupported(sSLSocket)) {
                this.setAlpnProtocols.invokeWithoutCheckedException(sSLSocket, Platform.concatLengthPrefixed(list));
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket sSLSocket) {
            byte[] bArr;
            OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
            if (optionalMethod == null || !optionalMethod.isSupported(sSLSocket) || (bArr = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(sSLSocket, new Object[0])) == null) {
                return null;
            }
            return new String(bArr, Util.UTF_8);
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void tagSocket(Socket socket) throws SocketException {
            Throwable e;
            Method method = this.trafficStatsTagSocket;
            if (method != null) {
                try {
                    method.invoke(null, socket);
                } catch (IllegalAccessException e2) {
                    e = e2;
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e3) {
                    e = e3.getCause();
                    throw new RuntimeException(e);
                }
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
            Object readFieldOrNull = Platform.readFieldOrNull(sSLSocketFactory, this.sslParametersClass, "sslParameters");
            if (readFieldOrNull == null) {
                try {
                    readFieldOrNull = Platform.readFieldOrNull(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
                } catch (ClassNotFoundException unused) {
                    return null;
                }
            }
            X509TrustManager x509TrustManager = (X509TrustManager) Platform.readFieldOrNull(readFieldOrNull, X509TrustManager.class, "x509TrustManager");
            if (x509TrustManager == null) {
                return (X509TrustManager) Platform.readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
            }
            return x509TrustManager;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void untagSocket(Socket socket) throws SocketException {
            Throwable e;
            Method method = this.trafficStatsUntagSocket;
            if (method != null) {
                try {
                    method.invoke(null, socket);
                } catch (IllegalAccessException e2) {
                    e = e2;
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e3) {
                    e = e3.getCause();
                    throw new RuntimeException(e);
                }
            }
        }

        public Android(Class<?> cls, OptionalMethod<Socket> optionalMethod, OptionalMethod<Socket> optionalMethod2, Method method, Method method2, OptionalMethod<Socket> optionalMethod3, OptionalMethod<Socket> optionalMethod4) {
            this.sslParametersClass = cls;
            this.setUseSessionTickets = optionalMethod;
            this.setHostname = optionalMethod2;
            this.trafficStatsTagSocket = method;
            this.trafficStatsUntagSocket = method2;
            this.getAlpnSelectedProtocol = optionalMethod3;
            this.setAlpnProtocols = optionalMethod4;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
            try {
                socket.connect(inetSocketAddress, i);
            } catch (AssertionError e) {
                if (Util.isAndroidGetsocknameError(e)) {
                    throw new IOException(e);
                }
                throw e;
            } catch (SecurityException e2) {
                IOException iOException = new IOException("Exception in connect");
                iOException.initCause(e2);
                throw iOException;
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void log(String str) {
            int min;
            int length = str.length();
            int i = 0;
            while (i < length) {
                int indexOf = str.indexOf(10, i);
                if (indexOf == -1) {
                    indexOf = length;
                }
                do {
                    min = Math.min(indexOf, i + 4000);
                    str.substring(i, min);
                    i = min;
                } while (min < indexOf);
                i = min + 1;
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public TrustRootIndex trustRootIndex(X509TrustManager x509TrustManager) {
            TrustRootIndex trustRootIndex = AndroidTrustRootIndex.get(x509TrustManager);
            if (trustRootIndex == null) {
                return Platform.super.trustRootIndex(x509TrustManager);
            }
            return trustRootIndex;
        }
    }

    public static class JdkPlatform extends Platform {
        public final Class<?> sslContextClass;

        @Override // com.squareup.okhttp.internal.Platform
        public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
            Object readFieldOrNull = Platform.readFieldOrNull(sSLSocketFactory, this.sslContextClass, "context");
            if (readFieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) Platform.readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
        }

        public JdkPlatform(Class<?> cls) {
            this.sslContextClass = cls;
        }
    }

    public static class JdkWithJettyBootPlatform extends JdkPlatform {
        public final Class<?> clientProviderClass;
        public final Method getMethod;
        public final Method putMethod;
        public final Method removeMethod;
        public final Class<?> serverProviderClass;

        @Override // com.squareup.okhttp.internal.Platform
        public void afterHandshake(SSLSocket sSLSocket) {
            try {
                this.removeMethod.invoke(null, sSLSocket);
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }

        @Override // com.squareup.okhttp.internal.Platform
        public String getSelectedProtocol(SSLSocket sSLSocket) {
            try {
                JettyNegoProvider jettyNegoProvider = (JettyNegoProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, sSLSocket));
                boolean z = jettyNegoProvider.unsupported;
                if (!z && jettyNegoProvider.selected == null) {
                    Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                } else if (!z) {
                    return jettyNegoProvider.selected;
                } else {
                    return null;
                }
            } catch (IllegalAccessException | InvocationTargetException unused) {
                throw new AssertionError();
            }
        }

        public JdkWithJettyBootPlatform(Class<?> cls, Method method, Method method2, Method method3, Class<?> cls2, Class<?> cls3) {
            super(cls);
            this.putMethod = method;
            this.getMethod = method2;
            this.removeMethod = method3;
            this.clientProviderClass = cls2;
            this.serverProviderClass = cls3;
        }

        @Override // com.squareup.okhttp.internal.Platform
        public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Protocol protocol = list.get(i);
                if (protocol != Protocol.HTTP_1_0) {
                    arrayList.add(protocol.toString());
                }
            }
            try {
                this.putMethod.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNegoProvider(arrayList)));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new AssertionError(e);
            }
        }
    }

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

    public void tagSocket(Socket socket) throws SocketException {
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        return null;
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public static class JettyNegoProvider implements InvocationHandler {
        public final List<String> protocols;
        public String selected;
        public boolean unsupported;

        public JettyNegoProvider(List<String> list) {
            this.protocols = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Object obj2;
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = Util.EMPTY_STRING_ARRAY;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (name.equals("protocols") && objArr.length == 0) {
                return this.protocols;
            } else {
                if ((name.equals("selectProtocol") || name.equals("select")) && String.class == returnType && objArr.length == 1) {
                    Object obj3 = objArr[0];
                    if (obj3 instanceof List) {
                        List list = (List) obj3;
                        int size = list.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                obj2 = this.protocols.get(0);
                                break;
                            } else if (this.protocols.contains(list.get(i))) {
                                obj2 = list.get(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        String str = (String) obj2;
                        this.selected = str;
                        return str;
                    }
                }
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.selected = (String) objArr[0];
                return null;
            }
        }
    }

    public static byte[] concatLengthPrefixed(List<Protocol> list) {
        AnonymousClass0HR r4 = new AnonymousClass0HR();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                String protocol2 = protocol.toString();
                r4.A09(protocol2.length());
                r4.A0F(protocol2);
            }
        }
        return r4.A79();
    }

    public static Platform findPlatform() {
        Class<?> cls;
        OptionalMethod optionalMethod;
        Method method;
        Method method2;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException unused) {
            try {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            } catch (ClassNotFoundException unused2) {
                try {
                    Class<?> cls2 = Class.forName("sun.security.ssl.SSLContextImpl");
                    try {
                        Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        return new JdkWithJettyBootPlatform(cls2, cls3.getMethod("put", SSLSocket.class, Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$Provider"))), cls3.getMethod("get", SSLSocket.class), cls3.getMethod("remove", SSLSocket.class), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ClientProvider")), Class.forName(AnonymousClass006.A05("org.eclipse.jetty.alpn.ALPN", "$ServerProvider")));
                    } catch (ClassNotFoundException | NoSuchMethodException unused3) {
                        return new JdkPlatform(cls2);
                    }
                } catch (ClassNotFoundException unused4) {
                    return new Platform();
                }
            }
        }
        OptionalMethod optionalMethod2 = null;
        OptionalMethod optionalMethod3 = new OptionalMethod(null, "setUseSessionTickets", Boolean.TYPE);
        OptionalMethod optionalMethod4 = new OptionalMethod(null, "setHostname", String.class);
        try {
            Class<?> cls4 = Class.forName("android.net.TrafficStats");
            method2 = cls4.getMethod("tagSocket", Socket.class);
            try {
                method = cls4.getMethod("untagSocket", Socket.class);
                try {
                    Class.forName("android.net.Network");
                    optionalMethod = new OptionalMethod(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                    try {
                        optionalMethod2 = new OptionalMethod(null, "setAlpnProtocols", byte[].class);
                    } catch (ClassNotFoundException | NoSuchMethodException unused5) {
                    }
                } catch (ClassNotFoundException | NoSuchMethodException unused6) {
                    optionalMethod = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException unused7) {
                method = null;
                optionalMethod = null;
                return new Android(cls, optionalMethod3, optionalMethod4, method2, method, optionalMethod, optionalMethod2);
            }
        } catch (ClassNotFoundException | NoSuchMethodException unused8) {
            method = null;
            method2 = null;
            optionalMethod = null;
            return new Android(cls, optionalMethod3, optionalMethod4, method2, method, optionalMethod, optionalMethod2);
        }
        return new Android(cls, optionalMethod3, optionalMethod4, method2, method, optionalMethod, optionalMethod2);
    }

    public static Platform get() {
        return PLATFORM;
    }

    public void log(String str) {
        System.out.println(str);
    }

    public void logW(String str) {
        System.out.println(str);
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

    public TrustRootIndex trustRootIndex(X509TrustManager x509TrustManager) {
        return new RealTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }
}
