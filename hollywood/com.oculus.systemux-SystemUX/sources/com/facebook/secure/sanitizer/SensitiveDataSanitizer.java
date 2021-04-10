package com.facebook.secure.sanitizer;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.facebook.secure.sanitizer.SanitizedURI;
import com.facebook.secure.sanitizer.SanitizerConfig;
import com.facebook.security.uriparser.UriParser;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensitiveDataSanitizer {
    public static final String PATH_SANITIZED_MARKER = "/--sanitized--";
    public static final String QUERY_SANITIZED_MARKER = "=--sanitized--";

    public static SanitizedURI sanitizeURI(Uri uri, @Nullable SanitizerConfig sanitizerConfig) {
        String str;
        boolean z = sanitizerConfig != null && sanitizerConfig.isPreserveEncoding();
        String scheme = uri.getScheme();
        if (z) {
            str = uri.getEncodedAuthority();
        } else {
            str = uri.getAuthority();
        }
        if (scheme == null || str == null) {
            return new SanitizedURI.Builder().setPath(uri.getPath()).setScheme(scheme).setAuthority(str).setQuery(uri.getQuery()).build();
        }
        return new SanitizedURI.Builder().setScheme(scheme).setAuthority(str).setPath(sanitizeUriPath(uri, sanitizerConfig)).setQuery(sanitizeUriQueryParameters(uri, sanitizerConfig)).build();
    }

    public static SanitizedURI sanitizeURI(Uri uri) {
        return sanitizeURI(uri, SanitizedURI.DEFAULT_CONFIG);
    }

    public static String sanitizeURIToString(Uri uri, @Nullable SanitizerConfig sanitizerConfig) {
        return sanitizeURI(uri, sanitizerConfig).getURIString();
    }

    public static String sanitizeURIToString(Uri uri) {
        return sanitizeURIToString(uri, SanitizedURI.DEFAULT_CONFIG);
    }

    public static String sanitizeURIToString(String str, @Nullable SanitizerConfig sanitizerConfig) {
        try {
            return sanitizeURIToString(UriParser.parseStrict(str), sanitizerConfig);
        } catch (IllegalArgumentException | SecurityException unused) {
            return str;
        }
    }

    public static String sanitizeURIToString(String str) {
        return sanitizeURIToString(str, SanitizedURI.DEFAULT_CONFIG);
    }

    @Nullable
    private static String sanitizeUriQueryParameters(Uri uri, @Nullable SanitizerConfig sanitizerConfig) {
        Set<String> set;
        boolean z;
        String queryParameter;
        String query = uri.getQuery();
        if (TextUtils.isEmpty(query)) {
            return null;
        }
        if (sanitizerConfig != null && sanitizerConfig.isKeepAllValue()) {
            return query;
        }
        try {
            set = uri.getQueryParameterNames();
        } catch (UnsupportedOperationException unused) {
            set = null;
        }
        if (set == null || set.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = sanitizerConfig != null && sanitizerConfig.isKeepValueWithConfig() && !sanitizerConfig.getFieldMatchers().isEmpty();
        List<List<Pattern>> fieldMatchers = sanitizerConfig != null ? sanitizerConfig.getFieldMatchers() : Collections.emptyList();
        for (String str : set) {
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (!z2) {
                sb.append(QUERY_SANITIZED_MARKER);
            } else {
                int i = 0;
                while (true) {
                    if (i >= fieldMatchers.size()) {
                        z = false;
                        break;
                    }
                    List<Pattern> list = fieldMatchers.get(i);
                    if (list.get(0).matcher(str).matches() && (queryParameter = uri.getQueryParameter(str)) != null) {
                        Matcher matcher = list.get(1).matcher(queryParameter);
                        if (matcher.matches()) {
                            sb.append('=');
                            int groupCount = matcher.groupCount();
                            if (groupCount > 0) {
                                for (int i2 = 1; i2 < groupCount; i2++) {
                                    sb.append(matcher.group(i2));
                                    sb.append(';');
                                }
                                sb.append(matcher.group(groupCount));
                            } else {
                                sb.append(matcher.group(0));
                            }
                            z = true;
                        }
                    }
                    i++;
                }
                if (!z) {
                    sb.append(QUERY_SANITIZED_MARKER);
                }
            }
        }
        return sb.toString();
    }

    @Nullable
    private static String sanitizeUriPath(Uri uri, @Nullable SanitizerConfig sanitizerConfig) {
        if (TextUtils.isEmpty(uri.getPath())) {
            return null;
        }
        if (sanitizerConfig != null && sanitizerConfig.isKeepUriPathWithConfig() && !sanitizerConfig.getKeepUriPathMatchers().isEmpty()) {
            String encodedPath = sanitizerConfig.isPreserveEncoding() ? uri.getEncodedPath() : uri.getPath();
            for (List<Pattern> list : sanitizerConfig.getKeepUriPathMatchers()) {
                Matcher matcher = list.get(2).matcher(encodedPath);
                if (matcher.matches() && list.get(0).matcher(uri.getScheme()).matches()) {
                    if (list.get(1).matcher(uri.getAuthority()).matches()) {
                        StringBuilder sb = new StringBuilder();
                        int groupCount = matcher.groupCount();
                        if (groupCount > 0) {
                            for (int i = 1; i < groupCount; i++) {
                                sb.append(matcher.group(i));
                                sb.append(';');
                            }
                            sb.append(matcher.group(groupCount));
                        } else {
                            sb.append(matcher.group(0));
                        }
                        return sb.toString();
                    }
                }
            }
        }
        return PATH_SANITIZED_MARKER;
    }

    public static JSONArray sanitizeIntentExtras(Bundle bundle, @Nullable SanitizerConfig sanitizerConfig) throws JSONException {
        return sanitizeIntentExtras(bundle, sanitizerConfig, new SanitizerConfig.Builder().build());
    }

    public static JSONArray sanitizeIntentExtras(Bundle bundle) throws JSONException {
        return sanitizeIntentExtras(bundle, new SanitizerConfig.Builder().build(), new SanitizerConfig.Builder().build());
    }

    @SuppressLint({"BadMethodUse-android.net.Uri.parse"})
    public static JSONArray sanitizeIntentExtras(Bundle bundle, @Nullable SanitizerConfig sanitizerConfig, @Nullable SanitizerConfig sanitizerConfig2) throws JSONException {
        List<List<Pattern>> list;
        String str;
        String str2;
        boolean z;
        String str3;
        SanitizerConfig sanitizerConfig3 = sanitizerConfig2;
        JSONArray jSONArray = new JSONArray();
        boolean z2 = sanitizerConfig != null && sanitizerConfig.isKeepAllValue();
        boolean z3 = sanitizerConfig != null && sanitizerConfig.isKeepValueWithConfig();
        int size = sanitizerConfig != null ? sanitizerConfig.getFieldMatchers().size() : 0;
        if (sanitizerConfig != null) {
            list = sanitizerConfig.getFieldMatchers();
        } else {
            list = Collections.emptyList();
        }
        for (String str4 : bundle.keySet()) {
            JSONObject jSONObject = new JSONObject();
            Object obj = bundle.get(str4);
            if (obj == null) {
                str = "";
            } else {
                str = obj.getClass().getCanonicalName();
            }
            jSONObject.put("name", str4);
            jSONObject.put("value_type", str);
            if (obj == null) {
                str2 = "";
            } else {
                str2 = obj.toString();
            }
            if (!(obj == null || !URLUtil.isValidUrl(str2) || sanitizerConfig3 == null)) {
                str2 = sanitizeURIToString(Uri.parse(str2), sanitizerConfig3);
            }
            if (z2) {
                jSONObject.put(AssistantComponentContract.Components.TextComponent.VALUE, str2);
            } else if (z3 && sanitizerConfig != null && size > 0) {
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    List<Pattern> list2 = list.get(i);
                    int size2 = list2.size();
                    if (size2 != 0) {
                        z = z2;
                        if (list2.get(0).matcher(str4).matches() && size2 != 1 && list2.get(1).matcher(str).matches() && size2 != 2) {
                            Pattern pattern = list2.get(2);
                            if (obj == null) {
                                str3 = "";
                            } else {
                                str3 = obj.toString();
                            }
                            Matcher matcher = pattern.matcher(str3);
                            if (matcher.matches()) {
                                int groupCount = matcher.groupCount();
                                if (groupCount > 0) {
                                    StringBuilder sb = new StringBuilder();
                                    for (int i2 = 1; i2 < groupCount; i2++) {
                                        sb.append(matcher.group(i));
                                        sb.append(';');
                                    }
                                    sb.append(matcher.group(groupCount));
                                    jSONObject.put(AssistantComponentContract.Components.TextComponent.VALUE, sb.toString());
                                } else {
                                    jSONObject.put(AssistantComponentContract.Components.TextComponent.VALUE, matcher.group(0));
                                }
                            }
                        }
                    } else {
                        z = z2;
                    }
                    i++;
                    z2 = z;
                }
                jSONArray.put(jSONObject);
                sanitizerConfig3 = sanitizerConfig2;
                z2 = z;
            }
            z = z2;
            jSONArray.put(jSONObject);
            sanitizerConfig3 = sanitizerConfig2;
            z2 = z;
        }
        return jSONArray;
    }
}
