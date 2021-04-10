package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.rti.push.service.FbnsService;
import com.oculus.partystatemanager.logging.PartyEventFields;
import javax.annotation.Nullable;

/* renamed from: X.0aL  reason: invalid class name */
public final class AnonymousClass0aL {
    public static void A01(Context context, String str, @Nullable String str2, boolean z, String str3) {
        if (z && context.getPackageName().equals(str3)) {
            A02(context, true, str);
        }
        ComponentName componentName = new ComponentName(str3, str);
        Intent intent = new Intent("Orca.START");
        intent.setComponent(componentName);
        if (str2 != null) {
            intent.putExtra(PartyEventFields.CALLER, str2);
        }
        new C01890Xx(context).A03(intent);
    }

    public static void A02(Context context, boolean z, String str) {
        ComponentName componentName = new ComponentName(context, str);
        PackageManager packageManager = context.getPackageManager();
        int i = 2;
        if (z) {
            i = 1;
        }
        packageManager.setComponentEnabledSetting(componentName, i, 1);
        componentName.getShortClassName();
    }

    public static void A00(Context context, String str) {
        String packageName = context.getPackageName();
        String A01 = FbnsService.A01(packageName);
        C01890Xx r3 = new C01890Xx(context);
        if (!TextUtils.isEmpty(str)) {
            if (packageName == null) {
                packageName = context.getPackageName();
            }
            if (context.getPackageName().equals(packageName)) {
                A02(context, true, A01);
            }
            Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
            intent.setComponent(new ComponentName(packageName, A01));
            intent.putExtra("pkg_name", context.getPackageName());
            intent.putExtra("appid", str);
            r3.A03(intent);
            return;
        }
        throw new IllegalArgumentException("Missing appId");
    }
}
