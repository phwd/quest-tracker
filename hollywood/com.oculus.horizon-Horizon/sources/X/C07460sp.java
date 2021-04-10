package X;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

/* renamed from: X.0sp  reason: invalid class name and case insensitive filesystem */
public final class C07460sp extends AnonymousClass04J {
    public static void A02(@NonNull Activity activity, @NonNull String[] strArr, @IntRange(from = 0) int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity instanceof AnonymousClass036) {
                ((AnonymousClass036) activity).validateRequestPermissionsRequestCode(i);
            }
            activity.requestPermissions(strArr, i);
        } else if (activity instanceof AnonymousClass034) {
            new Handler(Looper.getMainLooper()).post(new AnonymousClass032(strArr, activity, i));
        }
    }

    public static boolean A03(@NonNull Activity activity, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return activity.shouldShowRequestPermissionRationale(str);
        }
        return false;
    }
}
