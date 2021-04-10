package java.nio.file;

import java.io.IOException;

class CopyMoveHelper {
    private CopyMoveHelper() {
    }

    /* access modifiers changed from: private */
    public static class CopyOptions {
        boolean copyAttributes = false;
        boolean followLinks = true;
        boolean replaceExisting = false;

        private CopyOptions() {
        }

        static CopyOptions parse(CopyOption... options) {
            CopyOptions result = new CopyOptions();
            for (CopyOption option : options) {
                if (option == StandardCopyOption.REPLACE_EXISTING) {
                    result.replaceExisting = true;
                } else if (option == LinkOption.NOFOLLOW_LINKS) {
                    result.followLinks = false;
                } else if (option == StandardCopyOption.COPY_ATTRIBUTES) {
                    result.copyAttributes = true;
                } else if (option == null) {
                    throw new NullPointerException();
                } else {
                    throw new UnsupportedOperationException("'" + ((Object) option) + "' is not a recognized copy option");
                }
            }
            return result;
        }
    }

    private static CopyOption[] convertMoveToCopyOptions(CopyOption... options) throws AtomicMoveNotSupportedException {
        int len = options.length;
        CopyOption[] newOptions = new CopyOption[(len + 2)];
        for (int i = 0; i < len; i++) {
            CopyOption option = options[i];
            if (option != StandardCopyOption.ATOMIC_MOVE) {
                newOptions[i] = option;
            } else {
                throw new AtomicMoveNotSupportedException(null, null, "Atomic move between providers is not supported");
            }
        }
        newOptions[len] = LinkOption.NOFOLLOW_LINKS;
        newOptions[len + 1] = StandardCopyOption.COPY_ATTRIBUTES;
        return newOptions;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0077, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0078, code lost:
        if (r4 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007e, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007f, code lost:
        r2.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0082, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void copyToForeignTarget(java.nio.file.Path r7, java.nio.file.Path r8, java.nio.file.CopyOption... r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 149
        */
        throw new UnsupportedOperationException("Method not decompiled: java.nio.file.CopyMoveHelper.copyToForeignTarget(java.nio.file.Path, java.nio.file.Path, java.nio.file.CopyOption[]):void");
    }

    static void moveToForeignTarget(Path source, Path target, CopyOption... options) throws IOException {
        copyToForeignTarget(source, target, convertMoveToCopyOptions(options));
        Files.delete(source);
    }
}
