package javax.net.ssl;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.net.ServerSocketFactory;

public abstract class SSLServerSocketFactory extends ServerSocketFactory {
    private static SSLServerSocketFactory defaultServerSocketFactory;
    private static int lastVersion = -1;

    public abstract String[] getDefaultCipherSuites();

    public abstract String[] getSupportedCipherSuites();

    private static void log(String msg) {
        if (SSLSocketFactory.DEBUG) {
            System.out.println(msg);
        }
    }

    protected SSLServerSocketFactory() {
    }

    public static synchronized ServerSocketFactory getDefault() {
        synchronized (SSLServerSocketFactory.class) {
            if (defaultServerSocketFactory == null || lastVersion != Security.getVersion()) {
                lastVersion = Security.getVersion();
                SSLServerSocketFactory previousDefaultServerSocketFactory = defaultServerSocketFactory;
                defaultServerSocketFactory = null;
                String clsName = SSLSocketFactory.getSecurityProperty("ssl.ServerSocketFactory.provider");
                if (clsName != null) {
                    if (previousDefaultServerSocketFactory == null || !clsName.equals(previousDefaultServerSocketFactory.getClass().getName())) {
                        log("setting up default SSLServerSocketFactory");
                        Class<?> cls = null;
                        try {
                            cls = Class.forName(clsName);
                        } catch (ClassNotFoundException e) {
                            try {
                                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                                if (cl == null) {
                                    cl = ClassLoader.getSystemClassLoader();
                                }
                                if (cl != null) {
                                    cls = Class.forName(clsName, true, cl);
                                }
                            } catch (Exception e2) {
                                log("SSLServerSocketFactory instantiation failed: " + ((Object) e2));
                            }
                        }
                        log("class " + clsName + " is loaded");
                        SSLServerSocketFactory fac = (SSLServerSocketFactory) cls.newInstance();
                        log("instantiated an instance of class " + clsName);
                        defaultServerSocketFactory = fac;
                        return fac;
                    }
                    defaultServerSocketFactory = previousDefaultServerSocketFactory;
                    return defaultServerSocketFactory;
                }
                try {
                    SSLContext context = SSLContext.getDefault();
                    if (context != null) {
                        defaultServerSocketFactory = context.getServerSocketFactory();
                    } else {
                        defaultServerSocketFactory = new DefaultSSLServerSocketFactory(new IllegalStateException("No factory found."));
                    }
                    return defaultServerSocketFactory;
                } catch (NoSuchAlgorithmException e3) {
                    return new DefaultSSLServerSocketFactory(e3);
                }
            } else {
                return defaultServerSocketFactory;
            }
        }
    }
}
