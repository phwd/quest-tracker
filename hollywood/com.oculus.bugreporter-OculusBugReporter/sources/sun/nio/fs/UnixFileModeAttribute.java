package sun.nio.fs;

import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/* access modifiers changed from: package-private */
public class UnixFileModeAttribute {
    static final int ALL_PERMISSIONS = ((((((((UnixConstants.S_IRUSR | UnixConstants.S_IWUSR) | UnixConstants.S_IXUSR) | UnixConstants.S_IRGRP) | UnixConstants.S_IWGRP) | UnixConstants.S_IXGRP) | UnixConstants.S_IROTH) | UnixConstants.S_IWOTH) | UnixConstants.S_IXOTH);
    static final int ALL_READWRITE = (((((UnixConstants.S_IRUSR | UnixConstants.S_IWUSR) | UnixConstants.S_IRGRP) | UnixConstants.S_IWGRP) | UnixConstants.S_IROTH) | UnixConstants.S_IWOTH);
    static final int TEMPFILE_PERMISSIONS = ((UnixConstants.S_IRUSR | UnixConstants.S_IWUSR) | UnixConstants.S_IXUSR);

    private UnixFileModeAttribute() {
    }

    static int toUnixMode(Set<PosixFilePermission> perms) {
        int mode = 0;
        for (PosixFilePermission perm : perms) {
            if (perm != null) {
                switch (perm) {
                    case OWNER_READ:
                        mode |= UnixConstants.S_IRUSR;
                        break;
                    case OWNER_WRITE:
                        mode |= UnixConstants.S_IWUSR;
                        break;
                    case OWNER_EXECUTE:
                        mode |= UnixConstants.S_IXUSR;
                        break;
                    case GROUP_READ:
                        mode |= UnixConstants.S_IRGRP;
                        break;
                    case GROUP_WRITE:
                        mode |= UnixConstants.S_IWGRP;
                        break;
                    case GROUP_EXECUTE:
                        mode |= UnixConstants.S_IXGRP;
                        break;
                    case OTHERS_READ:
                        mode |= UnixConstants.S_IROTH;
                        break;
                    case OTHERS_WRITE:
                        mode |= UnixConstants.S_IWOTH;
                        break;
                    case OTHERS_EXECUTE:
                        mode |= UnixConstants.S_IXOTH;
                        break;
                }
            } else {
                throw new NullPointerException();
            }
        }
        return mode;
    }

    static int toUnixMode(int defaultMode, FileAttribute<?>... attrs) {
        int mode = defaultMode;
        for (FileAttribute<?> attr : attrs) {
            String name = attr.name();
            if (name.equals("posix:permissions") || name.equals("unix:permissions")) {
                mode = toUnixMode((Set) attr.value());
            } else {
                throw new UnsupportedOperationException("'" + attr.name() + "' not supported as initial attribute");
            }
        }
        return mode;
    }
}
