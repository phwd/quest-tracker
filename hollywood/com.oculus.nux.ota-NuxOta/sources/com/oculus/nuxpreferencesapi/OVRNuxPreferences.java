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

        public ClientPreferenceData(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, Status status, long j, String str2, int i, String str3, boolean z15, boolean z16, String str4, boolean z17, String str5, String str6) {
            this.defaultBrowser = str;
            this.hasSeenHandTrackingNux = z;
            this.hasFinishedFullVrNux = z2;
            this.hasFinishedNux = z3;
            this.hasSeenConfirmQuit = z4;
            this.hasSeenConfirmQuitNotif = z5;
            this.hasSeenFocus = z6;
            this.hasSeenInternetBrowserPrompt = z7;
            this.hasSeenNux = z8;
            this.hasSeenTutorial = z9;
            this.hasSeenPartyCallsNUX = z10;
            this.hasSeenSavedPrompt = z11;
            this.hasSeenLongHSW = z12;
            this.hasOptedoutOfHSW = z13;
            this.hasOptedOutOfMalibuRecenter = z14;
            this.highPriorityAppsDownloadStatus = status;
            this.highPriorityAppsDownloadWaitTime = j;
            this.nuxResult = str2;
            this.nuxSeenCount = i;
            this.nuxState = str3;
            this.hasFinishedIPDAdjust = z15;
            this.hasFinishedMontereyNux = z16;
            this.userID = str4;
            this.hasDocked = z17;
            this.rolloutToken = str5;
            this.highPriorityAppDownloadPackages = str6;
        }
    }

    public static ClientPreferenceData getClientPreferenceData(Context context) {
        Cursor query = context.getContentResolver().query(OculusContent.Profile.CONTENT_URI, null, null, null, null);
        if (query == null) {
            return new ClientPreferenceData(null, false, false, false, false, false, false, false, false, false, false, false, false, false, false, Status.UNSET, 0, null, 0, null, false, false, null, false, null, null);
        }
        query.moveToFirst();
        String stringWithDefault = getStringWithDefault(query, query.getColumnIndex("default_browser"), null);
        String stringWithDefault2 = getStringWithDefault(query, query.getColumnIndex("nux_result"), null);
        int intWithDefault = getIntWithDefault(query, query.getColumnIndex("nux_seen_count"), 0);
        String stringWithDefault3 = getStringWithDefault(query, query.getColumnIndex("nux_state"), null);
        String stringWithDefault4 = getStringWithDefault(query, query.getColumnIndex("id"), null);
        boolean booleanWithDefault = getBooleanWithDefault(query, query.getColumnIndex("has_seen_confirm_quit"), false);
        boolean booleanWithDefault2 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_confirm_quit_notif"), false);
        boolean booleanWithDefault3 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_focus"), false);
        boolean booleanWithDefault4 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_internet_browser_prompt"), false);
        boolean booleanWithDefault5 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_tutorial_prompt"), false);
        boolean booleanWithDefault6 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_party_calls_nux"), false);
        boolean booleanWithDefault7 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_saved_prompt"), false);
        boolean booleanWithDefault8 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_long_hsw"), false);
        boolean booleanWithDefault9 = getBooleanWithDefault(query, query.getColumnIndex("has_opted_out_hsw"), false);
        boolean booleanWithDefault10 = getBooleanWithDefault(query, query.getColumnIndex("has_opted_out_of_malibu_recenter"), false);
        Status statusWithDefault = getStatusWithDefault(query, query.getColumnIndex("high_priority_apps_download_status"), Status.UNSET);
        long longWithDefault = getLongWithDefault(query, query.getColumnIndex("high_priority_apps_download_waittime"), 0);
        boolean booleanWithDefault11 = getBooleanWithDefault(query, query.getColumnIndex("has_docked"), false);
        boolean booleanWithDefault12 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_hand_tracking_nux"), false);
        boolean booleanWithDefault13 = getBooleanWithDefault(query, query.getColumnIndex("has_finished_full_vr_nux"), false);
        boolean booleanWithDefault14 = getBooleanWithDefault(query, query.getColumnIndex("has_finished_nux"), false);
        boolean booleanWithDefault15 = getBooleanWithDefault(query, query.getColumnIndex("has_seen_nux"), false);
        boolean booleanWithDefault16 = getBooleanWithDefault(query, query.getColumnIndex("has_finished_ipd_adjust"), false);
        boolean booleanWithDefault17 = getBooleanWithDefault(query, query.getColumnIndex("has_finished_monterey_nux"), false);
        String stringWithDefault5 = getStringWithDefault(query, query.getColumnIndex("rollout_token"), null);
        String stringWithDefault6 = getStringWithDefault(query, query.getColumnIndex("high_priority_apps_pkgs"), null);
        query.close();
        return new ClientPreferenceData(stringWithDefault, booleanWithDefault12, booleanWithDefault13, booleanWithDefault14, booleanWithDefault, booleanWithDefault2, booleanWithDefault3, booleanWithDefault4, booleanWithDefault15, booleanWithDefault5, booleanWithDefault6, booleanWithDefault7, booleanWithDefault8, booleanWithDefault9, booleanWithDefault10, statusWithDefault, longWithDefault, stringWithDefault2, intWithDefault, stringWithDefault3, booleanWithDefault16, booleanWithDefault17, stringWithDefault4, booleanWithDefault11, stringWithDefault5, stringWithDefault6);
    }

    private static boolean getBooleanWithDefault(Cursor cursor, int i, boolean z) {
        if (i < 0) {
            return z;
        }
        return cursor.getInt(i) > 0;
    }

    private static int getIntWithDefault(Cursor cursor, int i, int i2) {
        return i < 0 ? i2 : cursor.getInt(i);
    }

    private static long getLongWithDefault(Cursor cursor, int i, long j) {
        return i < 0 ? j : cursor.getLong(i);
    }

    private static String getStringWithDefault(Cursor cursor, int i, String str) {
        return i < 0 ? str : cursor.getString(i);
    }

    private static Status getStatusWithDefault(Cursor cursor, int i, Status status) {
        return i < 0 ? status : Status.values()[cursor.getInt(i)];
    }
}
