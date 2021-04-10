package com.facebook.secure.switchoff;

import android.net.Uri;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class DeeplinkConfig {
    private static final String ENFORCE_SCHEME = "enforce_scheme";
    private static final String ENFORCE_SCHEME_AUTHORITY = "enforce_scheme_and_authority";
    private static final String WHITELIST = "whitelist";
    private final Map<String, Pattern> deeplinkWhitelist;
    private final boolean enforceSchemeAndAuthorityWhitelist;
    private final boolean enforceSchemeWhitelist;

    public enum WhitelistStatus {
        ALLOWED,
        DENIED_LOG_ONLY,
        DENIED_ENFORCED
    }

    public DeeplinkConfig(boolean z, boolean z2, Map<String, Pattern> map) {
        this.enforceSchemeWhitelist = z;
        this.enforceSchemeAndAuthorityWhitelist = z2;
        this.deeplinkWhitelist = map;
    }

    public Map<String, Pattern> getDeeplinkWhitelist() {
        return this.deeplinkWhitelist;
    }

    public WhitelistStatus isUriWhitelisted(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return WhitelistStatus.ALLOWED;
        }
        if (this.deeplinkWhitelist.containsKey(str)) {
            if (str2 == null) {
                str2 = "";
            }
            if (this.deeplinkWhitelist.get(str).matcher(str2).matches()) {
                return WhitelistStatus.ALLOWED;
            }
            if (this.enforceSchemeAndAuthorityWhitelist) {
                return WhitelistStatus.DENIED_ENFORCED;
            }
            return WhitelistStatus.DENIED_LOG_ONLY;
        } else if (this.enforceSchemeWhitelist) {
            return WhitelistStatus.DENIED_ENFORCED;
        } else {
            return WhitelistStatus.DENIED_LOG_ONLY;
        }
    }

    public static Map<String, DeeplinkConfig> parseConfig(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                boolean z = true;
                boolean z2 = jSONObject2.has(ENFORCE_SCHEME) && jSONObject2.getBoolean(ENFORCE_SCHEME);
                if (!jSONObject2.has(ENFORCE_SCHEME_AUTHORITY) || !jSONObject2.getBoolean(ENFORCE_SCHEME_AUTHORITY)) {
                    z = false;
                }
                JSONObject jSONObject3 = jSONObject2.getJSONObject(WHITELIST);
                Iterator<String> keys2 = jSONObject3.keys();
                HashMap hashMap2 = new HashMap();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    hashMap2.put(next2, Pattern.compile(jSONObject3.getString(next2)));
                }
                hashMap.put(next, new DeeplinkConfig(z2, z, hashMap2));
            }
        } catch (JSONException unused) {
        }
        return hashMap;
    }

    public static WhitelistStatus isWhitelisted(Object obj, @Nullable Uri uri, @Nullable Map<String, DeeplinkConfig> map) {
        if (uri == null) {
            return WhitelistStatus.ALLOWED;
        }
        if (map == null || map.isEmpty()) {
            return WhitelistStatus.ALLOWED;
        }
        String name = obj.getClass().getName();
        if (!map.containsKey(name)) {
            return WhitelistStatus.ALLOWED;
        }
        return map.get(name).isUriWhitelisted(uri.getScheme(), uri.getAuthority());
    }
}
