package X;

import androidx.annotation.VisibleForTesting;
import java.io.File;

@VisibleForTesting
/* renamed from: X.1lt  reason: invalid class name and case insensitive filesystem */
public class C10361lt {
    public long A00;
    public long A01;
    public final AnonymousClass1m2 A02;
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

    public C10361lt(String str, File file) {
        if (file == null) {
            throw null;
        } else if (str != null) {
            this.A03 = str;
            this.A02 = new AnonymousClass1m2(file);
            this.A00 = -1;
            this.A01 = -1;
        } else {
            throw null;
        }
    }
}
