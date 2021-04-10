package X;

import com.oculus.horizon.linkedaccounts.dumper.LinkedAccountsDumperPlugin;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1ae  reason: invalid class name and case insensitive filesystem */
public final class C09261ae {
    public static final List<String> A01 = Collections.unmodifiableList(Arrays.asList("scheme", "authority", "path", LinkedAccountsDumperPlugin.COMMAND_QUERY));
    public final Map<String, C09271af> A00;

    @Nullable
    public static C09261ae A00(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                for (String str : A01) {
                    if (jSONObject.has(str)) {
                        hashMap.put(str, C09271af.A00(jSONObject.get(str)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    return new C09261ae(hashMap);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public C09261ae(Map<String, C09271af> map) {
        this.A00 = map;
    }
}
