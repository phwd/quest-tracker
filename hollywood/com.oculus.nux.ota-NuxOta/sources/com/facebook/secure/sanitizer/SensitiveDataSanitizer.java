package com.facebook.secure.sanitizer;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.facebook.secure.sanitizer.SanitizedURI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensitiveDataSanitizer {
    public static SanitizedURI sanitizeURI(Uri uri, SanitizerConfig sanitizerConfig) {
        String str;
        boolean z = sanitizerConfig != null && sanitizerConfig.isPreserveEncoding();
        String scheme = uri.getScheme();
        if (z) {
            str = uri.getEncodedAuthority();
        } else {
            str = uri.getAuthority();
        }
        if (scheme == null || str == null) {
            SanitizedURI.Builder builder = new SanitizedURI.Builder();
            builder.setPath(uri.getPath());
            builder.setScheme(scheme);
            builder.setAuthority(str);
            builder.setQuery(uri.getQuery());
            return builder.build();
        }
        String sanitizeUriPath = sanitizeUriPath(uri, sanitizerConfig);
        String sanitizeUriQueryParameters = sanitizeUriQueryParameters(uri, sanitizerConfig);
        SanitizedURI.Builder builder2 = new SanitizedURI.Builder();
        builder2.setScheme(scheme);
        builder2.setAuthority(str);
        builder2.setPath(sanitizeUriPath);
        builder2.setQuery(sanitizeUriQueryParameters);
        return builder2.build();
    }

    public static String sanitizeURIToString(Uri uri, SanitizerConfig sanitizerConfig) {
        return sanitizeURI(uri, sanitizerConfig).getURIString();
    }

    private static String sanitizeUriQueryParameters(Uri uri, SanitizerConfig sanitizerConfig) {
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
                sb.append("=--sanitized--");
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
                    sb.append("=--sanitized--");
                }
            }
        }
        return sb.toString();
    }

    private static String sanitizeUriPath(Uri uri, SanitizerConfig sanitizerConfig) {
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
        return "/--sanitized--";
    }

    @SuppressLint({"BadMethodUse-android.net.Uri.parse"})
    public static JSONArray sanitizeIntentExtras(Bundle bundle, SanitizerConfig sanitizerConfig, SanitizerConfig sanitizerConfig2) throws JSONException {
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
                jSONObject.put("value", str2);
            } else if (z3 && sanitizerConfig != null && size > 0) {
                int i = 0;
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    List<Pattern> list2 = list.get(i);
                    int size2 = list2.size();
                    z = z2;
                    if (!(size2 == 0 || !list2.get(0).matcher(str4).matches() || size2 == 1 || !list2.get(1).matcher(str).matches() || size2 == 2)) {
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
                                jSONObject.put("value", sb.toString());
                            } else {
                                jSONObject.put("value", matcher.group(0));
                            }
                        }
                    }
                    i++;
                    z2 = z;
                }
            }
            z = z2;
            jSONArray.put(jSONObject);
            sanitizerConfig3 = sanitizerConfig2;
            z2 = z;
        }
        return jSONArray;
    }
}
