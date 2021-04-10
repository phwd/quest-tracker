package com.oculus.library.utils.app;

import X.AnonymousClass006;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AppConverter {
    public static final String COL_ACCESS_FEATURE_KEYS;
    @Deprecated
    public static final String COL_APK_SIZE;
    public static final String COL_APPLICATION_GROUPING_ID;
    public static final String COL_APPLICATION_ORGANIZATION_ID;
    public static final String COL_APP_MEDIA_CAPABILITIES;
    public static final String COL_APP_SCOPED_USER_ID;
    public static final String COL_AUTO_UPDATE_ENABLED;
    public static final String COL_CATEGORY;
    public static final String COL_CLOUD_STORAGE_STATUS;
    public static final String COL_COMFORT_RATING;
    public static final String COL_CURRENT_LANGUAGE_PACK;
    public static final String COL_DOWNLOADED_SIZE;
    public static final String COL_DUC_NON_COMPLIANT;
    public static final String COL_ENTITLEMENT_HASH;
    public static final String COL_ENVIRONMENT_SOURCE_DISPLAY_NAME;
    public static final String COL_ENVIRONMENT_SOURCE_ID;
    public static final String COL_GRANT_EXPIRATION;
    public static final String COL_GRANT_REASON;
    public static final String COL_GRANT_TIME;
    public static final String COL_HEAD_TRACKING;
    public static final String COL_HERO_IMAGE;
    public static final String COL_IMAGE_SOURCE_MAIN;
    public static final String COL_IMAGE_SOURCE_SQUARE;
    public static final String COL_IMAGE_SOURCE_TINY;
    public static final String COL_INSTALLABLE_ON_EXTERNAL_STORAGE;
    public static final String COL_INSTALLATION_SIZE;
    public static final String COL_INTERNET_CONNECTION;
    public static final String COL_IS_CONCEPT;
    public static final String COL_IS_DEMO_OF;
    public static final String COL_IS_TEST;
    public static final String COL_IS_TV_APP;
    public static final String COL_ITEM_ID;
    public static final String COL_LATEST_SDK_TARGET_VERSION;
    public static final String COL_LATEST_VERSION_CODE;
    public static final String COL_LATEST_VERSION_NAME;
    public static final String COL_MICROPHONE_USAGE;
    public static final String COL_MIN_RECOMMENDED_VERSION_CODE;
    public static final String COL_MIN_REQUIRED_VERSION_CODE;
    public static final String COL_NAME;
    public static final String COL_PACKAGE_NAME;
    public static final String COL_PERMISSIONS;
    public static final String COL_PLAY_MODES;
    public static final String COL_RECENT_ACTIVITY;
    public static final String COL_SMALL_LANDSCAPE_IMAGE;
    public static final String COL_STATUS;
    public static final String COL_SUPPORTED_INPUT_DEVICES;
    public static final String COL_SUPPORTED_PLATFORM;
    public static final String COL_SYSTEM_UI_OVERLAY_MODE;
    public static final String COL_TOTAL_ACTIVITY;
    public static final String COL_TRUSTED_BINARY_STATUS;
    public static final String COL_UNSEEN;
    public static final String COL_USER_ID;
    public static final String CONTENT_VALUE_VERSION = "ovrlibrary_cv_version";
    public static final int CV_VERSION_1 = 1;
    public static final int CV_VERSION_2 = 2;
    public static final int LOWEST_VALID_GRANT_EXPIRATION = -2;
    public static final int LOWEST_VALID_GRANT_TIME = -1;
    public static final String[] OPTIONAL_COLUMNS;
    public static final String[] REQUIRED_COLUMNS;
    public static final String TAG = "AppConverter";
    public final PackageManager mPackageManager;

    public App[] extractAllFromCursor(Cursor cursor) {
        if (cursor == null) {
            Log.w(TAG, "Cannot load database due to null cursor");
        } else if (!cursor.moveToFirst()) {
            Log.w(TAG, "Unable to move cursor to the first position");
            cursor.close();
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
        return new App[0];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0405, code lost:
        if (r83.getInt(r4) == 1) goto L_0x0407;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.library.model.App extractNextAppFromCursor(android.database.Cursor r83) {
        /*
        // Method dump skipped, instructions count: 1197
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.utils.app.AppConverter.extractNextAppFromCursor(android.database.Cursor):com.oculus.library.model.App");
    }

    public boolean isEqual(App app, App app2) {
        if (!(app == null && app2 == null)) {
            if (!(app == null || app2 == null)) {
                try {
                    if (Objects.equals(app.id, app2.id) && Objects.equals(app.packageName, app2.packageName) && app.status == app2.status && app.cloudStorageStatus == app2.cloudStorageStatus && Objects.equals(app.displayName, app2.displayName) && app.comfortRating == app2.comfortRating && app.downloadSizeBytes == app2.downloadSizeBytes && app.downloadedSizeBytes == app2.downloadedSizeBytes && app.versionCode == app2.versionCode && Objects.equals(app.versionName, app2.versionName) && app.latestVersionCode == app2.latestVersionCode && Objects.equals(app.latestVersionName, app2.latestVersionName) && app.latestTargetSdkVersion == app2.latestTargetSdkVersion && app.installableOnExternalStorage == app2.installableOnExternalStorage && Objects.equals(app.entitlementHash, app2.entitlementHash) && Objects.equals(app.appScopedUserId, app2.appScopedUserId) && Objects.equals(app.applicationGroupingId, app2.applicationGroupingId) && Objects.equals(app.applicationOrganizationId, app2.applicationOrganizationId) && app.microphoneUsage == app2.microphoneUsage && app.minRecommendedVersionCode == app2.minRecommendedVersionCode && app.minRequiredVersionCode == app2.minRequiredVersionCode && app.isUnseen == app2.isUnseen && app.recentActivityMs == app2.recentActivityMs && app.totalActivityMs == app2.totalActivityMs && Objects.equals(app.isDemoOf, app2.isDemoOf) && app.platform == app2.platform && app.category == app2.category && app.headTracking == app2.headTracking && app.grantReason == app2.grantReason && app.grantTimeMs == app2.grantTimeMs && app.grantExpirationMs == app2.grantExpirationMs && app.apkFullSizeBytes == app2.apkFullSizeBytes && Objects.equals(app.currentLanguagePack, app2.currentLanguagePack) && Objects.equals(app.internetConnection, app2.internetConnection) && Objects.equals(app.trustedBinaryStatus, app2.trustedBinaryStatus) && app.isConcept == app2.isConcept && app.isTVApp == app2.isTVApp && app.isTest == app2.isTest && Objects.equals(app.systemUiAsOverlayMode, app2.systemUiAsOverlayMode) && app.autoUpdateEnabled == app2.autoUpdateEnabled && app.ducNonCompliant == app2.ducNonCompliant && Objects.equals(app.environmentSourceId, app2.environmentSourceId) && Objects.equals(app.environmentSourceDisplayName, app2.environmentSourceDisplayName)) {
                        HashSet hashSet = new HashSet(app.appMediaCapabilities);
                        HashSet hashSet2 = new HashSet(app2.appMediaCapabilities);
                        if (hashSet.size() == hashSet2.size() && hashSet.containsAll(hashSet2)) {
                            HashSet hashSet3 = new HashSet(app.playModes);
                            HashSet hashSet4 = new HashSet(app2.playModes);
                            if (hashSet3.size() == hashSet4.size() && hashSet3.containsAll(hashSet4)) {
                                Image.ImageName[] values = Image.ImageName.values();
                                int length = values.length;
                                int i = 0;
                                while (true) {
                                    if (i < length) {
                                        Image.ImageName imageName = values[i];
                                        String imageUri = getImageUri(app, imageName);
                                        String imageUri2 = getImageUri(app2, imageName);
                                        if (imageUri != null) {
                                            if (imageUri2 == null) {
                                                break;
                                            } else if (!imageUri.equals(imageUri2)) {
                                                return false;
                                            }
                                        } else if (imageUri2 != null) {
                                            break;
                                        }
                                        i++;
                                    } else if (convertPermissionListToString(app.latestPermissions).equals(convertPermissionListToString(app2.latestPermissions)) && convertSupportedInputDevicesListToString(app.supportedInputDevices).equals(convertSupportedInputDevicesListToString(app2.supportedInputDevices))) {
                                        HashSet hashSet5 = new HashSet(app.accessFeatureKeys);
                                        HashSet hashSet6 = new HashSet(app2.accessFeatureKeys);
                                        if (hashSet5.size() == hashSet6.size()) {
                                            if (!hashSet5.containsAll(hashSet6)) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                return false;
                            }
                        }
                    }
                } catch (NullPointerException unused) {
                }
            }
            return false;
        }
        return true;
    }

    static {
        String str = LibraryDBContract.Columns.USER_ID.name;
        COL_USER_ID = str;
        String str2 = LibraryDBContract.Columns.ITEM_ID.name;
        COL_ITEM_ID = str2;
        String str3 = LibraryDBContract.Columns.NAME.name;
        COL_NAME = str3;
        String str4 = LibraryDBContract.Columns.APPLICATION_GROUPING_ID.name;
        COL_APPLICATION_GROUPING_ID = str4;
        String str5 = LibraryDBContract.Columns.APPLICATION_ORGANIZATION_ID.name;
        COL_APPLICATION_ORGANIZATION_ID = str5;
        String str6 = LibraryDBContract.Columns.IMAGE_SOURCE_SQUARE.name;
        COL_IMAGE_SOURCE_SQUARE = str6;
        String str7 = LibraryDBContract.Columns.IMAGE_SOURCE_MAIN.name;
        COL_IMAGE_SOURCE_MAIN = str7;
        String str8 = LibraryDBContract.Columns.IMAGE_SOURCE_TINY.name;
        COL_IMAGE_SOURCE_TINY = str8;
        String str9 = LibraryDBContract.Columns.PACKAGE_NAME.name;
        COL_PACKAGE_NAME = str9;
        String str10 = LibraryDBContract.Columns.LATEST_VERSION_CODE.name;
        COL_LATEST_VERSION_CODE = str10;
        String str11 = LibraryDBContract.Columns.LATEST_VERSION_NAME.name;
        COL_LATEST_VERSION_NAME = str11;
        String str12 = LibraryDBContract.Columns.LATEST_SDK_TARGET_VERSION.name;
        COL_LATEST_SDK_TARGET_VERSION = str12;
        String str13 = LibraryDBContract.Columns.INSTALLATION_SIZE.name;
        COL_INSTALLATION_SIZE = str13;
        String str14 = LibraryDBContract.Columns.DOWNLOADED_SIZE.name;
        COL_DOWNLOADED_SIZE = str14;
        String str15 = LibraryDBContract.Columns.INSTALLABLE_ON_EXTERNAL_STORAGE.name;
        COL_INSTALLABLE_ON_EXTERNAL_STORAGE = str15;
        String str16 = LibraryDBContract.Columns.STATUS.name;
        COL_STATUS = str16;
        String str17 = LibraryDBContract.Columns.CLOUD_STORAGE_STATUS.name;
        COL_CLOUD_STORAGE_STATUS = str17;
        String str18 = LibraryDBContract.Columns.SUPPORTED_INPUT_DEVICES.name;
        COL_SUPPORTED_INPUT_DEVICES = str18;
        String str19 = LibraryDBContract.Columns.ENTITLEMENT_HASH.name;
        COL_ENTITLEMENT_HASH = str19;
        String str20 = LibraryDBContract.Columns.APP_SCOPED_USER_ID.name;
        COL_APP_SCOPED_USER_ID = str20;
        String str21 = LibraryDBContract.Columns.RECENT_ACTIVITY.name;
        COL_RECENT_ACTIVITY = str21;
        String str22 = LibraryDBContract.Columns.COMFORT_RATING.name;
        COL_COMFORT_RATING = str22;
        String str23 = LibraryDBContract.Columns.SMALL_LANDSCAPE_IMAGE.name;
        COL_SMALL_LANDSCAPE_IMAGE = str23;
        String str24 = LibraryDBContract.Columns.HERO_IMAGE.name;
        COL_HERO_IMAGE = str24;
        String str25 = LibraryDBContract.Columns.UNSEEN.name;
        COL_UNSEEN = str25;
        String str26 = LibraryDBContract.Columns.MIN_RECOMMENDED_VERSION_CODE.name;
        COL_MIN_RECOMMENDED_VERSION_CODE = str26;
        String str27 = LibraryDBContract.Columns.MIN_REQUIRED_VERSION_CODE.name;
        COL_MIN_REQUIRED_VERSION_CODE = str27;
        String str28 = LibraryDBContract.Columns.MICROPHONE_USAGE.name;
        COL_MICROPHONE_USAGE = str28;
        String str29 = LibraryDBContract.Columns.TOTAL_ACTIVITY.name;
        COL_TOTAL_ACTIVITY = str29;
        String str30 = LibraryDBContract.Columns.IS_DEMO_OF.name;
        COL_IS_DEMO_OF = str30;
        String str31 = LibraryDBContract.Columns.SUPPORTED_PLATFORM.name;
        COL_SUPPORTED_PLATFORM = str31;
        String str32 = LibraryDBContract.Columns.APP_MEDIA_CAPABILITIES.name;
        COL_APP_MEDIA_CAPABILITIES = str32;
        String str33 = LibraryDBContract.Columns.HEAD_TRACKING.name;
        COL_HEAD_TRACKING = str33;
        String str34 = LibraryDBContract.Columns.PLAY_MODES.name;
        COL_PLAY_MODES = str34;
        String str35 = LibraryDBContract.Columns.CATEGORY.name;
        COL_CATEGORY = str35;
        String str36 = LibraryDBContract.Columns.GRANT_REASON.name;
        COL_GRANT_REASON = str36;
        String str37 = LibraryDBContract.Columns.GRANT_TIME.name;
        COL_GRANT_TIME = str37;
        String str38 = LibraryDBContract.Columns.GRANT_EXPIRATION.name;
        COL_GRANT_EXPIRATION = str38;
        String str39 = LibraryDBContract.Columns.CURRENT_LANGUAGE_PACK.name;
        COL_CURRENT_LANGUAGE_PACK = str39;
        String str40 = LibraryDBContract.Columns.PERMISSIONS_V2.name;
        COL_PERMISSIONS = str40;
        String str41 = LibraryDBContract.Columns.INTERNET_CONNECTION.name;
        COL_INTERNET_CONNECTION = str41;
        String str42 = LibraryDBContract.Columns.TRUSTED_BINARY_STATUS.name;
        COL_TRUSTED_BINARY_STATUS = str42;
        String str43 = LibraryDBContract.Columns.IS_CONCEPT.name;
        COL_IS_CONCEPT = str43;
        String str44 = LibraryDBContract.Columns.IS_TV_APP.name;
        COL_IS_TV_APP = str44;
        String str45 = LibraryDBContract.Columns.IS_TEST.name;
        COL_IS_TEST = str45;
        String str46 = LibraryDBContract.Columns.SYSTEM_UI_AS_OVERLAY_MODE.name;
        COL_SYSTEM_UI_OVERLAY_MODE = str46;
        String str47 = LibraryDBContract.Columns.AUTO_UPDATE_ENABLED.name;
        COL_AUTO_UPDATE_ENABLED = str47;
        String str48 = LibraryDBContract.Columns.DUC_NON_COMPLIANT.name;
        COL_DUC_NON_COMPLIANT = str48;
        String str49 = LibraryDBContract.Columns.ACCESS_FEATURE_KEYS.name;
        COL_ACCESS_FEATURE_KEYS = str49;
        String str50 = LibraryDBContract.Columns.ENVIRONMENT_SOURCE_ID.name;
        COL_ENVIRONMENT_SOURCE_ID = str50;
        String str51 = LibraryDBContract.Columns.ENVIRONMENT_SOURCE_DISPLAY_NAME.name;
        COL_ENVIRONMENT_SOURCE_DISPLAY_NAME = str51;
        String str52 = LibraryDBContract.Columns.APK_FULL_SIZE.name;
        COL_APK_SIZE = str52;
        OPTIONAL_COLUMNS = new String[]{str30, str31, str32, str34, str35, str33, str36, str37, str38, str39, str41, str42, str43, str44, str45, str46, str24, str47, str5, str48, str49, str50, str51};
        String[] strArr = new String[29];
        System.arraycopy(new String[]{str, str2, str3, str22, str4, str6, str7, str8, str23, str9, str10, str11, str12, str13, str14, str40, str15, str16, str17, str18, str19, str20, str21, str25, str26, str27, str28}, 0, strArr, 0, 27);
        System.arraycopy(new String[]{str29, str52}, 0, strArr, 27, 2);
        REQUIRED_COLUMNS = strArr;
    }

    public static String convertAccessFeatureKeysToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuffer.append(";");
            }
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }

    public static String convertAppMediaCapabilitiesToString(List<AppMediaCapability> list) {
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

    public static String convertPermissionListToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(list.get(i));
        }
        return stringBuffer.toString();
    }

    public static List<String> convertPermissionStringToList(String str) {
        return new ArrayList(Arrays.asList(str.split(",")));
    }

    public static String convertPlayModesToString(List<PlayMode> list) {
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

    public static List<String> convertStringToAccessFeatureKeys(String str) {
        return new ArrayList(Arrays.asList(str.split(";")));
    }

    public static List<AppMediaCapability> convertStringToAppMediaCapabilities(String str) {
        HashSet hashSet = new HashSet();
        if (str != null) {
            String[] split = str.split(";");
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    hashSet.add(AppMediaCapability.fromString(str2));
                }
            }
        }
        return new ArrayList(hashSet);
    }

    public static List<PlayMode> convertStringToPlayModes(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            String[] split = str.split(",");
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        arrayList.add(PlayMode.valueOf(str2));
                    } catch (IllegalArgumentException unused) {
                        Log.e(TAG, AnonymousClass006.A07("Unrecognized play mode: ", str2));
                        arrayList.add(PlayMode.UNKNOWN);
                    }
                }
            }
        }
        return arrayList;
    }

    public static String convertSupportedInputDevicesListToString(List<InputDevice> list) {
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
        if (str != null) {
            for (String str2 : str.split(",")) {
                InputDevice generateFromDeviceString = InputDevice.generateFromDeviceString(str2);
                if (generateFromDeviceString != null) {
                    arrayList.add(generateFromDeviceString);
                }
            }
        }
        return arrayList;
    }

    private String getImageUri(App app, Image.ImageName imageName) {
        Image image = app.images.get(imageName);
        if (image == null) {
            return null;
        }
        return image.uri;
    }

    public static String[] getOptionalColumns() {
        return OPTIONAL_COLUMNS;
    }

    public static String[] getRequiredColumns() {
        return REQUIRED_COLUMNS;
    }

    public static boolean isValid(App app, boolean z) {
        if (TextUtils.isEmpty(app.id) || TextUtils.isEmpty(app.packageName) || app.status == null || app.cloudStorageStatus == null || TextUtils.isEmpty(app.displayName) || app.comfortRating == null || app.images == null || app.downloadSizeBytes < 0 || app.downloadedSizeBytes < 0 || app.latestTargetSdkVersion < 0 || app.latestPermissions == null || app.supportedInputDevices == null || TextUtils.isEmpty(app.entitlementHash) || ((!z && TextUtils.isEmpty(app.appScopedUserId)) || TextUtils.isEmpty(app.applicationGroupingId) || app.microphoneUsage == null || app.recentActivityMs < 0 || app.totalActivityMs < 0 || app.appMediaCapabilities == null || app.playModes == null || app.category == null || app.headTracking == null || app.grantReason == null || app.grantTimeMs < -1 || app.grantExpirationMs < -2 || TextUtils.isEmpty(app.systemUiAsOverlayMode) || app.accessFeatureKeys == null)) {
            return false;
        }
        return true;
    }

    public static List<String> validate(App app, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(app.id)) {
            arrayList.add(AnonymousClass006.A07("Invalid id: ", app.id));
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
            arrayList.add(AnonymousClass006.A06("download size: ", app.downloadedSizeBytes));
        }
        long j = app.downloadedSizeBytes;
        if (j < 0) {
            arrayList.add(AnonymousClass006.A06("downloaded size: ", j));
        }
        int i = app.latestTargetSdkVersion;
        if (i < 0) {
            arrayList.add(AnonymousClass006.A03("latest target sdk: ", i));
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
        long j2 = app.recentActivityMs;
        if (j2 < 0) {
            arrayList.add(AnonymousClass006.A06("recent activity: ", j2));
        }
        long j3 = app.totalActivityMs;
        if (j3 < 0) {
            arrayList.add(AnonymousClass006.A06("total activity: ", j3));
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
        long j4 = app.grantTimeMs;
        if (j4 < -1) {
            arrayList.add(AnonymousClass006.A06("grant time: ", j4));
        }
        long j5 = app.grantExpirationMs;
        if (j5 < -2) {
            arrayList.add(AnonymousClass006.A06("grant expiration: ", j5));
        }
        if (TextUtils.isEmpty(app.systemUiAsOverlayMode)) {
            arrayList.add("system ui as overlay mode empty/null");
        }
        if (app.accessFeatureKeys == null) {
            arrayList.add("access feature keys null");
        }
        return arrayList;
    }

    public ContentValueUnpack fromContentValues(ContentValues contentValues) {
        String str;
        long j;
        SupportedPlatform fromString;
        List<AppMediaCapability> convertStringToAppMediaCapabilities;
        HeadTracking fromString2;
        List<PlayMode> convertStringToPlayModes;
        Category fromString3;
        long longValue;
        String asString;
        String asString2;
        String asString3;
        boolean booleanValue;
        boolean booleanValue2;
        boolean booleanValue3;
        String asString4;
        String asString5;
        boolean booleanValue4;
        String asString6;
        boolean booleanValue5;
        String asString7;
        String asString8;
        ArrayList arrayList = new ArrayList(Arrays.asList(REQUIRED_COLUMNS));
        arrayList.remove(COL_USER_ID);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!contentValues.containsKey(str2)) {
                Log.e(TAG, AnonymousClass006.A07("Unable to build app from content values. Missing required cv for ", str2));
                return null;
            }
        }
        String asString9 = contentValues.getAsString(COL_ITEM_ID);
        String asString10 = contentValues.getAsString(COL_NAME);
        String asString11 = contentValues.getAsString(COL_APPLICATION_GROUPING_ID);
        String asString12 = contentValues.getAsString(COL_IMAGE_SOURCE_SQUARE);
        String asString13 = contentValues.getAsString(COL_IMAGE_SOURCE_MAIN);
        String asString14 = contentValues.getAsString(COL_IMAGE_SOURCE_TINY);
        String asString15 = contentValues.getAsString(COL_SMALL_LANDSCAPE_IMAGE);
        String asString16 = contentValues.getAsString(COL_PACKAGE_NAME);
        long longValue2 = contentValues.getAsLong(COL_LATEST_VERSION_CODE).longValue();
        String asString17 = contentValues.getAsString(COL_LATEST_VERSION_NAME);
        int intValue = contentValues.getAsInteger(COL_LATEST_SDK_TARGET_VERSION).intValue();
        long longValue3 = contentValues.getAsLong(COL_INSTALLATION_SIZE).longValue();
        long longValue4 = contentValues.getAsLong(COL_DOWNLOADED_SIZE).longValue();
        String asString18 = contentValues.getAsString(COL_PERMISSIONS);
        int intValue2 = contentValues.getAsInteger(COL_INSTALLABLE_ON_EXTERNAL_STORAGE).intValue();
        String asString19 = contentValues.getAsString(COL_STATUS);
        String asString20 = contentValues.getAsString(COL_CLOUD_STORAGE_STATUS);
        String asString21 = contentValues.getAsString(COL_SUPPORTED_INPUT_DEVICES);
        String asString22 = contentValues.getAsString(COL_ENTITLEMENT_HASH);
        String asString23 = contentValues.getAsString(COL_APP_SCOPED_USER_ID);
        long longValue5 = contentValues.getAsLong(COL_RECENT_ACTIVITY).longValue();
        String asString24 = contentValues.getAsString(COL_COMFORT_RATING);
        int intValue3 = contentValues.getAsInteger(COL_UNSEEN).intValue();
        long longValue6 = contentValues.getAsLong(COL_MIN_RECOMMENDED_VERSION_CODE).longValue();
        long longValue7 = contentValues.getAsLong(COL_MIN_REQUIRED_VERSION_CODE).longValue();
        String asString25 = contentValues.getAsString(COL_MICROPHONE_USAGE);
        long longValue8 = contentValues.getAsLong(COL_TOTAL_ACTIVITY).longValue();
        long longValue9 = contentValues.getAsLong(COL_APK_SIZE).longValue();
        try {
            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(asString16, 0);
            j = (long) packageInfo.versionCode;
            str = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            j = LibraryDBContract.VERSION_NOT_INSTALLED;
            str = null;
        }
        List<InputDevice> convertSupportedInputDevicesStringToList = convertSupportedInputDevicesStringToList(asString21);
        HashSet hashSet = new HashSet();
        String asString26 = contentValues.getAsString(COL_IS_DEMO_OF);
        if (TextUtils.isEmpty(asString26)) {
            hashSet.add(COL_IS_DEMO_OF);
        }
        String asString27 = contentValues.getAsString(COL_SUPPORTED_PLATFORM);
        if (TextUtils.isEmpty(asString27)) {
            hashSet.add(COL_SUPPORTED_PLATFORM);
            fromString = SupportedPlatform.UNKNOWN;
        } else {
            fromString = SupportedPlatform.fromString(asString27);
        }
        String asString28 = contentValues.getAsString(COL_APP_MEDIA_CAPABILITIES);
        if (TextUtils.isEmpty(asString28)) {
            hashSet.add(COL_APP_MEDIA_CAPABILITIES);
            convertStringToAppMediaCapabilities = Collections.singletonList(AppMediaCapability.UNKNOWN);
        } else {
            convertStringToAppMediaCapabilities = convertStringToAppMediaCapabilities(asString28);
        }
        String asString29 = contentValues.getAsString(COL_HEAD_TRACKING);
        if (TextUtils.isEmpty(asString29)) {
            hashSet.add(COL_HEAD_TRACKING);
            fromString2 = HeadTracking.UNKNOWN;
        } else {
            fromString2 = HeadTracking.fromString(asString29);
        }
        String asString30 = contentValues.getAsString(COL_PLAY_MODES);
        if (TextUtils.isEmpty(asString30)) {
            hashSet.add(COL_PLAY_MODES);
            convertStringToPlayModes = Collections.singletonList(PlayMode.UNKNOWN);
        } else {
            convertStringToPlayModes = convertStringToPlayModes(asString30);
        }
        String asString31 = contentValues.getAsString(COL_CATEGORY);
        if (TextUtils.isEmpty(asString31)) {
            hashSet.add(COL_CATEGORY);
            fromString3 = Category.UNKNOWN;
        } else {
            fromString3 = Category.fromString(asString31);
        }
        String asString32 = contentValues.getAsString(COL_GRANT_REASON);
        GrantReason grantReason = GrantReason.UNKNOWN;
        if (TextUtils.isEmpty(asString32)) {
            hashSet.add(COL_GRANT_REASON);
        } else {
            grantReason = GrantReason.valueOf(asString32);
        }
        String str3 = COL_GRANT_TIME;
        Long asLong = contentValues.getAsLong(str3);
        long j2 = 0;
        if (asLong == null) {
            hashSet.add(str3);
            longValue = 0;
        } else {
            longValue = asLong.longValue();
        }
        Long asLong2 = contentValues.getAsLong(COL_GRANT_EXPIRATION);
        if (asLong == null) {
            hashSet.add(COL_GRANT_EXPIRATION);
        } else {
            j2 = asLong2.longValue();
        }
        if (!contentValues.containsKey(COL_CURRENT_LANGUAGE_PACK)) {
            hashSet.add(COL_CURRENT_LANGUAGE_PACK);
            asString = null;
        } else {
            asString = contentValues.getAsString(COL_CURRENT_LANGUAGE_PACK);
        }
        List<String> convertPermissionStringToList = convertPermissionStringToList(asString18);
        if (!contentValues.containsKey(COL_INTERNET_CONNECTION)) {
            hashSet.add(COL_INTERNET_CONNECTION);
            asString2 = null;
        } else {
            asString2 = contentValues.getAsString(COL_INTERNET_CONNECTION);
        }
        if (!contentValues.containsKey(COL_TRUSTED_BINARY_STATUS)) {
            hashSet.add(COL_TRUSTED_BINARY_STATUS);
            asString3 = null;
        } else {
            asString3 = contentValues.getAsString(COL_TRUSTED_BINARY_STATUS);
        }
        if (!contentValues.containsKey(COL_IS_CONCEPT)) {
            hashSet.add(COL_IS_CONCEPT);
            booleanValue = false;
        } else {
            booleanValue = contentValues.getAsBoolean(COL_IS_CONCEPT).booleanValue();
        }
        if (!contentValues.containsKey(COL_IS_TV_APP)) {
            hashSet.add(COL_IS_TV_APP);
            booleanValue2 = false;
        } else {
            booleanValue2 = contentValues.getAsBoolean(COL_IS_TV_APP).booleanValue();
        }
        if (!contentValues.containsKey(COL_IS_TEST)) {
            hashSet.add(COL_IS_TEST);
            booleanValue3 = false;
        } else {
            booleanValue3 = contentValues.getAsBoolean(COL_IS_TEST).booleanValue();
        }
        if (!contentValues.containsKey(COL_SYSTEM_UI_OVERLAY_MODE)) {
            hashSet.add(COL_SYSTEM_UI_OVERLAY_MODE);
            asString4 = null;
        } else {
            asString4 = contentValues.getAsString(COL_SYSTEM_UI_OVERLAY_MODE);
        }
        if (!contentValues.containsKey(COL_HERO_IMAGE)) {
            hashSet.add(COL_HERO_IMAGE);
            asString5 = null;
        } else {
            asString5 = contentValues.getAsString(COL_HERO_IMAGE);
        }
        if (!contentValues.containsKey(COL_AUTO_UPDATE_ENABLED)) {
            hashSet.add(COL_AUTO_UPDATE_ENABLED);
            booleanValue4 = false;
        } else {
            booleanValue4 = contentValues.getAsBoolean(COL_AUTO_UPDATE_ENABLED).booleanValue();
        }
        if (!contentValues.containsKey(COL_APPLICATION_ORGANIZATION_ID)) {
            hashSet.add(COL_APPLICATION_ORGANIZATION_ID);
            asString6 = null;
        } else {
            asString6 = contentValues.getAsString(COL_APPLICATION_ORGANIZATION_ID);
        }
        if (!contentValues.containsKey(COL_DUC_NON_COMPLIANT)) {
            hashSet.add(COL_DUC_NON_COMPLIANT);
            booleanValue5 = true;
        } else {
            booleanValue5 = contentValues.getAsBoolean(COL_DUC_NON_COMPLIANT).booleanValue();
        }
        List arrayList2 = new ArrayList();
        if (!contentValues.containsKey(COL_ACCESS_FEATURE_KEYS)) {
            hashSet.add(COL_ACCESS_FEATURE_KEYS);
        } else {
            arrayList2 = convertStringToAccessFeatureKeys(contentValues.getAsString(COL_ACCESS_FEATURE_KEYS));
        }
        if (!contentValues.containsKey(COL_ENVIRONMENT_SOURCE_ID)) {
            hashSet.add(COL_ENVIRONMENT_SOURCE_ID);
            asString7 = null;
        } else {
            asString7 = contentValues.getAsString(COL_ENVIRONMENT_SOURCE_ID);
        }
        if (!contentValues.containsKey(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME)) {
            hashSet.add(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME);
            asString8 = null;
        } else {
            asString8 = contentValues.getAsString(COL_ENVIRONMENT_SOURCE_DISPLAY_NAME);
        }
        AppStatus valueOf = AppStatus.valueOf(asString19);
        CloudStorageStatus valueOf2 = CloudStorageStatus.valueOf(asString20);
        ComfortRating fromString4 = ComfortRating.fromString(asString24);
        Map<Image.ImageName, Image> createImages = ImagesBuilder.createImages(asString12, asString13, asString14, asString15, asString5);
        boolean z = false;
        if (intValue2 == 1) {
            z = true;
        }
        MicrophoneUsage valueOf3 = MicrophoneUsage.valueOf(asString25);
        boolean z2 = false;
        if (intValue3 == 1) {
            z2 = true;
        }
        return new ContentValueUnpack(new App(asString9, asString16, valueOf, valueOf2, asString10, fromString4, createImages, longValue3, longValue4, j, str, longValue2, asString17, intValue, convertPermissionStringToList, convertSupportedInputDevicesStringToList, z, asString22, asString23, asString11, asString6, valueOf3, z2, longValue5, longValue8, longValue6, longValue7, asString26, fromString, convertStringToAppMediaCapabilities, convertStringToPlayModes, fromString3, fromString2, grantReason, longValue, j2, longValue9, asString, asString2, asString3, booleanValue, booleanValue2, booleanValue3, asString4, booleanValue4, booleanValue5, arrayList2, asString7, asString8), hashSet);
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
            builder.cloudStorageStatus = CloudStorageStatus.valueOf(contentValues.getAsString(COL_CLOUD_STORAGE_STATUS));
        }
        if (contentValues.containsKey(COL_DOWNLOADED_SIZE)) {
            builder.downloadedSizeBytes = contentValues.getAsLong(COL_DOWNLOADED_SIZE).longValue();
        }
        if (contentValues.containsKey(COL_INSTALLATION_SIZE)) {
            builder.downloadSizeBytes = contentValues.getAsLong(COL_INSTALLATION_SIZE).longValue();
        }
        if (contentValues.containsKey(COL_APP_SCOPED_USER_ID)) {
            builder.appScopedUserId = contentValues.getAsString(COL_APP_SCOPED_USER_ID);
        }
        if (contentValues.containsKey(COL_UNSEEN)) {
            boolean z = true;
            if (contentValues.getAsInteger(COL_UNSEEN).intValue() != 1) {
                z = false;
            }
            builder.isUnseen = z;
        }
        if (contentValues.containsKey(COL_RECENT_ACTIVITY)) {
            builder.recentActivityMs = contentValues.getAsLong(COL_RECENT_ACTIVITY).longValue();
        }
        if (contentValues.containsKey(COL_TOTAL_ACTIVITY)) {
            builder.totalActivityMs = contentValues.getAsLong(COL_TOTAL_ACTIVITY).longValue();
        }
        if (contentValues.containsKey(COL_CURRENT_LANGUAGE_PACK)) {
            builder.currentLanguagePack = contentValues.getAsString(COL_CURRENT_LANGUAGE_PACK);
        }
        if (contentValues.containsKey(COL_TRUSTED_BINARY_STATUS)) {
            builder.trustedBinaryStatus = contentValues.getAsString(COL_TRUSTED_BINARY_STATUS);
        }
        return builder.build();
    }

    public int getAndRemoveContentValuePackingVersion(ContentValues contentValues) {
        if (!contentValues.containsKey(CONTENT_VALUE_VERSION)) {
            return 1;
        }
        int intValue = contentValues.getAsInteger(CONTENT_VALUE_VERSION).intValue();
        contentValues.remove(CONTENT_VALUE_VERSION);
        return intValue;
    }

    public AppStatus getDefaultStatus(App app) {
        String str = app.packageName;
        if (str.equals(app.id)) {
            return AppStatus.INCOMPATIBLE;
        }
        try {
            this.mPackageManager.getPackageInfo(str, 0);
            return AppStatus.INSTALLED;
        } catch (PackageManager.NameNotFoundException unused) {
            return AppStatus.ENTITLED;
        }
    }

    public boolean isNotificationWorthy(App app, App app2) {
        App.Builder builder = new App.Builder(app);
        builder.entitlementHash = app2.entitlementHash;
        return !isEqual(app2, builder.build());
    }

    public AppConverter(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    public void addContentValuePackingVersion(ContentValues contentValues, int i) {
        contentValues.put(CONTENT_VALUE_VERSION, Integer.valueOf(i));
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
}
