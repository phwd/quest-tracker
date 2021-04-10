package X;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0Vc  reason: invalid class name and case insensitive filesystem */
public final class C01500Vc {
    public int A00 = 0;
    public long A01;
    public AnonymousClass0WY<String> A02;
    public String A03;
    public String A04;
    public String A05;
    public String A06;
    public List<AnonymousClass0VX> A07 = new ArrayList(50);
    public UUID A08;

    public final String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seq", this.A00);
            jSONObject.put("time", String.format(null, "%.3f", Double.valueOf(((double) this.A01) / 1000.0d)));
            jSONObject.putOpt("app_id", this.A03);
            jSONObject.putOpt("app_ver", this.A04);
            jSONObject.putOpt("build_num", this.A05);
            jSONObject.putOpt("device_id", this.A02.get());
            jSONObject.putOpt("session_id", this.A08);
            jSONObject.putOpt("uid", this.A06);
            if (this.A07 != null) {
                JSONArray jSONArray = new JSONArray();
                for (AnonymousClass0VX r0 : this.A07) {
                    jSONArray.put(r0.A00());
                }
                jSONObject.put("data", jSONArray);
            }
            jSONObject.put("log_type", "client_event");
            return jSONObject.toString();
        } catch (JSONException e) {
            AnonymousClass0NO.A0I("AnalyticsSession", e, "Failed to serialize");
            return "";
        }
    }
}
