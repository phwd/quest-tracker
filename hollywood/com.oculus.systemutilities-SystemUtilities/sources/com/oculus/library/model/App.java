package com.oculus.library.model;

import android.text.TextUtils;
import com.oculus.library.model.Image;
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

        public Editor withIsUnseen(boolean isUnseen2) {
            this.isUnseen = Optional.of(Boolean.valueOf(isUnseen2));
            return this;
        }

        public Editor withRecentActivityMs(long recentActivityMs2) {
            this.recentActivityMs = Optional.of(Long.valueOf(recentActivityMs2));
            return this;
        }
    }
}
