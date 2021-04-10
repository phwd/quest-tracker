package com.oculus.auth.util;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.auth.util.UtilModule;
import com.oculus.base.app.AppInfo;
import com.oculus.base.app.AppInfoModule;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.inject.Provider;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID"})
public class AccessTokenUtils {
    private static final String DEVICE_TOKEN_FORMAT = "OC|%s|%s|%s";
    private static final String DEVICE_TOKEN_PARAM = "device_access_token";
    private static final String LOGGED_OUT_TOKEN_FORMAT = "OC|%s|%s";
    private static final String TAG = "AccessTokenUtils";
    @Inject
    @Eager
    private final AppInfo mAppInfo;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_auth_util_AccessTokenUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UtilModule.UL_id._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final AccessTokenUtils _UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (AccessTokenUtils) UL.factorymap.get(UtilModule.UL_id._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final AccessTokenUtils _UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new AccessTokenUtils(injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_auth_util_AccessTokenUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UtilModule.UL_id._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public AccessTokenUtils(InjectorLike injectorLike) {
        this.mAppInfo = AppInfoModule._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_ACCESS_METHOD(injectorLike);
    }

    public static String createLoggedOutToken(AppInfo appInfo) {
        return createLoggedOutToken(appInfo, null);
    }

    public static String createLoggedOutToken(AppInfo appInfo, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(DEVICE_TOKEN_PARAM, str);
                return String.format(Locale.US, DEVICE_TOKEN_FORMAT, appInfo.oculusAppId, appInfo.oculusLoginToken, jSONObject.toString());
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
