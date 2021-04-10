package com.oculus.security.basecomponent;

import X.AbstractC02990bJ;
import X.AnonymousClass0R7;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusContentProviderLogger {
    public static final String EVENT_NAME = "oculus_security_content_provider";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_METHOD = "method";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Inject
    public OculusContentProviderLogger(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}
