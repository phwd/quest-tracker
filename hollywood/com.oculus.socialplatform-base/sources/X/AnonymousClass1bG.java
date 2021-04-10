package X;

import android.net.Uri;
import com.oculus.horizon.logging.LoggingKeys;

/* renamed from: X.1bG  reason: invalid class name */
public final class AnonymousClass1bG {
    public static boolean A00(Uri uri) {
        if (uri == null || !LoggingKeys.REFERRER_CONTENT.equals(uri.getScheme()) || !"media".equals(uri.getAuthority())) {
            return false;
        }
        return true;
    }
}
