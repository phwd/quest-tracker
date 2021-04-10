package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ControllerStateModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "ControllerStateModule";

    /* access modifiers changed from: protected */
    public abstract void getControllerStatusesImpl(Resolver<ControllerStatuses> resolver);

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public abstract void startListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void stopListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getControllerStatuses", "+ii"));
        arrayList.add(new Pair("startListeningForStatusChanges", "+ii"));
        arrayList.add(new Pair("stopListeningForStatusChanges", "+ii"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void emitOnControllerStatusChanged(ControllerStatusUpdate controllerStatusUpdate) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "ControllerStateModule_onControllerStatusChanged", String.valueOf(controllerStatusUpdate.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void getControllerStatuses(int i, int i2) {
        getControllerStatusesImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void startListeningForStatusChanges(int i, int i2) {
        startListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void stopListeningForStatusChanges(int i, int i2) {
        stopListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public static class ControllerStatusUpdate extends NativeModuleParcel {
        public String status;
        public String type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("status", this.status);
                jSONObject.put("type", this.type);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.status = jSONObject.optString("status");
            this.type = jSONObject.optString("type");
        }

        public static final ControllerStatusUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ControllerStatusUpdate controllerStatusUpdate = new ControllerStatusUpdate();
            controllerStatusUpdate.setFromJSONObject(jSONObject);
            return controllerStatusUpdate;
        }
    }

    public static class ControllerStatuses extends NativeModuleParcel {
        public String primary;
        public String secondary;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("primary", this.primary);
                jSONObject.put("secondary", this.secondary);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.primary = jSONObject.optString("primary");
            this.secondary = jSONObject.optString("secondary");
        }

        public static final ControllerStatuses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ControllerStatuses controllerStatuses = new ControllerStatuses();
            controllerStatuses.setFromJSONObject(jSONObject);
            return controllerStatuses;
        }
    }
}
