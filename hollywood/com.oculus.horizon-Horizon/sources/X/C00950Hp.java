package X;

import java.io.File;
import javax.annotation.Nullable;

/* renamed from: X.0Hp  reason: invalid class name and case insensitive filesystem */
public class C00950Hp {
    public final int A00;
    public final C000602o<String, File> A01;
    @Nullable
    public final String A02;
    public final boolean A03;

    public C00950Hp(int i, @Nullable String str, boolean z, C000602o<String, File> r6) {
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
