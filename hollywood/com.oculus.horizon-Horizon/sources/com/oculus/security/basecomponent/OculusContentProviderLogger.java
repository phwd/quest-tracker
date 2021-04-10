package com.oculus.security.basecomponent;

import X.AbstractC02640aw;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.security.basecomponent.MC;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
public class OculusContentProviderLogger implements AbstractC02640aw {
    public static final String EVENT_NAME = "oculus_security_content_provider";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_METHOD = "method";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // X.AbstractC02640aw
    public final void A5T(String str, String str2) {
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_security_content_provider_logging.enabled)) {
            Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(EVENT_NAME);
            A22.A15("endpoint_name", str);
            A22.A15("method", str2);
            A22.A5L();
        }
    }

    @Override // X.AbstractC02640aw
    public final void A5U(String str, String str2, String str3) {
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_security_content_provider_logging.enabled)) {
            Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(EVENT_NAME);
            A22.A15("endpoint_name", str);
            A22.A15("caller_identity", str3);
            A22.A15("method", str2);
            A22.A5L();
        }
    }

    @Inject
    public OculusContentProviderLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r3);
    }
}
