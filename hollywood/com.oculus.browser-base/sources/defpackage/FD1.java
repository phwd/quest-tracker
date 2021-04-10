package defpackage;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: FD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class FD1 extends KL0 {
    public final /* synthetic */ JSONObject t;
    public final /* synthetic */ ML0 u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FD1(ML0 ml0, YV yv, JSONObject jSONObject) {
        super(ml0, yv);
        this.u = ml0;
        this.t = jSONObject;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.u.c;
        RF1 rf1 = this.q;
        JSONObject jSONObject = this.t;
        Objects.requireNonNull(mf1);
        JSONObject jSONObject2 = new JSONObject();
        long b = mf1.b();
        try {
            jSONObject2.put("requestId", b);
            jSONObject2.put("type", "PLAY");
            jSONObject2.put("mediaSessionId", mf1.o());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject2.toString(), b, null);
        mf1.l.c(b, rf1);
    }
}
