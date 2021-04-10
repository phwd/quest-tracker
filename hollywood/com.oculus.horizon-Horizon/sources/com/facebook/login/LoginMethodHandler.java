package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.Utility;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class LoginMethodHandler implements Parcelable {
    public Map<String, String> A00;
    public LoginClient A01;

    public static AccessToken A00(Collection<String> collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) throws FacebookException {
        ArrayList arrayList;
        Collection<String> collection2 = collection;
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, AccessToken.EXPIRES_IN_KEY, new Date());
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("granted_scopes");
        if (!Utility.isNullOrEmpty(string2)) {
            collection2 = new ArrayList<>(Arrays.asList(string2.split(",")));
        }
        String string3 = bundle.getString("denied_scopes");
        if (!Utility.isNullOrEmpty(string3)) {
            arrayList = new ArrayList(Arrays.asList(string3.split(",")));
        } else {
            arrayList = null;
        }
        if (Utility.isNullOrEmpty(string)) {
            return null;
        }
        String string4 = bundle.getString("signed_request");
        if (string4 == null || string4.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = string4.split("\\.");
            if (split.length == 2) {
                return new AccessToken(string, str, new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id"), collection2, arrayList, accessTokenSource, bundleLongAsDate, new Date());
            }
        } catch (UnsupportedEncodingException | JSONException unused) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public final String A01() {
        if (this instanceof WebViewLoginMethodHandler) {
            return "web_view";
        }
        if (!(this instanceof KatanaProxyLoginMethodHandler)) {
            return "get_token";
        }
        return "katana_proxy_auth";
    }

    public final void A02(String str) {
        LoginClient loginClient = this.A01;
        String str2 = loginClient.A04.A04;
        AppEventsLogger appEventsLogger = new AppEventsLogger(loginClient.A03.getActivity(), str2);
        Bundle bundle = new Bundle();
        bundle.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, str);
        bundle.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        bundle.putString("app_id", str2);
        AppEventsLogger.A02(appEventsLogger, AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, bundle, true);
    }

    public final void A03(String str, Object obj) {
        String obj2;
        Map map = this.A00;
        if (map == null) {
            map = new HashMap();
            this.A00 = map;
        }
        if (obj == null) {
            obj2 = null;
        } else {
            obj2 = obj.toString();
        }
        map.put(str, obj2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        Utility.writeStringMapToParcel(parcel, this.A00);
    }

    public LoginMethodHandler(Parcel parcel) {
        this.A00 = Utility.readStringMapFromParcel(parcel);
    }

    public LoginMethodHandler(LoginClient loginClient) {
        this.A01 = loginClient;
    }
}
