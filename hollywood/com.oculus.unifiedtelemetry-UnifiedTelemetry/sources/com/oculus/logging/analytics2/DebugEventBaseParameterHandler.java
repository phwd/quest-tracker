package com.oculus.logging.analytics2;

import X.Mu;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DebugEventBaseParameterHandler {
    public static final String TAG = "DebugEventBaseParameterHandler";
    public final JSONObject mBaseParameters = new JSONObject();

    public static class ParamKeys {
        public static final String EVENT_NAME = "name";
        public static final String EXTRA = "extra";
        public static final String MODULE_NAME = "module";
        public static final String TIME = "time";
    }

    public final void A00(String str, @Nullable Object obj) {
        if (obj != null) {
            try {
                this.mBaseParameters.put(str, obj);
            } catch (JSONException e) {
                Mu.A0B(TAG, e, "Failed to add base parameter: %1$s", str);
            }
        }
    }
}
