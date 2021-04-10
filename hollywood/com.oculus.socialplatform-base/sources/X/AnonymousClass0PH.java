package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.STRICT)
@Immutable
/* renamed from: X.0PH  reason: invalid class name */
public final class AnonymousClass0PH {
    @Nullable
    public static Pattern A02;
    public final int A00;
    public final int A01;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0PH)) {
            return false;
        }
        AnonymousClass0PH r4 = (AnonymousClass0PH) obj;
        return this.A00 == r4.A00 && this.A01 == r4.A01;
    }

    public final String toString() {
        String num;
        String num2;
        int i = this.A00;
        if (i == Integer.MAX_VALUE) {
            num = "";
        } else {
            num = Integer.toString(i);
        }
        int i2 = this.A01;
        if (i2 == Integer.MAX_VALUE) {
            num2 = "";
        } else {
            num2 = Integer.toString(i2);
        }
        return String.format(null, "%s-%s", num, num2);
    }

    public final int hashCode() {
        return ((this.A00 + 31) * 31) + this.A01;
    }

    public AnonymousClass0PH(int i, int i2) {
        this.A00 = i;
        this.A01 = i2;
    }
}
