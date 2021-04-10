package X;

import org.json.JSONObject;

public final class QR extends AZ {
    public final /* synthetic */ AnonymousClass7q A00;
    public final /* synthetic */ JSONObject A01;

    public QR(AnonymousClass7q r1, JSONObject jSONObject) {
        this.A00 = r1;
        this.A01 = jSONObject;
    }

    @Override // X.AZ, X.C1300wu
    public final JSONObject A04() {
        JSONObject A04 = super.A04();
        JSONObject jSONObject = this.A01;
        if (jSONObject.getJSONArray("data").length() > 0) {
            A04.put("sections", jSONObject.get("data"));
            return A04;
        }
        C0139Dd.A0D("HelpTriggerCallback", "No help data was received. Falling back to static help data.");
        return A04;
    }
}
