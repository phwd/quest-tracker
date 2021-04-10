package com.facebook.secure.switchoff;

import android.content.Intent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentFieldMatcher {
    private final Map<String, RegexWithNegation> mIntentFieldPattern;
    private final IntentMatcher[] mSelectorIntentMatcher;

    private IntentFieldMatcher(Map<String, RegexWithNegation> map, IntentMatcher[] intentMatcherArr) {
        this.mIntentFieldPattern = map;
        this.mSelectorIntentMatcher = intentMatcherArr;
    }

    public boolean match(JSONObject jSONObject, Intent intent) {
        try {
            Iterator<Map.Entry<String, RegexWithNegation>> it = this.mIntentFieldPattern.entrySet().iterator();
            while (true) {
                JSONArray jSONArray = null;
                if (it.hasNext()) {
                    Map.Entry<String, RegexWithNegation> next = it.next();
                    String key = next.getKey();
                    if ("categories".equals(key)) {
                        if (jSONObject.has(key)) {
                            jSONArray = jSONObject.getJSONArray(key);
                        }
                        if (!matchCategories(jSONArray, next)) {
                            return false;
                        }
                    } else if ("extra_names".equals(key)) {
                        if (jSONObject.has(key)) {
                            jSONArray = jSONObject.getJSONArray(key);
                        }
                        if (!matchExtras(jSONArray, next)) {
                            return false;
                        }
                    } else if (!"extra_value_types".equals(key)) {
                        if ("flags".equals(key)) {
                            if (!matchFlags(jSONObject.has(key) ? jSONObject.getInt(key) : 0, next.getValue().getPattern())) {
                                return false;
                            }
                        } else {
                            String string = jSONObject.has(key) ? jSONObject.getString(key) : "";
                            if (string == null || !next.getValue().match(string)) {
                                return false;
                            }
                        }
                    }
                } else {
                    IntentMatcher[] intentMatcherArr = this.mSelectorIntentMatcher;
                    if (intentMatcherArr == null || intentMatcherArr.length <= 0) {
                        return true;
                    }
                    if (intent == null) {
                        return false;
                    }
                    for (IntentMatcher intentMatcher : intentMatcherArr) {
                        if (!intentMatcher.matchIntentFieldAndUri(intent, null)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public boolean match(JSONObject jSONObject) {
        return match(jSONObject, null);
    }

    public static IntentFieldMatcher parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        IntentMatcher[] intentMatcherArr = new IntentMatcher[0];
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                if (!next.equals("selector_config")) {
                    if (jSONObject.has(next)) {
                        hashMap.put(next, RegexWithNegation.parse(jSONObject.get(next)));
                    }
                }
            } catch (IllegalArgumentException | JSONException unused) {
                return null;
            }
        }
        String string = jSONObject.has("selector_config") ? jSONObject.getString("selector_config") : null;
        if (string != null) {
            intentMatcherArr = IntentMatcher.parseConfig(string);
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return intentMatcherArr.length > 0 ? new IntentFieldMatcher(hashMap, intentMatcherArr) : new IntentFieldMatcher(hashMap, null);
    }

    private static boolean matchCategories(JSONArray jSONArray, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
        if (jSONArray == null) {
            return false;
        }
        RegexWithNegation value = entry.getValue();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (value.match(jSONArray.getString(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean matchExtras(JSONArray jSONArray, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
        String str;
        boolean z;
        if (jSONArray == null) {
            return false;
        }
        RegexWithNegation value = entry.getValue();
        RegexWithNegation regexWithNegation = null;
        if (this.mIntentFieldPattern.containsKey("extra_value_types")) {
            regexWithNegation = this.mIntentFieldPattern.get("extra_value_types");
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.has("name") ? jSONObject.getString("name") : "";
            if (jSONObject.has("value_type")) {
                str = jSONObject.getString("value_type");
            } else {
                str = "";
            }
            boolean match = value.match(string);
            if (regexWithNegation == null) {
                z = str.equals("");
            } else {
                z = regexWithNegation.match(str);
            }
            if (match && z) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchFlags(int i, Pattern pattern) {
        try {
            int parseInt = Integer.parseInt(pattern.toString());
            return i == parseInt || (i & parseInt) > 0;
        } catch (NumberFormatException unused) {
            return false;
        }
    }
}
