package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.STRICT)
@Immutable
/* renamed from: X.1jz  reason: invalid class name */
public final class AnonymousClass1jz {
    @Nullable
    public static Pattern A02;
    public final int A00;
    public final int A01;

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass1jz)) {
            return false;
        }
        AnonymousClass1jz r4 = (AnonymousClass1jz) obj;
        return this.A00 == r4.A00 && this.A01 == r4.A01;
    }

    public final String toString() {
        String num;
        String num2;
        Object[] objArr = new Object[2];
        int i = this.A00;
        if (i == Integer.MAX_VALUE) {
            num = "";
        } else {
            num = Integer.toString(i);
        }
        objArr[0] = num;
        int i2 = this.A01;
        if (i2 == Integer.MAX_VALUE) {
            num2 = "";
        } else {
            num2 = Integer.toString(i2);
        }
        objArr[1] = num2;
        return String.format(null, "%s-%s", objArr);
    }

    public final int hashCode() {
        return ((this.A00 + 31) * 31) + this.A01;
    }

    public AnonymousClass1jz(int i, int i2) {
        this.A00 = i;
        this.A01 = i2;
    }
}
