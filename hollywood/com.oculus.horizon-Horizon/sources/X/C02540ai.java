package X;

import com.facebook.infer.annotation.Nullsafe;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ai  reason: invalid class name and case insensitive filesystem */
public final class C02540ai {
    public Long A00 = 0L;
    public String A01 = "";
    public String A02 = "";
    public String A03 = "";
    public boolean A04 = false;

    public static C02540ai A00(String str) throws JSONException {
        C02540ai r3 = new C02540ai();
        if (str != null) {
            JSONObject jSONObject = new JSONObject(str);
            r3.A01 = jSONObject.optString("app_id");
            r3.A02 = jSONObject.optString("pkg_name");
            r3.A03 = jSONObject.optString("token");
            r3.A00 = Long.valueOf(jSONObject.optLong("time"));
            r3.A04 = jSONObject.optBoolean("invalid");
        }
        return r3;
    }

    public final String A01() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("app_id", this.A01);
        jSONObject.putOpt("pkg_name", this.A02);
        jSONObject.putOpt("token", this.A03);
        jSONObject.putOpt("time", this.A00);
        jSONObject.putOpt("invalid", Boolean.valueOf(this.A04));
        return jSONObject.toString();
    }
}
