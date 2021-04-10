package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0W4  reason: invalid class name */
public final class AnonymousClass0W4 {
    public static boolean A00(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        return AnonymousClass0WG.A00.A00(context, AnonymousClass0WJ.A00.A00, false).getBoolean("fbns_secure_auth", false);
    }
}
