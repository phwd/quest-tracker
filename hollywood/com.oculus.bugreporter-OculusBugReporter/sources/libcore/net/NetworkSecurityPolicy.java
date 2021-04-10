package libcore.net;

public abstract class NetworkSecurityPolicy {
    private static volatile NetworkSecurityPolicy instance = new DefaultNetworkSecurityPolicy();

    public abstract boolean isCertificateTransparencyVerificationRequired(String str);

    public abstract boolean isCleartextTrafficPermitted();

    public abstract boolean isCleartextTrafficPermitted(String str);

    public static NetworkSecurityPolicy getInstance() {
        return instance;
    }

    public static void setInstance(NetworkSecurityPolicy policy) {
        if (policy != null) {
            instance = policy;
            return;
        }
        throw new NullPointerException("policy == null");
    }

    public static final class DefaultNetworkSecurityPolicy extends NetworkSecurityPolicy {
        @Override // libcore.net.NetworkSecurityPolicy
        public boolean isCleartextTrafficPermitted() {
            return true;
        }

        @Override // libcore.net.NetworkSecurityPolicy
        public boolean isCleartextTrafficPermitted(String hostname) {
            return isCleartextTrafficPermitted();
        }

        @Override // libcore.net.NetworkSecurityPolicy
        public boolean isCertificateTransparencyVerificationRequired(String hostname) {
            return false;
        }
    }
}
