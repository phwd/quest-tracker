package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LibraryModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = LibraryModule.class.getSimpleName();

    public enum Category {
        APPS,
        CONCEPTS,
        EARLY_ACCESS,
        ENTERTAINMENT,
        ENVIRONMENTS,
        GALLERY,
        GAMES,
        INTERNAL,
        SYSTEM,
        UNKNOWN
    }

    public enum CloudStorageStatus {
        DISABLED,
        DOWNLOAD_SYNCING,
        ENABLED,
        UPLOAD_SYNCING,
        UPLOAD_SYNC_REQUIRED
    }

    public enum ComfortRating {
        COMFORTABLE_FOR_ALL,
        COMFORTABLE_FOR_FEW,
        COMFORTABLE_FOR_MOST,
        COMFORTABLE_FOR_SOME,
        NOT_RATED,
        UNKNOWN
    }

    public enum EntitlementGrantReason {
        ADDITIONAL_ENTITLEMENT_GRANT,
        ADMIN_OFFER,
        BUNDLE_OFFER,
        CSR,
        DEVELOPER,
        ENTERPRISE,
        EXTENSION_CONSUMED,
        F2P_IAP_MIGRATION,
        FREE_OFFER,
        GIFT,
        INTERN,
        NUX,
        NUX_BUNDLE,
        OCULUS_EMPLOYEE_OFFER,
        OCULUS_KEYS,
        PAID_OFFER,
        PREORDER,
        PRESS_OFFER,
        RELEASE_CHANNEL_OFFER,
        RELEASE_CHANNEL_PERMANENT,
        RETAIL_DEMO,
        SAMSUNG_IMEI_PROMOTION,
        SHARED_ON_DEVICE,
        STORE_WIDE_OFFER,
        TEST_USER,
        THREE_DOF_UPGRADE,
        TRIAL_OFFER,
        UNKNOWN,
        XBUY
    }

    public enum HeadTrackingMode {
        ALLOW_6DOF,
        REQUIRE_3DOF,
        REQUIRE_6DOF,
        UNKNOWN
    }

    public enum InternetConnection {
        NOT_REQUIRED,
        REQUIRED,
        REQUIRED_FOR_DOWNLOAD
    }

    public enum LibraryRequestOrigin {
        GAMING_ACTIVITY,
        LAUNCH_PROMPT,
        LIBRARY,
        NOTIFICATION,
        SETTINGS,
        STORAGE_MANAGER,
        STORE
    }

    public enum MicrophoneUsage {
        EXCLUSIVE,
        NONE
    }

    public enum Platform {
        ANDROID,
        ANDROID_6DOF,
        UNKNOWN
    }

    public enum Status {
        DOWNLOADING,
        DOWNLOAD_QUEUED,
        ENTITLED,
        INCOMPATIBLE,
        INSTALLED,
        INSTALLING,
        INSTALL_AVAILABLE,
        NOT_ENTITLED,
        RUNNING,
        UNINSTALLING,
        UNKNOWN
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
    public abstract void fetchLibraryAsyncImpl(Resolver<List<Entitlement>> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchLibraryItemAsyncImpl(String str, Resolver<Entitlement> resolver);

    /* access modifiers changed from: protected */
    public abstract void fetchUnknownSourcesAsyncImpl(boolean z, boolean z2, Resolver<List<UnknownSource>> resolver);

    /* access modifiers changed from: protected */
    public abstract void genControllerSupportForPackageImpl(String str, Resolver<List<SupportedController>> resolver);

    /* access modifiers changed from: protected */
    public abstract void getInitialLibraryImpl(Resolver<List<Entitlement>> resolver);

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
        fetchLibraryAsyncImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void fetchLibraryItemAsync(String packageName, int resolveID, int rejectID) {
        fetchLibraryItemAsyncImpl(packageName, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
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
        getInitialLibraryImpl(ResolverFactory.createParcelListResolver(this, resolveID, rejectID));
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
    }

    public static class Entitlement extends NativeModuleParcel {
        public double apkFullSizeBytes;
        public String applicationGroupingId;
        public Category category;
        public CloudStorageStatus cloudStorageStatus;
        public ComfortRating comfortRating;
        public String displayName;
        public double downloadSizeBytes;
        public double downloadedSizeBytes;
        public EnvironmentSource environmentSource;
        public JSONObject extra;
        public EntitlementGrantReason grantReason;
        public double grantTimeMs;
        public HeadTrackingMode headTracking;
        public String id;
        public EntitlementImages images;
        public InternetConnection internetConnection;
        public boolean isConcept;
        public String isDemoOf;
        public boolean isTest;
        public boolean isUnseen;
        public List<String> latestPermissions;
        public double latestTargetSdkVersion;
        public double latestVersionCode;
        public MicrophoneUsage microphoneUsage;
        public double minRecommendedVersionCode;
        public double minRequiredVersionCode;
        public String packageName;
        public Platform platform;
        public double recentActivityMs;
        public Status status;
        public String trustedBinaryStatus;
        public double versionCode;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("apkFullSizeBytes", this.apkFullSizeBytes);
                parcel.put("applicationGroupingId", this.applicationGroupingId);
                parcel.put("category", this.category.name());
                parcel.put("cloudStorageStatus", this.cloudStorageStatus.name());
                parcel.put("comfortRating", this.comfortRating.name());
                parcel.put("displayName", this.displayName);
                parcel.put("downloadedSizeBytes", this.downloadedSizeBytes);
                parcel.put("downloadSizeBytes", this.downloadSizeBytes);
                if (this.environmentSource == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = this.environmentSource.convertToJSONObject();
                }
                parcel.put("environmentSource", convertToJSONObject);
                if (this.extra != null) {
                    parcel.put("extra", this.extra);
                }
                parcel.put("grantReason", this.grantReason.name());
                parcel.put("grantTimeMs", this.grantTimeMs);
                parcel.put("headTracking", this.headTracking.name());
                parcel.put("id", this.id);
                parcel.put("images", this.images.convertToJSONObject());
                if (this.internetConnection != null) {
                    parcel.put("internetConnection", this.internetConnection.name());
                }
                parcel.put("isConcept", this.isConcept);
                parcel.put("isDemoOf", this.isDemoOf);
                parcel.put("isTest", this.isTest);
                parcel.put("isUnseen", this.isUnseen);
                parcel.put("latestPermissions", NativeModuleParcel.convertStringListToJSONArray(this.latestPermissions));
                parcel.put("latestTargetSdkVersion", this.latestTargetSdkVersion);
                parcel.put("latestVersionCode", this.latestVersionCode);
                parcel.put("microphoneUsage", this.microphoneUsage.name());
                parcel.put("minRecommendedVersionCode", this.minRecommendedVersionCode);
                parcel.put("minRequiredVersionCode", this.minRequiredVersionCode);
                parcel.put("packageName", this.packageName);
                parcel.put("platform", this.platform.name());
                parcel.put("recentActivityMs", this.recentActivityMs);
                parcel.put("status", this.status.name());
                if (this.trustedBinaryStatus != null) {
                    parcel.put("trustedBinaryStatus", this.trustedBinaryStatus);
                }
                parcel.put("versionCode", this.versionCode);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            InternetConnection valueOf;
            String str = null;
            this.apkFullSizeBytes = json.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationGroupingId = json.optString("applicationGroupingId");
            this.category = Category.valueOf(json.optString("category"));
            this.cloudStorageStatus = CloudStorageStatus.valueOf(json.optString("cloudStorageStatus"));
            this.comfortRating = ComfortRating.valueOf(json.optString("comfortRating"));
            this.displayName = json.optString("displayName");
            this.downloadedSizeBytes = json.optDouble("downloadedSizeBytes", 0.0d);
            this.downloadSizeBytes = json.optDouble("downloadSizeBytes", 0.0d);
            this.environmentSource = json.isNull("environmentSource") ? null : EnvironmentSource.makeFromJSONObject(json.optJSONObject("environmentSource"));
            this.extra = json.isNull("extra") ? null : json.optJSONObject("extra");
            this.grantReason = EntitlementGrantReason.valueOf(json.optString("grantReason"));
            this.grantTimeMs = json.optDouble("grantTimeMs", 0.0d);
            this.headTracking = HeadTrackingMode.valueOf(json.optString("headTracking"));
            this.id = json.optString("id");
            this.images = EntitlementImages.makeFromJSONObject(json.optJSONObject("images"));
            if (json.isNull("internetConnection")) {
                valueOf = null;
            } else {
                valueOf = InternetConnection.valueOf(json.optString("internetConnection"));
            }
            this.internetConnection = valueOf;
            this.isConcept = json.optBoolean("isConcept");
            this.isDemoOf = json.optString("isDemoOf");
            this.isTest = json.optBoolean("isTest");
            this.isUnseen = json.optBoolean("isUnseen");
            this.latestPermissions = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("latestPermissions"));
            this.latestTargetSdkVersion = json.optDouble("latestTargetSdkVersion", 0.0d);
            this.latestVersionCode = json.optDouble("latestVersionCode", 0.0d);
            this.microphoneUsage = MicrophoneUsage.valueOf(json.optString("microphoneUsage"));
            this.minRecommendedVersionCode = json.optDouble("minRecommendedVersionCode", 0.0d);
            this.minRequiredVersionCode = json.optDouble("minRequiredVersionCode", 0.0d);
            this.packageName = json.optString("packageName");
            this.platform = Platform.valueOf(json.optString("platform"));
            this.recentActivityMs = json.optDouble("recentActivityMs", 0.0d);
            this.status = Status.valueOf(json.optString("status"));
            if (!json.isNull("trustedBinaryStatus")) {
                str = json.optString("trustedBinaryStatus");
            }
            this.trustedBinaryStatus = str;
            this.versionCode = json.optDouble("versionCode", 0.0d);
        }
    }

    public static class EnvironmentSource extends NativeModuleParcel {
        public String displayName;
        public String id;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("displayName", this.displayName);
                parcel.put("id", this.id);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.displayName = json.optString("displayName");
            this.id = json.optString("id");
        }

        public static final EnvironmentSource makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            EnvironmentSource result = new EnvironmentSource();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class EntitlementImages extends NativeModuleParcel {
        public AppImage HERO;
        public AppImage LANDSCAPE_SMALL;
        public AppImage SOURCE_MAIN;
        public AppImage SOURCE_SQUARE;
        public AppImage SOURCE_TINY;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("HERO", this.HERO.convertToJSONObject());
                parcel.put("LANDSCAPE_SMALL", this.LANDSCAPE_SMALL.convertToJSONObject());
                parcel.put("SOURCE_MAIN", this.SOURCE_MAIN.convertToJSONObject());
                parcel.put("SOURCE_SQUARE", this.SOURCE_SQUARE.convertToJSONObject());
                parcel.put("SOURCE_TINY", this.SOURCE_TINY.convertToJSONObject());
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.HERO = AppImage.makeFromJSONObject(json.optJSONObject("HERO"));
            this.LANDSCAPE_SMALL = AppImage.makeFromJSONObject(json.optJSONObject("LANDSCAPE_SMALL"));
            this.SOURCE_MAIN = AppImage.makeFromJSONObject(json.optJSONObject("SOURCE_MAIN"));
            this.SOURCE_SQUARE = AppImage.makeFromJSONObject(json.optJSONObject("SOURCE_SQUARE"));
            this.SOURCE_TINY = AppImage.makeFromJSONObject(json.optJSONObject("SOURCE_TINY"));
        }

        public static final EntitlementImages makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            EntitlementImages result = new EntitlementImages();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class AppImage extends NativeModuleParcel {
        public double height;
        public String uri;
        public double width;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("height", this.height);
                parcel.put("uri", this.uri);
                parcel.put("width", this.width);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.height = json.optDouble("height", 0.0d);
            this.uri = json.optString("uri");
            this.width = json.optDouble("width", 0.0d);
        }

        public static final AppImage makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            AppImage result = new AppImage();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
