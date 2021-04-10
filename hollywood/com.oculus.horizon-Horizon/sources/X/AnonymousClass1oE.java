package X;

import androidx.annotation.VisibleForTesting;
import java.io.File;

@VisibleForTesting
/* renamed from: X.1oE  reason: invalid class name */
public class AnonymousClass1oE {
    public long A00;
    public long A01;
    public final AnonymousClass1oH A02;
    public final String A03;

    public final long A00() {
        long j = this.A01;
        if (j >= 0) {
            return j;
        }
        long lastModified = this.A02.A00.lastModified();
        this.A01 = lastModified;
        return lastModified;
    }

    public AnonymousClass1oE(String str, File file) {
        if (file == null || str == null) {
            throw null;
        }
        this.A03 = str;
        this.A02 = new AnonymousClass1oH(file);
        this.A00 = -1;
        this.A01 = -1;
    }
}
