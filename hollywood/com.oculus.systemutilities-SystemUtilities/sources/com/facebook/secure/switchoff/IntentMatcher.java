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

    public IntentMatcher(String endpointClassNamePattern, IntentFieldMatcher callerInfoMatcher, UriMatcher uriMatcher, IntentFieldMatcher intentFieldMatcher) {
        Pattern compile;
        if (endpointClassNamePattern == null) {
            compile = null;
        } else {
            compile = Pattern.compile(endpointClassNamePattern, 32);
        }
        this.mEndpointClassNamePattern = compile;
        this.mIntentFieldMatcher = intentFieldMatcher;
        this.mCallerInfoMatcher = callerInfoMatcher;
        this.mUriMatcher = uriMatcher;
    }

    public static IntentMatcher[] parseConfig(String rawConfig) {
        try {
            JSONArray configs = new JSONArray(rawConfig);
            if (configs != null && configs.length() > 0) {
                IntentMatcher[] results = new IntentMatcher[configs.length()];
                for (int i = 0; i < configs.length(); i++) {
                    JSONObject config = configs.getJSONObject(i);
                    results[i] = new IntentMatcher(config.has("endpoint_name") ? config.getString("endpoint_name") : null, config.has("caller_info") ? IntentFieldMatcher.parse(config.getJSONObject("caller_info")) : null, config.has("uri_component") ? UriMatcher.parse(config.getJSONObject("uri_component")) : null, config.has("intent_field") ? IntentFieldMatcher.parse(config.getJSONObject("intent_field")) : null);
                }
                return results;
            }
        } catch (JSONException e) {
        }
        return new IntentMatcher[0];
    }

    public static boolean matches(Object endpoint, Intent intent, Context context, IntentParser.ParsedIntent parsedIntent, IntentMatcher[] intentMatchers) {
        if (intentMatchers == null || intentMatchers.length <= 0) {
            return false;
        }
        for (IntentMatcher intentMatcher : intentMatchers) {
            if (intentMatcher.matches(endpoint, intent, context, parsedIntent)) {
                return true;
            }
        }
        return false;
    }

    public boolean matches(Object endpoint, Intent intent, Context context, IntentParser.ParsedIntent parsedIntent) {
        if (!matchesEndpointName(endpoint)) {
            return false;
        }
        try {
            AppIdentity appIdentity = CallerInfoHelper.getCallerInfo(context, intent);
            if (this.mCallerInfoMatcher == null || (appIdentity != null && this.mCallerInfoMatcher.match(appIdentity.toJson()))) {
                return matchIntentFieldAndUri(intent, parsedIntent);
            }
            return false;
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean matchIntentFieldAndUri(Intent intent, IntentParser.ParsedIntent parsedIntent) {
        Intent intent2 = null;
        if (intent == null && parsedIntent == null) {
            return false;
        }
        IntentParser.ParsedIntent intentContent = parsedIntent == null ? intent == null ? null : IntentParser.parseIntent(intent) : parsedIntent;
        try {
            if (!matchesUriComponent(intentContent == null ? null : intentContent.mUris)) {
                return false;
            }
            JSONObject jSONObject = intentContent == null ? null : intentContent.mIntentContent;
            if (intent != null) {
                intent2 = intent.getSelector();
            }
            if (matchesIntentField(jSONObject, intent2)) {
                return true;
            }
            return false;
        } catch (JSONException e) {
            return false;
        }
    }

    public boolean matchesEndpointName(Object endpoint) {
        return this.mEndpointClassNamePattern == null || this.mEndpointClassNamePattern.matcher(endpoint.getClass().getName()).matches();
    }

    private boolean matchesIntentField(JSONObject intentContent, Intent selectorIntent) {
        if (this.mIntentFieldMatcher == null) {
            return true;
        }
        if (intentContent == null) {
            return false;
        }
        return this.mIntentFieldMatcher.match(intentContent, selectorIntent);
    }

    private boolean matchesUriComponent(List<Uri> uris) {
        if (this.mUriMatcher == null) {
            return true;
        }
        if (uris == null || uris.isEmpty()) {
            return false;
        }
        for (Uri uri : uris) {
            if (this.mUriMatcher.match(uri)) {
                return true;
            }
        }
        return false;
    }
}
