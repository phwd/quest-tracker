package java.nio.file;

import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.AccessController;
import java.security.SecureRandom;
import java.util.EnumSet;
import sun.security.action.GetPropertyAction;

class TempFileHelper {
    private static final boolean isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
    private static final SecureRandom random = new SecureRandom();
    private static final Path tmpdir = Paths.get((String) AccessController.doPrivileged(new GetPropertyAction("java.io.tmpdir")), new String[0]);

    /* access modifiers changed from: private */
    public static class PosixPermissions {
        static final FileAttribute dirPermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));
        static final FileAttribute filePermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE));
    }

    private static Path generatePath(String str, String str2, Path path) {
        long j;
        long nextLong = random.nextLong();
        if (nextLong == Long.MIN_VALUE) {
            j = 0;
        } else {
            j = Math.abs(nextLong);
        }
        FileSystem fileSystem = path.getFileSystem();
        Path path2 = fileSystem.getPath(str + Long.toString(j) + str2, new String[0]);
        if (path2.getParent() == null) {
            return path.resolve(path2);
        }
        throw new IllegalArgumentException("Invalid prefix or suffix");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0070, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0073, code lost:
        if (r5 != java.nio.file.TempFileHelper.tmpdir) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x007e, code lost:
        throw new java.lang.SecurityException("Unable to create temporary file or directory");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x007f, code lost:
        throw r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0062 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062 A[LOOP:1: B:35:0x0062->B:38:0x0068, LOOP_START, SYNTHETIC, Splitter:B:35:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.nio.file.Path create(java.nio.file.Path r5, java.lang.String r6, java.lang.String r7, boolean r8, java.nio.file.attribute.FileAttribute[] r9) {
        /*
        // Method dump skipped, instructions count: 140
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.TempFileHelper.create(java.nio.file.Path, java.lang.String, java.lang.String, boolean, java.nio.file.attribute.FileAttribute[]):java.nio.file.Path");
    }

    static Path createTempFile(Path path, String str, String str2, FileAttribute[] fileAttributeArr) {
        return create(path, str, str2, false, fileAttributeArr);
    }
}
