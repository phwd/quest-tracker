package com.facebook.secure.switchoff;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.facebook.secure.intentparser.IntentParser;
import com.facebook.secure.trustedapp.AppIdentity;
import com.facebook.secure.trustedapp.CallerInfoHelper;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentMatcher {
    private final IntentFieldMatcher mCallerInfoMatcher;
    private final Pattern mEndpointClassNamePattern;
    private final IntentFieldMatcher mIntentFieldMatcher;
    private final UriMatcher mUriMatcher;

    public IntentMatcher(String str, IntentFieldMatcher intentFieldMatcher, UriMatcher uriMatcher, IntentFieldMatcher intentFieldMatcher2) {
        Pattern pattern;
        if (str == null) {
            pattern = null;
        } else {
            pattern = Pattern.compile(str, 32);
        }
        this.mEndpointClassNamePattern = pattern;
        this.mIntentFieldMatcher = intentFieldMatcher2;
        this.mCallerInfoMatcher = intentFieldMatcher;
        this.mUriMatcher = uriMatcher;
    }

    public static IntentMatcher[] parseConfig(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                IntentMatcher[] intentMatcherArr = new IntentMatcher[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    IntentFieldMatcher intentFieldMatcher = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    IntentFieldMatcher parse = jSONObject.has("caller_info") ? IntentFieldMatcher.parse(jSONObject.getJSONObject("caller_info")) : null;
                    UriMatcher parse2 = jSONObject.has("uri_component") ? UriMatcher.parse(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        intentFieldMatcher = IntentFieldMatcher.parse(jSONObject.getJSONObject("intent_field"));
                    }
                    intentMatcherArr[i] = new IntentMatcher(string, parse, parse2, intentFieldMatcher);
                }
                return intentMatcherArr;
            }
        } catch (JSONException unused) {
        }
        return new IntentMatcher[0];
    }

    public static boolean matches(Object obj, Intent intent, Context context, IntentParser.ParsedIntent parsedIntent, IntentMatcher[] intentMatcherArr) {
        if (intentMatcherArr != null && intentMatcherArr.length > 0) {
            for (IntentMatcher intentMatcher : intentMatcherArr) {
                if (intentMatcher.matches(obj, intent, context, parsedIntent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean matches(Object obj, Intent intent, Context context, IntentParser.ParsedIntent parsedIntent) {
        if (!matchesEndpointName(obj)) {
            return false;
        }
        try {
            AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent);
            if (this.mCallerInfoMatcher == null || (callerInfo != null && this.mCallerInfoMatcher.match(callerInfo.toJson()))) {
                return matchIntentFieldAndUri(intent, parsedIntent);
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public boolean matchIntentFieldAndUri(Intent intent, IntentParser.ParsedIntent parsedIntent) {
        List<Uri> list;
        JSONObject jSONObject;
        if (intent == null && parsedIntent == null) {
            return false;
        }
        Intent intent2 = null;
        if (parsedIntent == null) {
            if (intent == null) {
                parsedIntent = null;
            } else {
                try {
                    parsedIntent = IntentParser.parseIntent(intent);
                } catch (JSONException unused) {
                    return false;
                }
            }
        }
        if (parsedIntent == null) {
            list = null;
        } else {
            list = parsedIntent.mUris;
        }
        if (!matchesUriComponent(list)) {
            return false;
        }
        if (parsedIntent == null) {
            jSONObject = null;
        } else {
            jSONObject = parsedIntent.mIntentContent;
        }
        if (intent != null) {
            intent2 = intent.getSelector();
        }
        if (!matchesIntentField(jSONObject, intent2)) {
            return false;
        }
        return true;
    }

    private boolean matchesEndpointName(Object obj) {
        Pattern pattern = this.mEndpointClassNamePattern;
        return pattern == null || pattern.matcher(obj.getClass().getName()).matches();
    }

    private boolean matchesIntentField(JSONObject jSONObject, Intent intent) {
        IntentFieldMatcher intentFieldMatcher = this.mIntentFieldMatcher;
        if (intentFieldMatcher == null) {
            return true;
        }
        if (jSONObject == null) {
            return false;
        }
        return intentFieldMatcher.match(jSONObject, intent);
    }

    private boolean matchesUriComponent(List<Uri> list) {
        if (this.mUriMatcher == null) {
            return true;
        }
        if (list != null && !list.isEmpty()) {
            for (Uri uri : list) {
                if (this.mUriMatcher.match(uri)) {
                    return true;
                }
            }
        }
        return false;
    }
}
