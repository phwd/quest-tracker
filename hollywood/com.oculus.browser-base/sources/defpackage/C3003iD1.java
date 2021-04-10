package defpackage;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: iD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3003iD1 extends KL0 {
    public final /* synthetic */ ML0 t;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C3003iD1(ML0 ml0, YV yv) {
        super(ml0, yv, true);
        this.t = ml0;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.t.c;
        RF1 rf1 = this.q;
        Objects.requireNonNull(mf1);
        JSONObject jSONObject = new JSONObject();
        long b = mf1.b();
        try {
            jSONObject.put("requestId", b);
            jSONObject.put("type", "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", mf1.o());
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject.toString(), b, null);
        mf1.x.c(b, rf1);
    }
}
