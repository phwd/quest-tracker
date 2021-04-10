package defpackage;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: UD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UD1 extends KL0 {
    public final /* synthetic */ boolean t;
    public final /* synthetic */ JSONObject u;
    public final /* synthetic */ ML0 v;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UD1(ML0 ml0, YV yv, boolean z, JSONObject jSONObject) {
        super(ml0, yv);
        this.v = ml0;
        this.t = z;
        this.u = jSONObject;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.v.c;
        RF1 rf1 = this.q;
        boolean z = this.t;
        JSONObject jSONObject = this.u;
        Objects.requireNonNull(mf1);
        JSONObject jSONObject2 = new JSONObject();
        long b = mf1.b();
        try {
            jSONObject2.put("requestId", b);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", mf1.o());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject2.toString(), b, null);
        mf1.p.c(b, rf1);
    }
}
