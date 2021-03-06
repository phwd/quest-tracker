package java.security;

import java.util.Enumeration;

public abstract class Policy {
    public static final PermissionCollection UNSUPPORTED_EMPTY_COLLECTION = new UnsupportedEmptyCollection();

    public interface Parameters {
    }

    public static Policy getPolicy() {
        return null;
    }

    public static void setPolicy(Policy p) {
    }

    public static Policy getInstance(String type, Parameters params) throws NoSuchAlgorithmException {
        return null;
    }

    public static Policy getInstance(String type, Parameters params, String provider) throws NoSuchProviderException, NoSuchAlgorithmException {
        return null;
    }

    public static Policy getInstance(String type, Parameters params, Provider provider) throws NoSuchAlgorithmException {
        return null;
    }

    public Provider getProvider() {
        return null;
    }

    public String getType() {
        return null;
    }

    public Parameters getParameters() {
        return null;
    }

    public PermissionCollection getPermissions(CodeSource codesource) {
        return null;
    }

    public PermissionCollection getPermissions(ProtectionDomain domain) {
        return null;
    }

    public boolean implies(ProtectionDomain domain, Permission permission) {
        return true;
    }

    public void refresh() {
    }

    private static class UnsupportedEmptyCollection extends PermissionCollection {
        @Override // java.security.PermissionCollection
        public void add(Permission permission) {
        }

        @Override // java.security.PermissionCollection
        public boolean implies(Permission permission) {
            return true;
        }

        @Override // java.security.PermissionCollection
        public Enumeration<Permission> elements() {
            return null;
        }
    }
}
