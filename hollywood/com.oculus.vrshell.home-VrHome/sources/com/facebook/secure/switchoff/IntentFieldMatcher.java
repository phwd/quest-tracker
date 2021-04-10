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
    @Nullable
    private final UriMatcher mClipDataMatcher;
    private final Map<String, RegexWithNegation> mIntentFieldPattern;
    @Nullable
    private final IntentMatcher[] mSelectorIntentMatcher;

    private IntentFieldMatcher(Map<String, RegexWithNegation> intentFieldPattern, @Nullable IntentMatcher[] selectorIntentMatcher, @Nullable UriMatcher clipDataMatcher) {
        this.mIntentFieldPattern = intentFieldPattern;
        this.mSelectorIntentMatcher = selectorIntentMatcher;
        this.mClipDataMatcher = clipDataMatcher;
    }

    public boolean match(JSONObject request, @Nullable Intent selectorIntent) {
        try {
            for (Map.Entry<String, RegexWithNegation> entry : this.mIntentFieldPattern.entrySet()) {
                String intentFieldName = entry.getKey();
                if ("categories".equals(intentFieldName)) {
                    if (!matchCategories(request.has(intentFieldName) ? request.getJSONArray(intentFieldName) : null, entry)) {
                        return false;
                    }
                } else if ("extra_names".equals(intentFieldName)) {
                    if (!matchExtras(request.has(intentFieldName) ? request.getJSONArray(intentFieldName) : null, entry)) {
                        return false;
                    }
                } else if ("extra_value_types".equals(intentFieldName)) {
                    continue;
                } else if ("flags".equals(intentFieldName)) {
                    if (!matchFlags(request.has(intentFieldName) ? request.getInt(intentFieldName) : 0, entry.getValue().getPattern())) {
                        return false;
                    }
                } else {
                    String intentFieldValue = request.has(intentFieldName) ? request.getString(intentFieldName) : "";
                    if (intentFieldValue == null || !entry.getValue().match(intentFieldValue)) {
                        return false;
                    }
                }
            }
            if (this.mClipDataMatcher != null) {
                if (!matchClipData(request.has("clip_data") ? request.getJSONArray("clip_data") : null, this.mClipDataMatcher)) {
                    return false;
                }
            }
            if (this.mSelectorIntentMatcher != null && this.mSelectorIntentMatcher.length > 0) {
                if (selectorIntent == null) {
                    return false;
                }
                for (IntentMatcher selectorMatcher : this.mSelectorIntentMatcher) {
                    if (!selectorMatcher.matchIntentFieldAndUri(selectorIntent, null)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean match(JSONObject request) {
        return match(request, null);
    }

    @Nullable
    public static IntentFieldMatcher parse(@Nullable JSONObject config) {
        String selectorConfig;
        if (config == null) {
            return null;
        }
        Map<String, RegexWithNegation> internFieldPattern = new HashMap<>();
        IntentMatcher[] selectorIntentMatcher = new IntentMatcher[0];
        UriMatcher clipDataMatcher = null;
        try {
            Iterator<String> iterator = config.keys();
            while (iterator.hasNext()) {
                String intentFieldName = iterator.next();
                if (!intentFieldName.equals("selector_config")) {
                    if (intentFieldName.equals("clip_data")) {
                        clipDataMatcher = UriMatcher.parse(config.getJSONObject("clip_data"));
                    } else if (config.has(intentFieldName)) {
                        internFieldPattern.put(intentFieldName, RegexWithNegation.parse(config.get(intentFieldName)));
                    }
                }
            }
            if (config.has("selector_config")) {
                selectorConfig = config.getString("selector_config");
            } else {
                selectorConfig = null;
            }
            if (selectorConfig != null) {
                selectorIntentMatcher = IntentMatcher.parseConfig(selectorConfig);
            }
            if (internFieldPattern.isEmpty()) {
                return null;
            }
            if (selectorIntentMatcher.length > 0) {
                return new IntentFieldMatcher(internFieldPattern, selectorIntentMatcher, clipDataMatcher);
            }
            return new IntentFieldMatcher(internFieldPattern, null, clipDataMatcher);
        } catch (IllegalArgumentException | JSONException e) {
            return null;
        }
    }

    private static boolean matchCategories(@Nullable JSONArray categories, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
        if (categories == null) {
            return false;
        }
        RegexWithNegation categoryPattern = entry.getValue();
        for (int i = 0; i < categories.length(); i++) {
            if (categoryPattern.match(categories.getString(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean matchExtras(@Nullable JSONArray extraEntries, Map.Entry<String, RegexWithNegation> entry) throws JSONException {
        boolean extraValueMatch;
        if (extraEntries == null) {
            return false;
        }
        RegexWithNegation extraNamePattern = entry.getValue();
        RegexWithNegation extraValueTypePattern = null;
        if (this.mIntentFieldPattern.containsKey("extra_value_types")) {
            extraValueTypePattern = this.mIntentFieldPattern.get("extra_value_types");
        }
        for (int i = 0; i < extraEntries.length(); i++) {
            JSONObject extraEntry = extraEntries.getJSONObject(i);
            String extraName = extraEntry.has("name") ? extraEntry.getString("name") : "";
            String extraValueType = extraEntry.has("value_type") ? extraEntry.getString("value_type") : "";
            boolean extraNameMatch = extraNamePattern.match(extraName);
            if (extraValueTypePattern == null) {
                extraValueMatch = extraValueType.equals("");
            } else {
                extraValueMatch = extraValueTypePattern.match(extraValueType);
            }
            if (extraNameMatch && extraValueMatch) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchFlags(int flags, Pattern pattern) {
        try {
            int expectedFlags = Integer.parseInt(pattern.toString());
            if (flags == expectedFlags || (flags & expectedFlags) > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean matchClipData(@Nullable JSONArray clipDataUris, UriMatcher uriMatcher) {
        if (clipDataUris == null) {
            return false;
        }
        for (int i = 0; i < clipDataUris.length(); i++) {
            try {
                if (uriMatcher.match(clipDataUris.getJSONObject(i))) {
                    return true;
                }
            } catch (JSONException e) {
                return false;
            }
        }
        return false;
    }
}
