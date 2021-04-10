package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.panellib.quickpromotion.QuickPromotionLogEvent;
import java.util.HashMap;
import java.util.Map;
import libraries.marauder.analytics.AnalyticsEventBase;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Kt  reason: invalid class name */
public final class AnonymousClass1Kt {
    public final long A00;
    public final String A01;
    public final String A02;
    public final Map<String, String> A03 = new HashMap();

    public final String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.A02);
            jSONObject.put(AnalyticsEventBase.TIME_KEY, String.format(null, "%.3f", Double.valueOf(((double) this.A00) / 1000.0d)));
            jSONObject.putOpt(QuickPromotionLogEvent.KEY_MODULE, this.A01);
            Map<String, String> map = this.A03;
            if (!map.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
                jSONObject.put(AnalyticsEventBase.EXTRAS_KEY, jSONObject2);
            }
        } catch (JSONException e) {
            AnonymousClass0MD.A0D("AnalyticsEvent", e, "Failed to serialize");
        }
        return jSONObject.toString();
    }

    public AnonymousClass1Kt(String str, String str2) {
        if (str == null) {
            throw null;
        } else if (str2 != null) {
            this.A00 = System.currentTimeMillis();
            this.A02 = str;
            this.A01 = str2;
        } else {
            throw null;
        }
    }
}
