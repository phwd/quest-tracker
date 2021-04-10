package com.oculus.library.utils.app;

import android.database.MatrixCursor;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import java.util.Collection;

public class AppsCursor extends MatrixCursor {
    private static final String[] COLUMNS = {LibraryDBContract.Columns.ITEM_ID.name, LibraryDBContract.Columns.NAME.name, LibraryDBContract.Columns.APPLICATION_GROUPING_ID.name, LibraryDBContract.Columns.APPLICATION_ORGANIZATION_ID.name, LibraryDBContract.Columns.IMAGE_SOURCE_SQUARE.name, LibraryDBContract.Columns.IMAGE_SOURCE_MAIN.name, LibraryDBContract.Columns.IMAGE_SOURCE_TINY.name, LibraryDBContract.Columns.SMALL_LANDSCAPE_IMAGE.name, LibraryDBContract.Columns.HERO_IMAGE.name, LibraryDBContract.Columns.PACKAGE_NAME.name, LibraryDBContract.Columns.LATEST_VERSION_CODE.name, LibraryDBContract.Columns.LATEST_VERSION_NAME.name, LibraryDBContract.Columns.LATEST_SDK_TARGET_VERSION.name, LibraryDBContract.Columns.INSTALLATION_SIZE.name, LibraryDBContract.Columns.DOWNLOADED_SIZE.name, LibraryDBContract.Columns.PERMISSIONS.name, LibraryDBContract.Columns.INSTALLABLE_ON_EXTERNAL_STORAGE.name, LibraryDBContract.Columns.USER_QUALITY_RATING.name, LibraryDBContract.Columns.STATUS.name, LibraryDBContract.Columns.CLOUD_STORAGE_STATUS.name, LibraryDBContract.Columns.SUPPORTED_INPUT_DEVICES.name, LibraryDBContract.Columns.ENTITLEMENT_HASH.name, LibraryDBContract.Columns.APP_SCOPED_USER_ID.name, LibraryDBContract.Columns.RECENT_ACTIVITY.name, LibraryDBContract.Columns.COMFORT_RATING.name, LibraryDBContract.Columns.FAVORITE.name, LibraryDBContract.Columns.UNSEEN.name, LibraryDBContract.Columns.LIVESTREAMING_STATUS.name, LibraryDBContract.Columns.LIVESTREAMING_STATUS_AUDIO_HOOKING_ENABLED.name, LibraryDBContract.Columns.CHROMECAST_AUDIO_ENABLED.name, LibraryDBContract.Columns.MIN_RECOMMENDED_VERSION_CODE.name, LibraryDBContract.Columns.MIN_REQUIRED_VERSION_CODE.name, LibraryDBContract.Columns.MICROPHONE_USAGE.name, LibraryDBContract.Columns.TOTAL_ACTIVITY.name, LibraryDBContract.Columns.PATCH_DOWNLOAD_SIZE.name, LibraryDBContract.Columns.APK_FULL_SIZE.name, LibraryDBContract.Columns.OBB_FULL_SIZE.name, LibraryDBContract.Columns.IS_DEMO_OF.name, LibraryDBContract.Columns.SUPPORTED_PLATFORM.name, LibraryDBContract.Columns.APP_MEDIA_CAPABILITIES.name, LibraryDBContract.Columns.PLAY_MODES.name, LibraryDBContract.Columns.CATEGORY.name, LibraryDBContract.Columns.HEAD_TRACKING.name, LibraryDBContract.Columns.GRANT_REASON.name, LibraryDBContract.Columns.GRANT_TIME.name, LibraryDBContract.Columns.GRANT_EXPIRATION.name, LibraryDBContract.Columns.CURRENT_LANGUAGE_PACK.name, LibraryDBContract.Columns.PERMISSIONS_V2.name, LibraryDBContract.Columns.INTERNET_CONNECTION.name, LibraryDBContract.Columns.TRUSTED_BINARY_STATUS.name, LibraryDBContract.Columns.IS_CONCEPT.name, LibraryDBContract.Columns.IS_TV_APP.name, LibraryDBContract.Columns.IS_TEST.name, LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name, LibraryDBContract.Columns.AUTO_UPDATE_ENABLED.name, LibraryDBContract.Columns.DUC_NON_COMPLIANT.name, LibraryDBContract.Columns.ACCESS_FEATURE_KEYS.name, LibraryDBContract.Columns.ENVIRONMENT_SOURCE_ID.name, LibraryDBContract.Columns.ENVIRONMENT_SOURCE_DISPLAY_NAME.name};

    public AppsCursor(int size) {
        super(COLUMNS, size);
    }

    public void fill(Collection<App> apps) {
        for (App app : apps) {
            Object[] values = new Object[59];
            values[0] = app.id;
            values[1] = app.displayName;
            values[2] = app.applicationGroupingId;
            values[3] = app.applicationOrganizationId;
            values[4] = app.images.get(Image.ImageName.SOURCE_SQUARE).uri;
            values[5] = app.images.get(Image.ImageName.SOURCE_MAIN).uri;
            values[6] = app.images.get(Image.ImageName.SOURCE_TINY).uri;
            values[7] = app.images.get(Image.ImageName.LANDSCAPE_SMALL).uri;
            values[8] = app.images.get(Image.ImageName.HERO).uri;
            values[9] = app.packageName;
            values[10] = Long.valueOf(app.latestVersionCode);
            values[11] = app.latestVersionName;
            values[12] = Integer.valueOf(app.latestTargetSdkVersion);
            values[13] = Long.valueOf(app.downloadSizeBytes);
            values[14] = Long.valueOf(app.downloadedSizeBytes);
            values[15] = "";
            values[16] = Integer.valueOf(app.installableOnExternalStorage ? 1 : 0);
            values[17] = 0;
            values[18] = app.status;
            values[19] = app.cloudStorageStatus;
            values[20] = AppConverter.convertSupportedInputDevicesListToString(app.supportedInputDevices);
            values[21] = app.entitlementHash;
            values[22] = app.appScopedUserId;
            values[23] = Long.valueOf(app.recentActivityMs);
            values[24] = app.comfortRating.name();
            values[25] = 0;
            values[26] = Integer.valueOf(app.isUnseen ? 1 : 0);
            values[27] = "UNKNOWN";
            values[28] = 0;
            values[29] = 0;
            values[30] = Long.valueOf(app.minRecommendedVersionCode);
            values[31] = Long.valueOf(app.minRequiredVersionCode);
            values[32] = app.microphoneUsage.name();
            values[33] = Long.valueOf(app.totalActivityMs);
            values[34] = 0;
            values[35] = Long.valueOf(app.apkFullSizeBytes);
            values[36] = 0;
            values[37] = app.isDemoOf;
            values[38] = app.platform.name();
            values[39] = AppConverter.convertAppMediaCapabilitiesToString(app.appMediaCapabilities);
            values[40] = AppConverter.convertPlayModesToString(app.playModes);
            values[41] = app.category.name();
            values[42] = app.headTracking.name();
            values[43] = app.grantReason.name();
            values[44] = Long.valueOf(app.grantTimeMs);
            values[45] = Long.valueOf(app.grantExpirationMs);
            values[46] = app.currentLanguagePack;
            values[47] = AppConverter.convertPermissionListToString(app.latestPermissions);
            values[48] = app.internetConnection;
            values[49] = app.trustedBinaryStatus;
            values[50] = Integer.valueOf(app.isConcept ? 1 : 0);
            values[51] = Integer.valueOf(app.isTVApp ? 1 : 0);
            values[52] = Integer.valueOf(app.isTest ? 1 : 0);
            values[53] = app.systemUiAsOverlayMode;
            values[54] = Integer.valueOf(app.autoUpdateEnabled ? 1 : 0);
            values[55] = Integer.valueOf(app.ducNonCompliant ? 1 : 0);
            values[56] = AppConverter.convertAccessFeatureKeysToString(app.accessFeatureKeys);
            values[57] = app.environmentSourceId;
            values[58] = app.environmentSourceDisplayName;
            addRow(values);
        }
    }
}
