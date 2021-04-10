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

    public static void unregisterNuxPrefListener(Context context, OnChangeListener onChangeListener) {
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<NuxPrefContentObserver> remove = mListeners.remove(onChangeListener);
        if (remove != null) {
            for (NuxPrefContentObserver nuxPrefContentObserver : remove) {
                contentResolver.unregisterContentObserver(nuxPrefContentObserver);
            }
        }
    }

    public static void registerNuxPrefChangeListener(Context context, String str, OnChangeListener onChangeListener) {
        registerObserverForUri(context, OculusContent.Profile.getUriFromNuxPref(str), onChangeListener);
    }

    private static void registerObserverForUri(Context context, Uri uri, OnChangeListener onChangeListener) {
        ArrayList<NuxPrefContentObserver> arrayList = mListeners.get(onChangeListener);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            mListeners.put(onChangeListener, arrayList);
        }
        Iterator<NuxPrefContentObserver> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().mUri.equals(uri)) {
                return;
            }
        }
        NuxPrefContentObserver nuxPrefContentObserver = new NuxPrefContentObserver(uri, onChangeListener);
        context.getContentResolver().registerContentObserver(uri, true, nuxPrefContentObserver);
        arrayList.add(nuxPrefContentObserver);
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
        String stringWithDefault = getStringWithDefault(query, query.getColumnIndex(OculusContent.Profile.DEFAULT_BROWSER), null);
        String stringWithDefault2 = getStringWithDefault(query, query.getColumnIndex(OculusContent.Profile.NUX_RESULT), null);
        int intWithDefault = getIntWithDefault(query, query.getColumnIndex(OculusContent.Profile.NUX_SEEN_COUNT), 0);
        String stringWithDefault3 = getStringWithDefault(query, query.getColumnIndex(OculusContent.Profile.NUX_STATE), null);
        String stringWithDefault4 = getStringWithDefault(query, query.getColumnIndex("id"), null);
        boolean booleanWithDefault = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT), false);
        boolean booleanWithDefault2 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_CONFIRM_QUIT_NOTIF), false);
        boolean booleanWithDefault3 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_FOCUS), false);
        boolean booleanWithDefault4 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_INTERNET_BROWSER_PROMPT), false);
        boolean booleanWithDefault5 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_TUTORIAL_PROMPT), false);
        boolean booleanWithDefault6 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_PARTY_CALLS_NUX), false);
        boolean booleanWithDefault7 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_SAVED_PROMPT), false);
        boolean booleanWithDefault8 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_LONG_HSW), false);
        boolean booleanWithDefault9 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_HSW), false);
        boolean booleanWithDefault10 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_OPTED_OUT_OF_MALIBU_RECENTER), false);
        Status statusWithDefault = getStatusWithDefault(query, query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS), Status.UNSET);
        long longWithDefault = getLongWithDefault(query, query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME), 0);
        boolean booleanWithDefault11 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_DOCKED), false);
        boolean booleanWithDefault12 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_HAND_TRACKING_NUX), false);
        boolean booleanWithDefault13 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_FULL_VR_NUX), false);
        boolean booleanWithDefault14 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_NUX), false);
        boolean booleanWithDefault15 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_SEEN_NUX), false);
        boolean booleanWithDefault16 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_IPD_ADJUST), false);
        boolean booleanWithDefault17 = getBooleanWithDefault(query, query.getColumnIndex(OculusContent.Profile.HAS_FINISHED_MONTEREY_NUX), false);
        String stringWithDefault5 = getStringWithDefault(query, query.getColumnIndex(OculusContent.Profile.ROLLOUT_TOKEN), null);
        String stringWithDefault6 = getStringWithDefault(query, query.getColumnIndex(OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES), null);
        query.close();
        return new ClientPreferenceData(stringWithDefault, booleanWithDefault12, booleanWithDefault13, booleanWithDefault14, booleanWithDefault, booleanWithDefault2, booleanWithDefault3, booleanWithDefault4, booleanWithDefault15, booleanWithDefault5, booleanWithDefault6, booleanWithDefault7, booleanWithDefault8, booleanWithDefault9, booleanWithDefault10, statusWithDefault, longWithDefault, stringWithDefault2, intWithDefault, stringWithDefault3, booleanWithDefault16, booleanWithDefault17, stringWithDefault4, booleanWithDefault11, stringWithDefault5, stringWithDefault6);
    }

    public static void setDefaultBrowser(Context context, String str) {
        updateStringPreference(context, OculusContent.Profile.DEFAULT_BROWSER, str);
    }

    public static void setHasSeenHandTrackingNux(Context context, boolean z) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_SEEN_HAND_TRACKING_NUX, z);
    }

    public static void setNuxResult(Context context, String str) {
        updateStringPreference(context, OculusContent.Profile.NUX_RESULT, str);
    }

    public static void setNuxState(Context context, String str) {
        updateStringPreference(context, OculusContent.Profile.NUX_STATE, str);
    }

    public static void setNuxSeenCount(Context context, int i) {
        updateIntPreference(context, OculusContent.Profile.NUX_SEEN_COUNT, i);
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

    public static void setOptedOutofHSW(Context context, boolean z) {
        updateBooleanPreference(context, OculusContent.Profile.HAS_OPTED_OUT_HSW, z);
    }

    public static void setHighPriorityAppsDownloadStatus(Context context, Status status) {
        updateIntPreference(context, OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_STATUS, status.ordinal());
    }

    public static void setHighPriorityAppsDownloadWaitTime(Context context, long j) {
        updateLongPreference(context, OculusContent.Profile.HIGH_PRIORITY_APPS_DOWNLOAD_WAITTIME, j);
    }

    public static void setHighPriorityAppDownloadPackages(Context context, Collection<String> collection) {
        if (collection.isEmpty()) {
            updateStringPreference(context, OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES, "");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            sb.append(str);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        updateStringPreference(context, OculusContent.Profile.HIGH_PRIORITY_APP_DOWNLOAD_PACKAGES, sb.toString());
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

    private static void updateStringPreference(Context context, String str, String str2) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createStringContentValues(str, str2), null, null);
    }

    private static ContentValues createStringContentValues(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, str2);
        return contentValues;
    }

    private static void updateIntPreference(Context context, String str, int i) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createIntContentValues(str, i), null, null);
    }

    private static ContentValues createIntContentValues(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Integer.valueOf(i));
        return contentValues;
    }

    private static void updateLongPreference(Context context, String str, long j) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createLongContentValues(str, j), null, null);
    }

    private static ContentValues createLongContentValues(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Long.valueOf(j));
        return contentValues;
    }

    private static void updateBooleanPreference(Context context, String str, boolean z) {
        context.getContentResolver().update(OculusContent.Profile.CONTENT_URI, createContentValues(str, z), null, null);
    }

    private static ContentValues createContentValues(String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str, Boolean.valueOf(z));
        return contentValues;
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

    /* access modifiers changed from: private */
    public static class NuxPrefContentObserver extends ContentObserver {
        public final OnChangeListener mListener;
        public final Uri mUri;

        public NuxPrefContentObserver(Uri uri, OnChangeListener onChangeListener) {
            super(null);
            this.mUri = uri;
            this.mListener = onChangeListener;
        }

        public void onChange(boolean z, Uri uri) {
            this.mListener.onChange();
        }
    }
}
