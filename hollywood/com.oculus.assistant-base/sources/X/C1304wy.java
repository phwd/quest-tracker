package X;

import com.facebook.assistant.oacr.OacrConstants;
import org.json.JSONObject;

/* renamed from: X.wy  reason: case insensitive filesystem */
public final class C1304wy extends XA {
    public String A00;
    public String A01;
    public final String A02;

    @Override // X.XA
    public final void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "jsonObject");
        String str = this.A00;
        if (str != null) {
            jSONObject.put("button-style", str);
        }
        jSONObject.put("text", this.A01);
    }

    public C1304wy() {
        this(OacrConstants.AUTO_SPEECH_DOMAIN);
    }

    public C1304wy(String str) {
        super(null, 1, null);
        this.A01 = str;
        this.A02 = "button";
    }
}
