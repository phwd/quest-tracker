package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.h1  reason: case insensitive filesystem */
public final class C0399h1 {
    public static final List<String> A01 = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    public final Map<String, C0398h0> A00;

    @Nullable
    public static C0399h1 A00(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                for (String str : A01) {
                    if (jSONObject.has(str)) {
                        hashMap.put(str, C0398h0.A00(jSONObject.get(str)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    return new C0399h1(hashMap);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public C0399h1(Map<String, C0398h0> map) {
        this.A00 = map;
    }
}
