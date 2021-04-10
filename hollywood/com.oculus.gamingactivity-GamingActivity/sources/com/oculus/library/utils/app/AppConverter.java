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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    private static final String CONTENT_VALUE_VERSION = "ovrlibrary_cv_version";
    public static final int CV_VERSION_1 = 1;
    public static final int CV_VERSION_2 = 2;
    private static final int LOWEST_VALID_GRANT_EXPIRATION = -2;
    private static final int LOWEST_VALID_GRANT_TIME = -1;
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

    public static String[] getRequiredColumns() {
        return REQUIRED_COLUMNS;
    }

    public void addContentValuePackingVersion(ContentValues contentValues, int version) {
        contentValues.put(CONTENT_VALUE_VERSION, Integer.valueOf(version));
    }

    public int getAndRemoveContentValuePackingVersion(ContentValues contentValues) {
        if (!contentValues.containsKey(CONTENT_VALUE_VERSION)) {
            return 1;
        }
        int result = contentValues.getAsInteger(CONTENT_VALUE_VERSION).intValue();
        contentValues.remove(CONTENT_VALUE_VERSION);
        return result;
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
        long installedVersionCode = LibraryDBContract.VERSION_NOT_INSTALLED;
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

    public App fromContentValuesUpdate(App app, ContentValues contentValues) {
        boolean z = true;
        if (app == null || !app.packageName.equals(contentValues.getAsString(COL_PACKAGE_NAME))) {
            throw new IllegalArgumentException("invalid args, must be non-null and contain package name");
        }
        App.Builder builder = new App.Builder(app);
        if (contentValues.containsKey(COL_STATUS)) {
            builder.withStatus(AppStatus.valueOf(contentValues.getAsString(COL_STATUS)));
        }
        if (contentValues.containsKey(COL_CLOUD_STORAGE_STATUS)) {
            builder.withCloudStorageStatus(CloudStorageStatus.valueOf(contentValues.getAsString(COL_CLOUD_STORAGE_STATUS)));
        }
        if (contentValues.containsKey(COL_DOWNLOADED_SIZE)) {
            builder.withDownloadedSizeBytes(contentValues.getAsLong(COL_DOWNLOADED_SIZE).longValue());
        }
        if (contentValues.containsKey(COL_INSTALLATION_SIZE)) {
            builder.withDownloadSizeBytes(contentValues.getAsLong(COL_INSTALLATION_SIZE).longValue());
        }
        if (contentValues.containsKey(COL_APP_SCOPED_USER_ID)) {
            builder.withAppScopedUserId(contentValues.getAsString(COL_APP_SCOPED_USER_ID));
        }
        if (contentValues.containsKey(COL_UNSEEN)) {
            if (contentValues.getAsInteger(COL_UNSEEN).intValue() != 1) {
                z = false;
            }
            builder.withIsUnseen(z);
        }
        if (contentValues.containsKey(COL_RECENT_ACTIVITY)) {
            builder.withRecentActivityMs(contentValues.getAsLong(COL_RECENT_ACTIVITY).longValue());
        }
        if (contentValues.containsKey(COL_TOTAL_ACTIVITY)) {
            builder.withTotalActivityMs(contentValues.getAsLong(COL_TOTAL_ACTIVITY).longValue());
        }
        if (contentValues.containsKey(COL_CURRENT_LANGUAGE_PACK)) {
            builder.withCurrentLanguagePack(contentValues.getAsString(COL_CURRENT_LANGUAGE_PACK));
        }
        if (contentValues.containsKey(COL_TRUSTED_BINARY_STATUS)) {
            builder.withTrustedBinaryStatus(contentValues.getAsString(COL_TRUSTED_BINARY_STATUS));
        }
        return builder.build();
    }

    public ContentValues toContentValues(App app) {
        int i = 1;
        String permissions = convertPermissionListToString(app.latestPermissions);
        String inputDevices = convertSupportedInputDevicesListToString(app.supportedInputDevices);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ITEM_ID, app.id);
        contentValues.put(COL_NAME, app.displayName);
        contentValues.put(COL_APPLICATION_GROUPING_ID, app.applicationGroupingId);
        contentValues.put(COL_APPLICATION_ORGANIZATION_ID, app.applicationOrganizationId);
        contentValues.put(COL_IMAGE_SOURCE_SQUARE, getImageUri(app, Image.ImageName.SOURCE_SQUARE));
        contentValues.put(COL_IMAGE_SOURCE_MAIN, getImageUri(app, Image.ImageName.SOURCE_MAIN));
        contentValues.put(COL_IMAGE_SOURCE_TINY, getImageUri(app, Image.ImageName.SOURCE_TINY));
        contentValues.put(COL_SMALL_LANDSCAPE_IMAGE, getImageUri(app, Image.ImageName.LANDSCAPE_SMALL));
        contentValues.put(COL_HERO_IMAGE, getImageUri(app, Image.ImageName.HERO));
        contentValues.put(COL_PACKAGE_NAME, app.packageName);
        contentValues.put(COL_LATEST_VERSION_CODE, Long.valueOf(app.latestVersionCode));
        contentValues.put(COL_LATEST_VERSION_NAME, app.latestVersionName);
        contentValues.put(COL_LATEST_SDK_TARGET_VERSION, Integer.valueOf(app.latestTargetSdkVersion));
        contentValues.put(COL_INSTALLATION_SIZE, Long.valueOf(app.downloadSizeBytes));
        contentValues.put(COL_DOWNLOADED_SIZE, Long.valueOf(app.downloadedSizeBytes));
        contentValues.put(COL_PERMISSIONS, permissions);
        contentValues.put(COL_INSTALLABLE_ON_EXTERNAL_STORAGE, Integer.valueOf(app.installableOnExternalStorage ? 1 : 0));
        contentValues.put(COL_STATUS, app.status.name());
        contentValues.put(COL_CLOUD_STORAGE_STATUS, app.cloudStorageStatus.name());
        contentValues.put(COL_SUPPORTED_INPUT_DEVICES, inputDevices);
        contentValues.put(COL_ENTITLEMENT_HASH, app.entitlementHash);
        contentValues.put(COL_APP_SCOPED_USER_ID, app.appScopedUserId);
        contentValues.put(COL_RECENT_ACTIVITY, Long.valueOf(app.recentActivityMs));
        contentValues.put(COL_COMFORT_RATING, app.comfortRating.name());
        String str = COL_UNSEEN;
        if (!app.isUnseen) {
            i = 0;
        }
        contentValues.put(str, Integer.valueOf(i));
        contentValues.put(COL_MIN_RECOMMENDED_VERSION_CODE, Long.valueOf(app.minRecommendedVersionCode));
        contentValues.put(COL_MIN_REQUIRED_VERSION_CODE, Long.valueOf(app.minRequiredVersionCode));
        contentValues.put(COL_MICROPHONE_USAGE, app.microphoneUsage.name());
        contentValues.put(COL_TOTAL_ACTIVITY, Long.valueOf(app.totalActivityMs));
        contentValues.put(COL_IS_DEMO_OF, app.isDemoOf);
        contentValues.put(COL_SUPPORTED_PLATFORM, app.platform.name());
        contentValues.put(COL_APP_MEDIA_CAPABILITIES, convertAppMediaCapabilitiesToString(app.appMediaCapabilities));
        contentValues.put(COL_PLAY_MODES, convertPlayModesToString(app.playModes));
        contentValues.put(COL_CATEGORY, app.category.name());
        contentValues.put(COL_HEAD_TRACKING, app.headTracking.name());
        contentValues.put(COL_GRANT_REASON, app.grantReason.name());
        contentValues.put(COL_GRANT_TIME, Long.valueOf(app.grantTimeMs));
        contentValues.put(COL_GRANT_EXPIRATION, Long.valueOf(app.grantExpirationMs));
        contentValues.put(COL_APK_SIZE, Long.valueOf(app.apkFullSizeBytes));
        contentValues.put(COL_CURRENT_LANGUAGE_PACK, app.currentLanguagePack);
        contentValues.put(COL_INTERNET_CONNECTION, app.internetConnection);
        contentValues.put(COL_TRUSTED_BINARY_STATUS, app.trustedBinaryStatus);
        contentValues.put(COL_IS_CONCEPT, Boolean.valueOf(app.isConcept));
        contentValues.put(COL_IS_TV_APP, Boolean.valueOf(app.isTVApp));
        contentValues.put(COL_IS_TEST, Boolean.valueOf(app.isTest));
        contentValues.put(COL_SYSTEM_UI_OVERLAY_MODE, app.systemUiAsOverlayMode);
        contentValues.put(COL_AUTO_UPDATE_ENABLED, Boolean.valueOf(app.autoUpdateEnabled));
        contentValues.put(COL_DUC_NON_COMPLIANT, Boolean.valueOf(app.ducNonCompliant));
        contentValues.put(COL_ACCESS_FEATURE_KEYS, convertAccessFeatureKeysToString(app.accessFeatureKeys));
        contentValues.put(COL_ENVIRONMENT_SOURCE_ID, app.environmentSourceId);
        contentValues.put(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME, app.environmentSourceDisplayName);
        return contentValues;
    }

    public ContentValueUnpack fromContentValues(ContentValues contentValues) {
        long versionCode;
        String versionName;
        SupportedPlatform platform;
        List<AppMediaCapability> capabilities;
        HeadTracking headTracking;
        List<PlayMode> playModes;
        Category category;
        boolean z;
        boolean z2;
        ArrayList<String> requiredColumns = new ArrayList<>(Arrays.asList(REQUIRED_COLUMNS));
        requiredColumns.remove(COL_USER_ID);
        Iterator<String> it = requiredColumns.iterator();
        while (it.hasNext()) {
            String column = it.next();
            if (!contentValues.containsKey(column)) {
                Log.e(TAG, "Unable to build app from content values. Missing required cv for " + column);
                return null;
            }
        }
        String itemId = contentValues.getAsString(COL_ITEM_ID);
        String displayName = contentValues.getAsString(COL_NAME);
        String applicationGroupingId = contentValues.getAsString(COL_APPLICATION_GROUPING_ID);
        String imageSquareUri = contentValues.getAsString(COL_IMAGE_SOURCE_SQUARE);
        String imageMainUri = contentValues.getAsString(COL_IMAGE_SOURCE_MAIN);
        String imageTinyUri = contentValues.getAsString(COL_IMAGE_SOURCE_TINY);
        String imageLandscapeUri = contentValues.getAsString(COL_SMALL_LANDSCAPE_IMAGE);
        String packageName = contentValues.getAsString(COL_PACKAGE_NAME);
        long latestVersionCode = contentValues.getAsLong(COL_LATEST_VERSION_CODE).longValue();
        String latestVersionName = contentValues.getAsString(COL_LATEST_VERSION_NAME);
        int latestSdkTargetVersion = contentValues.getAsInteger(COL_LATEST_SDK_TARGET_VERSION).intValue();
        long installationSize = contentValues.getAsLong(COL_INSTALLATION_SIZE).longValue();
        long downloadedSize = contentValues.getAsLong(COL_DOWNLOADED_SIZE).longValue();
        String permissions = contentValues.getAsString(COL_PERMISSIONS);
        int installableOnExternalStorage = contentValues.getAsInteger(COL_INSTALLABLE_ON_EXTERNAL_STORAGE).intValue();
        String status = contentValues.getAsString(COL_STATUS);
        String cloudStorageStatus = contentValues.getAsString(COL_CLOUD_STORAGE_STATUS);
        String supportedInputDevices = contentValues.getAsString(COL_SUPPORTED_INPUT_DEVICES);
        String entitlementHash = contentValues.getAsString(COL_ENTITLEMENT_HASH);
        String appScopedUserId = contentValues.getAsString(COL_APP_SCOPED_USER_ID);
        long recentActivity = contentValues.getAsLong(COL_RECENT_ACTIVITY).longValue();
        String comfortRating = contentValues.getAsString(COL_COMFORT_RATING);
        int isUnseen = contentValues.getAsInteger(COL_UNSEEN).intValue();
        long minRecommendedVersionCode = contentValues.getAsLong(COL_MIN_RECOMMENDED_VERSION_CODE).longValue();
        long minRequiredVersionCode = contentValues.getAsLong(COL_MIN_REQUIRED_VERSION_CODE).longValue();
        String microphoneUsage = contentValues.getAsString(COL_MICROPHONE_USAGE);
        long totalActivity = contentValues.getAsLong(COL_TOTAL_ACTIVITY).longValue();
        long apkSize = contentValues.getAsLong(COL_APK_SIZE).longValue();
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, 0);
            versionCode = (long) packageInfo.versionCode;
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = LibraryDBContract.VERSION_NOT_INSTALLED;
            versionName = null;
        }
        List<InputDevice> supportedInputDevicesList = convertSupportedInputDevicesStringToList(supportedInputDevices);
        Set<String> missingFields = new HashSet<>();
        String isDemoOf = contentValues.getAsString(COL_IS_DEMO_OF);
        if (TextUtils.isEmpty(isDemoOf)) {
            missingFields.add(COL_IS_DEMO_OF);
        }
        String platformString = contentValues.getAsString(COL_SUPPORTED_PLATFORM);
        if (TextUtils.isEmpty(platformString)) {
            missingFields.add(COL_SUPPORTED_PLATFORM);
            platform = SupportedPlatform.UNKNOWN;
        } else {
            platform = SupportedPlatform.fromString(platformString);
        }
        String capabilitiesValue = contentValues.getAsString(COL_APP_MEDIA_CAPABILITIES);
        if (TextUtils.isEmpty(capabilitiesValue)) {
            missingFields.add(COL_APP_MEDIA_CAPABILITIES);
            capabilities = Collections.singletonList(AppMediaCapability.UNKNOWN);
        } else {
            capabilities = convertStringToAppMediaCapabilities(capabilitiesValue);
        }
        String headTrackingString = contentValues.getAsString(COL_HEAD_TRACKING);
        if (TextUtils.isEmpty(headTrackingString)) {
            missingFields.add(COL_HEAD_TRACKING);
            headTracking = HeadTracking.UNKNOWN;
        } else {
            headTracking = HeadTracking.fromString(headTrackingString);
        }
        String playModeValue = contentValues.getAsString(COL_PLAY_MODES);
        if (TextUtils.isEmpty(playModeValue)) {
            missingFields.add(COL_PLAY_MODES);
            playModes = Collections.singletonList(PlayMode.UNKNOWN);
        } else {
            playModes = convertStringToPlayModes(playModeValue);
        }
        String categoryString = contentValues.getAsString(COL_CATEGORY);
        if (TextUtils.isEmpty(categoryString)) {
            missingFields.add(COL_CATEGORY);
            category = Category.UNKNOWN;
        } else {
            category = Category.fromString(categoryString);
        }
        String grantReasonValue = contentValues.getAsString(COL_GRANT_REASON);
        GrantReason grantReason = GrantReason.UNKNOWN;
        if (TextUtils.isEmpty(grantReasonValue)) {
            missingFields.add(COL_GRANT_REASON);
        } else {
            grantReason = GrantReason.valueOf(grantReasonValue);
        }
        Long grantTimeValue = contentValues.getAsLong(COL_GRANT_TIME);
        long grantTime = 0;
        if (grantTimeValue == null) {
            missingFields.add(COL_GRANT_TIME);
        } else {
            grantTime = grantTimeValue.longValue();
        }
        Long grantExpirationValue = contentValues.getAsLong(COL_GRANT_EXPIRATION);
        long grantExpiration = 0;
        if (grantTimeValue == null) {
            missingFields.add(COL_GRANT_EXPIRATION);
        } else {
            grantExpiration = grantExpirationValue.longValue();
        }
        String currentLanguagePack = null;
        if (!contentValues.containsKey(COL_CURRENT_LANGUAGE_PACK)) {
            missingFields.add(COL_CURRENT_LANGUAGE_PACK);
        } else {
            currentLanguagePack = contentValues.getAsString(COL_CURRENT_LANGUAGE_PACK);
        }
        List<String> permissionsList = convertPermissionStringToList(permissions);
        String internetConnection = null;
        if (!contentValues.containsKey(COL_INTERNET_CONNECTION)) {
            missingFields.add(COL_INTERNET_CONNECTION);
        } else {
            internetConnection = contentValues.getAsString(COL_INTERNET_CONNECTION);
        }
        String trustedBinaryStatus = null;
        if (!contentValues.containsKey(COL_TRUSTED_BINARY_STATUS)) {
            missingFields.add(COL_TRUSTED_BINARY_STATUS);
        } else {
            trustedBinaryStatus = contentValues.getAsString(COL_TRUSTED_BINARY_STATUS);
        }
        boolean isConcept = false;
        if (!contentValues.containsKey(COL_IS_CONCEPT)) {
            missingFields.add(COL_IS_CONCEPT);
        } else {
            isConcept = contentValues.getAsBoolean(COL_IS_CONCEPT).booleanValue();
        }
        boolean isTVApp = false;
        if (!contentValues.containsKey(COL_IS_TV_APP)) {
            missingFields.add(COL_IS_TV_APP);
        } else {
            isTVApp = contentValues.getAsBoolean(COL_IS_TV_APP).booleanValue();
        }
        boolean isTest = false;
        if (!contentValues.containsKey(COL_IS_TEST)) {
            missingFields.add(COL_IS_TEST);
        } else {
            isTest = contentValues.getAsBoolean(COL_IS_TEST).booleanValue();
        }
        String systemUiAsOverlayMode = null;
        if (!contentValues.containsKey(COL_SYSTEM_UI_OVERLAY_MODE)) {
            missingFields.add(COL_SYSTEM_UI_OVERLAY_MODE);
        } else {
            systemUiAsOverlayMode = contentValues.getAsString(COL_SYSTEM_UI_OVERLAY_MODE);
        }
        String imageHeroUri = null;
        if (!contentValues.containsKey(COL_HERO_IMAGE)) {
            missingFields.add(COL_HERO_IMAGE);
        } else {
            imageHeroUri = contentValues.getAsString(COL_HERO_IMAGE);
        }
        boolean autoUpdateEnabled = false;
        if (!contentValues.containsKey(COL_AUTO_UPDATE_ENABLED)) {
            missingFields.add(COL_AUTO_UPDATE_ENABLED);
        } else {
            autoUpdateEnabled = contentValues.getAsBoolean(COL_AUTO_UPDATE_ENABLED).booleanValue();
        }
        String applicationOrganizationId = null;
        if (!contentValues.containsKey(COL_APPLICATION_ORGANIZATION_ID)) {
            missingFields.add(COL_APPLICATION_ORGANIZATION_ID);
        } else {
            applicationOrganizationId = contentValues.getAsString(COL_APPLICATION_ORGANIZATION_ID);
        }
        boolean ducNonCompliant = true;
        if (!contentValues.containsKey(COL_DUC_NON_COMPLIANT)) {
            missingFields.add(COL_DUC_NON_COMPLIANT);
        } else {
            ducNonCompliant = contentValues.getAsBoolean(COL_DUC_NON_COMPLIANT).booleanValue();
        }
        List<String> accessFeatureKeys = new ArrayList<>();
        if (!contentValues.containsKey(COL_ACCESS_FEATURE_KEYS)) {
            missingFields.add(COL_ACCESS_FEATURE_KEYS);
        } else {
            accessFeatureKeys = convertStringToAccessFeatureKeys(contentValues.getAsString(COL_ACCESS_FEATURE_KEYS));
        }
        String environmentSourceId = null;
        if (!contentValues.containsKey(COL_ENVIRONMENT_SOURCE_ID)) {
            missingFields.add(COL_ENVIRONMENT_SOURCE_ID);
        } else {
            environmentSourceId = contentValues.getAsString(COL_ENVIRONMENT_SOURCE_ID);
        }
        String environmentSourceDisplayName = null;
        if (!contentValues.containsKey(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME)) {
            missingFields.add(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME);
        } else {
            environmentSourceDisplayName = contentValues.getAsString(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME);
        }
        AppStatus valueOf = AppStatus.valueOf(status);
        CloudStorageStatus valueOf2 = CloudStorageStatus.valueOf(cloudStorageStatus);
        ComfortRating fromString = ComfortRating.fromString(comfortRating);
        Map<Image.ImageName, Image> createImages = ImagesBuilder.createImages(imageSquareUri, imageMainUri, imageTinyUri, imageLandscapeUri, imageHeroUri);
        if (installableOnExternalStorage == 1) {
            z = true;
        } else {
            z = false;
        }
        MicrophoneUsage valueOf3 = MicrophoneUsage.valueOf(microphoneUsage);
        if (isUnseen == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        return new ContentValueUnpack(new App(itemId, packageName, valueOf, valueOf2, displayName, fromString, createImages, installationSize, downloadedSize, versionCode, versionName, latestVersionCode, latestVersionName, latestSdkTargetVersion, permissionsList, supportedInputDevicesList, z, entitlementHash, appScopedUserId, applicationGroupingId, applicationOrganizationId, valueOf3, z2, recentActivity, totalActivity, minRecommendedVersionCode, minRequiredVersionCode, isDemoOf, platform, capabilities, playModes, category, headTracking, grantReason, grantTime, grantExpiration, apkSize, currentLanguagePack, internetConnection, trustedBinaryStatus, isConcept, isTVApp, isTest, systemUiAsOverlayMode, autoUpdateEnabled, ducNonCompliant, accessFeatureKeys, environmentSourceId, environmentSourceDisplayName), missingFields);
    }

    private String getImageUri(App model, Image.ImageName imageName) {
        Image image = model.images.get(imageName);
        if (image == null) {
            return null;
        }
        return image.uri;
    }

    public AppStatus getDefaultStatus(App app) {
        if (app.packageName.equals(app.id)) {
            return AppStatus.INCOMPATIBLE;
        }
        try {
            this.mPackageManager.getPackageInfo(app.packageName, 0);
            return AppStatus.INSTALLED;
        } catch (PackageManager.NameNotFoundException e) {
            return AppStatus.ENTITLED;
        }
    }

    static String convertPermissionListToString(List<String> permissions) {
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < permissions.size(); index++) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(permissions.get(index));
        }
        return sb.toString();
    }

    private static List<String> convertPermissionStringToList(String permissions) {
        return new ArrayList(Arrays.asList(permissions.split(",")));
    }

    static String convertSupportedInputDevicesListToString(List<InputDevice> supportedInputDevices) {
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < supportedInputDevices.size(); index++) {
            if (index > 0) {
                sb.append(",");
            }
            sb.append(supportedInputDevices.get(index).generateDeviceString());
        }
        return sb.toString();
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

    static String convertPlayModesToString(List<PlayMode> playModes) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<PlayMode> i = playModes.iterator();
        while (i.hasNext()) {
            stringBuilder.append(i.next().name());
            if (i.hasNext()) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    static String convertAppMediaCapabilitiesToString(List<AppMediaCapability> capabilities) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<AppMediaCapability> i = capabilities.iterator();
        while (i.hasNext()) {
            stringBuilder.append(i.next().name());
            if (i.hasNext()) {
                stringBuilder.append(";");
            }
        }
        return stringBuilder.toString();
    }

    static String convertAccessFeatureKeysToString(List<String> accessFeatureKeys) {
        StringBuffer sb = new StringBuffer();
        for (int index = 0; index < accessFeatureKeys.size(); index++) {
            if (index > 0) {
                sb.append(";");
            }
            sb.append(accessFeatureKeys.get(index));
        }
        return sb.toString();
    }

    static List<String> convertStringToAccessFeatureKeys(String accessFeatureKeys) {
        return new ArrayList(Arrays.asList(accessFeatureKeys.split(";")));
    }

    public static List<String> validate(App app, boolean isEnterpriseModeEnabled) {
        List<String> errors = new ArrayList<>();
        if (TextUtils.isEmpty(app.id)) {
            errors.add("Invalid id: " + app.id);
        }
        if (TextUtils.isEmpty(app.packageName)) {
            errors.add("packageName empty/null");
        }
        if (app.status == null) {
            errors.add("status null");
        }
        if (app.cloudStorageStatus == null) {
            errors.add("cloud storage status null");
        }
        if (TextUtils.isEmpty(app.displayName)) {
            errors.add("display name empty/null");
        }
        if (app.comfortRating == null) {
            errors.add("comfort rating null");
        }
        if (app.images == null) {
            errors.add("images null");
        }
        if (app.downloadSizeBytes < 0) {
            errors.add("download size: " + app.downloadedSizeBytes);
        }
        if (app.downloadedSizeBytes < 0) {
            errors.add("downloaded size: " + app.downloadedSizeBytes);
        }
        if (app.latestTargetSdkVersion < 0) {
            errors.add("latest target sdk: " + app.latestTargetSdkVersion);
        }
        if (app.latestPermissions == null) {
            errors.add("latest permissions null");
        }
        if (app.supportedInputDevices == null) {
            errors.add("supported input devices null");
        }
        if (TextUtils.isEmpty(app.entitlementHash)) {
            errors.add("entitlement hash empty/null");
        }
        if (!isEnterpriseModeEnabled && TextUtils.isEmpty(app.appScopedUserId)) {
            errors.add("app scoped id is empty/null");
        }
        if (TextUtils.isEmpty(app.applicationGroupingId)) {
            errors.add("application grouping empty/null");
        }
        if (app.microphoneUsage == null) {
            errors.add("microphone usage null");
        }
        if (app.recentActivityMs < 0) {
            errors.add("recent activity: " + app.recentActivityMs);
        }
        if (app.totalActivityMs < 0) {
            errors.add("total activity: " + app.totalActivityMs);
        }
        if (app.appMediaCapabilities == null) {
            errors.add("app media capabilities null");
        }
        if (app.playModes == null) {
            errors.add("play modes null");
        }
        if (app.category == null) {
            errors.add("category null");
        }
        if (app.headTracking == null) {
            errors.add("headtracking null");
        }
        if (app.grantReason == null) {
            errors.add("grant reason null");
        }
        if (app.grantTimeMs < -1) {
            errors.add("grant time: " + app.grantTimeMs);
        }
        if (app.grantExpirationMs < -2) {
            errors.add("grant expiration: " + app.grantExpirationMs);
        }
        if (TextUtils.isEmpty(app.systemUiAsOverlayMode)) {
            errors.add("system ui as overlay mode empty/null");
        }
        if (app.accessFeatureKeys == null) {
            errors.add("access feature keys null");
        }
        return errors;
    }

    public static boolean isValid(App app, boolean isEnterpriseModeEnabled) {
        if (TextUtils.isEmpty(app.id) || TextUtils.isEmpty(app.packageName) || app.status == null || app.cloudStorageStatus == null || TextUtils.isEmpty(app.displayName) || app.comfortRating == null || app.images == null || app.downloadSizeBytes < 0 || app.downloadedSizeBytes < 0 || app.latestTargetSdkVersion < 0 || app.latestPermissions == null || app.supportedInputDevices == null || TextUtils.isEmpty(app.entitlementHash) || ((!isEnterpriseModeEnabled && TextUtils.isEmpty(app.appScopedUserId)) || TextUtils.isEmpty(app.applicationGroupingId) || app.microphoneUsage == null || app.recentActivityMs < 0 || app.totalActivityMs < 0 || app.appMediaCapabilities == null || app.playModes == null || app.category == null || app.headTracking == null || app.grantReason == null || app.grantTimeMs < -1 || app.grantExpirationMs < -2 || TextUtils.isEmpty(app.systemUiAsOverlayMode) || app.accessFeatureKeys == null)) {
            return false;
        }
        return true;
    }

    public boolean isNotificationWorthy(App updated, App old) {
        return !isEqual(old, new App.Builder(updated).withEntitlementHash(old.entitlementHash).build());
    }

    public boolean isEqual(App left, App right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        try {
            if (!(Objects.equals(left.id, right.id) && Objects.equals(left.packageName, right.packageName) && left.status == right.status && left.cloudStorageStatus == right.cloudStorageStatus && Objects.equals(left.displayName, right.displayName) && left.comfortRating == right.comfortRating && left.downloadSizeBytes == right.downloadSizeBytes && left.downloadedSizeBytes == right.downloadedSizeBytes && left.versionCode == right.versionCode && Objects.equals(left.versionName, right.versionName) && left.latestVersionCode == right.latestVersionCode && Objects.equals(left.latestVersionName, right.latestVersionName) && left.latestTargetSdkVersion == right.latestTargetSdkVersion && left.installableOnExternalStorage == right.installableOnExternalStorage && Objects.equals(left.entitlementHash, right.entitlementHash) && Objects.equals(left.appScopedUserId, right.appScopedUserId) && Objects.equals(left.applicationGroupingId, right.applicationGroupingId) && Objects.equals(left.applicationOrganizationId, right.applicationOrganizationId) && left.microphoneUsage == right.microphoneUsage && left.minRecommendedVersionCode == right.minRecommendedVersionCode && left.minRequiredVersionCode == right.minRequiredVersionCode && left.isUnseen == right.isUnseen && left.recentActivityMs == right.recentActivityMs && left.totalActivityMs == right.totalActivityMs && Objects.equals(left.isDemoOf, right.isDemoOf) && left.platform == right.platform && left.category == right.category && left.headTracking == right.headTracking && left.grantReason == right.grantReason && left.grantTimeMs == right.grantTimeMs && left.grantExpirationMs == right.grantExpirationMs && left.apkFullSizeBytes == right.apkFullSizeBytes && Objects.equals(left.currentLanguagePack, right.currentLanguagePack) && Objects.equals(left.internetConnection, right.internetConnection) && Objects.equals(left.trustedBinaryStatus, right.trustedBinaryStatus) && left.isConcept == right.isConcept && left.isTVApp == right.isTVApp && left.isTest == right.isTest && Objects.equals(left.systemUiAsOverlayMode, right.systemUiAsOverlayMode) && left.autoUpdateEnabled == right.autoUpdateEnabled && left.ducNonCompliant == right.ducNonCompliant && Objects.equals(left.environmentSourceId, right.environmentSourceId) && Objects.equals(left.environmentSourceDisplayName, right.environmentSourceDisplayName))) {
                return false;
            }
            Set<AppMediaCapability> leftCapabilities = new HashSet<>(left.appMediaCapabilities);
            Set<AppMediaCapability> rightCapabilities = new HashSet<>(right.appMediaCapabilities);
            if (leftCapabilities.size() != rightCapabilities.size() || !leftCapabilities.containsAll(rightCapabilities)) {
                return false;
            }
            Set<PlayMode> leftPlayModes = new HashSet<>(left.playModes);
            Set<PlayMode> rightPlayModes = new HashSet<>(right.playModes);
            if (leftPlayModes.size() != rightPlayModes.size() || !leftPlayModes.containsAll(rightPlayModes)) {
                return false;
            }
            Image.ImageName[] values = Image.ImageName.values();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                Image.ImageName imageName = values[i];
                String leftUri = getImageUri(left, imageName);
                String rightUri = getImageUri(right, imageName);
                if (leftUri == null || rightUri == null) {
                    if (!(leftUri == null && rightUri == null)) {
                        return false;
                    }
                } else if (!leftUri.equals(rightUri)) {
                    return false;
                }
            }
            if (!convertPermissionListToString(left.latestPermissions).equals(convertPermissionListToString(right.latestPermissions))) {
                return false;
            }
            if (!convertSupportedInputDevicesListToString(left.supportedInputDevices).equals(convertSupportedInputDevicesListToString(right.supportedInputDevices))) {
                return false;
            }
            Set<String> leftAccessFeatureKeys = new HashSet<>(left.accessFeatureKeys);
            Set<String> rightAccessFeatureKeys = new HashSet<>(right.accessFeatureKeys);
            if (leftAccessFeatureKeys.size() != rightAccessFeatureKeys.size() || !leftAccessFeatureKeys.containsAll(rightAccessFeatureKeys)) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
