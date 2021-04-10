package X;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;
import androidx.annotation.NonNull;

/* renamed from: X.04U  reason: invalid class name */
public final class AnonymousClass04U {
    public static int A00(@NonNull Context context, @NonNull String str) {
        int myPid = Process.myPid();
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, myPid, myUid) == -1) {
            return -1;
        }
        String permissionToOp = AppOpsManager.permissionToOp(str);
        if (permissionToOp != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(myUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            if (((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(permissionToOp, packageName) == 0) {
                return 0;
            }
            return -2;
        }
        return 0;
    }
}
