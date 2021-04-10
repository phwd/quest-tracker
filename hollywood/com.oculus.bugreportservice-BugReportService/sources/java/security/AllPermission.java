package java.security;

public final class AllPermission extends Permission {
    @Override // java.security.Permission
    public String getActions() {
        return null;
    }

    public AllPermission() {
        super("");
    }
}
