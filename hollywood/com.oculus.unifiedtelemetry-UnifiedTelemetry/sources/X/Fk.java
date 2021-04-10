package X;

import java.io.File;
import java.io.FileFilter;

public class Fk implements FileFilter {
    public final boolean accept(File file) {
        return file.isDirectory();
    }
}
