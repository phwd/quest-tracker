package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WifiManagerModule extends AbstractBroadcastReceiverModule {
    public static final String MODULE_NAME = "WifiManagerModule";

    public static class WifiUpdate extends NativeModuleParcel {
        public double level;
        public String ssid;
        public double state;

        public static final WifiUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            WifiUpdate wifiUpdate = new WifiUpdate();
            wifiUpdate.setFromJSONObject(jSONObject);
            return wifiUpdate;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("level", this.level);
                jSONObject.put("ssid", this.ssid);
                jSONObject.put("state", this.state);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.level = jSONObject.optDouble("level", 0.0d);
            this.ssid = jSONObject.optString("ssid");
            this.state = jSONObject.optDouble("state", 0.0d);
        }
    }

    public abstract void getConnectedSSIDImpl(Resolver<String> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModuleImpl() {
    }

    public final void emitOnWifiUpdated(WifiUpdate wifiUpdate) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "WifiManagerModule_onWifiUpdated", String.valueOf(wifiUpdate.convertToJSONObject()));
    }

    public static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> describe = AbstractBroadcastReceiverModule.describe();
        describe.add(new Pair<>("getConnectedSSID", "+ii"));
        return describe;
    }

    public final void getConnectedSSID(int i, int i2) {
        getConnectedSSIDImpl(new NullableResolverImpl(ResolverFactory.createBasicResolver(this, i, i2), this, i, i2));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return "WifiManagerModule";
    }

    public WifiManagerModule(Context context) {
        super(context);
    }
}
