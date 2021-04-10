package com.oculus.appsafety.signals;

import android.content.Context;
import android.os.SystemProperties;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;

public class ShellPropsSafetySignal implements ISafetySignal {
    private static final boolean DEBUG = false;
    private static final String DEFAULT = "unknown";
    private static final String TAG = ShellPropsSafetySignal.class.getSimpleName();
    public static final String TELEMETRY_KEY = "shell_properties";
    private Map<String, String> shellProps;

    private ShellPropsSafetySignal(Context context, JsonObject properties) {
        this.shellProps = (Map) new Gson().fromJson((JsonElement) properties, Map.class);
    }

    @Override // com.oculus.appsafety.signals.ISafetySignal
    public String getTelemetryKey() {
        return TELEMETRY_KEY;
    }

    @Override // com.oculus.appsafety.signals.ISafetySignal
    public JsonObject collect() {
        JsonObject extras = new JsonObject();
        for (Map.Entry<String, String> property : this.shellProps.entrySet()) {
            extras.addProperty(property.getKey(), SystemProperties.get(property.getValue(), DEFAULT));
        }
        return extras;
    }
}
