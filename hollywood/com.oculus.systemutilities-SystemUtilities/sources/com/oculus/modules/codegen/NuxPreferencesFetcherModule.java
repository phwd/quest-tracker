package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class NuxPreferencesFetcherModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = NuxPreferencesFetcherModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void fetchPreferencesImpl(Resolver<NuxPreferences> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("fetchPreferences", "+ii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void fetchPreferences(int resolveID, int rejectID) {
        fetchPreferencesImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class NuxPreferences extends NativeModuleParcel {
        public boolean hasDocked;
        public boolean hasFinishedIPDAdjust;
        public boolean hasFinishedMontereyNux;
        public boolean hasFinishedNux;
        public boolean hasOptedOutOfMalibuRecenter;
        public boolean hasSeenConfirmQuit;
        public boolean hasSeenConfirmQuitNotif;
        public boolean hasSeenFocus;
        public boolean hasSeenHandTrackingNux;
        public boolean hasSeenLongHSW;
        public boolean hasSeenNux;
        public boolean hasSeenPartyCallsNUX;
        public boolean hasSeenSavedPrompt;
        public boolean hasSeenTutorial;
        public String highPriorityAppDownloadPackages;
        public String highPriorityAppsDownloadStatus;
        public double highPriorityAppsDownloadWaitTime;
        public double nuxSeenCount;
        public String rolloutToken;
        public String userID;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("hasDocked", this.hasDocked);
                parcel.put("hasFinishedIPDAdjust", this.hasFinishedIPDAdjust);
                parcel.put("hasFinishedMontereyNux", this.hasFinishedMontereyNux);
                parcel.put("hasFinishedNux", this.hasFinishedNux);
                parcel.put("hasOptedOutOfMalibuRecenter", this.hasOptedOutOfMalibuRecenter);
                parcel.put("hasSeenConfirmQuit", this.hasSeenConfirmQuit);
                parcel.put("hasSeenConfirmQuitNotif", this.hasSeenConfirmQuitNotif);
                parcel.put("hasSeenFocus", this.hasSeenFocus);
                parcel.put("hasSeenHandTrackingNux", this.hasSeenHandTrackingNux);
                parcel.put("hasSeenLongHSW", this.hasSeenLongHSW);
                parcel.put("hasSeenNux", this.hasSeenNux);
                parcel.put("hasSeenPartyCallsNUX", this.hasSeenPartyCallsNUX);
                parcel.put("hasSeenSavedPrompt", this.hasSeenSavedPrompt);
                parcel.put("hasSeenTutorial", this.hasSeenTutorial);
                parcel.put("highPriorityAppDownloadPackages", this.highPriorityAppDownloadPackages);
                parcel.put("highPriorityAppsDownloadStatus", this.highPriorityAppsDownloadStatus);
                parcel.put("highPriorityAppsDownloadWaitTime", this.highPriorityAppsDownloadWaitTime);
                parcel.put("nuxSeenCount", this.nuxSeenCount);
                parcel.put("rolloutToken", this.rolloutToken);
                parcel.put("userID", this.userID);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.hasDocked = json.optBoolean("hasDocked");
            this.hasFinishedIPDAdjust = json.optBoolean("hasFinishedIPDAdjust");
            this.hasFinishedMontereyNux = json.optBoolean("hasFinishedMontereyNux");
            this.hasFinishedNux = json.optBoolean("hasFinishedNux");
            this.hasOptedOutOfMalibuRecenter = json.optBoolean("hasOptedOutOfMalibuRecenter");
            this.hasSeenConfirmQuit = json.optBoolean("hasSeenConfirmQuit");
            this.hasSeenConfirmQuitNotif = json.optBoolean("hasSeenConfirmQuitNotif");
            this.hasSeenFocus = json.optBoolean("hasSeenFocus");
            this.hasSeenHandTrackingNux = json.optBoolean("hasSeenHandTrackingNux");
            this.hasSeenLongHSW = json.optBoolean("hasSeenLongHSW");
            this.hasSeenNux = json.optBoolean("hasSeenNux");
            this.hasSeenPartyCallsNUX = json.optBoolean("hasSeenPartyCallsNUX");
            this.hasSeenSavedPrompt = json.optBoolean("hasSeenSavedPrompt");
            this.hasSeenTutorial = json.optBoolean("hasSeenTutorial");
            this.highPriorityAppDownloadPackages = json.optString("highPriorityAppDownloadPackages");
            this.highPriorityAppsDownloadStatus = json.optString("highPriorityAppsDownloadStatus");
            this.highPriorityAppsDownloadWaitTime = json.optDouble("highPriorityAppsDownloadWaitTime", 0.0d);
            this.nuxSeenCount = json.optDouble("nuxSeenCount", 0.0d);
            this.rolloutToken = json.optString("rolloutToken");
            this.userID = json.optString("userID");
        }

        public static final NuxPreferences makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            NuxPreferences result = new NuxPreferences();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
