package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat {
    private static PermissionCompatDelegate sDelegate;

    public interface OnRequestPermissionsResultCallback {
    }

    public interface PermissionCompatDelegate {
        boolean onActivityResult(Activity activity, int i, int i2, Intent intent);
    }

    public interface RequestPermissionsRequestCodeValidator {
    }

    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }
}
