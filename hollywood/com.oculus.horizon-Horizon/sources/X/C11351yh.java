package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Arrays;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1yh  reason: invalid class name and case insensitive filesystem */
public final class C11351yh {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final int A05;

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C11351yh r5 = (C11351yh) obj;
            if (!(this.A05 == r5.A05 && this.A00 == r5.A00 && this.A04 == r5.A04 && this.A01 == r5.A01 && this.A02 == r5.A02 && this.A03 == r5.A03)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{64000, Integer.valueOf(this.A05), 1, Integer.valueOf(this.A00), Integer.valueOf(this.A04), Integer.valueOf(this.A01), Integer.valueOf(this.A02), Integer.valueOf(this.A03)});
    }

    public C11351yh(AnonymousClass1z4 r2) {
        this.A05 = r2.A05;
        this.A00 = r2.A00;
        this.A04 = r2.A04;
        this.A01 = r2.A01;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
    }
}
