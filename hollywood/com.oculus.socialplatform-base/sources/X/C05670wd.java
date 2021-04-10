package X;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.0wd  reason: invalid class name and case insensitive filesystem */
public class C05670wd extends AnonymousClass04O {
    public static AnonymousClass03A sDelegate;

    public static void recreate(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= 28 || !AnonymousClass03J.A00(activity)) {
            activity.recreate();
        }
    }

    public static void requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, @IntRange(from = 0) int i) {
        if (activity instanceof AnonymousClass03B) {
            ((AnonymousClass03B) activity).validateRequestPermissionsRequestCode(i);
        }
        activity.requestPermissions(strArr, i);
    }

    public static void setEnterSharedElementCallback(@NonNull Activity activity, @Nullable AnonymousClass04H r2) {
        AnonymousClass03C r0;
        if (r2 != null) {
            r0 = new AnonymousClass03C(r2);
        } else {
            r0 = null;
        }
        activity.setEnterSharedElementCallback(r0);
    }

    public static void setExitSharedElementCallback(@NonNull Activity activity, @Nullable AnonymousClass04H r2) {
        AnonymousClass03C r0;
        if (r2 != null) {
            r0 = new AnonymousClass03C(r2);
        } else {
            r0 = null;
        }
        activity.setExitSharedElementCallback(r0);
    }
}
