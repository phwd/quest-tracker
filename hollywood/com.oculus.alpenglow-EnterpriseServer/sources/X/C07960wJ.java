package X;

import com.facebook.acra.constants.ErrorReportingConstants;
import com.oculus.alpenglow.logging.LoggerConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0wJ  reason: invalid class name and case insensitive filesystem */
public final class C07960wJ {
    public int A00 = 0;
    public long A01;
    public AbstractC09610zk<String> A02;
    public String A03;
    public String A04;
    public String A05;
    public String A06;
    public List<C07840w6> A07 = new ArrayList(50);
    public UUID A08;

    public final String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("seq", this.A00);
            jSONObject.put("time", String.format(null, "%.3f", Double.valueOf(((double) this.A01) / 1000.0d)));
            jSONObject.putOpt(LoggerConstants.APP_ID_KEY, this.A03);
            jSONObject.putOpt("app_ver", this.A04);
            jSONObject.putOpt("build_num", this.A05);
            jSONObject.putOpt(LoggerConstants.DEVICE_ID, this.A02.get());
            jSONObject.putOpt("session_id", this.A08);
            jSONObject.putOpt(ErrorReportingConstants.USER_ID_KEY, this.A06);
            if (this.A07 != null) {
                JSONArray jSONArray = new JSONArray();
                for (C07840w6 r0 : this.A07) {
                    jSONArray.put(r0.A00());
                }
                jSONObject.put("data", jSONArray);
            }
            jSONObject.put("log_type", "client_event");
            return jSONObject.toString();
        } catch (JSONException e) {
            AnonymousClass0NK.A0A("AnalyticsSession", e, "Failed to serialize");
            return "";
        }
    }
}
