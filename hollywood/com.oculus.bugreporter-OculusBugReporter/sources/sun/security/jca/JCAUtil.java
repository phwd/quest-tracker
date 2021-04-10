package sun.security.jca;

import java.security.SecureRandom;

public final class JCAUtil {
    private static final int ARRAY_SIZE = 4096;

    private JCAUtil() {
    }

    public static int getTempArraySize(int totalSize) {
        return Math.min(4096, totalSize);
    }

    private static class CachedSecureRandomHolder {
        public static SecureRandom instance = new SecureRandom();

        private CachedSecureRandomHolder() {
        }
    }

    public static SecureRandom getSecureRandom() {
        return CachedSecureRandomHolder.instance;
    }
}
