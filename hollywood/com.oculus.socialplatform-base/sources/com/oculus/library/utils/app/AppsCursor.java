package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.facebook.acra.CrashTimeDataCollector;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import java.util.Collection;

public class AppsCursor extends MatrixCursor {
    public static final String[] COLUMNS;

    static {
        String[] strArr = new String[59];
        System.arraycopy(new String[]{LibraryDBContract.Columns.ITEM_ID.name, LibraryDBContract.Columns.NAME.name, LibraryDBContract.Columns.APPLICATION_GROUPING_ID.name, LibraryDBContract.Columns.APPLICATION_ORGANIZATION_ID.name, LibraryDBContract.Columns.IMAGE_SOURCE_SQUARE.name, LibraryDBContract.Columns.IMAGE_SOURCE_MAIN.name, LibraryDBContract.Columns.IMAGE_SOURCE_TINY.name, LibraryDBContract.Columns.SMALL_LANDSCAPE_IMAGE.name, LibraryDBContract.Columns.HERO_IMAGE.name, LibraryDBContract.Columns.PACKAGE_NAME.name, LibraryDBContract.Columns.LATEST_VERSION_CODE.name, LibraryDBContract.Columns.LATEST_VERSION_NAME.name, LibraryDBContract.Columns.LATEST_SDK_TARGET_VERSION.name, LibraryDBContract.Columns.INSTALLATION_SIZE.name, LibraryDBContract.Columns.DOWNLOADED_SIZE.name, LibraryDBContract.Columns.PERMISSIONS.name, LibraryDBContract.Columns.INSTALLABLE_ON_EXTERNAL_STORAGE.name, LibraryDBContract.Columns.USER_QUALITY_RATING.name, LibraryDBContract.Columns.STATUS.name, LibraryDBContract.Columns.CLOUD_STORAGE_STATUS.name, LibraryDBContract.Columns.SUPPORTED_INPUT_DEVICES.name, LibraryDBContract.Columns.ENTITLEMENT_HASH.name, LibraryDBContract.Columns.APP_SCOPED_USER_ID.name, LibraryDBContract.Columns.RECENT_ACTIVITY.name, LibraryDBContract.Columns.COMFORT_RATING.name, LibraryDBContract.Columns.FAVORITE.name, LibraryDBContract.Columns.UNSEEN.name}, 0, strArr, 0, 27);
        System.arraycopy(new String[]{LibraryDBContract.Columns.LIVESTREAMING_STATUS.name, LibraryDBContract.Columns.LIVESTREAMING_STATUS_AUDIO_HOOKING_ENABLED.name, LibraryDBContract.Columns.CHROMECAST_AUDIO_ENABLED.name, LibraryDBContract.Columns.MIN_RECOMMENDED_VERSION_CODE.name, LibraryDBContract.Columns.MIN_REQUIRED_VERSION_CODE.name, LibraryDBContract.Columns.MICROPHONE_USAGE.name, LibraryDBContract.Columns.TOTAL_ACTIVITY.name, LibraryDBContract.Columns.PATCH_DOWNLOAD_SIZE.name, LibraryDBContract.Columns.APK_FULL_SIZE.name, LibraryDBContract.Columns.OBB_FULL_SIZE.name, LibraryDBContract.Columns.IS_DEMO_OF.name, LibraryDBContract.Columns.SUPPORTED_PLATFORM.name, LibraryDBContract.Columns.APP_MEDIA_CAPABILITIES.name, LibraryDBContract.Columns.PLAY_MODES.name, LibraryDBContract.Columns.CATEGORY.name, LibraryDBContract.Columns.HEAD_TRACKING.name, LibraryDBContract.Columns.GRANT_REASON.name, LibraryDBContract.Columns.GRANT_TIME.name, LibraryDBContract.Columns.GRANT_EXPIRATION.name, LibraryDBContract.Columns.CURRENT_LANGUAGE_PACK.name, LibraryDBContract.Columns.PERMISSIONS_V2.name, LibraryDBContract.Columns.INTERNET_CONNECTION.name, LibraryDBContract.Columns.TRUSTED_BINARY_STATUS.name, LibraryDBContract.Columns.IS_CONCEPT.name, LibraryDBContract.Columns.IS_TV_APP.name, LibraryDBContract.Columns.IS_TEST.name, LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name}, 0, strArr, 27, 27);
        System.arraycopy(new String[]{LibraryDBContract.Columns.AUTO_UPDATE_ENABLED.name, LibraryDBContract.Columns.DUC_NON_COMPLIANT.name, LibraryDBContract.Columns.ACCESS_FEATURE_KEYS.name, LibraryDBContract.Columns.ENVIRONMENT_SOURCE_ID.name, LibraryDBContract.Columns.ENVIRONMENT_SOURCE_DISPLAY_NAME.name}, 0, strArr, 54, 5);
        COLUMNS = strArr;
    }

    public AppsCursor(int i) {
        super(COLUMNS, i);
    }

    public void fill(Collection<App> collection) {
        for (App app : collection) {
            Object[] objArr = new Object[59];
            System.arraycopy(new Object[]{app.id, app.displayName, app.applicationGroupingId, app.applicationOrganizationId, app.images.get(Image.ImageName.SOURCE_SQUARE).uri, app.images.get(Image.ImageName.SOURCE_MAIN).uri, app.images.get(Image.ImageName.SOURCE_TINY).uri, app.images.get(Image.ImageName.LANDSCAPE_SMALL).uri, app.images.get(Image.ImageName.HERO).uri, app.packageName, Long.valueOf(app.latestVersionCode), app.latestVersionName, Integer.valueOf(app.latestTargetSdkVersion), Long.valueOf(app.downloadSizeBytes), Long.valueOf(app.downloadedSizeBytes), "", Integer.valueOf(app.installableOnExternalStorage ? 1 : 0), 0, app.status, app.cloudStorageStatus, AppConverter.convertSupportedInputDevicesListToString(app.supportedInputDevices), app.entitlementHash, app.appScopedUserId, Long.valueOf(app.recentActivityMs), app.comfortRating.name(), 0, Integer.valueOf(app.isUnseen ? 1 : 0)}, 0, objArr, 0, 27);
            System.arraycopy(new Object[]{CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN, 0, 0, Long.valueOf(app.minRecommendedVersionCode), Long.valueOf(app.minRequiredVersionCode), app.microphoneUsage.name(), Long.valueOf(app.totalActivityMs), 0, Long.valueOf(app.apkFullSizeBytes), 0, app.isDemoOf, app.platform.name(), AppConverter.convertAppMediaCapabilitiesToString(app.appMediaCapabilities), AppConverter.convertPlayModesToString(app.playModes), app.category.name(), app.headTracking.name(), app.grantReason.name(), Long.valueOf(app.grantTimeMs), Long.valueOf(app.grantExpirationMs), app.currentLanguagePack, AppConverter.convertPermissionListToString(app.latestPermissions), app.internetConnection, app.trustedBinaryStatus, Integer.valueOf(app.isConcept ? 1 : 0), Integer.valueOf(app.isTVApp ? 1 : 0), Integer.valueOf(app.isTest ? 1 : 0), app.systemUiAsOverlayMode}, 0, objArr, 27, 27);
            System.arraycopy(new Object[]{Integer.valueOf(app.autoUpdateEnabled ? 1 : 0), Integer.valueOf(app.ducNonCompliant ? 1 : 0), AppConverter.convertAccessFeatureKeysToString(app.accessFeatureKeys), app.environmentSourceId, app.environmentSourceDisplayName}, 0, objArr, 54, 5);
            addRow(objArr);
        }
    }
}
