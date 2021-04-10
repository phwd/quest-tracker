package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PanelCookiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = PanelCookiesModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void DEV_ONLY_clearAllCookiesImpl();

    /* access modifiers changed from: protected */
    public abstract void DEV_ONLY_getAllCookieNamesImpl(Resolver<List<String>> resolver);

    /* access modifiers changed from: protected */
    public abstract void DEV_ONLY_getCookieExpirationImpl(String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract void DEV_ONLY_setCookieWithNoExpirationImpl(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void clearCookieImpl(String str);

    /* access modifiers changed from: protected */
    public abstract void getCookieImpl(String str, Resolver<String> resolver);

    /* access modifiers changed from: protected */
    public abstract JSONObject marshallJSConstantCookiesAtStartup();

    /* access modifiers changed from: protected */
    public abstract void setCookieImpl(String str, String str2, double d);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("clearCookie", "s"));
        list.add(new Pair<>("DEV_ONLY_clearAllCookies", BuildConfig.PROVIDER_SUFFIX));
        list.add(new Pair<>("DEV_ONLY_getAllCookieNames", "+ii"));
        list.add(new Pair<>("DEV_ONLY_getCookieExpiration", "+sii"));
        list.add(new Pair<>("DEV_ONLY_setCookieWithNoExpiration", "ss"));
        list.add(new Pair<>("getCookie", "+sii"));
        list.add(new Pair<>("setCookie", "ssd"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject result = new JSONObject();
        try {
            result.put("COOKIES_AT_STARTUP", marshallJSConstantCookiesAtStartup());
        } catch (JSONException e) {
        }
        return result.toString();
    }

    /* access modifiers changed from: protected */
    public final void clearCookie(String cookieName) {
        clearCookieImpl(cookieName);
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_clearAllCookies() {
        DEV_ONLY_clearAllCookiesImpl();
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_getAllCookieNames(int resolveID, int rejectID) {
        DEV_ONLY_getAllCookieNamesImpl(ResolverFactory.createStringListResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_getCookieExpiration(String cookieName, int resolveID, int rejectID) {
        DEV_ONLY_getCookieExpirationImpl(cookieName, ResolverFactory.createNullableResolver(ResolverFactory.createBasicResolver(this, resolveID, rejectID), this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_setCookieWithNoExpiration(String cookieName, String cookieValue) {
        DEV_ONLY_setCookieWithNoExpirationImpl(cookieName, cookieValue);
    }

    /* access modifiers changed from: protected */
    public final void getCookie(String cookieName, int resolveID, int rejectID) {
        getCookieImpl(cookieName, ResolverFactory.createNullableResolver(ResolverFactory.createBasicResolver(this, resolveID, rejectID), this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void setCookie(String cookieName, String cookieValue, double durationInSeconds) {
        setCookieImpl(cookieName, cookieValue, durationInSeconds);
    }

    public void shutdownModule() {
    }
}
