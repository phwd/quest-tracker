package com.oculus.panelapp.social.profile;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.social.R;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.vrshell.SystemUXRoute;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileUtils {
    private static final String TAG = LoggingUtil.tag(ProfileUtils.class);

    public static void navigateToProfileShareSheet(Context context, SocialPanelApp socialPanelApp, String str, String str2) {
        try {
            socialPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, StringFormatUtil.formatStrLocaleSafe("?type=external_link&source=aui_share_profile&payload=%s&overrides=%s", Uri.encode(buildProfileShareSheetParams(buildProfileShareSheetUri(str)).toString()), Uri.encode(buildProfileShareSheetOverrides(context, str, str2).toString())));
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    private static Uri buildProfileShareSheetUri(String str) {
        return new Uri.Builder().path("www.oculus.com").appendPath("profile").appendPath(str).build();
    }

    private static JSONObject buildProfileShareSheetParams(Uri uri) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("linkAttachment", uri.toString());
        return jSONObject;
    }

    private static JSONObject buildProfileShareSheetOverrides(Context context, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("attachmentTitle", str);
        jSONObject.put("attachmentDescription", context.getString(R.string.anytime_tablet_profile_share_description));
        jSONObject.put("attachmentImageUri", str2);
        return jSONObject;
    }
}
