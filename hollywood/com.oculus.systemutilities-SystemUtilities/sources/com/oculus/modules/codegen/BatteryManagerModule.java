package com.oculus.modules.codegen;

import android.content.Context;
import android.util.Pair;
import com.oculus.panellib.modules.AbstractBroadcastReceiverModule;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BatteryManagerModule extends AbstractBroadcastReceiverModule {
    protected static final String MODULE_NAME = BatteryManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getLowPowerModeAsyncImpl(Resolver<GetLowPowerModeAsyncResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void setLowPowerModeImpl(boolean z, Resolver<Boolean> resolver);

    public BatteryManagerModule(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = AbstractBroadcastReceiverModule.describe();
        list.add(new Pair<>("getLowPowerModeAsync", "+ii"));
        list.add(new Pair<>("setLowPowerMode", "+bii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnBatterySaverUpdated(boolean data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "BatteryManagerModule_onBatterySaverUpdated", String.valueOf(data));
    }

    /* access modifiers changed from: protected */
    public final void emitOnBatteryUpdated(BatteryUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "BatteryManagerModule_onBatteryUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void getLowPowerModeAsync(int resolveID, int rejectID) {
        getLowPowerModeAsyncImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setLowPowerMode(boolean enable, int resolveID, int rejectID) {
        setLowPowerModeImpl(enable, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
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
    }

    public static class GetLowPowerModeAsyncResult extends NativeModuleParcel {
        public boolean isBatterySaver;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("isBatterySaver", this.isBatterySaver);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.isBatterySaver = json.optBoolean("isBatterySaver");
        }
    }
}
