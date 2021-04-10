package com.facebook.secure.switchoff;

import com.oculus.panelapp.social.SocialBundleConstants;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class RegexWithNegation {
    private static final String NEGATION = "negation";
    private static final String PATTERN = "pattern";
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
            if (jSONObject.has(PATTERN)) {
                compile = Pattern.compile(jSONObject.getString(PATTERN), 32);
                if (jSONObject.has(NEGATION) && jSONObject.getString(NEGATION).equalsIgnoreCase(SocialBundleConstants.FB_UPSELL_MUST_INTERACT)) {
                    z = true;
                }
            }
        } else if (obj instanceof String) {
            compile = Pattern.compile((String) obj, 32);
        }
        return new RegexWithNegation(compile, z);
    }

    public boolean match(String str) {
        return this.mPattern.matcher(str).matches() ^ this.mNegation;
    }

    public Pattern getPattern() {
        return this.mPattern;
    }

    public boolean isNegation() {
        return this.mNegation;
    }
}
