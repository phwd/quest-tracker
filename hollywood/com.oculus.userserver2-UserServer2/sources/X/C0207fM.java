package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.fM  reason: case insensitive filesystem */
public final class C0207fM {
    public static final List<String> A01 = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", "query"));
    public final Map<String, C0206fL> A00;

    @Nullable
    public static C0207fM A00(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                for (String str : A01) {
                    if (jSONObject.has(str)) {
                        hashMap.put(str, C0206fL.A00(jSONObject.get(str)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    return new C0207fM(hashMap);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public C0207fM(Map<String, C0206fL> map) {
        this.A00 = map;
    }
}
