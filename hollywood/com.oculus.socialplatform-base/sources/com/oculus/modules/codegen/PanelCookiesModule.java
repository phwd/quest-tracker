package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PanelCookiesModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "PanelCookiesModule";

    public abstract void DEV_ONLY_clearAllCookiesImpl();

    public abstract void DEV_ONLY_getAllCookieNamesImpl(Resolver<List<String>> resolver);

    public abstract void DEV_ONLY_getCookieExpirationImpl(String str, Resolver<String> resolver);

    public abstract void DEV_ONLY_setCookieWithNoExpirationImpl(String str, String str2);

    public abstract void clearCookieImpl(String str);

    public abstract void getCookieImpl(String str, Resolver<String> resolver);

    public abstract JSONObject marshallJSConstantCookiesAtStartup();

    public abstract void setCookieImpl(String str, String str2, double d);

    public void shutdownModule() {
    }

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("clearCookie", "s"));
        arrayList.add(new Pair("DEV_ONLY_clearAllCookies", ""));
        arrayList.add(new Pair("DEV_ONLY_getAllCookieNames", "+ii"));
        arrayList.add(new Pair("DEV_ONLY_getCookieExpiration", "+sii"));
        arrayList.add(new Pair("DEV_ONLY_setCookieWithNoExpiration", "ss"));
        arrayList.add(new Pair("getCookie", "+sii"));
        arrayList.add(new Pair("setCookie", "ssd"));
        return arrayList;
    }

    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("COOKIES_AT_STARTUP", marshallJSConstantCookiesAtStartup());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public final void DEV_ONLY_clearAllCookies() {
        DEV_ONLY_clearAllCookiesImpl();
    }

    public final void DEV_ONLY_getAllCookieNames(int i, int i2) {
        DEV_ONLY_getAllCookieNamesImpl(ResolverFactory.createStringListResolver(this, i, i2));
    }

    public final void DEV_ONLY_getCookieExpiration(String str, int i, int i2) {
        DEV_ONLY_getCookieExpirationImpl(str, new NullableResolverImpl(ResolverFactory.createBasicResolver(this, i, i2), this, i, i2));
    }

    public final void getCookie(String str, int i, int i2) {
        getCookieImpl(str, new NullableResolverImpl(ResolverFactory.createBasicResolver(this, i, i2), this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void clearCookie(String str) {
        clearCookieImpl(str);
    }

    public final void DEV_ONLY_setCookieWithNoExpiration(String str, String str2) {
        DEV_ONLY_setCookieWithNoExpirationImpl(str, str2);
    }

    public final void setCookie(String str, String str2, double d) {
        setCookieImpl(str, str2, d);
    }
}
