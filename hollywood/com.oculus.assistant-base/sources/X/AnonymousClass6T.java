package X;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* renamed from: X.6T  reason: invalid class name */
public final class AnonymousClass6T implements Iterable {
    public final File[] A00;

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new AnonymousClass6R(this.A00);
    }

    public AnonymousClass6T(File file, FileFilter fileFilter, Comparator comparator) {
        File[] listFiles;
        if (fileFilter != null) {
            listFiles = file.listFiles(fileFilter);
        } else {
            listFiles = file.listFiles();
        }
        if (listFiles == null) {
            listFiles = new File[0];
        } else if (comparator != null) {
            File[] fileArr = (File[]) Arrays.copyOf(listFiles, listFiles.length);
            try {
                Arrays.sort(fileArr, comparator);
                listFiles = fileArr;
            } catch (IllegalArgumentException unused) {
            }
        }
        this.A00 = listFiles;
    }
}
