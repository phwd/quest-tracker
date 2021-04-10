package X;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

/* renamed from: X.04J  reason: invalid class name */
public class AnonymousClass04J {
    public static final Object A00 = new Object();

    @ColorInt
    public static int A00(@NonNull Context context, @ColorRes int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i);
        }
        return context.getResources().getColor(i);
    }

    public static int A01(@NonNull Context context, @NonNull String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}
