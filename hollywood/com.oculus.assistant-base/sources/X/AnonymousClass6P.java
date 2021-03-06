package X;

import java.io.File;
import java.util.Comparator;

/* renamed from: X.6P  reason: invalid class name */
public final class AnonymousClass6P implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        File file = (File) obj;
        File file2 = (File) obj2;
        if (file.lastModified() == file2.lastModified()) {
            String name = file.getName();
            String name2 = file2.getName();
            int length = name.length();
            int length2 = name2.length();
            if (length > length2) {
                return -1;
            }
            if (length2 > length) {
                return 1;
            }
            return name2.compareTo(name);
        } else if (file.lastModified() <= file2.lastModified()) {
            return 1;
        } else {
            return -1;
        }
    }
}
