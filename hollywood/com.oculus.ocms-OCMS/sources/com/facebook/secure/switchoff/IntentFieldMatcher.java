package com.facebook.secure.switchoff;

import android.content.Intent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentFieldMatcher {
    private static final String CLIP_DATA = "clip_data";
    private static final String CONFIG_FIELD_EXTRA_VALUE_TYPES = "extra_value_types";
    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_VALUE_TYPE = "value_type";
    private static final String INTENT_FIELD_CATEGORIES = "categories";
    private static final String INTENT_FIELD_EXTRA_NAMES = "extra_names";
    private static final String INTENT_FIELD_FLAGS = "flags";
    private static final String SELECTOR_CONFIG = "selector_config";
    @Nullable
    private final UriMatcher mClipDataMatcher;
    private final Map<String, RegexWithNegation> mIntentFieldPattern;
    @Nullable
    private final IntentMatcher[] mSelectorIntentMatcher;

    private IntentFieldMatcher(Map<String, RegexWithNegation> map, @Nullable IntentMatcher[] intentMatcherArr, @Nullable UriMatcher uriMatcher) {
        this.mIntentFieldPattern = map;
        this.mSelectorIntentMatcher = intentMatcherArr;
        this.mClipDataMatcher = uriMatcher;
    }

    public boolean match(JSONObject jSONObject, @Nullable Intent intent) {
        try {
            Iterator<Map.Entry<String, RegexWithNegation>> it = this.mIntentFieldPattern.entrySet().iterator();
            while (true) {
                JSONArray jSONArray = null;
                if (it.hasNext()) {
                    Map.Entry<String, RegexWithNegation> next = it.next();
                    String key = next.getKey();
                    if (INTENT_FIELD_CATEGORIES.equals(key)) {
                        if (jSONObject.has(key)) {
                            jSONArray = jSONObject.getJSONArray(key);
                        }
                        if (!matchCategories(jSONArray, next)) {
                            return false;
                        }
                    } else if (INTENT_FIELD_EXTRA_NAMES.equals(key)) {
                        if (jSONObject.has(key)) {
                            jSONArray = jSONObject.getJSONArray(key);
                        }
                        if (!matchExtras(jSONArray, next)) {
                            return false;
                        }
                    } else if (!CONFIG_FIELD_EXTRA_VALUE_TYPES.equals(key)) {
                        if (INTENT_FIELD_FLAGS.equals(key)) {
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
                    if (this.mClipDataMatcher != null) {
                        if (!matchClipData(jSONObject.has(CLIP_DATA) ? jSONObject.getJSONArray(CLIP_DATA) : null, this.mClipDataMatcher)) {
                            return false;
                        }
                    }
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

    @Nullable
    public static IntentFieldMatcher parse(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        IntentMatcher[] intentMatcherArr = new IntentMatcher[0];
        Iterator<String> keys = jSONObject.keys();
        UriMatcher uriMatcher = null;
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                if (!next.equals(SELECTOR_CONFIG)) {
                    if (next.equals(CLIP_DATA)) {
                        uriMatcher = UriMatcher.parse(jSONObject.getJSONObject(CLIP_DATA));
                    } else if (jSONObject.has(next)) {
                        hashMap.put(next, RegexWithNegation.parse(jSONObject.get(next)));
                    }
                }
            } catch (IllegalArgumentException | JSONException unused) {
                return null;
            }
        }
        String string = jSONObject.has(SELECTOR_CONFIG) ? jSONObject.getString(SELECTOR_CONFIG) : null;
        if (string != null) {
            intentMatcherArr = IntentMatcher.parseConfig(string);
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        if (intentMatcherArr.length > 0) {
            return new IntentFieldMatcher(hashMap, intentMatcherArr, uriMatcher);
        }
        return new IntentFieldMatcher(hashMap, null, uriMatcher);
    }

    private static boolean matchCategories(@Nullable JSONArray jSONArray, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
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

    private boolean matchExtras(@Nullable JSONArray jSONArray, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
        String str;
        boolean z;
        if (jSONArray == null) {
            return false;
        }
        RegexWithNegation value = entry.getValue();
        RegexWithNegation regexWithNegation = null;
        if (this.mIntentFieldPattern.containsKey(CONFIG_FIELD_EXTRA_VALUE_TYPES)) {
            regexWithNegation = this.mIntentFieldPattern.get(CONFIG_FIELD_EXTRA_VALUE_TYPES);
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.has("name") ? jSONObject.getString("name") : "";
            if (jSONObject.has(EXTRA_VALUE_TYPE)) {
                str = jSONObject.getString(EXTRA_VALUE_TYPE);
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

    private static boolean matchClipData(@Nullable JSONArray jSONArray, UriMatcher uriMatcher) {
        if (jSONArray == null) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (uriMatcher.match(jSONArray.getJSONObject(i))) {
                    return true;
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }
}
