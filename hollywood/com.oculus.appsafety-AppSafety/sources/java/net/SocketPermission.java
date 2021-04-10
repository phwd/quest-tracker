package java.net;

import java.io.Serializable;
import java.security.Permission;

public final class SocketPermission extends Permission implements Serializable {
    public SocketPermission(String host, String action) {
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
