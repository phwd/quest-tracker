package X;

import java.io.File;
import java.io.FilenameFilter;

public class Ry implements FilenameFilter {
    public final /* synthetic */ XY A00;

    public Ry(XY xy) {
        this.A00 = xy;
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".mctable");
    }
}
