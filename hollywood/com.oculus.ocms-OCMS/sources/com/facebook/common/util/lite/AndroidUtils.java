package com.facebook.common.util.lite;

import android.os.Build;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AndroidUtils {
    private AndroidUtils() {
    }

    public static boolean isAndroidVersionAtLeast(int i) {
        return Build.VERSION.SDK_INT >= i;
    }
}
