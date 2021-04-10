package com.facebook.secure.switchoff;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class DeeplinkConfig {
    private final Map<String, Pattern> deeplinkWhitelist;
    private final boolean enforceSchemeAndAuthorityWhitelist;
    private final boolean enforceSchemeWhitelist;

    public DeeplinkConfig(boolean z, boolean z2, Map<String, Pattern> map) {
        this.enforceSchemeWhitelist = z;
        this.enforceSchemeAndAuthorityWhitelist = z2;
        this.deeplinkWhitelist = map;
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
                boolean z2 = jSONObject2.has("enforce_scheme") && jSONObject2.getBoolean("enforce_scheme");
                if (!jSONObject2.has("enforce_scheme_and_authority") || !jSONObject2.getBoolean("enforce_scheme_and_authority")) {
                    z = false;
                }
                JSONObject jSONObject3 = jSONObject2.getJSONObject("whitelist");
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
}
