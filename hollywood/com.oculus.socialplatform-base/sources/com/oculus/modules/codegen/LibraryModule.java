package com.oculus.modules.codegen;

import android.util.Pair;
import com.facebook.acra.CrashTimeDataCollector;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import libraries.marauder.analytics.AnalyticsEventBase;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LibraryModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "LibraryModule";

    public static class AppImage extends NativeModuleParcel {
        public double height;
        public String uri;
        public double width;

        public static final AppImage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            AppImage appImage = new AppImage();
            appImage.setFromJSONObject(jSONObject);
            return appImage;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("height", this.height);
                jSONObject.put("uri", this.uri);
                jSONObject.put("width", this.width);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.height = jSONObject.optDouble("height", 0.0d);
            this.uri = jSONObject.optString("uri");
            this.width = jSONObject.optDouble("width", 0.0d);
        }
    }

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

        public static final Entitlement makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Entitlement entitlement = new Entitlement();
            entitlement.setFromJSONObject(jSONObject);
            return entitlement;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            Object convertToJSONObject;
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
                EnvironmentSource environmentSource2 = this.environmentSource;
                if (environmentSource2 == null) {
                    convertToJSONObject = JSONObject.NULL;
                } else {
                    convertToJSONObject = environmentSource2.convertToJSONObject();
                }
                jSONObject.put("environmentSource", convertToJSONObject);
                JSONObject jSONObject2 = this.extra;
                if (jSONObject2 != null) {
                    jSONObject.put(AnalyticsEventBase.EXTRAS_KEY, jSONObject2);
                }
                jSONObject.put("grantReason", this.grantReason.name());
                jSONObject.put("grantTimeMs", this.grantTimeMs);
                jSONObject.put("headTracking", this.headTracking.name());
                jSONObject.put("id", this.id);
                jSONObject.put("images", this.images.convertToJSONObject());
                InternetConnection internetConnection2 = this.internetConnection;
                if (internetConnection2 != null) {
                    jSONObject.put("internetConnection", internetConnection2.name());
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
                jSONObject.put("packageName", this.packageName);
                jSONObject.put("platform", this.platform.name());
                jSONObject.put("recentActivityMs", this.recentActivityMs);
                jSONObject.put("status", this.status.name());
                String str = this.trustedBinaryStatus;
                if (str != null) {
                    jSONObject.put("trustedBinaryStatus", str);
                }
                jSONObject.put("versionCode", this.versionCode);
                return jSONObject;
            } catch (JSONException unused) {
                return jSONObject;
            }
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            EnvironmentSource makeFromJSONObject;
            JSONObject optJSONObject;
            InternetConnection valueOf;
            this.apkFullSizeBytes = jSONObject.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationGroupingId = jSONObject.optString("applicationGroupingId");
            this.category = Category.valueOf(jSONObject.optString("category"));
            this.cloudStorageStatus = CloudStorageStatus.valueOf(jSONObject.optString("cloudStorageStatus"));
            this.comfortRating = ComfortRating.valueOf(jSONObject.optString("comfortRating"));
            this.displayName = jSONObject.optString("displayName");
            this.downloadedSizeBytes = jSONObject.optDouble("downloadedSizeBytes", 0.0d);
            this.downloadSizeBytes = jSONObject.optDouble("downloadSizeBytes", 0.0d);
            String str = null;
            if (jSONObject.isNull("environmentSource")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = EnvironmentSource.makeFromJSONObject(jSONObject.optJSONObject("environmentSource"));
            }
            this.environmentSource = makeFromJSONObject;
            if (jSONObject.isNull(AnalyticsEventBase.EXTRAS_KEY)) {
                optJSONObject = null;
            } else {
                optJSONObject = jSONObject.optJSONObject(AnalyticsEventBase.EXTRAS_KEY);
            }
            this.extra = optJSONObject;
            this.grantReason = EntitlementGrantReason.valueOf(jSONObject.optString("grantReason"));
            this.grantTimeMs = jSONObject.optDouble("grantTimeMs", 0.0d);
            this.headTracking = HeadTrackingMode.valueOf(jSONObject.optString("headTracking"));
            this.id = jSONObject.optString("id");
            this.images = EntitlementImages.makeFromJSONObject(jSONObject.optJSONObject("images"));
            if (jSONObject.isNull("internetConnection")) {
                valueOf = null;
            } else {
                valueOf = InternetConnection.valueOf(jSONObject.optString("internetConnection"));
            }
            this.internetConnection = valueOf;
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
            this.packageName = jSONObject.optString("packageName");
            this.platform = Platform.valueOf(jSONObject.optString("platform"));
            this.recentActivityMs = jSONObject.optDouble("recentActivityMs", 0.0d);
            this.status = Status.valueOf(jSONObject.optString("status"));
            if (!jSONObject.isNull("trustedBinaryStatus")) {
                str = jSONObject.optString("trustedBinaryStatus");
            }
            this.trustedBinaryStatus = str;
            this.versionCode = jSONObject.optDouble("versionCode", 0.0d);
        }
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class EntitlementGrantReason extends Enum<EntitlementGrantReason> {
        public static final /* synthetic */ EntitlementGrantReason[] $VALUES;
        public static final EntitlementGrantReason ADDITIONAL_ENTITLEMENT_GRANT;
        public static final EntitlementGrantReason ADMIN_OFFER;
        public static final EntitlementGrantReason BUNDLE_OFFER;
        public static final EntitlementGrantReason CSR;
        public static final EntitlementGrantReason DEVELOPER;
        public static final EntitlementGrantReason ENTERPRISE;
        public static final EntitlementGrantReason EXTENSION_CONSUMED;
        public static final EntitlementGrantReason F2P_IAP_MIGRATION;
        public static final EntitlementGrantReason FREE_OFFER;
        public static final EntitlementGrantReason GIFT;
        public static final EntitlementGrantReason INTERN;
        public static final EntitlementGrantReason NUX;
        public static final EntitlementGrantReason NUX_BUNDLE;
        public static final EntitlementGrantReason OCULUS_EMPLOYEE_OFFER;
        public static final EntitlementGrantReason OCULUS_KEYS;
        public static final EntitlementGrantReason PAID_OFFER;
        public static final EntitlementGrantReason PREORDER;
        public static final EntitlementGrantReason PRESS_OFFER;
        public static final EntitlementGrantReason RELEASE_CHANNEL_OFFER;
        public static final EntitlementGrantReason RELEASE_CHANNEL_PERMANENT;
        public static final EntitlementGrantReason RETAIL_DEMO;
        public static final EntitlementGrantReason SAMSUNG_IMEI_PROMOTION;
        public static final EntitlementGrantReason SHARED_ON_DEVICE;
        public static final EntitlementGrantReason STORE_WIDE_OFFER;
        public static final EntitlementGrantReason TEST_USER;
        public static final EntitlementGrantReason THREE_DOF_UPGRADE;
        public static final EntitlementGrantReason TRIAL_OFFER;
        public static final EntitlementGrantReason UNKNOWN;
        public static final EntitlementGrantReason XBUY;

        static {
            EntitlementGrantReason entitlementGrantReason = new EntitlementGrantReason("ADDITIONAL_ENTITLEMENT_GRANT", 0);
            ADDITIONAL_ENTITLEMENT_GRANT = entitlementGrantReason;
            EntitlementGrantReason entitlementGrantReason2 = new EntitlementGrantReason("ADMIN_OFFER", 1);
            ADMIN_OFFER = entitlementGrantReason2;
            EntitlementGrantReason entitlementGrantReason3 = new EntitlementGrantReason("BUNDLE_OFFER", 2);
            BUNDLE_OFFER = entitlementGrantReason3;
            EntitlementGrantReason entitlementGrantReason4 = new EntitlementGrantReason("CSR", 3);
            CSR = entitlementGrantReason4;
            EntitlementGrantReason entitlementGrantReason5 = new EntitlementGrantReason("DEVELOPER", 4);
            DEVELOPER = entitlementGrantReason5;
            EntitlementGrantReason entitlementGrantReason6 = new EntitlementGrantReason("ENTERPRISE", 5);
            ENTERPRISE = entitlementGrantReason6;
            EntitlementGrantReason entitlementGrantReason7 = new EntitlementGrantReason("EXTENSION_CONSUMED", 6);
            EXTENSION_CONSUMED = entitlementGrantReason7;
            EntitlementGrantReason entitlementGrantReason8 = new EntitlementGrantReason("F2P_IAP_MIGRATION", 7);
            F2P_IAP_MIGRATION = entitlementGrantReason8;
            EntitlementGrantReason entitlementGrantReason9 = new EntitlementGrantReason("FREE_OFFER", 8);
            FREE_OFFER = entitlementGrantReason9;
            EntitlementGrantReason entitlementGrantReason10 = new EntitlementGrantReason("GIFT", 9);
            GIFT = entitlementGrantReason10;
            EntitlementGrantReason entitlementGrantReason11 = new EntitlementGrantReason("INTERN", 10);
            INTERN = entitlementGrantReason11;
            EntitlementGrantReason entitlementGrantReason12 = new EntitlementGrantReason("NUX", 11);
            NUX = entitlementGrantReason12;
            EntitlementGrantReason entitlementGrantReason13 = new EntitlementGrantReason("NUX_BUNDLE", 12);
            NUX_BUNDLE = entitlementGrantReason13;
            EntitlementGrantReason entitlementGrantReason14 = new EntitlementGrantReason("OCULUS_EMPLOYEE_OFFER", 13);
            OCULUS_EMPLOYEE_OFFER = entitlementGrantReason14;
            EntitlementGrantReason entitlementGrantReason15 = new EntitlementGrantReason("OCULUS_KEYS", 14);
            OCULUS_KEYS = entitlementGrantReason15;
            EntitlementGrantReason entitlementGrantReason16 = new EntitlementGrantReason("PAID_OFFER", 15);
            PAID_OFFER = entitlementGrantReason16;
            EntitlementGrantReason entitlementGrantReason17 = new EntitlementGrantReason("PREORDER", 16);
            PREORDER = entitlementGrantReason17;
            EntitlementGrantReason entitlementGrantReason18 = new EntitlementGrantReason("PRESS_OFFER", 17);
            PRESS_OFFER = entitlementGrantReason18;
            EntitlementGrantReason entitlementGrantReason19 = new EntitlementGrantReason("RELEASE_CHANNEL_OFFER", 18);
            RELEASE_CHANNEL_OFFER = entitlementGrantReason19;
            EntitlementGrantReason entitlementGrantReason20 = new EntitlementGrantReason("RELEASE_CHANNEL_PERMANENT", 19);
            RELEASE_CHANNEL_PERMANENT = entitlementGrantReason20;
            EntitlementGrantReason entitlementGrantReason21 = new EntitlementGrantReason("RETAIL_DEMO", 20);
            RETAIL_DEMO = entitlementGrantReason21;
            EntitlementGrantReason entitlementGrantReason22 = new EntitlementGrantReason("SAMSUNG_IMEI_PROMOTION", 21);
            SAMSUNG_IMEI_PROMOTION = entitlementGrantReason22;
            EntitlementGrantReason entitlementGrantReason23 = new EntitlementGrantReason("SHARED_ON_DEVICE", 22);
            SHARED_ON_DEVICE = entitlementGrantReason23;
            EntitlementGrantReason entitlementGrantReason24 = new EntitlementGrantReason("STORE_WIDE_OFFER", 23);
            STORE_WIDE_OFFER = entitlementGrantReason24;
            EntitlementGrantReason entitlementGrantReason25 = new EntitlementGrantReason("TEST_USER", 24);
            TEST_USER = entitlementGrantReason25;
            EntitlementGrantReason entitlementGrantReason26 = new EntitlementGrantReason("THREE_DOF_UPGRADE", 25);
            THREE_DOF_UPGRADE = entitlementGrantReason26;
            EntitlementGrantReason entitlementGrantReason27 = new EntitlementGrantReason("TRIAL_OFFER", 26);
            TRIAL_OFFER = entitlementGrantReason27;
            EntitlementGrantReason entitlementGrantReason28 = new EntitlementGrantReason(CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN, 27);
            UNKNOWN = entitlementGrantReason28;
            EntitlementGrantReason entitlementGrantReason29 = new EntitlementGrantReason("XBUY", 28);
            XBUY = entitlementGrantReason29;
            EntitlementGrantReason[] entitlementGrantReasonArr = new EntitlementGrantReason[29];
            System.arraycopy(new EntitlementGrantReason[]{entitlementGrantReason, entitlementGrantReason2, entitlementGrantReason3, entitlementGrantReason4, entitlementGrantReason5, entitlementGrantReason6, entitlementGrantReason7, entitlementGrantReason8, entitlementGrantReason9, entitlementGrantReason10, entitlementGrantReason11, entitlementGrantReason12, entitlementGrantReason13, entitlementGrantReason14, entitlementGrantReason15, entitlementGrantReason16, entitlementGrantReason17, entitlementGrantReason18, entitlementGrantReason19, entitlementGrantReason20, entitlementGrantReason21, entitlementGrantReason22, entitlementGrantReason23, entitlementGrantReason24, entitlementGrantReason25, entitlementGrantReason26, entitlementGrantReason27}, 0, entitlementGrantReasonArr, 0, 27);
            System.arraycopy(new EntitlementGrantReason[]{entitlementGrantReason28, entitlementGrantReason29}, 0, entitlementGrantReasonArr, 27, 2);
            $VALUES = entitlementGrantReasonArr;
        }

        public static EntitlementGrantReason valueOf(String str) {
            return (EntitlementGrantReason) Enum.valueOf(EntitlementGrantReason.class, str);
        }

        public static EntitlementGrantReason[] values() {
            return (EntitlementGrantReason[]) $VALUES.clone();
        }

        public EntitlementGrantReason(String str, int i) {
        }
    }

    public static class EntitlementImages extends NativeModuleParcel {
        public AppImage HERO;
        public AppImage LANDSCAPE_SMALL;
        public AppImage SOURCE_MAIN;
        public AppImage SOURCE_SQUARE;
        public AppImage SOURCE_TINY;

        public static final EntitlementImages makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            EntitlementImages entitlementImages = new EntitlementImages();
            entitlementImages.setFromJSONObject(jSONObject);
            return entitlementImages;
        }

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
    }

    public static class EnvironmentSource extends NativeModuleParcel {
        public String displayName;
        public String id;

        public static final EnvironmentSource makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            EnvironmentSource environmentSource = new EnvironmentSource();
            environmentSource.setFromJSONObject(jSONObject);
            return environmentSource;
        }

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

    public static class LibraryUpdate extends NativeModuleParcel {
        public String packageName;

        public static final LibraryUpdate makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            LibraryUpdate libraryUpdate = new LibraryUpdate();
            libraryUpdate.setFromJSONObject(jSONObject);
            return libraryUpdate;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("packageName", this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            String optString;
            if (jSONObject.isNull("packageName")) {
                optString = null;
            } else {
                optString = jSONObject.optString("packageName");
            }
            this.packageName = optString;
        }
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

    public static class UnknownSource extends NativeModuleParcel {
        public double apkFullSizeBytes;
        public String applicationName;
        public String packageName;

        public static final UnknownSource makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UnknownSource unknownSource = new UnknownSource();
            unknownSource.setFromJSONObject(jSONObject);
            return unknownSource;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("apkFullSizeBytes", this.apkFullSizeBytes);
                jSONObject.put("applicationName", this.applicationName);
                jSONObject.put("packageName", this.packageName);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.apkFullSizeBytes = jSONObject.optDouble("apkFullSizeBytes", 0.0d);
            this.applicationName = jSONObject.optString("applicationName");
            this.packageName = jSONObject.optString("packageName");
        }
    }

    public abstract void cancelDownloadImpl(String str);

    public abstract void downloadAndInstallAsyncImpl(String str, LibraryRequestOrigin libraryRequestOrigin, Resolver<Void> resolver);

    public abstract void fetchDisabledAppsAsyncImpl(Resolver<List<UnknownSource>> resolver);

    public abstract void fetchLibraryAsyncImpl(Resolver<List<Entitlement>> resolver);

    public abstract void fetchLibraryItemAsyncImpl(String str, Resolver<Entitlement> resolver);

    public abstract void fetchUnknownSourcesAsyncImpl(boolean z, boolean z2, Resolver<List<UnknownSource>> resolver);

    public abstract void genControllerSupportForPackageImpl(String str, Resolver<List<SupportedController>> resolver);

    public abstract void getInitialLibraryImpl(Resolver<List<Entitlement>> resolver);

    public abstract void getInstalledAppPermissionsImpl(String str, Resolver<List<JSONObject>> resolver);

    public abstract void getPermissionInfoAsyncImpl(String str, Resolver<JSONObject> resolver);

    public abstract void isInstalledAsyncImpl(String str, Resolver<Boolean> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void networkRefreshAsyncImpl(Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public abstract void toggleOffUnseenAsyncImpl(String str, Resolver<Void> resolver);

    public abstract void uninstallImpl(String str, LibraryRequestOrigin libraryRequestOrigin, Resolver<Void> resolver);

    public abstract void updateRecentActivityImpl(String str);

    public static final List<Pair<String, String>> describe() {
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

    public final void emitOnLibraryUpdated(LibraryUpdate libraryUpdate) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "LibraryModule_onLibraryUpdated", String.valueOf(libraryUpdate.convertToJSONObject()));
    }

    public final void downloadAndInstallAsync(String str, String str2, int i, int i2) {
        downloadAndInstallAsyncImpl(str, LibraryRequestOrigin.valueOf(str2), ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void fetchDisabledAppsAsync(int i, int i2) {
        fetchDisabledAppsAsyncImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    public final void fetchLibraryAsync(int i, int i2) {
        fetchLibraryAsyncImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    public final void fetchLibraryItemAsync(String str, int i, int i2) {
        fetchLibraryItemAsyncImpl(str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void fetchUnknownSourcesAsync(boolean z, boolean z2, int i, int i2) {
        fetchUnknownSourcesAsyncImpl(z, z2, ResolverFactory.createParcelListResolver(this, i, i2));
    }

    public final void genControllerSupportForPackage(String str, int i, int i2) {
        genControllerSupportForPackageImpl(str, ResolverFactory.createEnumListResolver(this, i, i2));
    }

    public final void getInitialLibrary(int i, int i2) {
        getInitialLibraryImpl(ResolverFactory.createParcelListResolver(this, i, i2));
    }

    public final void getInstalledAppPermissions(String str, int i, int i2) {
        getInstalledAppPermissionsImpl(str, ResolverFactory.createObjectListResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void getPermissionInfoAsync(String str, int i, int i2) {
        getPermissionInfoAsyncImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void isInstalledAsync(String str, int i, int i2) {
        isInstalledAsyncImpl(str, ResolverFactory.createBasicResolver(this, i, i2));
    }

    public final void networkRefreshAsync(int i, int i2) {
        networkRefreshAsyncImpl(ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void toggleOffUnseenAsync(String str, int i, int i2) {
        toggleOffUnseenAsyncImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void uninstall(String str, String str2, int i, int i2) {
        uninstallImpl(str, LibraryRequestOrigin.valueOf(str2), ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void cancelDownload(String str) {
        cancelDownloadImpl(str);
    }

    public final void updateRecentActivity(String str) {
        updateRecentActivityImpl(str);
    }
}
