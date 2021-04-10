package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSBundleModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = JSBundleModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void downloadBundleImpl(AvailableBundle availableBundle, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchAndApplyBundleOverrideImpl(String str, boolean z, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchAvailableBundlesListImpl(Resolver<List<AvailableBundle>> resolver);

    /* access modifiers changed from: protected */
    public abstract void forceApplyBundleImpl(AvailableBundle availableBundle, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void getInstalledBundlesListImpl(Resolver<List<AvailableBundle>> resolver);

    /* access modifiers changed from: protected */
    public abstract void removeBundleImpl(AvailableBundle availableBundle, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("downloadBundle", "+jii"));
        list.add(new Pair<>("fetchAndApplyBundleOverride", "+sbii"));
        list.add(new Pair<>("fetchAvailableBundlesList", "+ii"));
        list.add(new Pair<>("forceApplyBundle", "+jii"));
        list.add(new Pair<>("getInstalledBundlesList", "+ii"));
        list.add(new Pair<>("removeBundle", "+jii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void downloadBundle(JSONObject bundle, int resolveID, int rejectID) {
        downloadBundleImpl(AvailableBundle.makeFromJSONObject(bundle), ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchAndApplyBundleOverride(String serviceName, boolean qaOnly, int resolveID, int rejectID) {
        fetchAndApplyBundleOverrideImpl(serviceName, qaOnly, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchAvailableBundlesList(int resolveID, int rejectID) {
        fetchAvailableBundlesListImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void forceApplyBundle(JSONObject bundle, int resolveID, int rejectID) {
        forceApplyBundleImpl(AvailableBundle.makeFromJSONObject(bundle), ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getInstalledBundlesList(int resolveID, int rejectID) {
        getInstalledBundlesListImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void removeBundle(JSONObject bundle, int resolveID, int rejectID) {
        removeBundleImpl(AvailableBundle.makeFromJSONObject(bundle), ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class AvailableBundle extends NativeModuleParcel {
        public String checksum;
        public String filename;
        public boolean installed;
        public String url;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("checksum", this.checksum);
                parcel.put("filename", this.filename);
                parcel.put("installed", this.installed);
                parcel.put("url", this.url);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.checksum = json.optString("checksum");
            this.filename = json.optString("filename");
            this.installed = json.optBoolean("installed");
            this.url = json.optString("url");
        }

        public static final AvailableBundle makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AvailableBundle result = new AvailableBundle();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
