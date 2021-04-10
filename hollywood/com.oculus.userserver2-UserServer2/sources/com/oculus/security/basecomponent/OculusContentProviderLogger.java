package com.oculus.security.basecomponent;

import X.AbstractC0199er;
import X.BZ;
import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.security.basecomponent.MC;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusContentProviderLogger implements AbstractC0199er {
    public static final String EVENT_NAME = "oculus_security_content_provider";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_METHOD = "method";
    public Om _UL_mInjectionContext;

    @Override // X.AbstractC0199er
    public final void A2L(String str, String str2) {
        Om om = this._UL_mInjectionContext;
        if (((MC.oculus_security_content_provider_logging.enabled >> 61) & 1) == 1) {
            Event A1G = ((EventManager) BZ.A02(1, 43, om)).A1G(EVENT_NAME);
            A1G.A0m("endpoint_name", str);
            A1G.A0m(KEY_METHOD, str2);
            A1G.A2K();
        }
    }

    @Override // X.AbstractC0199er
    public final void A2M(String str, String str2, String str3) {
        Om om = this._UL_mInjectionContext;
        if (((MC.oculus_security_content_provider_logging.enabled >> 61) & 1) == 1) {
            Event A1G = ((EventManager) BZ.A02(1, 43, om)).A1G(EVENT_NAME);
            A1G.A0m("endpoint_name", str);
            A1G.A0m("caller_identity", str3);
            A1G.A0m(KEY_METHOD, str2);
            A1G.A2K();
        }
    }

    @Inject
    public OculusContentProviderLogger(SZ sz) {
        this._UL_mInjectionContext = new Om(2, sz);
    }
}
