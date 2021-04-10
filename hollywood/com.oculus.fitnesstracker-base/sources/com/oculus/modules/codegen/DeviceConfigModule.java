package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class DeviceConfigModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "DeviceConfigModule";

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
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void prefetchImpl(ConfigParamList configParamList);

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public abstract void subscribeImpl(boolean z);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
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
        arrayList.add(new Pair("getParamNamesFromSchema", "+ii"));
        arrayList.add(new Pair("getString", "+sii"));
        arrayList.add(new Pair("getStringSync", "-s"));
        arrayList.add(new Pair("prefetch", "j"));
        arrayList.add(new Pair("subscribe", "b"));
        return arrayList;
    }

    public final void emitOnSubscriptionFailure(String str) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionFailure", String.valueOf(str));
    }

    public final void emitOnSubscriptionPrefetched(ConfigParamList configParamList) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionPrefetched", String.valueOf(configParamList.convertToJSONObject()));
    }

    public final void emitOnSubscriptionSuccess(String str) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionSuccess", String.valueOf(str));
    }

    public final void emitOnSubscriptionUpdate(String str) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "DeviceConfigModule_onSubscriptionUpdate", String.valueOf(str));
    }

    /* access modifiers changed from: protected */
    public final void getBoolean(String str, int i, int i2) {
        getBooleanImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final Object getBooleanSync(String str) {
        return Boolean.valueOf(getBooleanSyncImpl(str));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceBoolean(String str, int i, int i2) {
        getDeviceBooleanImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceDouble(String str, int i, int i2) {
        getDeviceDoubleImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceLong(String str, int i, int i2) {
        getDeviceLongImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getDeviceString(String str, int i, int i2) {
        getDeviceStringImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getDouble(String str, int i, int i2) {
        getDoubleImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final Object getDoubleSync(String str) {
        return Double.valueOf(getDoubleSyncImpl(str));
    }

    /* access modifiers changed from: protected */
    public final void getLong(String str, int i, int i2) {
        getLongImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final Object getLongSync(String str) {
        return Double.valueOf(getLongSyncImpl(str));
    }

    /* access modifiers changed from: protected */
    public final void getParamNamesFromSchema(int i, int i2) {
        getParamNamesFromSchemaImpl(ResolverFactory.createStringListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getString(String str, int i, int i2) {
        getStringImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final Object getStringSync(String str) {
        return getStringSyncImpl(str);
    }

    /* access modifiers changed from: protected */
    public final void prefetch(JSONObject jSONObject) {
        prefetchImpl(ConfigParamList.makeFromJSONObject(jSONObject));
    }

    /* access modifiers changed from: protected */
    public final void subscribe(boolean z) {
        subscribeImpl(z);
    }

    public static class ConfigParamList extends NativeModuleParcel {
        public List<String> paramNames;

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

        public static final ConfigParamList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ConfigParamList configParamList = new ConfigParamList();
            configParamList.setFromJSONObject(jSONObject);
            return configParamList;
        }
    }
}
