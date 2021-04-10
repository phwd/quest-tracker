package com.oculus.nuxpreferencesapi;

import android.content.Context;
import android.database.Cursor;
import com.oculus.provider.OculusContent;
import java.util.ArrayList;
import java.util.HashMap;

public class OVRNuxPreferences {
    private static final HashMap<Object, ArrayList<Object>> mListeners = new HashMap<>();

    public enum Status {
        UNSET,
        COMPLETED,
        FAILED
    }

    public static class ClientPreferenceData {
        public final String defaultBrowser;
        public final boolean hasDocked;
        public final boolean hasFinishedFullVrNux;
        public final boolean hasFinishedIPDAdjust;
        public final boolean hasFinishedMontereyNux;
        public final boolean hasFinishedNux;
        public final boolean hasOptedOutOfMalibuRecenter;
        public final boolean hasOptedoutOfHSW;
        public final boolean hasSeenConfirmQuit;
        public final boolean hasSeenConfirmQuitNotif;
        public final boolean hasSeenFocus;
        public final boolean hasSeenHandTrackingNux;
        public final boolean hasSeenInternetBrowserPrompt;
        public final boolean hasSeenLongHSW;
        public final boolean hasSeenNux;
        public final boolean hasSeenPartyCallsNUX;
        public final boolean hasSeenSavedPrompt;
        public final boolean hasSeenTutorial;
        public final String highPriorityAppDownloadPackages;
        public final Status highPriorityAppsDownloadStatus;
        public final long highPriorityAppsDownloadWaitTime;
        public final String nuxResult;
        public final int nuxSeenCount;
        public final String nuxState;
        public final String rolloutToken;
        public final String userID;

        public ClientPreferenceData(String defaultBrowser2, boolean hasSeenHandTrackingNux2, boolean hasFinishedFullVrNux2, boolean hasFinishedNux2, boolean hasSeenConfirmQuit2, boolean hasSeenConfirmQuitNotif2, boolean hasSeenFocus2, boolean hasSeenInternetBrowserPrompt2, boolean hasSeenNux2, boolean hasSeenTutorial2, boolean hasSeenPartyCallsNUX2, boolean hasSeenSavedPrompt2, boolean hasSeenLongHSW2, boolean hasOptedoutOfHSW2, boolean hasOptedOutOfMalibuRecenter2, Status highPriorityAppsDownloadStatus2, long highPriorityAppsDownloadWaitTime2, String nuxResult2, int nuxSeenCount2, String nuxState2, boolean hasFinishedIPDAdjust2, boolean hasFinishedMontereyNux2, String userID2, boolean hasDocked2, String rolloutToken2, String highPriorityAppDownloadPackages2) {
            this.defaultBrowser = defaultBrowser2;
            this.hasSeenHandTrackingNux = hasSeenHandTrackingNux2;
            this.hasFinishedFullVrNux = hasFinishedFullVrNux2;
            this.hasFinishedNux = hasFinishedNux2;
            this.hasSeenConfirmQuit = hasSeenConfirmQuit2;
            this.hasSeenConfirmQuitNotif = hasSeenConfirmQuitNotif2;
            this.hasSeenFocus = hasSeenFocus2;
            this.hasSeenInternetBrowserPrompt = hasSeenInternetBrowserPrompt2;
            this.hasSeenNux = hasSeenNux2;
            this.hasSeenTutorial = hasSeenTutorial2;
            this.hasSeenPartyCallsNUX = hasSeenPartyCallsNUX2;
            this.hasSeenSavedPrompt = hasSeenSavedPrompt2;
            this.hasSeenLongHSW = hasSeenLongHSW2;
            this.hasOptedoutOfHSW = hasOptedoutOfHSW2;
            this.hasOptedOutOfMalibuRecenter = hasOptedOutOfMalibuRecenter2;
            this.highPriorityAppsDownloadStatus = highPriorityAppsDownloadStatus2;
            this.highPriorityAppsDownloadWaitTime = highPriorityAppsDownloadWaitTime2;
            this.nuxResult = nuxResult2;
            this.nuxSeenCount = nuxSeenCount2;
            this.nuxState = nuxState2;
            this.hasFinishedIPDAdjust = hasFinishedIPDAdjust2;
            this.hasFinishedMontereyNux = hasFinishedMontereyNux2;
            this.userID = userID2;
            this.hasDocked = hasDocked2;
            this.rolloutToken = rolloutToken2;
            this.highPriorityAppDownloadPackages = highPriorityAppDownloadPackages2;
        }
    }

    public static ClientPreferenceData getClientPreferenceData(Context context) {
        Cursor cursor = context.getContentResolver().query(OculusContent.Profile.CONTENT_URI, null, null, null, null);
        if (cursor == null) {
            return new ClientPreferenceData(null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, Status.UNSET, 0, null, 0, null, false, false, null, false, null, null);
        }
        cursor.moveToFirst();
        String defaultBrowserChoice = getStringWithDefault(cursor, cursor.getColumnIndex("default_browser"), null);
        String nuxResultChoice = getStringWithDefault(cursor, cursor.getColumnIndex("nux_result"), null);
        int nuxSeenCountInt = getIntWithDefault(cursor, cursor.getColumnIndex("nux_seen_count"), 0);
        String nuxStateChoice = getStringWithDefault(cursor, cursor.getColumnIndex("nux_state"), null);
        String id = getStringWithDefault(cursor, cursor.getColumnIndex("id"), null);
        boolean confirmQuitBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_confirm_quit"), false);
        boolean confirmQuitNotifBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_confirm_quit_notif"), false);
        boolean focusBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_focus"), false);
        boolean browserPromptBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_internet_browser_prompt"), false);
        boolean tutorialBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_tutorial_prompt"), false);
        boolean partyCallsNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_party_calls_nux"), false);
        boolean savedPromptBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_saved_prompt"), false);
        boolean longHSWBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_long_hsw"), false);
        boolean hswOptOutBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_opted_out_hsw"), false);
        boolean malibuRecenterOptOutBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_opted_out_of_malibu_recenter"), false);
        Status highPriorityAppsDownloadStatus = getStatusWithDefault(cursor, cursor.getColumnIndex("high_priority_apps_download_status"), Status.UNSET);
        long highPriorityAppsDownloadWaitTime = getLongWithDefault(cursor, cursor.getColumnIndex("high_priority_apps_download_waittime"), 0);
        boolean hasDockedBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_docked"), false);
        boolean hasSeenHandTrackingNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_hand_tracking_nux"), false);
        boolean hasFinishedFullVrNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_finished_full_vr_nux"), false);
        boolean hasFinishedNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_finished_nux"), false);
        boolean hasSeenNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_seen_nux"), false);
        boolean hasFinishedIPDAdjustBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_finished_ipd_adjust"), false);
        boolean hasFinishedMontereyNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex("has_finished_monterey_nux"), false);
        String rolloutToken = getStringWithDefault(cursor, cursor.getColumnIndex("rollout_token"), null);
        String highPriorityAppDownloadPackages = getStringWithDefault(cursor, cursor.getColumnIndex("high_priority_apps_pkgs"), null);
        cursor.close();
        return new ClientPreferenceData(defaultBrowserChoice, hasSeenHandTrackingNuxBool, hasFinishedFullVrNuxBool, hasFinishedNuxBool, confirmQuitBool, confirmQuitNotifBool, focusBool, browserPromptBool, hasSeenNuxBool, tutorialBool, partyCallsNuxBool, savedPromptBool, longHSWBool, hswOptOutBool, malibuRecenterOptOutBool, highPriorityAppsDownloadStatus, highPriorityAppsDownloadWaitTime, nuxResultChoice, nuxSeenCountInt, nuxStateChoice, hasFinishedIPDAdjustBool, hasFinishedMontereyNuxBool, id, hasDockedBool, rolloutToken, highPriorityAppDownloadPackages);
    }

    private static boolean getBooleanWithDefault(Cursor cursor, int colId, boolean defaultValue) {
        if (colId < 0) {
            return defaultValue;
        }
        return cursor.getInt(colId) > 0;
    }

    private static int getIntWithDefault(Cursor cursor, int colId, int defaultValue) {
        return colId < 0 ? defaultValue : cursor.getInt(colId);
    }

    private static long getLongWithDefault(Cursor cursor, int colId, long defaultValue) {
        return colId < 0 ? defaultValue : cursor.getLong(colId);
    }

    private static String getStringWithDefault(Cursor cursor, int colId, String defaultValue) {
        return colId < 0 ? defaultValue : cursor.getString(colId);
    }

    private static Status getStatusWithDefault(Cursor cursor, int colId, Status defaultStatus) {
        return colId < 0 ? defaultStatus : Status.values()[cursor.getInt(colId)];
    }
}
