package javax.net.ssl;

import java.security.Provider;
import java.security.SecureRandom;
import sun.security.jca.GetInstance;

public class SSLContext {
    private static SSLContext defaultContext;
    private final SSLContextSpi contextSpi;
    private final String protocol;
    private final Provider provider;

    protected SSLContext(SSLContextSpi sSLContextSpi, Provider provider2, String str) {
        this.contextSpi = sSLContextSpi;
        this.provider = provider2;
        this.protocol = str;
    }

    public static synchronized SSLContext getDefault() {
        SSLContext sSLContext;
        synchronized (SSLContext.class) {
            if (defaultContext == null) {
                defaultContext = getInstance("Default");
            }
            sSLContext = defaultContext;
        }
        return sSLContext;
    }

    public static SSLContext getInstance(String str) {
        GetInstance.Instance instance = GetInstance.getInstance("SSLContext", SSLContextSpi.class, str);
        return new SSLContext((SSLContextSpi) instance.impl, instance.provider, str);
    }

    public final void init(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom) {
        this.contextSpi.engineInit(keyManagerArr, trustManagerArr, secureRandom);
    }

    public final SSLSocketFactory getSocketFactory() {
        return this.contextSpi.engineGetSocketFactory();
    }
}
