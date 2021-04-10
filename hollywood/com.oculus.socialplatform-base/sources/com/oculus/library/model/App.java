package com.oculus.library.model;

import X.AnonymousClass006;
import android.text.TextUtils;
import com.oculus.horizoncontent.social.SocialDestinationDeeplinkabilityType;
import com.oculus.library.model.Image;
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

    public static class Builder {
        public List<String> accessFeatureKeys;
        @Deprecated
        public long apkFullSizeBytes;
        public List<AppMediaCapability> appMediaCapabilities;
        public String appScopedUserId;
        public String applicationGroupingId;
        public String applicationOrganizationId;
        public boolean autoUpdateEnabled;
        public Category category;
        public CloudStorageStatus cloudStorageStatus;
        public ComfortRating comfortRating;
        public String currentLanguagePack;
        public String displayName;
        public long downloadSizeBytes;
        public long downloadedSizeBytes;
        public boolean ducNonCompliant;
        public String entitlementHash;
        public String environmentSourceDisplayName;
        public String environmentSourceId;
        public long grantExpiration;
        public GrantReason grantReason;
        public long grantTime;
        public HeadTracking headTracking;
        public String id;
        public Map<Image.ImageName, Image> images;
        public boolean installableFromExternalStorage;
        public String internetConnection;
        public boolean isConcept;
        public String isDemoOf;
        public boolean isTVApp;
        public boolean isTest;
        public boolean isUnseen;
        public List<String> latestPermissions;
        public int latestTargetSdkVersion;
        public long latestVersionCode;
        public String latestVersionName;
        public MicrophoneUsage microphoneUsage;
        public long minRecommendedVersionCode;
        public long minRequiredVersionCode;
        public String packageName;
        public SupportedPlatform platform;
        public List<PlayMode> playMode;
        public long recentActivityMs;
        public AppStatus status;
        public List<InputDevice> supportedInputDevices;
        public String systemUiOverlayMode;
        public long totalActivityMs;
        public String trustedBinaryStatus;
        public long versionCode;
        public String versionName;

        public static class LocalData {
            public final CloudStorageStatus cloudStorageStatus;
            public final String currentLanguagePack;
            public final long downloadBytes;
            public final long downloadedBytes;
            public final boolean isUnseen;
            public final AppStatus status;
            public final long totalActivityMs;
            public final String trustedBinaryStatus;

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
        }

        public App build() {
            return new App(this.id, this.packageName, this.status, this.cloudStorageStatus, this.displayName, this.comfortRating, this.images, this.downloadSizeBytes, this.downloadedSizeBytes, this.versionCode, this.versionName, this.latestVersionCode, this.latestVersionName, this.latestTargetSdkVersion, this.latestPermissions, this.supportedInputDevices, this.installableFromExternalStorage, this.entitlementHash, this.appScopedUserId, this.applicationGroupingId, this.applicationOrganizationId, this.microphoneUsage, this.isUnseen, this.recentActivityMs, this.totalActivityMs, this.minRecommendedVersionCode, this.minRequiredVersionCode, this.isDemoOf, this.platform, this.appMediaCapabilities, this.playMode, this.category, this.headTracking, this.grantReason, this.grantTime, this.grantExpiration, this.apkFullSizeBytes, this.currentLanguagePack, this.internetConnection, this.trustedBinaryStatus, this.isConcept, this.isTVApp, this.isTest, this.systemUiOverlayMode, this.autoUpdateEnabled, this.ducNonCompliant, this.accessFeatureKeys, this.environmentSourceId, this.environmentSourceDisplayName);
        }

        public Builder withIncrementedActivityMs(long j) {
            this.totalActivityMs += j;
            return this;
        }

        public Builder withLatestVersion(long j, String str) {
            this.latestVersionCode = j;
            this.latestVersionName = str;
            return this;
        }

        public Builder withStatus(AppStatus appStatus) {
            this.status = appStatus;
            if (appStatus == AppStatus.ENTITLED) {
                this.downloadedSizeBytes = 0;
            }
            return this;
        }

        public Builder withVersion(long j, String str) {
            this.versionCode = j;
            this.versionName = str;
            return this;
        }

        public Builder withAccessFeature(List<String> list) {
            this.accessFeatureKeys = list;
            return this;
        }

        @Deprecated
        public Builder withApkSize(long j) {
            this.apkFullSizeBytes = j;
            return this;
        }

        public Builder withAppMediaCapabilities(List<AppMediaCapability> list) {
            this.appMediaCapabilities = list;
            return this;
        }

        public Builder withAppScopedUserId(String str) {
            this.appScopedUserId = str;
            return this;
        }

        public Builder withApplicationGroupingId(String str) {
            this.applicationGroupingId = str;
            return this;
        }

        public Builder withApplicationOrganizationId(String str) {
            this.applicationOrganizationId = str;
            return this;
        }

        public Builder withAutoUpdateEnabled(boolean z) {
            this.autoUpdateEnabled = z;
            return this;
        }

        public Builder withCategory(Category category2) {
            this.category = category2;
            return this;
        }

        public Builder withCloudStorageStatus(CloudStorageStatus cloudStorageStatus2) {
            this.cloudStorageStatus = cloudStorageStatus2;
            return this;
        }

        public Builder withComfortRating(ComfortRating comfortRating2) {
            this.comfortRating = comfortRating2;
            return this;
        }

        public Builder withCurrentLanguagePack(String str) {
            this.currentLanguagePack = str;
            return this;
        }

        public Builder withDisplayName(String str) {
            this.displayName = str;
            return this;
        }

        public Builder withDownloadSizeBytes(long j) {
            this.downloadSizeBytes = j;
            return this;
        }

        public Builder withDownloadedSizeBytes(long j) {
            this.downloadedSizeBytes = j;
            return this;
        }

        public Builder withDucNonCompliant(boolean z) {
            this.ducNonCompliant = z;
            return this;
        }

        public Builder withEntitlementHash(String str) {
            this.entitlementHash = str;
            return this;
        }

        public Builder withEnvironmentSourceDisplayName(String str) {
            this.environmentSourceDisplayName = str;
            return this;
        }

        public Builder withEnvironmentSourceId(String str) {
            this.environmentSourceId = str;
            return this;
        }

        public Builder withGrantExpirationMs(long j) {
            this.grantExpiration = j;
            return this;
        }

        public Builder withGrantReason(GrantReason grantReason2) {
            this.grantReason = grantReason2;
            return this;
        }

        public Builder withGrantTimeMs(long j) {
            this.grantTime = j;
            return this;
        }

        public Builder withHeadTracking(HeadTracking headTracking2) {
            this.headTracking = headTracking2;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withImages(Map<Image.ImageName, Image> map) {
            this.images = map;
            return this;
        }

        public Builder withInstallableExternalStorage(boolean z) {
            this.installableFromExternalStorage = z;
            return this;
        }

        public Builder withInternetConnection(String str) {
            this.internetConnection = str;
            return this;
        }

        public Builder withIsConcept(boolean z) {
            this.isConcept = z;
            return this;
        }

        public Builder withIsDemoOf(String str) {
            this.isDemoOf = str;
            return this;
        }

        public Builder withIsTVApp(boolean z) {
            this.isTVApp = z;
            return this;
        }

        public Builder withIsTest(boolean z) {
            this.isTest = z;
            return this;
        }

        public Builder withIsUnseen(boolean z) {
            this.isUnseen = z;
            return this;
        }

        public Builder withLatestTargetSdkVersion(int i) {
            this.latestTargetSdkVersion = i;
            return this;
        }

        public Builder withLocalData(LocalData localData) {
            localData.applyToBuilder(this);
            return this;
        }

        public Builder withMicrophoneUsage(MicrophoneUsage microphoneUsage2) {
            this.microphoneUsage = microphoneUsage2;
            return this;
        }

        public Builder withMinRecommendedVersionCode(long j) {
            this.minRecommendedVersionCode = j;
            return this;
        }

        public Builder withMinRequiredVersionCode(long j) {
            this.minRequiredVersionCode = j;
            return this;
        }

        public Builder withPackageName(String str) {
            this.packageName = str;
            return this;
        }

        public Builder withPermission(List<String> list) {
            this.latestPermissions = list;
            return this;
        }

        public Builder withPlatform(SupportedPlatform supportedPlatform) {
            this.platform = supportedPlatform;
            return this;
        }

        public Builder withPlayMode(List<PlayMode> list) {
            this.playMode = list;
            return this;
        }

        public Builder withRecentActivityMs(long j) {
            this.recentActivityMs = j;
            return this;
        }

        public Builder withSupportedDevices(List<InputDevice> list) {
            this.supportedInputDevices = list;
            return this;
        }

        public Builder withSystemUiAsOverlayMode(String str) {
            this.systemUiOverlayMode = str;
            return this;
        }

        public Builder withTotalActivityMs(long j) {
            this.totalActivityMs = j;
            return this;
        }

        public Builder withTrustedBinaryStatus(String str) {
            this.trustedBinaryStatus = str;
            return this;
        }

        public Builder withUnseen(boolean z) {
            this.isUnseen = z;
            return this;
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

        public App apply(App app) {
            Builder builder = new Builder(app);
            if (this.status.isPresent()) {
                builder.withStatus(this.status.get());
            }
            if (this.cloudStorageStatus.isPresent()) {
                builder.cloudStorageStatus = this.cloudStorageStatus.get();
            }
            if (this.downloadedSizeBytes.isPresent()) {
                builder.downloadedSizeBytes = this.downloadedSizeBytes.get().longValue();
            }
            if (this.downloadSizeBytes.isPresent()) {
                builder.downloadSizeBytes = this.downloadSizeBytes.get().longValue();
            }
            if (this.appScopedUserId.isPresent()) {
                builder.appScopedUserId = this.appScopedUserId.get();
            }
            if (this.isUnseen.isPresent()) {
                builder.isUnseen = this.isUnseen.get().booleanValue();
            }
            if (this.recentActivityMs.isPresent()) {
                builder.recentActivityMs = this.recentActivityMs.get().longValue();
            }
            if (this.totalActivityMs.isPresent()) {
                builder.totalActivityMs = this.totalActivityMs.get().longValue();
            }
            if (this.languagePack.isPresent()) {
                builder.currentLanguagePack = this.languagePack.get();
            }
            if (this.trustedBinaryStatus.isPresent()) {
                builder.trustedBinaryStatus = this.trustedBinaryStatus.get();
            }
            return builder.build();
        }

        public Editor withCloudStorageStatus(CloudStorageStatus cloudStorageStatus2) {
            if (cloudStorageStatus2 != null) {
                this.cloudStorageStatus = Optional.of(cloudStorageStatus2);
                return this;
            }
            throw new IllegalArgumentException("cloud storage status must not be null");
        }

        public Editor withStatus(AppStatus appStatus) {
            if (appStatus != null) {
                this.status = Optional.of(appStatus);
                if (appStatus == AppStatus.ENTITLED) {
                    this.downloadedSizeBytes = Optional.of(0L);
                }
                return this;
            }
            throw new IllegalArgumentException("app status must not be null");
        }

        public Editor(String str) {
            this.packageName = str;
        }

        public Editor withAppScopedUserId(String str) {
            this.appScopedUserId = Optional.of(str);
            return this;
        }

        public Editor withCurrentLanguagePack(String str) {
            this.languagePack = Optional.of(str);
            return this;
        }

        public Editor withDownloadSizeBytes(long j) {
            this.downloadSizeBytes = Optional.of(Long.valueOf(j));
            return this;
        }

        public Editor withDownloadedSizeBytes(long j) {
            this.downloadedSizeBytes = Optional.of(Long.valueOf(j));
            return this;
        }

        public Editor withIsUnseen(boolean z) {
            this.isUnseen = Optional.of(Boolean.valueOf(z));
            return this;
        }

        public Editor withRecentActivityMs(long j) {
            this.recentActivityMs = Optional.of(Long.valueOf(j));
            return this;
        }

        public Editor withTotalActivityMs(long j) {
            this.totalActivityMs = Optional.of(Long.valueOf(j));
            return this;
        }

        public Editor withTrustedBinaryStatus(String str) {
            this.trustedBinaryStatus = Optional.of(str);
            return this;
        }
    }

    public static Builder forTesting() {
        Builder builder = new Builder();
        builder.id = "id";
        builder.packageName = "pkg";
        builder.withStatus(AppStatus.ENTITLED);
        builder.displayName = "name";
        builder.comfortRating = ComfortRating.COMFORTABLE_FOR_ALL;
        builder.images = Collections.EMPTY_MAP;
        builder.category = Category.APPS;
        builder.downloadSizeBytes = 0;
        builder.downloadedSizeBytes = 0;
        builder.versionCode = 1;
        builder.versionName = "1";
        builder.latestVersionCode = 2;
        builder.latestVersionName = "2";
        builder.latestTargetSdkVersion = 25;
        builder.latestPermissions = Collections.emptyList();
        builder.supportedInputDevices = Collections.emptyList();
        builder.installableFromExternalStorage = false;
        builder.entitlementHash = "fake";
        builder.appScopedUserId = "scoped";
        builder.applicationGroupingId = "group-id";
        builder.applicationOrganizationId = "organization-id";
        builder.microphoneUsage = MicrophoneUsage.NONE;
        builder.minRecommendedVersionCode = 2;
        builder.minRequiredVersionCode = 1;
        builder.isDemoOf = "pkg";
        builder.platform = SupportedPlatform.ANDROID_6DOF;
        builder.headTracking = HeadTracking.REQUIRE_6DOF;
        builder.appMediaCapabilities = Collections.singletonList(AppMediaCapability.SCREENRECORDING);
        builder.playMode = Collections.singletonList(PlayMode.ROOM_SCALE);
        builder.cloudStorageStatus = CloudStorageStatus.DISABLED;
        builder.isUnseen = false;
        builder.recentActivityMs = 0;
        builder.totalActivityMs = 0;
        builder.grantReason = GrantReason.OCULUS_EMPLOYEE_OFFER;
        builder.grantTime = 1;
        builder.grantExpiration = 1000;
        builder.apkFullSizeBytes = 0;
        builder.currentLanguagePack = "en-US";
        builder.internetConnection = "interwebs";
        builder.trustedBinaryStatus = "TRUSTED";
        builder.isConcept = false;
        builder.isTVApp = false;
        builder.isTest = false;
        builder.systemUiOverlayMode = SocialDestinationDeeplinkabilityType.ENABLED;
        builder.autoUpdateEnabled = false;
        builder.ducNonCompliant = false;
        builder.accessFeatureKeys = Collections.emptyList();
        builder.environmentSourceId = "env-src-id";
        builder.environmentSourceDisplayName = "env-src-display-name";
        return builder;
    }

    public boolean hasUpdate() {
        long j = this.versionCode;
        if (j < 0 || this.latestVersionCode <= j) {
            return false;
        }
        return true;
    }

    public boolean isDeveloper() {
        if (this.grantReason == GrantReason.DEVELOPER) {
            return true;
        }
        return false;
    }

    public boolean isInstalled() {
        if (this.status == AppStatus.INSTALLED) {
            return true;
        }
        return false;
    }

    public String toString() {
        return AnonymousClass006.A0C("App[id=", this.id, ", packageName=", this.packageName, "]");
    }

    public App(String str, String str2, AppStatus appStatus, CloudStorageStatus cloudStorageStatus2, String str3, ComfortRating comfortRating2, Map<Image.ImageName, Image> map, long j, long j2, long j3, String str4, long j4, String str5, int i, List<String> list, List<InputDevice> list2, boolean z, String str6, String str7, String str8, String str9, MicrophoneUsage microphoneUsage2, boolean z2, long j5, long j6, long j7, long j8, String str10, SupportedPlatform supportedPlatform, List<AppMediaCapability> list3, List<PlayMode> list4, Category category2, HeadTracking headTracking2, GrantReason grantReason2, long j9, long j10, long j11, String str11, String str12, String str13, boolean z3, boolean z4, boolean z5, String str14, boolean z6, boolean z7, List<String> list5, String str15, String str16) {
        if (!TextUtils.isEmpty(str6)) {
            this.id = str;
            this.packageName = str2;
            this.status = appStatus;
            this.cloudStorageStatus = cloudStorageStatus2;
            this.displayName = str3;
            this.comfortRating = comfortRating2;
            this.images = map;
            this.downloadSizeBytes = j;
            this.downloadedSizeBytes = j2;
            this.versionCode = j3;
            this.versionName = str4;
            this.latestVersionCode = j4;
            this.latestVersionName = str5;
            this.latestTargetSdkVersion = i;
            this.latestPermissions = list;
            this.supportedInputDevices = list2;
            this.installableOnExternalStorage = z;
            this.entitlementHash = str6;
            this.appScopedUserId = str7;
            this.applicationGroupingId = str8;
            this.applicationOrganizationId = str9;
            this.microphoneUsage = microphoneUsage2;
            this.isUnseen = z2;
            this.recentActivityMs = j5;
            this.totalActivityMs = j6;
            this.minRecommendedVersionCode = j7;
            this.minRequiredVersionCode = j8;
            this.isDemoOf = str10;
            this.platform = supportedPlatform;
            this.appMediaCapabilities = list3;
            this.headTracking = headTracking2;
            this.playModes = list4;
            this.category = category2;
            this.grantReason = grantReason2;
            this.grantTimeMs = j9;
            this.grantExpirationMs = j10;
            this.apkFullSizeBytes = j11;
            this.currentLanguagePack = str11;
            this.internetConnection = str12;
            this.trustedBinaryStatus = str13;
            this.isConcept = z3;
            this.isTVApp = z4;
            this.isTest = z5;
            this.systemUiAsOverlayMode = str14;
            this.autoUpdateEnabled = z6;
            this.ducNonCompliant = z7;
            this.accessFeatureKeys = list5;
            this.environmentSourceId = str15;
            this.environmentSourceDisplayName = str16;
            return;
        }
        throw new IllegalArgumentException("null entitlement hash");
    }
}
