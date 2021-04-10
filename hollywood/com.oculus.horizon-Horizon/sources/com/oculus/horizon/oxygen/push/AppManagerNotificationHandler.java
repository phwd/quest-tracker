package com.oculus.horizon.oxygen.push;

import X.AnonymousClass12f;
import X.AnonymousClass20E;
import com.facebook.ultralight.Dependencies;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.horizon.logging.OculusLogger;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_oxygen_preloads_sdk_firstparty_settings_FirstPartyMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_oxygen_sdk_status_PreloadSdkStatus_ULSEP_BINDING_ID"})
public class AppManagerNotificationHandler {
    public static final String PARAM_KEY_ACTION = "action";
    public static final String PARAM_KEY_EXECUTION_DELAY = "exec_delay";
    public static final String PARAM_KEY_PARAMS = "oxygen_params";
    public static final String PARAM_KEY_REQUEST_ID = "request_id";
    public static final String PARAM_KEY_WAKEUP_DEVICE = "wakeup_device";
    public static final String PARAM_VALUE_ACTION_FORCE_SYNC = "force_sync";
    public static final String SOFT_ERROR_FORCE_SYNC_UNEXPECTED_TERMINATION = "NotificationReceiver_FORCE_SYNC_UNEXPECTED_TERMINATION";
    public static final String SOFT_ERROR_PREFIX = "NotificationReceiver_";
    public static final String TAG = "AppManagerNotificationHandler";
    public IErrorReporter mErrorReporter;
    public AnonymousClass12f mFirstPartyMethods;
    public OculusLogger mOculusLogger;
    public AnonymousClass20E mPreloadSdkStatus;

    @Inject
    public AppManagerNotificationHandler(IErrorReporter iErrorReporter, AnonymousClass12f r2, OculusLogger oculusLogger, AnonymousClass20E r4) {
        this.mErrorReporter = iErrorReporter;
        this.mFirstPartyMethods = r2;
        this.mOculusLogger = oculusLogger;
        this.mPreloadSdkStatus = r4;
    }
}
