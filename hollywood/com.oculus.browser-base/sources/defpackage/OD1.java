package defpackage;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: OD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OD1 extends KL0 {
    public final /* synthetic */ C4953th0 t;
    public final /* synthetic */ ML0 u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OD1(ML0 ml0, YV yv, C4953th0 th0) {
        super(ml0, yv);
        this.u = ml0;
        this.t = th0;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.u.c;
        RF1 rf1 = this.q;
        C4953th0 th0 = this.t;
        Objects.requireNonNull(mf1);
        JSONObject jSONObject = new JSONObject();
        long b = mf1.b();
        Objects.requireNonNull(th0);
        long j = th0.f11361a;
        try {
            jSONObject.put("requestId", b);
            jSONObject.put("type", "SEEK");
            jSONObject.put("mediaSessionId", mf1.o());
            jSONObject.put("currentTime", ((double) j) / 1000.0d);
            int i = th0.b;
            if (i == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (i == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            JSONObject jSONObject2 = th0.c;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject.toString(), b, null);
        mf1.h = Long.valueOf(j);
        mf1.n.c(b, new OF1(mf1, rf1));
    }
}
