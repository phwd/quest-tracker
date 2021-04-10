package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ControllerStateModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = ControllerStateModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getControllerStatusesImpl(Resolver<ControllerStatuses> resolver);

    /* access modifiers changed from: protected */
    public abstract void startListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void stopListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getControllerStatuses", "+ii"));
        list.add(new Pair<>("startListeningForStatusChanges", "+ii"));
        list.add(new Pair<>("stopListeningForStatusChanges", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnControllerStatusChanged(ControllerStatusUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "ControllerStateModule_onControllerStatusChanged", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void getControllerStatuses(int resolveID, int rejectID) {
        getControllerStatusesImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void startListeningForStatusChanges(int resolveID, int rejectID) {
        startListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void stopListeningForStatusChanges(int resolveID, int rejectID) {
        stopListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class ControllerStatusUpdate extends NativeModuleParcel {
        public String status;
        public String type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("status", this.status);
                parcel.put("type", this.type);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.status = json.optString("status");
            this.type = json.optString("type");
        }
    }

    public static class ControllerStatuses extends NativeModuleParcel {
        public String primary;
        public String secondary;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("primary", this.primary);
                parcel.put("secondary", this.secondary);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.primary = json.optString("primary");
            this.secondary = json.optString("secondary");
        }
    }
}
