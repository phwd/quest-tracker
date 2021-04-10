package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0VX  reason: invalid class name */
public final class AnonymousClass0VX {
    public final String A00;
    public final Map<String, String> A01 = new HashMap();
    public final long A02;
    public final String A03;

    public final JSONObject A00() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.A00);
            jSONObject.put("time", String.format(null, "%.3f", Double.valueOf(((double) this.A02) / 1000.0d)));
            jSONObject.putOpt("module", this.A03);
            Map<String, String> map = this.A01;
            if (!map.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
                jSONObject.put("extra", jSONObject2);
                return jSONObject;
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0I("AnalyticsEvent", e, "Failed to serialize");
        }
        return jSONObject;
    }

    public AnonymousClass0VX(String str, String str2) {
        if (str == null || str2 == null) {
            throw null;
        }
        this.A02 = System.currentTimeMillis();
        this.A00 = str;
        this.A03 = str2;
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/util/Map<Ljava/lang/String;*>;)LX/0VX; */
    public final void A01(Map map) {
        String obj;
        for (Map.Entry entry : map.entrySet()) {
            String obj2 = entry.getKey().toString();
            if (entry.getValue() == null) {
                obj = "";
            } else {
                obj = entry.getValue().toString();
            }
            this.A01.put(obj2, obj);
        }
    }

    public final String toString() {
        return A00().toString();
    }
}
