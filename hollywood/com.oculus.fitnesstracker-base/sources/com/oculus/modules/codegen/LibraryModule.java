package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.fitnesstracker.database.FitnessTrackerMoveContract;
import com.oculus.fitnesstracker.database.FitnessTrackerUserContract;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LibraryModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "LibraryModule";

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
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void networkRefreshAsyncImpl(Resolver<Void> resolver);

    public void shutdownModule() {
    }

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
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("cancelDownload", "s"));
        arrayList.add(new Pair("downloadAndInstallAsync", "+ssii"));
        arrayList.add(new Pair("fetchDisabledAppsAsync", "+ii"));
        arrayList.add(new Pair("fetchLibraryAsync", "+ii"));
        arrayList.add(new Pair("fetchLibraryItemAsync", "+sii"));
        arrayList.add(new Pair("fetchUnknownSourcesAsync", "+bbii"));
        arrayList.add(new Pair("genControllerSupportForPackage", "+sii"));
        arrayList.add(new Pair("getInitialLibrary", "+ii"));
        arrayList.add(new Pair("getInstalledAppPermissions", "+sii"));
        arrayList.add(new Pair("getPermissionInfoAsync", "+sii"));
        arrayList.add(new Pair("isInstalledAsync", "+sii"));
        arrayList.add(new Pair("networkRefreshAsync", "+ii"));
        arrayList.add(new Pair("toggleOffUnseenAsync", "+sii"));
        arrayList.add(new Pair("uninstall", "+ssii"));
        arrayList.add(new Pair("updateRecentActivity", "s"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void emitOnLibraryUpdated(LibraryUpdate libraryUpdate) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "LibraryModule_onLibraryUpdated", String.valueOf(libraryUpdate.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void cancelDownload(String str) {
        cancelDownloadImpl(str);
    }

    /* access modifiers changed from: protected */
    public final void downloadAndInstallAsync(String str, String str2, int i, int i2) {
        downloadAndInstallAsyncImpl(str, LibraryRequestOrigin.valueOf(str2), ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void fetchDisabledAppsAsync(int i, int i2) {
        fetchDisabledAppsAsyncImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void fetchLibraryAsync(int i, int i2) {
        fetchLibraryAsyncImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void fetchLibraryItemAsync(String str, int i, int i2) {
        fetchLibraryItemAsyncImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void fetchUnknownSourcesAsync(boolean z, boolean z2, int i, int i2) {
        fetchUnknownSourcesAsyncImpl(z, z2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void genControllerSupportForPackage(String str, int i, int i2) {
        genControllerSupportForPackageImpl(str, ResolverFactory.createEnumListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getInitialLibrary(int i, int i2) {
        getInitialLibraryImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getInstalledAppPermissions(String str, int i, int i2) {
        getInstalledAppPermissionsImpl(str, ResolverFactory.createObjectListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void getPermissionInfoAsync(String str, int i, int i2) {
        getPermissionInfoAsyncImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void isInstalledAsync(String str, int i, int i2) {
        isInstalledAsyncImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void networkRefreshAsync(int i, int i2) {
        networkRefreshAsyncImpl(ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void toggleOffUnseenAsync(String str, int i, int i2) {
        toggleOffUnseenAsyncImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void uninstall(String str, String str2, int i, int i2) {
        uninstallImpl(str, LibraryRequestOrigin.valueOf(str2), ResolverFactory.createVoidResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void updateRecentActivity(String str) {
        updateRecentActivityImpl(str);
    }

    public static class LibraryUpdate extends NativeModuleParcel {
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.packageName = jSONObject.isNull(FitnessTrackerMoveContract.Session.PACKAGE_NAME) ? null : jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
        }

        public static final LibraryUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            LibraryUpdate libraryUpdate = new LibraryUpdate();
            libraryUpdate.setFromJSONObject(jSONObject);
            return libraryUpdate;
        }
    }

    public static class UnknownSource extends NativeModuleParcel {
        public double apkFullSizeBytes;
        public String applicationName;
        public String packageName;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("apkFullSizeBytes", this.apkFullSizeBytes);
                jSONObject.put("applicationName", this.applicationName);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.apkFullSizeBytes = jSONObject.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationName = jSONObject.optString("applicationName");
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
        }

        public static final UnknownSource makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UnknownSource unknownSource = new UnknownSource();
            unknownSource.setFromJSONObject(jSONObject);
            return unknownSource;
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
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("apkFullSizeBytes", this.apkFullSizeBytes);
                jSONObject.put("applicationGroupingId", this.applicationGroupingId);
                jSONObject.put("category", this.category.name());
                jSONObject.put("cloudStorageStatus", this.cloudStorageStatus.name());
                jSONObject.put("comfortRating", this.comfortRating.name());
                jSONObject.put("displayName", this.displayName);
                jSONObject.put("downloadedSizeBytes", this.downloadedSizeBytes);
                jSONObject.put("downloadSizeBytes", this.downloadSizeBytes);
                jSONObject.put("environmentSource", this.environmentSource == null ? JSONObject.NULL : this.environmentSource.convertToJSONObject());
                if (this.extra != null) {
                    jSONObject.put("extra", this.extra);
                }
                jSONObject.put("grantReason", this.grantReason.name());
                jSONObject.put("grantTimeMs", this.grantTimeMs);
                jSONObject.put("headTracking", this.headTracking.name());
                jSONObject.put("id", this.id);
                jSONObject.put("images", this.images.convertToJSONObject());
                if (this.internetConnection != null) {
                    jSONObject.put("internetConnection", this.internetConnection.name());
                }
                jSONObject.put("isConcept", this.isConcept);
                jSONObject.put("isDemoOf", this.isDemoOf);
                jSONObject.put("isTest", this.isTest);
                jSONObject.put("isUnseen", this.isUnseen);
                jSONObject.put("latestPermissions", NativeModuleParcel.convertStringListToJSONArray(this.latestPermissions));
                jSONObject.put("latestTargetSdkVersion", this.latestTargetSdkVersion);
                jSONObject.put("latestVersionCode", this.latestVersionCode);
                jSONObject.put("microphoneUsage", this.microphoneUsage.name());
                jSONObject.put("minRecommendedVersionCode", this.minRecommendedVersionCode);
                jSONObject.put("minRequiredVersionCode", this.minRequiredVersionCode);
                jSONObject.put(FitnessTrackerMoveContract.Session.PACKAGE_NAME, this.packageName);
                jSONObject.put("platform", this.platform.name());
                jSONObject.put("recentActivityMs", this.recentActivityMs);
                jSONObject.put("status", this.status.name());
                if (this.trustedBinaryStatus != null) {
                    jSONObject.put("trustedBinaryStatus", this.trustedBinaryStatus);
                }
                jSONObject.put("versionCode", this.versionCode);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.apkFullSizeBytes = jSONObject.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationGroupingId = jSONObject.optString("applicationGroupingId");
            this.category = Category.valueOf(jSONObject.optString("category"));
            this.cloudStorageStatus = CloudStorageStatus.valueOf(jSONObject.optString("cloudStorageStatus"));
            this.comfortRating = ComfortRating.valueOf(jSONObject.optString("comfortRating"));
            this.displayName = jSONObject.optString("displayName");
            this.downloadedSizeBytes = jSONObject.optDouble("downloadedSizeBytes", 0.0d);
            this.downloadSizeBytes = jSONObject.optDouble("downloadSizeBytes", 0.0d);
            String str = null;
            this.environmentSource = jSONObject.isNull("environmentSource") ? null : EnvironmentSource.makeFromJSONObject(jSONObject.optJSONObject("environmentSource"));
            this.extra = jSONObject.isNull("extra") ? null : jSONObject.optJSONObject("extra");
            this.grantReason = EntitlementGrantReason.valueOf(jSONObject.optString("grantReason"));
            this.grantTimeMs = jSONObject.optDouble("grantTimeMs", 0.0d);
            this.headTracking = HeadTrackingMode.valueOf(jSONObject.optString("headTracking"));
            this.id = jSONObject.optString("id");
            this.images = EntitlementImages.makeFromJSONObject(jSONObject.optJSONObject("images"));
            this.internetConnection = jSONObject.isNull("internetConnection") ? null : InternetConnection.valueOf(jSONObject.optString("internetConnection"));
            this.isConcept = jSONObject.optBoolean("isConcept");
            this.isDemoOf = jSONObject.optString("isDemoOf");
            this.isTest = jSONObject.optBoolean("isTest");
            this.isUnseen = jSONObject.optBoolean("isUnseen");
            this.latestPermissions = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("latestPermissions"));
            this.latestTargetSdkVersion = jSONObject.optDouble("latestTargetSdkVersion", 0.0d);
            this.latestVersionCode = jSONObject.optDouble("latestVersionCode", 0.0d);
            this.microphoneUsage = MicrophoneUsage.valueOf(jSONObject.optString("microphoneUsage"));
            this.minRecommendedVersionCode = jSONObject.optDouble("minRecommendedVersionCode", 0.0d);
            this.minRequiredVersionCode = jSONObject.optDouble("minRequiredVersionCode", 0.0d);
            this.packageName = jSONObject.optString(FitnessTrackerMoveContract.Session.PACKAGE_NAME);
            this.platform = Platform.valueOf(jSONObject.optString("platform"));
            this.recentActivityMs = jSONObject.optDouble("recentActivityMs", 0.0d);
            this.status = Status.valueOf(jSONObject.optString("status"));
            if (!jSONObject.isNull("trustedBinaryStatus")) {
                str = jSONObject.optString("trustedBinaryStatus");
            }
            this.trustedBinaryStatus = str;
            this.versionCode = jSONObject.optDouble("versionCode", 0.0d);
        }

        public static final Entitlement makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Entitlement entitlement = new Entitlement();
            entitlement.setFromJSONObject(jSONObject);
            return entitlement;
        }
    }

    public static class EnvironmentSource extends NativeModuleParcel {
        public String displayName;
        public String id;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("displayName", this.displayName);
                jSONObject.put("id", this.id);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.displayName = jSONObject.optString("displayName");
            this.id = jSONObject.optString("id");
        }

        public static final EnvironmentSource makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            EnvironmentSource environmentSource = new EnvironmentSource();
            environmentSource.setFromJSONObject(jSONObject);
            return environmentSource;
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
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("HERO", this.HERO.convertToJSONObject());
                jSONObject.put("LANDSCAPE_SMALL", this.LANDSCAPE_SMALL.convertToJSONObject());
                jSONObject.put("SOURCE_MAIN", this.SOURCE_MAIN.convertToJSONObject());
                jSONObject.put("SOURCE_SQUARE", this.SOURCE_SQUARE.convertToJSONObject());
                jSONObject.put("SOURCE_TINY", this.SOURCE_TINY.convertToJSONObject());
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.HERO = AppImage.makeFromJSONObject(jSONObject.optJSONObject("HERO"));
            this.LANDSCAPE_SMALL = AppImage.makeFromJSONObject(jSONObject.optJSONObject("LANDSCAPE_SMALL"));
            this.SOURCE_MAIN = AppImage.makeFromJSONObject(jSONObject.optJSONObject("SOURCE_MAIN"));
            this.SOURCE_SQUARE = AppImage.makeFromJSONObject(jSONObject.optJSONObject("SOURCE_SQUARE"));
            this.SOURCE_TINY = AppImage.makeFromJSONObject(jSONObject.optJSONObject("SOURCE_TINY"));
        }

        public static final EntitlementImages makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            EntitlementImages entitlementImages = new EntitlementImages();
            entitlementImages.setFromJSONObject(jSONObject);
            return entitlementImages;
        }
    }

    public static class AppImage extends NativeModuleParcel {
        public double height;
        public String uri;
        public double width;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FitnessTrackerUserContract.Metrics.HEIGHT, this.height);
                jSONObject.put("uri", this.uri);
                jSONObject.put("width", this.width);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.height = jSONObject.optDouble(FitnessTrackerUserContract.Metrics.HEIGHT, 0.0d);
            this.uri = jSONObject.optString("uri");
            this.width = jSONObject.optDouble("width", 0.0d);
        }

        public static final AppImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AppImage appImage = new AppImage();
            appImage.setFromJSONObject(jSONObject);
            return appImage;
        }
    }
}
