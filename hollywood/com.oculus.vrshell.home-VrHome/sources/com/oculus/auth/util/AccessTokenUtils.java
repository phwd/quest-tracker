package com.oculus.auth.util;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.auth.util.UtilModule;
import com.oculus.base.app.AppInfo;
import com.oculus.base.app.AppInfoModule;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessTokenUtils {
    private static final String DEVICE_TOKEN_FORMAT = "OC|%s|%s|%s";
    private static final String DEVICE_TOKEN_PARAM = "device_access_token";
    private static final String LOGGED_OUT_TOKEN_FORMAT = "OC|%s|%s";
    private static final String TAG = AccessTokenUtils.class.getSimpleName();
    private final AppInfo mAppInfo;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_auth_util_AccessTokenUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UtilModule.UL_id.$ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final AccessTokenUtils $ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (AccessTokenUtils) UL.factorymap.get(UtilModule.UL_id.$ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final AccessTokenUtils $ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new AccessTokenUtils($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_auth_util_AccessTokenUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UtilModule.UL_id.$ul_$xXXcom_oculus_auth_util_AccessTokenUtils$xXXBINDING_ID, $ul_injector);
    }

    public AccessTokenUtils(InjectorLike $ul_injector) {
        this.mAppInfo = AppInfoModule.$ul_$xXXcom_oculus_base_app_AppInfo$xXXACCESS_METHOD($ul_injector);
    }

    public static String createLoggedOutToken(AppInfo appInfo) {
        return createLoggedOutToken(appInfo, null);
    }

    public static String createLoggedOutToken(AppInfo appInfo, @Nullable String deviceAccessToken) {
        if (!TextUtils.isEmpty(deviceAccessToken)) {
            try {
                JSONObject oauthData = new JSONObject();
                oauthData.put(DEVICE_TOKEN_PARAM, deviceAccessToken);
                return String.format(Locale.US, DEVICE_TOKEN_FORMAT, appInfo.oculusAppId, appInfo.oculusLoginToken, oauthData.toString());
            } catch (JSONException e) {
                BLog.e(TAG, e, "Failed to embed device auth token into logged out access token");
            }
        }
        return String.format(Locale.US, LOGGED_OUT_TOKEN_FORMAT, appInfo.oculusAppId, appInfo.oculusLoginToken);
    }

    public String createLoggedOutToken() {
        return createLoggedOutToken(this.mAppInfo);
    }
}
