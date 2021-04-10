package X;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: X.wz  reason: case insensitive filesystem */
public class C1305wz extends XA {
    public final List A00 = new ArrayList();

    public C1305wz() {
        super(null, 1, null);
    }

    @Override // X.XA
    public void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "jsonObject");
        JSONArray jSONArray = new JSONArray();
        for (XB xb : this.A00) {
            jSONArray.put(xb.A00.A00());
        }
        jSONObject.put("components", jSONArray);
    }
}
