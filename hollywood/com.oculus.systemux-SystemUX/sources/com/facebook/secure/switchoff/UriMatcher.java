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
    private static final String AUTHORITY = "authority";
    private static final List<String> COMPONENT_NAMES = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    private static final String PATH = "path";
    private static final String QUERY = "query";
    private static final String SCHEME = "scheme";
    private final Map<String, RegexWithNegation> mUriComponentPattern;

    private UriMatcher(Map<String, RegexWithNegation> map) {
        this.mUriComponentPattern = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.net.Uri r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, com.facebook.secure.switchoff.RegexWithNegation> r0 = r4.mUriComponentPattern
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0031
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r2 = getUriComponentValue(r5, r2)
            r3 = 0
            if (r2 != 0) goto L_0x0024
            return r3
        L_0x0024:
            java.lang.Object r1 = r1.getValue()
            com.facebook.secure.switchoff.RegexWithNegation r1 = (com.facebook.secure.switchoff.RegexWithNegation) r1
            boolean r1 = r1.match(r2)
            if (r1 != 0) goto L_0x000a
            return r3
        L_0x0031:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.switchoff.UriMatcher.match(android.net.Uri):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0011 A[Catch:{ JSONException -> 0x0037 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(org.json.JSONObject r6) {
        /*
            r5 = this;
            r0 = 0
            java.util.Map<java.lang.String, com.facebook.secure.switchoff.RegexWithNegation> r1 = r5.mUriComponentPattern     // Catch:{ JSONException -> 0x0037 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ JSONException -> 0x0037 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ JSONException -> 0x0037 }
        L_0x000b:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x0037 }
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x0037 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ JSONException -> 0x0037 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ JSONException -> 0x0037 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ JSONException -> 0x0037 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ JSONException -> 0x0037 }
            com.facebook.secure.switchoff.RegexWithNegation r2 = (com.facebook.secure.switchoff.RegexWithNegation) r2     // Catch:{ JSONException -> 0x0037 }
            boolean r4 = r6.has(r3)     // Catch:{ JSONException -> 0x0037 }
            if (r4 != 0) goto L_0x002a
            return r0
        L_0x002a:
            java.lang.String r3 = r6.getString(r3)     // Catch:{ JSONException -> 0x0037 }
            boolean r2 = r2.match(r3)     // Catch:{ JSONException -> 0x0037 }
            if (r2 != 0) goto L_0x000b
            return r0
        L_0x0035:
            r6 = 1
            return r6
        L_0x0037:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.switchoff.UriMatcher.match(org.json.JSONObject):boolean");
    }

    @Nullable
    public static UriMatcher parse(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str : COMPONENT_NAMES) {
                if (jSONObject.has(str)) {
                    hashMap.put(str, RegexWithNegation.parse(jSONObject.get(str)));
                }
            }
            if (hashMap.isEmpty()) {
                return null;
            }
            return new UriMatcher(hashMap);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    private static String getUriComponentValue(Uri uri, String str) {
        char c;
        switch (str.hashCode()) {
            case -907987547:
                if (str.equals("scheme")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3433509:
                if (str.equals("path")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 107944136:
                if (str.equals("query")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1475610435:
                if (str.equals("authority")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return uri.getScheme();
        }
        if (c == 1) {
            return uri.getAuthority();
        }
        if (c == 2) {
            return uri.getPath();
        }
        if (c != 3) {
            return null;
        }
        return uri.getQuery();
    }
}
