package com.oculus.security.basecomponent;

import X.Om;
import X.SD;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class OculusIntentLogger extends SD {
    public static final String EVENT_NAME = "oculus_security_horizon_intent_logging";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SUB_ENDPOINT_NAME = "sub_endpoint_name";
    public Om _UL_mInjectionContext;

    @Inject
    public OculusIntentLogger(SZ sz) {
        this._UL_mInjectionContext = new Om(3, sz);
    }
}
