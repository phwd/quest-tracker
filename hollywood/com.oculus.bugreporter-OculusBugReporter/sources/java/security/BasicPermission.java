package java.security;

import java.io.Serializable;

public abstract class BasicPermission extends Permission implements Serializable {
    public BasicPermission(String name) {
        super("");
    }

    public BasicPermission(String name, String actions) {
        super("");
    }

    @Override // java.security.Permission
    public boolean implies(Permission p) {
        return true;
    }

    @Override // java.security.Permission
    public String getActions() {
        return "";
    }
}
