package javax.net.ssl;

import java.security.SecureRandom;

public abstract class SSLContextSpi {
    /* access modifiers changed from: protected */
    public abstract SSLSocketFactory engineGetSocketFactory();

    /* access modifiers changed from: protected */
    public abstract void engineInit(KeyManager[] keyManagerArr, TrustManager[] trustManagerArr, SecureRandom secureRandom);
}
