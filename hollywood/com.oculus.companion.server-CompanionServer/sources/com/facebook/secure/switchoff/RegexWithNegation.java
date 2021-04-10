package com.facebook.secure.switchoff;

import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class RegexWithNegation {
    private boolean mNegation;
    private Pattern mPattern;

    public RegexWithNegation(Pattern pattern, boolean z) {
        this.mPattern = pattern;
        this.mNegation = z;
    }

    public RegexWithNegation() {
        this(Pattern.compile(""), false);
    }

    public static RegexWithNegation parse(Object obj) throws JSONException {
        Pattern compile = Pattern.compile("");
        boolean z = false;
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.has("pattern")) {
                compile = Pattern.compile(jSONObject.getString("pattern"), 32);
                if (jSONObject.has("negation") && jSONObject.getString("negation").equalsIgnoreCase("true")) {
                    z = true;
                }
            }
        } else if (obj instanceof String) {
            compile = Pattern.compile((String) obj, 32);
        }
        return new RegexWithNegation(compile, z);
    }

    public boolean match(String str) {
        return this.mNegation ^ this.mPattern.matcher(str).matches();
    }

    public Pattern getPattern() {
        return this.mPattern;
    }
}
