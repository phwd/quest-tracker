package X;

import org.json.JSONObject;

public abstract class XA {
    public static final X9 A06 = new X9();
    public Integer A00;
    public Integer A01;
    public Integer A02;
    public String A03;
    public final XE A04;
    public final XG A05;

    public XA() {
        this(null, 1, null);
    }

    public abstract void A01(JSONObject jSONObject);

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cd, code lost:
        if (r1 != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0115, code lost:
        if (r1 != false) goto L_0x0110;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject A00() {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: X.XA.A00():org.json.JSONObject");
    }

    public /* synthetic */ XA(String str, int i, b9 b9Var) {
        this.A03 = null;
        this.A05 = new XG();
        this.A04 = new XE();
    }
}
