package com.oculus.horizon.logging;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.horizon.foreground.ApplicationForegroundListener;
import com.oculus.horizon.logging.LoggingModule;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class HorizonForegroundListener implements ApplicationForegroundListener {
    private InjectionContext _UL_mInjectionContext;
    private final OculusLogger mOculusLogger;
    private String mPackageName;
    private int mVersionCode = -1;
    private String mVersionName;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_logging_HorizonForegroundListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final HorizonForegroundListener _UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (HorizonForegroundListener) UL.factorymap.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final HorizonForegroundListener _UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new HorizonForegroundListener(injectorLike, BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(injectorLike), OculusLogger._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_logging_HorizonForegroundListener_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public HorizonForegroundListener(InjectorLike injectorLike, @ForAppContext Context context, OculusLogger oculusLogger) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
        this.mOculusLogger = oculusLogger;
        this.mPackageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.mPackageName, 0);
            this.mVersionName = packageInfo.versionName;
            this.mVersionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public void onApplicationForeground() {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).startFunnel(FunnelContract.NAV_TO_VR_FUNNEL_NAME);
        this.mOculusLogger.reportAppStart2D(this.mPackageName, this.mVersionName, this.mVersionCode);
    }

    @Override // com.oculus.horizon.foreground.ApplicationForegroundListener
    public void onApplicationBackground(long j) {
        this.mOculusLogger.reportAppEnd2D(this.mPackageName, this.mVersionName, this.mVersionCode, j);
    }
}