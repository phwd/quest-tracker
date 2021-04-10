package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class JW {
    public static final List A01 = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    public final Map A00;

    public static JW A00(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                for (String str : A01) {
                    if (jSONObject.has(str)) {
                        hashMap.put(str, JV.A00(jSONObject.get(str)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    return new JW(hashMap);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public JW(Map map) {
        this.A00 = map;
    }
}
