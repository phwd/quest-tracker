package X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1VR  reason: invalid class name */
public final class AnonymousClass1VR {
    public final Map<String, Pattern> A00;

    public static Map<String, AnonymousClass1VR> A00(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                if (jSONObject2.has("enforce_scheme")) {
                    jSONObject2.getBoolean("enforce_scheme");
                }
                if (jSONObject2.has("enforce_scheme_and_authority")) {
                    jSONObject2.getBoolean("enforce_scheme_and_authority");
                }
                JSONObject jSONObject3 = jSONObject2.getJSONObject("whitelist");
                Iterator<String> keys2 = jSONObject3.keys();
                HashMap hashMap2 = new HashMap();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    hashMap2.put(next2, Pattern.compile(jSONObject3.getString(next2)));
                }
                hashMap.put(next, new AnonymousClass1VR(hashMap2));
            }
        } catch (JSONException unused) {
        }
        return hashMap;
    }

    /* JADX WARN: Incorrect args count in method signature: (ZZLjava/util/Map<Ljava/lang/String;Ljava/util/regex/Pattern;>;)V */
    public AnonymousClass1VR(Map map) {
        this.A00 = map;
    }
}
