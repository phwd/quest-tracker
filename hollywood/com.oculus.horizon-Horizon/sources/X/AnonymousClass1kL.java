package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.1kL  reason: invalid class name */
public final class AnonymousClass1kL {
    public long A00;
    public long A01;

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass1kL r7 = (AnonymousClass1kL) obj;
            if (!(this.A00 == r7.A00 && this.A01 == r7.A01)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.A00), Long.valueOf(this.A01)});
    }
}
