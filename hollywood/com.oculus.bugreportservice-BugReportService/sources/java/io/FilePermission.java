package java.io;

import java.security.Permission;

public final class FilePermission extends Permission implements Serializable {
    @Override // java.security.Permission
    public String getActions() {
        return null;
    }

    public FilePermission(String str, String str2) {
        super(str);
    }
}
