package oculus.internal;

import android.content.Context;
import android.os.Binder;
import android.os.Process;

public class PermissionUtils {
    public static void checkCallingPermission(String tag, Context context, String permission) {
        if (Process.myPid() != Binder.getCallingPid() && context.checkCallingPermission(permission) != 0) {
            throw new SecurityException(String.format("%s: Permission denied: %s. callingUid: %d, callingPid: %d", tag, permission, Integer.valueOf(Binder.getCallingUid()), Integer.valueOf(Binder.getCallingPid())));
        }
    }
}
