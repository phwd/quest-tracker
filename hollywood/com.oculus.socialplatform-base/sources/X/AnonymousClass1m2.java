package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1m2  reason: invalid class name */
public final class AnonymousClass1m2 {
    public final File A00;

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof AnonymousClass1m2)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass1m2) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass1m2(File file) {
        this.A00 = file;
    }
}
