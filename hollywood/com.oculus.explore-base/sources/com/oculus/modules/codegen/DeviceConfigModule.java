package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceConfigModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = DeviceConfigModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getBooleanImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract boolean getBooleanSyncImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void getDeviceBooleanImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDeviceDoubleImpl(String str, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDeviceLongImpl(String str, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDeviceStringImpl(String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDoubleImpl(String str, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract double getDoubleSyncImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void getLongImpl(String str, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract double getLongSyncImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void getParamNamesFromSchemaImpl(Resolver<List<String>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getStringImpl(String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract String getStringSyncImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void prefetchImpl(ConfigParamList configParamList);

    /* access modifiers changed from: protected */
    public abstract void subscribeImpl(boolean z);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getBoolean", "+sii"));
        list.add(new Pair<>("getBooleanSync", "-s"));
        list.add(new Pair<>("getDeviceBoolean", "+sii"));
        list.add(new Pair<>("getDeviceDouble", "+sii"));
        list.add(new Pair<>("getDeviceLong", "+sii"));
        list.add(new Pair<>("getDeviceString", "+sii"));
        list.add(new Pair<>("getDouble", "+sii"));
        list.add(new Pair<>("getDoubleSync", "-s"));
        list.add(new Pair<>("getLong", "+sii"));
        list.add(new Pair<>("getLongSync", "-s"));
        list.add(new Pair<>("getParamNamesFromSchema", "+ii"));
        list.add(new Pair<>("getString", "+sii"));
        list.add(new Pair<>("getStringSync", "-s"));
        list.add(new Pair<>("prefetch", "j"));
        list.add(new Pair<>("subscribe", "b"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnSubscriptionFailure(String data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionFailure", String.valueOf(data));
    }

    /* access modifiers changed from: protected */
    public final void emitOnSubscriptionPrefetched(ConfigParamList data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionPrefetched", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void emitOnSubscriptionSuccess(String data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionSuccess", String.valueOf(data));
    }

    /* access modifiers changed from: protected */
    public final void emitOnSubscriptionUpdate(String data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionUpdate", String.valueOf(data));
    }

    /* access modifiers changed from: protected */
    public final void getBoolean(String paramName, int resolveID, int rejectID) {
        getBooleanImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final Object getBooleanSync(String paramName) {
        return Boolean.valueOf(getBooleanSyncImpl(paramName));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceBoolean(String paramName, int resolveID, int rejectID) {
        getDeviceBooleanImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceDouble(String paramName, int resolveID, int rejectID) {
        getDeviceDoubleImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceLong(String paramName, int resolveID, int rejectID) {
        getDeviceLongImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceString(String paramName, int resolveID, int rejectID) {
        getDeviceStringImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDouble(String paramName, int resolveID, int rejectID) {
        getDoubleImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final Object getDoubleSync(String paramName) {
        return Double.valueOf(getDoubleSyncImpl(paramName));
    }

    /* access modifiers changed from: protected */
    public final void getLong(String paramName, int resolveID, int rejectID) {
        getLongImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final Object getLongSync(String paramName) {
        return Double.valueOf(getLongSyncImpl(paramName));
    }

    /* access modifiers changed from: protected */
    public final void getParamNamesFromSchema(int resolveID, int rejectID) {
        getParamNamesFromSchemaImpl(ResolverFactory.createStringListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getString(String paramName, int resolveID, int rejectID) {
        getStringImpl(paramName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final Object getStringSync(String paramName) {
        return getStringSyncImpl(paramName);
    }

    /* access modifiers changed from: protected */
    public final void prefetch(JSONObject paramNames) {
        prefetchImpl(ConfigParamList.makeFromJSONObject(paramNames));
    }

    /* access modifiers changed from: protected */
    public final void subscribe(boolean enableAutoPrefetch) {
        subscribeImpl(enableAutoPrefetch);
    }

    public void shutdownModule() {
    }

    public static class ConfigParamList extends NativeModuleParcel {
        public List<String> paramNames;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("paramNames", NativeModuleParcel.convertStringListToJSONArray(this.paramNames));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.paramNames = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("paramNames"));
        }

        public static final ConfigParamList makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            ConfigParamList result = new ConfigParamList();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
