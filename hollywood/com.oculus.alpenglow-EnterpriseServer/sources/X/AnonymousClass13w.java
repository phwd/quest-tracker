package X;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: X.13w  reason: invalid class name */
public class AnonymousClass13w implements FilenameFilter {
    public final /* synthetic */ AnonymousClass12p A00;

    public AnonymousClass13w(AnonymousClass12p r1) {
        this.A00 = r1;
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".mctable");
    }
}
