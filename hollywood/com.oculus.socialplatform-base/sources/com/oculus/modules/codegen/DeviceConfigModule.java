package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceConfigModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "DeviceConfigModule";

    public static class ConfigParamList extends NativeModuleParcel {
        public List<String> paramNames;

        public static final ConfigParamList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ConfigParamList configParamList = new ConfigParamList();
            configParamList.setFromJSONObject(jSONObject);
            return configParamList;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("paramNames", NativeModuleParcel.convertStringListToJSONArray(this.paramNames));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.paramNames = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("paramNames"));
        }
    }

    public abstract void getBooleanImpl(String str, Resolver<Boolean> resolver);

    public abstract boolean getBooleanSyncImpl(String str);

    public abstract void getDeviceBooleanImpl(String str, Resolver<Boolean> resolver);

    public abstract void getDeviceDoubleImpl(String str, Resolver<Double> resolver);

    public abstract void getDeviceLongImpl(String str, Resolver<Double> resolver);

    public abstract void getDeviceStringImpl(String str, Resolver<String> resolver);

    public abstract void getDoubleImpl(String str, Resolver<Double> resolver);

    public abstract double getDoubleSyncImpl(String str);

    public abstract void getLongImpl(String str, Resolver<Double> resolver);

    public abstract double getLongSyncImpl(String str);

    public abstract void getStringImpl(String str, Resolver<String> resolver);

    public abstract String getStringSyncImpl(String str);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void prefetchImpl(ConfigParamList configParamList);

    public void shutdownModule() {
    }

    public abstract void subscribeImpl(boolean z, boolean z2);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getBoolean", "+sii"));
        arrayList.add(new Pair("getBooleanSync", "-s"));
        arrayList.add(new Pair("getDeviceBoolean", "+sii"));
        arrayList.add(new Pair("getDeviceDouble", "+sii"));
        arrayList.add(new Pair("getDeviceLong", "+sii"));
        arrayList.add(new Pair("getDeviceString", "+sii"));
        arrayList.add(new Pair("getDouble", "+sii"));
        arrayList.add(new Pair("getDoubleSync", "-s"));
        arrayList.add(new Pair("getLong", "+sii"));
        arrayList.add(new Pair("getLongSync", "-s"));
        arrayList.add(new Pair("getString", "+sii"));
        arrayList.add(new Pair("getStringSync", "-s"));
        arrayList.add(new Pair("prefetch", "j"));
        arrayList.add(new Pair("subscribe", "bb"));
        return arrayList;
    }

    public final void emitOnSubscriptionFailure(String str) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionFailure", String.valueOf(str));
    }

    public final void emitOnSubscriptionPrefetched(ConfigParamList configParamList) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionPrefetched", String.valueOf(configParamList.convertToJSONObject()));
    }

    public final void emitOnSubscriptionSuccess(String str) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionSuccess", String.valueOf(str));
    }

    public final void emitOnSubscriptionUpdate(String str) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionUpdate", String.valueOf(str));
    }

    public final void getBoolean(String str, int i, int i2) {
        getBooleanImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final Object getBooleanSync(String str) {
        return Boolean.valueOf(getBooleanSyncImpl(str));
    }

    public final void getDeviceBoolean(String str, int i, int i2) {
        getDeviceBooleanImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getDeviceDouble(String str, int i, int i2) {
        getDeviceDoubleImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getDeviceLong(String str, int i, int i2) {
        getDeviceLongImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getDeviceString(String str, int i, int i2) {
        getDeviceStringImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getDouble(String str, int i, int i2) {
        getDoubleImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final Object getDoubleSync(String str) {
        return Double.valueOf(getDoubleSyncImpl(str));
    }

    public final void getLong(String str, int i, int i2) {
        getLongImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final Object getLongSync(String str) {
        return Double.valueOf(getLongSyncImpl(str));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void getString(String str, int i, int i2) {
        getStringImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final Object getStringSync(String str) {
        return getStringSyncImpl(str);
    }

    public final void prefetch(JSONObject jSONObject) {
        prefetchImpl(ConfigParamList.makeFromJSONObject(jSONObject));
    }

    public final void subscribe(boolean z, boolean z2) {
        subscribeImpl(z, z2);
    }
}
