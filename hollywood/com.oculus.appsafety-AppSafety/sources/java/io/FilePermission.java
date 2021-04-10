package java.io;

import java.security.Permission;

public final class FilePermission extends Permission implements Serializable {
    public FilePermission(String path, String actions) {
        super(path);
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
