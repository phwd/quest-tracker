package X;

import androidx.core.content.FileProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0iW  reason: invalid class name */
public final class AnonymousClass0iW {
    public static final List<String> A01 = Collections.unmodifiableList(Arrays.asList("scheme", "authority", FileProvider.ATTR_PATH, "query"));
    public final Map<String, C05060iV> A00;

    @Nullable
    public static AnonymousClass0iW A00(@Nullable JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                HashMap hashMap = new HashMap();
                for (String str : A01) {
                    if (jSONObject.has(str)) {
                        hashMap.put(str, C05060iV.A00(jSONObject.get(str)));
                    }
                }
                if (!hashMap.isEmpty()) {
                    return new AnonymousClass0iW(hashMap);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    public AnonymousClass0iW(Map<String, C05060iV> map) {
        this.A00 = map;
    }
}
