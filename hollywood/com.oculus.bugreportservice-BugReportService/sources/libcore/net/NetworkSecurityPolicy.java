package libcore.net;

public abstract class NetworkSecurityPolicy {
    private static volatile NetworkSecurityPolicy instance = new DefaultNetworkSecurityPolicy();

    public abstract boolean isCleartextTrafficPermitted();

    public abstract boolean isCleartextTrafficPermitted(String str);

    public static NetworkSecurityPolicy getInstance() {
        return instance;
    }

    public static final class DefaultNetworkSecurityPolicy extends NetworkSecurityPolicy {
        @Override // libcore.net.NetworkSecurityPolicy
        public boolean isCleartextTrafficPermitted() {
            return true;
        }

        @Override // libcore.net.NetworkSecurityPolicy
        public boolean isCleartextTrafficPermitted(String str) {
            return isCleartextTrafficPermitted();
        }
    }
}
