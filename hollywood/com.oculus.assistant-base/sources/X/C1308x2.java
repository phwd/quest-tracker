package X;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: X.x2  reason: case insensitive filesystem */
public final class C1308x2 extends XA {
    public int A00;
    public String A01;
    public String A02 = "body1";
    public String A03 = null;
    public final String A04 = "text";

    @Override // X.XA
    public final void A01(JSONObject jSONObject) {
        C0514bB.A02(jSONObject, "jsonObject");
        jSONObject.put("value", this.A03);
        if (!TextUtils.isEmpty(this.A01)) {
            jSONObject.put("gravity", this.A01);
        } else {
            int i = this.A00;
            if (i != 0) {
                StringBuilder sb = new StringBuilder();
                X9.A01(i, sb, "center", 17);
                X9.A01(i, sb, "center-horizontal", 1);
                X9.A01(i, sb, "center-vertical", 16);
                X9.A01(i, sb, "left", 3);
                X9.A01(i, sb, "right", 5);
                X9.A01(i, sb, "top", 48);
                X9.A01(i, sb, "bottom", 80);
                String obj = sb.toString();
                C0514bB.A01(obj, "stringBuilder.toString()");
                jSONObject.put("gravity", obj);
            }
        }
        jSONObject.put("style", this.A02);
    }

    public C1308x2() {
        super(null, 1, null);
    }

    public C1308x2(int i) {
        super(null, 1, null);
        this.A03 = BX.A00().getString(i);
    }
}
