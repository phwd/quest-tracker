package com.facebook.secure.switchoff;

import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class RegexWithNegation {
    private boolean mNegation;
    private Pattern mPattern;

    public RegexWithNegation(Pattern pattern, boolean negation) {
        this.mPattern = pattern;
        this.mNegation = negation;
    }

    public RegexWithNegation() {
        this(Pattern.compile(""), false);
    }

    public static RegexWithNegation parse(Object regexComponent) throws JSONException {
        Pattern pattern = Pattern.compile("");
        boolean negation = false;
        if (regexComponent instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) regexComponent;
            if (jsonObject.has("pattern")) {
                pattern = Pattern.compile(jsonObject.getString("pattern"), 32);
                if (jsonObject.has("negation") && jsonObject.getString("negation").equalsIgnoreCase("true")) {
                    negation = true;
                }
            }
        } else if (regexComponent instanceof String) {
            pattern = Pattern.compile((String) regexComponent, 32);
        }
        return new RegexWithNegation(pattern, negation);
    }

    public boolean match(String pattern) {
        return this.mPattern.matcher(pattern).matches() ^ this.mNegation;
    }

    public Pattern getPattern() {
        return this.mPattern;
    }
}
