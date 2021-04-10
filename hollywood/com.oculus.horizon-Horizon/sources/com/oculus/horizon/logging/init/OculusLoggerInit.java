package com.oculus.horizon.logging.init;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.common.init.INeedInit;
import com.oculus.config.updater.ConfigUpdater;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.locale.LocaleModule;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.util.device.DeviceUtils;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusLoggerInit implements INeedInit {
    public static final String APP_LOCALE = "app_locale";
    public static final String BUILD_BRAND = "build_brand";
    public static final String BUILD_DISPLAY = "build_display";
    public static final String BUILD_FINGERPRINT = "build_fingerprint";
    public static final String BUILD_ID = "build_id";
    public static final String BUILD_MODEL = "build_model";
    public static final String BUILD_VERSION_SDK_INT = "build_sdk_int";
    public static final String COLD_START_EVENT = "moonlight_cold_start";
    public static final String CONFIG_UPDATE_LAST_TIME_MS = "last_time_ms";
    public static final String DEVICE_LOCALE = "device_locale";
    public static final String DEVICE_TIME_ZONE = "device_time_zone";
    public static final String EXTERNAL_STORAGE_STATE = "external_storage";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final ConfigUpdater mConfigUpdater;
    @Inject
    public final Provider<Locale> mLocaleProvider;

    @Override // com.oculus.common.init.INeedInit
    public final void init() {
        ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A84(FunnelContract.NAV_TO_VR_FUNNEL_NAME, 60);
        ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A84(FunnelContract.CAST_SERVER_SESSION_FUNNEL_NAME, (int) TimeUnit.DAYS.toSeconds(1));
        ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A84(FunnelContract.CAST_TO_BROWSER_SERVER_SESSION_FUNNEL_NAME, (int) TimeUnit.DAYS.toSeconds(1));
        ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A84(FunnelContract.CAPTURE_FUNNEL_NAME, (int) TimeUnit.DAYS.toSeconds(1));
        ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A84(FunnelContract.LOGIN_APPROVALS_FUNNEL_NAME, (int) TimeUnit.DAYS.toSeconds(1));
        Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(COLD_START_EVENT);
        A22.A1G();
        A22.A15(APP_LOCALE, this.mLocaleProvider.get().toString());
        A22.A15(DEVICE_LOCALE, Locale.getDefault().toString());
        A22.A15(BUILD_MODEL, DeviceUtils.A02());
        A22.A15(BUILD_ID, Build.ID);
        A22.A15(BUILD_BRAND, Build.BRAND);
        A22.A13(BUILD_VERSION_SDK_INT, Build.VERSION.SDK_INT);
        A22.A15(BUILD_FINGERPRINT, Build.FINGERPRINT);
        A22.A15(BUILD_DISPLAY, Build.DISPLAY);
        A22.A15(DEVICE_TIME_ZONE, TimeZone.getDefault().getID());
        A22.A15(EXTERNAL_STORAGE_STATE, Environment.getExternalStorageState());
        A22.A14("last_time_ms", this.mConfigUpdater.getTimeSinceLastUpdate());
        A22.A5L();
        new FunnelHMTStatusReceiver().registerReceiver((Context) AnonymousClass0J2.A03(0, 294, this._UL_mInjectionContext));
    }

    @Inject
    public OculusLoggerInit(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
        this.mLocaleProvider = LocaleModule.A01(r3);
        this.mConfigUpdater = ConfigUpdater._UL__ULSEP_com_oculus_config_updater_ConfigUpdater_ULSEP_ACCESS_METHOD(r3);
    }
}
