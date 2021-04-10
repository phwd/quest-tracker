package X;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: X.1bV  reason: invalid class name */
public class AnonymousClass1bV implements FilenameFilter {
    public final /* synthetic */ C09321au A00;

    public AnonymousClass1bV(C09321au r1) {
        this.A00 = r1;
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".mctable");
    }
}
