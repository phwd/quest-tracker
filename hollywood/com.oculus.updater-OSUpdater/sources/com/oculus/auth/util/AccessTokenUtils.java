package com.oculus.auth.util;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.ultralight.Dependencies;
import com.oculus.base.app.AppInfo;
import java.util.Locale;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies
public class AccessTokenUtils {
    private static final String TAG = "AccessTokenUtils";

    public static String createLoggedOutToken(AppInfo appInfo) {
        return createLoggedOutToken(appInfo, null);
    }

    public static String createLoggedOutToken(AppInfo appInfo, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("device_access_token", str);
                return String.format(Locale.US, "OC|%s|%s|%s", appInfo.oculusAppId, appInfo.oculusLoginToken, jSONObject.toString());
            } catch (JSONException e) {
                BLog.e(TAG, e, "Failed to embed device auth token into logged out access token");
            }
        }
        return String.format(Locale.US, "OC|%s|%s", appInfo.oculusAppId, appInfo.oculusLoginToken);
    }
}
