package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WifiManagerModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = WifiManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getConnectedSSIDImpl(Resolver<String> resolver);

    public WifiManagerModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("getConnectedSSID", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnWifiUpdated(WifiUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "WifiManagerModule_onWifiUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void getConnectedSSID(int resolveID, int rejectID) {
        getConnectedSSIDImpl(ResolverFactory.createNullableResolver(ResolverFactory.createBasicResolver(this, resolveID, rejectID), this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class WifiUpdate extends NativeModuleParcel {
        public double level;
        public String ssid;
        public double state;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("level", this.level);
                parcel.put("ssid", this.ssid);
                parcel.put(ApkUpdateInfoContract.EXTRA_STATE, this.state);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.level = json.optDouble("level", 0.0d);
            this.ssid = json.optString("ssid");
            this.state = json.optDouble(ApkUpdateInfoContract.EXTRA_STATE, 0.0d);
        }

        public static final WifiUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            WifiUpdate result = new WifiUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
