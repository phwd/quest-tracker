package X;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Process;
import androidx.annotation.NonNull;

/* renamed from: X.0ew  reason: invalid class name and case insensitive filesystem */
public class C04460ew extends AbstractC000302q {
    public final AnonymousClass035 A00;
    public final /* synthetic */ LayoutInflater$Factory2C04430et A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04460ew(@NonNull LayoutInflater$Factory2C04430et r1, AnonymousClass035 r2) {
        super(r1);
        this.A01 = r1;
        this.A00 = r2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d  */
    @Override // X.AbstractC000302q
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A03() {
        /*
        // Method dump skipped, instructions count: 222
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04460ew.A03():int");
    }

    @Override // X.AbstractC000302q
    public final IntentFilter A04() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        return intentFilter;
    }

    @Override // X.AbstractC000302q
    public final void A05() {
        LayoutInflater$Factory2C04430et.A0D(this.A01, true);
    }

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
