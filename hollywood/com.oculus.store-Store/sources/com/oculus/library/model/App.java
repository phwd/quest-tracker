package com.oculus.library.model;

import android.text.TextUtils;
import com.oculus.library.model.Image;
import com.oculus.ocms.library.provider.contract.OCMSLibraryContract;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {
    public final List<String> accessFeatureKeys;
    @Deprecated
    public long apkFullSizeBytes;
    public final List<AppMediaCapability> appMediaCapabilities;
    public final String appScopedUserId;
    public final String applicationGroupingId;
    public final String applicationOrganizationId;
    public final boolean autoUpdateEnabled;
    public final Category category;
    public final CloudStorageStatus cloudStorageStatus;
    public final ComfortRating comfortRating;
    public final String currentLanguagePack;
    public final String displayName;
    public final long downloadSizeBytes;
    public final long downloadedSizeBytes;
    public final boolean ducNonCompliant;
    public final String entitlementHash;
    public final String environmentSourceDisplayName;
    public final String environmentSourceId;
    public final long grantExpirationMs;
    public final GrantReason grantReason;
    public final long grantTimeMs;
    public final HeadTracking headTracking;
    public final String id;
    public final Map<Image.ImageName, Image> images;
    public final boolean installableOnExternalStorage;
    public final String internetConnection;
    public final boolean isConcept;
    public final String isDemoOf;
    public final boolean isTVApp;
    public final boolean isTest;
    public final boolean isUnseen;
    public final List<String> latestPermissions;
    public final int latestTargetSdkVersion;
    public final long latestVersionCode;
    public final String latestVersionName;
    public final MicrophoneUsage microphoneUsage;
    public final long minRecommendedVersionCode;
    public final long minRequiredVersionCode;
    public final String packageName;
    public final SupportedPlatform platform;
    public final List<PlayMode> playModes;
    public final long recentActivityMs;
    public final AppStatus status;
    public final List<InputDevice> supportedInputDevices;
    public final String systemUiAsOverlayMode;
    public final long totalActivityMs;
    public final String trustedBinaryStatus;
    public final long versionCode;
    public final String versionName;

    public static Builder forTesting() {
        return new Builder().withId(OCMSLibraryContract.ASSETS_PATH_BY_ID).withPackageName("pkg").withStatus(AppStatus.ENTITLED).withDisplayName("name").withComfortRating(ComfortRating.COMFORTABLE_FOR_ALL).withImages(Collections.EMPTY_MAP).withCategory(Category.APPS).withDownloadSizeBytes(0).withDownloadedSizeBytes(0).withVersion(1, "1").withLatestVersion(2, "2").withLatestTargetSdkVersion(25).withPermission(Collections.emptyList()).withSupportedDevices(Collections.emptyList()).withInstallableExternalStorage(false).withEntitlementHash("fake").withAppScopedUserId("scoped").withApplicationGroupingId("group-id").withApplicationOrganizationId("organization-id").withMicrophoneUsage(MicrophoneUsage.NONE).withMinRecommendedVersionCode(2).withMinRequiredVersionCode(1).withIsDemoOf("pkg").withPlatform(SupportedPlatform.ANDROID_6DOF).withHeadTracking(HeadTracking.REQUIRE_6DOF).withAppMediaCapabilities(Collections.singletonList(AppMediaCapability.SCREENRECORDING)).withPlayMode(Collections.singletonList(PlayMode.ROOM_SCALE)).withCloudStorageStatus(CloudStorageStatus.DISABLED).withIsUnseen(false).withRecentActivityMs(0).withTotalActivityMs(0).withGrantReason(GrantReason.OCULUS_EMPLOYEE_OFFER).withGrantTimeMs(1).withGrantExpirationMs(1000).withApkSize(0).withCurrentLanguagePack("en-US").withInternetConnection("interwebs").withTrustedBinaryStatus("TRUSTED").withIsConcept(false).withIsTVApp(false).withIsTest(false).withSystemUiAsOverlayMode("ENABLED").withAutoUpdateEnabled(false).withDucNonCompliant(false).withAccessFeature(Collections.emptyList()).withEnvironmentSourceId("env-src-id").withEnvironmentSourceDisplayName("env-src-display-name");
    }

    public App(String id2, String packageName2, AppStatus status2, CloudStorageStatus cloudStorageStatus2, String displayName2, ComfortRating comfortRating2, Map<Image.ImageName, Image> images2, long downloadSizeBytes2, long downloadedSizeBytes2, long versionCode2, String versionName2, long latestVersionCode2, String latestVersionName2, int latestTargetSdkVersion2, List<String> latestPermissions2, List<InputDevice> supportedInputDevices2, boolean installableOnExternalStorage2, String entitlementHash2, String appScopedUserId2, String applicationGroupingId2, String applicationOrganizationId2, MicrophoneUsage microphoneUsage2, boolean isUnseen2, long recentActivityMs2, long totalActivityMs2, long minRecommendedVersionCode2, long minRequiredVersionCode2, String isDemoOf2, SupportedPlatform platform2, List<AppMediaCapability> appMediaCapabilities2, List<PlayMode> playModes2, Category category2, HeadTracking headTracking2, GrantReason grantReason2, long grantTimeMs2, long grantExpirationMs2, long apkSize, String currentLanguagePack2, String internetConnection2, String trustedBinaryStatus2, boolean isConcept2, boolean isTVApp2, boolean isTest2, String systemUiAsOverlayMode2, boolean autoUpdateEnabled2, boolean ducNonCompliant2, List<String> accessFeatureKeys2, String environmentSourceId2, String environmentSourceDisplayName2) {
        if (TextUtils.isEmpty(entitlementHash2)) {
            throw new IllegalArgumentException("null entitlement hash");
        }
        this.id = id2;
        this.packageName = packageName2;
        this.status = status2;
        this.cloudStorageStatus = cloudStorageStatus2;
        this.displayName = displayName2;
        this.comfortRating = comfortRating2;
        this.images = images2;
        this.downloadSizeBytes = downloadSizeBytes2;
        this.downloadedSizeBytes = downloadedSizeBytes2;
        this.versionCode = versionCode2;
        this.versionName = versionName2;
        this.latestVersionCode = latestVersionCode2;
        this.latestVersionName = latestVersionName2;
        this.latestTargetSdkVersion = latestTargetSdkVersion2;
        this.latestPermissions = latestPermissions2;
        this.supportedInputDevices = supportedInputDevices2;
        this.installableOnExternalStorage = installableOnExternalStorage2;
        this.entitlementHash = entitlementHash2;
        this.appScopedUserId = appScopedUserId2;
        this.applicationGroupingId = applicationGroupingId2;
        this.applicationOrganizationId = applicationOrganizationId2;
        this.microphoneUsage = microphoneUsage2;
        this.isUnseen = isUnseen2;
        this.recentActivityMs = recentActivityMs2;
        this.totalActivityMs = totalActivityMs2;
        this.minRecommendedVersionCode = minRecommendedVersionCode2;
        this.minRequiredVersionCode = minRequiredVersionCode2;
        this.isDemoOf = isDemoOf2;
        this.platform = platform2;
        this.appMediaCapabilities = appMediaCapabilities2;
        this.headTracking = headTracking2;
        this.playModes = playModes2;
        this.category = category2;
        this.grantReason = grantReason2;
        this.grantTimeMs = grantTimeMs2;
        this.grantExpirationMs = grantExpirationMs2;
        this.apkFullSizeBytes = apkSize;
        this.currentLanguagePack = currentLanguagePack2;
        this.internetConnection = internetConnection2;
        this.trustedBinaryStatus = trustedBinaryStatus2;
        this.isConcept = isConcept2;
        this.isTVApp = isTVApp2;
        this.isTest = isTest2;
        this.systemUiAsOverlayMode = systemUiAsOverlayMode2;
        this.autoUpdateEnabled = autoUpdateEnabled2;
        this.ducNonCompliant = ducNonCompliant2;
        this.accessFeatureKeys = accessFeatureKeys2;
        this.environmentSourceId = environmentSourceId2;
        this.environmentSourceDisplayName = environmentSourceDisplayName2;
    }

    public boolean hasUpdate() {
        return this.versionCode >= 0 && this.latestVersionCode > this.versionCode;
    }

    public boolean isInstalled() {
        return this.status == AppStatus.INSTALLED;
    }

    public boolean isDeveloper() {
        return this.grantReason == GrantReason.DEVELOPER;
    }

    public String toString() {
        return "App[id=" + this.id + ", packageName=" + this.packageName + "]";
    }

    public static class Editor {
        public Optional<String> appScopedUserId = Optional.empty();
        public Optional<CloudStorageStatus> cloudStorageStatus = Optional.empty();
        public Optional<Long> downloadSizeBytes = Optional.empty();
        public Optional<Long> downloadedSizeBytes = Optional.empty();
        public Optional<Boolean> isUnseen = Optional.empty();
        public Optional<String> languagePack = Optional.empty();
        public String packageName;
        public Optional<Long> recentActivityMs = Optional.empty();
        public Optional<AppStatus> status = Optional.empty();
        public Optional<Long> totalActivityMs = Optional.empty();
        public Optional<String> trustedBinaryStatus = Optional.empty();

        public Editor(String packageName2) {
            this.packageName = packageName2;
        }

        public Editor withStatus(AppStatus newStatus) {
            if (newStatus == null) {
                throw new IllegalArgumentException("app status must not be null");
            }
            this.status = Optional.of(newStatus);
            if (newStatus == AppStatus.ENTITLED) {
                this.downloadedSizeBytes = Optional.of(0L);
            }
            return this;
        }

        public Editor withCloudStorageStatus(CloudStorageStatus newStatus) {
            if (newStatus == null) {
                throw new IllegalArgumentException("cloud storage status must not be null");
            }
            this.cloudStorageStatus = Optional.of(newStatus);
            return this;
        }

        public Editor withIsUnseen(boolean isUnseen2) {
            this.isUnseen = Optional.of(Boolean.valueOf(isUnseen2));
            return this;
        }

        public Editor withDownloadSizeBytes(long downloadSizeBytes2) {
            this.downloadSizeBytes = Optional.of(Long.valueOf(downloadSizeBytes2));
            return this;
        }

        public Editor withDownloadedSizeBytes(long downloadedSizeBytes2) {
            this.downloadedSizeBytes = Optional.of(Long.valueOf(downloadedSizeBytes2));
            return this;
        }

        public Editor withRecentActivityMs(long recentActivityMs2) {
            this.recentActivityMs = Optional.of(Long.valueOf(recentActivityMs2));
            return this;
        }

        public Editor withTotalActivityMs(long totalActivityMs2) {
            this.totalActivityMs = Optional.of(Long.valueOf(totalActivityMs2));
            return this;
        }

        public Editor withAppScopedUserId(String appScopedUserId2) {
            this.appScopedUserId = Optional.of(appScopedUserId2);
            return this;
        }

        public Editor withCurrentLanguagePack(String languagePack2) {
            this.languagePack = Optional.of(languagePack2);
            return this;
        }

        public Editor withTrustedBinaryStatus(String status2) {
            this.trustedBinaryStatus = Optional.of(status2);
            return this;
        }

        public App apply(App app) {
            Builder builder = new Builder(app);
            if (this.status.isPresent()) {
                builder.withStatus(this.status.get());
            }
            if (this.cloudStorageStatus.isPresent()) {
                builder.withCloudStorageStatus(this.cloudStorageStatus.get());
            }
            if (this.downloadedSizeBytes.isPresent()) {
                builder.withDownloadedSizeBytes(this.downloadedSizeBytes.get().longValue());
            }
            if (this.downloadSizeBytes.isPresent()) {
                builder.withDownloadSizeBytes(this.downloadSizeBytes.get().longValue());
            }
            if (this.appScopedUserId.isPresent()) {
                builder.withAppScopedUserId(this.appScopedUserId.get());
            }
            if (this.isUnseen.isPresent()) {
                builder.withIsUnseen(this.isUnseen.get().booleanValue());
            }
            if (this.recentActivityMs.isPresent()) {
                builder.withRecentActivityMs(this.recentActivityMs.get().longValue());
            }
            if (this.totalActivityMs.isPresent()) {
                builder.withTotalActivityMs(this.totalActivityMs.get().longValue());
            }
            if (this.languagePack.isPresent()) {
                builder.withCurrentLanguagePack(this.languagePack.get());
            }
            if (this.trustedBinaryStatus.isPresent()) {
                builder.withTrustedBinaryStatus(this.trustedBinaryStatus.get());
            }
            return builder.build();
        }
    }

    public static class Builder {
        private List<String> accessFeatureKeys;
        @Deprecated
        private long apkFullSizeBytes;
        private List<AppMediaCapability> appMediaCapabilities;
        private String appScopedUserId;
        private String applicationGroupingId;
        private String applicationOrganizationId;
        private boolean autoUpdateEnabled;
        private Category category;
        private CloudStorageStatus cloudStorageStatus;
        private ComfortRating comfortRating;
        private String currentLanguagePack;
        private String displayName;
        private long downloadSizeBytes;
        private long downloadedSizeBytes;
        private boolean ducNonCompliant;
        private String entitlementHash;
        private String environmentSourceDisplayName;
        private String environmentSourceId;
        private long grantExpiration;
        private GrantReason grantReason;
        private long grantTime;
        private HeadTracking headTracking;
        private String id;
        private Map<Image.ImageName, Image> images;
        private boolean installableFromExternalStorage;
        private String internetConnection;
        private boolean isConcept;
        private String isDemoOf;
        private boolean isTVApp;
        private boolean isTest;
        private boolean isUnseen;
        private List<String> latestPermissions;
        private int latestTargetSdkVersion;
        private long latestVersionCode;
        private String latestVersionName;
        private MicrophoneUsage microphoneUsage;
        private long minRecommendedVersionCode;
        private long minRequiredVersionCode;
        private String packageName;
        private SupportedPlatform platform;
        private List<PlayMode> playMode;
        private long recentActivityMs;
        private AppStatus status;
        private List<InputDevice> supportedInputDevices;
        private String systemUiOverlayMode;
        private long totalActivityMs;
        private String trustedBinaryStatus;
        private long versionCode;
        private String versionName;

        public static class LocalData {
            public final CloudStorageStatus cloudStorageStatus;
            public final String currentLanguagePack;
            public final long downloadBytes;
            public final long downloadedBytes;
            public final boolean isUnseen;
            public final AppStatus status;
            public final long totalActivityMs;
            public final String trustedBinaryStatus;

            public LocalData(App app) {
                this.status = app.status;
                this.downloadBytes = app.downloadSizeBytes;
                this.downloadedBytes = app.downloadedSizeBytes;
                this.cloudStorageStatus = app.cloudStorageStatus;
                this.totalActivityMs = app.totalActivityMs;
                this.isUnseen = app.isUnseen;
                this.currentLanguagePack = app.currentLanguagePack;
                this.trustedBinaryStatus = app.trustedBinaryStatus;
            }

            /* access modifiers changed from: private */
            /* access modifiers changed from: public */
            private void applyToBuilder(Builder builder) {
                builder.status = this.status;
                builder.downloadSizeBytes = this.downloadBytes;
                builder.downloadedSizeBytes = this.downloadedBytes;
                builder.cloudStorageStatus = this.cloudStorageStatus;
                builder.totalActivityMs = this.totalActivityMs;
                builder.isUnseen = this.isUnseen;
                builder.currentLanguagePack = this.currentLanguagePack;
                builder.trustedBinaryStatus = this.trustedBinaryStatus;
            }
        }

        public Builder() {
        }

        public Builder(App app) {
            this.id = app.id;
            this.packageName = app.packageName;
            this.status = app.status;
            this.cloudStorageStatus = app.cloudStorageStatus;
            this.displayName = app.displayName;
            this.comfortRating = app.comfortRating;
            this.images = app.images;
            this.downloadSizeBytes = app.downloadSizeBytes;
            this.downloadedSizeBytes = app.downloadedSizeBytes;
            this.versionCode = app.versionCode;
            this.versionName = app.versionName;
            this.latestVersionCode = app.latestVersionCode;
            this.latestVersionName = app.latestVersionName;
            this.latestTargetSdkVersion = app.latestTargetSdkVersion;
            this.latestPermissions = app.latestPermissions;
            this.supportedInputDevices = app.supportedInputDevices;
            this.installableFromExternalStorage = app.installableOnExternalStorage;
            this.entitlementHash = app.entitlementHash;
            this.appScopedUserId = app.appScopedUserId;
            this.applicationGroupingId = app.applicationGroupingId;
            this.applicationOrganizationId = app.applicationOrganizationId;
            this.microphoneUsage = app.microphoneUsage;
            this.isUnseen = app.isUnseen;
            this.recentActivityMs = app.recentActivityMs;
            this.totalActivityMs = app.totalActivityMs;
            this.minRecommendedVersionCode = app.minRecommendedVersionCode;
            this.minRequiredVersionCode = app.minRequiredVersionCode;
            this.isDemoOf = app.isDemoOf;
            this.platform = app.platform;
            this.appMediaCapabilities = app.appMediaCapabilities;
            this.headTracking = app.headTracking;
            this.playMode = app.playModes;
            this.category = app.category;
            this.grantReason = app.grantReason;
            this.grantTime = app.grantTimeMs;
            this.grantExpiration = app.grantExpirationMs;
            this.apkFullSizeBytes = app.apkFullSizeBytes;
            this.currentLanguagePack = app.currentLanguagePack;
            this.internetConnection = app.internetConnection;
            this.trustedBinaryStatus = app.trustedBinaryStatus;
            this.isConcept = app.isConcept;
            this.isTVApp = app.isTVApp;
            this.isTest = app.isTest;
            this.systemUiOverlayMode = app.systemUiAsOverlayMode;
            this.autoUpdateEnabled = app.autoUpdateEnabled;
            this.ducNonCompliant = app.ducNonCompliant;
            this.accessFeatureKeys = app.accessFeatureKeys;
            this.environmentSourceId = app.environmentSourceId;
            this.environmentSourceDisplayName = app.environmentSourceDisplayName;
        }

        public App build() {
            return new App(this.id, this.packageName, this.status, this.cloudStorageStatus, this.displayName, this.comfortRating, this.images, this.downloadSizeBytes, this.downloadedSizeBytes, this.versionCode, this.versionName, this.latestVersionCode, this.latestVersionName, this.latestTargetSdkVersion, this.latestPermissions, this.supportedInputDevices, this.installableFromExternalStorage, this.entitlementHash, this.appScopedUserId, this.applicationGroupingId, this.applicationOrganizationId, this.microphoneUsage, this.isUnseen, this.recentActivityMs, this.totalActivityMs, this.minRecommendedVersionCode, this.minRequiredVersionCode, this.isDemoOf, this.platform, this.appMediaCapabilities, this.playMode, this.category, this.headTracking, this.grantReason, this.grantTime, this.grantExpiration, this.apkFullSizeBytes, this.currentLanguagePack, this.internetConnection, this.trustedBinaryStatus, this.isConcept, this.isTVApp, this.isTest, this.systemUiOverlayMode, this.autoUpdateEnabled, this.ducNonCompliant, this.accessFeatureKeys, this.environmentSourceId, this.environmentSourceDisplayName);
        }

        public Builder withStatus(AppStatus newStatus) {
            this.status = newStatus;
            if (this.status == AppStatus.ENTITLED) {
                this.downloadedSizeBytes = 0;
            }
            return this;
        }

        public Builder withCloudStorageStatus(CloudStorageStatus newStatus) {
            this.cloudStorageStatus = newStatus;
            return this;
        }

        public Builder withIsUnseen(boolean isUnseen2) {
            this.isUnseen = isUnseen2;
            return this;
        }

        public Builder withDisplayName(String name) {
            this.displayName = name;
            return this;
        }

        public Builder withComfortRating(ComfortRating comfortRating2) {
            this.comfortRating = comfortRating2;
            return this;
        }

        public Builder withImages(Map<Image.ImageName, Image> images2) {
            this.images = images2;
            return this;
        }

        public Builder withDownloadSizeBytes(long downloadSizeBytes2) {
            this.downloadSizeBytes = downloadSizeBytes2;
            return this;
        }

        public Builder withDownloadedSizeBytes(long downloadedSizeBytes2) {
            this.downloadedSizeBytes = downloadedSizeBytes2;
            return this;
        }

        public Builder withRecentActivityMs(long recentActivityMs2) {
            this.recentActivityMs = recentActivityMs2;
            return this;
        }

        public Builder withTotalActivityMs(long totalActivityMs2) {
            this.totalActivityMs = totalActivityMs2;
            return this;
        }

        public Builder withIncrementedActivityMs(long addActivityMs) {
            this.totalActivityMs += addActivityMs;
            return this;
        }

        public Builder withUnseen(boolean unseen) {
            this.isUnseen = unseen;
            return this;
        }

        public Builder withLocalData(LocalData localData) {
            localData.applyToBuilder(this);
            return this;
        }

        public Builder withVersion(long versionCode2, String versionName2) {
            this.versionCode = versionCode2;
            this.versionName = versionName2;
            return this;
        }

        public Builder withLatestVersion(long versionCode2, String versionName2) {
            this.latestVersionCode = versionCode2;
            this.latestVersionName = versionName2;
            return this;
        }

        public Builder withLatestTargetSdkVersion(int sdkVersion) {
            this.latestTargetSdkVersion = sdkVersion;
            return this;
        }

        public Builder withEntitlementHash(String entitlementHash2) {
            this.entitlementHash = entitlementHash2;
            return this;
        }

        public Builder withMinRecommendedVersionCode(long version) {
            this.minRecommendedVersionCode = version;
            return this;
        }

        public Builder withMinRequiredVersionCode(long version) {
            this.minRequiredVersionCode = version;
            return this;
        }

        public Builder withId(String id2) {
            this.id = id2;
            return this;
        }

        public Builder withPackageName(String packageName2) {
            this.packageName = packageName2;
            return this;
        }

        public Builder withApplicationGroupingId(String applicationGroupingId2) {
            this.applicationGroupingId = applicationGroupingId2;
            return this;
        }

        public Builder withApplicationOrganizationId(String applicationOrganizationId2) {
            this.applicationOrganizationId = applicationOrganizationId2;
            return this;
        }

        public Builder withMicrophoneUsage(MicrophoneUsage microphoneUsage2) {
            this.microphoneUsage = microphoneUsage2;
            return this;
        }

        public Builder withAppScopedUserId(String appScopedUserId2) {
            this.appScopedUserId = appScopedUserId2;
            return this;
        }

        public Builder withIsDemoOf(String isDemoOf2) {
            this.isDemoOf = isDemoOf2;
            return this;
        }

        public Builder withPlatform(SupportedPlatform platform2) {
            this.platform = platform2;
            return this;
        }

        public Builder withAppMediaCapabilities(List<AppMediaCapability> appMediaCapabilities2) {
            this.appMediaCapabilities = appMediaCapabilities2;
            return this;
        }

        public Builder withPlayMode(List<PlayMode> playMode2) {
            this.playMode = playMode2;
            return this;
        }

        public Builder withCategory(Category category2) {
            this.category = category2;
            return this;
        }

        public Builder withHeadTracking(HeadTracking headTracking2) {
            this.headTracking = headTracking2;
            return this;
        }

        public Builder withPermission(List<String> permissions) {
            this.latestPermissions = permissions;
            return this;
        }

        public Builder withSupportedDevices(List<InputDevice> devices) {
            this.supportedInputDevices = devices;
            return this;
        }

        public Builder withInstallableExternalStorage(boolean supported) {
            this.installableFromExternalStorage = supported;
            return this;
        }

        public Builder withGrantReason(GrantReason reason) {
            this.grantReason = reason;
            return this;
        }

        public Builder withGrantTimeMs(long timeMs) {
            this.grantTime = timeMs;
            return this;
        }

        public Builder withGrantExpirationMs(long timeMs) {
            this.grantExpiration = timeMs;
            return this;
        }

        @Deprecated
        public Builder withApkSize(long size) {
            this.apkFullSizeBytes = size;
            return this;
        }

        public Builder withCurrentLanguagePack(String languagePack) {
            this.currentLanguagePack = languagePack;
            return this;
        }

        public Builder withInternetConnection(String internetConnection2) {
            this.internetConnection = internetConnection2;
            return this;
        }

        public Builder withTrustedBinaryStatus(String trustedBinaryStatus2) {
            this.trustedBinaryStatus = trustedBinaryStatus2;
            return this;
        }

        public Builder withIsConcept(boolean isConcept2) {
            this.isConcept = isConcept2;
            return this;
        }

        public Builder withIsTVApp(boolean isTVApp2) {
            this.isTVApp = isTVApp2;
            return this;
        }

        public Builder withIsTest(boolean isTest2) {
            this.isTest = isTest2;
            return this;
        }

        public Builder withSystemUiAsOverlayMode(String systemUiOverlayMode2) {
            this.systemUiOverlayMode = systemUiOverlayMode2;
            return this;
        }

        public Builder withAutoUpdateEnabled(boolean autoUpdateEnabled2) {
            this.autoUpdateEnabled = autoUpdateEnabled2;
            return this;
        }

        public Builder withDucNonCompliant(boolean ducNonCompliant2) {
            this.ducNonCompliant = ducNonCompliant2;
            return this;
        }

        public Builder withAccessFeature(List<String> accessFeatureKeys2) {
            this.accessFeatureKeys = accessFeatureKeys2;
            return this;
        }

        public Builder withEnvironmentSourceId(String environmentSourceId2) {
            this.environmentSourceId = environmentSourceId2;
            return this;
        }

        public Builder withEnvironmentSourceDisplayName(String environmentSourceDisplayName2) {
            this.environmentSourceDisplayName = environmentSourceDisplayName2;
            return this;
        }
    }
}
