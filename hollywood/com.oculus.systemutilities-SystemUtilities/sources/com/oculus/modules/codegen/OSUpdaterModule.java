package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class OSUpdaterModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = OSUpdaterModule.class.getSimpleName();

    public enum OSUpdateState {
        STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES,
        STATE_NOT_ALLOWED_BY_SYSTEM,
        STATE_OTA_DISABLED_BY_USER,
        STATE_READY_TO_CHECK_FOR_OTA,
        STATE_UNKNOWN,
        STATE_UPDATE_IN_PROGRESS,
        STATE_WAITING_FOR_REBOOT,
        STATE_WIFI_DISABLED
    }

    /* access modifiers changed from: protected */
    public abstract void checkIfUpdatesAreAvailableImpl(Resolver<OSUpdaterResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void downloadUpdateIfAvailableImpl(Resolver<OSUpdaterResponse> resolver);

    /* access modifiers changed from: protected */
    public abstract void rebootAndApplyUpdateImpl();

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("checkIfUpdatesAreAvailable", "+ii"));
        list.add(new Pair<>("downloadUpdateIfAvailable", "+ii"));
        list.add(new Pair<>("rebootAndApplyUpdate", ""));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void checkIfUpdatesAreAvailable(int resolveID, int rejectID) {
        checkIfUpdatesAreAvailableImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void downloadUpdateIfAvailable(int resolveID, int rejectID) {
        downloadUpdateIfAvailableImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void rebootAndApplyUpdate() {
        rebootAndApplyUpdateImpl();
    }

    public void shutdownModule() {
    }

    public static class OSUpdaterResponse extends NativeModuleParcel {
        public boolean areUpdatesAvailable;
        public String currentOSVersion;
        public String targetVersion;
        public OSUpdateState updaterState;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("areUpdatesAvailable", this.areUpdatesAvailable);
                parcel.put("currentOSVersion", this.currentOSVersion);
                parcel.put("targetVersion", this.targetVersion);
                parcel.put("updaterState", this.updaterState.name());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.areUpdatesAvailable = json.optBoolean("areUpdatesAvailable");
            this.currentOSVersion = json.optString("currentOSVersion");
            this.targetVersion = json.optString("targetVersion");
            this.updaterState = OSUpdateState.valueOf(json.optString("updaterState"));
        }
    }
}
