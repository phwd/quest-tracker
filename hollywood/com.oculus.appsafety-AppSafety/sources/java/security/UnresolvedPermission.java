package java.security;

import java.io.Serializable;
import java.security.cert.Certificate;

public final class UnresolvedPermission extends Permission implements Serializable {
    public UnresolvedPermission(String type, String name, String actions, Certificate[] certs) {
        super("");
    }

    @Override // java.security.Permission
    public boolean implies(Permission p) {
        return false;
    }

    @Override // java.security.Permission
    public String getActions() {
        return null;
    }

    public String getUnresolvedType() {
        return null;
    }

    public String getUnresolvedName() {
        return null;
    }

    public String getUnresolvedActions() {
        return null;
    }

    public Certificate[] getUnresolvedCerts() {
        return null;
    }
}
