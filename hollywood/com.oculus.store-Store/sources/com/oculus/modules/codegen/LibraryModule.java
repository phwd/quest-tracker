package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LibraryModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = LibraryModule.class.getSimpleName();

    public enum LibraryRequestOrigin {
        GAMING_ACTIVITY,
        LAUNCH_PROMPT,
        LIBRARY,
        NOTIFICATION,
        SETTINGS,
        STORAGE_MANAGER,
        STORE
    }

    public enum SupportedController {
        HANDS,
        TOUCH
    }

    /* access modifiers changed from: protected */
    public abstract void cancelDownloadImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void downloadAndInstallAsyncImpl(String str, LibraryRequestOrigin libraryRequestOrigin, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchDisabledAppsAsyncImpl(Resolver<List<UnknownSource>> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchLibraryAsyncImpl(Resolver<List<JSONObject>> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchLibraryItemAsyncImpl(String str, Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchUnknownSourcesAsyncImpl(boolean z, boolean z2, Resolver<List<UnknownSource>> resolver);

    /* access modifiers changed from: protected */
    public abstract void genControllerSupportForPackageImpl(String str, Resolver<List<SupportedController>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getInitialLibraryImpl(Resolver<List<JSONObject>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getInstalledAppPermissionsImpl(String str, Resolver<List<JSONObject>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getPermissionInfoAsyncImpl(String str, Resolver<JSONObject> resolver);

    /* access modifiers changed from: protected */
    public abstract void isInstalledAsyncImpl(String str, Resolver<Boolean> resolver);

    /* access modifiers changed from: protected */
    public abstract void networkRefreshAsyncImpl(Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void toggleOffUnseenAsyncImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void uninstallImpl(String str, LibraryRequestOrigin libraryRequestOrigin, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void updateRecentActivityImpl(String str);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("cancelDownload", "s"));
        list.add(new Pair<>("downloadAndInstallAsync", "+ssii"));
        list.add(new Pair<>("fetchDisabledAppsAsync", "+ii"));
        list.add(new Pair<>("fetchLibraryAsync", "+ii"));
        list.add(new Pair<>("fetchLibraryItemAsync", "+sii"));
        list.add(new Pair<>("fetchUnknownSourcesAsync", "+bbii"));
        list.add(new Pair<>("genControllerSupportForPackage", "+sii"));
        list.add(new Pair<>("getInitialLibrary", "+ii"));
        list.add(new Pair<>("getInstalledAppPermissions", "+sii"));
        list.add(new Pair<>("getPermissionInfoAsync", "+sii"));
        list.add(new Pair<>("isInstalledAsync", "+sii"));
        list.add(new Pair<>("networkRefreshAsync", "+ii"));
        list.add(new Pair<>("toggleOffUnseenAsync", "+sii"));
        list.add(new Pair<>("uninstall", "+ssii"));
        list.add(new Pair<>("updateRecentActivity", "s"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnLibraryUpdated(LibraryUpdate data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "LibraryModule_onLibraryUpdated", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void cancelDownload(String packageName) {
        cancelDownloadImpl(packageName);
    }

    /* access modifiers changed from: protected */
    public final void downloadAndInstallAsync(String packageName, String requestOrigin, int resolveID, int rejectID) {
        downloadAndInstallAsyncImpl(packageName, LibraryRequestOrigin.valueOf(requestOrigin), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchDisabledAppsAsync(int resolveID, int rejectID) {
        fetchDisabledAppsAsyncImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchLibraryAsync(int resolveID, int rejectID) {
        fetchLibraryAsyncImpl(ResolverFactory.createObjectListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchLibraryItemAsync(String packageName, int resolveID, int rejectID) {
        fetchLibraryItemAsyncImpl(packageName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchUnknownSourcesAsync(boolean displaySideloadedAppsEnabled, boolean includeDisabledApps, int resolveID, int rejectID) {
        fetchUnknownSourcesAsyncImpl(displaySideloadedAppsEnabled, includeDisabledApps, ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void genControllerSupportForPackage(String packageName, int resolveID, int rejectID) {
        genControllerSupportForPackageImpl(packageName, ResolverFactory.createEnumListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getInitialLibrary(int resolveID, int rejectID) {
        getInitialLibraryImpl(ResolverFactory.createObjectListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getInstalledAppPermissions(String packageName, int resolveID, int rejectID) {
        getInstalledAppPermissionsImpl(packageName, ResolverFactory.createObjectListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void getPermissionInfoAsync(String permissionName, int resolveID, int rejectID) {
        getPermissionInfoAsyncImpl(permissionName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void isInstalledAsync(String packageName, int resolveID, int rejectID) {
        isInstalledAsyncImpl(packageName, ResolverFactory.createBasicResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void networkRefreshAsync(int resolveID, int rejectID) {
        networkRefreshAsyncImpl(ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void toggleOffUnseenAsync(String packageName, int resolveID, int rejectID) {
        toggleOffUnseenAsyncImpl(packageName, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void uninstall(String packageName, String requestOrigin, int resolveID, int rejectID) {
        uninstallImpl(packageName, LibraryRequestOrigin.valueOf(requestOrigin), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void updateRecentActivity(String packageName) {
        updateRecentActivityImpl(packageName);
    }

    public void shutdownModule() {
    }

    public static class LibraryUpdate extends NativeModuleParcel {
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("packageName", this.packageName);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.packageName = json.isNull("packageName") ? null : json.optString("packageName");
        }

        public static final LibraryUpdate makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            LibraryUpdate result = new LibraryUpdate();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class UnknownSource extends NativeModuleParcel {
        public double apkFullSizeBytes;
        public String applicationName;
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("apkFullSizeBytes", this.apkFullSizeBytes);
                parcel.put("applicationName", this.applicationName);
                parcel.put("packageName", this.packageName);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.apkFullSizeBytes = json.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationName = json.optString("applicationName");
            this.packageName = json.optString("packageName");
        }

        public static final UnknownSource makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            UnknownSource result = new UnknownSource();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
