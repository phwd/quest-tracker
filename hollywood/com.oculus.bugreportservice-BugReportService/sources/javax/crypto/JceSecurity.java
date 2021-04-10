package javax.crypto;

import java.net.URL;
import java.security.SecureRandom;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* access modifiers changed from: package-private */
public final class JceSecurity {
    private static final URL NULL_URL;
    private static final Object PROVIDER_VERIFIED = Boolean.TRUE;
    static final SecureRandom RANDOM = new SecureRandom();
    private static final Map codeBaseCacheRef = new WeakHashMap();
    private static CryptoPermissions defaultPolicy = null;
    private static CryptoPermissions exemptPolicy = null;
    private static final Map verificationResults = new IdentityHashMap();
    private static final Map verifyingProviders = new IdentityHashMap();

    static {
        try {
            NULL_URL = new URL("http://null.sun.com/");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
