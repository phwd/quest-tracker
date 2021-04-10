package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.DeadObjectException;
import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Qn  reason: invalid class name */
public final class AnonymousClass1Qn {
    public static AnonymousClass1Qn A03;
    public final String A00;
    public final String A01;
    public final boolean A02;

    public AnonymousClass1Qn(Context context) {
        ApplicationInfo applicationInfo;
        boolean z;
        String packageName = context.getPackageName();
        boolean z2 = false;
        AnonymousClass1Ia r4 = new AnonymousClass1Ia(packageName);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            r4.A01 = packageInfo;
            if (!(packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null)) {
                if (applicationInfo.enabled) {
                    C06171Qx r1 = (C06171Qx) ((AbstractC06121Qp) AnonymousClass1R0.A00);
                    if (r1.A01.equals(packageName) || r1.A00.equals(packageName)) {
                        z = true;
                    } else {
                        try {
                            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(new Intent("com.facebook.rti.fbns.intent.RECEIVE").setPackage(packageName), 0);
                            if (queryBroadcastReceivers != null) {
                                z = Boolean.valueOf(!queryBroadcastReceivers.isEmpty());
                            }
                        } catch (RuntimeException e) {
                            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e, "Failed to queryBroadcastReceivers");
                            if (!(e.getCause() instanceof DeadObjectException)) {
                                throw e;
                            }
                        }
                        z = null;
                    }
                    if (Boolean.TRUE.equals(z)) {
                        r4.A02 = AnonymousClass007.A05;
                    } else if (Boolean.FALSE.equals(z)) {
                        r4.A02 = AnonymousClass007.A04;
                    }
                } else {
                    r4.A02 = AnonymousClass007.A03;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
            r4.A02 = AnonymousClass007.A01;
        } catch (RuntimeException e2) {
            AnonymousClass0MD.A0C("RtiGracefulSystemMethodHelper", e2, "Failed to getPackageInfo");
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
        }
        String str = "1";
        String str2 = "unknown";
        if (r4.A01 != null) {
            this.A01 = !TextUtils.isEmpty(r4.A01.versionName) ? r4.A01.versionName : str2;
            if (r4.A01.versionCode > 0) {
                str = String.valueOf(AnonymousClass0HJ.A01());
            }
        } else {
            this.A01 = str2;
        }
        this.A00 = str;
        try {
            z2 = AnonymousClass0k6.A10.contains(AnonymousClass0kC.A03(context, context.getPackageName()));
        } catch (SecurityException e3) {
            if (e3.getCause() == null) {
                throw e3;
            } else if (!(e3.getCause().getCause() instanceof DeadObjectException)) {
                throw e3;
            }
        }
        this.A02 = z2;
    }
}
