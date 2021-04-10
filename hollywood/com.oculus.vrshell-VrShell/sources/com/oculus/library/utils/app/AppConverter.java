package com.oculus.library.utils.app;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.library.model.App;
import com.oculus.library.model.AppMediaCapability;
import com.oculus.library.model.AppStatus;
import com.oculus.library.model.CloudStorageStatus;
import com.oculus.library.model.Image;
import com.oculus.library.model.InputDevice;
import com.oculus.library.model.PlayMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
    private static final String TAG = "AppConverter";
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

    public void addContentValuePackingVersion(ContentValues contentValues, int i) {
        contentValues.put(CONTENT_VALUE_VERSION, Integer.valueOf(i));
    }

    public int getAndRemoveContentValuePackingVersion(ContentValues contentValues) {
        if (!contentValues.containsKey(CONTENT_VALUE_VERSION)) {
            return 1;
        }
        int intValue = contentValues.getAsInteger(CONTENT_VALUE_VERSION).intValue();
        contentValues.remove(CONTENT_VALUE_VERSION);
        return intValue;
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
            ArrayList arrayList = new ArrayList();
            do {
                App extractNextAppFromCursor = extractNextAppFromCursor(cursor);
                if (extractNextAppFromCursor != null) {
                    arrayList.add(extractNextAppFromCursor);
                } else {
                    Log.w(TAG, "Got unexpected null from cursor app extraction");
                }
            } while (cursor.moveToNext());
            return (App[]) arrayList.toArray(new App[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r14v24. Raw type applied. Possible types: java.util.List<java.lang.String> */
    /* JADX DEBUG: Type inference failed for r11v124. Raw type applied. Possible types: java.util.List<com.oculus.library.model.PlayMode> */
    /* JADX DEBUG: Type inference failed for r11v132. Raw type applied. Possible types: java.util.List<com.oculus.library.model.AppMediaCapability> */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x041e  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0432  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x043e  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x044e  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0455  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0465  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0471  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0486  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x04a8  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x04b8  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x04bd  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x04ca  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x04cd  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x04d3  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x04d5  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0240 A[LOOP:1: B:23:0x023e->B:24:0x0240, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0298  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x02e1  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x02f8  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0332  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0344  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0349  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0359  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x036d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0384  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x038b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x03a2  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x03b3  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x03bf  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x03cf  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x03db  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x03eb  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x03f7  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0407  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x040e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.library.model.App extractNextAppFromCursor(android.database.Cursor r77) {
        /*
        // Method dump skipped, instructions count: 1321
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.utils.app.AppConverter.extractNextAppFromCursor(android.database.Cursor):com.oculus.library.model.App");
    }

    public ContentValues toContentValues(App.Editor editor) {
        if (TextUtils.isEmpty(editor.packageName)) {
            Log.e(TAG, "Invalid app editor received, returning a null content values pack");
            return new ContentValues();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PACKAGE_NAME, editor.packageName);
        if (editor.status.isPresent()) {
            contentValues.put(COL_STATUS, editor.status.get().name());
        }
        if (editor.cloudStorageStatus.isPresent()) {
            contentValues.put(COL_CLOUD_STORAGE_STATUS, editor.cloudStorageStatus.get().name());
        }
        if (editor.downloadedSizeBytes.isPresent()) {
            contentValues.put(COL_DOWNLOADED_SIZE, editor.downloadedSizeBytes.get());
        }
        if (editor.downloadSizeBytes.isPresent()) {
            contentValues.put(COL_INSTALLATION_SIZE, editor.downloadSizeBytes.get());
        }
        if (editor.appScopedUserId.isPresent()) {
            contentValues.put(COL_APP_SCOPED_USER_ID, editor.appScopedUserId.get());
        }
        if (editor.isUnseen.isPresent()) {
            contentValues.put(COL_UNSEEN, Integer.valueOf(editor.isUnseen.get().booleanValue() ? 1 : 0));
        }
        if (editor.recentActivityMs.isPresent()) {
            contentValues.put(COL_RECENT_ACTIVITY, editor.recentActivityMs.get());
        }
        if (editor.totalActivityMs.isPresent()) {
            contentValues.put(COL_TOTAL_ACTIVITY, editor.totalActivityMs.get());
        }
        if (editor.languagePack.isPresent()) {
            contentValues.put(COL_CURRENT_LANGUAGE_PACK, editor.languagePack.get());
        }
        if (editor.trustedBinaryStatus.isPresent()) {
            contentValues.put(COL_TRUSTED_BINARY_STATUS, editor.trustedBinaryStatus.get());
        }
        return contentValues;
    }

    public App fromContentValuesUpdate(App app, ContentValues contentValues) {
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
            boolean z = true;
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
        String convertPermissionListToString = convertPermissionListToString(app.latestPermissions);
        String convertSupportedInputDevicesListToString = convertSupportedInputDevicesListToString(app.supportedInputDevices);
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
        contentValues.put(COL_PERMISSIONS, convertPermissionListToString);
        contentValues.put(COL_INSTALLABLE_ON_EXTERNAL_STORAGE, Integer.valueOf(app.installableOnExternalStorage ? 1 : 0));
        contentValues.put(COL_STATUS, app.status.name());
        contentValues.put(COL_CLOUD_STORAGE_STATUS, app.cloudStorageStatus.name());
        contentValues.put(COL_SUPPORTED_INPUT_DEVICES, convertSupportedInputDevicesListToString);
        contentValues.put(COL_ENTITLEMENT_HASH, app.entitlementHash);
        contentValues.put(COL_APP_SCOPED_USER_ID, app.appScopedUserId);
        contentValues.put(COL_RECENT_ACTIVITY, Long.valueOf(app.recentActivityMs));
        contentValues.put(COL_COMFORT_RATING, app.comfortRating.name());
        contentValues.put(COL_UNSEEN, Integer.valueOf(app.isUnseen ? 1 : 0));
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

    /* JADX WARNING: Removed duplicated region for block: B:100:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x036f  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0377  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0387  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x03ac  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x03b8  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0212  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x027e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0323  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x033c  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0355  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.library.utils.app.ContentValueUnpack fromContentValues(android.content.ContentValues r73) {
        /*
        // Method dump skipped, instructions count: 1033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.utils.app.AppConverter.fromContentValues(android.content.ContentValues):com.oculus.library.utils.app.ContentValueUnpack");
    }

    private String getImageUri(App app, Image.ImageName imageName) {
        Image image = app.images.get(imageName);
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
        } catch (PackageManager.NameNotFoundException unused) {
            return AppStatus.ENTITLED;
        }
    }

    static String convertPermissionListToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }

    private static List<String> convertPermissionStringToList(String str) {
        return new ArrayList(Arrays.asList(str.split(",")));
    }

    static String convertSupportedInputDevicesListToString(List<InputDevice> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(list.get(i).generateDeviceString());
        }
        return stringBuffer.toString();
    }

    private List<InputDevice> convertSupportedInputDevicesStringToList(String str) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            InputDevice generateFromDeviceString = InputDevice.generateFromDeviceString(str2);
            if (generateFromDeviceString != null) {
                arrayList.add(generateFromDeviceString);
            }
        }
        return arrayList;
    }

    static List<PlayMode> convertStringToPlayModes(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        String[] split = str.split(",");
        for (String str2 : split) {
            if (!TextUtils.isEmpty(str2)) {
                try {
                    arrayList.add(PlayMode.valueOf(str2));
                } catch (IllegalArgumentException unused) {
                    Log.e(TAG, "Unrecognized play mode: " + str2);
                    arrayList.add(PlayMode.UNKNOWN);
                }
            }
        }
        return arrayList;
    }

    static List<AppMediaCapability> convertStringToAppMediaCapabilities(String str) {
        HashSet hashSet = new HashSet();
        if (str == null) {
            return new ArrayList(hashSet);
        }
        String[] split = str.split(";");
        for (String str2 : split) {
            if (!TextUtils.isEmpty(str2)) {
                hashSet.add(AppMediaCapability.fromString(str2));
            }
        }
        return new ArrayList(hashSet);
    }

    static String convertPlayModesToString(List<PlayMode> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<PlayMode> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    static String convertAppMediaCapabilitiesToString(List<AppMediaCapability> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<AppMediaCapability> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name());
            if (it.hasNext()) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    static String convertAccessFeatureKeysToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuffer.append(";");
            }
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }

    static List<String> convertStringToAccessFeatureKeys(String str) {
        return new ArrayList(Arrays.asList(str.split(";")));
    }

    public static List<String> validate(App app, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(app.id)) {
            arrayList.add("Invalid id: " + app.id);
        }
        if (TextUtils.isEmpty(app.packageName)) {
            arrayList.add("packageName empty/null");
        }
        if (app.status == null) {
            arrayList.add("status null");
        }
        if (app.cloudStorageStatus == null) {
            arrayList.add("cloud storage status null");
        }
        if (TextUtils.isEmpty(app.displayName)) {
            arrayList.add("display name empty/null");
        }
        if (app.comfortRating == null) {
            arrayList.add("comfort rating null");
        }
        if (app.images == null) {
            arrayList.add("images null");
        }
        if (app.downloadSizeBytes < 0) {
            arrayList.add("download size: " + app.downloadedSizeBytes);
        }
        if (app.downloadedSizeBytes < 0) {
            arrayList.add("downloaded size: " + app.downloadedSizeBytes);
        }
        if (app.latestTargetSdkVersion < 0) {
            arrayList.add("latest target sdk: " + app.latestTargetSdkVersion);
        }
        if (app.latestPermissions == null) {
            arrayList.add("latest permissions null");
        }
        if (app.supportedInputDevices == null) {
            arrayList.add("supported input devices null");
        }
        if (TextUtils.isEmpty(app.entitlementHash)) {
            arrayList.add("entitlement hash empty/null");
        }
        if (!z && TextUtils.isEmpty(app.appScopedUserId)) {
            arrayList.add("app scoped id is empty/null");
        }
        if (TextUtils.isEmpty(app.applicationGroupingId)) {
            arrayList.add("application grouping empty/null");
        }
        if (app.microphoneUsage == null) {
            arrayList.add("microphone usage null");
        }
        if (app.recentActivityMs < 0) {
            arrayList.add("recent activity: " + app.recentActivityMs);
        }
        if (app.totalActivityMs < 0) {
            arrayList.add("total activity: " + app.totalActivityMs);
        }
        if (app.appMediaCapabilities == null) {
            arrayList.add("app media capabilities null");
        }
        if (app.playModes == null) {
            arrayList.add("play modes null");
        }
        if (app.category == null) {
            arrayList.add("category null");
        }
        if (app.headTracking == null) {
            arrayList.add("headtracking null");
        }
        if (app.grantReason == null) {
            arrayList.add("grant reason null");
        }
        if (app.grantTimeMs < -1) {
            arrayList.add("grant time: " + app.grantTimeMs);
        }
        if (app.grantExpirationMs < -2) {
            arrayList.add("grant expiration: " + app.grantExpirationMs);
        }
        if (TextUtils.isEmpty(app.systemUiAsOverlayMode)) {
            arrayList.add("system ui as overlay mode empty/null");
        }
        if (app.accessFeatureKeys == null) {
            arrayList.add("access feature keys null");
        }
        return arrayList;
    }

    public static boolean isValid(App app, boolean z) {
        return !TextUtils.isEmpty(app.id) && !TextUtils.isEmpty(app.packageName) && app.status != null && app.cloudStorageStatus != null && !TextUtils.isEmpty(app.displayName) && app.comfortRating != null && app.images != null && app.downloadSizeBytes >= 0 && app.downloadedSizeBytes >= 0 && app.latestTargetSdkVersion >= 0 && app.latestPermissions != null && app.supportedInputDevices != null && !TextUtils.isEmpty(app.entitlementHash) && (z || !TextUtils.isEmpty(app.appScopedUserId)) && !TextUtils.isEmpty(app.applicationGroupingId) && app.microphoneUsage != null && app.recentActivityMs >= 0 && app.totalActivityMs >= 0 && app.appMediaCapabilities != null && app.playModes != null && app.category != null && app.headTracking != null && app.grantReason != null && app.grantTimeMs >= -1 && app.grantExpirationMs >= -2 && !TextUtils.isEmpty(app.systemUiAsOverlayMode) && app.accessFeatureKeys != null;
    }

    public boolean isNotificationWorthy(App app, App app2) {
        return !isEqual(app2, new App.Builder(app).withEntitlementHash(app2.entitlementHash).build());
    }

    public boolean isEqual(App app, App app2) {
        if (app == null && app2 == null) {
            return true;
        }
        if (!(app == null || app2 == null)) {
            try {
                if (Objects.equals(app.id, app2.id) && Objects.equals(app.packageName, app2.packageName) && app.status == app2.status && app.cloudStorageStatus == app2.cloudStorageStatus && Objects.equals(app.displayName, app2.displayName) && app.comfortRating == app2.comfortRating && app.downloadSizeBytes == app2.downloadSizeBytes && app.downloadedSizeBytes == app2.downloadedSizeBytes && app.versionCode == app2.versionCode && Objects.equals(app.versionName, app2.versionName) && app.latestVersionCode == app2.latestVersionCode && Objects.equals(app.latestVersionName, app2.latestVersionName) && app.latestTargetSdkVersion == app2.latestTargetSdkVersion && app.installableOnExternalStorage == app2.installableOnExternalStorage && Objects.equals(app.entitlementHash, app2.entitlementHash) && Objects.equals(app.appScopedUserId, app2.appScopedUserId) && Objects.equals(app.applicationGroupingId, app2.applicationGroupingId) && Objects.equals(app.applicationOrganizationId, app2.applicationOrganizationId) && app.microphoneUsage == app2.microphoneUsage && app.minRecommendedVersionCode == app2.minRecommendedVersionCode && app.minRequiredVersionCode == app2.minRequiredVersionCode && app.isUnseen == app2.isUnseen && app.recentActivityMs == app2.recentActivityMs && app.totalActivityMs == app2.totalActivityMs && Objects.equals(app.isDemoOf, app2.isDemoOf) && app.platform == app2.platform && app.category == app2.category && app.headTracking == app2.headTracking && app.grantReason == app2.grantReason && app.grantTimeMs == app2.grantTimeMs && app.grantExpirationMs == app2.grantExpirationMs && app.apkFullSizeBytes == app2.apkFullSizeBytes && Objects.equals(app.currentLanguagePack, app2.currentLanguagePack) && Objects.equals(app.internetConnection, app2.internetConnection) && Objects.equals(app.trustedBinaryStatus, app2.trustedBinaryStatus) && app.isConcept == app2.isConcept && app.isTVApp == app2.isTVApp && app.isTest == app2.isTest && Objects.equals(app.systemUiAsOverlayMode, app2.systemUiAsOverlayMode) && app.autoUpdateEnabled == app2.autoUpdateEnabled && app.ducNonCompliant == app2.ducNonCompliant && Objects.equals(app.environmentSourceId, app2.environmentSourceId)) {
                    if (Objects.equals(app.environmentSourceDisplayName, app2.environmentSourceDisplayName)) {
                        HashSet hashSet = new HashSet(app.appMediaCapabilities);
                        HashSet hashSet2 = new HashSet(app2.appMediaCapabilities);
                        if (hashSet.size() == hashSet2.size()) {
                            if (hashSet.containsAll(hashSet2)) {
                                HashSet hashSet3 = new HashSet(app.playModes);
                                HashSet hashSet4 = new HashSet(app2.playModes);
                                if (hashSet3.size() == hashSet4.size()) {
                                    if (hashSet3.containsAll(hashSet4)) {
                                        Image.ImageName[] values = Image.ImageName.values();
                                        for (Image.ImageName imageName : values) {
                                            String imageUri = getImageUri(app, imageName);
                                            String imageUri2 = getImageUri(app2, imageName);
                                            if (imageUri == null || imageUri2 == null) {
                                                if (imageUri == null) {
                                                    if (imageUri2 != null) {
                                                    }
                                                }
                                                return false;
                                            } else if (!imageUri.equals(imageUri2)) {
                                                return false;
                                            }
                                        }
                                        if (!(convertPermissionListToString(app.latestPermissions).equals(convertPermissionListToString(app2.latestPermissions)) && convertSupportedInputDevicesListToString(app.supportedInputDevices).equals(convertSupportedInputDevicesListToString(app2.supportedInputDevices)))) {
                                            return false;
                                        }
                                        HashSet hashSet5 = new HashSet(app.accessFeatureKeys);
                                        HashSet hashSet6 = new HashSet(app2.accessFeatureKeys);
                                        return hashSet5.size() == hashSet6.size() && hashSet5.containsAll(hashSet6);
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (NullPointerException unused) {
            }
        }
    }
}
