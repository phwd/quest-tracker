package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceUnlockStatusModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = DeviceUnlockStatusModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract boolean marshallJSConstantIsDeviceUnlocked();

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        return new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("IS_DEVICE_UNLOCKED", marshallJSConstantIsDeviceUnlocked());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    public void shutdownModule() {
    }
}
