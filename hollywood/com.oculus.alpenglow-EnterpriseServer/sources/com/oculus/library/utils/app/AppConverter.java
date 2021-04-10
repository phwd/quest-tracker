package com.oculus.library.utils.app;

import android.content.pm.PackageManager;
import com.oculus.library.database.contract.LibraryDBContract;

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

    public AppConverter(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:120:0x04b1, code lost:
        if (r91.getInt(r4) == 1) goto L_0x04b3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.oculus.library.model.App[] A00(android.database.Cursor r91) {
        /*
        // Method dump skipped, instructions count: 1482
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.utils.app.AppConverter.A00(android.database.Cursor):com.oculus.library.model.App[]");
    }
}
