package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BatteryStateModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = BatteryStateModule.class.getSimpleName();

    public BatteryStateModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        return AbstractBroadcastReceiverModule.describe();
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnBatteryUpdated(BatteryUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "BatteryStateModule_onBatteryUpdated", String.valueOf(data.convertToJSONObject()));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule, com.oculus.panellib.modules.EarlyDestroyable
    public final void shutdownModule() {
        super.shutdownModule();
        shutdownModuleImpl();
    }

    /* access modifiers changed from: protected */
    public void shutdownModuleImpl() {
    }

    public static class BatteryUpdate extends NativeModuleParcel {
        public double level;
        public double plugged;
        public double status;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("level", this.level);
                parcel.put("plugged", this.plugged);
                parcel.put("status", this.status);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.level = json.optDouble("level", 0.0d);
            this.plugged = json.optDouble("plugged", 0.0d);
            this.status = json.optDouble("status", 0.0d);
        }

        public static final BatteryUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            BatteryUpdate result = new BatteryUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
