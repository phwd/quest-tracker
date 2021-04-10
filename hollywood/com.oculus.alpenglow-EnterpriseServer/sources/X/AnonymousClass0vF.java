package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vF  reason: invalid class name */
public final class AnonymousClass0vF {
    public static boolean A00(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(C07680vm.A00.A00, 0).getBoolean("fbns_secure_auth", false);
    }
}
