package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class NightShiftManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = NightShiftManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getNightShiftSettingsImpl(Resolver<NightShiftData> resolver);

    /* access modifiers changed from: protected */
    public abstract void setNightShiftActiveImpl(boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setNightShiftAutoModeImpl(double d, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setNightShiftEndTimeImpl(double d, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setNightShiftStartTimeImpl(double d, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getNightShiftSettings", "+ii"));
        list.add(new Pair<>("setNightShiftActive", "+bii"));
        list.add(new Pair<>("setNightShiftAutoMode", "+dii"));
        list.add(new Pair<>("setNightShiftEndTime", "+dii"));
        list.add(new Pair<>("setNightShiftStartTime", "+dii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getNightShiftSettings(int resolveID, int rejectID) {
        getNightShiftSettingsImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setNightShiftActive(boolean active, int resolveID, int rejectID) {
        setNightShiftActiveImpl(active, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setNightShiftAutoMode(double mode, int resolveID, int rejectID) {
        setNightShiftAutoModeImpl(mode, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setNightShiftEndTime(double time, int resolveID, int rejectID) {
        setNightShiftEndTimeImpl(time, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setNightShiftStartTime(double time, int resolveID, int rejectID) {
        setNightShiftStartTimeImpl(time, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class NightShiftData extends NativeModuleParcel {
        public boolean active;
        public double autoMode;
        public double customEndTimeMinute;
        public double customStartTimeMinute;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("active", this.active);
                parcel.put("autoMode", this.autoMode);
                parcel.put("customEndTimeMinute", this.customEndTimeMinute);
                parcel.put("customStartTimeMinute", this.customStartTimeMinute);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.active = json.optBoolean("active");
            this.autoMode = json.optDouble("autoMode", 0.0d);
            this.customEndTimeMinute = json.optDouble("customEndTimeMinute", 0.0d);
            this.customStartTimeMinute = json.optDouble("customStartTimeMinute", 0.0d);
        }
    }
}
