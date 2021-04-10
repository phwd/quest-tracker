package com.facebook.secure.switchoff;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UriMatcher {
    private static final List<String> COMPONENT_NAMES = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    private final Map<String, RegexWithNegation> mUriComponentPattern;

    private UriMatcher(Map<String, RegexWithNegation> map) {
        this.mUriComponentPattern = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(android.net.Uri r4) {
        /*
            r3 = this;
            java.util.Map<java.lang.String, com.facebook.secure.switchoff.RegexWithNegation> r3 = r3.mUriComponentPattern
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x000a:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0031
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r1 = getUriComponentValue(r4, r1)
            r2 = 0
            if (r1 != 0) goto L_0x0024
            return r2
        L_0x0024:
            java.lang.Object r0 = r0.getValue()
            com.facebook.secure.switchoff.RegexWithNegation r0 = (com.facebook.secure.switchoff.RegexWithNegation) r0
            boolean r0 = r0.match(r1)
            if (r0 != 0) goto L_0x000a
            return r2
        L_0x0031:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.switchoff.UriMatcher.match(android.net.Uri):boolean");
    }

    public static UriMatcher parse(JSONObject jSONObject) {
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
