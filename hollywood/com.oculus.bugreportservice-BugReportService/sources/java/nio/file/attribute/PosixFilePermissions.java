package java.nio.file.attribute;

import java.util.HashSet;
import java.util.Set;

public final class PosixFilePermissions {
    public static FileAttribute asFileAttribute(Set set) {
        final HashSet<PosixFilePermission> hashSet = new HashSet(set);
        for (PosixFilePermission posixFilePermission : hashSet) {
            if (posixFilePermission == null) {
                throw new NullPointerException();
            }
        }
        return new FileAttribute() {
            /* class java.nio.file.attribute.PosixFilePermissions.AnonymousClass1 */

            @Override // java.nio.file.attribute.FileAttribute
            public String name() {
                return "posix:permissions";
            }
        };
    }
}
