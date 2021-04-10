package com.facebook.secure.switchoff;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class UriMatcher {
    private static final List<String> COMPONENT_NAMES = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    private final Map<String, RegexWithNegation> mUriComponentPattern;

    private UriMatcher(Map<String, RegexWithNegation> uriComponentPattern) {
        this.mUriComponentPattern = uriComponentPattern;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.net.Uri r8) {
        /*
            r7 = this;
            r4 = 0
            java.util.Map<java.lang.String, com.facebook.secure.switchoff.RegexWithNegation> r5 = r7.mUriComponentPattern
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x000b:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0031
            java.lang.Object r2 = r5.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r0 = r2.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = getUriComponentValue(r8, r0)
            if (r1 != 0) goto L_0x0024
        L_0x0023:
            return r4
        L_0x0024:
            java.lang.Object r3 = r2.getValue()
            com.facebook.secure.switchoff.RegexWithNegation r3 = (com.facebook.secure.switchoff.RegexWithNegation) r3
            boolean r6 = r3.match(r1)
            if (r6 != 0) goto L_0x000b
            goto L_0x0023
        L_0x0031:
            r4 = 1
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.switchoff.UriMatcher.match(android.net.Uri):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0011 A[Catch:{ JSONException -> 0x0035 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(org.json.JSONObject r8) {
        /*
            r7 = this;
            r4 = 0
            java.util.Map<java.lang.String, com.facebook.secure.switchoff.RegexWithNegation> r5 = r7.mUriComponentPattern     // Catch:{ JSONException -> 0x0035 }
            java.util.Set r5 = r5.entrySet()     // Catch:{ JSONException -> 0x0035 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ JSONException -> 0x0035 }
        L_0x000b:
            boolean r6 = r5.hasNext()     // Catch:{ JSONException -> 0x0035 }
            if (r6 == 0) goto L_0x0037
            java.lang.Object r2 = r5.next()     // Catch:{ JSONException -> 0x0035 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ JSONException -> 0x0035 }
            java.lang.Object r0 = r2.getKey()     // Catch:{ JSONException -> 0x0035 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0035 }
            java.lang.Object r3 = r2.getValue()     // Catch:{ JSONException -> 0x0035 }
            com.facebook.secure.switchoff.RegexWithNegation r3 = (com.facebook.secure.switchoff.RegexWithNegation) r3     // Catch:{ JSONException -> 0x0035 }
            boolean r6 = r8.has(r0)     // Catch:{ JSONException -> 0x0035 }
            if (r6 != 0) goto L_0x002a
        L_0x0029:
            return r4
        L_0x002a:
            java.lang.String r6 = r8.getString(r0)     // Catch:{ JSONException -> 0x0035 }
            boolean r6 = r3.match(r6)     // Catch:{ JSONException -> 0x0035 }
            if (r6 != 0) goto L_0x000b
            goto L_0x0029
        L_0x0035:
            r1 = move-exception
            goto L_0x0029
        L_0x0037:
            r4 = 1
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.switchoff.UriMatcher.match(org.json.JSONObject):boolean");
    }

    @Nullable
    public static UriMatcher parse(@Nullable JSONObject config) {
        if (config == null) {
            return null;
        }
        try {
            Map<String, RegexWithNegation> uriComponentPattern = new HashMap<>();
            for (String componentName : COMPONENT_NAMES) {
                if (config.has(componentName)) {
                    uriComponentPattern.put(componentName, RegexWithNegation.parse(config.get(componentName)));
                }
            }
            if (!uriComponentPattern.isEmpty()) {
                return new UriMatcher(uriComponentPattern);
            }
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    @Nullable
    private static String getUriComponentValue(Uri uri, String componentName) {
        char c = 65535;
        switch (componentName.hashCode()) {
            case -907987547:
                if (componentName.equals("scheme")) {
                    c = 0;
                    break;
                }
                break;
            case 3433509:
                if (componentName.equals("path")) {
                    c = 2;
                    break;
                }
                break;
            case 107944136:
                if (componentName.equals("query")) {
                    c = 3;
                    break;
                }
                break;
            case 1475610435:
                if (componentName.equals("authority")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return uri.getScheme();
            case 1:
                return uri.getAuthority();
            case 2:
                return uri.getPath();
            case 3:
                return uri.getQuery();
            default:
                return null;
        }
    }
}
