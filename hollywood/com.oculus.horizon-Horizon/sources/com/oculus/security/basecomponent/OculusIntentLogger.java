package com.oculus.security.basecomponent;

import X.AbstractC04590iB;
import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.C02790bO;
import X.C02800bY;
import X.C09231ab;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.security.basecomponent.MC;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class OculusIntentLogger extends AbstractC04590iB {
    public static final String EVENT_NAME = "oculus_security_horizon_intent_logging";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SUB_ENDPOINT_NAME = "sub_endpoint_name";
    public AnonymousClass0QC _UL_mInjectionContext;

    @Override // X.AbstractC04590iB
    public final void A00(String str, @Nullable String str2, @Nullable String str3, @Nullable Intent intent) {
        String obj;
        String str4;
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(0, 399, this._UL_mInjectionContext)).A36(MC.oculus_security_intent_logging.horizon_enabled)) {
            Event A22 = ((EventManager) AnonymousClass0J2.A03(1, 242, this._UL_mInjectionContext)).A22(EVENT_NAME);
            A22.A15("endpoint_name", str);
            if (str2 != null) {
                A22.A15(KEY_SUB_ENDPOINT_NAME, str2);
            }
            if (str3 != null) {
                A22.A15("status", str3);
            }
            if (intent != null) {
                C02790bO A00 = C02800bY.A00((Context) AnonymousClass0J2.A03(2, 294, this._UL_mInjectionContext), intent);
                if (A00 == null) {
                    obj = "null";
                } else {
                    obj = A00.toString();
                }
                A22.A15("caller_identity", obj);
                try {
                    JSONObject jSONObject = C09231ab.A00(intent).A01;
                    if (jSONObject == null) {
                        str4 = "";
                    } else {
                        str4 = jSONObject.toString();
                    }
                    A22.A15(KEY_INTENT, str4);
                } catch (JSONException e) {
                    A22.A15(KEY_INTENT, e.toString());
                    A22.A5L();
                    return;
                }
            }
            A22.A5L();
        }
    }

    @Inject
    public OculusIntentLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(3, r3);
    }
}
