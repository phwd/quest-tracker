package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SettingsManagerModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "SettingsManagerModule";

    public static class SettingUpdate extends NativeModuleParcel {
        public String name;

        public static final SettingUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            SettingUpdate settingUpdate = new SettingUpdate();
            settingUpdate.setFromJSONObject(jSONObject);
            return settingUpdate;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", this.name);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.name = jSONObject.optString("name");
        }
    }

    public abstract void getBooleanImpl(String str, boolean z, Resolver<Boolean> resolver);

    public abstract void getDoubleImpl(String str, double d, Resolver<Double> resolver);

    public abstract void getFloatImpl(String str, double d, Resolver<Double> resolver);

    public abstract void getIntImpl(String str, double d, Resolver<Double> resolver);

    public abstract void getLongImpl(String str, double d, Resolver<Double> resolver);

    public abstract void getStringImpl(String str, String str2, Resolver<String> resolver);

    public abstract void hasDefinedValueImpl(String str, Resolver<Boolean> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void setBooleanImpl(String str, boolean z, Resolver<Void> resolver);

    public abstract void setDoubleImpl(String str, double d, Resolver<Void> resolver);

    public abstract void setFloatImpl(String str, double d, Resolver<Void> resolver);

    public abstract void setIntImpl(String str, double d, Resolver<Void> resolver);

    public abstract void setLongImpl(String str, double d, Resolver<Void> resolver);

    public abstract void setStringImpl(String str, String str2, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public abstract void subscribeImpl(String str, Resolver<Void> resolver);

    public abstract void unsubscribeImpl(String str, Resolver<Void> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("getBoolean", "+sbii"));
        arrayList.add(new Pair("getDouble", "+sdii"));
        arrayList.add(new Pair("getFloat", "+sdii"));
        arrayList.add(new Pair("getInt", "+sdii"));
        arrayList.add(new Pair("getLong", "+sdii"));
        arrayList.add(new Pair("getString", "+ssii"));
        arrayList.add(new Pair("hasDefinedValue", "+sii"));
        arrayList.add(new Pair("setBoolean", "+sbii"));
        arrayList.add(new Pair("setDouble", "+sdii"));
        arrayList.add(new Pair("setFloat", "+sdii"));
        arrayList.add(new Pair("setInt", "+sdii"));
        arrayList.add(new Pair("setLong", "+sdii"));
        arrayList.add(new Pair("setString", "+ssii"));
        arrayList.add(new Pair("subscribe", "+sii"));
        arrayList.add(new Pair("unsubscribe", "+sii"));
        return arrayList;
    }

    public final void emitOnUpdated(SettingUpdate settingUpdate) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "SettingsManagerModule_onUpdated", String.valueOf(settingUpdate.convertToJSONObject()));
    }

    public final void getBoolean(String str, boolean z, int i, int i2) {
        getBooleanImpl(str, z, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getDouble(String str, double d, int i, int i2) {
        getDoubleImpl(str, d, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getFloat(String str, double d, int i, int i2) {
        getFloatImpl(str, d, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getInt(String str, double d, int i, int i2) {
        getIntImpl(str, d, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void getLong(String str, double d, int i, int i2) {
        getLongImpl(str, d, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void getString(String str, String str2, int i, int i2) {
        getStringImpl(str, str2, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void hasDefinedValue(String str, int i, int i2) {
        hasDefinedValueImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void setBoolean(String str, boolean z, int i, int i2) {
        setBooleanImpl(str, z, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setDouble(String str, double d, int i, int i2) {
        setDoubleImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setFloat(String str, double d, int i, int i2) {
        setFloatImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setInt(String str, double d, int i, int i2) {
        setIntImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setLong(String str, double d, int i, int i2) {
        setLongImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void setString(String str, String str2, int i, int i2) {
        setStringImpl(str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void subscribe(String str, int i, int i2) {
        subscribeImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void unsubscribe(String str, int i, int i2) {
        unsubscribeImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
