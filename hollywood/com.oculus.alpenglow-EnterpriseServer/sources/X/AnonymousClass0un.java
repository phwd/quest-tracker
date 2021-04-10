package X;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.logging.LoggerConstants;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0un  reason: invalid class name */
public final class AnonymousClass0un {
    public Long A00 = 0L;
    public String A01 = "";
    public String A02 = "";
    public String A03 = "";
    public boolean A04 = false;

    public static AnonymousClass0un A00(String str) throws JSONException {
        AnonymousClass0un r3 = new AnonymousClass0un();
        if (str != null) {
            JSONObject jSONObject = new JSONObject(str);
            r3.A01 = jSONObject.optString(LoggerConstants.APP_ID_KEY);
            r3.A02 = jSONObject.optString("pkg_name");
            r3.A03 = jSONObject.optString("token");
            r3.A00 = Long.valueOf(jSONObject.optLong("time"));
            r3.A04 = jSONObject.optBoolean("invalid");
        }
        return r3;
    }

    public final String A01() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt(LoggerConstants.APP_ID_KEY, this.A01);
        jSONObject.putOpt("pkg_name", this.A02);
        jSONObject.putOpt("token", this.A03);
        jSONObject.putOpt("time", this.A00);
        jSONObject.putOpt("invalid", Boolean.valueOf(this.A04));
        return jSONObject.toString();
    }
}
