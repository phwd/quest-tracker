package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class QuickExperimentsModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = QuickExperimentsModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void getCachedQEsImpl(Resolver<GetCachedQEsResult> resolver);

    /* access modifiers changed from: protected */
    public abstract void overrideImpl(String str, JSONObject jSONObject, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void remoteFetchQEsImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void removeOverrideImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("getCachedQEs", "+ii"));
        list.add(new Pair<>("override", "+sjii"));
        list.add(new Pair<>("remoteFetchQEs", "s"));
        list.add(new Pair<>("removeOverride", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void getCachedQEs(int resolveID, int rejectID) {
        getCachedQEsImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void override(String universeName, JSONObject value, int resolveID, int rejectID) {
        overrideImpl(universeName, value, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void remoteFetchQEs(String userId) {
        remoteFetchQEsImpl(userId);
    }

    /* access modifiers changed from: protected */
    public final void removeOverride(String universeName, int resolveID, int rejectID) {
        removeOverrideImpl(universeName, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class GetCachedQEsResult extends NativeModuleParcel {
        public GetCachedQEsResultCache cache;
        public JSONObject overrides;
        public double timestamp;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("cache", this.cache == null ? JSONObject.NULL : this.cache.convertToJSONObject());
                parcel.put("overrides", this.overrides);
                parcel.put("timestamp", this.timestamp);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.cache = json.isNull("cache") ? null : GetCachedQEsResultCache.makeFromJSONObject(json.optJSONObject("cache"));
            this.overrides = json.optJSONObject("overrides");
            this.timestamp = json.optDouble("timestamp", 0.0d);
        }
    }

    public static class GetCachedQEsResultCache extends NativeModuleParcel {
        public List<JSONObject> data;
        public String id;
        public List<String> names;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("data", NativeModuleParcel.convertObjectListToJSONArray(this.data));
                parcel.put("id", this.id);
                parcel.put("names", NativeModuleParcel.convertStringListToJSONArray(this.names));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.data = NativeModuleParcel.convertJSONArrayToObjectList(json.optJSONArray("data"));
            this.id = json.optString("id");
            this.names = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("names"));
        }

        public static final GetCachedQEsResultCache makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            GetCachedQEsResultCache result = new GetCachedQEsResultCache();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
