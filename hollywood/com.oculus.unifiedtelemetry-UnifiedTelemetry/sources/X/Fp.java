package X;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.Nullable;

public class Fp implements Iterable<File> {
    public final File[] A00;

    @Override // java.lang.Iterable
    public final Iterator<File> iterator() {
        return new Fn(this.A00);
    }

    public Fp(File file, @Nullable FileFilter fileFilter, @Nullable Comparator<File> comparator) {
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
