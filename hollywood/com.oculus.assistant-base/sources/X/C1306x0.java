package X;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: X.x0  reason: case insensitive filesystem */
public final class C1306x0 extends XA {
    public final List A00 = new ArrayList();

    public C1306x0() {
        super(null, 1, null);
    }

    public final void A02(XA xa) {
        C0514bB.A02(xa, "component");
        List list = this.A00;
        C0514bB.A02(xa, "component");
        XC xc = new XC(this, xa);
        xc.A00 = 1;
        list.add(xc);
    }

    @Override // X.XA
    public final void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "jsonObject");
        JSONArray jSONArray = new JSONArray();
        for (XC xc : this.A00) {
            JSONObject A002 = xc.A01.A00();
            if (A002.has("data")) {
                JSONObject jSONObject2 = A002.getJSONObject("data");
                Integer num = xc.A00;
                if (num != null) {
                    jSONObject2.put("weight", num.intValue());
                }
            }
            jSONArray.put(A002);
        }
        jSONObject.put("components", jSONArray);
        jSONObject.put("orientation", 0);
    }
}
