package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class HandTrackingSettingsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = HandTrackingSettingsModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void enableHandTrackingImpl();

    /* access modifiers changed from: protected */
    public abstract void startListeningForSettingsChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void stopListeningForSettingsChangesImpl(Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("enableHandTracking", ""));
        list.add(new Pair<>("startListeningForSettingsChanges", "+ii"));
        list.add(new Pair<>("stopListeningForSettingsChanges", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnHandTrackingSettingsUpdated(HandTrackingSettingsUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "HandTrackingSettingsModule_onHandTrackingSettingsUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void enableHandTracking() {
        enableHandTrackingImpl();
    }

    /* access modifiers changed from: protected */
    public final void startListeningForSettingsChanges(int resolveID, int rejectID) {
        startListeningForSettingsChangesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void stopListeningForSettingsChanges(int resolveID, int rejectID) {
        stopListeningForSettingsChangesImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class HandTrackingSettingsUpdate extends NativeModuleParcel {
        public boolean enabled;
        public boolean optedIn;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put(SharingManagerContract.ARGUMENT_IS_ENABLED, this.enabled);
                parcel.put("optedIn", this.optedIn);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.enabled = json.optBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED);
            this.optedIn = json.optBoolean("optedIn");
        }

        public static final HandTrackingSettingsUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            HandTrackingSettingsUpdate result = new HandTrackingSettingsUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
