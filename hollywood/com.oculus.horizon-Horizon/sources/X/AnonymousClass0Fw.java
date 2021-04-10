package X;

import java.io.File;
import java.io.FileFilter;

/* renamed from: X.0Fw  reason: invalid class name */
public class AnonymousClass0Fw implements FileFilter {
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
