package X;

import java.io.File;
import java.util.Comparator;

public final class Yw implements Comparator {
    public final /* synthetic */ boolean A00 = true;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        long lastModified = ((File) obj).lastModified() - ((File) obj2).lastModified();
        if (lastModified > 0) {
            return 1;
        }
        if (lastModified == 0) {
            return 0;
        }
        if (this.A00) {
            return -1;
        }
        return 1;
    }
}
