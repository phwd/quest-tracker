package X;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.concurrent.Executor;

/* renamed from: X.04O  reason: invalid class name */
public class AnonymousClass04O {
    public static final String TAG = "ContextCompat";
    public static final Object sLock = new Object();
    public static TypedValue sTempValue;

    public static int checkSelfPermission(@NonNull Context context, @NonNull String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }

    public static synchronized File createFilesDir(File file) {
        synchronized (AnonymousClass04O.class) {
            if (!file.exists() && !file.mkdirs() && !file.exists()) {
                Log.w(TAG, AnonymousClass006.A07("Unable to create files subdir ", file.getPath()));
                file = null;
            }
        }
        return file;
    }

    public static Executor getMainExecutor(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return context.getMainExecutor();
        }
        return new AnonymousClass04N(new Handler(context.getMainLooper()));
    }

    public static void startForegroundService(@NonNull Context context, @NonNull Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context) {
        return context.createDeviceProtectedStorageContext();
    }

    public static File getCodeCacheDir(@NonNull Context context) {
        return context.getCodeCacheDir();
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int i) {
        return context.getColor(i);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i) {
        return context.getColorStateList(i);
    }

    @Nullable
    public static File getDataDir(@NonNull Context context) {
        return context.getDataDir();
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        return context.getDrawable(i);
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context) {
        return context.getExternalCacheDirs();
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String str) {
        return context.getExternalFilesDirs(str);
    }

    @Nullable
    public static File getNoBackupFilesDir(@NonNull Context context) {
        return context.getNoBackupFilesDir();
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context) {
        return context.getObbDirs();
    }

    @Nullable
    public static <T> T getSystemService(@NonNull Context context, @NonNull Class<T> cls) {
        return (T) context.getSystemService(cls);
    }

    @Nullable
    public static String getSystemServiceName(@NonNull Context context, @NonNull Class<?> cls) {
        return context.getSystemServiceName(cls);
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context) {
        return context.isDeviceProtectedStorage();
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle bundle) {
        context.startActivity(intent, bundle);
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intentArr) {
        context.startActivities(intentArr, null);
        return true;
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }
}
