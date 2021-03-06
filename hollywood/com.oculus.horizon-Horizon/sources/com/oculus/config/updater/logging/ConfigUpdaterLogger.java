package com.oculus.config.updater.logging;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass117;
import X.C003008y;
import X.C01020Iw;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.deviceconfigclient.ConfigStorageCache;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import javax.inject.Provider;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class ConfigUpdaterLogger {
    public static final String CONFIG_UPDATE_DURATION_MS = "duration_ms";
    public static final String CONFIG_UPDATE_ERROR = "moonlight_config_update_error";
    public static final String CONFIG_UPDATE_LAST_TIME_MS = "last_time_ms";
    public static final String CONFIG_UPDATE_NO_NETWORK = "moonlight_config_update_no_network";
    public static final String CONFIG_UPDATE_SUCCESS = "moonlight_config_update_success";
    public static final String EVENT_VALUE = "event_value";
    public static final String PATH = "path";
    public static final String REQUEST_UUID = "request_uuid";
    public static final String RESPONSE_HEADERS = "response_headers";
    public AnonymousClass0QC _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_config_updater_logging_ConfigUpdaterLogger_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C003008y(228, r2);
    }

    @AutoGeneratedAccessMethod
    public static final ConfigUpdaterLogger _UL__ULSEP_com_oculus_config_updater_logging_ConfigUpdaterLogger_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (ConfigUpdaterLogger) AnonymousClass117.A00(228, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final ConfigUpdaterLogger _UL__ULSEP_com_oculus_config_updater_logging_ConfigUpdaterLogger_ULSEP_FACTORY_METHOD(AbstractC06640p5 r1) {
        return new ConfigUpdaterLogger(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_config_updater_logging_ConfigUpdaterLogger_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01020Iw(228, r2);
    }

    @VisibleForTesting
    public void logConfigUpdateFailed(ApiError apiError) {
        Response response;
        String lowerCase = apiError.type.name().toLowerCase();
        Throwable cause = apiError.getCause();
        String str = null;
        if ((cause instanceof RetrofitError) && (response = ((RetrofitError) cause).response) != null) {
            str = String.valueOf(response.headers);
        }
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(CONFIG_UPDATE_ERROR);
        A22.A15("event_value", lowerCase);
        if (str == null) {
            str = "";
        }
        A22.A15("response_headers", str);
        A22.A15("path", apiError.path);
        A22.A15("request_uuid", apiError.uuid);
        A22.A5L();
    }

    @VisibleForTesting
    public void logConfigUpdateNoNetwork(long j) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(CONFIG_UPDATE_NO_NETWORK);
        A22.A14("last_time_ms", j);
        A22.A5L();
    }

    @VisibleForTesting
    public void logConfigUpdateSuccess(long j, long j2) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(CONFIG_UPDATE_SUCCESS);
        A22.A14("duration_ms", Math.max(0L, j));
        A22.A14("last_time_ms", j2);
        A22.A5L();
    }

    public void logMobileConfigExposure(String str, String str2) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22("mobile_config_exposure_log");
        A22.A15("log_event", "exposure");
        A22.A15(ConfigStorageCache.PARAM_LOGGINGID_JSON_KEY, str);
        A22.A15("exposure_type", "auto");
        A22.A15("user_id", str2);
        A22.A5L();
    }

    public void logMobileConfigStatus(String str, String str2) {
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22("oculus_config");
        A22.A15("status", str);
        A22.A15("error", str2);
        A22.A5L();
    }

    @Inject
    public ConfigUpdaterLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
