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

    public DeeplinkConfig(boolean enforceSchemeWhitelist2, boolean enforceSchemeAndAuthorityWhitelist2, Map<String, Pattern> deeplinkWhitelist2) {
        this.enforceSchemeWhitelist = enforceSchemeWhitelist2;
        this.enforceSchemeAndAuthorityWhitelist = enforceSchemeAndAuthorityWhitelist2;
        this.deeplinkWhitelist = deeplinkWhitelist2;
    }

    public static Map<String, DeeplinkConfig> parseConfig(String rawConfig) {
        boolean enforceSchemeForActivity;
        boolean enforceSchemeAuthorityForActivity;
        Map<String, DeeplinkConfig> result = new HashMap<>();
        try {
            JSONObject configJson = new JSONObject(rawConfig);
            Iterator<String> activityNames = configJson.keys();
            while (activityNames.hasNext()) {
                String activityName = activityNames.next();
                JSONObject activityConfigJson = configJson.getJSONObject(activityName);
                if (!activityConfigJson.has("enforce_scheme") || !activityConfigJson.getBoolean("enforce_scheme")) {
                    enforceSchemeForActivity = false;
                } else {
                    enforceSchemeForActivity = true;
                }
                if (!activityConfigJson.has("enforce_scheme_and_authority") || !activityConfigJson.getBoolean("enforce_scheme_and_authority")) {
                    enforceSchemeAuthorityForActivity = false;
                } else {
                    enforceSchemeAuthorityForActivity = true;
                }
                JSONObject whitelistJson = activityConfigJson.getJSONObject("whitelist");
                Iterator<String> schemeNames = whitelistJson.keys();
                Map<String, Pattern> whitelist = new HashMap<>();
                while (schemeNames.hasNext()) {
                    String schemeName = schemeNames.next();
                    whitelist.put(schemeName, Pattern.compile(whitelistJson.getString(schemeName)));
                }
                result.put(activityName, new DeeplinkConfig(enforceSchemeForActivity, enforceSchemeAuthorityForActivity, whitelist));
            }
        } catch (JSONException e) {
        }
        return result;
    }
}
