package X;

import android.app.Activity;
import android.os.Build;
import androidx.annotation.NonNull;

/* renamed from: X.0dg  reason: invalid class name and case insensitive filesystem */
public final class C03930dg extends AnonymousClass07g {
    public static void A00(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT >= 28 || !C006406b.A00(activity)) {
            activity.recreate();
        }
    }
}
