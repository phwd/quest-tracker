package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PanelCookiesModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = "PanelCookiesModule";

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

    public void shutdownModule() {
    }

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("clearCookie", "s"));
        arrayList.add(new Pair("DEV_ONLY_clearAllCookies", BuildConfig.PROVIDER_SUFFIX));
        arrayList.add(new Pair("DEV_ONLY_getAllCookieNames", "+ii"));
        arrayList.add(new Pair("DEV_ONLY_getCookieExpiration", "+sii"));
        arrayList.add(new Pair("DEV_ONLY_setCookieWithNoExpiration", "ss"));
        arrayList.add(new Pair("getCookie", "+sii"));
        arrayList.add(new Pair("setCookie", "ssd"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("COOKIES_AT_STARTUP", marshallJSConstantCookiesAtStartup());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: protected */
    public final void clearCookie(String str) {
        clearCookieImpl(str);
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_clearAllCookies() {
        DEV_ONLY_clearAllCookiesImpl();
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_getAllCookieNames(int i, int i2) {
        DEV_ONLY_getAllCookieNamesImpl(ResolverFactory.createStringListResolver(this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_getCookieExpiration(String str, int i, int i2) {
        DEV_ONLY_getCookieExpirationImpl(str, ResolverFactory.createNullableResolver(ResolverFactory.createBasicResolver(this, i, i2), this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void DEV_ONLY_setCookieWithNoExpiration(String str, String str2) {
        DEV_ONLY_setCookieWithNoExpirationImpl(str, str2);
    }

    /* access modifiers changed from: protected */
    public final void getCookie(String str, int i, int i2) {
        getCookieImpl(str, ResolverFactory.createNullableResolver(ResolverFactory.createBasicResolver(this, i, i2), this, i, i2));
    }

    /* access modifiers changed from: protected */
    public final void setCookie(String str, String str2, double d) {
        setCookieImpl(str, str2, d);
    }
}
