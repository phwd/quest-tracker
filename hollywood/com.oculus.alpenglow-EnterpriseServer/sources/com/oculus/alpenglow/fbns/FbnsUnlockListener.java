package com.oculus.alpenglow.fbns;

import X.AnonymousClass10W;
import X.C07800w2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.facebook.rti.push.service.FbnsService;
import com.facebook.ultralight.Dependencies;
import com.oculus.alpenglow.boot.UnlockListener;
import com.oculus.alpenglow.lifecycle.StartupListener;
import com.oculus.util.constants.OculusConstants;

@Dependencies({})
public class FbnsUnlockListener implements UnlockListener, StartupListener {
    public static final String TAG = "EnterpriseServer.FbnsUnlockListener";

    public static void A00(Context context) {
        String name = FbnsUnlockListener.class.getName();
        String packageName = context.getPackageName();
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        AnonymousClass10W.A00(context, FbnsService.A01(packageName), name, false, packageName);
        String packageName2 = context.getPackageName();
        if (packageName2 == null) {
            packageName2 = context.getPackageName();
        }
        AnonymousClass10W.A00(context, FbnsService.A01(packageName2), "onUserUnlocked", true, packageName2);
        String packageName3 = context.getPackageName();
        String A01 = FbnsService.A01(packageName3);
        C07800w2 r3 = new C07800w2(context);
        if (!TextUtils.isEmpty(OculusConstants.ALPENGLOW_HW_APP_ID)) {
            if (packageName3 == null) {
                packageName3 = context.getPackageName();
            }
            if (context.getPackageName().equals(packageName3)) {
                ComponentName componentName = new ComponentName(context, A01);
                context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
                componentName.getShortClassName();
            }
            Intent intent = new Intent("com.facebook.rti.fbns.intent.REGISTER");
            intent.setComponent(new ComponentName(packageName3, A01));
            intent.putExtra("pkg_name", context.getPackageName());
            intent.putExtra("appid", OculusConstants.ALPENGLOW_HW_APP_ID);
            String packageName4 = intent.getComponent().getPackageName();
            if (packageName4 != null && r3.A03(packageName4)) {
                r3.A02(intent);
                r3.A01.A04(r3.A00, intent);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Missing appId");
    }

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        A00(context);
    }

    @Override // com.oculus.alpenglow.boot.UnlockListener
    public final void A6h(Context context) {
        A00(context);
    }
}
