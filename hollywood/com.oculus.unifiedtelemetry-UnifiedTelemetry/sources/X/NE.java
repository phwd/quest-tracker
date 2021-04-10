package X;

import java.io.File;
import java.io.FileFilter;

public class NE implements FileFilter {
    public final boolean accept(File file) {
        String name = file.getName();
        if (name.startsWith("cpu")) {
            for (int i = 3; i < name.length(); i++) {
                if (Character.isDigit(name.charAt(i))) {
                }
            }
            return true;
        }
        return false;
    }
}
