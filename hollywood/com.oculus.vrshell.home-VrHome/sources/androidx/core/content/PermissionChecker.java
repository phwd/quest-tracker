package androidx.core.content;

import android.content.Context;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;

public final class PermissionChecker {
    public static int checkPermission(Context context, String permission, int pid, int uid, String packageName) {
        if (context.checkPermission(permission, pid, uid) == -1) {
            return -1;
        }
        String op = AppOpsManagerCompat.permissionToOp(permission);
        if (op == null) {
            return 0;
        }
        if (packageName == null) {
            String[] packageNames = context.getPackageManager().getPackagesForUid(uid);
            if (packageNames == null || packageNames.length <= 0) {
                return -1;
            }
            packageName = packageNames[0];
        }
        if (AppOpsManagerCompat.noteProxyOpNoThrow(context, op, packageName) != 0) {
            return -2;
        }
        return 0;
    }

    public static int checkSelfPermission(Context context, String permission) {
        return checkPermission(context, permission, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
