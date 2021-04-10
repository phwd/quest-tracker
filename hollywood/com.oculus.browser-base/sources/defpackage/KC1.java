package defpackage;

import com.google.android.gms.cast.MediaInfo;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: KC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KC1 extends KL0 {
    public final /* synthetic */ C4092oe0 t;
    public final /* synthetic */ ML0 u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KC1(ML0 ml0, YV yv, C4092oe0 oe0) {
        super(ml0, yv);
        this.u = ml0;
        this.t = oe0;
    }

    @Override // defpackage.KL0
    public final void n(C3350kF1 kf1) {
        MF1 mf1 = this.u.c;
        RF1 rf1 = this.q;
        C4092oe0 oe0 = this.t;
        Objects.requireNonNull(mf1);
        if (oe0.f10565a == null) {
            throw new IllegalArgumentException("MediaInfo and MediaQueueData should not be both null");
        }
        JSONObject jSONObject = new JSONObject();
        long b = mf1.b();
        try {
            jSONObject.put("requestId", b);
            jSONObject.put("type", "LOAD");
            MediaInfo mediaInfo = oe0.f10565a;
            if (mediaInfo != null) {
                jSONObject.put("media", mediaInfo.x());
            }
            Boolean bool = oe0.b;
            if (bool != null) {
                jSONObject.put("autoplay", bool);
            }
            long j = oe0.c;
            if (j != -1) {
                jSONObject.put("currentTime", ((double) j) / 1000.0d);
            }
            jSONObject.put("playbackRate", oe0.d);
            String str = oe0.g;
            if (str != null) {
                jSONObject.put("credentials", str);
            }
            String str2 = oe0.h;
            if (str2 != null) {
                jSONObject.put("credentialsType", str2);
            }
            long[] jArr = oe0.e;
            if (jArr != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < jArr.length; i++) {
                    jSONArray.put(i, jArr[i]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject jSONObject2 = oe0.f;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        mf1.a(jSONObject.toString(), b, null);
        mf1.j.c(b, rf1);
    }
}
