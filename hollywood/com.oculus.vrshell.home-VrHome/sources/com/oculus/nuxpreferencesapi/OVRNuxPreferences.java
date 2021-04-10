package com.oculus.nuxpreferencesapi;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import com.oculus.provider.OculusContent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OVRNuxPreferences {
    public static final long DEFAULT_DOWNLOAD_WAIT_TIME = 0;
    private static final HashMap<OnChangeListener, ArrayList<NuxPrefContentObserver>> mListeners = new HashMap<>();

    public interface OnChangeListener {
        void onChange();
    }

    public enum Status {
        UNSET,
        COMPLETED,
        FAILED
    }

    public static void unregisterNuxPrefListener(Context context, OnChangeListener listener) {
        ContentResolver contentResolver = context.getContentResolver();
        List<NuxPrefContentObserver> contentObservers = mListeners.remove(listener);
        if (contentObservers != null) {
            for (NuxPrefContentObserver contentObserver : contentObservers) {
                contentResolver.unregisterContentObserver(contentObserver);
            }
        }
    }

    public static void registerNuxPrefChangeListener(Context context, String NuxPref, OnChangeListener listener) {
        registerObserverForUri(context, OculusContent.Profile.getUriFromNuxPref(NuxPref), listener);
    }

    private static void registerObserverForUri(Context context, Uri uri, OnChangeListener listener) {
        ArrayList<NuxPrefContentObserver> contentObservers = mListeners.get(listener);
        if (contentObservers == null) {
            contentObservers = new ArrayList<>();
            mListeners.put(listener, contentObservers);
        }
        Iterator<NuxPrefContentObserver> it = contentObservers.iterator();
        while (it.hasNext()) {
            if (it.next().mUri.equals(uri)) {
                return;
            }
        }
        NuxPrefContentObserver contentObserver = new NuxPrefContentObserver(uri, listener);
        context.getContentResolver().registerContentObserver(uri, true, contentObserver);
        contentObservers.add(contentObserver);
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
        String defaultBrowserChoice = getStringWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.DEFAULT_BROWSER), null);
        String nuxResultChoice = getStringWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.NUX_RESULT), null);
        int nuxSeenCountInt = getIntWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.NUX_SEEN_COUNT), 0);
        String nuxStateChoice = getStringWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.NUX_STATE), null);
        String id = getStringWithDefault(cursor, cursor.getColumnIndex("id"), null);
        boolean confirmQuitBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT), false);
        boolean confirmQuitNotifBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT_NOTIF), false);
        boolean focusBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_FOCUS), false);
        boolean browserPromptBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_INTERNET_BROWSER_PROMPT), false);
        boolean tutorialBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_TUTORIAL_PROMPT), false);
        boolean partyCallsNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_PARTY_CALLS_NUX), false);
        boolean savedPromptBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_SAVED_PROMPT), false);
        boolean longHSWBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_LONG_HSW), false);
        boolean hswOptOutBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_HSW), false);
        boolean malibuRecenterOptOutBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_OF_MALIBU_RECENTER), false);
        Status highPriorityAppsDownloadStatus = getStatusWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS), Status.UNSET);
        long highPriorityAppsDownloadWaitTime = getLongWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME), 0);
        boolean hasDockedBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_DOCKED), false);
        boolean hasSeenHandTrackingNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_HAND_TRACKING_NUX), false);
        boolean hasFinishedFullVrNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_FINISHED_FULL_VR_NUX), false);
        boolean hasFinishedNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_FINISHED_NUX), false);
        boolean hasSeenNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_SEEN_NUX), false);
        boolean hasFinishedIPDAdjustBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_FINISHED_IPD_ADJUST), false);
        boolean hasFinishedMontereyNuxBool = getBooleanWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HAS_FINISHED_MONTEREY_NUX), false);
        String rolloutToken = getStringWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.ROLLOUT_TOKEN), null);
        String highPriorityAppDownloadPackages = getStringWithDefault(cursor, cursor.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES), null);
        cursor.close();
        return new ClientPreferenceData(defaultBrowserChoice, hasSeenHandTrackingNuxBool, hasFinishedFullVrNuxBool, hasFinishedNuxBool, confirmQuitBool, confirmQuitNotifBool, focusBool, browserPromptBool, hasSeenNuxBool, tutorialBool, partyCallsNuxBool, savedPromptBool, longHSWBool, hswOptOutBool, malibuRecenterOptOutBool, highPriorityAppsDownloadStatus, highPriorityAppsDownloadWaitTime, nuxResultChoice, nuxSeenCountInt, nuxStateChoice, hasFinishedIPDAdjustBool, hasFinishedMontereyNuxBool, id, hasDockedBool, rolloutToken, highPriorityAppDownloadPackages);
    }

    public static void setDefaultBrowser(Context context, String browser) {
        updateStringPreference(context, OculusContent.Profile.DEFAULT_BROWSER, browser);
    }

    public static void setHasSeenHandTrackingNux(Context context, boolean seen) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_HAND_TRACKING_NUX, seen);
    }

    public static void setNuxResult(Context context, String result) {
        updateStringPreference(context, OculusContent.Profile.NUX_RESULT, result);
    }

    public static void setNuxState(Context context, String state) {
        updateStringPreference(context, OculusContent.Profile.NUX_STATE, state);
    }

    public static void setNuxSeenCount(Context context, int count) {
        updateIntPreference(context, OculusContent.Profile.NUX_SEEN_COUNT, count);
    }

    public static void markHasFinishedFullVrNux(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_FULL_VR_NUX, true);
    }

    public static void markHasNuxFinished(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_NUX, true);
    }

    public static void markNuxSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_NUX, true);
    }

    public static void markNuxNotSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_NUX, false);
    }

    public static void markConfirmQuitSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT, true);
    }

    public static void markConfirmQuitNotifSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT_NOTIF, true);
    }

    public static void markFocusSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_FOCUS, true);
    }

    public static void markInternetBrowserPromptSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_INTERNET_BROWSER_PROMPT, true);
    }

    public static void markNuxNotFinished(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_NUX, false);
    }

    public static void markSavedPromptSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_SAVED_PROMPT, true);
    }

    public static void markLongHSWSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_LONG_HSW, true);
    }

    public static void setOptedOutofHSW(Context context, boolean optOut) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_OPTED_OUT_HSW, optOut);
    }

    public static void setHighPriorityAppsDownloadStatus(Context context, Status downloadStatus) {
        updateIntPreference(context, OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS, downloadStatus.ordinal());
    }

    public static void setHighPriorityAppsDownloadWaitTime(Context context, long waitTimeInMilliSeconds) {
        updateLongPreference(context, OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME, waitTimeInMilliSeconds);
    }

    public static void setHighPriorityAppDownloadPackages(Context context, Collection<String> packageNames) {
        if (packageNames.isEmpty()) {
            updateStringPreference(context, OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES, "");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String packageName : packageNames) {
            builder.append(packageName).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        updateStringPreference(context, OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES, builder.toString());
    }

    public static void markHasOptedOutOfMalibuRecenter(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_OPTED_OUT_OF_MALIBU_RECENTER, true);
    }

    public static void markTutorialPromptSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_TUTORIAL_PROMPT, true);
    }

    public static void markPartyCallsNUXSeen(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_PARTY_CALLS_NUX, true);
    }

    public static void markDocked(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_DOCKED, true);
    }

    public static void markFinishedIPDAdjust(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_IPD_ADJUST, true);
    }

    public static void markNotFinishedIPDAdjust(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_IPD_ADJUST, false);
    }

    public static void markFinishedMontereyNuxIndex(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_MONTEREY_NUX, true);
    }

    public static void markNotFinishedMontereyNuxIndex(Context context) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_FINISHED_MONTEREY_NUX, false);
    }

    private static void updateStringPreference(Context context, String preferenceColumn, String value) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createStringContentValues(preferenceColumn, value), null, null);
    }

    private static ContentValues createStringContentValues(String column, String updated) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, updated);
        return contentValues;
    }

    private static void updateIntPreference(Context context, String preferenceColumn, int value) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createIntContentValues(preferenceColumn, value), null, null);
    }

    private static ContentValues createIntContentValues(String column, int updated) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, Integer.valueOf(updated));
        return contentValues;
    }

    private static void updateLongPreference(Context context, String preferenceColumn, long value) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createLongContentValues(preferenceColumn, value), null, null);
    }

    private static ContentValues createLongContentValues(String column, long updated) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, Long.valueOf(updated));
        return contentValues;
    }

    private static void updateBooleanPreference(Context context, String preferenceColumn, boolean value) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createContentValues(preferenceColumn, value), null, null);
    }

    private static ContentValues createContentValues(String column, boolean updated) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, Boolean.valueOf(updated));
        return contentValues;
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

    /* access modifiers changed from: private */
    public static class NuxPrefContentObserver extends ContentObserver {
        public final OnChangeListener mListener;
        public final Uri mUri;

        public NuxPrefContentObserver(Uri uri, OnChangeListener listener) {
            super(null);
            this.mUri = uri;
            this.mListener = listener;
        }

        public void onChange(boolean selfChange, Uri uri) {
            this.mListener.onChange();
        }
    }
}
