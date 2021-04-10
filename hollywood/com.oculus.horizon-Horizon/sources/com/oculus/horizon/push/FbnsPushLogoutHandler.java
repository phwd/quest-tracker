package com.oculus.horizon.push;

import X.AnonymousClass0NO;
import X.AnonymousClass0WE;
import X.AnonymousClass0Wc;
import X.AnonymousClass0aL;
import X.C01890Xx;
import X.C02320aG;
import X.C06510nV;
import X.C06520nY;
import X.CallableC02300aD;
import X.EnumC02330aI;
import X.EnumC02400aQ;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.acra.AppComponentStats;
import com.facebook.inject.ForAppContext;
import com.facebook.push.fbns.ipc.FbnsAIDLRequest;
import com.facebook.rti.push.service.FbnsService;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.auth.handler.LogoutHandler;
import java.util.Iterator;
import java.util.List;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class FbnsPushLogoutHandler implements LogoutHandler {
    public final Context mContext;

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        Context context = this.mContext;
        String A01 = FbnsService.A01(null);
        String packageName = context.getPackageName();
        if (A01 == null) {
            A01 = FbnsService.A01(packageName);
        }
        Intent intent = new Intent("com.facebook.rti.fbns.intent.UNREGISTER");
        intent.setComponent(new ComponentName(packageName, A01));
        intent.putExtra("pkg_name", context.getPackageName());
        new C01890Xx(context).A03(intent);
        Context context2 = this.mContext;
        String A012 = FbnsService.A01(context2.getPackageName());
        try {
            int componentEnabledSetting = context2.getPackageManager().getComponentEnabledSetting(new ComponentName(context2, A012));
            if (componentEnabledSetting == 1 || componentEnabledSetting == 0) {
                String packageName2 = context2.getPackageName();
                try {
                    List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) AnonymousClass0Wc.A00.A00(context2, AppComponentStats.TAG_ACTIVITY, ActivityManager.class)).getRunningServices(Integer.MAX_VALUE);
                    if (runningServices != null) {
                        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ActivityManager.RunningServiceInfo next = it.next();
                            String className = next.service.getClassName();
                            String packageName3 = next.service.getPackageName();
                            if (A012.equals(className) && packageName2.equals(packageName3)) {
                                if (next.started) {
                                    Intent intent2 = new Intent("Orca.STOP");
                                    intent2.setComponent(new ComponentName(context2.getPackageName(), A012));
                                    new C01890Xx(context2).A03(intent2);
                                }
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    AnonymousClass0NO.A0H("RtiGracefulSystemMethodHelper", e, "Failed to getRunningServices");
                }
                AnonymousClass0aL.A02(context2, false, A012);
            }
        } catch (Exception unused) {
        }
        Context context3 = this.mContext;
        Bundle bundle = new Bundle();
        EnumC02400aQ.ANALYTIC_FB_UID.set(bundle, (Object) null);
        EnumC02400aQ.ANALYTIC_IS_EMPLOYEE.set(bundle, (Object) false);
        C02320aG r4 = new C02320aG(context3);
        r4.A04.submit(new CallableC02300aD(r4, new FbnsAIDLRequest[]{new FbnsAIDLRequest(bundle, EnumC02330aI.SET_ANALYTICS_CONFIG.toOperationType())}[0]));
        C06520nY A2L = new C06510nV(this.mContext).A00(AnonymousClass0WE.TOKEN_STORE).A2L();
        A2L.A00.clear();
        A2L.A00();
        this.mContext.getSharedPreferences(PushTokenSharedPreferencesHelper.SHARED_PREFS_NAME, 0).edit().clear().apply();
    }

    @Inject
    public FbnsPushLogoutHandler(@ForAppContext Context context) {
        this.mContext = context;
    }
}
