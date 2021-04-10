package com.oculus.horizon.logging;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.foreground.ApplicationForegroundListener;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class HorizonForegroundListener implements ApplicationForegroundListener {
    public AnonymousClass0QC _UL_mInjectionContext;
    public final OculusLogger mOculusLogger;
    public String mPackageName;
    public int mVersionCode = -1;
    public String mVersionName;

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A5i(long j) {
        OculusLogger oculusLogger = this.mOculusLogger;
        String str = this.mPackageName;
        String str2 = this.mVersionName;
        int i = this.mVersionCode;
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, oculusLogger._UL_mInjectionContext)).A22(LoggingEvents.APP_END_2D);
        A22.A15("app_package_name", str);
        A22.A15(LoggingKeys.APP_VERSION_NAME, str2);
        A22.A13(LoggingKeys.APP_VERSION_CODE, i);
        A22.A14(LoggingKeys.ACTIVE_DURATION_MS, j);
        A22.A5L();
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public final void A5j() {
        ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A9H(FunnelContract.NAV_TO_VR_FUNNEL_NAME);
        OculusLogger oculusLogger = this.mOculusLogger;
        String str = this.mPackageName;
        String str2 = this.mVersionName;
        int i = this.mVersionCode;
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, oculusLogger._UL_mInjectionContext)).A22(LoggingEvents.APP_START_2D);
        A22.A15("app_package_name", str);
        A22.A15(LoggingKeys.APP_VERSION_NAME, str2);
        A22.A13(LoggingKeys.APP_VERSION_CODE, i);
        A22.A5L();
    }

    @Inject
    public HorizonForegroundListener(AbstractC06640p5 r4, @ForAppContext Context context, OculusLogger oculusLogger) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r4);
        this.mOculusLogger = oculusLogger;
        this.mPackageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.mPackageName, 0);
            this.mVersionName = packageInfo.versionName;
            this.mVersionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
