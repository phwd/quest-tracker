package java.security;

import java.io.Serializable;
import java.util.Enumeration;

public final class Permissions extends PermissionCollection implements Serializable {
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
