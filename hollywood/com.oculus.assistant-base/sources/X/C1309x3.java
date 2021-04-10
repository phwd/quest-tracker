package X;

import org.json.JSONObject;

/* renamed from: X.x3  reason: case insensitive filesystem */
public final class C1309x3 extends XA {
    public boolean A00;
    public final String A01;

    @Override // X.XA
    public final void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "json");
        jSONObject.put("checked", this.A00);
    }

    public C1309x3() {
        this(false);
    }

    public C1309x3(boolean z) {
        super(null, 1, null);
        this.A00 = z;
        this.A01 = "toggle";
    }
}
