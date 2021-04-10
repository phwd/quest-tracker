package com.oculus.security.basecomponent;

import X.AbstractC02820as;
import X.AbstractC02990bJ;
import X.AnonymousClass0Lh;
import X.AnonymousClass0R7;
import X.AnonymousClass0Sp;
import X.C04940i6;
import X.C05130ih;
import X.C05200ip;
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
public class OculusIntentLogger extends AbstractC02820as {
    public static final String EVENT_NAME = "oculus_security_horizon_intent_logging";
    public static final String KEY_CALLER_IDENTITY = "caller_identity";
    public static final String KEY_ENDPOINT_NAME = "endpoint_name";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_STATUS = "status";
    public static final String KEY_SUB_ENDPOINT_NAME = "sub_endpoint_name";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // X.AbstractC02820as
    public final void A00(String str, @Nullable String str2, @Nullable String str3, @Nullable Intent intent) {
        String r1;
        String str4;
        if (((AnonymousClass0Sp) AnonymousClass0Lh.A03(0, 116, this._UL_mInjectionContext)).A36(MC.oculus_security_intent_logging.horizon_enabled)) {
            Event A1z = ((EventManager) AnonymousClass0Lh.A03(1, 103, this._UL_mInjectionContext)).A1z(EVENT_NAME);
            A1z.A0z("endpoint_name", str);
            if (str2 != null) {
                A1z.A0z(KEY_SUB_ENDPOINT_NAME, str2);
            }
            if (str3 != null) {
                A1z.A0z("status", str3);
            }
            if (intent != null) {
                C05130ih A00 = C05200ip.A00((Context) AnonymousClass0Lh.A03(2, 4, this._UL_mInjectionContext), intent, false, null);
                if (A00 == null) {
                    r1 = "null";
                } else {
                    r1 = A00.toString();
                }
                A1z.A0z("caller_identity", r1);
                try {
                    JSONObject jSONObject = C04940i6.A00(intent).A01;
                    if (jSONObject == null) {
                        str4 = "";
                    } else {
                        str4 = jSONObject.toString();
                    }
                    A1z.A0z(KEY_INTENT, str4);
                } catch (JSONException e) {
                    A1z.A0z(KEY_INTENT, e.toString());
                    A1z.A5i();
                    return;
                }
            }
            A1z.A5i();
        }
    }

    @Inject
    public OculusIntentLogger(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(3, r3);
    }
}
