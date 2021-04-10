package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MobileConfigModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = MobileConfigModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getBoolImpl(MobileConfigParamType mobileConfigParamType, boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getBoolWithoutLoggingExposureImpl(MobileConfigParamType mobileConfigParamType, boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDoubleImpl(MobileConfigParamType mobileConfigParamType, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getDoubleWithoutLoggingExposureImpl(MobileConfigParamType mobileConfigParamType, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getIntImpl(MobileConfigParamType mobileConfigParamType, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getIntWithoutLoggingExposureImpl(MobileConfigParamType mobileConfigParamType, double d, Resolver<Double> resolver);

    /* access modifiers changed from: protected */
    public abstract void getStringImpl(MobileConfigParamType mobileConfigParamType, String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void getStringWithoutLoggingExposureImpl(MobileConfigParamType mobileConfigParamType, String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void initializeImpl(Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public abstract void logExposureImpl(MobileConfigParamType mobileConfigParamType, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getBool", "+jbii"));
        list.add(new Pair<>("getBoolWithoutLoggingExposure", "+jbii"));
        list.add(new Pair<>("getDouble", "+jdii"));
        list.add(new Pair<>("getDoubleWithoutLoggingExposure", "+jdii"));
        list.add(new Pair<>("getInt", "+jdii"));
        list.add(new Pair<>("getIntWithoutLoggingExposure", "+jdii"));
        list.add(new Pair<>("getString", "+jsii"));
        list.add(new Pair<>("getStringWithoutLoggingExposure", "+jsii"));
        list.add(new Pair<>("initialize", "+ii"));
        list.add(new Pair<>("logExposure", "+jii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getBool(JSONObject param, boolean defaultVal, int resolveID, int rejectID) {
        getBoolImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getBoolWithoutLoggingExposure(JSONObject param, boolean defaultVal, int resolveID, int rejectID) {
        getBoolWithoutLoggingExposureImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDouble(JSONObject param, double defaultVal, int resolveID, int rejectID) {
        getDoubleImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getDoubleWithoutLoggingExposure(JSONObject param, double defaultVal, int resolveID, int rejectID) {
        getDoubleWithoutLoggingExposureImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getInt(JSONObject param, double defaultVal, int resolveID, int rejectID) {
        getIntImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getIntWithoutLoggingExposure(JSONObject param, double defaultVal, int resolveID, int rejectID) {
        getIntWithoutLoggingExposureImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getString(JSONObject param, String defaultVal, int resolveID, int rejectID) {
        getStringImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getStringWithoutLoggingExposure(JSONObject param, String defaultVal, int resolveID, int rejectID) {
        getStringWithoutLoggingExposureImpl(MobileConfigParamType.makeFromJSONObject(param), defaultVal, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void initialize(int resolveID, int rejectID) {
        initializeImpl(ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void logExposure(JSONObject param, int resolveID, int rejectID) {
        logExposureImpl(MobileConfigParamType.makeFromJSONObject(param), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class MobileConfigParamType extends NativeModuleParcel {
        public String config;
        public double configId;
        public String defaultValue;
        public double paramId;
        public String parameter;
        public double slotId;
        public String type;
        public double unit_type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("config", this.config);
                parcel.put("configId", this.configId);
                parcel.put("defaultValue", this.defaultValue);
                parcel.put("parameter", this.parameter);
                parcel.put("paramId", this.paramId);
                parcel.put("slotId", this.slotId);
                parcel.put("type", this.type);
                parcel.put("unit_type", this.unit_type);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.config = json.optString("config");
            this.configId = json.optDouble("configId", 0.0d);
            this.defaultValue = json.optString("defaultValue");
            this.parameter = json.optString("parameter");
            this.paramId = json.optDouble("paramId", 0.0d);
            this.slotId = json.optDouble("slotId", 0.0d);
            this.type = json.optString("type");
            this.unit_type = json.optDouble("unit_type", 0.0d);
        }

        public static final MobileConfigParamType makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            MobileConfigParamType result = new MobileConfigParamType();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
