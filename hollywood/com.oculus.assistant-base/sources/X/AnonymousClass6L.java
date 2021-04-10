package X;

import java.io.File;
import java.io.FileFilter;

/* renamed from: X.6L  reason: invalid class name */
public final class AnonymousClass6L implements FileFilter {
    public final boolean accept(File file) {
        if (!file.isFile()) {
            return false;
        }
        String name = file.getName();
        if (!name.startsWith("batch-") || !name.endsWith(".json")) {
            return false;
        }
        return true;
    }
}
