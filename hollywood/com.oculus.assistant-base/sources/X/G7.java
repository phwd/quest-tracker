package X;

import java.io.File;
import java.io.FilenameFilter;

public final class G7 implements FilenameFilter {
    public final /* synthetic */ C0891oD A00;

    public G7(C0891oD oDVar) {
        this.A00 = oDVar;
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".mctable");
    }
}
