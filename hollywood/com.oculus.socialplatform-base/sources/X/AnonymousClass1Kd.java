package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Kd  reason: invalid class name */
public abstract class AnonymousClass1Kd implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.msys.mci.common.NamedRunnable";
    @Nullable
    public final String A00;

    public final String toString() {
        String str = this.A00;
        if (str == null) {
            return super.toString();
        }
        return str;
    }

    public AnonymousClass1Kd(@Nullable String str) {
        this.A00 = str;
    }
}
