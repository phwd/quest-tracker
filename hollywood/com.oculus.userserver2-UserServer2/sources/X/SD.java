package X;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.logger.IntentLogger$Status;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.security.basecomponent.MC;
import com.oculus.security.basecomponent.OculusIntentLogger;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SD {
    public final void A00(String str, @Nullable String str2, @IntentLogger$Status @Nullable String str3, @Nullable Intent intent) {
        String obj;
        String str4;
        if (this instanceof OculusIntentLogger) {
            OculusIntentLogger oculusIntentLogger = (OculusIntentLogger) this;
            Om om = oculusIntentLogger._UL_mInjectionContext;
            if (((MC.oculus_security_intent_logging.horizon_enabled >> 61) & 1) == 1) {
                Event A1G = ((EventManager) BZ.A02(1, 43, om)).A1G(OculusIntentLogger.EVENT_NAME);
                A1G.A0m("endpoint_name", str);
                if (str2 != null) {
                    A1G.A0m(OculusIntentLogger.KEY_SUB_ENDPOINT_NAME, str2);
                }
                if (str3 != null) {
                    A1G.A0m(OculusIntentLogger.KEY_STATUS, str3);
                }
                if (intent != null) {
                    fX A00 = C0215ff.A00((Context) BZ.A02(2, 1, oculusIntentLogger._UL_mInjectionContext), intent, null);
                    if (A00 == null) {
                        obj = "null";
                    } else {
                        obj = A00.toString();
                    }
                    A1G.A0m("caller_identity", obj);
                    try {
                        JSONObject jSONObject = C0198eq.A00(intent).A01;
                        if (jSONObject == null) {
                            str4 = "";
                        } else {
                            str4 = jSONObject.toString();
                        }
                        A1G.A0m(OculusIntentLogger.KEY_INTENT, str4);
                    } catch (JSONException e) {
                        A1G.A0m(OculusIntentLogger.KEY_INTENT, e.toString());
                        A1G.A2K();
                        return;
                    }
                }
                A1G.A2K();
            }
        }
    }
}
