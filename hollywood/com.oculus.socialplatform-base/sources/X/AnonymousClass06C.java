package X;

import android.net.Uri;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* renamed from: X.06C  reason: invalid class name */
public class AnonymousClass06C {
    public final int A00;
    public final int A01;
    public final int A02;
    public final Uri A03;
    public final boolean A04;

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass06C(@NonNull Uri uri, @IntRange(from = 0) int i, @IntRange(from = 1, to = 1000) int i2, boolean z, int i3) {
        if (uri != null) {
            this.A03 = uri;
            this.A01 = i;
            this.A02 = i2;
            this.A04 = z;
            this.A00 = i3;
            return;
        }
        throw null;
    }
}
