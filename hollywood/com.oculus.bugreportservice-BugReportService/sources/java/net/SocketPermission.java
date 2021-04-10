package java.net;

import java.io.Serializable;
import java.security.Permission;

public final class SocketPermission extends Permission implements Serializable {
    @Override // java.security.Permission
    public String getActions() {
        return null;
    }

    public SocketPermission(String str, String str2) {
        super("");
    }
}
