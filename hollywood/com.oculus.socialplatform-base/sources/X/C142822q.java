package X;

import com.facebook.infer.annotation.Nullsafe;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22q  reason: invalid class name and case insensitive filesystem */
public final class C142822q {
    public int A00;
    public String A01 = "";
    public String A02 = "";
    public String A03 = "";
    public String A04 = "";
    public String A05 = "";

    public final String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("ck", this.A01);
            jSONObject.putOpt("cs", this.A02);
            jSONObject.putOpt("di", this.A03);
            jSONObject.putOpt("ds", this.A04);
            jSONObject.put("sr", this.A00);
            jSONObject.putOpt("rc", this.A05);
            return jSONObject.toString();
        } catch (JSONException e) {
            AnonymousClass0MD.A0D("ConnAckPayload", e, "failed to serialize");
            return "";
        }
    }
}
