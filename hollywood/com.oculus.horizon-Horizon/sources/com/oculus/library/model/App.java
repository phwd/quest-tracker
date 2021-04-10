package com.oculus.library.model;

import X.AnonymousClass006;
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

        public Editor(String str) {
            this.packageName = str;
        }
    }

    public final String toString() {
        return AnonymousClass006.A09("App[id=", this.id, ", packageName=", this.packageName, "]");
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
