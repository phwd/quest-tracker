package X;

import java.io.File;
import java.io.FileFilter;

public class Fh implements FileFilter {
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
