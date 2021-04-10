package android.support.v4.content;

import android.content.Context;
import android.os.Build;
import java.io.File;

public class ContextCompat {
    private static final Object sLock = new Object();

    public static File[] getExternalFilesDirs(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalFilesDirs(str);
        }
        return new File[]{context.getExternalFilesDir(str)};
    }

    public static File[] getExternalCacheDirs(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalCacheDirs();
        }
        return new File[]{context.getExternalCacheDir()};
    }
}
