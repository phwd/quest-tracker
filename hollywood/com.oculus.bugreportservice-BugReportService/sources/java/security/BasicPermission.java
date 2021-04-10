package java.security;

import java.io.Serializable;

public abstract class BasicPermission extends Permission implements Serializable {
    @Override // java.security.Permission
    public String getActions() {
        return "";
    }

    public BasicPermission(String str) {
        super("");
    }

    public BasicPermission(String str, String str2) {
        super("");
    }
}
