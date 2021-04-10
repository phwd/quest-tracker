package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;

public class ActivityCompat extends ContextCompat {
    private static PermissionCompatDelegate sDelegate;

    public interface PermissionCompatDelegate {
        boolean onActivityResult(@NonNull Activity activity, @IntRange int i, int i2, @Nullable Intent intent);
    }

    protected ActivityCompat() {
    }

    @RestrictTo
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }
}
