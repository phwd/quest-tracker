package com.oculus.modules;

import X.AnonymousClass006;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.oculus.modules.codegen.PanelCookiesModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.panellib.SystraceBlock;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PanelCookiesModuleImpl extends PanelCookiesModule {
    public static final String COOKIES_JSON_KEY = "cookies";
    public static final String COOKIE_ACCESS_TOKEN_KEY = "Home:CachedAccessToken";
    public static final String COOKIE_JSON_EXPIRES = "expires";
    public static final String COOKIE_JSON_KEY = "key";
    public static final String COOKIE_JSON_VALUE = "value";
    public static final boolean DebugLog = false;
    public static final int MAX_DURATION_IN_SECONDS = 604800;
    public static final String PREF_COOKIES_KEY = "Cookies";
    public static final String PREF_FILE_KEY = "PanelCookies";
    public static final String TAG = "PanelCookiesModuleImpl";
    public static final String VERSION_JSON_KEY = "version";
    public static final String VERSION_JSON_VALUE = "1.0";
    public final Context mContext;
    public final HashMap<String, CookieData> mCookies = new HashMap<>();

    public class CookieData {
        public final Date Expires;
        public final String Value;

        public CookieData(String str, Date date) {
            this.Value = str;
            this.Expires = date;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CookieData)) {
                return false;
            }
            CookieData cookieData = (CookieData) obj;
            if (!this.Value.equals(cookieData.Value) || !this.Expires.equals(cookieData.Expires)) {
                return false;
            }
            return true;
        }
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void setCookieImpl(String str, String str2, double d) {
        int min = Math.min((int) d, (int) MAX_DURATION_IN_SECONDS);
        if (min <= 0) {
            internalRemoveCookie(str);
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(13, min);
        internalAddCookie(str, new CookieData(str2, instance.getTime()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00da, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeFromStorageState() {
        /*
        // Method dump skipped, instructions count: 219
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.PanelCookiesModuleImpl.initializeFromStorageState():void");
    }

    private void internalAddCookie(String str, CookieData cookieData) {
        CookieData cookieData2 = this.mCookies.get(str);
        this.mCookies.put(str, cookieData);
        if (!cookieData.equals(cookieData2)) {
            updateStorageState();
        }
    }

    private CookieData internalGetCookie(String str) {
        CookieData cookieData = this.mCookies.get(str);
        if (cookieData != null) {
            if (!cookieData.Expires.before(new Date())) {
                return cookieData;
            }
            this.mCookies.remove(str);
            updateStorageState();
        }
        return null;
    }

    private void internalRemoveCookie(String str) {
        if (this.mCookies.remove(str) != null) {
            updateStorageState();
        }
    }

    private void updateStorageState() {
        SystraceBlock systraceBlock = new SystraceBlock(TAG, "updateStorageState");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
            for (Map.Entry<String, CookieData> entry : this.mCookies.entrySet()) {
                CookieData value = entry.getValue();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(COOKIE_JSON_KEY, entry.getKey());
                jSONObject2.put("value", value.Value);
                jSONObject2.put(COOKIE_JSON_EXPIRES, dateTimeInstance.format(value.Expires));
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(COOKIES_JSON_KEY, jSONArray);
            jSONObject.put("version", "1.0");
            String obj = jSONObject.toString();
            SharedPreferences.Editor edit = this.mContext.getSharedPreferences(PREF_FILE_KEY, 0).edit();
            edit.putString(PREF_COOKIES_KEY, obj);
            edit.apply();
        } catch (Throwable th) {
            Log.e(TAG, AnonymousClass006.A07("Failure to write cookies: ", th.getMessage()));
        }
        systraceBlock.close();
        return;
        try {
        } catch (Throwable th2) {
            try {
                systraceBlock.close();
            } catch (Throwable unused) {
            }
            throw th2;
        }
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_clearAllCookiesImpl() {
        this.mCookies.clear();
        updateStorageState();
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_getAllCookieNamesImpl(Resolver<List<String>> resolver) {
        resolver.resolve(new ArrayList(this.mCookies.keySet()));
    }

    public String getAccessToken() {
        CookieData internalGetCookie = internalGetCookie(COOKIE_ACCESS_TOKEN_KEY);
        if (internalGetCookie != null) {
            return internalGetCookie.Value;
        }
        return null;
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public JSONObject marshallJSConstantCookiesAtStartup() {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, CookieData> entry : this.mCookies.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue().Value);
            } catch (JSONException unused) {
                Log.e(TAG, AnonymousClass006.A09("Could not expose constant value for cookie '", entry.getKey(), "'"));
            }
        }
        return jSONObject;
    }

    public PanelCookiesModuleImpl(Context context) {
        this.mContext = context;
        initializeFromStorageState();
    }

    public static String getJSONValue(JSONObject jSONObject, String str, String str2) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
        } catch (JSONException unused) {
        }
        return str2;
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_getCookieExpirationImpl(String str, Resolver<String> resolver) {
        String str2;
        CookieData internalGetCookie = internalGetCookie(str);
        if (internalGetCookie != null) {
            str2 = internalGetCookie.Expires.toString();
        } else {
            str2 = null;
        }
        resolver.resolve(str2);
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_setCookieWithNoExpirationImpl(String str, String str2) {
        Calendar instance = Calendar.getInstance();
        instance.add(1, 1000);
        internalAddCookie(str, new CookieData(str2, instance.getTime()));
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void getCookieImpl(String str, Resolver<String> resolver) {
        String str2;
        CookieData internalGetCookie = internalGetCookie(str);
        if (internalGetCookie != null) {
            str2 = internalGetCookie.Value;
        } else {
            str2 = null;
        }
        resolver.resolve(str2);
    }

    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void clearCookieImpl(String str) {
        internalRemoveCookie(str);
    }
}
