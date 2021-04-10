package defpackage;

import com.google.android.gms.cast.MediaStatus;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: yI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5746yI1 extends KL0 {
    public final /* synthetic */ ML0 t;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5746yI1(ML0 ml0, YV yv) {
        super(ml0, yv);
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
            jSONObject.put("type", "GET_STATUS");
            MediaStatus mediaStatus = mf1.g;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.G);
            }
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject.toString(), b, null);
        mf1.q.c(b, rf1);
    }
}
