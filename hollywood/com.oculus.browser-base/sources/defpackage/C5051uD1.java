package defpackage;

import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: uD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5051uD1 extends KL0 {
    public final /* synthetic */ int[] t;
    public final /* synthetic */ ML0 u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5051uD1(ML0 ml0, YV yv, int[] iArr) {
        super(ml0, yv, true);
        this.u = ml0;
        this.t = iArr;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.u.c;
        RF1 rf1 = this.q;
        int[] iArr = this.t;
        Objects.requireNonNull(mf1);
        JSONObject jSONObject = new JSONObject();
        long b = mf1.b();
        try {
            jSONObject.put("requestId", b);
            jSONObject.put("type", "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", mf1.o());
            JSONArray jSONArray = new JSONArray();
            for (int i : iArr) {
                jSONArray.put(i);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject.toString(), b, null);
        mf1.y.c(b, rf1);
    }
}
