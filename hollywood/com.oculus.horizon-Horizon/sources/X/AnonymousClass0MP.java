package X;

import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.logging.LoggingKeys;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0MP  reason: invalid class name */
public final class AnonymousClass0MP {
    public static final Uri A00 = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "display_photo");

    public static boolean A01(@Nullable Uri uri) {
        String scheme;
        if (uri == null) {
            scheme = null;
        } else {
            scheme = uri.getScheme();
        }
        return LoggingKeys.REFERRER_CONTENT.equals(scheme);
    }

    public static boolean A02(@Nullable Uri uri) {
        String scheme;
        if (uri == null) {
            scheme = null;
        } else {
            scheme = uri.getScheme();
        }
        if ("https".equals(scheme) || "http".equals(scheme)) {
            return true;
        }
        return false;
    }

    public static boolean A00(Uri uri) {
        String obj = uri.toString();
        if (obj.startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()) || obj.startsWith(MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString())) {
            return true;
        }
        return false;
    }
}
