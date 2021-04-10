package com.oculus.mediaupload.model;

import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata {
    public static final String APP_ID_KEY = "appID";
    public static final String CAPTURED_WITH_SHORTCUT_KEY = "capturedWithShortcut";
    public static final String IS_INSTANT_REPLAY_KEY = "isInstantReplay";
    public static final String RICH_PRESENCE_JSON_KEY = "richPresenceJSON";
    @Nullable
    public final String appID;
    public final boolean capturedWithShortcut;
    public final boolean isInstantReplay;
    @Nullable
    public final String richPresenceJSON;

    public final JSONObject A00() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(APP_ID_KEY, this.appID);
        jSONObject.put(RICH_PRESENCE_JSON_KEY, this.richPresenceJSON);
        jSONObject.put(IS_INSTANT_REPLAY_KEY, this.isInstantReplay);
        jSONObject.put(CAPTURED_WITH_SHORTCUT_KEY, this.capturedWithShortcut);
        return jSONObject;
    }

    public MediaMetadata() {
        this.appID = null;
        this.richPresenceJSON = null;
        this.isInstantReplay = false;
        this.capturedWithShortcut = false;
    }

    public MediaMetadata(@Nullable String str, @Nullable String str2, boolean z, boolean z2) {
        this.appID = str;
        this.richPresenceJSON = str2;
        this.isInstantReplay = z;
        this.capturedWithShortcut = z2;
    }
}
