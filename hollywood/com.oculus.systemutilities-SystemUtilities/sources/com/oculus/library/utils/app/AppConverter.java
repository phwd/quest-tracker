package com.oculus.library.utils.app;

import android.content.ContentValues;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.model.AppMediaCapability;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.Category;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.library.model.ComfortRating;
import com.oculus.library.model.GrantReason;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.Image;
import com.oculus.library.model.InputDevice;
import com.oculus.library.model.MicrophoneUsage;
import com.oculus.library.model.PlayMode;
import com.oculus.library.model.SupportedPlatform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppConverter {
    public static final String COL_ACCESS_FEATURE_KEYS = LibraryDBContract.Columns.ACCESS_FEATURE_KEYS.name;
    @Deprecated
    public static final String COL_APK_SIZE = LibraryDBContract.Columns.APK_FULL_SIZE.name;
    private static final String COL_APPLICATION_GROUPING_ID = LibraryDBContract.Columns.APPLICATION_GROUPING_ID.name;
    private static final String COL_APPLICATION_ORGANIZATION_ID = LibraryDBContract.Columns.APPLICATION_ORGANIZATION_ID.name;
    public static final String COL_APP_MEDIA_CAPABILITIES = LibraryDBContract.Columns.APP_MEDIA_CAPABILITIES.name;
    private static final String COL_APP_SCOPED_USER_ID = LibraryDBContract.Columns.APP_SCOPED_USER_ID.name;
    public static final String COL_AUTO_UPDATE_ENABLED = LibraryDBContract.Columns.AUTO_UPDATE_ENABLED.name;
    public static final String COL_CATEGORY = LibraryDBContract.Columns.CATEGORY.name;
    private static final String COL_CLOUD_STORAGE_STATUS = LibraryDBContract.Columns.CLOUD_STORAGE_STATUS.name;
    private static final String COL_COMFORT_RATING = LibraryDBContract.Columns.COMFORT_RATING.name;
    public static final String COL_CURRENT_LANGUAGE_PACK = LibraryDBContract.Columns.CURRENT_LANGUAGE_PACK.name;
    private static final String COL_DOWNLOADED_SIZE = LibraryDBContract.Columns.DOWNLOADED_SIZE.name;
    public static final String COL_DUC_NON_COMPLIANT = LibraryDBContract.Columns.DUC_NON_COMPLIANT.name;
    private static final String COL_ENTITLEMENT_HASH = LibraryDBContract.Columns.ENTITLEMENT_HASH.name;
    public static final String COL_ENVIRONMENT_SOURCE_DISPLAY_NAME = LibraryDBContract.Columns.ENVIRONMENT_SOURCE_DISPLAY_NAME.name;
    public static final String COL_ENVIRONMENT_SOURCE_ID = LibraryDBContract.Columns.ENVIRONMENT_SOURCE_ID.name;
    public static final String COL_GRANT_EXPIRATION = LibraryDBContract.Columns.GRANT_EXPIRATION.name;
    public static final String COL_GRANT_REASON = LibraryDBContract.Columns.GRANT_REASON.name;
    public static final String COL_GRANT_TIME = LibraryDBContract.Columns.GRANT_TIME.name;
    public static final String COL_HEAD_TRACKING = LibraryDBContract.Columns.HEAD_TRACKING.name;
    private static final String COL_HERO_IMAGE = LibraryDBContract.Columns.HERO_IMAGE.name;
    private static final String COL_IMAGE_SOURCE_MAIN = LibraryDBContract.Columns.IMAGE_SOURCE_MAIN.name;
    private static final String COL_IMAGE_SOURCE_SQUARE = LibraryDBContract.Columns.IMAGE_SOURCE_SQUARE.name;
    private static final String COL_IMAGE_SOURCE_TINY = LibraryDBContract.Columns.IMAGE_SOURCE_TINY.name;
    private static final String COL_INSTALLABLE_ON_EXTERNAL_STORAGE = LibraryDBContract.Columns.INSTALLABLE_ON_EXTERNAL_STORAGE.name;
    private static final String COL_INSTALLATION_SIZE = LibraryDBContract.Columns.INSTALLATION_SIZE.name;
    public static final String COL_INTERNET_CONNECTION = LibraryDBContract.Columns.INTERNET_CONNECTION.name;
    public static final String COL_IS_CONCEPT = LibraryDBContract.Columns.IS_CONCEPT.name;
    public static final String COL_IS_DEMO_OF = LibraryDBContract.Columns.IS_DEMO_OF.name;
    public static final String COL_IS_TEST = LibraryDBContract.Columns.IS_TEST.name;
    public static final String COL_IS_TV_APP = LibraryDBContract.Columns.IS_TV_APP.name;
    private static final String COL_ITEM_ID = LibraryDBContract.Columns.ITEM_ID.name;
    private static final String COL_LATEST_SDK_TARGET_VERSION = LibraryDBContract.Columns.LATEST_SDK_TARGET_VERSION.name;
    private static final String COL_LATEST_VERSION_CODE = LibraryDBContract.Columns.LATEST_VERSION_CODE.name;
    private static final String COL_LATEST_VERSION_NAME = LibraryDBContract.Columns.LATEST_VERSION_NAME.name;
    private static final String COL_MICROPHONE_USAGE = LibraryDBContract.Columns.MICROPHONE_USAGE.name;
    private static final String COL_MIN_RECOMMENDED_VERSION_CODE = LibraryDBContract.Columns.MIN_RECOMMENDED_VERSION_CODE.name;
    private static final String COL_MIN_REQUIRED_VERSION_CODE = LibraryDBContract.Columns.MIN_REQUIRED_VERSION_CODE.name;
    private static final String COL_NAME = LibraryDBContract.Columns.NAME.name;
    private static final String COL_PACKAGE_NAME = LibraryDBContract.Columns.PACKAGE_NAME.name;
    public static final String COL_PERMISSIONS = LibraryDBContract.Columns.PERMISSIONS_V2.name;
    public static final String COL_PLAY_MODES = LibraryDBContract.Columns.PLAY_MODES.name;
    private static final String COL_RECENT_ACTIVITY = LibraryDBContract.Columns.RECENT_ACTIVITY.name;
    private static final String COL_SMALL_LANDSCAPE_IMAGE = LibraryDBContract.Columns.SMALL_LANDSCAPE_IMAGE.name;
    private static final String COL_STATUS = LibraryDBContract.Columns.STATUS.name;
    private static final String COL_SUPPORTED_INPUT_DEVICES = LibraryDBContract.Columns.SUPPORTED_INPUT_DEVICES.name;
    public static final String COL_SUPPORTED_PLATFORM = LibraryDBContract.Columns.SUPPORTED_PLATFORM.name;
    public static final String COL_SYSTEM_UI_OVERLAY_MODE = LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name;
    private static final String COL_TOTAL_ACTIVITY = LibraryDBContract.Columns.TOTAL_ACTIVITY.name;
    public static final String COL_TRUSTED_BINARY_STATUS = LibraryDBContract.Columns.TRUSTED_BINARY_STATUS.name;
    private static final String COL_UNSEEN = LibraryDBContract.Columns.UNSEEN.name;
    private static final String COL_USER_ID = LibraryDBContract.Columns.USER_ID.name;
    private static final String[] OPTIONAL_COLUMNS = {COL_IS_DEMO_OF, COL_SUPPORTED_PLATFORM, COL_APP_MEDIA_CAPABILITIES, COL_PLAY_MODES, COL_CATEGORY, COL_HEAD_TRACKING, COL_GRANT_REASON, COL_GRANT_TIME, COL_GRANT_EXPIRATION, COL_CURRENT_LANGUAGE_PACK, COL_INTERNET_CONNECTION, COL_TRUSTED_BINARY_STATUS, COL_IS_CONCEPT, COL_IS_TV_APP, COL_IS_TEST, COL_SYSTEM_UI_OVERLAY_MODE, COL_HERO_IMAGE, COL_AUTO_UPDATE_ENABLED, COL_APPLICATION_ORGANIZATION_ID, COL_DUC_NON_COMPLIANT, COL_ACCESS_FEATURE_KEYS, COL_ENVIRONMENT_SOURCE_ID, COL_ENVIRONMENT_SOURCE_DISPLAY_NAME};
    private static final String[] REQUIRED_COLUMNS = {COL_USER_ID, COL_ITEM_ID, COL_NAME, COL_COMFORT_RATING, COL_APPLICATION_GROUPING_ID, COL_IMAGE_SOURCE_SQUARE, COL_IMAGE_SOURCE_MAIN, COL_IMAGE_SOURCE_TINY, COL_SMALL_LANDSCAPE_IMAGE, COL_PACKAGE_NAME, COL_LATEST_VERSION_CODE, COL_LATEST_VERSION_NAME, COL_LATEST_SDK_TARGET_VERSION, COL_INSTALLATION_SIZE, COL_DOWNLOADED_SIZE, COL_PERMISSIONS, COL_INSTALLABLE_ON_EXTERNAL_STORAGE, COL_STATUS, COL_CLOUD_STORAGE_STATUS, COL_SUPPORTED_INPUT_DEVICES, COL_ENTITLEMENT_HASH, COL_APP_SCOPED_USER_ID, COL_RECENT_ACTIVITY, COL_UNSEEN, COL_MIN_RECOMMENDED_VERSION_CODE, COL_MIN_REQUIRED_VERSION_CODE, COL_MICROPHONE_USAGE, COL_TOTAL_ACTIVITY, COL_APK_SIZE};
    private static final String TAG = AppConverter.class.getSimpleName();
    private final PackageManager mPackageManager;

    public AppConverter(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    public static String[] getOptionalColumns() {
        return OPTIONAL_COLUMNS;
    }

    public void addContentValuePackingVersion(ContentValues contentValues, int version) {
        contentValues.put("ovrlibrary_cv_version", Integer.valueOf(version));
    }

    public App[] extractAllFromCursor(Cursor cursor) {
        if (cursor == null) {
            Log.w(TAG, "Cannot load database due to null cursor");
            return new App[0];
        } else if (!cursor.moveToFirst()) {
            Log.w(TAG, "Unable to move cursor to the first position");
            cursor.close();
            return new App[0];
        } else {
            List<App> apps = new ArrayList<>();
            do {
                App app = extractNextAppFromCursor(cursor);
                if (app != null) {
                    apps.add(app);
                } else {
                    Log.w(TAG, "Got unexpected null from cursor app extraction");
                }
            } while (cursor.moveToNext());
            return (App[]) apps.toArray(new App[0]);
        }
    }

    public App extractNextAppFromCursor(Cursor cursor) {
        boolean z;
        boolean z2;
        if (cursor == null) {
            return null;
        }
        Map<String, Integer> requiredColumns = new HashMap<>();
        String[] strArr = REQUIRED_COLUMNS;
        for (String column : strArr) {
            requiredColumns.put(column, Integer.valueOf(cursor.getColumnIndex(column)));
        }
        String itemId = cursor.getString(requiredColumns.get(COL_ITEM_ID).intValue());
        String name = cursor.getString(requiredColumns.get(COL_NAME).intValue());
        String applicationGroupingId = cursor.getString(requiredColumns.get(COL_APPLICATION_GROUPING_ID).intValue());
        String imageSquare = cursor.getString(requiredColumns.get(COL_IMAGE_SOURCE_SQUARE).intValue());
        String imageMain = cursor.getString(requiredColumns.get(COL_IMAGE_SOURCE_MAIN).intValue());
        String imageTiny = cursor.getString(requiredColumns.get(COL_IMAGE_SOURCE_TINY).intValue());
        String imageSmallLandscape = cursor.getString(requiredColumns.get(COL_SMALL_LANDSCAPE_IMAGE).intValue());
        String packageName = cursor.getString(requiredColumns.get(COL_PACKAGE_NAME).intValue());
        long latestVersionCode = cursor.getLong(requiredColumns.get(COL_LATEST_VERSION_CODE).intValue());
        String latestVersionName = cursor.getString(requiredColumns.get(COL_LATEST_VERSION_NAME).intValue());
        int latestSdkTarget = cursor.getInt(requiredColumns.get(COL_LATEST_SDK_TARGET_VERSION).intValue());
        long installationSize = cursor.getLong(requiredColumns.get(COL_INSTALLATION_SIZE).intValue());
        long downloadedSize = cursor.getLong(requiredColumns.get(COL_DOWNLOADED_SIZE).intValue());
        String permissionsValue = cursor.getString(requiredColumns.get(COL_PERMISSIONS).intValue());
        long sdCardInstallable = cursor.getLong(requiredColumns.get(COL_INSTALLABLE_ON_EXTERNAL_STORAGE).intValue());
        String statusValue = cursor.getString(requiredColumns.get(COL_STATUS).intValue());
        String cloudStorageStatusValue = cursor.getString(requiredColumns.get(COL_CLOUD_STORAGE_STATUS).intValue());
        String supportedInputDevices = cursor.getString(requiredColumns.get(COL_SUPPORTED_INPUT_DEVICES).intValue());
        String entitlementHash = cursor.getString(requiredColumns.get(COL_ENTITLEMENT_HASH).intValue());
        String appScopedUserId = cursor.getString(requiredColumns.get(COL_APP_SCOPED_USER_ID).intValue());
        long recentActivity = cursor.getLong(requiredColumns.get(COL_RECENT_ACTIVITY).intValue());
        String comfortRatingValue = cursor.getString(requiredColumns.get(COL_COMFORT_RATING).intValue());
        long unseen = cursor.getLong(requiredColumns.get(COL_UNSEEN).intValue());
        long minRecommendedVersion = cursor.getLong(requiredColumns.get(COL_MIN_RECOMMENDED_VERSION_CODE).intValue());
        long minRequiredVersion = cursor.getLong(requiredColumns.get(COL_MIN_REQUIRED_VERSION_CODE).intValue());
        String microphoneValue = cursor.getString(requiredColumns.get(COL_MICROPHONE_USAGE).intValue());
        long totalActivity = cursor.getLong(requiredColumns.get(COL_TOTAL_ACTIVITY).intValue());
        long apkSize = cursor.getLong(requiredColumns.get(COL_APK_SIZE).intValue());
        AppStatus appStatus = AppStatus.valueOf(statusValue);
        CloudStorageStatus cloudStorageStatus = CloudStorageStatus.valueOf(cloudStorageStatusValue);
        ComfortRating comfortRating = ComfortRating.valueOf(comfortRatingValue);
        long installedVersionCode = -2147483648L;
        String installedVersionName = null;
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, 0);
            installedVersionCode = (long) packageInfo.versionCode;
            installedVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        List<String> permissions = convertPermissionStringToList(permissionsValue);
        List<InputDevice> supportedDevices = convertSupportedInputDevicesStringToList(supportedInputDevices);
        MicrophoneUsage micUsage = MicrophoneUsage.NONE;
        if (!TextUtils.isEmpty(microphoneValue)) {
            micUsage = MicrophoneUsage.valueOf(microphoneValue);
        } else {
            Log.w(TAG, "Unexpected null for microphone usage in db");
        }
        Map<String, Integer> optionalColumns = new HashMap<>();
        String[] optionalColumns2 = getOptionalColumns();
        for (String column2 : optionalColumns2) {
            optionalColumns.put(column2, Integer.valueOf(cursor.getColumnIndex(column2)));
        }
        int isDemoCol = optionalColumns.get(COL_IS_DEMO_OF).intValue();
        String isDemoOf = null;
        if (isDemoCol != -1) {
            isDemoOf = cursor.getString(isDemoCol);
        }
        int platformCol = optionalColumns.get(COL_SUPPORTED_PLATFORM).intValue();
        SupportedPlatform platform = SupportedPlatform.UNKNOWN;
        if (platformCol != -1) {
            platform = SupportedPlatform.fromString(cursor.getString(platformCol));
        }
        int capabilitiesCol = optionalColumns.get(COL_APP_MEDIA_CAPABILITIES).intValue();
        List<AppMediaCapability> capabilities = new ArrayList<>();
        if (capabilitiesCol != -1) {
            capabilities = convertStringToAppMediaCapabilities(cursor.getString(optionalColumns.get(COL_APP_MEDIA_CAPABILITIES).intValue()));
        }
        int headTrackingCol = optionalColumns.get(COL_HEAD_TRACKING).intValue();
        HeadTracking headTracking = HeadTracking.UNKNOWN;
        if (headTrackingCol != -1) {
            headTracking = HeadTracking.fromString(cursor.getString(headTrackingCol));
        }
        int playModeCol = optionalColumns.get(COL_PLAY_MODES).intValue();
        List<PlayMode> playModes = new ArrayList<>();
        if (playModeCol != -1) {
            playModes = convertStringToPlayModes(cursor.getString(optionalColumns.get(COL_PLAY_MODES).intValue()));
        }
        int categoryCol = optionalColumns.get(COL_CATEGORY).intValue();
        Category category = Category.UNKNOWN;
        if (categoryCol != -1) {
            category = Category.fromString(cursor.getString(categoryCol));
        }
        int grantReasonCol = optionalColumns.get(COL_GRANT_REASON).intValue();
        GrantReason grantReason = GrantReason.UNKNOWN;
        if (grantReasonCol != -1) {
            grantReason = GrantReason.valueOf(cursor.getString(grantReasonCol));
        }
        int grantTimeCol = optionalColumns.get(COL_GRANT_TIME).intValue();
        long grantTime = -1;
        if (grantTimeCol != -1) {
            grantTime = cursor.getLong(grantTimeCol);
        }
        int grantExpirationCol = optionalColumns.get(COL_GRANT_EXPIRATION).intValue();
        long grantExpiration = -1;
        if (grantExpirationCol != -1) {
            grantExpiration = cursor.getLong(grantExpirationCol);
        }
        int currentLanguagePackCol = optionalColumns.get(COL_CURRENT_LANGUAGE_PACK).intValue();
        String currentLanguagePack = null;
        if (currentLanguagePackCol != -1) {
            currentLanguagePack = cursor.getString(currentLanguagePackCol);
        }
        int internetConnectionCol = optionalColumns.get(COL_INTERNET_CONNECTION).intValue();
        String internetConnection = null;
        if (internetConnectionCol != -1) {
            internetConnection = cursor.getString(internetConnectionCol);
        }
        int trustedBinaryStatusCol = optionalColumns.get(COL_TRUSTED_BINARY_STATUS).intValue();
        String trustedBinaryStatus = null;
        if (trustedBinaryStatusCol != -1) {
            trustedBinaryStatus = cursor.getString(trustedBinaryStatusCol);
        }
        int isConceptCol = optionalColumns.get(COL_IS_CONCEPT).intValue();
        boolean isConcept = false;
        if (isConceptCol != -1) {
            isConcept = cursor.getInt(isConceptCol) == 1;
        }
        int isTVAppCol = optionalColumns.get(COL_IS_TV_APP).intValue();
        boolean isTVApp = false;
        if (isTVAppCol != -1) {
            isTVApp = cursor.getInt(isTVAppCol) == 1;
        }
        int isTestCol = optionalColumns.get(COL_IS_TEST).intValue();
        boolean isTest = false;
        if (isTestCol != -1) {
            isTest = cursor.getInt(isTestCol) == 1;
        }
        int systemUiAsOverlayModeCol = optionalColumns.get(COL_SYSTEM_UI_OVERLAY_MODE).intValue();
        String systemUiAsOverlayMode = null;
        if (systemUiAsOverlayModeCol != -1) {
            systemUiAsOverlayMode = cursor.getString(systemUiAsOverlayModeCol);
        }
        int heroImageCol = optionalColumns.get(COL_HERO_IMAGE).intValue();
        String imageHero = null;
        if (heroImageCol != -1) {
            imageHero = cursor.getString(heroImageCol);
        }
        int autoUpdateEnabledCol = optionalColumns.get(COL_AUTO_UPDATE_ENABLED).intValue();
        boolean autoUpdateEnabled = false;
        if (autoUpdateEnabledCol != -1) {
            autoUpdateEnabled = cursor.getInt(autoUpdateEnabledCol) == 1;
        }
        int applicationOrganizationIdCol = optionalColumns.get(COL_APPLICATION_ORGANIZATION_ID).intValue();
        String applicationOrganizationId = null;
        if (applicationOrganizationIdCol != -1) {
            applicationOrganizationId = cursor.getString(applicationOrganizationIdCol);
        }
        int ducNonCompliantCol = optionalColumns.get(COL_DUC_NON_COMPLIANT).intValue();
        boolean ducNonCompliant = true;
        if (ducNonCompliantCol != -1) {
            ducNonCompliant = cursor.getInt(ducNonCompliantCol) == 1;
        }
        int accessFeatureKeysCol = optionalColumns.get(COL_ACCESS_FEATURE_KEYS).intValue();
        List<String> accessFeatureKeys = new ArrayList<>();
        if (accessFeatureKeysCol != -1) {
            accessFeatureKeys = convertStringToAccessFeatureKeys(cursor.getString(accessFeatureKeysCol));
        }
        int environmentSourceIdCol = optionalColumns.get(COL_ENVIRONMENT_SOURCE_ID).intValue();
        String environmentSourceId = null;
        if (environmentSourceIdCol != -1) {
            environmentSourceId = cursor.getString(environmentSourceIdCol);
        }
        int environmentSourceDisplayNameCol = optionalColumns.get(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME).intValue();
        String environmentSourceDisplayName = null;
        if (environmentSourceDisplayNameCol != -1) {
            environmentSourceDisplayName = cursor.getString(environmentSourceDisplayNameCol);
        }
        Map<Image.ImageName, Image> createImages = ImagesBuilder.createImages(imageSquare, imageMain, imageTiny, imageSmallLandscape, imageHero);
        if (sdCardInstallable == 1) {
            z = true;
        } else {
            z = false;
        }
        if (unseen == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        return new App(itemId, packageName, appStatus, cloudStorageStatus, name, comfortRating, createImages, installationSize, downloadedSize, installedVersionCode, installedVersionName, latestVersionCode, latestVersionName, latestSdkTarget, permissions, supportedDevices, z, entitlementHash, appScopedUserId, applicationGroupingId, applicationOrganizationId, micUsage, z2, recentActivity, TimeUnit.SECONDS.toMillis(totalActivity), minRecommendedVersion, minRequiredVersion, isDemoOf, platform, capabilities, playModes, category, headTracking, grantReason, grantTime, grantExpiration, apkSize, currentLanguagePack, internetConnection, trustedBinaryStatus, isConcept, isTVApp, isTest, systemUiAsOverlayMode, autoUpdateEnabled, ducNonCompliant, accessFeatureKeys, environmentSourceId, environmentSourceDisplayName);
    }

    public ContentValues toContentValues(App.Editor appEditor) {
        if (TextUtils.isEmpty(appEditor.packageName)) {
            Log.e(TAG, "Invalid app editor received, returning a null content values pack");
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PACKAGE_NAME, appEditor.packageName);
        if (appEditor.status.isPresent()) {
            contentValues.put(COL_STATUS, appEditor.status.get().name());
        }
        if (appEditor.cloudStorageStatus.isPresent()) {
            contentValues.put(COL_CLOUD_STORAGE_STATUS, appEditor.cloudStorageStatus.get().name());
        }
        if (appEditor.downloadedSizeBytes.isPresent()) {
            contentValues.put(COL_DOWNLOADED_SIZE, appEditor.downloadedSizeBytes.get());
        }
        if (appEditor.downloadSizeBytes.isPresent()) {
            contentValues.put(COL_INSTALLATION_SIZE, appEditor.downloadSizeBytes.get());
        }
        if (appEditor.appScopedUserId.isPresent()) {
            contentValues.put(COL_APP_SCOPED_USER_ID, appEditor.appScopedUserId.get());
        }
        if (appEditor.isUnseen.isPresent()) {
            contentValues.put(COL_UNSEEN, Integer.valueOf(appEditor.isUnseen.get().booleanValue() ? 1 : 0));
        }
        if (appEditor.recentActivityMs.isPresent()) {
            contentValues.put(COL_RECENT_ACTIVITY, appEditor.recentActivityMs.get());
        }
        if (appEditor.totalActivityMs.isPresent()) {
            contentValues.put(COL_TOTAL_ACTIVITY, appEditor.totalActivityMs.get());
        }
        if (appEditor.languagePack.isPresent()) {
            contentValues.put(COL_CURRENT_LANGUAGE_PACK, appEditor.languagePack.get());
        }
        if (!appEditor.trustedBinaryStatus.isPresent()) {
            return contentValues;
        }
        contentValues.put(COL_TRUSTED_BINARY_STATUS, appEditor.trustedBinaryStatus.get());
        return contentValues;
    }

    private static List<String> convertPermissionStringToList(String permissions) {
        return new ArrayList(Arrays.asList(permissions.split(",")));
    }

    private List<InputDevice> convertSupportedInputDevicesStringToList(String supportedInputDevices) {
        String[] inputDeviceStrings;
        List<InputDevice> retSupportedInputDevices = new ArrayList<>();
        if (supportedInputDevices != null) {
            for (String str : supportedInputDevices.split(",")) {
                InputDevice inputDevice = InputDevice.generateFromDeviceString(str);
                if (inputDevice != null) {
                    retSupportedInputDevices.add(inputDevice);
                }
            }
        }
        return retSupportedInputDevices;
    }

    static List<PlayMode> convertStringToPlayModes(String playModeValue) {
        List<PlayMode> playModes = new ArrayList<>();
        if (playModeValue != null) {
            String[] split = playModeValue.split(",");
            for (String split2 : split) {
                if (!TextUtils.isEmpty(split2)) {
                    try {
                        playModes.add(PlayMode.valueOf(split2));
                    } catch (IllegalArgumentException e) {
                        Log.e(TAG, "Unrecognized play mode: " + split2);
                        playModes.add(PlayMode.UNKNOWN);
                    }
                }
            }
        }
        return playModes;
    }

    static List<AppMediaCapability> convertStringToAppMediaCapabilities(String values) {
        Set<AppMediaCapability> capabilities = new HashSet<>();
        if (values == null) {
            return new ArrayList(capabilities);
        }
        String[] split = values.split(";");
        for (String split2 : split) {
            if (!TextUtils.isEmpty(split2)) {
                capabilities.add(AppMediaCapability.fromString(split2));
            }
        }
        return new ArrayList(capabilities);
    }

    static List<String> convertStringToAccessFeatureKeys(String accessFeatureKeys) {
        return new ArrayList(Arrays.asList(accessFeatureKeys.split(";")));
    }
}
