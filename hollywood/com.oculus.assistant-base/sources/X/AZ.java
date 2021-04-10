package X;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AZ extends C1300wu {
    public final List A00 = new ArrayList();

    @Override // X.C1300wu
    public final String A03() {
        return "multisuggestion-dialog";
    }

    @Override // X.C1300wu
    public JSONObject A04() {
        JSONObject A04 = super.A04();
        JSONArray jSONArray = new JSONArray();
        for (W5 w5 : this.A00) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            for (W6 w6 : w5.A01) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("label", w6.A00);
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("header", w5.A00);
            jSONObject.put("items", jSONArray2);
            jSONArray.put(jSONObject);
        }
        A04.put("sections", jSONArray);
        return A04;
    }
}
