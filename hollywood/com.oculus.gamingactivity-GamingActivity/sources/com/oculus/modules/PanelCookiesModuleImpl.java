package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.modules.codegen.PanelCookiesModule;
import com.oculus.modules.codegen.Resolver;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PanelCookiesModuleImpl extends PanelCookiesModule {
    private static final String COOKIES_JSON_KEY = "cookies";
    private static final String COOKIE_ACCESS_TOKEN_KEY = "Home:CachedAccessToken";
    private static final String COOKIE_JSON_EXPIRES = "expires";
    private static final String COOKIE_JSON_KEY = "key";
    private static final String COOKIE_JSON_VALUE = "value";
    private static final boolean DebugLog = false;
    private static final int MAX_DURATION_IN_SECONDS = 604800;
    private static final String PREF_COOKIES_KEY = "Cookies";
    private static final String PREF_FILE_KEY = "PanelCookies";
    private static final String TAG = PanelCookiesModuleImpl.class.getSimpleName();
    private static final String VERSION_JSON_KEY = "version";
    private static final String VERSION_JSON_VALUE = "1.0";
    private final Context mContext;
    private final HashMap<String, CookieData> mCookies = new HashMap<>();

    /* access modifiers changed from: package-private */
    public class CookieData {
        public final Date Expires;
        public final String Value;

        public CookieData(String value, Date expires) {
            this.Value = value;
            this.Expires = expires;
        }

        public boolean equals(Object other) {
            if (!(other instanceof CookieData)) {
                return false;
            }
            CookieData otherData = (CookieData) other;
            if (!this.Value.equals(otherData.Value) || !this.Expires.equals(otherData.Expires)) {
                return false;
            }
            return true;
        }
    }

    public PanelCookiesModuleImpl(Context context) {
        this.mContext = context;
        initializeFromStorageState();
    }

    public String getAccessToken() {
        CookieData cookieData = internalGetCookie(COOKIE_ACCESS_TOKEN_KEY);
        if (cookieData != null) {
            return cookieData.Value;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public JSONObject marshallJSConstantCookiesAtStartup() {
        JSONObject cookiesObject = new JSONObject();
        for (Map.Entry<String, CookieData> cookieEntry : this.mCookies.entrySet()) {
            try {
                cookiesObject.put(cookieEntry.getKey(), cookieEntry.getValue().Value);
            } catch (JSONException e) {
                Log.e(TAG, "Could not expose constant value for cookie '" + cookieEntry.getKey() + "'");
            }
        }
        return cookiesObject;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void setCookieImpl(String cookieName, String cookieValue, double durationInSeconds) {
        int durationToStoreInSeconds = Math.min((int) durationInSeconds, (int) MAX_DURATION_IN_SECONDS);
        if (durationToStoreInSeconds <= 0) {
            internalRemoveCookie(cookieName);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, durationToStoreInSeconds);
        internalAddCookie(cookieName, new CookieData(cookieValue, calendar.getTime()));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void getCookieImpl(String cookieName, Resolver<String> resolver) {
        CookieData cookieData = internalGetCookie(cookieName);
        resolver.resolve(cookieData != null ? cookieData.Value : null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void clearCookieImpl(String cookieName) {
        internalRemoveCookie(cookieName);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_clearAllCookiesImpl() {
        this.mCookies.clear();
        updateStorageState();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_getAllCookieNamesImpl(Resolver<List<String>> resolver) {
        resolver.resolve(new ArrayList(this.mCookies.keySet()));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_getCookieExpirationImpl(String cookieName, Resolver<String> resolver) {
        CookieData cookieData = internalGetCookie(cookieName);
        resolver.resolve(cookieData != null ? cookieData.Expires.toString() : null);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.PanelCookiesModule
    public void DEV_ONLY_setCookieWithNoExpirationImpl(String cookieName, String cookieValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, 1000);
        internalAddCookie(cookieName, new CookieData(cookieValue, calendar.getTime()));
    }

    private void internalAddCookie(String cookieName, CookieData cookieData) {
        this.mCookies.put(cookieName, cookieData);
        if (!cookieData.equals(this.mCookies.get(cookieName))) {
            updateStorageState();
        }
    }

    private void internalRemoveCookie(String cookieName) {
        if (this.mCookies.remove(cookieName) != null) {
            updateStorageState();
        }
    }

    private CookieData internalGetCookie(String cookieName) {
        CookieData cookieData = this.mCookies.get(cookieName);
        if (cookieData == null) {
            return null;
        }
        if (!cookieData.Expires.before(new Date())) {
            return cookieData;
        }
        this.mCookies.remove(cookieName);
        updateStorageState();
        return null;
    }

    private static String getJSONValue(JSONObject object, String key, String defaultValue) {
        try {
            if (object.has(key)) {
                return object.getString(key);
            }
            return defaultValue;
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0203, code lost:
        r22 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0206, code lost:
        if (r23 != null) goto L_0x0208;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x021b, code lost:
        r23 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x021c, code lost:
        r23 = r22;
        r22 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0223, code lost:
        r24 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0224, code lost:
        r23.addSuppressed(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0228, code lost:
        r5.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0203 A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0206  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeFromStorageState() {
        /*
        // Method dump skipped, instructions count: 556
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.PanelCookiesModuleImpl.initializeFromStorageState():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00a7, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00aa, code lost:
        if (r13 != null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00c2, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c3, code lost:
        r13.addSuppressed(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c7, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00a7 A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateStorageState() {
        /*
        // Method dump skipped, instructions count: 203
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.PanelCookiesModuleImpl.updateStorageState():void");
    }
}
