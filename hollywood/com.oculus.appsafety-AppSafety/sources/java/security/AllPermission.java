package java.security;

public final class AllPermission extends Permission {
    public AllPermission() {
        super("");
    }

    public AllPermission(String name, String actions) {
        super("");
    }

    @Override // java.security.Permission
    public boolean implies(Permission p) {
        return true;
    }

    @Override // java.security.Permission
    public String getActions() {
        return null;
    }
}
