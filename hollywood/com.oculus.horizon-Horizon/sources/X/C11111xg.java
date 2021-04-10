package X;

import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xg  reason: invalid class name and case insensitive filesystem */
public final class C11111xg {
    public final String A00() {
        if (Build.VERSION.SDK_INT >= 24) {
            return "baseline";
        }
        return "high";
    }
}
