package com.oculus.panelapp.anytimeui.v2.tablet.apps.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryPlatform;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibrarySorter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LibraryStateHelper {
    private static final String APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD = "app_migration_apps_for_later_download";
    private static final String APP_MIGRATION_DIALOG_ACTED = "app_migration_dialog_acted";
    private static final String APP_MIGRATION_DIALOG_SHOWN = "app_migration_dialog_shown";
    private static final String BOOT_TIME_ID = "boot_time";
    private static final String FILTER_STATE_ID = "filter_state";
    private static final String GO_GEAR_VR_BANNER_DISMISSED_STATE_ID = "go_gear_vr_banner_dismissed_state";
    private static final String HMD_OFF_TIMESTAMP_ID = "hmd_off_timestamp";
    public static final long INACTIVITY_LIBRARY_STATE_RESET_THRESHOLD = 3600000;
    public static final long INACTIVITY_LIBRARY_STATE_RESET_THRESHOLD_AUTOMATION = 5000;
    private static final String IS_FIRST_TIME_ID = "is_first_time_v2";
    private static final String IS_UNSEEN_ID = "_is_unseen";
    private static final String LIBRARY_SHARED_PREFERENCES_FILE = "library_v2_state";
    private static final String PERMISSIONS_ACCEPTED = "_permissions_accepted";
    private static final String PLATFORM_STATE_ID = "platform_state";
    public static final String RECENT_ACTIVITY_ID = "_recent_activity";
    private static final String SCROLL_POSITION_STATE_ID = "scroll_position_state";
    private static final String SORTER_STATE_ID = "sort_state";
    private static final String TAG = LoggingUtil.tag(LibraryStateHelper.class);
    private SharedPreferences mSharedPreferences;

    public LibraryStateHelper(Context context) {
        this.mSharedPreferences = getSharedPreferences(context);
    }

    public boolean loadFakeAppIsUnseenState(String str, boolean z) {
        String str2 = str + IS_UNSEEN_ID;
        if (this.mSharedPreferences.contains(str2)) {
            return this.mSharedPreferences.getBoolean(str2, z);
        }
        boolean isUnseenInitialValue = getIsUnseenInitialValue(str);
        this.mSharedPreferences.edit().putBoolean(str2, isUnseenInitialValue).apply();
        Log.d(TAG, String.format("Creating state record for %s, %b", str2, Boolean.valueOf(isUnseenInitialValue)));
        return isUnseenInitialValue;
    }

    public long loadFakeAppRecentActivityState(String str, long j) {
        String str2 = str + RECENT_ACTIVITY_ID;
        if (this.mSharedPreferences.contains(str2)) {
            return this.mSharedPreferences.getLong(str2, j);
        }
        this.mSharedPreferences.edit().putLong(str2, j).apply();
        Log.d(TAG, String.format("Creating state record for %s, %d", str2, Long.valueOf(j)));
        return j;
    }

    public void saveFakeAppState(String str, boolean z, long j) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        SharedPreferences.Editor putBoolean = edit.putBoolean(str + IS_UNSEEN_ID, z);
        putBoolean.putLong(str + RECENT_ACTIVITY_ID, j).apply();
        Log.d(TAG, String.format("Updating state record for %s: isUnseen %b, recentActivity %d", str, Boolean.valueOf(z), Long.valueOf(j)));
    }

    public void saveDefaultDropdownsState() {
        saveDropdownsState(LibraryPlatform.ANDROID_6DOF, LibrarySorter.MOST_RECENT, LibraryFilter.ALL);
    }

    public void saveDropdownsState(LibraryPlatform libraryPlatform, LibrarySorter librarySorter, LibraryFilter libraryFilter) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        if (libraryPlatform == null) {
            libraryPlatform = LibraryPlatform.getDefault();
        }
        SharedPreferences.Editor putInt = edit.putInt(PLATFORM_STATE_ID, libraryPlatform.getId());
        if (librarySorter == null) {
            librarySorter = LibrarySorter.getDefault();
        }
        putInt.putInt(SORTER_STATE_ID, librarySorter.getId()).putInt(FILTER_STATE_ID, libraryFilter == null ? LibraryFilter.getDefault().getId() : libraryFilter.getId()).apply();
    }

    public LibraryPlatform loadLastPlatformState() {
        LibraryPlatform valueOf = LibraryPlatform.valueOf(this.mSharedPreferences.getInt(PLATFORM_STATE_ID, LibraryPlatform.getDefault().getId()));
        return valueOf == null ? LibraryPlatform.getDefault() : valueOf;
    }

    public LibrarySorter loadLastSorterState() {
        LibrarySorter valueOf = LibrarySorter.valueOf(this.mSharedPreferences.getInt(SORTER_STATE_ID, LibrarySorter.getDefault().getId()));
        return valueOf == null ? LibrarySorter.getDefault() : valueOf;
    }

    public LibraryFilter loadLastFilterState() {
        LibraryFilter valueOf = LibraryFilter.valueOf(this.mSharedPreferences.getInt(FILTER_STATE_ID, LibraryFilter.getDefault().getId()));
        return valueOf == null ? LibraryFilter.getDefault() : valueOf;
    }

    public void saveDefaultScrollPositionState() {
        saveScrollPositionState(0);
    }

    public void saveScrollPositionState(int i) {
        Log.d(TAG, String.format("Storing scroll position: %d", Integer.valueOf(i)));
        this.mSharedPreferences.edit().putInt(SCROLL_POSITION_STATE_ID, i).apply();
    }

    public int loadScrollPositionState() {
        return this.mSharedPreferences.getInt(SCROLL_POSITION_STATE_ID, -1);
    }

    public void saveBootTime(long j) {
        this.mSharedPreferences.edit().putLong(BOOT_TIME_ID, j).apply();
    }

    public long loadBootTime() {
        return this.mSharedPreferences.getLong(BOOT_TIME_ID, 0);
    }

    public long loadHMDOffTimestamp() {
        return this.mSharedPreferences.getLong(HMD_OFF_TIMESTAMP_ID, 0);
    }

    public void saveGoGearVrBannerDismissedState(boolean z) {
        this.mSharedPreferences.edit().putBoolean(GO_GEAR_VR_BANNER_DISMISSED_STATE_ID, z).apply();
    }

    public boolean loadGoGearVrBannerDismissedState() {
        return this.mSharedPreferences.getBoolean(GO_GEAR_VR_BANNER_DISMISSED_STATE_ID, false);
    }

    public boolean loadIsFirstTime() {
        if (!this.mSharedPreferences.getBoolean(IS_FIRST_TIME_ID, true)) {
            return false;
        }
        this.mSharedPreferences.edit().putBoolean(IS_FIRST_TIME_ID, false).apply();
        return true;
    }

    private boolean getIsUnseenInitialValue(String str) {
        return !str.equals(LibraryFakeAppUtils.FIT_AND_FOCUS_ENTITLEMENT_PACKAGE_NAME) && !str.equals(LibraryFakeAppUtils.HANDS_TUTORIAL_ENTITLEMENT_PACKAGE_NAME);
    }

    public boolean loadAppMigrationDialogShownState() {
        return this.mSharedPreferences.getBoolean(APP_MIGRATION_DIALOG_SHOWN, false);
    }

    public void saveAppMigrationDialogShownState() {
        this.mSharedPreferences.edit().putBoolean(APP_MIGRATION_DIALOG_SHOWN, true).apply();
    }

    public boolean loadAppMigrationDialogActionState() {
        return this.mSharedPreferences.getBoolean(APP_MIGRATION_DIALOG_ACTED, false);
    }

    public void saveAppMigrationDialogActedState() {
        this.mSharedPreferences.edit().putBoolean(APP_MIGRATION_DIALOG_ACTED, true).apply();
    }

    public boolean isAppMigrationDialogBlocked() {
        boolean contains = this.mSharedPreferences.contains("hands_guide_entitlement_is_unseen");
        String str = TAG;
        Object[] objArr = new Object[1];
        objArr[0] = contains ? "is" : "is not";
        Log.d(str, String.format("App Migration Dialog %s blocked.", objArr));
        return contains;
    }

    public void saveMigrationAppsForLaterDownload(List<String> list) {
        this.mSharedPreferences.edit().putStringSet(APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD, new HashSet(list)).apply();
    }

    public boolean hasMigrationAppsForLaterDownload() {
        return this.mSharedPreferences.contains(APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD);
    }

    public List<String> loadMigrationAppsForLaterDownload() {
        if (!this.mSharedPreferences.contains(APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSharedPreferences.getStringSet(APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD, null));
        this.mSharedPreferences.edit().remove(APP_MIGRATION_APPS_FOR_LATER_DOWNLOAD).apply();
        return arrayList;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(LIBRARY_SHARED_PREFERENCES_FILE, 0);
    }

    public static void saveHMDOffTimestamp(Context context, long j) {
        getSharedPreferences(context).edit().putLong(HMD_OFF_TIMESTAMP_ID, j).apply();
    }

    public static long loadHMDOffTimestamp(Context context) {
        return getSharedPreferences(context).getLong(HMD_OFF_TIMESTAMP_ID, 0);
    }

    public static LibraryFilter loadLastFilterState(Context context) {
        LibraryFilter valueOf = LibraryFilter.valueOf(getSharedPreferences(context).getInt(FILTER_STATE_ID, LibraryFilter.getDefault().getId()));
        return valueOf == null ? LibraryFilter.getDefault() : valueOf;
    }

    public static void saveDefaultDropdownsState(Context context) {
        getSharedPreferences(context).edit().putInt(PLATFORM_STATE_ID, LibraryPlatform.getDefault().getId()).putInt(SORTER_STATE_ID, LibrarySorter.getDefault().getId()).putInt(FILTER_STATE_ID, LibraryFilter.getDefault().getId()).apply();
    }

    public static void saveDefaultScrollPositionState(Context context) {
        getSharedPreferences(context).edit().putInt(SCROLL_POSITION_STATE_ID, 0).apply();
    }
}
