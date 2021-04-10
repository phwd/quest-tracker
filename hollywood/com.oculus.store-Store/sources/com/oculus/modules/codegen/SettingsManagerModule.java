package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SettingsManagerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = SettingsManagerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getBooleanImpl(String str, boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDoubleImpl(String str, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getFloatImpl(String str, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getIntImpl(String str, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getLongImpl(String str, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getStringImpl(String str, String str2, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void hasDefinedValueImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void setBooleanImpl(String str, boolean z, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setDoubleImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setFloatImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setIntImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setLongImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void setStringImpl(String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void subscribeImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void unsubscribeImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getBoolean", "+sbii"));
        list.add(new Pair<>("getDouble", "+sdii"));
        list.add(new Pair<>("getFloat", "+sdii"));
        list.add(new Pair<>("getInt", "+sdii"));
        list.add(new Pair<>("getLong", "+sdii"));
        list.add(new Pair<>("getString", "+ssii"));
        list.add(new Pair<>("hasDefinedValue", "+sii"));
        list.add(new Pair<>("setBoolean", "+sbii"));
        list.add(new Pair<>("setDouble", "+sdii"));
        list.add(new Pair<>("setFloat", "+sdii"));
        list.add(new Pair<>("setInt", "+sdii"));
        list.add(new Pair<>("setLong", "+sdii"));
        list.add(new Pair<>("setString", "+ssii"));
        list.add(new Pair<>("subscribe", "+sii"));
        list.add(new Pair<>("unsubscribe", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnUpdated(SettingUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "SettingsManagerModule_onUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void getBoolean(String name, boolean defaultValue, int resolveID, int rejectID) {
        getBooleanImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDouble(String name, double defaultValue, int resolveID, int rejectID) {
        getDoubleImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getFloat(String name, double defaultValue, int resolveID, int rejectID) {
        getFloatImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getInt(String name, double defaultValue, int resolveID, int rejectID) {
        getIntImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getLong(String name, double defaultValue, int resolveID, int rejectID) {
        getLongImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getString(String name, String defaultValue, int resolveID, int rejectID) {
        getStringImpl(name, defaultValue, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void hasDefinedValue(String name, int resolveID, int rejectID) {
        hasDefinedValueImpl(name, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setBoolean(String name, boolean value, int resolveID, int rejectID) {
        setBooleanImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setDouble(String name, double value, int resolveID, int rejectID) {
        setDoubleImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setFloat(String name, double value, int resolveID, int rejectID) {
        setFloatImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setInt(String name, double value, int resolveID, int rejectID) {
        setIntImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setLong(String name, double value, int resolveID, int rejectID) {
        setLongImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setString(String name, String value, int resolveID, int rejectID) {
        setStringImpl(name, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void subscribe(String name, int resolveID, int rejectID) {
        subscribeImpl(name, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void unsubscribe(String name, int resolveID, int rejectID) {
        unsubscribeImpl(name, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class SettingUpdate extends NativeModuleParcel {
        public String name;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("name", this.name);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.name = json.optString("name");
        }

        public static final SettingUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            SettingUpdate result = new SettingUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
