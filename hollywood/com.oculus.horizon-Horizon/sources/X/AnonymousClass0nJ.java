package X;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0nJ  reason: invalid class name */
public abstract class AnonymousClass0nJ implements AbstractC01730Wz {
    public final String A00;
    @GuardedBy("this")
    public final HashMap<AbstractC01720Wy, Object> A01 = new HashMap<>();

    public final synchronized <T> T A00(AbstractC01720Wy r6) {
        HashMap<AbstractC01720Wy, Object> hashMap;
        try {
            hashMap = this.A01;
            if (!hashMap.containsKey(r6)) {
                hashMap.put(r6, r6.getValueType().newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Incorrect usage for %s type %s", r6.getKey(), r6.getValueType()), e);
        }
        return (T) hashMap.get(r6);
    }

    public synchronized JSONObject A01(boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        for (Map.Entry<AbstractC01720Wy, Object> entry : this.A01.entrySet()) {
            jSONObject.putOpt(entry.getKey().getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public final synchronized <T> void A02(AbstractC01720Wy r2, @Nullable T t) {
        this.A01.put(r2, t);
    }

    public final String toString() {
        try {
            return A01(false, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public AnonymousClass0nJ(String str) {
        this.A00 = str;
    }
}
