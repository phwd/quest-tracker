package com.oculus.nuxpreferencesapi;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;

public class OVRNuxPreferences {
    public static final long DEFAULT_DOWNLOAD_WAIT_TIME = 0;
    public static final HashMap<OnChangeListener, ArrayList<NuxPrefContentObserver>> mListeners = new HashMap<>();

    public static class NuxPrefContentObserver extends ContentObserver {
        public final OnChangeListener mListener;
        public final Uri mUri;
    }

    public interface OnChangeListener {
    }

    public enum Status {
        UNSET,
        COMPLETED,
        FAILED
    }

    public static boolean A00(Cursor cursor, int i) {
        return i >= 0 && cursor.getInt(i) > 0;
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
}
