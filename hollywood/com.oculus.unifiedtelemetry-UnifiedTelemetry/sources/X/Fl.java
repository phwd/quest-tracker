package X;

import java.io.File;
import java.util.Comparator;

public class Fl implements Comparator<File> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(File file, File file2) {
        File file3 = file;
        File file4 = file2;
        if (file3.lastModified() == file4.lastModified()) {
            String name = file3.getName();
            String name2 = file4.getName();
            int length = name.length();
            int length2 = name2.length();
            if (length > length2) {
                return -1;
            }
            if (length2 > length) {
                return 1;
            }
            return name2.compareTo(name);
        } else if (file3.lastModified() <= file4.lastModified()) {
            return 1;
        } else {
            return -1;
        }
    }
}
