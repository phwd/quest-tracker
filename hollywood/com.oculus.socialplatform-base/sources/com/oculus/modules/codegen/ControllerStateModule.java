package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.modules.ControllerStateModuleImpl;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ControllerStateModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "ControllerStateModule";

    public static class ControllerStatusUpdate extends NativeModuleParcel {
        public String status;
        public String type;

        public static final ControllerStatusUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ControllerStatusUpdate controllerStatusUpdate = new ControllerStatusUpdate();
            controllerStatusUpdate.setFromJSONObject(jSONObject);
            return controllerStatusUpdate;
        }

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
    }

    public static class ControllerStatuses extends NativeModuleParcel {
        public String primary;

        /* renamed from: secondary  reason: collision with root package name */
        public String f3secondary;

        public static final ControllerStatuses makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ControllerStatuses controllerStatuses = new ControllerStatuses();
            controllerStatuses.setFromJSONObject(jSONObject);
            return controllerStatuses;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ControllerStateModuleImpl.PRIMARY_DEVICE_TYPE, this.primary);
                jSONObject.put(ControllerStateModuleImpl.SECONDARY_DEVICE_TYPE, this.f3secondary);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.primary = jSONObject.optString(ControllerStateModuleImpl.PRIMARY_DEVICE_TYPE);
            this.f3secondary = jSONObject.optString(ControllerStateModuleImpl.SECONDARY_DEVICE_TYPE);
        }
    }

    public abstract void getControllerStatusesImpl(Resolver<ControllerStatuses> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public void shutdownModule() {
    }

    public abstract void startListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    public abstract void stopListeningForStatusChangesImpl(Resolver<Boolean> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getControllerStatuses", "+ii"));
        arrayList.add(new Pair("startListeningForStatusChanges", "+ii"));
        arrayList.add(new Pair("stopListeningForStatusChanges", "+ii"));
        return arrayList;
    }

    public final void emitOnControllerStatusChanged(ControllerStatusUpdate controllerStatusUpdate) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "ControllerStateModule_onControllerStatusChanged", String.valueOf(controllerStatusUpdate.convertToJSONObject()));
    }

    public final void getControllerStatuses(int i, int i2) {
        getControllerStatusesImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void startListeningForStatusChanges(int i, int i2) {
        startListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void stopListeningForStatusChanges(int i, int i2) {
        stopListeningForStatusChangesImpl(ResolverFactory.createBasicResolver(this, i, i2));
    }
}
