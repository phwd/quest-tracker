package X;

import java.io.File;
import java.util.Comparator;

/* renamed from: X.1Xy  reason: invalid class name */
public class AnonymousClass1Xy implements Comparator<File> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(File file, File file2) {
        File file3 = file;
        File file4 = file2;
        if (file3.lastModified() == file4.lastModified()) {
            return 0;
        }
        if (file3.lastModified() < file4.lastModified()) {
            return -1;
        }
        return 1;
    }
}
