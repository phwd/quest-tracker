package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oH  reason: invalid class name */
public final class AnonymousClass1oH {
    public final File A00;

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof AnonymousClass1oH)) {
            return false;
        }
        return this.A00.equals(((AnonymousClass1oH) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass1oH(File file) {
        this.A00 = file;
    }
}
