package java.net;

import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.net.Proxy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.StringTokenizer;
import sun.net.ApplicationProxy;
import sun.net.www.protocol.file.Handler;
import sun.security.util.SecurityConstants;

public final class URL implements Serializable {
    private static final Set BUILTIN_HANDLER_CLASS_NAMES = createBuiltinHandlerClassNames();
    static URLStreamHandlerFactory factory = null;
    static Hashtable handlers = new Hashtable();
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("protocol", String.class), new ObjectStreamField("host", String.class), new ObjectStreamField("port", Integer.TYPE), new ObjectStreamField("authority", String.class), new ObjectStreamField("file", String.class), new ObjectStreamField("ref", String.class)};
    static final long serialVersionUID = -7627629688361524110L;
    private static Object streamHandlerLock = new Object();
    private String authority;
    private String file;
    transient URLStreamHandler handler;
    private int hashCode;
    private String host;
    transient InetAddress hostAddress;
    private transient String path;
    private int port;
    private String protocol;
    private transient String query;
    private String ref;
    private transient UrlDeserializedState tempState;
    private transient String userInfo;

    public URL(String str, String str2, int i, String str3) {
        this(str, str2, i, str3, null);
    }

    public URL(String str, String str2, String str3) {
        this(str, str2, -1, str3);
    }

    public URL(String str, String str2, int i, String str3, URLStreamHandler uRLStreamHandler) {
        String str4;
        SecurityManager securityManager;
        this.port = -1;
        this.hashCode = -1;
        if (uRLStreamHandler == null || (securityManager = System.getSecurityManager()) == null) {
            String lowerCase = str.toLowerCase();
            this.protocol = lowerCase;
            if (str2 != null) {
                if (str2.indexOf(58) >= 0 && !str2.startsWith("[")) {
                    str2 = "[" + str2 + "]";
                }
                this.host = str2;
                if (i >= -1) {
                    this.port = i;
                    if (i == -1) {
                        str4 = str2;
                    } else {
                        str4 = str2 + ":" + i;
                    }
                    this.authority = str4;
                } else {
                    throw new MalformedURLException("Invalid port number :" + i);
                }
            }
            Parts parts = new Parts(str3, str2);
            this.path = parts.getPath();
            this.query = parts.getQuery();
            if (this.query != null) {
                this.file = this.path + "?" + this.query;
            } else {
                this.file = this.path;
            }
            this.ref = parts.getRef();
            if (uRLStreamHandler == null && (uRLStreamHandler = getURLStreamHandler(lowerCase)) == null) {
                throw new MalformedURLException("unknown protocol: " + lowerCase);
            }
            this.handler = uRLStreamHandler;
            return;
        }
        checkSpecifyHandler(securityManager);
        throw null;
    }

    public URL(String str) {
        this(null, str);
    }

    public URL(URL url, String str) {
        this(url, str, (URLStreamHandler) null);
    }

    public URL(URL url, String str, URLStreamHandler uRLStreamHandler) {
        String str2;
        SecurityManager securityManager;
        this.port = -1;
        this.hashCode = -1;
        String str3 = null;
        if (uRLStreamHandler == null || (securityManager = System.getSecurityManager()) == null) {
            try {
                int length = str.length();
                while (length > 0 && str.charAt(length - 1) <= ' ') {
                    length--;
                }
                boolean z = false;
                int i = 0;
                while (i < length && str.charAt(i) <= ' ') {
                    i++;
                }
                i = str.regionMatches(true, i, "url:", 0, 4) ? i + 4 : i;
                boolean z2 = i < str.length() && str.charAt(i) == '#';
                int i2 = i;
                while (true) {
                    if (z2 || i2 >= length) {
                        break;
                    }
                    char charAt = str.charAt(i2);
                    if (charAt == '/') {
                        break;
                    } else if (charAt == ':') {
                        str2 = str.substring(i, i2).toLowerCase();
                        if (isValidProtocol(str2)) {
                            i = i2 + 1;
                        }
                    } else {
                        i2++;
                    }
                }
                str2 = null;
                this.protocol = str2;
                if (url != null && (str2 == null || str2.equalsIgnoreCase(url.protocol))) {
                    uRLStreamHandler = uRLStreamHandler == null ? url.handler : uRLStreamHandler;
                    if (url.path == null || !url.path.startsWith("/")) {
                        str3 = str2;
                    }
                    if (str3 == null) {
                        this.protocol = url.protocol;
                        this.authority = url.authority;
                        this.userInfo = url.userInfo;
                        this.host = url.host;
                        this.port = url.port;
                        this.file = url.file;
                        this.path = url.path;
                        z = true;
                    }
                }
                if (this.protocol != null) {
                    if (uRLStreamHandler == null) {
                        uRLStreamHandler = getURLStreamHandler(this.protocol);
                        if (uRLStreamHandler == null) {
                            throw new MalformedURLException("unknown protocol: " + this.protocol);
                        }
                    }
                    this.handler = uRLStreamHandler;
                    int indexOf = str.indexOf(35, i);
                    if (indexOf >= 0) {
                        this.ref = str.substring(indexOf + 1, length);
                    } else {
                        indexOf = length;
                    }
                    if (z && i == indexOf) {
                        this.query = url.query;
                        if (this.ref == null) {
                            this.ref = url.ref;
                        }
                    }
                    uRLStreamHandler.parseURL(this, str, i, indexOf);
                    return;
                }
                throw new MalformedURLException("no protocol: " + str);
            } catch (MalformedURLException e) {
                throw e;
            } catch (Exception e2) {
                MalformedURLException malformedURLException = new MalformedURLException(e2.getMessage());
                malformedURLException.initCause(e2);
                throw malformedURLException;
            }
        } else {
            checkSpecifyHandler(securityManager);
            throw null;
        }
    }

    private boolean isValidProtocol(String str) {
        int length = str.length();
        if (length < 1 || !Character.isLetter(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            char charAt = str.charAt(i);
            if (!(Character.isLetterOrDigit(charAt) || charAt == '.' || charAt == '+' || charAt == '-')) {
                return false;
            }
        }
        return true;
    }

    private void checkSpecifyHandler(SecurityManager securityManager) {
        securityManager.checkPermission(SecurityConstants.SPECIFY_HANDLER_PERMISSION);
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void set(String str, String str2, int i, String str3, String str4, String str5, String str6, String str7) {
        String str8;
        synchronized (this) {
            this.protocol = str;
            this.host = str2;
            this.port = i;
            if (str6 != null) {
                if (!str6.isEmpty()) {
                    str8 = str5 + "?" + str6;
                    this.file = str8;
                    this.userInfo = str4;
                    this.path = str5;
                    this.ref = str7;
                    this.hashCode = -1;
                    this.hostAddress = null;
                    this.query = str6;
                    this.authority = str3;
                }
            }
            str8 = str5;
            this.file = str8;
            this.userInfo = str4;
            this.path = str5;
            this.ref = str7;
            this.hashCode = -1;
            this.hostAddress = null;
            this.query = str6;
            this.authority = str3;
        }
    }

    public String getQuery() {
        return this.query;
    }

    public String getPath() {
        return this.path;
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public String getAuthority() {
        return this.authority;
    }

    public int getPort() {
        return this.port;
    }

    public int getDefaultPort() {
        return this.handler.getDefaultPort();
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getHost() {
        return this.host;
    }

    public String getFile() {
        return this.file;
    }

    public String getRef() {
        return this.ref;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof URL)) {
            return false;
        }
        return this.handler.equals(this, (URL) obj);
    }

    public synchronized int hashCode() {
        if (this.hashCode != -1) {
            return this.hashCode;
        }
        this.hashCode = this.handler.hashCode(this);
        return this.hashCode;
    }

    public String toString() {
        return toExternalForm();
    }

    public String toExternalForm() {
        return this.handler.toExternalForm(this);
    }

    public URI toURI() {
        return new URI(toString());
    }

    public URLConnection openConnection() {
        return this.handler.openConnection(this);
    }

    public URLConnection openConnection(Proxy proxy) {
        if (proxy != null) {
            Proxy proxy2 = Proxy.NO_PROXY;
            if (proxy != proxy2) {
                proxy2 = ApplicationProxy.create(proxy);
            }
            SecurityManager securityManager = System.getSecurityManager();
            if (proxy2.type() == Proxy.Type.DIRECT || securityManager == null) {
                return this.handler.openConnection(this, proxy2);
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) proxy2.address();
            if (inetSocketAddress.isUnresolved()) {
                securityManager.checkConnect(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
                throw null;
            }
            securityManager.checkConnect(inetSocketAddress.getAddress().getHostAddress(), inetSocketAddress.getPort());
            throw null;
        }
        throw new IllegalArgumentException("proxy can not be null");
    }

    public final InputStream openStream() {
        return openConnection().getInputStream();
    }

    static URLStreamHandler getURLStreamHandler(String str) {
        URLStreamHandler uRLStreamHandler = (URLStreamHandler) handlers.get(str);
        if (uRLStreamHandler == null) {
            boolean z = false;
            URLStreamHandlerFactory uRLStreamHandlerFactory = factory;
            if (uRLStreamHandlerFactory != null) {
                uRLStreamHandler = uRLStreamHandlerFactory.createURLStreamHandler(str);
                z = true;
            }
            if (uRLStreamHandler == null) {
                StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.protocol.handler.pkgs", ""), "|");
                while (uRLStreamHandler == null && stringTokenizer.hasMoreTokens()) {
                    try {
                        String str2 = stringTokenizer.nextToken().trim() + "." + str + ".Handler";
                        Class cls = null;
                        try {
                            cls = Class.forName(str2, true, ClassLoader.getSystemClassLoader());
                        } catch (ClassNotFoundException unused) {
                            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                            if (contextClassLoader != null) {
                                cls = Class.forName(str2, true, contextClassLoader);
                            }
                        }
                        if (cls != null) {
                            uRLStreamHandler = (URLStreamHandler) cls.newInstance();
                        }
                    } catch (ReflectiveOperationException unused2) {
                    }
                }
            }
            if (uRLStreamHandler == null) {
                try {
                    uRLStreamHandler = createBuiltinHandler(str);
                } catch (Exception e) {
                    throw new AssertionError(e);
                }
            }
            synchronized (streamHandlerLock) {
                URLStreamHandler uRLStreamHandler2 = (URLStreamHandler) handlers.get(str);
                if (uRLStreamHandler2 != null) {
                    return uRLStreamHandler2;
                }
                if (!z && factory != null) {
                    uRLStreamHandler2 = factory.createURLStreamHandler(str);
                }
                if (uRLStreamHandler2 != null) {
                    uRLStreamHandler = uRLStreamHandler2;
                }
                if (uRLStreamHandler != null) {
                    handlers.put(str, uRLStreamHandler);
                }
            }
        }
        return uRLStreamHandler;
    }

    private static URLStreamHandler createBuiltinHandler(String str) {
        if (str.equals("file")) {
            return new Handler();
        }
        if (str.equals("ftp")) {
            return new sun.net.www.protocol.ftp.Handler();
        }
        if (str.equals("jar")) {
            return new sun.net.www.protocol.jar.Handler();
        }
        if (str.equals("http")) {
            return (URLStreamHandler) Class.forName("com.android.okhttp.HttpHandler").newInstance();
        }
        if (str.equals("https")) {
            return (URLStreamHandler) Class.forName("com.android.okhttp.HttpsHandler").newInstance();
        }
        return null;
    }

    private static Set createBuiltinHandlerClassNames() {
        HashSet hashSet = new HashSet();
        hashSet.add("sun.net.www.protocol.file.Handler");
        hashSet.add("sun.net.www.protocol.ftp.Handler");
        hashSet.add("sun.net.www.protocol.jar.Handler");
        hashSet.add("com.android.okhttp.HttpHandler");
        hashSet.add("com.android.okhttp.HttpsHandler");
        return Collections.unmodifiableSet(hashSet);
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private synchronized void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        throw null;
    }

    private Object readResolve() {
        URLStreamHandler uRLStreamHandler = getURLStreamHandler(this.tempState.getProtocol());
        if (isBuiltinStreamHandler(uRLStreamHandler.getClass().getName())) {
            return fabricateNewURL();
        }
        setDeserializedFields(uRLStreamHandler);
        return this;
    }

    private URL setDeserializedFields(URLStreamHandler uRLStreamHandler) {
        String str;
        String str2;
        int indexOf;
        String str3;
        String protocol2 = this.tempState.getProtocol();
        String host2 = this.tempState.getHost();
        int port2 = this.tempState.getPort();
        String authority2 = this.tempState.getAuthority();
        String file2 = this.tempState.getFile();
        String ref2 = this.tempState.getRef();
        int hashCode2 = this.tempState.getHashCode();
        String str4 = null;
        if (authority2 != null || ((host2 == null || host2.length() <= 0) && port2 == -1)) {
            str = (authority2 == null || (indexOf = authority2.indexOf(64)) == -1) ? null : authority2.substring(0, indexOf);
        } else {
            if (host2 == null) {
                host2 = "";
            }
            if (port2 == -1) {
                authority2 = host2;
            } else {
                authority2 = host2 + ":" + port2;
            }
            int lastIndexOf = host2.lastIndexOf(64);
            if (lastIndexOf != -1) {
                str3 = host2.substring(0, lastIndexOf);
                host2 = host2.substring(lastIndexOf + 1);
            } else {
                str3 = null;
            }
            str = str3;
        }
        if (file2 != null) {
            int lastIndexOf2 = file2.lastIndexOf(63);
            if (lastIndexOf2 != -1) {
                str4 = file2.substring(lastIndexOf2 + 1);
                str2 = file2.substring(0, lastIndexOf2);
            } else {
                str2 = file2;
            }
        } else {
            str2 = null;
        }
        this.protocol = protocol2;
        this.host = host2;
        this.port = port2;
        this.file = file2;
        this.authority = authority2;
        this.ref = ref2;
        this.hashCode = hashCode2;
        this.handler = uRLStreamHandler;
        this.query = str4;
        this.path = str2;
        this.userInfo = str;
        return this;
    }

    private URL fabricateNewURL() {
        String reconstituteUrlString = this.tempState.reconstituteUrlString();
        try {
            URL url = new URL(reconstituteUrlString);
            url.setSerializedHashCode(this.tempState.getHashCode());
            resetState();
            return url;
        } catch (MalformedURLException e) {
            resetState();
            InvalidObjectException invalidObjectException = new InvalidObjectException("Malformed URL: " + reconstituteUrlString);
            invalidObjectException.initCause(e);
            throw invalidObjectException;
        }
    }

    private boolean isBuiltinStreamHandler(String str) {
        return BUILTIN_HANDLER_CLASS_NAMES.contains(str);
    }

    private void resetState() {
        this.protocol = null;
        this.host = null;
        this.port = -1;
        this.file = null;
        this.authority = null;
        this.ref = null;
        this.hashCode = -1;
        this.handler = null;
        this.query = null;
        this.path = null;
        this.userInfo = null;
        this.tempState = null;
    }

    private void setSerializedHashCode(int i) {
        this.hashCode = i;
    }
}
