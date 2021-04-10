package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

/* renamed from: X.10W  reason: invalid class name */
public final class AnonymousClass10W {
    public static void A00(Context context, String str, @Nullable String str2, boolean z, String str3) {
        if (z && context.getPackageName().equals(str3)) {
            ComponentName componentName = new ComponentName(context, str);
            context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
            componentName.getShortClassName();
        }
        ComponentName componentName2 = new ComponentName(str3, str);
        Intent intent = new Intent("Orca.START");
        intent.setComponent(componentName2);
        if (str2 != null) {
            intent.putExtra("caller", str2);
        }
        C07800w2 r2 = new C07800w2(context);
        String packageName = intent.getComponent().getPackageName();
        if (packageName != null && r2.A03(packageName)) {
            r2.A02(intent);
            r2.A01.A04(r2.A00, intent);
        }
    }
}
