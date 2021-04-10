package com.oculus.panelapp.social;

import android.net.Uri;
import android.util.Log;
import com.facebook.common.stringformat.StringFormatUtil;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.SystemUXRoute;
import org.json.JSONException;
import org.json.JSONObject;

public class SocialPartyUtils {
    private static final String TAG = LoggingUtil.tag(SocialPartyUtils.class);

    public static void navigateToSocialPartyShareSheet(SocialPanelApp socialPanelApp, String str) {
        try {
            socialPanelApp.actionNavigate(SystemUXRoute.SHARE_SHEET_V2, StringFormatUtil.formatStrLocaleSafe("?type=deeplink_target&source=unknown&payload=%s", Uri.encode(buildSocialPartyShareSheetParams(str).toString())));
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    private static JSONObject buildSocialPartyShareSheetParams(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deeplinkTargetId", str);
        return jSONObject;
    }
}
