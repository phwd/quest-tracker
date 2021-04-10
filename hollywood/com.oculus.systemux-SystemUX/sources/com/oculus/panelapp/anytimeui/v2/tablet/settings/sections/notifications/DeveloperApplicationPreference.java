package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications;

import android.net.Uri;
import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import org.json.JSONException;
import org.json.JSONObject;

public class DeveloperApplicationPreference {
    private static final String TAG = LoggingUtil.tag(DeveloperApplicationPreference.class);
    private final String mDisplayName;
    private boolean mEnabled;
    private final String mId;
    private final Uri mImageUri;

    public DeveloperApplicationPreference(String str, String str2, boolean z, Uri uri) {
        this.mId = str;
        this.mDisplayName = str2;
        this.mEnabled = z;
        this.mImageUri = uri;
    }

    public String getId() {
        return this.mId;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public Uri getImageUri() {
        return this.mImageUri;
    }

    public void setEnabled(Boolean bool) {
        this.mEnabled = bool.booleanValue();
    }

    public static DeveloperApplicationPreference fromJSON(JSONObject jSONObject) {
        String str;
        Uri uri;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("application");
            JSONObject optJSONObject = jSONObject2.optJSONObject("icon_image");
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("cover_square_image");
            if (optJSONObject != null) {
                str = optJSONObject.getString(AssistantComponentContract.Components.RemoteImageViewComponent.URI);
            } else {
                str = optJSONObject2 != null ? optJSONObject2.getString(AssistantComponentContract.Components.RemoteImageViewComponent.URI) : null;
            }
            String string = jSONObject2.getString("id");
            String string2 = jSONObject2.getString("display_name");
            boolean z = jSONObject.getBoolean(SharingManagerContract.ARGUMENT_IS_ENABLED);
            if (str == null) {
                uri = null;
            } else {
                uri = Uri.parse(str);
            }
            return new DeveloperApplicationPreference(string, string2, z, uri);
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing json", e);
            return null;
        }
    }
}
