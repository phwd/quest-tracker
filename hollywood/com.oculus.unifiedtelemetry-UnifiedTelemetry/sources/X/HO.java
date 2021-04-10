package X;

import java.io.File;
import javax.annotation.Nullable;

public class HO {
    public final int A00;
    public final C00062o<String, File> A01;
    @Nullable
    public final String A02;
    public final boolean A03;

    public HO(int i, @Nullable String str, boolean z, C00062o<String, File> r6) {
        if (i != -1) {
            this.A00 = i;
            this.A02 = str;
            this.A03 = z;
            this.A01 = r6;
            return;
        }
        throw new IllegalArgumentException("jobId = -1");
    }
}
